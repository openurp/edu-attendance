package org.openurp.webapp.apps.teach.attendance.counselor.action

import org.beangle.struts2.action.EntityDrivenAction
import org.openurp.webapp.apps.teach.attendance.AttendanceAction
import org.openurp.webapp.apps.teach.attendance.model.Form

class AdminclassAction extends CounselorAction{

  override protected def findItem(form: Form) = attendanceService findAdminclassItem form
  
}