<table>
    <tbody>

    <tr class="prop">
        <td valign="top" class="name">
            <label for="sysconfKey"><g:message code="sysConf.sysconfKey.label" default="SysconfKey" />*</label>
        </td>
        <td valign="top" class="value ${hasErrors(bean: sysConf, field: 'sysconfKey', 'errors')}">
            <g:textField name="sysconfKey" id="key" maxlength="128" required="" value="${sysConfInstance?.sysconfKey}"
                         onchange="${remoteFunction(action:'isGKeyExist', params:'\'gkey=\'+$("#group").val()+","+this.value',update:'gkeyDiv')}"/>
            <input type="hidden" id="gkeyNum" name="gkeyNum" value="0">
        </td>
    </tr>

    <tr class="prop">
        <td valign="top" class="name">
            <label for="sysconfValue"><g:message code="sysConf.sysconfValue.label" default="SysconfValue" />*</label>
        </td>
        <td valign="top" class="value ${hasErrors(bean: sysConf, field: 'sysconfValue', 'errors')}">
            <g:textArea name="sysconfValue" id="value" cols="40" rows="5" maxlength="256" required="" value="${sysConfInstance?.sysconfValue}"/>
        </td>
    </tr>

    <tr class="prop">
        <td valign="top" class="name">
            <label for="status"><g:message code="sysConf.status.label" default="Status" />*</label>
        </td>
        <td >
            <g:radioGroup name="status" labels="['无效','有效']" values="[0, 1]" value="${sysConfInstance?.status}" >
                ${it.radio}&nbsp;<g:message code="${it.label}" />
            </g:radioGroup>
        </td>
    </tr>


    <tr class="prop">
        <td valign="top" class="name">
            <label for="remark"><g:message code="sysConf.remark.label" default="Remark" />*</label>
        </td>
        <td valign="top" class="value ${hasErrors(bean: sysConf, field: 'remark', 'errors')}">
            <g:textArea name="remark" cols="40" rows="5" maxlength="256" value="${sysConfInstance?.remark}"/>
        </td>
    </tr>

    <tr class="prop">
        <td valign="top" class="name">
            <label for="remark"><g:message code="sysConf.group.label" default="Group" />*</label>
        </td>
        <td valign="top" class="value ${hasErrors(bean: sysConf, field: 'remark', 'errors')}">
            <g:select id="group" name="group.id" from="${com.emag.gamecms.domain.sysconf.SysConfGroup .list()}" optionKey="id" required="" optionValue="${{it.groupName}}" value="${sysConfInstance?.group?.id}" class="many-to-one"
                      onchange="${remoteFunction(action:'isGKeyExist', params:'\'gkey=\'+this.value+","+$("#key").val()',update:'gkeyDiv')}"/>
        </td>
    </tr>




    <div id="gkeyDiv"/>

    </tbody>
</table>