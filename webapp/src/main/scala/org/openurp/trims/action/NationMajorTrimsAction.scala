package org.openurp.trims.action

import org.beangle.data.jpa.dao.OqlBuilder
import javax.security.auth.Subject
import org.beangle.webmvc.api.view.View
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet

class NationMajorTrimsAction extends MajorSearchTrimsAction {

//	def  index() :String={
//		prepareBasicInfo()
//		forward()
//	}
//	
//	/**
//	 * 修改和新建全国专业时调用的动作
//	 */
//	def  edit() :String={
////		val major = getEntity(classOf[NationMajor], "nationMajorTrims")
////		addEntity("nationMajorTrims", major)
//		prepareBasicInfo()
//		forward()
//	}
//
//	/**
//	 * 保存专业信息，新建的全国专业或修改的全国专业. <br>
//	 * 接受主键冲突异常，跳转到异常页面.
//	 */
//	def  save() :View={
//		val majorId = getLong("nationMajorTrims.id")
//
//		val major :NationMajor= null
//		val params = getParams("nationMajorTrims")
//		try {
//			if (null == majorId) {
//				major =  Model.newInstance(classOf[NationMajor])
//				populate(params, major)
//				EntityUtils.evictEmptyProperty(major)
//				info("Create a major with name:" + major.getName())
//			} else {
//				major = entityDao.get(classOf[NationMajor], majorId)
//				buildNationMajor(major)
//				info("Update a major with name:" + major.getName())
//			}
//			major.getEstablishInfos().clear()
//			major.getEstablishInfos().addAll(buildEstablishInfos(major))
//			entityDao.saveOrUpdate(major)
//		} catch {
//		  case e : EntityExistException =>{
//			  info("Failure save or update a major with name:" + major.getName(), e)
//			  forward( "entity.major", "error.model.existed" )
//		  }
//		  case e : org.springframework.dao.DataIntegrityViolationException  =>{
//			info("Failure save or update a major with name:" + major.getName(), e)
//			forward("已存在相同代码的二级学科")
//		}
//		  case e : Exception =>{
//			info("Failure save or update a major with name:" + major.getName(), e)
//			forward("error.occurred")
//		}
//		}
//		 redirect("search", "info.save.success")
//	}
//
//	
//	private def buildNationMajor( major:NationMajor) {
//		major.setSubject(entityDao.get(classOf[Subject], getLong("nationMajorTrims.subject.id"))
//		)
//		major.setCode(get("nationMajorTrims.code"))
//		major.setName(get("nationMajorTrims.name"))
//		major.setEngName(get("nationMajorTrims.engName"))
//		major.setIntroduction(get("nationMajorTrims.introduction"))
//		
//	}
//
//	def  search() :String={
//		super.majorsOfNationSearch()
//		forward()
//	}
//	
//	def  remove():View= {
//		val params = new HashMap()
//		params.put("ids", SeqStringUtil.transformToLong(get("id")))
//		entityDao.getEntityDao().executeUpdateHql("delete from NationMajorEstablishInfo where id in (select info.id from NationMajor nm join nm.establishInfos info where nm.id in(:ids))", params)
//		entityDao.getEntityDao().executeUpdateHql("delete from NationMajor where id in (:ids)", params)
//		redirect("search", "info.delete.success")
//	}
//	
//	def  info():String= {
//		put("major", entityDao.get(classOf[NationMajor], getLong("major.id")))
//		forward()
//	}
//	
//	private def prepareBasicInfo() {
//		val query = OqlBuilder.from(classOf[Subject], "subject")
//    	query.where("subject.category, subject")
//    	query.orderBy("subject.category.code")
//    	query.orderBy("subject.code")
//    val project = getProject()
//    	put("subjectCategories", baseCodeService.getCodes(project,classOf[SubjectCategory]))
//    	put("categorySubjects", entityDao.search(query))
//	}
//	
//	private def buildEstablishInfos(major :NationMajor ) {
//		val res = new HashSet()
//		val infoIds = get("infoIds").get.split(",", 0)
//		for(i <-0 until infoIds.length ) {
////			if(StringUtils.isEmpty(infoIds[i])) continue
//			val schoolName = get(infoIds(i) + "_schoolName").toString()
//			val lengthOfSchooling = get(infoIds(i) + "_lengthOfSchooling").toString()
//			val degreeName = get(infoIds(i) + "_degreeName").toString()
//			val departmentName = get(infoIds(i) + "_departmentName").toString()
//			val establishedAt = get(infoIds(i) + "_establishedAt").toString()
//			val province = get(infoIds(i) + "_province").toString()
//			val managedBy = get(infoIds(i) + "_managedBy").toString()
//			val district = get(infoIds(i) + "_district").toString()
//			
//			val info = new NationMajorEstablishInfo()
//			info.setDegreeName(degreeName)
//			info.setSchoolName(schoolName)
//			info.setDistrict(district)
//			info.setEstablishedAt(establishedAt)
//			info.setLengthOfSchooling(new Integer(lengthOfSchooling))
//			info.setManagedBy(managedBy)
//			info.setProvince(province)
//			info.setDepartmentName(departmentName)
//			entityDao.saveOrUpdate(info)
//			res.add(info)
//		}
//		res
//	}
}