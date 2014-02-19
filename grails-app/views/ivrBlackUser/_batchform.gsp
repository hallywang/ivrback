<table>
  <tbody>

    <tr class="prop">
    <td valign="top" class="name">
      <label for="scope">
        <g:message code="ivrBlackUser.scope.label" default="Scope"/>
        <span class="required-indicator">*</span>
      </label>
    </td>
    <td valign="top" class="value">
      <g:select name="scope" from="${com.emag.gamecms.domain.ivr.IvrServiceInfo.list()}"
                optionKey="serviceId"
                value="${ivrBlackUserInstance?.scope}" optionValue="serviceName"
                noSelection="${['0': '-- 全局 --']}"/>
    </td>
  </tr>

  <tr class="prop">
    <td valign="top" class="name">
      <label>
                号码

      </label>
    </td>
    <td valign="top" class="value">
      <g:textArea name="blackMobiles" />
    </td>
  </tr>

  <tr class="prop">
    <td valign="top" class="name">
      <label>
            文件

      </label>
    </td>
    <td valign="top" class="value">
      <input type="file" name="blackFile" />
    </td>
  </tr>



  </tbody>
</table>