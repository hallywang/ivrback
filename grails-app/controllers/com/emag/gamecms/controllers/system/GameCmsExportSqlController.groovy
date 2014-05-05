package com.emag.gamecms.controllers.system

import com.emag.gamecms.domain.system.GameCmsExportSql
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowCallbackHandler

import javax.sql.DataSource

class GameCmsExportSqlController {
  DataSource dataSource
  def cacheService
  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  /*def beforeInterceptor = {
    response.sendError(405)
    return false
  }*/

  def index = {
    redirect(action: "list", params: params)
  }

  def list = {
    params.max = Math.min(params.max ? params.int('max') : 10, 100)
    [gameCmsExportSqlInstanceList: GameCmsExportSql.list(params), gameCmsExportSqlInstanceTotal: GameCmsExportSql.count()]
  }

  def create = {
    def gameCmsExportSqlInstance = new GameCmsExportSql()
    gameCmsExportSqlInstance.properties = params
    return [gameCmsExportSqlInstance: gameCmsExportSqlInstance]
  }

  def save = {
    def gameCmsExportSqlInstance = new GameCmsExportSql(params)
    if (gameCmsExportSqlInstance.save(flush: true)) {
      flash.message = "${message(code: 'default.created.message', args: [message(code: 'gameCmsExportSql.label', default: 'GameCmsExportSql'), gameCmsExportSqlInstance.id])}"
      redirect(action: "show", id: gameCmsExportSqlInstance.id)
    }
    else {
      render(view: "create", model: [gameCmsExportSqlInstance: gameCmsExportSqlInstance])
    }
  }

  def show = {
    def gameCmsExportSqlInstance = GameCmsExportSql.get(params.id)
    if (!gameCmsExportSqlInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsExportSql.label', default: 'GameCmsExportSql'), params.id])}"
      redirect(action: "list")
    }
    else {
      [gameCmsExportSqlInstance: gameCmsExportSqlInstance]
    }
  }

  def edit = {
    def gameCmsExportSqlInstance = GameCmsExportSql.get(params.id)
    if (!gameCmsExportSqlInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsExportSql.label', default: 'GameCmsExportSql'), params.id])}"
      redirect(action: "list")
    }
    else {
      return [gameCmsExportSqlInstance: gameCmsExportSqlInstance]
    }
  }

  def update = {
    def gameCmsExportSqlInstance = GameCmsExportSql.get(params.id)
    if (gameCmsExportSqlInstance) {
      if (params.version) {
        def version = params.version.toLong()
        if (gameCmsExportSqlInstance.version > version) {

          gameCmsExportSqlInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'gameCmsExportSql.label', default: 'GameCmsExportSql')] as Object[], "Another user has updated this GameCmsExportSql while you were editing")
          render(view: "edit", model: [gameCmsExportSqlInstance: gameCmsExportSqlInstance])
          return
        }
      }
      gameCmsExportSqlInstance.properties = params
      if (!gameCmsExportSqlInstance.hasErrors() && gameCmsExportSqlInstance.save(flush: true)) {
        flash.message = "${message(code: 'default.updated.message', args: [message(code: 'gameCmsExportSql.label', default: 'GameCmsExportSql'), gameCmsExportSqlInstance.id])}"
        redirect(action: "show", id: gameCmsExportSqlInstance.id)
      }
      else {
        render(view: "edit", model: [gameCmsExportSqlInstance: gameCmsExportSqlInstance])
      }
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsExportSql.label', default: 'GameCmsExportSql'), params.id])}"
      redirect(action: "list")
    }
  }

  def delete = {
    def gameCmsExportSqlInstance = GameCmsExportSql.get(params.id)
    if (gameCmsExportSqlInstance) {
      try {
        gameCmsExportSqlInstance.delete(flush: true)
        flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'gameCmsExportSql.label', default: 'GameCmsExportSql'), params.id])}"
        redirect(action: "list")
      }
      catch (org.springframework.dao.DataIntegrityViolationException e) {
        flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'gameCmsExportSql.label', default: 'GameCmsExportSql'), params.id])}"
        redirect(action: "show", id: params.id)
      }
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsExportSql.label', default: 'GameCmsExportSql'), params.id])}"
      redirect(action: "list")
    }
  }
  // 导出数据
  def exportSql = {   //todo
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);


    def exportSql = GameCmsExportSql.get(params.id)


    String sql = ""
    if (exportSql) {
      sql = exportSql.content

      sql = sql.replaceAll("\\{1\\}", params.paramA?:'').replaceAll("\\{2\\}", params.paramB?:'')
              .replaceAll("\\{3\\}", params.paramC?:'').replaceAll("\\{4\\}", params.paramD?:'')
              .replaceAll("\\{5\\}", params.paramE?:'').replaceAll("\\{6\\}", params.paramF?:'')
              .replaceAll("\\{7\\}", params.paramG?:'').replaceAll("\\{8\\}", params.paramH?:'')
              .replaceAll("\\{9\\}", params.paramI?:'').replaceAll("\\{10\\}", params.paramJ?:'')

      log.info "导出 export sql = $sql"

    }

    if (!sql.toLowerCase().startsWith("select")) {  //不是select开头的直接忽略
      flash.message = "不是查询语句，请编辑"
      redirect(action: "show", id: params.id)
      return true
    }

    def filenamedisplay = "export.csv"
    response.addHeader("Content-Disposition", "attachment;filename=" + filenamedisplay);
    response.setCharacterEncoding "GBK"
    response.setContentType("application/csv;charset=GBK")
    PrintWriter out = new PrintWriter(new OutputStreamWriter(
            response.getOutputStream(), "GBK"));
    def head = new StringBuffer()
    def rowCallback = {java.sql.ResultSet rs ->
      def ress = new StringBuffer()
      def metas = rs.getMetaData()
      def colcount = metas.getColumnCount()

      if (!head) {
        for (i in 1..colcount) {
          head << "\""
          head << metas.getColumnLabel(i)
          head << "\""
          head << ","
        }
        out.println(head.substring(0, head.lastIndexOf(",")));
      }
      for (i in 1..colcount) {
        ress << "\""
        ress << rs.getString(metas.getColumnLabel(i))
        ress << "\""
        ress << ","
      }

      out.println(ress.substring(0, ress.lastIndexOf(",")));
    } as RowCallbackHandler;

    jdbcTemplate.query(sql, rowCallback)
    out.flush();
    out.close();


  }

  //执行sql  todo
  def excuteSql = {
    def exportSql = GameCmsExportSql.get(params.id)
    String sql = ""
    if (exportSql) {
      sql = exportSql.content
    }

    if (sql) {
      JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

      sql.split(";").each {
        if (it?.trim()) {
          //println "it=$it"
          jdbcTemplate.execute(it?.trim())
        }
      }
    }
    flash.message = "SQL执行完毕"
    redirect(action: "show", id: params.id)
    return true

  }


}
