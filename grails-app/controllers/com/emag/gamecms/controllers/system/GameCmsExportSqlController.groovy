package com.emag.gamecms.controllers.system

import com.emag.ObjectCache
import com.emag.gamecms.domain.system.GameCmsExportSql
import com.vivame.util.TimeUtil
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

      sql = sql.replaceAll("\\{1\\}", params.paramA).replaceAll("\\{2\\}", params.paramB).replaceAll("\\{3\\}", params.paramC).replaceAll("\\{4\\}", params.paramD).replaceAll("\\{5\\}", params.paramE).replaceAll("\\{6\\}", params.paramF).replaceAll("\\{7\\}", params.paramG).replaceAll("\\{8\\}", params.paramH).replaceAll("\\{9\\}", params.paramI).replaceAll("\\{10\\}", params.paramJ)

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
          head << metas.getColumnName(i)
          head << "\""
          head << ","
        }
        out.println(head.substring(0, head.lastIndexOf(",")));
      }
      for (i in 1..colcount) {
        ress << "\""
        ress << rs.getString(metas.getColumnName(i))
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

  /**
   * 日报
   */
  def dateReport = {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    
    def yxjldown = []  //游戏精灵日下载  go/downjl
    def expodown = [] //  expo 日下载   go/downexpo
    def yxjluse = []   //go/game
    def expouse = []   //go/expo

    def yxjldownM = []    //游戏精灵月下载  go/downjl
    def expodownM = [] //  expo 月下载   go/downexpo
    def yxjluseM = []   //go/game
    def expouseM = []   //go/expo

     def yxjldownA = []    //游戏精灵总下载  go/downjl
    def expodownA = [] //  expo 总下载   go/downexpo
    def yxjluseA = []   //go/game
    def expouseA = []   //go/expo

    //游戏精灵日下载  go/downjl
    yxjldown = getQueryList(getDateQuerySql("go/downjl"), jdbcTemplate)

    //expo 日下载   go/downexpo
    expodown = getQueryList(getDateQuerySql("go/downexpo"), jdbcTemplate)

    //使用 日  go/game
    yxjluse = getQueryList(getDateQuerySql("go/game"), jdbcTemplate)

    //go/expo
    expouse = getQueryList(getDateQuerySql("go/expo"), jdbcTemplate)

    yxjldownM = getQueryList(getMonthQuerySql("go/downjl",TimeUtil.getDate(new Date(),"yyyyMM")+"01"), jdbcTemplate)
    expodownM = getQueryList(getMonthQuerySql("go/downexpo",TimeUtil.getDate(new Date(),"yyyyMM")+"01"), jdbcTemplate)
    yxjluseM = getQueryList(getMonthQuerySql("go/game",TimeUtil.getDate(new Date(),"yyyyMM")+"01"), jdbcTemplate)
    expouseM = getQueryList(getMonthQuerySql("go/expo",TimeUtil.getDate(new Date(),"yyyyMM")+"01"), jdbcTemplate)

      yxjldownA = getQueryList(getMonthQuerySql("go/downjl","20100401"), jdbcTemplate)
    expodownA = getQueryList(getMonthQuerySql("go/downexpo","20100501"), jdbcTemplate)
    yxjluseA = getQueryList(getMonthQuerySql("go/game","20100401"), jdbcTemplate)
    expouseA = getQueryList(getMonthQuerySql("go/expo","20100501"), jdbcTemplate)



    [yxjldown: yxjldown, expodown: expodown, yxjluse: yxjluse, expouse: expouse,
            yxjldownM: yxjldownM, yxjluseM: yxjluseM, expodownM: expodownM, expouseM: expouseM,
    yxjldownA:yxjldownA,expodownA:expodownA,yxjluseA:yxjluseA,expouseA:expouseA]
  }
  /**
   * 周报
   */
  def weekReport = {
    def yxjldown = []  //游戏精灵日下载  go/downjl
    def expodown = [] //  expo 日下载   go/downexpo
    def yxjluse = []   //go/game
    def expouse = []   //go/expo

    def yxjldownM = []    //游戏精灵月下载  go/downjl
    def expodownM = [] //  expo 月下载   go/downexpo
    def yxjluseM = []   //go/game
    def expouseM = []   //go/expo
  }

  /**
   *  得到按月统计sql
   * @param url
   * @return sql
   */
  private String getMonthQuerySql(String url,String startTime) {



    StringBuilder buff = new StringBuilder();
    buff.append("select b.prov_name, a.cnt");
    buff.append(" from (select province_id, count(distinct msisdn) cnt");
    buff.append("	  from game_cms_visit_log");
    buff.append("	  where substring(optime, 1, 8) >= '$startTime'");
    buff.append("	  and substring(optime, 1, 8) <= '${TimeUtil.getDate(TimeUtil.getDateBefore(-1), 'yyyyMMdd')}'");
    buff.append("	  and request_url like '%$url'");
    buff.append("	  group by province_id) a");
    buff.append(" left join province b on a.province_id = b.prov_id");
    buff.append(" where b.prov_name is not null");
    buff.append(" order by b.prov_name");

    return buff.toString()


  }

  /**
   * 得到按日统计的sql
   * @param url
   * @return String sql
   */
  private String getDateQuerySql(String url) {
    StringBuilder buff = new StringBuilder();

    // 游戏精灵日下载  go/downjl
    buff.append("select b.prov_name, a.cnt");
    buff.append(" from (select province_id, count(distinct msisdn) cnt");
    buff.append("	  from game_cms_visit_log");
    buff.append("	  where substring(optime, 1, 8) = '${TimeUtil.getDate(TimeUtil.getDateBefore(-1), 'yyyyMMdd')}'");
    buff.append("	  and (request_url like '%${url}')");
    buff.append("	  group by province_id) a");
    buff.append(" left join province b on a.province_id = b.prov_id");
    buff.append(" where b.prov_name is not null");
    buff.append(" order by b.prov_name");

    return buff.toString()
  }

  /**
   * 得到list，根据sql
   * @param sql
   * @param list
   * @return list
   */
  private List getQueryList(String sql, JdbcTemplate jdbcTemplate) {

    ObjectCache dateReport = cacheService.getCache("dateReport")


    def list = []

    if (!dateReport.get(sql)) {
      log.info "noreportcache,load from db"
      // 游戏精灵日下载  go/downjl
      def rowCallback = {java.sql.ResultSet rs ->

        def metas = rs.getMetaData()
        def colcount = metas.getColumnCount()
        String[] col = new String[colcount]

        for (i in 1..colcount) {
          col[i - 1] = rs.getString(metas.getColumnName(i))
        }

        list.add(col)
      } as RowCallbackHandler;

      jdbcTemplate.query(sql, rowCallback)

      dateReport.put(sql, list)

    } else {
      list = dateReport.get(sql)
    }
    log.info "size=${list.size()},sql=$sql"

    return list
  }

}
