package org.openurp.trims.action

import org.beangle.data.jpa.dao.SqlBuilder
import ch.qos.logback.classic.db.SQLBuilder
import org.beangle.data.jpa.dao.OqlBuilder
import org.openurp.edu.base.Teacher
import org.openurp.base.Department
import org.openurp.hr.base.code.ProfessionalTitle
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
		(select a.person_id,a.title_id,cast(avg(num) as int) num from 
		(select lt.person_id,post.title_id, sum(c.period) num
		from edu_teach.lessons l 
		join edu_teach.lessons_teachers lt on lt.lesson_id=l.id
		join base.semesters s on l.semester_id = s.id 
		join edu_teach.courses c on c.id = l.course_id
        join hr_base.staffs staff on staff.person_id=lt.person_id
        join hr_base.staff_post_infoes post on staff.post_head_id=post.id
		group by lt.person_id,post.title_id,s.id)a
		group by a.title_id,a.person_id)b
		group by b.title_id
		order by num desc"""
//     val sql = """select b.title_id,cast(avg(num) as int) num from
//        (select a.person_id,a.title_id,cast(avg(num) as int) num from 
//        (select lt.person_id,post.title_id, sum(c.period) num
//        from edu_teach.lessons l 
//        join edu_teach.lessons_teachers lt on lt.lesson_id=l.id
//        join base.semesters s on l.semester_id = s.id 
//        join edu_teach.courses c on c.id = l.course_id
//        join edu_base.teachers t on t.id =  lt.person_id
//        join hr_base.staffs staff on staff.person_id=t.person_id
//        join hr_base.staff_post_infoes post on staff.id=post.staff_id
//        where post.begin_on <= s.end_on and (post.end_on is null or post.end_on >=s.end_on)
//        group by lt.person_id,post.title_id,s.id)a
//        group by a.title_id,a.person_id)b
//        group by b.title_id
//        order by num desc"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    val map = new collection.mutable.HashMap[String, String]
    entityDao.getAll(classOf[ProfessionalTitle]).foreach(d => {
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
		(select a.department_id,a.person_id,cast(avg(num) as int) num from 
		(select post.department_id,lt.person_id, sum(c.period) num
		from edu_teach.lessons l 
		join edu_teach.lessons_teachers lt on lt.lesson_id=l.id
		join base.semesters s on l.semester_id = s.id 
		join edu_teach.courses c on c.id = l.course_id
		join hr_base.staffs staff on staff.person_id=lt.person_id
        join hr_base.staff_post_infoes post on staff.post_head_id=post.id
		join base.departments d on d.id = post.department_id
		where """ +
      (if (tid == 0) { "post.title_id is null" } else { "post.title_id=" + tid }) +
      """
		group by lt.person_id,post.department_id,s.id)a
		group by a.department_id,a.person_id)b
		group by b.department_id
		order by num desc"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    val map = getDepartmentMap
    val title = entityDao.get(classOf[ProfessionalTitle], new Integer(tid))
    put("title", title)
    putNamesAndValues(datas, data => map.get(data(0) + ""))
    forward()
  }

  def title(): String = {
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
		where """ +
      (if (tid == 0) "post.title_id is null" else "post.title_id=" + tid) +
    s""" and post.department_id=${did}
		group by lt.person_id,p.name,s.school_year,s.name)a
		group by a.p_name
		order by num desc"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    val department = entityDao.get(classOf[Department], new Integer(did))
    val title = entityDao.get(classOf[ProfessionalTitle], new Integer(tid))
    put("title", title)
    put("department", department)
    put("datas", datas)
    forward()
  }

}