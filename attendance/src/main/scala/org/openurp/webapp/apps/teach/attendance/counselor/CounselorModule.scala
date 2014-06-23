package org.openurp.webapp.apps.teach.attendance.counselor

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.webapp.apps.teach.attendance.counselor.action.AdminclassAction
import org.openurp.webapp.apps.teach.attendance.counselor.action.StudentAction
import org.openurp.webapp.apps.teach.attendance.counselor.action.LessonAction

class CounselorModule extends AbstractBindModule {

  override def binding(): Unit = {
    bind(classOf[AdminclassAction], classOf[StudentAction], classOf[LessonAction])
  }
}