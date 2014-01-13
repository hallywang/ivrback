class ThirtyMinJob {
  static triggers = {
    simple name: 'ThirtyMinJobTrigger', startDelay: 1000 * 60 * 30, repeatInterval: 1000 * 60 * 30
    //应用启动后30分钟运行，30分钟运行一次
  }
  private static final String JOB_TYPE = 'thirtyMin'
  def runJobService
  def gameHallService
  def initPackageService
  def initPageService
  def initStatisticService

  def execute() {
    log.info "Thirty minute job start to run ..."
    long a = System.currentTimeMillis()

    runJobService.run(JOB_TYPE);

    /*//加载包内免费游戏下载限制次数
    try {
      initPackageService.initGamePool();
    } catch (Exception e) {
      log.error e
    }

    //SP模板信息
    try {
      initPageService.initSpTemplate();
    } catch (Exception e) {
      log.error e
    }

    //SP厂商类别游戏信息
    try {
      initStatisticService.initSpCategoryGames();
    } catch (Exception e) {
      log.error e
    }

    //加载大尺寸游戏下载次数
    try {
      gameHallService.initGameDownloadCount();
    } catch (Exception e) {
      log.error e
    }*/

    log.info "Thirty minute job end to run, use ${(System.currentTimeMillis() - a) / 1000} s"
  }
}
