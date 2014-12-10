package org.openurp.trims

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.trims.action.TeacherAction
import org.openurp.trims.action.TeacherInfoAction
import org.openurp.trims.action.TeachingAction
import org.openurp.trims.action.CourseSearchTrimsAction
import org.openurp.trims.action.CourseAction
import org.openurp.trims.service.impl.CecServiceImpl

class DefaultModule2 extends AbstractBindModule {

  protected override def binding() {
    bind(classOf[CourseSearchTrimsAction])
    bind(classOf[CourseAction], classOf[CecServiceImpl])
  }
}