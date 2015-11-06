package org.openurp.edu.trims.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.beangle.data.dao.OqlBuilder
import org.beangle.commons.lang.Strings
import org.openurp.base.model.Department
import org.openurp.edu.lesson.model.Lesson

class LessonPeriodAction extends AbsEamsAction[Lesson] {

  def index(): String = {
    put("years", getLessonTerms())
    forward()
  }

  def search(): String = {
    val teaching = getBoolean("teaching")
    val query = OqlBuilder.from(classOf[Lesson], "l")
    query.select("l.teachDepart.id, sum(l.course.period) as num")
    getInt("beginYear").map(year => {
      if (year != 0) {
        put("beginYear", year)
        query.where("l.semester.id>=:year", year)
      }
    })
    getInt("endYear").map(year => {
      if (year != 0) {
        put("endYear", year)
        query.where("l.semester.id<=:year", year)
      }
    })
    if (teaching.isDefined) {
      query.where("l.teachDepart.teaching=:teaching", teaching.get)
    }
    query.groupBy("l.teachDepart.id")
    query.orderBy("sum(l.course.period) desc")
    val datas = entityDao.search(query)
    val map = getDepartmentMap
    putNamesAndValues(datas, d => map(d(0).toString))
    forward()
  }

  def department(): String = {
    val did = getInt("did").get
    val department = entityDao.get(classOf[Department], did)
    val query = OqlBuilder.from(classOf[Lesson], "l")
    query.select("l.semester.code, sum(l.course.period) as num")
    query.where("l.teachDepart.id=:did", did)
    query.groupBy("l.semester.code")
    query.orderBy("l.semester.code")
    put("datas", entityDao.search(query))
    put("department", department)
    forward()
  }
}