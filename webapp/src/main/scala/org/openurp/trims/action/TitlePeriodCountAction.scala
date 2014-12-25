package org.openurp.trims.action

import org.beangle.data.jpa.dao.SqlBuilder
import org.openurp.base.code.TeacherTitle
import ch.qos.logback.classic.db.SQLBuilder
import org.beangle.data.jpa.dao.OqlBuilder
import org.openurp.base.Teacher
import org.openurp.base.Department
/**
 * 按职称对课时统计
 */
class TitlePeriodCountAction extends AbsEamsAction {

  def index(): String = {
    forward()
  }

  /**
   * 按职称对课时统计
   */

  def search(): String = {
    val sql = """select b.title_id,cast(avg(num) as int) num from
		(select a.teacher_id,a.title_id,cast(avg(num) as int) num from 
		(select lt.teacher_id,t.title_id, sum(c.period) num
		from teach.lessons l 
		join teach.lessons_teachers lt on lt.lesson_id=l.id
		join base.semesters s on l.semester_id = s.id 
		join teach.courses c on c.id = l.course_id
		join base.teachers t on t.id =  lt.teacher_id
		group by lt.teacher_id,t.title_id,s.id)a
		group by a.title_id,a.teacher_id)b
		group by b.title_id
		order by num desc"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    val map = new collection.mutable.HashMap[String, String]
    entityDao.getAll(classOf[TeacherTitle]).foreach(d => {
      map.put(d.id.toString(), d.name)
    })
    putNamesAndValues(datas, data => map.get(data(0) + "").getOrElse("无职称"))
    forward()
  }

  /**
   * 某职称按院系统计课时
   */
  def department(): String = {
    val tid = getInt("tid").get
    val sql = s"""select b.department_id,cast(avg(num) as int) num from 
		(select a.department_id,a.teacher_id,cast(avg(num) as int) num from 
		(select t.department_id,lt.teacher_id, sum(c.period) num
		from teach.lessons l 
		join teach.lessons_teachers lt on lt.lesson_id=l.id
		join base.semesters s on l.semester_id = s.id 
		join teach.courses c on c.id = l.course_id
		join base.teachers t on t.id =  lt.teacher_id
		join base.departments d on d.id = t.department_id
		where """ +
      (if (tid == 0) { "t.title_id is null" } else { "t.title_id=" + tid }) +
      """
		group by lt.teacher_id,t.department_id,s.id)a
		group by a.department_id,a.teacher_id)b
		group by b.department_id
		order by num desc"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    val map = getDepartmentMap
    val title = entityDao.get(classOf[TeacherTitle], new Integer(tid))
    put("title", title)
    putNamesAndValues(datas, data => map.get(data(0) + ""))
    forward()
  }

  def title(): String = {
    val tid = getInt("tid").get
    val did = getInt("did").get
    val sql = s"""select a.p_name,cast(avg(num) as int) num from 
		(select p.name p_name,lt.teacher_id,s.school_year,s.name, sum(c.period) num
		from teach.lessons l 
		join teach.lessons_teachers lt on lt.lesson_id=l.id
		join base.semesters s on l.semester_id = s.id 
		join teach.courses c on c.id = l.course_id
		join base.teachers t on t.id =  lt.teacher_id
		join base.people p on t.person_id = p.id
		where """ +
      (if (tid == 0) "t.title_id is null" else "t.title_id=" + tid) +
    s""" and t.department_id=${did}
		group by lt.teacher_id,p.name,s.school_year,s.name)a
		group by a.p_name
		order by num desc"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    val department = entityDao.get(classOf[Department], new Integer(did))
    val title = entityDao.get(classOf[TeacherTitle], new Integer(tid))
    put("title", title)
    put("department", department)
    put("datas", datas)
    forward()
  }

}