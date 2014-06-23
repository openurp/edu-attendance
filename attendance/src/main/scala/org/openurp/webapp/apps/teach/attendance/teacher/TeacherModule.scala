package org.openurp.webapp.apps.teach.attendance.teacher

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.webapp.apps.teach.attendance.teacher.action.LessonAction
import org.openurp.webapp.apps.teach.attendance.teacher.action.StudentAction

class TeacherModule extends AbstractBindModule {

  override def binding(): Unit = {
    bind(classOf[LessonAction], classOf[StudentAction])
  }
}