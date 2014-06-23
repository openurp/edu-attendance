package org.openurp.webapp.apps.teach.attendance.college

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.webapp.apps.teach.attendance.college.action.AdminclassAction
import org.openurp.webapp.apps.teach.attendance.college.action.AdminclassStudentAction
import org.openurp.webapp.apps.teach.attendance.college.action.StudentLessonAction
import org.openurp.webapp.apps.teach.attendance.college.action.TeacherAction
import org.openurp.webapp.apps.teach.attendance.college.action.TeacherLessonAction
import org.openurp.webapp.apps.teach.attendance.college.action.LessonStudentAction

class CollegeModule extends AbstractBindModule {

  override def binding(): Unit = {
    bind(classOf[AdminclassAction], classOf[AdminclassStudentAction], classOf[StudentLessonAction])
    bind(classOf[TeacherAction], classOf[TeacherLessonAction], classOf[LessonStudentAction])
  }
}