package org.openurp.trims.action

import org.beangle.webmvc.api.action.ActionSupport
import org.beangle.data.jpa.dao.SqlBuilder
import org.beangle.data.model.dao.EntityDao
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.teach.lesson.Lesson
import org.beangle.data.jpa.dao.OqlBuilder
import org.beangle.commons.lang.Strings
import org.openurp.base.Department
/**
 * 平均课时人数统计
 * */
class PeriodStatisticsAction extends  RestfulAction[Lesson]{
  
  override def index(): String = {
    val query = OqlBuilder.from(classOf[Lesson], "l")
    query.select("l.semester.schoolYear")
    query.groupBy("l.semester.schoolYear")
    query.orderBy("l.semester.schoolYear desc")
    put("datas", entityDao.search(query))
    val departs = entityDao .findBy(classOf[Department], "teaching", Array(true))
    put("departs", departs)
    forward()
  }
  
  def period():String={
    val year = get("year")
    val term = get("term")
    val departmentId = getInt("departmentId")
    val sql = """select num * 10, count(*) from
		(select t.teacher_id, cast(avg(num) / 10 as int) num from 
		(select  lt.teacher_id,s.school_year, s.name, sum(c.period) num
		from edu_teach.lessons l 
		join edu_teach.lessons_teachers lt on lt.lesson_id=l.id 
		join base.semesters s on l.semester_id = s.id 
		join edu_teach.courses c on c.id = l.course_id where 1=1 """ + 
		(if(year.isDefined && Strings.isNotBlank(year.get))s" and s.school_year = '${year.get}'"else"")+
		(if(term.isDefined && Strings.isNotBlank(term.get))s" and s.name = '${term.get}'"else"")+
		(if(departmentId.isDefined)s" and l.teach_depart_id = '${departmentId.get}'"else"")+
		""" group by s.school_year,s.name,lt.teacher_id
		order by lt.teacher_id) t
		group by teacher_id order by avg(num) desc) t
		group by num 
		order by num"""
	if(year.isDefined && Strings.isNotBlank(year.get)) put("year", year)
	if(term.isDefined && Strings.isNotBlank(term.get)) put("term", term)
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
    val year = get("year")
    val term = get("term")
    val departmentId = getInt("departmentId")
    val sql="""	select  p.name p_name,s.school_year, s.name s_name, sum(c.period) num, d.name d_name
		from edu_teach.lessons l 
		join edu_teach.lessons_teachers lt on lt.lesson_id=l.id 
		join base.semesters s on l.semester_id = s.id 
		join edu_base.teachers t on t.id = lt.teacher_id
		join base.people p on p.id=t.person_id
		join base.departments d on d.id=p.department_id
		join edu_teach.courses c on c.id = l.course_id where 1=1"""+  
		(if(year.isDefined && Strings.isNotBlank(year.get))s" and s.school_year = '${year.get}'"else"")+
		(if(term.isDefined && Strings.isNotBlank(term.get))s" and s.name = '${term.get}'"else"")+
		(if(departmentId.isDefined)s" and l.teach_depart_id = '${departmentId.get}'"else"")+
		"""group by s.school_year,s.name,p.name, d.name
		order by num desc limit 10"""
	if(year.isDefined && Strings.isNotBlank(year.get)) put("year", year)
	if(term.isDefined && Strings.isNotBlank(term.get)) put("term", term)
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