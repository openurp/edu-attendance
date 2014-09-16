package org.openurp.webapp.apps.teach.attendance.stumgr.action


import org.openurp.webapp.apps.teach.attendance.AttendanceAction
import org.openurp.webapp.apps.teach.attendance.model.Form

class CollegeAction extends AttendanceAction{

  override protected def findItem(form: Form) = attendanceService findSkxyItem form
  
}