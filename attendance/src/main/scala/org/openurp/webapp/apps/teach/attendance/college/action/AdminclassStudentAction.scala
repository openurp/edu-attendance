package org.openurp.webapp.apps.teach.attendance.college.action

import org.openurp.webapp.apps.teach.attendance.AttendanceAction
import org.openurp.webapp.apps.teach.attendance.model.Form

class AdminclassStudentAction extends CollegeAction{
  
  override def populateForm(): Form = {
    val form = super.populateForm()
    form.enableKkxyId = false
    form
  }
  
  override protected def findItem(form: Form) = attendanceService findStudentItem form
}