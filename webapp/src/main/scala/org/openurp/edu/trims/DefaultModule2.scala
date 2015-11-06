package org.openurp.edu.trims

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.edu.trims.action._
import org.openurp.edu.trims.service.impl._

class DefaultModule2 extends AbstractBindModule {

  protected override def binding() {
    bind(classOf[CecServiceImpl])

    bind(classOf[CourseSearchTrimsAction], classOf[LessonTrimsAction], classOf[LessonPeriodAction])
    bind(classOf[LessonTeachClassStdCountAction], classOf[CourseTakeReStudyAction])
    bind(classOf[StudentDepartStatisticsAction], classOf[TeacherTitleAction], classOf[TeacherTitleAllAction])
    bind(classOf[TeacherTitleLevelAllAction], classOf[TitleLevelPeriodCountAction])
    bind(classOf[TeachingQualityAction])
    bind(classOf[StudentAreaAction])
    // TODO should remoed to openurp-teach-core 
//    bind(classOf[BaseCodeServiceImpl])
  }
}