package org.openurp.webapp.apps.teach.attendance.student

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.webapp.apps.teach.attendance.student.action.LessonAction
import org.openurp.webapp.apps.teach.attendance.student.action.AttendAction

class StudentModule extends AbstractBindModule {
  
  override def binding(): Unit = {
    bind(classOf[LessonAction], classOf[AttendAction])
  }
}