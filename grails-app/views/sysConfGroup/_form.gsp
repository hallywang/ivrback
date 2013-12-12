<table>
    <tbody>

    <tr class="prop">
        <td valign="top" class="name">
            <label for="sysconfKey"><g:message code="sysConfGroup.groupName.label" default="Group Name" />*</label>
        </td>
        <td valign="top" class="value ${hasErrors(bean: sysConf, field: 'sysconfKey', 'errors')}">
            <g:textField name="groupName" id='groupName' maxlength="128" required="" value="${sysConfGroupInstance?.groupName}"
                         onchange="${remoteFunction(action:'isExistGroup', params:'\'gName=\'+this.value', update:'gNameMess')}"/>
            <input type="hidden" id="groupNum" name="groupNum" value="0"/>
        </td>
    </tr>

    <tr class="prop">
        <td valign="top" class="name">
            <label for="sysconfValue"><g:message code="sysConfGroup.remark.label" default="Remark" />*</label>
        </td>
        <td valign="top" class="value ${hasErrors(bean: sysConf, field: 'sysconfValue', 'errors')}">
            <g:textArea name="remark" cols="40" rows="5" maxlength="256" value="${sysConfGroupInstance?.remark}"/>
        </td>
    </tr>

    <div id="gNameMess"/>

    </tbody>
</table>