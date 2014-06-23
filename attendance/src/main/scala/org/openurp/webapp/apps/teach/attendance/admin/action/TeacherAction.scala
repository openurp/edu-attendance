package org.openurp.webapp.apps.teach.attendance.admin.action

import org.beangle.struts2.action.EntityDrivenAction
import org.openurp.webapp.apps.teach.attendance.AttendanceAction
import org.openurp.webapp.apps.teach.attendance.model.Form

class TeacherAction extends AttendanceAction {

  override protected def findItem(form: Form) = attendanceService findTeacherItem form
}