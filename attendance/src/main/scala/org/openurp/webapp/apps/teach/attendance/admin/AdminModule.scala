package org.openurp.webapp.apps.teach.attendance.admin

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.webapp.apps.teach.attendance.admin.action.TeacherAction
import org.openurp.webapp.apps.teach.attendance.admin.action.CollegeAction
import org.openurp.webapp.apps.teach.attendance.admin.action.LessonAction

class AdminModule extends AbstractBindModule {

  def doBinding(): Unit = {
    bind(classOf[CollegeAction], classOf[TeacherAction], classOf[LessonAction])
  }
}