package org.openurp.edu.trims.action

import org.beangle.data.dao.OqlBuilder
import org.openurp.base.model.Semester
import org.openurp.edu.lesson.model.CourseTake
import org.openurp.edu.lesson.code.model.CourseTakeType

class CourseTakeReStudyAction extends AbsEamsAction[CourseTake] {

  def index(): String = {
    forward()
  }

  def search(): String = {
    val query = OqlBuilder.from(classOf[CourseTake], "ct")
    query.select("ct.semester.code, count(*) as num")
    query.groupBy("ct.semester.code")
    query.orderBy("ct.semester.code")
    query.where("ct.takeType.id=:type", CourseTakeType.RESTUDY)
    putNamesAndValues(entityDao.search(query))
    forward()
  }

  def department(): String = {
    val code = get("semesterId").get
    val semester = entityDao.findBy(classOf[Semester], "code", Array(code))(0)
    val query = OqlBuilder.from(classOf[CourseTake], "ct")
    query.select("ct.std.department.id, count(*) as num")
    query.where("ct.semester.id=:sid", semester.id)
    query.groupBy("ct.std.department.id")
    query.orderBy("count(*) desc")
    query.where("ct.courseTakeType.id=:type", CourseTakeType.RESTUDY)
    val map = getDepartmentMap()
    putNamesAndValues(entityDao.search(query), data => map(data(0).toString))
    put("semester", semester)
    forward()
  }

}