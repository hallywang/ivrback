package com.emag.gamecms.controllers.actionlog

import com.emag.gamecms.domain.actionlog.GameCmsActionLog
import com.vivame.util.TimeUtil

class GameCmsActionLogController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index = {
    redirect(action: "list", params: params)
  }

  def list = {
    params.max = Math.min(params.max ? params.int('max') : 10, 100)
    if (!params.sort) {
      params.sort = 'id'
      params.order = 'desc'
    }

    def list = GameCmsActionLog.createCriteria().list(params) {
      if (params.userName) {
        eq('userName', params.userName)
      }

      if (params.controllName) {
        eq('controllName', params.controllName)
      }

      if (params.actionName) {
        eq('actionName', params.actionName)
      }

      if (params.actionId) {
        eq('actionId', params.actionId)
      }

      if (params.startTime) {
        ge("actionTime", TimeUtil.parseDateByString(params.startTime, "yyyy-MM-dd", 0))
      }

      if (params.endTime) {
        le("actionTime", TimeUtil.parseDateByString(params.endTime, "yyyy-MM-dd", 1))
      }
    }


    [gameCmsActionLogInstanceList: list, gameCmsActionLogInstanceTotal: list?.totalCount]
  }
}
