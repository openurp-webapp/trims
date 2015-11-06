package org.openurp.edu.trims.action

import java.util.Calendar
import org.beangle.commons.codec.digest.Digests
import org.beangle.webmvc.api.annotation.{ mapping, param }
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.hr.base.model.Staff
import org.openurp.platform.api.Urp

class TeacherInfoAction extends RestfulAction[Staff] {

  @mapping(value = "{id}")
  def info(@param("id") id: Long): String = {
    val staff = entityDao.get(classOf[Staff], id)
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
    put("service_base", Urp.wsBase)
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