package org.openurp.trims

import org.beangle.commons.inject.bind.AbstractBindModule
import org.beangle.commons.io.IOs
import org.beangle.commons.lang.ClassLoaders
import org.openurp.trims.action.{DegreeResearchAvgCountAction, DepartJobRatioAction, DepartPeriodCountAction, DepartResearchAction, DepartTeacherCountAction, ExpCourseSearchTrimsAction, HarvestTypeCountAction, MajorSearchTrimsAction, PeriodStatisticsAction, ResearchAction, TeacherAction, TeacherInfoAction, TeachingAction, TitlePeriodCountAction, TitleResearchAvgCountAction, TitleResearchCountAction}

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
    bind(classOf[DegreeResearchAvgCountAction])
    
    bind(classOf[DepartJobRatioAction])
  }
}