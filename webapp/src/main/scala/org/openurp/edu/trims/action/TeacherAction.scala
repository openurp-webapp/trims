package org.openurp.edu.trims.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.beangle.webmvc.api.view.View
import org.beangle.data.dao.OqlBuilder
import org.openurp.hr.base.model.Staff
import org.openurp.code.hr.model.StaffType

class TeacherAction extends AbsEamsAction[Staff] {
  
  def index():String={
    put("departments", getAllDepartments())
    put("teacherTypes", entityDao.getAll(classOf[StaffType]))
    forward()
  }
  
  def search(): String = {
    put(simpleEntityName + "s", entityDao.search(getQueryBuilder()))
    forward()
  }
  
  override def getQueryBuilder() = {
    val query = super.getQueryBuilder
//    query.where("state.id = 1")
    query
  }

}