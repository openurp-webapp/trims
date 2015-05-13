package org.openurp.trims.action

import org.beangle.data.jpa.dao.SqlBuilder
import scala.collection.mutable.ListBuffer
import java.util.Calendar
import org.beangle.commons.lang.Strings
import org.openurp.code.job.model.ProfessionalTitle

class TitleResearchCountAction extends AbsEamsAction {

  def index(): String = {
    val years = getYears()
    put("years", years)
    forward()
  }

  def thesis(): String = {
    val beginYear = get("beginYear")
    val endYear = get("endYear")
    val teaching = getBoolean("teaching")
    val sql = """select spi.title_id,count(*) num
        from sin_harvest.thesis_harvests t
        join sin_harvest.published_situations p on p.id = t.published_situation_id
        join sin_harvest.researchers r on r.id = t.researcher_id
      	join hr_base.staffs staff on staff.id = r.staff_id
        join hr_base.staff_post_infoes spi on spi.id = staff.post_head_id
      join base.departments d on t.department_id = d.id
        where staff.state_id=1  """ +
        (if (teaching.isDefined) s" and d.teaching = ${teaching.get}" else "") +
      (if (beginYear.isDefined && Strings.isNotBlank(beginYear.get)) " and to_char(p.published_date,'YYYY') >= '" + beginYear.get + "'" else "") +
      (if (endYear.isDefined && Strings.isNotBlank(endYear.get)) " and to_char(p.published_date,'YYYY') <= '" + endYear.get + "'" else "") +
      """ group by spi.title_id
        order by num desc"""
    val query = SqlBuilder.sql(sql)
    val thesises = entityDao.search(query)
    val map = new collection.mutable.HashMap[String, String]
    entityDao.getAll(classOf[ProfessionalTitle]).foreach( d => {
      map.put(d.id.toString(), d.name)
    })
    putNamesAndValues(thesises, data => map.get(data(0)+"").getOrElse("暂定系列"))
    put("beginYear", beginYear)
    put("endYear", endYear)
    forward()
  }

  def literature(): String = {
    val beginYear = get("beginYear")
    val endYear = get("endYear")
    val teaching = getBoolean("teaching")
    val sql = """select spi.title_id,count(*) num
        from sin_harvest.literatures l
        join sin_harvest.researchers r on r.id = l.researcher_id
      	join hr_base.staffs staff on staff.id = r.staff_id
        join hr_base.staff_post_infoes spi on spi.id = staff.post_head_id
      join base.departments d on l.department_id = d.id
        where staff.state_id=1  """ +
        (if (teaching.isDefined) s" and d.teaching = ${teaching.get}" else "") +
      (if (beginYear.isDefined && Strings.isNotBlank(beginYear.get)) " and to_char(publish_date,'YYYY') >= '" + beginYear.get + "'" else "") +
      (if (endYear.isDefined && Strings.isNotBlank(endYear.get)) " and to_char(publish_date,'YYYY') <= '" + endYear.get + "'" else "") +
      """ group by spi.title_id
        order by num desc"""
    val query = SqlBuilder.sql(sql)
    val literatures = entityDao.search(query)
    val map = new collection.mutable.HashMap[String, String]
    entityDao.getAll(classOf[ProfessionalTitle]).foreach( d => {
      map.put(d.id.toString(), d.name)
    })
    putNamesAndValues(literatures, data => map.get(data(0)+"").getOrElse("暂定系列"))
    put("beginYear", beginYear)
    put("endYear", endYear)
    forward()
  }
}