package org.openurp.webapp.apps.teach.attendance.college.action

import org.beangle.struts2.action.EntityDrivenAction
import org.openurp.webapp.apps.teach.attendance.AttendanceAction
import org.openurp.webapp.apps.teach.attendance.model.Form

class StudentLessonAction extends CollegeAction{
  
  override def populateForm(): Form = {
    val form = super.populateForm()
    form.enableKkxyId = false
    form
  }

  override protected def findItem(form: Form) = attendanceService findLessonItem form

}