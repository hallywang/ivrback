<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'gameCmsProtectUrl.label', default: 'GameCmsProtectUrl')}"/>
  <title><g:message code="default.edit.label" args="[entityName]"/></title>
  <g:javascript library="jquery"/>
  <script type="text/javascript">
    $(document).ready(function(){
              showTip(document.getElementById('byIp'), 'divtip');         
            }
    );

    function showTip(selObj,divObj) {
       if (selObj.value == '1') {
        document.getElementById(divObj).style.display = "";
       } else {
         document.getElementById(divObj).style.display = "none";
       }
    }
  </script>
</head>
<body>
<div class="nav">
  <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
  <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
  <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <g:hasErrors bean="${gameCmsProtectUrlInstance}">
    <div class="errors">
      <g:renderErrors bean="${gameCmsProtectUrlInstance}" as="list"/>
    </div>
  </g:hasErrors>
  <g:form method="post">
    <g:hiddenField name="id" value="${gameCmsProtectUrlInstance?.id}"/>
    <g:hiddenField name="version" value="${gameCmsProtectUrlInstance?.version}"/>
    <div class="dialog">
      <table>
        <tbody>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="url"><g:message code="gameCmsProtectUrl.url.label" default="Url"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsProtectUrlInstance, field: 'url', 'errors')}">
            <g:textField name="url" value="${gameCmsProtectUrlInstance?.url}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="startTime"><g:message code="gameCmsProtectUrl.startTime.label" default="Start Time"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsProtectUrlInstance, field: 'startTime', 'errors')}">
            <g:datePicker name="startTime" precision="day" value="${gameCmsProtectUrlInstance?.startTime}" noSelection="['': '']"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="endTime"><g:message code="gameCmsProtectUrl.endTime.label" default="End Time"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsProtectUrlInstance, field: 'endTime', 'errors')}">
            <g:datePicker name="endTime" precision="day" value="${gameCmsProtectUrlInstance?.endTime}" noSelection="['': '']"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="byIp">是否需要ip验证</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsProtectUrlInstance, field: 'byIp', 'errors')}">
            <g:select name="byIp" from="${[[okey:'0',ovalue:'否'],[okey:'1',ovalue:'是']]}" onchange="showTip(this,'divtip');"
                    optionKey="okey" optionValue="ovalue" value="${gameCmsProtectUrlInstance.byIp}"></g:select>
            <div id="divtip" style="color:#ff0000;display:none;">选择“是”后下方设置全部忽略</div>
          </td>
        </tr>

        %{--<tr style="display:none;" class="prop" id="iplist">--}%
          %{--<td valign="top" class="name">--}%
            %{--<label for="byIp">允许访问的ip</label>--}%
          %{--</td>--}%
          %{--<td valign="top" class="value ${hasErrors(bean: gameCmsProtectUrlInstance, field: 'ips', 'errors')}">--}%
            %{--<g:select name="ips" from='${GameCmsIp.findAll("from GameCmsIp as p where p.flag = 1 order by p.ip asc")}' multiple="yes" size="10"--}%
                    %{--optionKey="id" optionValue="ip" value="${gameCmsProtectUrlInstance.ips}"></g:select>--}%
              %{--<span style="color:red">选择验证IP后下方设置全部忽略</span> --}%
          %{--</td>--}%
        %{--</tr>--}%

        <tr class="prop">
          <td valign="top" class="name">
            <label for="allowReferer"><g:message code="gameCmsProtectUrl.allowReferer.label" default="Allow Referer"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsProtectUrlInstance, field: 'allowReferer', 'errors')}">
            <g:textField name="allowReferer" value="${gameCmsProtectUrlInstance?.allowReferer}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="blockReferer"><g:message code="gameCmsProtectUrl.blockReferer.label" default="Block Referer"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsProtectUrlInstance, field: 'blockReferer', 'errors')}">
            <g:textField name="blockReferer" value="${gameCmsProtectUrlInstance?.blockReferer}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="urlStatus"><g:message code="gameCmsProtectUrl.urlStatus.label" default="Url Status"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsProtectUrlInstance, field: 'urlStatus', 'errors')}">
            <g:select name="urlStatus" from="[0,1]" valueMessagePrefix="gameCmsProtectUrl.urlStatus"
                    value="${gameCmsProtectUrlInstance?.urlStatus}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="isAllowNull"><g:message code="gameCmsProtectUrl.isAllowNull.label" default="Is Allow Null"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsProtectUrlInstance, field: 'isAllowNull', 'errors')}">
            <g:checkBox name="isAllowNull" value="${gameCmsProtectUrlInstance?.isAllowNull}"/>
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="afterBlock">空referer跳转地址</label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsProtectUrlInstance, field: 'afterBlock', 'errors')}">
            <g:textField name="afterBlock" value="${gameCmsProtectUrlInstance?.afterBlock}"/>
          </td>
        </tr>

        </tbody>
      </table>
    </div>
    <div class="buttons">
      <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}"/></span>
      <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
    </div>
  </g:form>
</div>
<script type="text/javascript">
  showIpList();
</script>
</body>

</html>
