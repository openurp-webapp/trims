package org.openurp.edu.trims.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.beangle.data.dao.OqlBuilder
import org.beangle.commons.lang.Strings
import org.openurp.base.model.Department
import org.openurp.edu.lesson.model.Lesson

class LessonTeachClassStdCountAction extends AbsEamsAction[Lesson] {

  def index(): String = {
    put("years", getLessonTerms())
    put("departments", getDepartments())
    forward()
  }

  def search(): String = {
    val teaching = getBoolean("teaching")
    val query = OqlBuilder.from(classOf[Lesson], "l")
    query.select("l.teachclass.stdCount, count(*)")
    query.where("l.teachclass.stdCount > 0")
    getInt("beginYear").map(year => {
      if (year != 0) {
        put("beginYear", year)
        query.where("l.semester.id>=:beginYear", year)
      }
    })
    getInt("endYear").map(year => {
      if (year != 0) {
        put("endYear", year)
        query.where("l.semester.id<=:endYear", year)
      }
    })
    if (teaching.isDefined) {
      query.where("l.teachDepart.teaching=:teaching", teaching.get)
    }
    getInt("departmentId").map(departmentId => {
      put("department", entityDao.get(classOf[Department], departmentId))
      query.where("l.teachDepart.id=:did", departmentId)
    })
    query.groupBy("l.teachclass.stdCount")
    query.orderBy("l.teachclass.stdCount")
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