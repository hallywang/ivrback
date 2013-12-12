<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'gameCmsIp.label', default: 'gameCmsIp')}" />
        <title>导入ip地址</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1>导入ip地址</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${gameCmsIpInstance}">
              <div class="errors">
                  <g:renderErrors bean="${gameCmsIpInstance}" as="list" />
              </div>
            </g:hasErrors>

            <g:form enctype="multipart/form-data" action="upload" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label>文件</label>
                                </td>
                                <td valign="top">
                                   <input type="file" name="myFile"/>  <br/>
                                   请上传txt文本文件，文件内容格式如： <br/>
                                   127.0.0.1,2,本地ip地址  <br/>
                                   122.96.62.123,2,测试机ip地址  <br/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="上传" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
