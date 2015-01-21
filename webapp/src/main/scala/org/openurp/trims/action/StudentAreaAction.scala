package org.openurp.trims.action

import org.beangle.data.jpa.dao.SqlBuilder
import java.text.SimpleDateFormat
import java.util.Date

class StudentAreaAction extends AbsEamsAction {

  def index = forward()

  def search = {
    val sfzx = getBoolean("sfzx")
    val sdf = new SimpleDateFormat("yyyy-MM-dd")
    val now = sdf.format(new Date())
    val sql = """
      select name, num from
      (select substring(d.code, 1, 2)||'0000' code, count(*) num
      from edu_std.examinees e
      join edu_base.students s on s.id = e.std_id
      join base.gb_divisions d on d.id = e.origin_division_id 
      where 1=1 """ +
      (if(sfzx.isDefined) s" and s.graduate_on >= '${now}'" else "") +
      """ group by substring(d.code, 1, 2)) t
      join base.gb_divisions d on d.code = t.code
      order by num desc"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    var max = 0
    datas.foreach(data => {
      val value = new Integer(data(1).toString)
      if(max < value) max = value
    })
    max = max / 100 * 100 + 100
    put("datas", datas)
    put("sfzx", sfzx)
    put("max", max)
    forward()
  }
  
  def depart = {
    val sfzx = getBoolean("sfzx")
    val sdf = new SimpleDateFormat("yyyy-MM-dd")
    val now = sdf.format(new Date())
    val area = get("area").get
    val sql = s"""
      select s.department_id, count(*) num
      from edu_std.examinees e
      join edu_base.students s on s.id = e.std_id
      join base.gb_divisions d0 on d0.id = e.origin_division_id 
      join base.gb_divisions d1 on d1.code = substring(d0.code, 1, 2)||'0000'
      where d1.name like '${area}%' """ +
      (if(sfzx.isDefined) s" and s.graduate_on >= '${now}'" else "") +
      """ group by s.department_id
      order by count(*) desc"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    val map = getDepartmentMap
    datas.foreach(data=> data(0) = map(data(0).toString))
    put("area", area)
    put("datas", datas)
    forward()
  }

}