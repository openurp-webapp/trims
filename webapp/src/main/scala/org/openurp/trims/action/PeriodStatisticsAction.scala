package org.openurp.trims.action

import org.beangle.webmvc.api.action.ActionSupport
import org.beangle.data.jpa.dao.SqlBuilder
import org.beangle.data.model.dao.EntityDao
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.teach.lesson.Lesson
import org.beangle.data.jpa.dao.OqlBuilder
import org.beangle.commons.lang.Strings
import org.openurp.base.Department
/**
 * 平均课时人数统计
 * */
class PeriodStatisticsAction extends  RestfulAction[Lesson]{
  
  override def index(): String = {
    val query = OqlBuilder.from(classOf[Lesson], "l")
    query.select("l.semester.id, l.semester.code")
    query.groupBy("l.semester.id, l.semester.code")
    query.orderBy("l.semester.id, l.semester.code")
    put("years", entityDao.search(query))
    val departs = entityDao .findBy(classOf[Department], "teaching", Array(true))
    put("departs", departs)
    forward()
  }
  
  def period():String={
    val beginYear = getInt("beginYear")
    val endYear = getInt("endYear")
    val teaching = getBoolean("teaching")
    val departmentId = getInt("departmentId")
    val sql = """select num * 10, count(*) from
		(select t.person_id, cast(avg(num) / 10 as int) num from 
		(select  lt.person_id,l.semester_id, sum(c.period) num
		from edu_teach.lessons l 
    join base.departments d on l.teach_depart_id = d.id
		join edu_teach.lessons_teachers lt on lt.lesson_id=l.id 
		join edu_teach.courses c on c.id = l.course_id where 1=1 """ + 
    (if(teaching.isDefined)s" and d.teaching = '${teaching.get}'"else"")+
		(if(beginYear.isDefined)s" and l.semester_id >= ${beginYear.get}"else"")+
		(if(endYear.isDefined)s" and l.semester_id <= '${endYear.get}'"else"")+
		(if(departmentId.isDefined)s" and l.teach_depart_id = '${departmentId.get}'"else"")+
		""" group by l.semester_id,lt.person_id
		order by lt.person_id) t
		group by person_id order by avg(num) desc) t
		group by num 
		order by num"""
		put("beginYear", beginYear)
    put("endYear", endYear)
    put("teaching", teaching)
  	if(departmentId.isDefined){
  	  val departId = departmentId.get
  	  val department = entityDao .get(classOf[Department], new Integer(departId))
  	  put("department",department)
  	}
    val query = SqlBuilder.sql(sql)
    val datas = entityDao .search(query)
    put("datas", datas)
    forward()
  }
  
  def top10():String={
    val beginYear = getInt("beginYear")
    val endYear = getInt("endYear")
    val departmentId = getInt("departmentId")
    val sql="""	select  p.name p_name,s.code, sum(c.period) num, d.name d_name
		from edu_teach.lessons l 
		join edu_teach.lessons_teachers lt on lt.lesson_id=l.id 
		join edu_base.teachers t on t.person_id = lt.person_id
		join base.people p on p.id=t.person_id
		join base.departments d on d.id=p.department_id
    join base.semesters s on l.semester_id = s.id
		join edu_teach.courses c on c.id = l.course_id where 1=1"""+  
    (if(beginYear.isDefined)s" and s.id >= '${beginYear.get}'"else"")+
    (if(endYear.isDefined)s" and s.id <= '${endYear.get}'"else"")+
		(if(departmentId.isDefined)s" and l.teach_depart_id = '${departmentId.get}'"else"")+
		""" group by s.code,p.name, d.name
		order by num desc limit 10"""
  	if(departmentId.isDefined){
  	  val departId = departmentId.get
  	  val department = entityDao .get(classOf[Department], new Integer(departId))
  	  put("department",department)
  	}
    val query = SqlBuilder.sql(sql)
    val datas = entityDao .search(query)
    put("datas", datas)
    forward()
  }

}