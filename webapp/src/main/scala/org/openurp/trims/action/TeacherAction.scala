package org.openurp.trims.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.Teacher
import org.beangle.webmvc.api.view.View

class TeacherAction extends RestfulAction[Teacher] {

  override def save(): View = null

  override def remove(): View = null

}