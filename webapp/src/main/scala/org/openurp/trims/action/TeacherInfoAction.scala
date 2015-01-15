package org.openurp.trims.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.Teacher
import org.beangle.webmvc.api.annotation.mapping
import org.beangle.webmvc.api.annotation.param
import java.util.Calendar
import org.beangle.commons.codec.digest.Digests
import org.openurp.platform.ws.ServiceConfig
import org.beangle.data.jpa.dao.OqlBuilder
import org.openurp.hr.base.Staff

class TeacherInfoAction extends RestfulAction[Teacher] {

  @mapping(value = "{id}")
  override def info(@param("id") id: String): String = {
    val teacher = entityDao.get(classOf[Teacher], new Integer(id))
    put(shortName, teacher)
    val query = OqlBuilder.from(classOf[Staff], "staff")
    query.where("staff.person=:person",teacher.person)
    val staffs = entityDao .search(query)
    if(staffs.length>0){
      val staff = staffs(0)
      put("staff", staff)
    }
    
    val birthday = teacher.person.birthday
    if (birthday != null) {
      val now = Calendar.getInstance()
      val cbirthday = Calendar.getInstance()
      cbirthday.setTime(birthday)
      val age = now.get(Calendar.YEAR) - cbirthday.get(Calendar.YEAR) -
        (if (now.get(Calendar.MONTH) < cbirthday.get(Calendar.MONTH)) 1 else 0)
      put("age", age)
    }
    put("service_base", ServiceConfig.wsBase)
    put("photo_url", Digests.md5Hex(teacher.person.code + "@sfu.edu.cn"))
    forward()
  }

  def nav(): String = {
    put("id", get("id"))
    forward()
  }

}