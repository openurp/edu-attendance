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

package org.openurp.edu.attendance.web.action.admin

import org.beangle.data.dao.{EntityDao, OqlBuilder}
import org.openurp.edu.attendance.model.{StdLeave, StdLeaveLesson}
import org.openurp.edu.clazz.model.{Clazz, CourseTaker}
import org.openurp.edu.schedule.service.LessonSchedule

import java.time.{Duration, ZoneId}

class StdLeaveServiceImpl extends StdLeaveService {

  var entityDao: EntityDao = _

  override def generateLeaveLessons(leave: StdLeave): Boolean = {
    val exists = entityDao.findBy(classOf[StdLeaveLesson], "leave", leave)
    val duration = Duration.between(leave.beginAt, leave.endAt)
    leave.days = Math.ceil(duration.getSeconds / (24 * 60 * 60.0)).toInt
    if (exists.isEmpty) {
      val takerQuery = OqlBuilder.from(classOf[CourseTaker], "ct")
      takerQuery.where("ct.std=:std and ct.semester=:semester", leave.std, leave.semester)
      val takers = entityDao.search(takerQuery)
      val activities = takers.flatMap(_.clazz.schedule.activities)
      val bAt = leave.beginAt.atZone(ZoneId.systemDefault()).toLocalDateTime
      val eAt = leave.endAt.atZone(ZoneId.systemDefault()).toLocalDateTime
      val schedules = LessonSchedule.convert(activities, bAt, eAt)
      val leaveLessons = schedules.map { s =>
        val ll = new StdLeaveLesson
        ll.std = leave.std
        val clazz = entityDao.get(classOf[Clazz], s.task.id.toLong)
        ll.clazz = clazz
        ll.semester = clazz.semester
        ll.leaveType = leave.leaveType
        ll.lessonOn = s.date
        ll.lessonTime = s.time
        ll.leave = Some(leave)
        ll
      }
      leave.lessons = leaveLessons.size
      entityDao.saveOrUpdate(leaveLessons)
      entityDao.saveOrUpdate(leave)
      true
    } else {
      entityDao.saveOrUpdate(leave)
      false
    }
  }
}
