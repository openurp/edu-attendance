package org.openurp.webapp.apps.teach.attendance.stumgr

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.webapp.apps.teach.attendance.stumgr.action.CollegeAction
import org.openurp.webapp.apps.teach.attendance.stumgr.action.AdminclassAction
import org.openurp.webapp.apps.teach.attendance.stumgr.action.StudentAction
import org.openurp.webapp.apps.teach.attendance.stumgr.action.LessonAction

class StumgrModule extends AbstractBindModule {

  override def binding(): Unit = {
    bind(classOf[CollegeAction], classOf[AdminclassAction], classOf[StudentAction], classOf[LessonAction])
  }
}