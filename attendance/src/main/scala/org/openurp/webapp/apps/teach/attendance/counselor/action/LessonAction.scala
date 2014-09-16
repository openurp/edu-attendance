package org.openurp.webapp.apps.teach.attendance.counselor.action

import org.openurp.webapp.apps.teach.attendance.AttendanceAction
import org.openurp.webapp.apps.teach.attendance.model.Form

class LessonAction extends CounselorAction {

  override protected def findItem(form: Form) = attendanceService findLessonItem form

}