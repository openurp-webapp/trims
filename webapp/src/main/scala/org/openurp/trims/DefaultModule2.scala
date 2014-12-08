package org.openurp.trims

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.trims.action.TeacherAction
import org.openurp.trims.action.TeacherInfoAction
import org.openurp.trims.action.TeachingAction
import org.openurp.trims.action.CourseSearchTrimsAction

class DefaultModule2 extends AbstractBindModule {

  protected override def binding() {
    bind(classOf[CourseSearchTrimsAction])
  }
}