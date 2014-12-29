package org.openurp.trims.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.teach.lesson.Lesson
import org.beangle.data.jpa.dao.OqlBuilder
import org.beangle.commons.lang.Strings
import org.openurp.base.Department

class LessonTeachClassStdCountAction extends AbsEamsAction[Lesson] {

  def index(): String = {
    put("years", getLessonYears())
    put("departments", getDepartments())
    forward()
  }

  def search(): String = {
    val query = OqlBuilder.from(classOf[Lesson], "l")
    query.select("l.teachClass.stdCount, count(*)")
    query.where("l.teachClass.stdCount > 0")
    get("year").map(year => {
      if (Strings.isNotBlank(year)) {
        put("year", year)
        query.where("l.semester.schoolYear=:year", year)
      }
    })
    get("term").map(term => {
      if (Strings.isNotBlank(term)) {
        put("term", term)
        query.where("l.semester.name=:term", term)
      }
    })
    getInt("departmentId").map(departmentId => {
      put("department", entityDao.get(classOf[Department], new Integer(departmentId)))
      query.where("l.teachDepart.id=:did", departmentId)
    })
    query.groupBy("l.teachClass.stdCount")
    query.orderBy("l.teachClass.stdCount")
    val datas = entityDao.search(query)
    val avg = getAvg(datas)(d => {
      d.asInstanceOf[Array[Any]](1).toString.toDouble
    })
    val standardDeviation = getStandardDeviation(datas, avg)(d => {
      d.asInstanceOf[Array[Any]](1).toString.toDouble
    })
    putNamesAndValues(datas)
    put("avg", avg)
    put("standardDeviation", standardDeviation)
    forward()
  }

}