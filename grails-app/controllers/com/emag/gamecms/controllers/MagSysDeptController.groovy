package com.emag.gamecms.controllers

import com.emag.gamecms.domain.system.MagSysDept

/**
 * Dept Controller.
 */
class MagSysDeptController {

  // the delete, save and update actions only accept POST requests
  def static allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']
  def index = {
    redirect(action: list, params: params)
  }

  def list = {
    if (!params.max) {
      params.max = 10
    }
    def  depts =MagSysDept.createCriteria().list(params){
        //添加查询条件
       if(params.deptName){
           like('deptName',"%${params.deptName}%")
       }
        if(params.deptCode){
            like('deptCode',"%${params.deptCode}%")
        }
        if(params.description){
            like('description',"%${params.description}%")
        }
      }
    [deptList: depts,deptsTotalCount:depts?.totalCount]
  }

  def show = {
    [dept: MagSysDept.get(params.id)]
  }

  def create = {
    def dept = new MagSysDept()
    dept.properties = params
    [dept: dept]
  }

  /**
   * Dept save action.
   */
  def save = {
    def dept = new MagSysDept()
    dept.properties = params
    if (dept.save()) {
      redirect(action: show, id: dept.id)
    }
    else {
      render(view: 'create')
    }
  }

  /**
   * Dept delete action. Before removing an existing dept
   */
  def delete = {
    def dept = MagSysDept.get(params.id)
    if (dept) {
      dept.delete()
      flash.message = "MagSysDept ${params.id} deleted."
    }
    else {
      flash.message = "MagSysDept not found with id ${params.id}"
    }
    redirect(action: list)
  }

  def edit = {
    def dept = MagSysDept.get(params.id)
    if (!dept) {
      flash.message = "MagSysDept not found with id ${params.id}"
      redirect(action: list)
      return
    }
    [dept: dept]
  }

  /**
	 * Dept update action.
	 */
	def update = {

		def dept = MagSysDept.get(params.id)
		if (!dept) {
			flash.message = "MagSysDept not found with id ${params.id}"
			redirect(action: edit, id: params.id)
			return
		}

		dept.properties = params
		if (dept.save()) {
			redirect(action: show, id: dept.id)
		}
		else {
			render(view: 'edit', model: [dept: dept])
		}
	}

}
