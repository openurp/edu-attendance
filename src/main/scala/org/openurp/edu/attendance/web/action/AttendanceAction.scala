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

package org.openurp.edu.attendance.web.action

import org.beangle.data.dao.EntityDao
import org.beangle.security.Securities
import org.beangle.webmvc.context.ActionContext
import org.beangle.webmvc.support.ActionSupport
import org.beangle.webmvc.support.action.EntityAction
import org.openurp.base.model.User
import org.openurp.edu.attendance.service.{AttendanceService, Form, Item}

class AttendanceAction extends ActionSupport, EntityAction[Form] {

  var entityDao: EntityDao = _
  var attendanceService: AttendanceService = _

  protected def getUser(): User = {
    entityDao.findBy(classOf[User], "code", Securities.user).head
  }

  def index() = {
    val form = populateForm()
    val item = attendanceService.count(form)
    put("item", item)
    put("college", attendanceService getCollege form.kkxyId)
    if (form.skxyId != 0) {
      put("college", attendanceService getCollege form.skxyId)
    }
    put("teacher", attendanceService getTeacher form.teacherId)
    put("lesson", attendanceService getLesson form.jxrwId)
    put("adminclass", attendanceService getAdminclass form.adminclassId)
    put("student", attendanceService getStudent form.studentId)
    put("f", form)
    put("items", findItem(form))
    val request = ActionContext.current.request
    val action = {
      val action = request.getRequestURI() +
        (if (request.getQueryString() != null) "?" + request.getQueryString() else "")
      if (action.lastIndexOf("&f.days=") > 0) {
        action.replaceAll("&f\\.days=[-]?\\d+", "")
      } else action
    }
    //    println(action)
    put("formAction", action)
    forward()
  }

  protected def findItem(form: Form): Seq[Item] = null

  def populateForm(): Form = {
    val form = new Form()
    populate(form, "f").asInstanceOf[Form]
    form
  }

  def getTeacherId(): Long = {
    attendanceService.getTeacherId(getUser().code)
  }

  def getStuentId(): Long = {
    attendanceService.getStudentId(getUser().code)
  }

}
