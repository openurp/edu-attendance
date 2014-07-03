package org.openurp.webapp.apps.teach.attendance.student.action

import org.beangle.struts2.action.EntityDrivenAction
import org.openurp.webapp.apps.teach.attendance.AttendanceAction
import org.openurp.webapp.apps.teach.attendance.model.Form

class AttendAction extends StudentAction {

  override protected def findItem(form: Form) = attendanceService findAttendItem form

}