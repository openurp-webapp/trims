package org.openurp.trims

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.trims.action.TeacherAction
import org.openurp.trims.action.TeacherInfoAction
import org.openurp.trims.action.TeachingAction
import org.openurp.trims.action.CourseSearchTrimsAction
import org.openurp.trims.service.impl.CecServiceImpl
import org.openurp.teach.code.service.internal.BaseCodeServiceImpl

class DefaultModule2 extends AbstractBindModule {

  protected override def binding() {
    bind(classOf[CecServiceImpl])

    bind(classOf[CourseSearchTrimsAction])
    
    // TODO should remoed to openurp-teach-core 
    bind(classOf[BaseCodeServiceImpl])
  }
}