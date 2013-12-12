package com.emag.gamecms.controllers.system

import com.emag.gamecms.domain.system.GameCmsReferer
import com.vivame.util.TimeUtil


class GameCmsRefererController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [gameCmsRefererInstanceList: GameCmsReferer.list(params), gameCmsRefererInstanceTotal: GameCmsReferer.count()]
    }

    def create = {
        def gameCmsRefererInstance = new GameCmsReferer()
        gameCmsRefererInstance.properties = params
        return [gameCmsRefererInstance: gameCmsRefererInstance]
    }

    def save = {
        def gameCmsRefererInstance = new GameCmsReferer(params)
        if (gameCmsRefererInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'gameCmsReferer.label', default: 'GameCmsReferer'), gameCmsRefererInstance.id])}"
            redirect(action: "show", id: gameCmsRefererInstance.id)
        }
        else {
            render(view: "create", model: [gameCmsRefererInstance: gameCmsRefererInstance])
        }
    }

    def show = {
        def gameCmsRefererInstance = GameCmsReferer.get(params.id)
        if (!gameCmsRefererInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsReferer.label', default: 'GameCmsReferer'), params.id])}"
            redirect(action: "list")
        }
        else {

            [gameCmsRefererInstance: gameCmsRefererInstance]
        }
    }

    def edit = {
        def gameCmsRefererInstance = GameCmsReferer.get(params.id)
        if (!gameCmsRefererInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsReferer.label', default: 'GameCmsReferer'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [gameCmsRefererInstance: gameCmsRefererInstance]
        }
    }

    def update = {
        def gameCmsRefererInstance = GameCmsReferer.get(params.id)
        if (gameCmsRefererInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (gameCmsRefererInstance.version > version) {
                    
                    gameCmsRefererInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'gameCmsReferer.label', default: 'GameCmsReferer')] as Object[], "Another user has updated this GameCmsReferer while you were editing")
                    render(view: "edit", model: [gameCmsRefererInstance: gameCmsRefererInstance])
                    return
                }
            }
            gameCmsRefererInstance.properties = params
            if (!gameCmsRefererInstance.hasErrors() && gameCmsRefererInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'gameCmsReferer.label', default: 'GameCmsReferer'), gameCmsRefererInstance.id])}"
                redirect(action: "show", id: gameCmsRefererInstance.id)
            }
            else {
                render(view: "edit", model: [gameCmsRefererInstance: gameCmsRefererInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsReferer.label', default: 'GameCmsReferer'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def gameCmsRefererInstance = GameCmsReferer.get(params.id)
        if (gameCmsRefererInstance) {
            try {
                gameCmsRefererInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'gameCmsReferer.label', default: 'GameCmsReferer'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'gameCmsReferer.label', default: 'GameCmsReferer'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsReferer.label', default: 'GameCmsReferer'), params.id])}"
            redirect(action: "list")
        }
    }
}
