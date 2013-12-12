package com.emag.gamecms.controllers.system

import com.emag.gamecms.domain.system.GameCmsTagParams

class GameCmsTagParamsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [gameCmsTagParamsInstanceList: GameCmsTagParams.list(params), gameCmsTagParamsInstanceTotal: GameCmsTagParams.count()]
    }

    def create = {
        def gameCmsTagParamsInstance = new GameCmsTagParams()
        gameCmsTagParamsInstance.properties = params
        return [gameCmsTagParamsInstance: gameCmsTagParamsInstance]
    }

    def save = {
        def gameCmsTagParamsInstance = new GameCmsTagParams(params)
        if (gameCmsTagParamsInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'gameCmsTagParams.label', default: 'GameCmsTagParams'), gameCmsTagParamsInstance.id])}"
            redirect(action: "show", id: gameCmsTagParamsInstance.id)
        }
        else {
            render(view: "create", model: [gameCmsTagParamsInstance: gameCmsTagParamsInstance])
        }
    }

    def show = {
        def gameCmsTagParamsInstance = GameCmsTagParams.get(params.id)
        if (!gameCmsTagParamsInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsTagParams.label', default: 'GameCmsTagParams'), params.id])}"
            redirect(action: "list")
        }
        else {
            [gameCmsTagParamsInstance: gameCmsTagParamsInstance]
        }
    }

    def edit = {
        def gameCmsTagParamsInstance = GameCmsTagParams.get(params.id)
        if (!gameCmsTagParamsInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsTagParams.label', default: 'GameCmsTagParams'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [gameCmsTagParamsInstance: gameCmsTagParamsInstance]
        }
    }

    def update = {
        def gameCmsTagParamsInstance = GameCmsTagParams.get(params.id)
        if (gameCmsTagParamsInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (gameCmsTagParamsInstance.version > version) {
                    
                    gameCmsTagParamsInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'gameCmsTagParams.label', default: 'GameCmsTagParams')] as Object[], "Another user has updated this GameCmsTagParams while you were editing")
                    render(view: "edit", model: [gameCmsTagParamsInstance: gameCmsTagParamsInstance])
                    return
                }
            }
            gameCmsTagParamsInstance.properties = params
            if (!gameCmsTagParamsInstance.hasErrors() && gameCmsTagParamsInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'gameCmsTagParams.label', default: 'GameCmsTagParams'), gameCmsTagParamsInstance.id])}"
                redirect(action: "show", id: gameCmsTagParamsInstance.id)
            }
            else {
                render(view: "edit", model: [gameCmsTagParamsInstance: gameCmsTagParamsInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsTagParams.label', default: 'GameCmsTagParams'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def gameCmsTagParamsInstance = GameCmsTagParams.get(params.id)
        if (gameCmsTagParamsInstance) {
            try {
                gameCmsTagParamsInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'gameCmsTagParams.label', default: 'GameCmsTagParams'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'gameCmsTagParams.label', default: 'GameCmsTagParams'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsTagParams.label', default: 'GameCmsTagParams'), params.id])}"
            redirect(action: "list")
        }
    }
}
