package com.emag.gamecms.controllers.ivr

import com.emag.gamecms.domain.ivr.IvrServiceInfo
import org.springframework.dao.DataIntegrityViolationException

class IvrServiceInfoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ivrServiceInfoInstanceList: IvrServiceInfo.list(params), ivrServiceInfoInstanceTotal: IvrServiceInfo.count()]
    }

    def create() {
        [ivrServiceInfoInstance: new IvrServiceInfo(params)]
    }

    def save() {
        def ivrServiceInfoInstance = new IvrServiceInfo(params)
        if (!ivrServiceInfoInstance.save(flush: true)) {
            render(view: "create", model: [ivrServiceInfoInstance: ivrServiceInfoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ivrServiceInfo.label', default: 'IvrServiceInfo'), ivrServiceInfoInstance.id])
        redirect(action: "show", id: ivrServiceInfoInstance.id)
    }

    def show(Long id) {
        def ivrServiceInfoInstance = IvrServiceInfo.get(id)
        if (!ivrServiceInfoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrServiceInfo.label', default: 'IvrServiceInfo'), id])
            redirect(action: "list")
            return
        }

        [ivrServiceInfoInstance: ivrServiceInfoInstance]
    }

    def edit(Long id) {
        def ivrServiceInfoInstance = IvrServiceInfo.get(id)
        if (!ivrServiceInfoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrServiceInfo.label', default: 'IvrServiceInfo'), id])
            redirect(action: "list")
            return
        }

        [ivrServiceInfoInstance: ivrServiceInfoInstance]
    }

    def update(Long id, Long version) {
        def ivrServiceInfoInstance = IvrServiceInfo.get(id)
        if (!ivrServiceInfoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrServiceInfo.label', default: 'IvrServiceInfo'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ivrServiceInfoInstance.version > version) {
                ivrServiceInfoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'ivrServiceInfo.label', default: 'IvrServiceInfo')] as Object[],
                          "Another user has updated this IvrServiceInfo while you were editing")
                render(view: "edit", model: [ivrServiceInfoInstance: ivrServiceInfoInstance])
                return
            }
        }

        ivrServiceInfoInstance.properties = params

        if (!ivrServiceInfoInstance.save(flush: true)) {
            render(view: "edit", model: [ivrServiceInfoInstance: ivrServiceInfoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ivrServiceInfo.label', default: 'IvrServiceInfo'), ivrServiceInfoInstance.id])
        redirect(action: "show", id: ivrServiceInfoInstance.id)
    }

    def delete(Long id) {
        def ivrServiceInfoInstance = IvrServiceInfo.get(id)
        if (!ivrServiceInfoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrServiceInfo.label', default: 'IvrServiceInfo'), id])
            redirect(action: "list")
            return
        }

        try {
            ivrServiceInfoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ivrServiceInfo.label', default: 'IvrServiceInfo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ivrServiceInfo.label', default: 'IvrServiceInfo'), id])
            redirect(action: "show", id: id)
        }
    }
}
