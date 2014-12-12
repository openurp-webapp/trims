package org.openurp.trims.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.teach.lesson.Lesson
import org.beangle.data.jpa.dao.SqlBuilder
import org.beangle.data.jpa.dao.OqlBuilder
import org.beangle.commons.lang.Strings
import org.openurp.base.Department
/**
 * 各部门学期平均课时统计
 * */
class DepartPeriodCountAction extends RestfulAction[Lesson] {
  
  override def index(): String = {
    val query = OqlBuilder.from(classOf[Lesson], "l")
    query.select("l.semester.schoolYear")
    query.groupBy("l.semester.schoolYear")
    query.orderBy("l.semester.schoolYear desc")
    put("datas", entityDao.search(query))
    forward()
  }

  def period(): String = {
    val year = get("year")
    val term = get("term")
    val sql = """select teach_depart_id, cast(avg(num) as int) num from 
		(select  l.teach_depart_id,lt.teacher_id,s.school_year, s.name, sum(c.period) num
		from teach.lessons l 
		join teach.lessons_teachers lt on lt.lesson_id=l.id 
		join base.semesters s on l.semester_id = s.id 
		join teach.courses c on c.id = l.course_id
		where 1=1 """ + 
		(if(year.isDefined && Strings.isNotBlank(year.get))s" and s.school_year = '${year.get}'"else"")+
		(if(term.isDefined && Strings.isNotBlank(term.get))s" and s.name = '${term.get}'"else"")+
		"""group by l.teach_depart_id,s.school_year,s.name,lt.teacher_id
		order by lt.teacher_id) t
		group by teach_depart_id order by avg(num) desc"""
	if(year.isDefined && Strings.isNotBlank(year.get)) put("year", year.orNull)
	if(term.isDefined && Strings.isNotBlank(term.get)) put("term", term)
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    val map = new collection.mutable.HashMap[String, String]
    entityDao.getAll(classOf[Department]).foreach( d => {
      map.put(d.id.toString(), if(Strings.isNotBlank(d.shortName)) d.shortName else d.name)
    })
    put("dempartmentMap", map)
    put("datas", datas)
    forward()
  }

}