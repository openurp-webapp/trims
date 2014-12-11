package org.openurp.trims.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.teach.lesson.Lesson
import org.beangle.data.jpa.dao.OqlBuilder
import org.beangle.commons.lang.Strings
import org.openurp.base.Department

class LessonTrimsAction extends RestfulAction[Lesson] {

  override def index(): String = {
    val query = OqlBuilder.from(classOf[Lesson], "l")
    query.select("l.semester.schoolYear")
    query.groupBy("l.semester.schoolYear")
    query.orderBy("l.semester.schoolYear desc")
    put("datas", entityDao.search(query))
    forward()
  }

  override def search(): String = {
    val query = OqlBuilder.from(classOf[Lesson], "l")
    query.select("l.teachDepart.id, count(*) as num")
    get("year").map(year=>{
      if(Strings.isNotBlank(year)){
        put("year", year)
        query.where("l.semester.schoolYear=:year", year)
      }
    })
    get("term").map(term=>{
      if(Strings.isNotBlank(term)){
        put("term", term)
        query.where("l.semester.name=:term", term)
      }
    })
    query.groupBy("l.teachDepart.id")
    query.orderBy("count(*) desc")
    put("datas", entityDao.search(query))
    val map = new collection.mutable.HashMap[String, String]
    entityDao.getAll(classOf[Department]).foreach( d => {
      map.put(d.id.toString(), if(Strings.isNotBlank(d.shortName)) d.shortName else d.name)
    })
    put("dempartmentMap", map)
    forward()
  }
  
  def department():String = {
    val did = getInt("did").get
    val department = entityDao.get(classOf[Department], new Integer(did))
    val query = OqlBuilder.from(classOf[Lesson], "l")
    query.select("l.semester.code, count(*) as num")
    query.where("l.teachDepart.id=:did", did)
    query.groupBy("l.semester.code")
    query.orderBy("l.semester.code  desc")
    put("datas", entityDao.search(query))
    put("department", department)
    forward()
  }
}