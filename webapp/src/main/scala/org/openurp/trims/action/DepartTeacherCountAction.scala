package org.openurp.trims.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.Teacher
import org.beangle.data.jpa.dao.SqlBuilder
import org.openurp.base.Department
import org.beangle.commons.lang.Strings
/**
 * 各部门教师人数分布
 * *
 */
class DepartTeacherCountAction extends RestfulAction[Teacher]{

  def list(): String = {
    val sql = """select t.department_id,count(*)
		from edu_base.teachers t
    	where t.teaching=true
		group by t.department_id
		order by count(*) desc"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao .search(query)
    val map = new collection.mutable.HashMap[String, String]
    entityDao.getAll(classOf[Department]).foreach( d => {
      map.put(d.id.toString(), if(Strings.isNotBlank(d.shortName)) d.shortName else d.name)
    })
    put("dempartmentMap", map)
    put("datas", datas)
    forward()
  }

}