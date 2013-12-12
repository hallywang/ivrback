package com.emag.gamecms.controllers.system

import com.emag.gamecms.domain.system.GameCmsProtectUrl
import com.vivame.util.TimeUtil

class GameCmsProtectUrlController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
  def index = {
    redirect(action: "list", params: params)
  }

  def list = {
    params.max = Math.min(params.max ? params.int('max') : 10, 100)
    params.offset = params.offset ? params.int('offset') : 0

    StringBuffer hql = new StringBuffer()
    hql << "from GameCmsProtectUrl"
    def urllist = GameCmsProtectUrl.executeQuery(hql.toString())
    def allGameCount = urllist.size()
    def rtnList
    def toIndex = (params.offset + params.max) <= allGameCount ? (params.offset + params.max) : allGameCount //本页截止显示的游戏index
    if (params.offset <= toIndex) {
      rtnList = urllist.subList(params.offset, toIndex) //页面上只显示本页游戏
    }

    [gameCmsProtectUrlInstanceList: rtnList, gameCmsProtectUrlInstanceTotal: allGameCount]
  }

  def create = {
    def gameCmsProtectUrlInstance = new GameCmsProtectUrl()
    gameCmsProtectUrlInstance.properties = params
    return [gameCmsProtectUrlInstance: gameCmsProtectUrlInstance]
  }

  def save = {
    def gameCmsProtectUrlInstance = new GameCmsProtectUrl(params)

      
    if (gameCmsProtectUrlInstance.save(flush: true)) {
      flash.message = "${message(code: 'default.created.message', args: [message(code: 'gameCmsProtectUrl.label', default: 'GameCmsProtectUrl'), gameCmsProtectUrlInstance.id])}"
      redirect(action: "show", id: gameCmsProtectUrlInstance.id)
    }
    else {
      render(view: "create", model: [gameCmsProtectUrlInstance: gameCmsProtectUrlInstance])
    }
  }

  def show = {
    def gameCmsProtectUrlInstance = GameCmsProtectUrl.get(params.id)

    if (!gameCmsProtectUrlInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsProtectUrl.label', default: 'GameCmsProtectUrl'), params.id])}"
      redirect(action: "list")
    }
    else {
      [gameCmsProtectUrlInstance: gameCmsProtectUrlInstance]
    }
  }

  def edit = {
    def gameCmsProtectUrlInstance = GameCmsProtectUrl.get(params.id)

    if (!gameCmsProtectUrlInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsProtectUrl.label', default: 'GameCmsProtectUrl'), params.id])}"
      redirect(action: "list")
    }
    else {

      return [gameCmsProtectUrlInstance: gameCmsProtectUrlInstance]
    }
  }

  def update = {
    def gameCmsProtectUrlInstance = GameCmsProtectUrl.get(params.id)



    if (gameCmsProtectUrlInstance) {
      if (params.version) {
        def version = params.version.toLong()
        if (gameCmsProtectUrlInstance.version > version) {

          gameCmsProtectUrlInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'gameCmsProtectUrl.label', default: 'GameCmsProtectUrl')] as Object[], "Another user has updated this GameCmsProtectUrl while you were editing")
          render(view: "edit", model: [gameCmsProtectUrlInstance: gameCmsProtectUrlInstance])
          return
        }
      }
      gameCmsProtectUrlInstance.properties = params

      if (!gameCmsProtectUrlInstance.hasErrors() && gameCmsProtectUrlInstance.save(flush: true)) {
        flash.message = "${message(code: 'default.updated.message', args: [message(code: 'gameCmsProtectUrl.label', default: 'GameCmsProtectUrl'), gameCmsProtectUrlInstance.id])}"
        redirect(action: "show", id: gameCmsProtectUrlInstance.id)
      }
      else {
        render(view: "edit", model: [gameCmsProtectUrlInstance: gameCmsProtectUrlInstance])
      }
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsProtectUrl.label', default: 'GameCmsProtectUrl'), params.id])}"
      redirect(action: "list")
    }
  }

  def delete = {
    def gameCmsProtectUrlInstance = GameCmsProtectUrl.get(params.id)
    if (gameCmsProtectUrlInstance) {
      try {
        gameCmsProtectUrlInstance.delete(flush: true)
        flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'gameCmsProtectUrl.label', default: 'GameCmsProtectUrl'), params.id])}"
        redirect(action: "list")
      }
      catch (org.springframework.dao.DataIntegrityViolationException e) {
        flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'gameCmsProtectUrl.label', default: 'GameCmsProtectUrl'), params.id])}"
        redirect(action: "show", id: params.id)
      }
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsProtectUrl.label', default: 'GameCmsProtectUrl'), params.id])}"
      redirect(action: "list")
    }
  }
}
