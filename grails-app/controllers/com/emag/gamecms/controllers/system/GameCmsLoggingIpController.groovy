package com.emag.gamecms.controllers.system

import com.emag.gamecms.domain.system.GameCmsLoggingIp

class GameCmsLoggingIpController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [gameCmsLoggingIpInstanceList: GameCmsLoggingIp.list(params), gameCmsLoggingIpInstanceTotal: GameCmsLoggingIp.count()]
    }

    def create = {
        def gameCmsLoggingIpInstance = new GameCmsLoggingIp()
        gameCmsLoggingIpInstance.properties = params
        return [gameCmsLoggingIpInstance: gameCmsLoggingIpInstance]
    }

    def save = {
        def gameCmsLoggingIpInstance = new GameCmsLoggingIp(params)
        if (gameCmsLoggingIpInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'gameCmsLoggingIp.label', default: 'GameCmsLoggingIp'), gameCmsLoggingIpInstance.id])}"
            redirect(action: "show", id: gameCmsLoggingIpInstance.id)
        }
        else {
            render(view: "create", model: [gameCmsLoggingIpInstance: gameCmsLoggingIpInstance])
        }
    }

    def show = {
        def gameCmsLoggingIpInstance = GameCmsLoggingIp.get(params.id)
        if (!gameCmsLoggingIpInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsLoggingIp.label', default: 'GameCmsLoggingIp'), params.id])}"
            redirect(action: "list")
        }
        else {
            [gameCmsLoggingIpInstance: gameCmsLoggingIpInstance]
        }
    }

    def edit = {
        def gameCmsLoggingIpInstance = GameCmsLoggingIp.get(params.id)
        if (!gameCmsLoggingIpInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsLoggingIp.label', default: 'GameCmsLoggingIp'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [gameCmsLoggingIpInstance: gameCmsLoggingIpInstance]
        }
    }

    def update = {
        def gameCmsLoggingIpInstance = GameCmsLoggingIp.get(params.id)
        if (gameCmsLoggingIpInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (gameCmsLoggingIpInstance.version > version) {
                    
                    gameCmsLoggingIpInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'gameCmsLoggingIp.label', default: 'GameCmsLoggingIp')] as Object[], "Another user has updated this GameCmsLoggingIp while you were editing")
                    render(view: "edit", model: [gameCmsLoggingIpInstance: gameCmsLoggingIpInstance])
                    return
                }
            }
            gameCmsLoggingIpInstance.properties = params
            if (!gameCmsLoggingIpInstance.hasErrors() && gameCmsLoggingIpInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'gameCmsLoggingIp.label', default: 'GameCmsLoggingIp'), gameCmsLoggingIpInstance.id])}"
                redirect(action: "show", id: gameCmsLoggingIpInstance.id)
            }
            else {
                render(view: "edit", model: [gameCmsLoggingIpInstance: gameCmsLoggingIpInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsLoggingIp.label', default: 'GameCmsLoggingIp'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def gameCmsLoggingIpInstance = GameCmsLoggingIp.get(params.id)
        if (gameCmsLoggingIpInstance) {
            try {
                gameCmsLoggingIpInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'gameCmsLoggingIp.label', default: 'GameCmsLoggingIp'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'gameCmsLoggingIp.label', default: 'GameCmsLoggingIp'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsLoggingIp.label', default: 'GameCmsLoggingIp'), params.id])}"
            redirect(action: "list")
        }
    }
}
