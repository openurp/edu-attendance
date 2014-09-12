package org.openurp.webapp.apps.teach.attendance.service.impl

import org.openurp.webapp.apps.teach.attendance.service.AttendanceService
import org.openurp.webapp.apps.teach.attendance.model.Form
import org.openurp.webapp.apps.teach.attendance.model.Item
import org.beangle.data.model.dao.GeneralDao
import org.beangle.data.jpa.dao.SqlBuilder
import java.sql.Date
import org.beangle.data.model.dao.Query
import scala.collection.mutable.HashMap
import org.openurp.webapp.apps.teach.attendance.model.IdNameObject
import scala.math.Ordering

class AttendanceServiceImpl extends AttendanceService {

  var attendanceDao: GeneralDao = _

  def count(form: Form): Item = {
    setDate(form)
    val sql = """
      select d.attendtype, count(*)
      from dutyreport1 d""" +
      (if (form.collegeId != 0) " join jxrw_t rw on d.jxrwid = rw.id" else "") +
      (if (form.teacherId != 0) " join jxrw_ls_t rwls on rwls.jxrwid = d.jxrwid" else "") +
      (if (form.adminclassId != 0 || form.counselorId != 0)
        """ join jcxx_bj_xs_t bjxs on bjxs.xsid = d.stuid
        join jcxx_bj_t bj on bj.id = bjxs.bjid"""
      else "") +
      " where d.attenddate between :start and :end" +
      (if (form.jxrwId != 0) s" and d.jxrwid=${form.jxrwId}" else "") +
      (if (form.studentId != 0) s" and d.stuid=${form.studentId}" else "") +
      (if (form.teacherId != 0) s" and rwls.lsid=${form.teacherId}" else "") +
      (if (form.counselorId != 0) s" and bj.fdyid=${form.counselorId}" else "") +
      (if (form.adminclassId != 0) s" and bjxs.bjid=${form.adminclassId}" else "") +
      (if (form.lessonCollegeId != 0) s" and rw.kkyx=${form.lessonCollegeId}" else "") +
      (if (form.collegeId != 0) s" and d.departmentid=${form.collegeId}" else "") +
      " group by d.attendtype"
    val query = SqlBuilder.sql(sql).params(Map[String, Any]()).param("start", form.startDate).param("end", form.endDate)
    val result = attendanceDao.search(query.build)
    val count = new Item()
    for (o <- result) {
      val array = o.asInstanceOf[Array[Object]]
      count + (Integer.parseInt(array(0).toString()), Integer.parseInt(array(1).toString()))
    }
    count
  }

  def setDate(form: Form) {
    if (form.days == 1) {
      val now = new Date(System.currentTimeMillis())
      val sql = "select max(qssj), max(jzsj) from jxrl_t where qssj <= :now order by qssj desc"
      val query = SqlBuilder.sql(sql).params(Map[String, Any]()).param("now", now)
      val result = attendanceDao.search(query.build)
      if (result.size > 0) {
        val oo = result(0)
        form.startDate = new Date(oo(0).asInstanceOf[java.util.Date].getTime())
        form.endDate = new Date(oo(1).asInstanceOf[java.util.Date].getTime())
      } else {
        form.days = 0;
      }
    }
    if (form.days <= 0) {
      form.startDate = new Date(System.currentTimeMillis() + form.days * 24 * 60 * 60 * 1000)
      form.endDate = if (form.days == -1) form.startDate else new Date(System.currentTimeMillis())
    }
  }

  def findCollegeItem(form: Form): Seq[Item] = {
    val sql = """
      select bm.id, d.attendtype, count(*) as num, bm.bmdm, bm.bmmc
      from dutyreport1 d
      join jxrw_t rw on rw.id = d.jxrwid
      join jcxx_bm_t bm on bm.id = rw.kkyx
      where d.attenddate between :start and :end
      group by bm.id, bm.bmdm, bm.bmmc, d.attendtype
      """
    val query = SqlBuilder.sql(sql).params(Map[String, Any]())
    find(query, form)
  }

  def findDepartmentItem(form: Form): Seq[Item] = {
    val sql = """
      select bm.id, d.attendtype, count(*) as num, bm.bmdm, bm.bmmc
      from dutyreport1 d
      join jcxx_bm_t bm on d.departmentid = bm.id
      where d.attenddate between :start and :end
      group by bm.id, bm.bmdm, bm.bmmc, d.attendtype
      """
    val query = SqlBuilder.sql(sql).params(Map[String, Any]())
    find(query, form)
  }

  def findTeacherItem(form: Form): Seq[Item] = {
    val sql = """
      select ls.id, d.attendtype, count(*) as num, ls.jszgh, ls.xm
      from dutyreport1 d
      join jxrw_t rw on rw.id = d.jxrwid
      join jxrw_ls_t rwls on rwls.jxrwid = d.jxrwid
      join jcxx_jzg_t ls on ls.id = rwls.lsid
      where d.attenddate between :start and :end""" +
      (if (form.collegeId != 0) s" and rw.kkyx = ${form.collegeId}" else "") +
      (if (form.teacherName != null) s" and ls.xm like '%${form.teacherName}%'" else "") +
      " group by ls.id, ls.jszgh, ls.xm, d.attendtype"
    val query = SqlBuilder.sql(sql).params(Map[String, Any]())
    find(query, form)
  }

  def findLessonItem(form: Form): Seq[Item] = {
    val sql = """
      select rw.id, d.attendtype, count(*) as num, rw.kcxh, kc.kcmc, rw.jxbmc, ls.xm
      from dutyreport1 d
      join jxrw_t rw on rw.id = d.jxrwid
      join jxrw_ls_t rwls on rwls.jxrwid = d.jxrwid
      join jcxx_jzg_t ls on ls.id = rwls.lsid
      join jcxx_kc_t kc on kc.id = rw.kcid
      where d.attenddate between :start and :end""" +
      (if (form.teacherId != 0) s" and rwls.lsid = ${form.teacherId}" else "") +
      (if (form.studentId != 0) s" and d.stuid = ${form.studentId}" else "") +
      " group by rw.id, rw.kcxh, kc.kcmc, rw.jxbmc, ls.xm, d.attendtype"
    val query = SqlBuilder.sql(sql).params(Map[String, Any]())
    find(query, form)
  }

  def findStudentItem(form: Form): Seq[Item] = {
    val sql = """
      select xs.id, d.attendtype, count(*) as num, xs.xh, xs.xm
      from dutyreport1 d
      join xsxx_t xs on xs.id = d.stuid""" +
      (if (form.adminclassId != 0) " join jcxx_bj_xs_t bjxs on bjxs.xsid = d.stuid" else "" )+
      " where d.attenddate between :start and :end" +
      (if (form.jxrwId != 0) s" and d.jxrwid = ${form.jxrwId}" else "") +
      (if (form.adminclassId != 0) s" and bjxs.bjid = ${form.adminclassId}" else "") +
      (if (form.studentName != null) s" and xs.xm like '%${form.studentName}%'" else "") +
      //(if (form.collegeId != 0) s" and d.departmentid=${form.collegeId}" else "") +
      " group by xs.id, xs.xh, xs.xm, d.attendtype"
    val query = SqlBuilder.sql(sql).params(Map[String, Any]())
    find(query, form)
  }

  def findAdminclassItem(form: Form): Seq[Item] = {
    val sql = """
      select bj.id, d.attendtype, count(*) as num, bj.bjdm, bj.bjmc, ls.xm
      from dutyreport1 d
      join jcxx_bj_xs_t bjxs on bjxs.xsid = d.stuid
      join jcxx_bj_t bj on bj.id = bjxs.bjid
      left join jcxx_jzg_t ls on ls.id = bj.fdyid
      where d.attenddate between :start and :end""" +
      (if (form.counselorId != 0) s" and bj.fdyid = ${form.counselorId}" else "") +
      (if (form.collegeId != 0) s" and bj.bmid = ${form.collegeId}" else "") +
      (if (form.adminclassName != null) s" and bj.bjmc like '%${form.adminclassName}%'" else "") +
      (if (form.teacherName != null) s" and ls.xm like '%${form.teacherName}%'" else "") +
      " group by bj.id, bj.bjdm, bj.bjmc, ls.xm, d.attendtype"
    val query = SqlBuilder.sql(sql).params(Map[String, Any]())
    find(query, form)
  }

  def findAttendItem(form: Form): Seq[Item] = {
    val sql = s"""
      select d.attenddate, to_char(d.attendtime, 'HH24:mi'), js.jsmc, fs.cqfsmc
      from dutyreport1 d
      join bzhb_cqfs_t fs on fs.id = d.attendtype
      left join device_js skjs on skjs.devid = d.devid
      left join jcxx_js_t js on js.id = skjs.jsid 
      where d.attenddate between :start and :end
      and d.stuid = ${form.studentId }
      and d.jxrwid = ${form.jxrwId}
      order by d.attenddate"""
    val query = SqlBuilder.sql(sql).params(Map[String, Any]())
    query.param("start", form.startDate).param("end", form.endDate)
    val result = attendanceDao.search(query.build)
    result.asInstanceOf[Seq[Item]]
  }

  def find(sql: SqlBuilder, form: Form): Seq[Item] = {
    find(sql, form, null)
  }

  def find(sql: SqlBuilder, form: Form, p: (Seq[_], Item) => Unit): Seq[Item] = {
    sql.param("start", form.startDate).param("end", form.endDate)
    val result = attendanceDao.search(sql.build)
    val map = new HashMap[Any, Item]()
    for (o <- result) {
      val item = map.getOrElseUpdate(o(0), new Item(o(0)))
      item + (Integer.parseInt(o(1).toString()), Integer.parseInt(o(2).toString()))
      item.code = o(3).toString
      item.name = o(4).toString
      if (o.length >= 6) item.name2 = if(o(5) != null) o(5).toString else ""
      if (o.length >= 7) item.name3 = if(o(6) != null) o(6).toString else ""
      if (p != null) p(o, item)
    }
    val seq = map.values.toSeq
    if (form.sortBy != null) {
      seq.sortBy(if (form.sortBy == "signRate") _.signRate else _.absenceRate).reverse
    } else seq.sortBy(_.code)
  }
  
  def getTeacher(teacherId: Long): IdNameObject = {
    get(teacherId, s"select id, xm from jcxx_jzg_t where id = ${teacherId}")
  }

  def getCollege(collegeId: Long): IdNameObject = {
    get(collegeId, s"select id, bmmc from jcxx_bm_t where id = ${collegeId}")
  }
  def getLesson(jxrwId: Long): IdNameObject = {
    get(jxrwId, s"select kc.id, kc.kcmc from jxrw_t rw join jcxx_kc_t kc on kc.id = rw.kcid where rw.id = ${jxrwId}")
  }

  def getAdminclass(adminclassId: Long): IdNameObject = {
    get(adminclassId, s"select id, bjmc from jcxx_bj_t where id = ${adminclassId}")
  }

  def getStudent(studentId: Long): IdNameObject = {
    get(studentId, s"select id, xm from xsxx_t where id = ${studentId}")
  }

  def get(id: Long, sql: String) = {
    if (id == 0) null
    else {
      val query = SqlBuilder.sql(sql)
      val result = attendanceDao search query.build
      if (result.size > 0) new IdNameObject(result(0)(0), result(0)(1).toString) else null
    }
  }

  def getTeacherId(code: String): Long = {
    val sql = s"select id from jcxx_jzg_t where jszgh = ${code}"
    val result = attendanceDao.search(SqlBuilder.sql(sql).build)
    if(result.isEmpty) 0 
    else result(0).asInstanceOf[Number].longValue
  }
  
  def getStudentId(code: String): Long = {
    val sql = s"select id from xsxx_t where xh = ${code}"
    val result = attendanceDao.search(SqlBuilder.sql(sql).build)
    if(result.isEmpty) 0 
    else result(0).asInstanceOf[Number].longValue
  }
}