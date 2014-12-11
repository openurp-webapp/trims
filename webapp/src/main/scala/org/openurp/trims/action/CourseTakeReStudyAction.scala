package org.openurp.trims.action

import org.openurp.teach.lesson.CourseTake
import org.beangle.data.jpa.dao.OqlBuilder
import org.openurp.teach.code.CourseTakeType

class CourseTakeReStudyAction extends AbsEamsAction[CourseTake] {

  def index(): String = {
    forward()
  }

  def search(): String = {
    val query = OqlBuilder.from(classOf[CourseTake], "ct")
    query.select("ct.semester.id, count(*) as num")
    query.groupBy("ct.semester.id")
    query.orderBy("ct.semester.id")
//    put("datas", entityDao.search(query))
    query.where("ct.courseTakeType.id=:type", CourseTakeType.RESTUDY)
    put("datas2", entityDao.search(query))
    forward()
  }

}