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

import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.beangle.doc.transfer.importer.{ImportListener, ImportResult}
import org.openurp.base.model.Project
import org.openurp.base.service.SemesterService
import org.openurp.base.std.model.Student
import org.openurp.edu.attendance.model.{LeaveType, StdLeave}

import java.time.{LocalDateTime, ZoneId}

class StdLeaveImportListener(entityDao: EntityDao, semesterService: SemesterService,
                             stdLeaveService: StdLeaveService, project: Project) extends ImportListener {

  override def onItemStart(tr: ImportResult): Unit = {
    transfer.curData.get("std.code") foreach { code =>
      val cs = entityDao.findBy(classOf[Student], "code" -> code, "project" -> project)
      if (cs.nonEmpty) {
        val q = OqlBuilder.from(classOf[StdLeave], "leave")
        q.where("leave.std=:std", cs.head)
        q.where("to_char(leave.beginAt,'yyyy-MM-dd') =:beginOn",
          transfer.curData("stdLeave.beginAt").asInstanceOf[LocalDateTime].toLocalDate.toString)
        val leaves = entityDao.search(q)
        if (leaves.nonEmpty) {
          tr.transfer.current = leaves.head
        } else {
          val off = new StdLeave
          off.std = cs.head
          tr.transfer.current = off
        }
      }
    }
  }

  override def onItemFinish(tr: ImportResult): Unit = {
    val leave = transfer.current.asInstanceOf[StdLeave]
    transfer.curData.get("leaveType") foreach { offTypeName =>
      LeaveType.values find (x => x.name == offTypeName) foreach { t =>
        leave.leaveType = t
      }
    }
    if (!leave.endAt.isAfter(leave.beginAt)) {
      tr.addFailure("结束时间不能早于开始时间", leave.endAt)
    } else {
      val semester = semesterService.get(project, leave.beginAt.atZone(ZoneId.systemDefault()).toLocalDate)
      leave.semester = semester
      entityDao.saveOrUpdate(leave)
      stdLeaveService.generateLeaveLessons(leave)
    }
  }
}
