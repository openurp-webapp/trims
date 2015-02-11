package org.openurp.trims.action

import org.beangle.data.jpa.dao.SqlBuilder
import scala.collection.mutable.ListBuffer
import java.util.Calendar
import org.beangle.commons.lang.Strings
import org.openurp.code.edu.DegreeLevel

class DegreeResearchAvgCountAction extends AbsEamsAction {

  def index(): String = {
    val years = getYears()
    put("years", years)
    forward()
  }

  def thesis(): String = {
    val beginYear = get("beginYear")
    val endYear = get("endYear")
    val sql = """select degree.level_id,sum(num)/count(*) num from
      (select degree.level_id,r.person_id,count(*) num
      from sin_harvest.thesis_harvests t
      join sin_harvest.published_situations p on p.id = t.published_situation_id
      join sin_harvest.researchers r on r.id = t.researcher_id
      join hr_base.staffs staff on staff.person_id=r.person_id
      join base.gb_degrees degree on degree.id = staff.degree_id
      where staff.state_id=1 """ +
      (if (beginYear.isDefined && Strings.isNotBlank(beginYear.get)) " and to_char(staff.employ_on,'YYYY') >= '" + beginYear.get + "'" else "") +
      (if (endYear.isDefined && Strings.isNotBlank(endYear.get)) " and to_char(staff.employ_on,'YYYY') <= '" + endYear.get + "'" else "") +
      """ group by degree.level_id,r.person_id) t
      right join hr_base.staffs staff on staff.person_id=t.person_id
      join base.gb_degrees degree on degree.id = staff.degree_id
      where staff.state_id=1 """ +
      (if (beginYear.isDefined && Strings.isNotBlank(beginYear.get)) " and to_char(staff.employ_on,'YYYY') >= '" + beginYear.get + "'" else "") +
      (if (endYear.isDefined && Strings.isNotBlank(endYear.get)) " and to_char(staff.employ_on,'YYYY') <= '" + endYear.get + "'" else "") +
      """ group by degree.level_id
      having sum(t.num) > 0
      order by num desc"""
    val query = SqlBuilder.sql(sql)
    val thesises = entityDao.search(query)
//    thesises.foreach(d=>{
//      d(1)=String.format("%.4f", new java.lang.Double(d(1).toString))
//    })
    val map = new collection.mutable.HashMap[String, String]
    entityDao.getAll(classOf[DegreeLevel]).foreach(d => {
      map.put(d.id.toString(), d.name)
    })
    putNamesAndValues(thesises, data => map.get(data(0) + ""))
    put("beginYear", beginYear)
    put("endYear", endYear)
    forward()
  }

  def literature(): String = {
    val beginYear = get("beginYear")
    val endYear = get("endYear")
    val sql = """select degree.level_id,sum(num)/count(*) num from
        (select degree.level_id,r.person_id,count(*) num
        from sin_harvest.literatures l
        join sin_harvest.researchers r on r.id = l.researcher_id
        join hr_base.staffs staff on staff.person_id=r.person_id
        join base.gb_degrees degree on degree.id = staff.degree_id
        where staff.state_id=1 """ +
        (if (beginYear.isDefined && Strings.isNotBlank(beginYear.get)) " and to_char(staff.employ_on,'YYYY') >= '" + beginYear.get + "'" else "") +
        (if (endYear.isDefined && Strings.isNotBlank(endYear.get)) " and to_char(staff.employ_on,'YYYY') <= '" + endYear.get + "'" else "") +
        """ group by degree.level_id,r.person_id)t
        right join hr_base.staffs staff on staff.person_id=t.person_id
        join base.gb_degrees degree on degree.id = staff.degree_id
        where staff.state_id=1 """ +
        (if (beginYear.isDefined && Strings.isNotBlank(beginYear.get)) " and to_char(staff.employ_on,'YYYY') >= '" + beginYear.get + "'" else "") +
        (if (endYear.isDefined && Strings.isNotBlank(endYear.get)) " and to_char(staff.employ_on,'YYYY') <= '" + endYear.get + "'" else "") +
        """ group by degree.level_id
        having sum(t.num) > 0
        order by num desc"""
    val query = SqlBuilder.sql(sql)
    val literatures = entityDao.search(query)
//    literatures.foreach(d=>{
//      d(1)=String.format("%.4f", new java.lang.Double(d(1).toString))
//    })
    val map = new collection.mutable.HashMap[String, String]
    entityDao.getAll(classOf[DegreeLevel]).foreach(d => {
      map.put(d.id.toString(), d.name)
    })
    putNamesAndValues(literatures, data => map.get(data(0) + ""))
    put("beginYear", beginYear)
    put("endYear", endYear)
    forward()
  }
}