/**
 * 用来记录后台的操作
 */
class ActionLogFilters {
    def logService

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                return true
            }
            after = {
                try {
                    /**
                     * 后台对记录的删除、编辑等操作有时候是通过点击按钮来完成
                     * 这时候参数中params.action可能是index，而且参数中还会多出一个名为 _action_delete(删除时为_action_delete，编辑时为_action_edit) 的参数
                     * 其实这个params里多出来的参数才是用户真正的操作
                     * 但在过滤器中，actionName也是真实的操作名，因此在params中加入realAction来记录用户真实操作的action名称
                     */
                    if (actionName) {
                        params.realAction = actionName
                    }
                    logService.logBackAction(request, session, params) //后台操作日志
                } catch (Exception ex) {
                    log.error('记录后台日志发生异常', ex)
                    return false
                }

                return true
            }
            afterView = {
            }
        }
    }


}
