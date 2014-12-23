package org.openurp.trims

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.trims.action._
import org.openurp.trims.service.impl._
import org.openurp.teach.code.service.internal.BaseCodeServiceImpl
import org.openurp.trims.security.DaoUserStore

class DefaultModule2 extends AbstractBindModule {

  protected override def binding() {
    bind(classOf[CecServiceImpl])

    bind(classOf[CourseSearchTrimsAction], classOf[LessonTrimsAction], classOf[LessonPeriodAction])
    bind(classOf[LessonTeachClassStdCountAction], classOf[CourseTakeReStudyAction])
    bind(classOf[StudentDepartStatisticsAction], classOf[TeacherTitleAction], classOf[TeacherTitleAllAction])

    // TODO should remoed to openurp-teach-core 
    bind(classOf[BaseCodeServiceImpl])
  }
}