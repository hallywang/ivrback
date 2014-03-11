package com.emag.gamecms.controllers.ivr

import com.emag.gamecms.domain.ivr.IvrUserLogs
import org.springframework.dao.DataIntegrityViolationException

class IvrUserLogsController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index() {
    redirect(action: "list", params: params)
  }

  def list(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    def userLogs = IvrUserLogs.createCriteria().list(params) {
      //添加查询条件
      if (params.msisdn) {
        eq('msisdn', "${params.msisdn}")
      }

      if(params.serviceId){
        eq('serviceId', "${params.serviceId}")
      }

      if(params.operateId){
        eq('operateId',"${params.operateId}")
      }

    }
    [ivrUserLogsInstanceList: userLogs, ivrUserLogsInstanceTotal: userLogs.totalCount]
  }

  def create() {
    [ivrUserLogsInstance: new IvrUserLogs(params)]
  }

  def save() {
    def ivrUserLogsInstance = new IvrUserLogs(params)
    if (!ivrUserLogsInstance.save(flush: true)) {
      render(view: "create", model: [ivrUserLogsInstance: ivrUserLogsInstance])
      return
    }

    flash.message = message(code: 'default.created.message', args: [message(code: 'ivrUserLogs.label', default: 'IvrUserLogs'), ivrUserLogsInstance.id])
    redirect(action: "show", id: ivrUserLogsInstance.id)
  }

  def show(Long id) {
    def ivrUserLogsInstance = IvrUserLogs.get(id)
    if (!ivrUserLogsInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrUserLogs.label', default: 'IvrUserLogs'), id])
      redirect(action: "list")
      return
    }

    [ivrUserLogsInstance: ivrUserLogsInstance]
  }

  def edit(Long id) {
    def ivrUserLogsInstance = IvrUserLogs.get(id)
    if (!ivrUserLogsInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrUserLogs.label', default: 'IvrUserLogs'), id])
      redirect(action: "list")
      return
    }

    [ivrUserLogsInstance: ivrUserLogsInstance]
  }

  def update(Long id, Long version) {
    def ivrUserLogsInstance = IvrUserLogs.get(id)
    if (!ivrUserLogsInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrUserLogs.label', default: 'IvrUserLogs'), id])
      redirect(action: "list")
      return
    }

    if (version != null) {
      if (ivrUserLogsInstance.version > version) {
        ivrUserLogsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                [message(code: 'ivrUserLogs.label', default: 'IvrUserLogs')] as Object[],
                "Another user has updated this IvrUserLogs while you were editing")
        render(view: "edit", model: [ivrUserLogsInstance: ivrUserLogsInstance])
        return
      }
    }

    ivrUserLogsInstance.properties = params

    if (!ivrUserLogsInstance.save(flush: true)) {
      render(view: "edit", model: [ivrUserLogsInstance: ivrUserLogsInstance])
      return
    }

    flash.message = message(code: 'default.updated.message', args: [message(code: 'ivrUserLogs.label', default: 'IvrUserLogs'), ivrUserLogsInstance.id])
    redirect(action: "show", id: ivrUserLogsInstance.id)
  }

  def delete(Long id) {
    def ivrUserLogsInstance = IvrUserLogs.get(id)
    if (!ivrUserLogsInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrUserLogs.label', default: 'IvrUserLogs'), id])
      redirect(action: "list")
      return
    }

    try {
      ivrUserLogsInstance.delete(flush: true)
      flash.message = message(code: 'default.deleted.message', args: [message(code: 'ivrUserLogs.label', default: 'IvrUserLogs'), id])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e) {
      flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ivrUserLogs.label', default: 'IvrUserLogs'), id])
      redirect(action: "show", id: id)
    }
  }
}
