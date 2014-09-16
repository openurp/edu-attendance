package org.openurp.webapp.apps.teach.attendance

import org.openurp.webapp.apps.teach.attendance.model.Form
import org.openurp.webapp.apps.teach.attendance.service.AttendanceService
import java.sql.Date
import org.openurp.webapp.apps.teach.attendance.model.Item
import org.beangle.security.context.SecurityContext
import org.beangle.security.authc.Account
import org.openurp.kernel.base.unit.model.UrpUserBean
import org.beangle.webmvc.entity.action.EntityDrivenAction

class AttendanceAction extends EntityDrivenAction {

  var attendanceService: AttendanceService = _

  override def getEntityType: Class[_] = classOf[Form]
  override def search() = forward()
  override def edit() = null
  override def save() = null
  override def remove() = null

  protected def getUser(): UrpUserBean = {
  getUserId() match {
      case Some(id) =>
        entityDao.get(classOf[UrpUserBean], SecurityContext.principal.asInstanceOf[Account].id)
      case _ => null
    }
  }

  protected def getUserId(): Option[Long] = {
    if (SecurityContext.principal == SecurityContext.Anonymous) None
    else Some(SecurityContext.principal.asInstanceOf[Account].id.asInstanceOf[Long])
  }

  override def index() = {
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
    val action = {
      val action = request.getRequestURI() +
        (if (request.getQueryString() != null) "?" + request.getQueryString() else "")
      if (action.lastIndexOf("&f.days=") > 0) {
        action.substring(0, action.lastIndexOf("&f.days="))
      } else action
    }
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
    attendanceService.getTeacherId(getUser.code)
  }

  def getStuentId(): Long = {
    attendanceService.getStudentId(getUser.code)
  }

}