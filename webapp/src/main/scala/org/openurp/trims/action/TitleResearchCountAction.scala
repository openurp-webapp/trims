package org.openurp.trims.action

import org.beangle.data.jpa.dao.SqlBuilder
import scala.collection.mutable.ListBuffer
import java.util.Calendar
import org.beangle.commons.lang.Strings
import org.openurp.base.code.TeacherTitle

class TitleResearchCountAction extends AbsEamsAction {

  def index(): String = {
    val sql = """select min(_year)
        from(
        (select to_char(p.published_date,'YYYY') as _year
        from research.thesis_harvests t
        join research.published_situations p on p.id = t.published_situation_id)
        union
        (select to_char(publish_date,'YYYY') as _year
        from research.literatures )) t"""
    val query = SqlBuilder.sql(sql)
    val startYear = new Integer(entityDao.search(query)(0).toString).toInt
    val years = new ListBuffer[Int]
    val curYear = Calendar.getInstance().get(Calendar.YEAR)
    for (year <- startYear to curYear) {
      years += year
    }
    put("years", years)
    forward()
  }

  def thesis(): String = {
    val beginYear = get("beginYear")
    val endYear = get("endYear")
    val sql = """select te.title_id,count(*) num
        from research.thesis_harvests t
        join research.published_situations p on p.id = t.published_situation_id
        join research.researchers r on r.id = t.researcher_id
        join base.people pe on pe.id = r.person_id
        join base.teachers te on te.person_id = pe.id
        where 1=1 """ +
      (if (beginYear.isDefined && Strings.isNotBlank(beginYear.get)) " and to_char(p.published_date,'YYYY') >= '" + beginYear.get + "'" else "") +
      (if (endYear.isDefined && Strings.isNotBlank(endYear.get)) " and to_char(p.published_date,'YYYY') <= '" + endYear.get + "'" else "") +
      """ group by te.title_id
        order by num desc"""
    val query = SqlBuilder.sql(sql)
    val thesises = entityDao.search(query)
    val map = new collection.mutable.HashMap[String, String]
    entityDao.getAll(classOf[TeacherTitle]).foreach( d => {
      map.put(d.id.toString(), d.name)
    })
    putNamesAndValues(thesises, data => map.get(data(0)+"").getOrElse("无职称"))
    put("beginYear", beginYear)
    put("endYear", endYear)
    forward()
  }

  def literature(): String = {
    val beginYear = get("beginYear")
    val endYear = get("endYear")
    val sql = """select te.title_id,count(*) num
        from research.literatures l
        join research.researchers r on r.id = l.researcher_id
        join base.people pe on pe.id = r.person_id
        join base.teachers te on te.person_id = pe.id
        where 1=1 """ +
      (if (beginYear.isDefined && Strings.isNotBlank(beginYear.get)) " and to_char(publish_date,'YYYY') >= '" + beginYear.get + "'" else "") +
      (if (endYear.isDefined && Strings.isNotBlank(endYear.get)) " and to_char(publish_date,'YYYY') <= '" + endYear.get + "'" else "") +
      """ group by te.title_id
        order by num desc"""
    val query = SqlBuilder.sql(sql)
    val literatures = entityDao.search(query)
    val map = new collection.mutable.HashMap[String, String]
    entityDao.getAll(classOf[TeacherTitle]).foreach( d => {
      map.put(d.id.toString(), d.name)
    })
    putNamesAndValues(literatures, data => map.get(data(0)+"").getOrElse("无职称"))
    put("beginYear", beginYear)
    put("endYear", endYear)
    forward()
  }
}