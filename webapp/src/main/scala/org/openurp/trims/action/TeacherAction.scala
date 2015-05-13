package org.openurp.trims.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.beangle.webmvc.api.view.View
import org.beangle.data.jpa.dao.OqlBuilder
import org.openurp.hr.base.code.model.TeacherType
import org.openurp.hr.base.model.Staff

class TeacherAction extends AbsEamsAction[Staff] {
  
  def index():String={
    put("departments", getAllDepartments())
    put("teacherTypes", entityDao.getAll(classOf[TeacherType]))
    forward()
  }
  
  def search(): String = {
    put(shortName + "s", entityDao.search(getQueryBuilder()))
    forward()
  }
  
  override def getQueryBuilder() = {
    val query = super.getQueryBuilder
//    query.where("state.id = 1")
    query
  }

}