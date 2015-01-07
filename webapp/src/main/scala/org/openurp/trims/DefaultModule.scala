package org.openurp.trims

import org.openurp.trims.action._
import org.beangle.commons.inject.bind.AbstractBindModule
import com.sun.org.apache.bcel.internal.generic.ClassObserver

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
    bind(classOf[DepartResearchAction])
    bind(classOf[HarvestTypeCountAction])
    bind(classOf[TitleResearchCountAction])
    bind(classOf[TitleResearchAvgCountAction])
  }
}