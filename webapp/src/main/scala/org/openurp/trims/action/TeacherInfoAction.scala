package org.openurp.trims.action

import java.util.Calendar
import org.beangle.commons.codec.digest.Digests
import org.beangle.webmvc.api.annotation.{mapping, param}
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.platform.ws.ServiceConfig
import org.openurp.hr.base.model.Staff

class TeacherInfoAction extends RestfulAction[Staff] {

  @mapping(value = "{id}")
  override def info(@param("id") id: String): String = {
    val staff = entityDao.get(classOf[Staff], new java.lang.Long(id))
    val person = staff.person
    val birthday = person.birthday
    if (birthday != null) {
      val now = Calendar.getInstance()
      val cbirthday = Calendar.getInstance()
      cbirthday.setTime(birthday)
      val age = now.get(Calendar.YEAR) - cbirthday.get(Calendar.YEAR) -
        (if (now.get(Calendar.MONTH) < cbirthday.get(Calendar.MONTH)) 1 else 0)
      put("age", age)
    }
    put("service_base", ServiceConfig.wsBase)
    put("person", person)
    put("photo_url", Digests.md5Hex(person.code + "@sfu.edu.cn"))
    put("staff", staff)
    forward()
  }

  def nav(): String = {
    put("id", get("id"))
    forward()
  }

}