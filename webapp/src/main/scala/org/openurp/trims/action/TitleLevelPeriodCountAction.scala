package org.openurp.trims.action

import org.beangle.data.jpa.dao.SqlBuilder
import ch.qos.logback.classic.db.SQLBuilder
import org.beangle.data.jpa.dao.OqlBuilder
import org.openurp.edu.base.Teacher
import org.openurp.base.Department
import org.openurp.hr.base.code.ProfessionalTitle
import org.openurp.hr.base.code.ProfessionalTitleLevel
/**
 * 按职称对课时统计
 */
class TitleLevelPeriodCountAction extends AbsEamsAction {

  def index(): String = {
    put("years", getLessonYears())
    forward()
  }

  /**
   * 按职称对课时统计
   */

  def search(): String = {
    val beginYear = getInt("beginYear")
    val endYear = getInt("endYear")
    val teaching = getBoolean("teaching")
    val sql = """select b.level_id,cast(avg(num) as int) num from
		(select a.person_id,a.level_id,cast(avg(num) as int) num from 
		(select lt.person_id,pt.level_id, sum(c.period) num
		from edu_teach.lessons l 
		join edu_teach.lessons_teachers lt on lt.lesson_id=l.id
		join base.semesters s on l.semester_id = s.id 
		join edu_teach.courses c on c.id = l.course_id
    join hr_base.staffs staff on staff.person_id=lt.person_id
    join hr_base.staff_post_infoes post on staff.post_head_id=post.id
    join base.departments d on post.department_id = d.id
    join hr_base.gb_professional_titles pt on pt.id = post.title_id
    where staff.state_id = 1 """ +
    (if(teaching.isDefined)s" and d.teaching = ${teaching.get}"else"")+
    (if(beginYear.isDefined)s" and l.semester_id >= ${beginYear.get}"else"")+
    (if(endYear.isDefined)s" and l.semester_id <= '${endYear.get}'"else"")+
		""" group by lt.person_id,pt.level_id,s.id)a
		group by a.level_id,a.person_id)b
		group by b.level_id
		order by num desc"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    val map = new collection.mutable.HashMap[String, String]
    entityDao.getAll(classOf[ProfessionalTitleLevel]).foreach(d => {
      map.put(d.id.toString(), d.name)
    })
    putNamesAndValues(datas, data => map.get(data(0) + "").getOrElse("暂定系列"))
    put("teaching", teaching)
    put("beginYear", beginYear)
    put("endYear", endYear)
    forward()
  }

  /**
   * 某职称按院系统计课时
   */
  def department(): String = {
    val beginYear = getInt("beginYear")
    val endYear = getInt("endYear")
    val teaching = getBoolean("teaching")
    val tid = getInt("tid").get
    val sql = s"""select b.department_id,cast(avg(num) as int) num from 
		(select a.department_id,a.person_id,cast(avg(num) as int) num from 
		(select post.department_id,lt.person_id, sum(c.period) num
		from edu_teach.lessons l 
		join edu_teach.lessons_teachers lt on lt.lesson_id=l.id
		join base.semesters s on l.semester_id = s.id 
		join edu_teach.courses c on c.id = l.course_id
		join hr_base.staffs staff on staff.person_id=lt.person_id
    join hr_base.staff_post_infoes post on staff.post_head_id=post.id
    join base.departments d on post.department_id = d.id
    join hr_base.gb_professional_titles pt on pt.id = post.title_id
		where staff.state_id = 1""" +
    (if (tid == 0) { " and pt.level_id is null" } else { " and pt.level_id=" + tid }) +
    (if(teaching.isDefined)s" and d.teaching = ${teaching.get}"else"")+
    (if(beginYear.isDefined)s" and l.semester_id >= ${beginYear.get}"else"")+
    (if(endYear.isDefined)s" and l.semester_id <= '${endYear.get}'"else"")+
    """ group by lt.person_id,post.department_id,s.id)a
		group by a.department_id,a.person_id)b
		group by b.department_id
		order by num desc"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    val map = getDepartmentMap
    val title = entityDao.get(classOf[ProfessionalTitleLevel], new Integer(tid))
    put("title", title)
    putNamesAndValues(datas, data => map.get(data(0) + ""))
    put("teaching", teaching)
    put("tid", tid)
    put("beginYear", beginYear)
    put("endYear", endYear)
    forward()
  }

  def title(): String = {
    val beginYear = getInt("beginYear")
    val endYear = getInt("endYear")
    val teaching = getBoolean("teaching")
    val tid = getInt("tid").get
    val did = getInt("did").get
    val sql = s"""select a.p_name,cast(avg(num) as int) num from 
		(select p.name p_name,lt.person_id,s.school_year,s.name, sum(c.period) num
		from edu_teach.lessons l 
		join edu_teach.lessons_teachers lt on lt.lesson_id=l.id
		join base.semesters s on l.semester_id = s.id 
		join edu_teach.courses c on c.id = l.course_id
		join hr_base.staffs staff on staff.person_id=lt.person_id
    join hr_base.staff_post_infoes post on staff.post_head_id=post.id
		join base.people p on staff.person_id = p.id
    join hr_base.gb_professional_titles pt on pt.id = post.title_id
		where staff.state_id = 1""" +
    (if (tid == 0) { " and pt.level_id is null" } else { " and pt.level_id=" + tid }) +
    (if(teaching.isDefined)s" and d.teaching = ${teaching.get}"else"")+
    (if(beginYear.isDefined)s" and l.semester_id >= ${beginYear.get}"else"")+
    (if(endYear.isDefined)s" and l.semester_id <= '${endYear.get}'"else"")+
    s""" and post.department_id=${did}
		group by lt.person_id,p.name,s.school_year,s.name)a
		group by a.p_name
		order by num desc"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    val department = entityDao.get(classOf[Department], new Integer(did))
    val title = entityDao.get(classOf[ProfessionalTitleLevel], new Integer(tid))
    put("title", title)
    put("department", department)
    put("datas", datas)
    put("teaching", teaching)
    put("beginYear", beginYear)
    put("endYear", endYear)
    forward()
  }

}