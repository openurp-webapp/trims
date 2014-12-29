package org.openurp.trims.action

import org.openurp.edu.teach.lesson.CourseTake
import org.beangle.data.jpa.dao.OqlBuilder
import org.openurp.edu.teach.code.CourseTakeType
import org.openurp.base.Semester

class CourseTakeReStudyAction extends AbsEamsAction[CourseTake] {

  def index(): String = {
    forward()
  }

  def search(): String = {
    val query = OqlBuilder.from(classOf[CourseTake], "ct")
    query.select("ct.semester.code, count(*) as num")
    query.groupBy("ct.semester.code")
    query.orderBy("ct.semester.code")
    query.where("ct.courseTakeType.id=:type", CourseTakeType.RESTUDY)
    putNamesAndValues(entityDao.search(query))
    forward()
  }

  def department(): String = {
    val semester = entityDao.get(classOf[Semester], new Integer(getInt("semesterId").get))
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