package org.openurp.webapp.apps.teach.attendance.service

import org.openurp.webapp.apps.teach.attendance.model.Form
import org.openurp.webapp.apps.teach.attendance.model.Item
import org.openurp.webapp.apps.teach.attendance.model.IdNameObject

trait AttendanceService {

  def count(form: Form): Item

  def findCollegeItem(form: Form): Seq[Item]
  
  def findDepartmentItem(form: Form): Seq[Item]

  def findTeacherItem(form: Form): Seq[Item]

  def findLessonItem(form: Form): Seq[Item]

  def findStudentItem(form: Form): Seq[Item]

  def findAdminclassItem(form: Form): Seq[Item]

  def findAttendItem(form: Form): Seq[Item]

  def getTeacher(teacherId: Long): IdNameObject

  def getCollege(teacherId: Long): IdNameObject

  def getLesson(jxrwId: Long): IdNameObject

  def getAdminclass(adminclassId: Long): IdNameObject

  def getStudent(studentId: Long): IdNameObject

  def getTeacherId(code: String): Long
  
  def getStudentId(code: String): Long
  
}