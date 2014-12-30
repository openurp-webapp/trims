package org.openurp.trims.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.Teacher
import org.beangle.webmvc.api.view.View
import org.beangle.data.jpa.dao.OqlBuilder
import org.openurp.base.Department

class TeacherAction extends AbsEamsAction[Teacher] {
  
  def index():String={
    put("departments", getAllDepartments())
    forward()
  }  
  
  def search(): String = {
    put(shortName + "s", entityDao.search(getQueryBuilder()))
    forward()
  }

}