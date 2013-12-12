package com.emag.gamecms.controllers.system

import com.emag.gamecms.domain.system.GameCmsTagTips

class GameCmsTagTipsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [gameCmsTagTipsInstanceList: GameCmsTagTips.list(params), gameCmsTagTipsInstanceTotal: GameCmsTagTips.count()]
    }

    def create = {
        def gameCmsTagTipsInstance = new GameCmsTagTips()
        gameCmsTagTipsInstance.properties = params
        return [gameCmsTagTipsInstance: gameCmsTagTipsInstance]
    }

    def save = {
        def gameCmsTagTipsInstance = new GameCmsTagTips(params)
        if (gameCmsTagTipsInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'gameCmsTagTips.label', default: 'GameCmsTagTips'), gameCmsTagTipsInstance.id])}"
            redirect(action: "show", id: gameCmsTagTipsInstance.id)
        }
        else {
            render(view: "create", model: [gameCmsTagTipsInstance: gameCmsTagTipsInstance])
        }
    }

    def show = {
        def gameCmsTagTipsInstance = GameCmsTagTips.get(params.id)
        if (!gameCmsTagTipsInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsTagTips.label', default: 'GameCmsTagTips'), params.id])}"
            redirect(action: "list")
        }
        else {
            [gameCmsTagTipsInstance: gameCmsTagTipsInstance]
        }
    }

    def edit = {
        def gameCmsTagTipsInstance = GameCmsTagTips.get(params.id)
        if (!gameCmsTagTipsInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsTagTips.label', default: 'GameCmsTagTips'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [gameCmsTagTipsInstance: gameCmsTagTipsInstance]
        }
    }

    def update = {
        def gameCmsTagTipsInstance = GameCmsTagTips.get(params.id)
        if (gameCmsTagTipsInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (gameCmsTagTipsInstance.version > version) {
                    
                    gameCmsTagTipsInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'gameCmsTagTips.label', default: 'GameCmsTagTips')] as Object[], "Another user has updated this GameCmsTagTips while you were editing")
                    render(view: "edit", model: [gameCmsTagTipsInstance: gameCmsTagTipsInstance])
                    return
                }
            }
            gameCmsTagTipsInstance.properties = params
            if (!gameCmsTagTipsInstance.hasErrors() && gameCmsTagTipsInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'gameCmsTagTips.label', default: 'GameCmsTagTips'), gameCmsTagTipsInstance.id])}"
                redirect(action: "show", id: gameCmsTagTipsInstance.id)
            }
            else {
                render(view: "edit", model: [gameCmsTagTipsInstance: gameCmsTagTipsInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsTagTips.label', default: 'GameCmsTagTips'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def gameCmsTagTipsInstance = GameCmsTagTips.get(params.id)
        if (gameCmsTagTipsInstance) {
            try {
                gameCmsTagTipsInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'gameCmsTagTips.label', default: 'GameCmsTagTips'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'gameCmsTagTips.label', default: 'GameCmsTagTips'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsTagTips.label', default: 'GameCmsTagTips'), params.id])}"
            redirect(action: "list")
        }
    }
}
