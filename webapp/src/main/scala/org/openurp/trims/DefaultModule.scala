package org.openurp.trims

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.trims.action.TeacherAction
import org.openurp.trims.action.TeacherInfoAction
import org.openurp.trims.action.TeachingAction
import org.openurp.trims.action.ExpCourseSearchTrimsAction
import org.openurp.trims.action.MajorSearchTrimsAction
import com.sun.org.apache.bcel.internal.generic.ClassObserver
import org.openurp.trims.action.PeriodStatisticsAction
import org.openurp.trims.action.DepartPeriodCountAction
import org.openurp.trims.action.DepartTeacherCountAction
import org.openurp.trims.action.TitlePeriodCountAction
import org.openurp.trims.action.ResearchAction

class DefaultModule extends AbstractBindModule {

  protected override def binding() {
    bind(classOf[TeacherAction])
    bind(classOf[TeacherInfoAction])
    bind(classOf[TeachingAction])
    bind(classOf[ExpCourseSearchTrimsAction])
    bind(classOf[MajorSearchTrimsAction])
    bind(classOf[PeriodStatisticsAction])
    bind(classOf[DepartPeriodCountAction])
    bind(classOf[DepartTeacherCountAction])
    bind(classOf[TitlePeriodCountAction])
    bind(classOf[ResearchAction])
  }
}