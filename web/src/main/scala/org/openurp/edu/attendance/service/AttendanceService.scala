/*
 * Copyright (C) 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.openurp.edu.attendance.service

trait AttendanceService {

  def count(form: Form): Item

  def findKkxyItem(form: Form): Seq[Item]

  def findSkxyItem(form: Form): Seq[Item]

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
