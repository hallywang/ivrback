/**
 * 启动之后四小时执行，之后每次四小时支持定时任务
 * User: guoqiang
 * Date: 12-11-7
 * Time: 下午2:55
 * To change this template use File | Settings | File Templates.
 */
class FourHourJob {
  static triggers = {
    simple name: 'FourHourJobTrigger', startDelay: 1000 * 60 * 60 * 4, repeatInterval: (1000 * 60 * 60 * 4)
    //应用启动后4小时运行一次，之后每4小时运行一次
  }

  private static final String JOB_TYPE = 'fourHour'
  def runJobService

  def execute() {
    long a = System.currentTimeMillis()
    log.info "Four Hour job start to run ..."

    runJobService.run(JOB_TYPE);

    log.info "Four Hour job end to run, use ${(System.currentTimeMillis() - a) / 1000} s"
  }
}
