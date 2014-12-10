package org.openurp.trims.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.base.Teacher
import org.beangle.webmvc.api.annotation.mapping
import org.beangle.webmvc.api.annotation.param
import java.util.Calendar
import org.beangle.commons.codec.digest.Digests
import org.openurp.platform.ws.ServiceConfig

class TeacherInfoAction extends RestfulAction[Teacher]{  
  
  @mapping(value = "{id}")
  override def info(@param("id") id: String): String = {
    val teacher = entityDao .get(classOf[Teacher], new Integer(id))
    put(shortName, teacher)
    val birthday = teacher.person.birthday
    if (birthday !=null){
      val now = Calendar.getInstance()
      val cbirthday= Calendar.getInstance()
      cbirthday.setTime(birthday)
      val age = now.get(Calendar.YEAR)-cbirthday.get(Calendar.YEAR) - 
      	(if(now.get(Calendar.MONTH)<cbirthday.get(Calendar.MONTH)) 1 else 0)
      put("age", age)
    }
    put("service_base",ServiceConfig.wsBase)
    put("photo_url",Digests.md5Hex(teacher.person.code+"@sfu.edu.cn"))
    forward()
  }
  
  def nav():String = {
    put("id", get("id"))
    forward()
  }

}