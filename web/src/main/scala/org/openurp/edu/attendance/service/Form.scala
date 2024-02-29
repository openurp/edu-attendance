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

import org.beangle.data.model.{Entity, IntId}

import java.sql.Date
import java.text.SimpleDateFormat

class Form extends IntId {
  var startDate: Date = _
  var endDate: Date = _
  var days: Long = _
  var kkxyId: Int = _
  var enableKkxyId = true
  var skxyId:Int = _
  var teacherId: Long = _
  var teacherName: String = _
  var studentId:Long = _
  var studentName: String = _
  var jxrwId: Long = _
  var adminclassId: Long = _
  var adminclassName:String = _
  var counselorId:Long = _
  var sortBy: String = _
  def params: String = {
    (if (jxrwId != 0) s"&f.jxrwId=${jxrwId}" else "") +
      (if (teacherId != 0) s"&f.teacherId=${teacherId}" else "") +
      (if (adminclassId != 0) s"&f.adminclassId=${adminclassId}" else "") +
      (if (kkxyId != 0) s"&f.kkxyId=${kkxyId}" else "") +
      (if (skxyId != 0) s"&f.skxyId=${skxyId}" else "") + dayParams
  }
  def dayParams: String = {
    val sdf = new SimpleDateFormat("YYYY-MM-dd")
    s"&f.days=${days}" +
      s"&f.startDate=${sdf.format(startDate)}" +
      s"&f.endDate=${sdf.format(endDate)}"
  }
}

object Item {
  val signKeys = Set(1, 3, 5)
}
class Item(val id: Any = 0) {
  var code: String = _
  var name: String = _
  var name2: String = _
  var name3: String = _
  val num = {
    val map = new collection.mutable.HashMap[Int, Int]();
    for (i <- 1 to 5) map.put(i, 0)
    map
  }
  def +(value: (Int, Int)) = {
    num(value._1) = value._2
  }
  def total: Int = { num.values.sum }
  def signNum = {
    num.filterKeys(key => Item.signKeys.contains(key)).values.sum
  }
  def signRate = rate(signNum)
  def attendNum = num(1)
  def attendRate = rate(attendNum)
  def absenceNum = num(2)
  def absenceRate = rate(absenceNum)
  def lateNum = num(3)
  def lateRate = rate(lateNum)
  def offNum = num(5)
  def offRate = rate(offNum)
  def rate(num: Int) = {
    if (total == 0) {
      0
    } else {
      100.0 * num / total
    }
  }
}

class IdNameObject(val id: Any, val name: String)
