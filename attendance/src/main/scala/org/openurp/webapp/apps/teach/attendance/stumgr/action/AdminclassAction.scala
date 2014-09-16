package org.openurp.webapp.apps.teach.attendance.stumgr.action


import org.openurp.webapp.apps.teach.attendance.AttendanceAction
import org.openurp.webapp.apps.teach.attendance.model.Form

class AdminclassAction extends AttendanceAction{

  override protected def findItem(form: Form) = attendanceService findAdminclassItem form
  
}