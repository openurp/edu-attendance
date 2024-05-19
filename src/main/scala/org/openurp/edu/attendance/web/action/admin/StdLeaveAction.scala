/*
 * Copyright (C) 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.openurp.edu.attendance.web.action.admin

import org.beangle.commons.activation.MediaTypes
import org.beangle.data.dao.OqlBuilder
import org.beangle.doc.excel.schema.ExcelSchema
import org.beangle.doc.transfer.importer.ImportSetting
import org.beangle.web.action.annotation.{mapping, param, response}
import org.beangle.web.action.view.{Stream, View}
import org.beangle.webmvc.support.action.{ExportSupport, ImportSupport, RestfulAction}
import org.openurp.base.model.Project
import org.openurp.edu.attendance.model.{LeaveType, StdLeave, StdLeaveLesson}
import org.openurp.starter.web.support.ProjectSupport

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}
import java.time.ZoneId

class StdLeaveAction extends RestfulAction[StdLeave], ProjectSupport, ImportSupport[StdLeave], ExportSupport[StdLeave] {

  var stdLeaveService: StdLeaveService = _

  override protected def indexSetting(): Unit = {
    given project: Project = getProject

    put("project", project)
    put("semester", getSemester)
    put("leaveTypes", LeaveType.values)
    put("departs", getDeparts)
    super.indexSetting()
  }

  override protected def editSetting(entity: StdLeave): Unit = {
    given project: Project = getProject

    put("project", project)
    put("semester", getSemester)
    super.editSetting(entity)
  }

  override def getQueryBuilder: OqlBuilder[StdLeave] = {
    val query = super.getQueryBuilder
    getDate("leaveOn") foreach { date =>
      query.where(":leaveOn between date(stdLeave.beginAt) and date(stdLeave.endAt)", date)
    }
    query
  }

  def generateLeaveLessons(): View = {
    val leaves = entityDao.find(classOf[StdLeave], getLongIds("stdLeave"))
    var i = 0
    leaves foreach { leave =>
      val success = stdLeaveService.generateLeaveLessons(leave)
      val exists = entityDao.findBy(classOf[StdLeaveLesson], "leave", leave)
      if (success) i += 1
    }
    redirect("search", s"生成成功${i}个请假课程")
  }

  @response
  def downloadTemplate(): Any = {
    given project: Project = getProject

    val leaveTypes = LeaveType.values.map(x => x.name)
    val schema = new ExcelSchema()
    val sheet = schema.createScheet("数据模板")
    sheet.title("请假信息模板")
    sheet.remark("特别说明：\n1、不可改变本表格的行列结构以及批注，否则将会导入失败！\n2、必须按照规格说明的格式填写。\n3、可以多次导入，重复的信息会被新数据更新覆盖。\n4、保存的excel文件名称可以自定。")
    sheet.add("学号", "std.code").length(15).required().remark("≤15位")
    sheet.add("类型", "leaveType").ref(leaveTypes).required()

    sheet.add("起始时间", "stdLeave.beginAt").remark("yyyy-MM-dd HH:mm:ss").datatime().required()
    sheet.add("结束时间", "stdLeave.endAt").remark("yyyy-MM-dd HH:mm:ss").datatime().required()
    sheet.add("事由", "stdLeave.reason").remark("100字以内").required()
    val os = new ByteArrayOutputStream()
    schema.generate(os)
    Stream(new ByteArrayInputStream(os.toByteArray), MediaTypes.ApplicationXlsx.toString, "学生请假信息.xlsx")
  }

  @mapping(value = "{id}")
  override def info(@param("id") id: String): View = {
    val leave = entityDao.get(classOf[StdLeave], id.toLong)
    val lessons = entityDao.findBy(classOf[StdLeaveLesson], "leave", leave)
    put("leave", leave)
    put("lessons", lessons)
    forward()
  }

  override protected def saveAndRedirect(leave: StdLeave): View = {
    leave.semester = semesterService.get(getProject, leave.beginAt.atZone(ZoneId.systemDefault()).toLocalDate)
    entityDao.saveOrUpdate(leave)
    stdLeaveService.generateLeaveLessons(leave)
    super.saveAndRedirect(leave)
  }

  protected override def configImport(setting: ImportSetting): Unit = {
    setting.listeners = List(new StdLeaveImportListener(entityDao, semesterService, stdLeaveService, getProject))
  }
}
