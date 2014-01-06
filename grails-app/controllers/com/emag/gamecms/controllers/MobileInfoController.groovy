package com.emag.gamecms.controllers

import com.emag.gamecms.domain.city.MobileInfo
import org.springframework.dao.DataIntegrityViolationException

class MobileInfoController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index() {
    redirect(action: "list", params: params)
  }

  def list(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    String mobilePrefix = params.mobilePrefix
    def mobileInfos = MobileInfo.createCriteria().list(params) {
      //添加查询条件
      if (mobilePrefix) {

        if (mobilePrefix.length() > 7) {
          mobilePrefix = mobilePrefix.substring(0,7)
        }
        like('mobilePrefix', "%${mobilePrefix}%")
      }
    }
    [mobileInfoInstanceList: mobileInfos, mobileInfoInstanceTotal: mobileInfos.totalCount]
  }

  def create() {
    [mobileInfoInstance: new MobileInfo(params)]
  }

  def save() {
    def mobileInfoInstance = new MobileInfo(params)
    if (!mobileInfoInstance.save(flush: true)) {
      render(view: "create", model: [mobileInfoInstance: mobileInfoInstance])
      return
    }

    flash.message = message(code: 'default.created.message', args: [message(code: 'mobileInfo.label', default: 'MobileInfo'), mobileInfoInstance.id])
    redirect(action: "show", id: mobileInfoInstance.id)
  }

  def show(Long id) {
    def mobileInfoInstance = MobileInfo.get(id)
    if (!mobileInfoInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'mobileInfo.label', default: 'MobileInfo'), id])
      redirect(action: "list")
      return
    }

    [mobileInfoInstance: mobileInfoInstance]
  }

  def edit(Long id) {
    def mobileInfoInstance = MobileInfo.get(id)
    if (!mobileInfoInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'mobileInfo.label', default: 'MobileInfo'), id])
      redirect(action: "list")
      return
    }

    [mobileInfoInstance: mobileInfoInstance]
  }

  def update(Long id, Long version) {
    def mobileInfoInstance = MobileInfo.get(id)
    if (!mobileInfoInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'mobileInfo.label', default: 'MobileInfo'), id])
      redirect(action: "list")
      return
    }

    if (version != null) {
      if (mobileInfoInstance.version > version) {
        mobileInfoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                [message(code: 'mobileInfo.label', default: 'MobileInfo')] as Object[],
                "Another user has updated this MobileInfo while you were editing")
        render(view: "edit", model: [mobileInfoInstance: mobileInfoInstance])
        return
      }
    }

    mobileInfoInstance.properties = params

    if (!mobileInfoInstance.save(flush: true)) {
      render(view: "edit", model: [mobileInfoInstance: mobileInfoInstance])
      return
    }

    flash.message = message(code: 'default.updated.message', args: [message(code: 'mobileInfo.label', default: 'MobileInfo'), mobileInfoInstance.id])
    redirect(action: "show", id: mobileInfoInstance.id)
  }

  def delete(Long id) {
    def mobileInfoInstance = MobileInfo.get(id)
    if (!mobileInfoInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'mobileInfo.label', default: 'MobileInfo'), id])
      redirect(action: "list")
      return
    }

    try {
      mobileInfoInstance.delete(flush: true)
      flash.message = message(code: 'default.deleted.message', args: [message(code: 'mobileInfo.label', default: 'MobileInfo'), id])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e) {
      flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'mobileInfo.label', default: 'MobileInfo'), id])
      redirect(action: "show", id: id)
    }
  }
}
