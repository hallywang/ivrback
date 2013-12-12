<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title>模板详情</title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><g:link class="list" action="list">模板列表</g:link></span>
  <span class="menuButton"><g:link class="create" action="create">新建模板</g:link></span>
</div>
<div class="body">
  <h1>模板修改记录详情</h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <div class="dialog">
    <table>
      <tbody>

      <tr class="prop">
        <td valign="top" class="name">编号:</td>

        <td valign="top" class="value">${fieldValue(bean: tvWapPageLog, field: 'id')}</td>

      </tr>
       <tr class="prop">
        <td valign="top" class="name">修改人:</td>

        <td valign="top" class="value">${fieldValue(bean: tvWapPageLog, field: 'userName')}</td>

      </tr>
       <tr class="prop">
        <td valign="top" class="name">修改时间:</td>

        <td valign="top" class="value">${fieldValue(bean: tvWapPageLog, field: 'updateTime')}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name">名称:</td>

        <td valign="top" class="value">${fieldValue(bean: tvWapPageLog, field: 'name')}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name">标题:</td>

        <td valign="top" class="value">${fieldValue(bean: tvWapPageLog, field: 'description')}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name">描述:</td>

        <td valign="top" class="value">${fieldValue(bean: tvWapPageLog, field: 'title')}</td>

      </tr>

       <tr class="prop">
        <td valign="top" class="name">是否发布:</td>

        <td valign="top" class="value">
          <g:if test="${tvWapPageLog.published == '1'}">
             已发布
          </g:if>
          <g:else>
             未发布
          </g:else>
        </td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name">链接:</td>

        <td valign="top" class="value">${fieldValue(bean: tvWapPageLog, field: 'link')}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name">内容:</td>

        <td valign="top" class="value">
          <g:textArea style="height:300;width :600" id="content" name="content" value="${tvWapPageLog.content}" rows="10" cols="80"/>

        </td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name">内容类型:</td>

        <td valign="top" class="value">${fieldValue(bean: tvWapPageLog, field: 'contentType')}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name">题头:</td>

        <td valign="top" class="value">
          <g:textArea style="height:300;width :600" id="head" name="head" value="${tvWapPageLog.head}" rows="10" cols="80"/>

        </td>

      </tr>


      </tbody>
    </table>
  </div>
    <div class="buttons">
    <g:form action="recoverLog">
      <input type="hidden" name="id" value="${tvWapPageLog?.id}"/>
      <span class="button"><g:actionSubmit  onclick="return confirm('确定要恢复模板到该版本吗?');" value="恢复到该版本" action="recoverLog"/></span>
    </g:form>
  </div>

</div>
</body>
</html>
