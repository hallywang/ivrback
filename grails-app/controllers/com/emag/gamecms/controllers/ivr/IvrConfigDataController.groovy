package com.emag.gamecms.controllers.ivr

import com.emag.gamecms.domain.ivr.IvrConfigData
import org.springframework.dao.DataIntegrityViolationException

class IvrConfigDataController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index() {
    redirect(action: "list", params: params)
  }

  def list(Integer max) {
    params.max = Math.min(max ?: 10, 100)

    def datas = IvrConfigData.createCriteria().list(params) {
      //添加查询条件

      if(params.serviceId){
        eq('serviceId', "${params.serviceId}")
      }

      if(params.operateId){
        eq('operateId',"${params.operateId}")
      }

    }

    [ivrConfigDataInstanceList: datas, ivrConfigDataInstanceTotal: datas.totalCount]
  }

  def create() {
    [ivrConfigDataInstance: new IvrConfigData(params)]
  }

  def save() {
    def ivrConfigDataInstance = new IvrConfigData(params)
    if (!ivrConfigDataInstance.save(flush: true)) {
      render(view: "create", model: [ivrConfigDataInstance: ivrConfigDataInstance])
      return
    }

    flash.message = message(code: 'default.created.message', args: [message(code: 'ivrConfigData.label', default: 'IvrConfigData'), ivrConfigDataInstance.id])
    redirect(action: "show", id: ivrConfigDataInstance.id)
  }

  def show(Long id) {
    def ivrConfigDataInstance = IvrConfigData.get(id)
    if (!ivrConfigDataInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrConfigData.label', default: 'IvrConfigData'), id])
      redirect(action: "list")
      return
    }

    [ivrConfigDataInstance: ivrConfigDataInstance]
  }

  def edit(Long id) {
    def ivrConfigDataInstance = IvrConfigData.get(id)
    if (!ivrConfigDataInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrConfigData.label', default: 'IvrConfigData'), id])
      redirect(action: "list")
      return
    }

    [ivrConfigDataInstance: ivrConfigDataInstance]
  }

  def update(Long id, Long version) {
    def ivrConfigDataInstance = IvrConfigData.get(id)
    if (!ivrConfigDataInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrConfigData.label', default: 'IvrConfigData'), id])
      redirect(action: "list")
      return
    }

    if (version != null) {
      if (ivrConfigDataInstance.version > version) {
        ivrConfigDataInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                [message(code: 'ivrConfigData.label', default: 'IvrConfigData')] as Object[],
                "Another user has updated this IvrConfigData while you were editing")
        render(view: "edit", model: [ivrConfigDataInstance: ivrConfigDataInstance])
        return
      }
    }

    ivrConfigDataInstance.properties = params

    if (!ivrConfigDataInstance.save(flush: true)) {
      render(view: "edit", model: [ivrConfigDataInstance: ivrConfigDataInstance])
      return
    }

    flash.message = message(code: 'default.updated.message', args: [message(code: 'ivrConfigData.label', default: 'IvrConfigData'), ivrConfigDataInstance.id])
    redirect(action: "show", id: ivrConfigDataInstance.id)
  }

  def delete(Long id) {
    def ivrConfigDataInstance = IvrConfigData.get(id)
    if (!ivrConfigDataInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrConfigData.label', default: 'IvrConfigData'), id])
      redirect(action: "list")
      return
    }

    try {
      ivrConfigDataInstance.delete(flush: true)
      flash.message = message(code: 'default.deleted.message', args: [message(code: 'ivrConfigData.label', default: 'IvrConfigData'), id])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e) {
      flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ivrConfigData.label', default: 'IvrConfigData'), id])
      redirect(action: "show", id: id)
    }
  }
}
