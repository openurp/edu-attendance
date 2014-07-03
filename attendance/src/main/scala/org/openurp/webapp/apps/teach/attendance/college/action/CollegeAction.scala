package org.openurp.webapp.apps.teach.attendance.college.action

import org.beangle.struts2.action.EntityDrivenAction
import org.openurp.webapp.apps.teach.attendance.AttendanceAction
import org.openurp.webapp.apps.teach.attendance.model.Form

class CollegeAction extends AttendanceAction {

  override def populateForm(): Form = {
    val form = super.populateForm
    form.collegeId = getUser.department.id.asInstanceOf[Number].intValue()
    form
  }
}