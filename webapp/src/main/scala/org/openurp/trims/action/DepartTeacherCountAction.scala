package org.openurp.trims.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.Teacher
import org.beangle.data.jpa.dao.SqlBuilder
import org.openurp.base.Department
import org.beangle.commons.lang.Strings
import scala.collection.mutable.ListBuffer
import org.openurp.hr.base.code.TeacherType
/**
 * 各部门教师人数分布
 * *
 */
class DepartTeacherCountAction extends AbsEamsAction{

  def index(): String = {
    forward()
  }
  
  def list(): String = {
    val teaching = getBoolean("teaching")
    val sql = """select d.id, pi.teacher_type_id,count(*)
    from hr_base.staffs f
    join hr_base.staff_post_infoes pi on f.post_head_id = pi.id
    join base.departments d on pi.department_id=d.id
    where f.state_id = 1 """ +
    (if(teaching.isDefined)s" and d.teaching = '${teaching.get}'"else"")+
    """ and pi.teacher_type_id is not null
    group by d.id, pi.teacher_type_id
    order by count(*) desc"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao .search(query)
    val idsSet = new collection.mutable.HashSet[Integer]
    datas.foreach(d => {
      idsSet += d(0).asInstanceOf[Integer]
    })
    val ids = idsSet.toList
    val names = new ListBuffer[String]
    val values = new ListBuffer[Any]()
    val teacherTypes = entityDao.getAll(classOf[TeacherType])
    val ititles = new collection.mutable.HashSet[String]
    val departs = getDepartmentMap()
    ids.foreach(id =>{
      names += departs(id.toString())
    })
    teacherTypes.foreach(t => {
      val value = new ListBuffer[Any]()
      value += t.name
      ids.foreach(id => {
        val data = datas.find(o => id.equals(o(0)) && t.id.equals(o(1)))
        val num = (if (data.isDefined) { ititles.add(t.name); (data.get)(2) } else 0)
        value += num
      })
      values += value
    })
    
    put("departIds", ids)
    put("names", names)
    put("values", values)
    put("titles", ititles)
    forward()
  }
  
  def teachers() = {
    val teaching = getBoolean("teaching")
    val departId = getInt("departId").get
    val sql = s"""select p.code, p.name, tt.name typeName
    from hr_base.staffs f
    join base.people p on f.person_id = p.id
    join hr_base.staff_post_infoes pi on f.post_head_id = pi.id
    join hr_base.xb_teacher_types tt on pi.teacher_type_id = tt.id
    where f.state_id = 1 and pi.department_id = ${departId}""" +
    (if(teaching.isDefined)s" and d.teaching = '${teaching.get}'"else"")+
    """ and pi.teacher_type_id is not null 
    order by tt.id"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao .search(query)
    put("depart", entityDao.get(classOf[Department], new Integer(departId)))
    put("teachers", datas)
    forward()
  }

}