package org.openurp.trims.action

import org.beangle.data.jpa.dao.OqlBuilder
import org.openurp.base.Department
import org.beangle.commons.lang.Strings
import org.openurp.teach.lesson.Lesson
import org.openurp.base.Teacher
import org.openurp.base.code.TeacherTitle

class TeacherTitleAction extends AbsEamsAction{

  def index(): String = {
    forward()
  }

  def search(): String = {
    val query = OqlBuilder.from(classOf[Teacher], "l")
    query.select("l.title.id, count(*) as num")
    query.groupBy("l.title.id")
    query.orderBy("count(*) desc")
    val datas = entityDao.search(query)
    val map = new collection.mutable.HashMap[String, String]
    entityDao.getAll(classOf[TeacherTitle]).foreach( d => {
      map.put(d.id.toString(), d.name)
    })
    val o = null
    println(o.toString())
    putNamesAndValues(datas, data => map.get(data(0).toString).getOrElse(data(0)))
    forward()
  }
  
  def department():String = {
    val tid = getInt("tid").get
    val title = entityDao.get(classOf[TeacherTitle], new Integer(tid))
    val query = OqlBuilder.from(classOf[Teacher], "l")
    query.select("l.department.id, count(*) as num")
    query.where("l.title.id=:tid", tid)
    query.groupBy("l.department.id")
    query.orderBy("l.department.id")
    val datas = entityDao.search(query)
    val map = getDepartmentMap()
    putNamesAndValues(datas, data => map(data(0).toString))
    forward()
  }

}