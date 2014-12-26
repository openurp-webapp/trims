package org.openurp.trims.action

import org.beangle.data.jpa.dao.SqlBuilder
import scala.collection.mutable.ListBuffer
import java.util.Calendar
import org.beangle.commons.lang.Strings

class DepartResearchAction extends AbsEamsAction {

  def index(): String = {
    val years = getYears()
    put("years", years)
    forward()
  }

  def research(): String = {
    val thesises = getThesis
    val literatures = getLiterature
    val departsIdSet = new collection.mutable.HashSet[Integer]
    addDepartId(departsIdSet, thesises)
    addDepartId(departsIdSet, literatures)
    val departsIds = departsIdSet.toList.sortBy(i => i)
    val names = new ListBuffer[String]
    val map = getDepartmentMap
    departsIds.foreach(id => names += map(id.toString()))
    val values = new ListBuffer[ListBuffer[Any]]
    if(departsIds.length > 0){
      values += getValues(departsIds, thesises)
      values += getValues(departsIds, literatures)
    }
    put("names", names)
    put("values", values)
    put("beginYear", get("beginYear"))
    put("endYear", get("endYear"))
    forward()
  }

  private def getValues(ids: List[Integer], datas: Seq[Any]): ListBuffer[Any] = {
    val list = new ListBuffer[Any]
    ids.foreach(id => {
      val data = datas.find(d => {
        val data = d.asInstanceOf[Array[Any]]
        data(0).equals(id)
      })
      if (data.isDefined) {
        list += data.get.asInstanceOf[Array[Any]](1)
      } else {
        list += 0
      }
    })
    list
  }

  private def addDepartId(ids: collection.mutable.HashSet[Integer], datas: Seq[Any]) {
    datas.foreach(data => {
      ids += data.asInstanceOf[Array[Any]](0).asInstanceOf[Integer]
    })
  }

  /**论文*/
  private def getThesis(): Seq[Any] = {
    val beginYear = get("beginYear")
    val endYear = get("endYear")
    val sql = """select t.department_id, count(*)
        from sin_harvest.thesis_harvests t
        join sin_harvest.published_situations p on p.id = t.published_situation_id
        where 1=1 """ +
        (if (beginYear.isDefined && Strings.isNotBlank(beginYear.get)) " and to_char(p.published_date,'YYYY') >= '" + beginYear.get + "'" else "") +
        (if (endYear.isDefined && Strings.isNotBlank(endYear.get)) " and to_char(p.published_date,'YYYY') <= '" + endYear.get + "'" else "") +
        """ group by t.department_id"""
    val query = SqlBuilder.sql(sql)
    entityDao.search(query)
  }

  /**专著*/
  private def getLiterature(): Seq[Any] = {
    val beginYear = get("beginYear")
    val endYear = get("endYear")
    val sql = """select department_id,count(*)
        from sin_harvest.literatures
        where 1=1 """ +
        (if (beginYear.isDefined && Strings.isNotBlank(beginYear.get)) " and to_char(publish_date,'YYYY') >= '" + beginYear.get + "'" else "") +
        (if (endYear.isDefined && Strings.isNotBlank(endYear.get)) " and to_char(publish_date,'YYYY') <= '" + endYear.get + "'" else "") +
        """ group by department_id"""
    val query = SqlBuilder.sql(sql)
    entityDao.search(query)
  }

  def top10(): String = {
    val thesises = getThesisTop10
    val literatures = getLiteratureTop10
    put("thesises", thesises)
    put("literatures", literatures)
    put("beginYear", get("beginYear"))
    put("endYear", get("endYear"))
    forward()
  }

  private def getThesisTop10(): Seq[Any] = {
    val beginYear = get("beginYear")
    val endYear = get("endYear")
    val sql = """select pe.name,d.name dname,count(*) num
        from sin_harvest.thesis_harvests t
        join sin_harvest.researchers r on r.id = t.researcher_id
        join base.people pe on pe.id = r.person_id
        join base.departments d on d.id=t.department_id
        join sin_harvest.published_situations p on p.id = t.published_situation_id
        where 1=1 """ +
        (if (beginYear.isDefined && Strings.isNotBlank(beginYear.get)) " and to_char(p.published_date,'YYYY') >= '" + beginYear.get + "'" else "") +
        (if (endYear.isDefined && Strings.isNotBlank(endYear.get)) " and to_char(p.published_date,'YYYY') <= '" + endYear.get + "'" else "") +
        """ group by pe.name,d.name
        order by num desc limit 10"""
    val query = SqlBuilder.sql(sql)
    entityDao.search(query)
  }

  private def getLiteratureTop10(): Seq[Any] = {
    val beginYear = get("beginYear")
    val endYear = get("endYear")
    val sql = """select pe.name,d.name dname,count(*) num
        from sin_harvest.literatures l
        join sin_harvest.researchers r on r.id = l.researcher_id
        join base.people pe on pe.id = r.person_id
        join base.departments d on d.id=l.department_id
        where 1=1 """ +
        (if (beginYear.isDefined && Strings.isNotBlank(beginYear.get)) " and to_char(l.publish_date,'YYYY') >= '" + beginYear.get + "'" else "") +
        (if (endYear.isDefined && Strings.isNotBlank(endYear.get)) " and to_char(l.publish_date,'YYYY') <= '" + endYear.get + "'" else "") +
        """ group by pe.name,d.name
        order by num desc limit 10"""
    val query = SqlBuilder.sql(sql)
    entityDao.search(query)
  }

}