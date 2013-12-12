package com.emag.gamecms.controllers.sysconf

import com.emag.gamecms.domain.sysconf.SysConf
import com.emag.gamecms.domain.sysconf.SysConfGroup
import org.springframework.dao.DataIntegrityViolationException

class SysConfController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list= {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def  sysConfInstanceList = SysConf.createCriteria().list(params){
            if(params.status){
                eq("status",params.int("status"))
            }
            if (params.sysconfKey){
                like("sysconfKey","%${params.sysconfKey}%")
            }
            if (params.group && params.group.id){
                eq("group.id",Long.parseLong(params.group.id))
            }
        }
        [sysConfInstanceList: sysConfInstanceList, sysConfInstanceTotal: sysConfInstanceList.totalCount]
    }

    def create() {
        [sysConfInstance: new SysConf(params)]
    }

    def save() {
        def sysConfInstance = new SysConf(params)
        if (SysConf.findBySysconfKeyAndGroup(sysConfInstance?.sysconfKey,sysConfInstance?.group)){
            sysConfInstance.errors.rejectValue("sysconfKey",message(code: 'sysConf.sysconfKey.same.inGroup',default: 'sysconfKey already exists in the same group'))
            render(view: "create", model: [sysConfInstance: sysConfInstance])
            return
        }
        if (!sysConfInstance.save(flush: true)) {
            render(view: "create", model: [sysConfInstance: sysConfInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'sysConf.label', default: 'SysConf'),sysConfInstance.id])
        redirect(action: "show", id: sysConfInstance.id)
    }

    def show(Long id) {
        def sysConfInstance = SysConf.get(id)
        if (!sysConfInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sysConf.label', default: 'SysConf'), id])
            redirect(action: "list")
            return
        }

        [sysConfInstance: sysConfInstance]
    }

    def edit(Long id) {
        def sysConfInstance = SysConf.get(id)
        if (!sysConfInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sysConf.label', default: 'SysConf'), id])
            redirect(action: "list")
            return
        }

        [sysConfInstance: sysConfInstance]
    }

    def update(Long id, Long version) {
        def sysConfInstance = SysConf.get(id)
        if (!sysConfInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sysConf.label', default: 'SysConf'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (sysConfInstance.version > version) {
                sysConfInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'sysConf.label', default: 'SysConf')] as Object[],
                        "Another user has updated this SysConf while you were editing")
                render(view: "edit", model: [sysConfInstance: sysConfInstance])
                return
            }
        }

        def tmpsysConf= SysConf.findBySysconfKeyAndGroup(params.sysconfKey,SysConfGroup.get(Long.parseLong(params.group.id)))
        if (tmpsysConf?.id && tmpsysConf.id != sysConfInstance.id){
            sysConfInstance.errors.rejectValue("sysconfKey",message(code: 'sysConf.sysconfKey.same.inGroup',default: 'sysconfKey already exists in the same group'))
            render(view: "edit", model: [sysConfInstance: sysConfInstance])
            return
        }
        sysConfInstance.properties = params
        sysConfInstance.updateTime = new Date()

        if (!sysConfInstance.save(flush: true)) {
            render(view: "edit", model: [sysConfInstance:sysConfInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'sysConf.label', default: 'SysConf'), sysConfInstance.id])
        redirect(action: "show", id: sysConfInstance.id)
    }

    def delete(Long id) {
        def sysConfInstance = SysConf.get(id)
        if (!sysConfInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sysConf.label', default: 'SysConf'), id])
            redirect(action: "list")
            return
        }

        try {
            sysConfInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'sysConf.label', default: 'SysConf'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'sysConf.label', default: 'SysConf'), id])
            redirect(action: "show", id: id)
        }
    }
    def validateKeyGroup(){
        def result
        def keyName = params.keyGroup.split(',')[0]
        def groupId = params.keyGroup.split(',')[1]
        if(keyName && groupId){
            if (SysConf.findBySysconfKeyAndGroup(keyName,SysConfGroup.get(Long.parseLong(groupId)))){
                result =1
            }else{
                result= 2
            }
        } else{
            result =3
        }
        render (template: "validateKeyGroup", model : ['keyGroup' : result])
    }

    /**
     * 判断相同组下是否有相同的配置名称
     */
    def isGKeyExist() {
        def gkeyNum = 0     // 相同组，相同key的数据条数
        def gkey = params.gkey    // 组合键值名称 groupId,key

        if (gkey) {
            // 取到groupId和key
            def gkeyStrs = gkey.split(',')
            if (gkeyStrs.length == 2) {
                // 如果groupId和key都存在的时候才判断是，否则说明数据还没有填写完，不做判断
                gkeyNum = SysConf.countByGroupAndSysconfKey(SysConfGroup.findById(Long.parseLong(gkeyStrs[0])), gkeyStrs[1])
            }
        }

        render (template: 'groupkey', model: ['gkeyNum': gkeyNum])
    }
}
