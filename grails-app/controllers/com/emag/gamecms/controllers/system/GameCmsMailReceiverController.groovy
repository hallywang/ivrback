package com.emag.gamecms.controllers.system

import com.emag.gamecms.domain.system.GameCmsMailReceiver

class GameCmsMailReceiverController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index = {
    redirect(action: "create", params: params)
  }

  def create = {
    def gameCmsMailReceiverInstance = new GameCmsMailReceiver()
    gameCmsMailReceiverInstance.properties = params
    return [gameCmsMailReceiverInstance: gameCmsMailReceiverInstance]
  }

  def save = {
    def gameCmsMailReceiverInstance = new GameCmsMailReceiver(params)
    if (gameCmsMailReceiverInstance.save(flush: true)) {
      flash.message = "${message(code: 'default.created.message', args: [message(code: 'gameCmsMailReceiver.label', default: 'GameCmsMailReceiver'), gameCmsMailReceiverInstance.id])}"
      redirect(controller: 'gameCmsMailServer', action: 'show', id: gameCmsMailReceiverInstance.mailServer.id)
    }
    else {
      render(view: "create", model: [gameCmsMailReceiverInstance: gameCmsMailReceiverInstance])
    }
  }

  def show = {
    def gameCmsMailReceiverInstance = GameCmsMailReceiver.get(params.id)
    if (!gameCmsMailReceiverInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsMailReceiver.label', default: 'GameCmsMailReceiver'), params.id])}"
      redirect(action: "list")
    }
    else {
      [gameCmsMailReceiverInstance: gameCmsMailReceiverInstance]
    }
  }

  def edit = {
    def gameCmsMailReceiverInstance = GameCmsMailReceiver.get(params.id)
    if (!gameCmsMailReceiverInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsMailReceiver.label', default: 'GameCmsMailReceiver'), params.id])}"
      redirect(action: "list")
    }
    else {
      return [gameCmsMailReceiverInstance: gameCmsMailReceiverInstance]
    }
  }

  def update = {
    def gameCmsMailReceiverInstance = GameCmsMailReceiver.get(params.id)
    if (gameCmsMailReceiverInstance) {
      if (params.version) {
        def version = params.version.toLong()
        if (gameCmsMailReceiverInstance.version > version) {

          gameCmsMailReceiverInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'gameCmsMailReceiver.label', default: 'GameCmsMailReceiver')] as Object[], "Another user has updated this GameCmsMailReceiver while you were editing")
          render(view: "edit", model: [gameCmsMailReceiverInstance: gameCmsMailReceiverInstance])
          return
        }
      }
      gameCmsMailReceiverInstance.properties = params
      if (!gameCmsMailReceiverInstance.hasErrors() && gameCmsMailReceiverInstance.save(flush: true)) {
        flash.message = "${message(code: 'default.updated.message', args: [message(code: 'gameCmsMailReceiver.label', default: 'GameCmsMailReceiver'), gameCmsMailReceiverInstance.id])}"
        redirect(controller: 'gameCmsMailServer', action: 'show', id: gameCmsMailReceiverInstance.mailServer.id)
      }
      else {
        render(view: "edit", model: [gameCmsMailReceiverInstance: gameCmsMailReceiverInstance])
      }
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsMailReceiver.label', default: 'GameCmsMailReceiver'), params.id])}"
      redirect(controller: 'gameCmsMailServer', action: 'list')
    }
  }

  def delete = {
    def gameCmsMailReceiverInstance = GameCmsMailReceiver.get(params.id)
    if (gameCmsMailReceiverInstance) {
      try {
        gameCmsMailReceiverInstance.delete(flush: true)
        flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'gameCmsMailReceiver.label', default: 'GameCmsMailReceiver'), params.id])}"
        redirect(controller: 'gameCmsMailServer', action: "show", id: gameCmsMailReceiverInstance.mailServer.id)
      }
      catch (org.springframework.dao.DataIntegrityViolationException e) {
        flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'gameCmsMailReceiver.label', default: 'GameCmsMailReceiver'), params.id])}"
        redirect(action: "show", id: params.id)
      }
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsMailReceiver.label', default: 'GameCmsMailReceiver'), params.id])}"
      redirect(controller: 'gameCmsMailServer', action: 'list')
    }
  }
}
