package com.emag.gamecms.controllers

import com.emag.gamecms.domain.system.MagSysRequestmap

import org.springframework.util.StringUtils

/**
 * Requestmap controller.
 */
class MagSysRequestmapController {

  // the delete, save and update actions only accept POST requests
  def static allowedMethods = [save: 'POST', update: 'POST']

  def index = {
    redirect(action: 'list', params: params)
  }

  def list = {
    if (!params.max) {
      params.max = 10
    }
    def sysRequestMaps = MagSysRequestmap.createCriteria().list(params) {
      //添加查询条件
      if (params.name) {
        like('name', "%${params.name}%")
      }
      if (params.fatherId) {
        eq('father.id', Long.parseLong(params.fatherId))
      }
      if (params.realUrl) {
        like('realUrl', "%${params.realUrl}%")
      }
      if (params.configAttribute) {
        like('configAttribute', "%${params.configAttribute}%")
      }
    }
    [requestmapList: sysRequestMaps, requestmapTotalCount: sysRequestMaps.totalCount]
  }

  def show = {
    [requestmap: MagSysRequestmap.get(params.id)]
  }

  def delete = {
    def requestmap = MagSysRequestmap.get(params.id)
    if (!requestmap) {
      flash.message = "MagSysRequestmap not found with id ${params.id}"
      redirect(action: list)
      return
    }

    requestmap.delete()
    flash.message = "MagSysRequestmap ${params.id} deleted."
    redirect(action: list)
  }

  def edit = {
    def requestmap = MagSysRequestmap.get(params.id)
    if (!requestmap) {
      flash.message = "MagSysRequestmap not found with id ${params.id}"
      redirect(action: list)
      return
    }

    [requestmap: requestmap]
  }

  /**
   * Update action, called when an existing Requestmap is updated.
   */
  def update = {

    def requestmap = MagSysRequestmap.get(params.id)
    if (!requestmap) {
      flash.message = "MagSysRequestmap not found with id ${params.id}"
      redirect(action: edit, id: params.id)
      return
    }

    updateFromParams(requestmap)
    if (requestmap.save()) {
      flash.message = "MagSysRequestmap with id ${params.id} has been updated successfully"
      redirect(action: show, id: requestmap.id)
    } else {
      flash.message = "MagSysRequestmap with id ${params.id} has been updated failed"
      render(view: 'edit', model: [requestmap: requestmap])
    }
  }

  def create = {
    def requestmap = new MagSysRequestmap()
    requestmap.properties = params
    [requestmap: requestmap]
  }

  /**
   * Save action, called when a new Requestmap is created.
   */
  def save = {

    def requestmap = new MagSysRequestmap()
    updateFromParams(requestmap)
    if (requestmap.save()) {
      flash.message = "MagSysRequestmap created successfully"
      redirect(action: show, id: requestmap.id)
    } else {
      flash.message = "MagSysRequestmap created failed"
      render(view: 'create', model: [requestmap: requestmap])
    }
  }

  private void updateFromParams(requestmap) {
    requestmap.properties = params
    //get user's enter field "configAttribute" from the params.
    String[] configAttrs = StringUtils.commaDelimitedListToStringArray(params.configAttribute)
    //Format the configAttributes to meet Spring Security's requirement.
    String formattedConfigAttrs = ''
    String delimiter = ''
    for (String configAttribute in configAttrs) {
      if (configAttribute.trim().length() > 0) {
        formattedConfigAttrs += delimiter + 'ROLE_' + configAttribute.toUpperCase()
        delimiter = ','
      }
    }
    requestmap.configAttribute = formattedConfigAttrs
  }
}
