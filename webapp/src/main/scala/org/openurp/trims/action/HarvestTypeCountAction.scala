package org.openurp.trims.action

import org.beangle.data.jpa.dao.SqlBuilder
import scala.collection.mutable.ListBuffer
import java.util.Calendar
import org.beangle.commons.lang.Strings

class HarvestTypeCountAction extends AbsEamsAction {

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
    val sql = """select h.name hname,count(*) num
        from research.thesis_harvests t
        join research.published_situations p on p.id = t.published_situation_id
        join research.harvest_types h on h.id = p.harvest_type_id
        where 1=1 """ +
        (if (beginYear.isDefined && Strings.isNotBlank(beginYear.get)) " and to_char(p.published_date,'YYYY') >= '" + beginYear.get + "'" else "") +
        (if (endYear.isDefined && Strings.isNotBlank(endYear.get)) " and to_char(p.published_date,'YYYY') <= '" + endYear.get + "'" else "") +
        """ group by hname
        order by num desc"""
    val query = SqlBuilder.sql(sql)
    val thesises = entityDao.search(query)
    putNamesAndValues(thesises)
    put("beginYear",beginYear.orNull)
    put("endYear",endYear.orNull)
    forward()
  }

  def literature(): String = {
    val beginYear = get("beginYear")
    val endYear = get("endYear")
    val sql = """select h.name hname,count(*) num
        from research.literatures l
        join research.harvest_types h on h.id = l.harvest_type_id
        where 1=1 """ +
        (if (beginYear.isDefined && Strings.isNotBlank(beginYear.get)) " and to_char(publish_date,'YYYY') >= '" + beginYear.get + "'" else "") +
        (if (endYear.isDefined && Strings.isNotBlank(endYear.get)) " and to_char(publish_date,'YYYY') <= '" + endYear.get + "'" else "") +
        """ group by hname
        order by num desc"""
    val query = SqlBuilder.sql(sql)
    val literatures = entityDao.search(query)
    putNamesAndValues(literatures)
    put("beginYear",beginYear.orNull)
    put("endYear",endYear.orNull)
    forward()
  }
}