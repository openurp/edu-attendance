package org.openurp.webapp.apps.teach.attendance.student.action

import org.openurp.webapp.apps.teach.attendance.AttendanceAction
import org.openurp.webapp.apps.teach.attendance.model.Form

class StudentAction extends AttendanceAction {

  override def populateForm(): Form = {
    val form = super.populateForm
    form.studentId = getUserId
    form.days  = 1
    form
  }
  
  def getUserId = 68939
}