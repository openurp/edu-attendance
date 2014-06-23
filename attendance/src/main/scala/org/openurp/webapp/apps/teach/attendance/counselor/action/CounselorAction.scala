package org.openurp.webapp.apps.teach.attendance.counselor.action

import org.openurp.webapp.apps.teach.attendance.AttendanceAction
import org.openurp.webapp.apps.teach.attendance.model.Form

class CounselorAction extends AttendanceAction{
  
  override def populateForm(): Form = {
    val form = super.populateForm
    form.counselorId = 282
    form
  }
}