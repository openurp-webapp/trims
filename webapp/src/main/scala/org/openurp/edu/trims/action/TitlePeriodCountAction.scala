package org.openurp.edu.trims.action

import org.beangle.data.dao.SqlBuilder
import org.openurp.base.model.Department
import org.openurp.code.job.model.ProfessionalTitle
/**
 * 按职称对课时统计
 */
class TitlePeriodCountAction  extends AbsEamsAction {

  def index(): String = {
    put("years", getLessonTerms())
    forward()
  }

  /**
   * 按职称对课时统计
   */

  def search(): String = {
    val beginYear = getInt("beginYear")
    val endYear = getInt("endYear")
    val teaching = getInt("teaching")
    val sql = """select b.title_id,cast(avg(num) as int) num from
    (select a.staff_id,a.title_id,cast(avg(num) as int) num from 
    (select lt.staff_id,post.title_id, sum(c.period) num
    from edu_teach.lessons l 
    join edu_teach.lessons_teachers lt on lt.lesson_id=l.id
    join base.semesters s on l.semester_id = s.id 
    join edu_base.courses c on c.id = l.course_id
    join hr_base.staffs staff on staff.id=lt.staff_id
    join hr_base.staff_post_infoes post on staff.post_head_id=post.id
    join base.departments d on post.department_id = d.id
    where staff.state_id = 1 """ +
    (if(teaching.isDefined)s" and d.teaching = ${teaching.get}"else"")+
    (if(beginYear.isDefined)s" and l.semester_id >= ${beginYear.get}"else"")+
    (if(endYear.isDefined)s" and l.semester_id <= '${endYear.get}'"else"")+
    """ group by lt.staff_id,post.title_id,s.id)a
    group by a.title_id,a.staff_id)b
    group by b.title_id
    order by num desc"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    val map = new collection.mutable.HashMap[String, String]
    entityDao.getAll(classOf[ProfessionalTitle]).foreach(d => {
      map.put(d.id.toString(), d.name)
    })
    putNamesAndValues(datas, data => map.get(data(0) + "").getOrElse("无职称"))
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
    (select a.department_id,a.staff_id,cast(avg(num) as int) num from 
    (select post.department_id,lt.staff_id, sum(c.period) num
    from edu_teach.lessons l 
    join edu_teach.lessons_teachers lt on lt.lesson_id=l.id
    join base.semesters s on l.semester_id = s.id 
    join edu_base.courses c on c.id = l.course_id
    join hr_base.staffs staff on staff.id=lt.staff_id
    join hr_base.staff_post_infoes post on staff.post_head_id=post.id
    join base.departments d on post.department_id = d.id
    where staff.state_id = 1""" +
    (if (tid == 0) { " and post.title_id is null" } else { " and post.title_id=" + tid }) +
    (if(teaching.isDefined)s" and d.teaching = ${teaching.get}"else"")+
    (if(beginYear.isDefined)s" and l.semester_id >= ${beginYear.get}"else"")+
    (if(endYear.isDefined)s" and l.semester_id <= '${endYear.get}'"else"")+
      """
    group by lt.staff_id,post.department_id,s.id)a
    group by a.department_id,a.staff_id)b
    group by b.department_id
    order by num desc"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    val map = getDepartmentMap
    var title = entityDao.get(classOf[ProfessionalTitle], tid)
    if (tid == 0) {
      title = new ProfessionalTitle
      title.id = 0
      title.name = "无职称"
    }
    put("title", title)
    put("teaching", teaching)
    put("beginYear", beginYear)
    put("endYear", endYear)
    putNamesAndValues(datas, data => map.get(data(0) + ""))
    forward()
  }

  def title(): String = {
    val beginYear = getInt("beginYear")
    val endYear = getInt("endYear")
    val teaching = getBoolean("teaching")
    val tid = getInt("tid").get
    val did = getInt("did").get
    val sql = s"""select a.p_name,cast(avg(num) as int) num from 
    (select p.name p_name,lt.staff_id,s.school_year,s.name, sum(c.period) num
    from edu_teach.lessons l 
    join edu_teach.lessons_teachers lt on lt.lesson_id=l.id
    join base.semesters s on l.semester_id = s.id 
    join edu_base.courses c on c.id = l.course_id
    join hr_base.staffs staff on staff.id=lt.staff_id
    join hr_base.staff_post_infoes post on staff.post_head_id=post.id
		join ppl_base.people p on staff.person_id = p.id
    where staff.state_id = 1""" +
    (if (tid == 0) "and post.title_id is null" else " and post.title_id=" + tid) +
    (if(beginYear.isDefined)s" and l.semester_id >= ${beginYear.get}"else"")+
    (if(endYear.isDefined)s" and l.semester_id <= '${endYear.get}'"else"")+
    s""" and post.department_id=${did}
    group by lt.staff_id,p.name,s.school_year,s.name)a
    group by a.p_name
    order by num desc"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    val department = entityDao.get(classOf[Department], did)
    var title = entityDao.get(classOf[ProfessionalTitle], tid)
    if (tid == 0) {
      title = new ProfessionalTitle
      title.name = "无职称"
    }
    put("title", title)
    put("teaching", teaching)
    put("beginYear", beginYear)
    put("endYear", endYear)
    put("department", department)
    put("datas", datas)
    forward()
  }
}