package org.openurp.edu.trims.action

import org.beangle.data.dao.SqlBuilder
import scala.collection.mutable.ListBuffer
import java.util.Calendar
import org.beangle.commons.lang.Strings

class HarvestTypeCountAction extends AbsEamsAction {

    def index(): String = {
    val years = getYears()
    put("years", years)
    forward()
  }

  def thesis(): String = {
    val beginYear = get("beginYear")
    val endYear = get("endYear")
    val teaching = getBoolean("teaching")
    val sql = """select h.name hname,count(*) num
        from sin_harvest.thesis_harvests t
        join sin_harvest.published_situations p on p.id = t.published_situation_id
        join sin_harvest.harvest_types h on h.id = p.harvest_type_id
      	join base.departments d on t.department_id = d.id
        where 1=1 """ +
        (if (teaching.isDefined) s" and d.teaching = ${teaching.get}" else "") +
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
    val teaching = getBoolean("teaching")
    val sql = """select h.name hname,count(*) num
        from sin_harvest.literatures l
        join sin_harvest.harvest_types h on h.id = l.harvest_type_id
      	join base.departments d on l.department_id = d.id
        where 1=1 """ +
        (if (teaching.isDefined) s" and d.teaching = ${teaching.get}" else "") +
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