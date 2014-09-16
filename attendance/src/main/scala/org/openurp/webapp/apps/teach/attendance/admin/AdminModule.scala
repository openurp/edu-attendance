package org.openurp.webapp.apps.teach.attendance.admin

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.webapp.apps.teach.attendance.admin.action.TeacherAction
import org.openurp.webapp.apps.teach.attendance.admin.action.CollegeAction
import org.openurp.webapp.apps.teach.attendance.admin.action.LessonAction
import org.openurp.webapp.apps.teach.attendance.admin.action.StudentAction
import org.openurp.webapp.apps.teach.attendance.SecurityInterceptor
import org.openurp.webapp.apps.teach.attendance.HttpMethodPermissionFilter

class AdminModule extends AbstractBindModule {

  override def binding(): Unit = {
    bind(classOf[CollegeAction], classOf[TeacherAction], classOf[LessonAction], classOf[StudentAction])
    bind("mvc.Interceptor.security",classOf[SecurityInterceptor])
  }
}