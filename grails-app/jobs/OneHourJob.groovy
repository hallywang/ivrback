class OneHourJob {
  static triggers = {
    simple name: 'OneHourJobCronTrigger', startDelay: 1000 * 60 * 60, repeatInterval: 1000 * 60 * 60
    //启动后一小时更新，每小时更新一次
  }

  private static final String JOB_TYPE = 'oneHour'
  def runJobService

  def execute() {
    log.info "One hour job start to run ..."
    long a = System.currentTimeMillis()

    runJobService.run(JOB_TYPE);

    /*try {
      initPackageService.initPkgInfos() //加载包信息和包价格
    } catch (Exception e) {
      log.error e
    }

    try {
      initPackageService.initPkgConsumeData();//加载g+游戏包的上周下载量（激活量）数据
    } catch (Exception e) {
      log.error e
    }

    // 游戏包分省暂停轮询服务
    try {
      initPackageService.initProvincePauseGPkg();
    } catch (Exception ex) {
      log.error ex
    }

    try {
      initUaService.initGroupDevice() //加载机型组和机型
    } catch (Exception e) {
      log.error e
    }

    try {
      initConfigService.initAdapterRuleArea() //mano中的地域适配
    } catch (Exception e) {
      log.error e
    }

    try {
      misService.initServiceFC() //加载业务分成比例
    } catch (Exception e) {
      log.error e
    }

    try {
      initConfigService.initYingxiaoAct() //加载营销活动信息
    } catch (Exception e) {
      log.error e
    }

    //统计抽样下载量到中间表
    try {
      initStatisticService.initGameDownInfo();
    } catch (Exception e) {
      log.error e
    }

    // 加载游戏查询配置信息
    try {
      initConfigService.initGameSearchConf();
    } catch (Exception ex) {
      log.error ex
    }

    try {
      initSecurityService.initProtectUrls()  //加载保护url地址
    } catch (Exception e) {
      log.error e
    }

    try {
      initSecurityService.initGameCmsReferer() //加载referer白名单
    } catch (Exception e) {
      log.error e
    }

    try {
      initSecurityService.initWapGateWayIps() //加载wap网关地址
    } catch (Exception e) {
      log.error e
    }

    try {
      initPageService.initTemplate() //load template to cache
    } catch (Exception e) {
      log.error e
    }

    try {
      initConfigService.initGameCmsRolls()//新轮询加载
    } catch (Exception e) {
      log.error e
    }

    try {
      initConfigService.initShortUrl()//加载短地址
    } catch (Exception e) {
      log.error e
    }

    try {
      initConfigService.initNodeGames() // 加载栏目中游戏，角色，体育，冒险
    } catch (Exception e) {
      log.error e
    }

    try {
      initSecurityService.initProtectService() // 初始化 ProtectService referer保护的业务列表
    } catch (Exception e) {
      log.error e
    }

    try {
      initGameService.deleteNotExistItem() // 删除不存在的游戏所在的关联
    } catch (Exception e) {
      log.error e
    }

    try {
      initConfigService.initDemandGUrl() //加载点播地址动态key,@editor:RongWei,@2011-02-11
    } catch (Exception e) {
      log.error e
    }

    try {
      initConfigService.initUrlAdapterGotoConf() //加载URL跳转配置,@editor:RongWei,@2011-05-18
    } catch (Exception e) {
      log.error e
    }*/

    log.info "One hour job end to run, use ${(System.currentTimeMillis() - a) / 1000} s"
  }
}
