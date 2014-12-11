package org.openurp.trims

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.trims.action.TeacherAction
import org.openurp.trims.action.TeacherInfoAction
import org.openurp.trims.action.TeachingAction
import org.openurp.trims.action.CourseSearchTrimsAction
import org.openurp.trims.service.impl.CecServiceImpl
import org.openurp.teach.code.service.internal.BaseCodeServiceImpl
import org.openurp.trims.action.LessonTrimsAction
import org.openurp.trims.action.LessonPeriodAction

class DefaultModule2 extends AbstractBindModule {

  protected override def binding() {
    bind(classOf[CecServiceImpl])

    bind(classOf[CourseSearchTrimsAction], classOf[LessonTrimsAction], classOf[LessonPeriodAction])
    
    // TODO should remoed to openurp-teach-core 
    bind(classOf[BaseCodeServiceImpl])
  }
}