/**
 * Created by IntelliJ IDEA.
 * User: cyg
 * Date: 12-3-7
 * Time: 下午3:45
 * 每月2号凌晨零点运行
 */
class OneMonthJob {

  static triggers = {
    // 秒、分、时、日、月、周
    cron name: 'oneMonthJobTrigger', cronExpression: "0 0 0 2 * ?"
  }

  private static final String JOB_TYPE = 'oneMonth'
  def runJobService

  def execute() {
    log.info "oneMonth job start to run ..."
    long a = System.currentTimeMillis()

    runJobService.run(JOB_TYPE);

    log.info "oneMonth end to run, use ${(System.currentTimeMillis() - a) / 1000} s"
  }
}
