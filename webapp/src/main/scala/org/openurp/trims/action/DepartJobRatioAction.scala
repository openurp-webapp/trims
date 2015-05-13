package org.openurp.trims.action

import annotation.migration
import collection.mutable.ListBuffer
import org.beangle.commons.lang.Strings
import org.beangle.data.jpa.dao.SqlBuilder
import org.openurp.base.model.Department

class DepartJobRatioAction extends AbsEamsAction {

  def index(): String = {
    val query = "select id,name from std_job.graduate_batches where enabled=true order by name desc"
    put("seasons", entityDao.search(SqlBuilder.sql(query)))
    forward()
  }

  def depart(): String = {
    val seasonId = get("seasonId").get
    val query = """
    select std.department_id,sum(case when status.has_job=true  then 1 else 0 end)*100/(count(*)*1.0) ratio
    from std_job.std_employments em 
    join edu_base.students std on em.std_id = std.id
    join std_job.employment_statuses status on status.id=em.employment_status_id
    where em.graduate_batch_id = """ + seasonId +
      " group by std.department_id order by sum(case when status.has_job=true  then 1 else 0 end)*100/(count(*)*1.0) desc"

    val datas = entityDao.search(SqlBuilder.sql(query)).asInstanceOf[Seq[Array[Any]]]
    val departs = getDepartmentMap()
    val names = for (data <- datas) yield departs.get(data.asInstanceOf[Array[Any]](0).toString).getOrElse("")
    val values = for (data <- datas) yield data.asInstanceOf[Array[Any]](1)

    val query2 = "select  name from std_job.graduate_batches where id= " + seasonId
    put("season", entityDao.search(SqlBuilder.sql(query2)).head)
    put("names", names)
    put("datas", datas)
    put("values", values)
    put("seasonId", seasonId)
    forward()
  }


  def status(): String = {
    val seasonId = get("seasonId").get
    val query = """
    select status.name,count(*) num
    from std_job.std_employments em 
    join edu_base.students std on em.std_id = std.id
    join std_job.employment_statuses status on status.id=em.employment_status_id
    where em.graduate_batch_id = """ + seasonId +
      " group by status.name"
    val datas = entityDao.search(SqlBuilder.sql(query)).asInstanceOf[Seq[Array[Any]]]
    put("datas", datas)
    forward()
  }
  
  def season(): String = {
    val departId = new Integer(getInt("departmentId").get)
    val query = """
     select season.name,sum(case when status.has_job=true  then 1 else 0 end)*100/(count(*)*1.0) ratio
     from std_job.std_employments em
     join edu_base.students std on em.std_id = std.id
     join std_job.employment_statuses status on status.id=em.employment_status_id
     join std_job.graduate_batches season on season.id=em.graduate_batch_id
     where std.department_id=:departId
     and season.enabled=true
     group by season.name order by season.name
    """
    val datas = entityDao.search(SqlBuilder.sql(query).param("departId", departId))
    put("department",entityDao.get(classOf[Department], departId))
    putNamesAndValues(datas)
    put("datas", datas)
    forward()
  }
}