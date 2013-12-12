package com.emag.gamecms.controllers.sysconf

import com.emag.gamecms.domain.sysconf.SysConfGroup
import org.springframework.dao.DataIntegrityViolationException

class SysConfGroupController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list= {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def sysConfGroupInstanceList = SysConfGroup.createCriteria().list(params){
            if(params.groupName){
                or{
                    like("groupName", "%${params.groupName}%")
                    like("remark","%${params.groupName}%")
                }
            }
        }
        [sysConfGroupInstanceList: sysConfGroupInstanceList, sysConfGroupInstanceTotal: sysConfGroupInstanceList.totalCount]
    }

    def create() {
        [sysConfGroupInstance: new SysConfGroup(params)]
    }

    def save() {
        def sysConfGroupInstance = new SysConfGroup(params)
        if (!sysConfGroupInstance.save(flush: true)) {
            render(view: "create", model: [sysConfGroupInstance: sysConfGroupInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'sysConfGroup.label', default: 'SysConfGroup'), sysConfGroupInstance.id])
        redirect(action: "show", id: sysConfGroupInstance.id)
    }

    def show(Long id) {
        def sysConfGroupInstance = SysConfGroup.get(id)
        if (!sysConfGroupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sysConfGroup.label', default: 'SysConfGroup'), id])
            redirect(action: "list")
            return
        }

        [sysConfGroupInstance: sysConfGroupInstance]
    }

    def edit(Long id) {
        def sysConfGroupInstance = SysConfGroup.get(id)
        if (!sysConfGroupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sysConfGroup.label', default: 'SysConfGroup'), id])
            redirect(action: "list")
            return
        }

        [sysConfGroupInstance: sysConfGroupInstance]
    }

    def update(Long id, Long version) {
        def sysConfGroupInstance = SysConfGroup.get(id)
        sysConfGroupInstance.updateTime = new Date()
        if (!sysConfGroupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sysConfGroup.label', default: 'SysConfGroup'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (sysConfGroupInstance.version > version) {
                sysConfGroupInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'sysConfGroup.label', default: 'SysConfGroup')] as Object[],
                          "Another user has updated this SysConfGroup while you were editing")
                render(view: "edit", model: [sysConfGroupInstance: sysConfGroupInstance])
                return
            }
        }

        sysConfGroupInstance.properties = params

        if (!sysConfGroupInstance.save(flush: true)) {
            render(view: "edit", model: [sysConfGroupInstance: sysConfGroupInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'sysConfGroup.label', default: 'SysConfGroup'), sysConfGroupInstance.id])
        redirect(action: "show", id: sysConfGroupInstance.id)
    }

    def delete(Long id) {
        def sysConfGroupInstance = SysConfGroup.get(id)
        if (!sysConfGroupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sysConfGroup.label', default: 'SysConfGroup'), id])
            redirect(action: "list")
            return
        }

        try {
            sysConfGroupInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'sysConfGroup.label', default: 'SysConfGroup'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'sysConfGroup.label', default: 'SysConfGroup'), id])
            redirect(action: "show", id: id)
        }
    }

    /**
     * ajax判断数据库中是否存在相同名称的分组
     */
    def isExistGroup() {

        def groupName = params.gName     // 得到输入的groupName
        def groupNum = groupName ? SysConfGroup.countByGroupName(groupName) : 0   // 是否有相同名称的组存在，0：不存在，其他：存在

        render (template: "isExistGroup", model : ['groupNum' : groupNum])
    }
}
