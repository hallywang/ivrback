<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:set var="entityName" value="${message(code: 'gameCmsProtectUrl.label', default: 'GameCmsProtectUrl')}"/>
  <title><g:message code="default.create.label" args="[entityName]"/></title>
  <g:javascript library="jquery"/>
  <script type="text/javascript">
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
</div>
<div class="body">
  <h1><g:message code="default.create.label" args="[entityName]"/></h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <g:hasErrors bean="${gameCmsProtectUrlInstance}">
    <div class="errors">
      <g:renderErrors bean="${gameCmsProtectUrlInstance}" as="list"/>
    </div>
  </g:hasErrors>
  <g:form action="save" method="post">
    <div class="dialog">
      <table>
        <tbody>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="url"><g:message code="gameCmsProtectUrl.url.label" default="Url"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsProtectUrlInstance, field: 'url', 'errors')}">
            <g:textField name="url" value="${gameCmsProtectUrlInstance?.url}"/>

            需要保护模板url，格式：专题名称/模板名称,如：game3/index
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="startTime"><g:message code="gameCmsProtectUrl.startTime.label" default="Start Time"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsProtectUrlInstance, field: 'startTime', 'errors')}">
            <g:datePicker name="startTime" precision="day" value="${gameCmsProtectUrlInstance?.startTime}" noSelection="['': '']"/>
            生效开始时间
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="endTime"><g:message code="gameCmsProtectUrl.endTime.label" default="End Time"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsProtectUrlInstance, field: 'endTime', 'errors')}">
            <g:datePicker name="endTime" precision="day" value="${gameCmsProtectUrlInstance?.endTime}" noSelection="['': '']"/>
            生效结束时间
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
            <g:textArea name="allowReferer" style="height:100px" value="${gameCmsProtectUrlInstance?.allowReferer}"/><br/>
           
            <br/>
            允许访问的网址半角逗号分隔，如：http://www.g.cn,http://www.baidu.com     ；如果有此配置将忽略禁止url，以此为准
          </td>
        </tr>

        <tr class="prop">
          <td valign="top" class="name">
            <label for="blockReferer"><g:message code="gameCmsProtectUrl.blockReferer.label" default="Block Referer"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsProtectUrlInstance, field: 'blockReferer', 'errors')}">
            <g:textArea name="blockReferer" style="height:100px" value="${gameCmsProtectUrlInstance?.blockReferer}"/>
            <br/>禁止访问的网址半角逗号分隔，如：http://www.g.cn,http://www.baidu.com  ；如果允许网址配置，此项忽略
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
            是否允许空网址referer,超过90%的referer取不到，建议允许
          </td>
        </tr>

       <tr class="prop">
          <td valign="top" class="name">
            <label for="afterBlock"><g:message code="gameCmsProtectUrl.afterBlock.label" default="After Block"/></label>
          </td>
          <td valign="top" class="value ${hasErrors(bean: gameCmsProtectUrlInstance, field: 'afterBlock', 'errors')}">
            <g:textField name="afterBlock" value="${gameCmsProtectUrlInstance?.afterBlock}"/>
            空referer跳转地址，如http://gamepie.g188.net
          </td>
        </tr>

        </tbody>
      </table>
    </div>
    <div class="buttons">
      <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
    </div>
  </g:form>
</div>
</body>
</html>
