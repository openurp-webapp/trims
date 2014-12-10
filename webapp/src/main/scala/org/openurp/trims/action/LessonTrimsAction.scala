package org.openurp.trims.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.teach.lesson.Lesson
import org.beangle.data.jpa.dao.OqlBuilder
import org.beangle.commons.lang.Strings

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
    query.select("l.teachDepart.name, count(*) as num")
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
    query.groupBy("l.teachDepart.name")
    query.orderBy("count(*) desc")
    put("datas", entityDao.search(query))
    forward()
  }
}