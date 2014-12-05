package org.openurp.trims

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.trims.action.TeacherAction
import org.openurp.trims.action.TeacherInfoAction
import org.openurp.trims.action.TeachingAction

class DefaultModule extends AbstractBindModule {

  protected override def binding() {
    bind(classOf[TeacherAction])
    bind(classOf[TeacherInfoAction])
    bind(classOf[TeachingAction])
  }
}