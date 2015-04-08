package org.openurp.trims.action

import scala.collection.mutable.ListBuffer
import org.beangle.data.jpa.dao.SqlBuilder
import org.openurp.base.Department

class TeachingQualityAction extends AbsEamsAction {

  def index() = {
//    put("years", getLessonYears)
    forward()
  }
  def search() = {
    val years = getLessonYears
    val beginYear = years(years.size -4)(0)
    val endYear = years(years.size - 2)(0)
    val sql = s"""select s.school_year, d.id, avg(q.score)
    from edu_quality.lesson_questionnaire_stats q
    join edu_teach.lessons l on l.id = q.lesson_id
    join base.semesters s on s.id = l.semester_id 
    join edu_teach.lessons_teachers lt on l.id = lt.lesson_id
    join edu_base.teachers te on te.id = lt.teacher_id
    join hr_base.staffs f on f.id = te.staff_id
    join hr_base.staff_post_infoes pi on pi.id = f.post_head_id
    join base.departments d on d.id = pi.department_id
    where d.teaching = true and f.state_id = 1
    and s.school_year >= '${beginYear}'
    and s.school_year <= '${endYear}'
    group by s.school_year, d.id
    """
    println(sql)
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    val yearSet = new collection.mutable.HashSet[String]
    val departIdSet = new collection.mutable.HashSet[Integer]
    datas.foreach(d => {
      val data = d.asInstanceOf[Array[Any]]
      yearSet += data(0).toString
      departIdSet += data(1).asInstanceOf[Integer]
    })
    val departIds = departIdSet.toList
    val titles = yearSet.toList
    val names = new ListBuffer[String]
    val departMap = getDepartmentMap
    departIds.foreach(id=>{
      names += departMap(id.toString())
    })
    val values = new ListBuffer[ListBuffer[Any]]
    titles.foreach(year =>{
      val value = new ListBuffer[Any]
      departIds.foreach( did =>{
        val o = datas.find(data => year.equals(data(0)) && did.equals(data(1)))
        if(o.isDefined){
          value += (o.get)(2)
        }else{
          value += 0
        }
      })
      values += value
    })
    put("departIds", departIds)
    put("names", names)
    put("values", values)
    put("titles", titles)
    put("beginYear", beginYear)
    put("endYear", endYear)
    forward()
  }

  def top10() = {
    val departId = getInt("departId").get
//    val beginYear = get("beginYear").get
//    val endYear = get("endYear").get
    val schoolYear = get("schoolYear").get
    val sql = s"""select s.school_year, p.code, p.name, avg(q.score)
    from edu_quality.lesson_questionnaire_stats q
    join edu_teach.lessons l on l.id = q.lesson_id
    join base.semesters s on s.id = l.semester_id 
    join edu_teach.lessons_teachers lt on l.id = lt.lesson_id 
    join edu_base.teachers te on te.id = lt.teacher_id
    join hr_base.staffs f on f.id = te.staff_id
    join ppl_base.people p on p.id = f.person_id
    join hr_base.staff_post_infoes pi on pi.id = f.post_head_id
    where f.state_id = 1 and pi.department_id = ${departId}
    and s.school_year = '${schoolYear}'
    group by s.school_year, p.code, p.name
    order by avg(q.score) desc
    limit 10"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    val depart = entityDao.get(classOf[Department], new Integer(departId)) 
    put("datas", datas)
    put("depart", depart)
    forward()
  }

}