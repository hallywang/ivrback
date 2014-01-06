package com.emag.gamecms.controllers.ivr

import com.emag.gamecms.domain.ivr.IvrBlackUser
import org.springframework.dao.DataIntegrityViolationException

class IvrBlackUserController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index() {
    redirect(action: "list", params: params)
  }

  def list(Integer max) {
    params.max = Math.min(max ?: 10, 100)

    def  users =IvrBlackUser.createCriteria().list(params){
      //添加查询条件
      if(params.msisdn){
        like('msisdn',"%${params.msisdn}%")
      }
      if(params.scope){
        eq('scope',"${params.scope}")
      }
      if(params.status){
        eq('status',Integer.parseInt(params.status))
      }
    }

    [ivrBlackUserInstanceList: users, ivrBlackUserInstanceTotal: users.totalCount]
  }

  def create() {
    [ivrBlackUserInstance: new IvrBlackUser(params)]
  }

  def save() {
    def ivrBlackUserInstance = new IvrBlackUser(params)
    if (!ivrBlackUserInstance.save(flush: true)) {
      render(view: "create", model: [ivrBlackUserInstance: ivrBlackUserInstance])
      return
    }

    flash.message = message(code: 'default.created.message', args: [message(code: 'ivrBlackUser.label', default: 'IvrBlackUser'), ivrBlackUserInstance.id])
    redirect(action: "show", id: ivrBlackUserInstance.id)
  }

  def show(Long id) {
    def ivrBlackUserInstance = IvrBlackUser.get(id)
    if (!ivrBlackUserInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrBlackUser.label', default: 'IvrBlackUser'), id])
      redirect(action: "list")
      return
    }

    [ivrBlackUserInstance: ivrBlackUserInstance]
  }

  def edit(Long id) {
    def ivrBlackUserInstance = IvrBlackUser.get(id)
    if (!ivrBlackUserInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrBlackUser.label', default: 'IvrBlackUser'), id])
      redirect(action: "list")
      return
    }

    [ivrBlackUserInstance: ivrBlackUserInstance]
  }

  def update(Long id, Long version) {
    def ivrBlackUserInstance = IvrBlackUser.get(id)
    if (!ivrBlackUserInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrBlackUser.label', default: 'IvrBlackUser'), id])
      redirect(action: "list")
      return
    }

    if (version != null) {
      if (ivrBlackUserInstance.version > version) {
        ivrBlackUserInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                [message(code: 'ivrBlackUser.label', default: 'IvrBlackUser')] as Object[],
                "Another user has updated this IvrBlackUser while you were editing")
        render(view: "edit", model: [ivrBlackUserInstance: ivrBlackUserInstance])
        return
      }
    }

    ivrBlackUserInstance.properties = params

    if (!ivrBlackUserInstance.save(flush: true)) {
      render(view: "edit", model: [ivrBlackUserInstance: ivrBlackUserInstance])
      return
    }

    flash.message = message(code: 'default.updated.message', args: [message(code: 'ivrBlackUser.label', default: 'IvrBlackUser'), ivrBlackUserInstance.id])
    redirect(action: "show", id: ivrBlackUserInstance.id)
  }

  def delete(Long id) {
    def ivrBlackUserInstance = IvrBlackUser.get(id)
    if (!ivrBlackUserInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrBlackUser.label', default: 'IvrBlackUser'), id])
      redirect(action: "list")
      return
    }

    try {
      ivrBlackUserInstance.delete(flush: true)
      flash.message = message(code: 'default.deleted.message', args: [message(code: 'ivrBlackUser.label', default: 'IvrBlackUser'), id])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e) {
      flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ivrBlackUser.label', default: 'IvrBlackUser'), id])
      redirect(action: "show", id: id)
    }
  }
}
