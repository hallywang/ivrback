package com.emag.gamecms.controllers.system

import com.emag.gamecms.domain.system.GameCmsMailServer

class GameCmsMailServerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [gameCmsMailServerInstanceList: GameCmsMailServer.list(params), gameCmsMailServerInstanceTotal: GameCmsMailServer.count()]
    }

    def create = {
        def gameCmsMailServerInstance = new GameCmsMailServer()
        gameCmsMailServerInstance.properties = params
        return [gameCmsMailServerInstance: gameCmsMailServerInstance]
    }

    def save = {
        def gameCmsMailServerInstance = new GameCmsMailServer(params)
        if (gameCmsMailServerInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'gameCmsMailServer.label', default: 'GameCmsMailServer'), gameCmsMailServerInstance.id])}"
            redirect(action: "show", id: gameCmsMailServerInstance.id)
        }
        else {
            render(view: "create", model: [gameCmsMailServerInstance: gameCmsMailServerInstance])
        }
    }

    def show = {
        def gameCmsMailServerInstance = GameCmsMailServer.get(params.id)
        if (!gameCmsMailServerInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsMailServer.label', default: 'GameCmsMailServer'), params.id])}"
            redirect(action: "list")
        }
        else {
            [gameCmsMailServerInstance: gameCmsMailServerInstance]
        }
    }

    def edit = {
        def gameCmsMailServerInstance = GameCmsMailServer.get(params.id)
        if (!gameCmsMailServerInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsMailServer.label', default: 'GameCmsMailServer'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [gameCmsMailServerInstance: gameCmsMailServerInstance]
        }
    }

    def update = {
        def gameCmsMailServerInstance = GameCmsMailServer.get(params.id)
        if (gameCmsMailServerInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (gameCmsMailServerInstance.version > version) {
                    
                    gameCmsMailServerInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'gameCmsMailServer.label', default: 'GameCmsMailServer')] as Object[], "Another user has updated this GameCmsMailServer while you were editing")
                    render(view: "edit", model: [gameCmsMailServerInstance: gameCmsMailServerInstance])
                    return
                }
            }
            gameCmsMailServerInstance.properties = params
            if (!gameCmsMailServerInstance.hasErrors() && gameCmsMailServerInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'gameCmsMailServer.label', default: 'GameCmsMailServer'), gameCmsMailServerInstance.id])}"
                redirect(action: "show", id: gameCmsMailServerInstance.id)
            }
            else {
                render(view: "edit", model: [gameCmsMailServerInstance: gameCmsMailServerInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsMailServer.label', default: 'GameCmsMailServer'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def gameCmsMailServerInstance = GameCmsMailServer.get(params.id)
        if (gameCmsMailServerInstance) {
            try {
                gameCmsMailServerInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'gameCmsMailServer.label', default: 'GameCmsMailServer'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'gameCmsMailServer.label', default: 'GameCmsMailServer'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsMailServer.label', default: 'GameCmsMailServer'), params.id])}"
            redirect(action: "list")
        }
    }
}
