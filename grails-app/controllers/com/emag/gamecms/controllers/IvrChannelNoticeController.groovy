package com.emag.gamecms.controllers

import com.emag.gamecms.domain.ivr.IvrChannelNotice
import org.springframework.dao.DataIntegrityViolationException

class IvrChannelNoticeController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index() {
    redirect(action: "list", params: params)
  }

  def list(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    [ivrChannelNoticeInstanceList: IvrChannelNotice.list(params), ivrChannelNoticeInstanceTotal: IvrChannelNotice.count()]
  }

  def create() {
    [ivrChannelNoticeInstance: new IvrChannelNotice(params)]
  }

  def save() {
    def ivrChannelNoticeInstance = new IvrChannelNotice(params)
    if (!ivrChannelNoticeInstance.save(flush: true)) {
      render(view: "create", model: [ivrChannelNoticeInstance: ivrChannelNoticeInstance])
      return
    }

    flash.message = message(code: 'default.created.message', args: [message(code: 'ivrChannelNotice.label', default: 'IvrChannelNotice'), ivrChannelNoticeInstance.id])
    redirect(action: "show", id: ivrChannelNoticeInstance.id)
  }

  def show(Long id) {
    def ivrChannelNoticeInstance = IvrChannelNotice.get(id)
    if (!ivrChannelNoticeInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrChannelNotice.label', default: 'IvrChannelNotice'), id])
      redirect(action: "list")
      return
    }

    [ivrChannelNoticeInstance: ivrChannelNoticeInstance]
  }

  def edit(Long id) {
    def ivrChannelNoticeInstance = IvrChannelNotice.get(id)
    if (!ivrChannelNoticeInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrChannelNotice.label', default: 'IvrChannelNotice'), id])
      redirect(action: "list")
      return
    }

    [ivrChannelNoticeInstance: ivrChannelNoticeInstance]
  }

  def update(Long id, Long version) {
    def ivrChannelNoticeInstance = IvrChannelNotice.get(id)
    if (!ivrChannelNoticeInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrChannelNotice.label', default: 'IvrChannelNotice'), id])
      redirect(action: "list")
      return
    }

    if (version != null) {
      if (ivrChannelNoticeInstance.version > version) {
        ivrChannelNoticeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                [message(code: 'ivrChannelNotice.label', default: 'IvrChannelNotice')] as Object[],
                "Another user has updated this IvrChannelNotice while you were editing")
        render(view: "edit", model: [ivrChannelNoticeInstance: ivrChannelNoticeInstance])
        return
      }
    }

    ivrChannelNoticeInstance.properties = params

    if (!ivrChannelNoticeInstance.save(flush: true)) {
      render(view: "edit", model: [ivrChannelNoticeInstance: ivrChannelNoticeInstance])
      return
    }

    flash.message = message(code: 'default.updated.message', args: [message(code: 'ivrChannelNotice.label', default: 'IvrChannelNotice'), ivrChannelNoticeInstance.id])
    redirect(action: "show", id: ivrChannelNoticeInstance.id)
  }

  def delete(Long id) {
    def ivrChannelNoticeInstance = IvrChannelNotice.get(id)
    if (!ivrChannelNoticeInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrChannelNotice.label', default: 'IvrChannelNotice'), id])
      redirect(action: "list")
      return
    }

    try {
      ivrChannelNoticeInstance.delete(flush: true)
      flash.message = message(code: 'default.deleted.message', args: [message(code: 'ivrChannelNotice.label', default: 'IvrChannelNotice'), id])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e) {
      flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ivrChannelNotice.label', default: 'IvrChannelNotice'), id])
      redirect(action: "show", id: id)
    }
  }
}
