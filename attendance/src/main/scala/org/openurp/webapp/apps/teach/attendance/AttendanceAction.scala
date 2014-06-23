package org.openurp.webapp.apps.teach.attendance

import org.beangle.struts2.action.EntityDrivenAction
import org.openurp.webapp.apps.teach.attendance.model.Form
import org.openurp.webapp.apps.teach.attendance.service.AttendanceService
import java.sql.Date
import org.openurp.webapp.apps.teach.attendance.model.Item

class AttendanceAction extends EntityDrivenAction {

  var attendanceService: AttendanceService = _

  override protected def entityType: Class[_] = classOf[Form]
  override def search() = forward
  override def edit() = null
  override def save() = null
  override def remove() = null

  override def index() = {
    val form = populateForm()
    val item = attendanceService.count(form)
    put("item", item)
    put("college", attendanceService getCollege form.collegeId)
    if(form.departmentId != 0){
      put("college", attendanceService getCollege form.departmentId)
    }
    put("teacher", attendanceService getTeacher form.teacherId)
    put("lesson", attendanceService getLesson form.jxrwId)
    put("adminclass", attendanceService getAdminclass form.adminclassId)
    put("student", attendanceService getStudent form.studentId)
    put("f", form)
    put("items", findItem(form))
    val action = {
      val action = getRequest.getRequestURI() +
        (if (getRequest.getQueryString() != null) "?" + getRequest.getQueryString() else "")
      if (action.lastIndexOf("&f.days=") > 0) {
        action.substring(0, action.lastIndexOf("&f.days="))
      } else action
    }
    put("formAction", action)
    forward
  }

  protected def findItem(form: Form): Seq[Item] = null

  def populateForm(): Form = {
    val form = new Form()
    populate(form, "f").asInstanceOf[Form]
    form
  }

}