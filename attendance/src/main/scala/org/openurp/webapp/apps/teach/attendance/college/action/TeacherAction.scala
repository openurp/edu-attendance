package org.openurp.webapp.apps.teach.attendance.college.action

import org.openurp.webapp.apps.teach.attendance.AttendanceAction
import org.openurp.webapp.apps.teach.attendance.model.Form

class TeacherAction extends CollegeAction {

  override protected def findItem(form: Form) = attendanceService findTeacherItem form

}