package org.openurp.trims.action

import org.beangle.data.jpa.dao.OqlBuilder
import org.openurp.base.Department
import org.beangle.commons.lang.Strings
import org.openurp.edu.teach.lesson.Lesson
import org.openurp.edu.base.Teacher
import org.openurp.hr.base.code.ProfessionalTitle
import org.openurp.hr.base.Staff
//import org.openurp.edu.base.code.TeacherTitle

class TeacherTitleAction extends AbsEamsAction{

  def index(): String = {
    forward()
  }

  def search(): String = {
    val query = OqlBuilder.from(classOf[Staff], "staff").join("staff.post.head", "l")
    query.select("l.title.id, count(*) as num")
    query.where("staff.state.id = 1")
//    query.where("l.teaching = true")
//    query.where("l.title.id is not null")
    query.groupBy("l.title.id")
    query.orderBy("count(*) desc")
    val datas = entityDao.search(query)
    val map = new collection.mutable.HashMap[String, String]
    entityDao.getAll(classOf[ProfessionalTitle]).foreach( d => {
      map.put(d.id.toString(), d.name)
    })
    putNamesAndValues(datas, data => map.get(data(0)+"").getOrElse("无职称"))
    forward()
  }
  
  def department():String = {
    val tid = getInt("tid").get
    val title = entityDao.get(classOf[ProfessionalTitle], new Integer(tid))
    val query = OqlBuilder.from(classOf[Staff], "staff").join("staff.post.head", "l")
    query.where("staff.state.id = 1")
    query.select("l.department.id, count(*) as num")
//    query.where("l.teaching = true")
    if("undefined".equals(get("tid").get)){
       query.where("l.title.id is null")
    }else{
      query.where("l.title.id=:tid", tid)
    }
//    query.where("l.department.id is not null")
    query.groupBy("l.department.id")
    query.orderBy("count(*) desc")
    val datas = entityDao.search(query)
    val map = getDepartmentMap()
    putNamesAndValues(datas, data => map(data(0).toString))
    put("title", title)
    forward()
  }

}