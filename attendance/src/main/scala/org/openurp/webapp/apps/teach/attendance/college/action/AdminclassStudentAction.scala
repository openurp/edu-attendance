package org.openurp.webapp.apps.teach.attendance.college.action

import org.beangle.struts2.action.EntityDrivenAction
import org.openurp.webapp.apps.teach.attendance.AttendanceAction
import org.openurp.webapp.apps.teach.attendance.model.Form

class AdminclassStudentAction extends CollegeAction{
  
  override protected def findItem(form: Form) = attendanceService findStudentItem form
}