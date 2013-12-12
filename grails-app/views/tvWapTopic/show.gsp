<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title>专题信息</title>
  <g:javascript library="jquery"/>
  <jq:plugin name="dialog"/>
  <jq:plugin name="fieldselection"/>
  <script type="text/javascript" src="${request.contextPath}/js/wappage.js"></script>
  <script type="text/javascript">
    $().ready(function() {
      $('#allSelect').click(function() {
        $('.allSelects').attr('checked', $(this).attr('checked'));
      })
    });
    function createPages() {
      var checkedStr = "";

      $(".allSelects").each(function() {
        if ($(this).attr("checked")) {
          state = true;
          checkedStr += ',' + this.value;
        }
      });

      if (checkedStr.length <= 0) {
        alert("${message(code: 'game.cms.noSelectDate.label', default: 'No date')}");
        return false;
      }

      if (confirm('您选择的模板将从数据库重新读取内容，生成模板文件。您确认此操作吗？')) {
        document.bthCreateForm.pageValues.value = checkedStr.substring(1);
        document.bthCreateForm.action = "<%=request.contextPath%>/tvWapPage/createPages"
        document.bthCreateForm.submit();
      }
    }

    function openUrl() {
      var topId = document.getElementById("topicId");
      var uploadzip = "${request.contextPath}/tvWapTopic/uploadZip.gsp?flag=false&topicId=" + topId.value;
      window.showModalDialog(uploadzip, '模板上传', "modal:yes; dialogWidth:850px;dialogHeight:550px;scroll:no;center:yes;status:no;help:no");
    }
    /**
    更新最新模板文件至数据库*
     */
    function pagesIntoDb() {
      var checkedStr = "";

      $(".allSelects").each(function() {
        if ($(this).attr("checked")) {
          state = true;
          checkedStr += ',' + this.value;
        }
      });
      if (checkedStr.length <= 0) {
        alert("${message(code: 'game.cms.noSelectDate.label', default: 'No date')}");
        return false;
      }
      if (confirm('您选择的模板将从本地读取更新至数据库。您确认此操作吗？')) {
        document.bthCreateForm.pageValues.value = checkedStr.substring(1);
        document.bthCreateForm.action = "<%=request.contextPath%>/tvWapPage/pagesIntoDb"
        document.bthCreateForm.submit();
      }
    }

  </script>
</head>
<body>
<div id='tagDiv'></div>
<div class="nav">

  <span class="menuButton"><g:link class="list" action="list">专题列表</g:link></span>
  <span class="menuButton"><g:link class="create" action="create">新建专题</g:link></span>
</div>
<div class="body">
  <h1>专题信息</h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <div class="dialog">
    <table>
      <tbody>

      <tr class="prop">
        <td valign="top" class="name">编号:</td>

        <td valign="top" class="value">${fieldValue(bean: tvWapTopic, field: 'id')}</td>

      </tr>
      <tr class="prop">
        <td valign="top" class="name">名称:</td>

        <td valign="top" class="value">${fieldValue(bean: tvWapTopic, field: 'name')}</td>

      </tr>
      <tr class="prop">
        <td valign="top" class="name">描述:</td>

        <td valign="top" class="value">${fieldValue(bean: tvWapTopic, field: 'description')}</td>

      </tr>
      <tr class="prop">
        <td valign="top" class="name">创建时间:</td>

        <td valign="top" class="value">
            <g:formatDate date="${tvWapTopic?.createTime}" />
        </td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name">创建人:</td>

        <td valign="top" class="value"><g:link controller="tvSysUser" action="show" id="${tvWapTopic?.creator?.id}">${tvWapTopic?.creator?.encodeAsHTML()}</g:link></td>

      </tr>

      </tbody>
    </table>
  </div>
  <div class="buttons">
    <g:form>
      <input type="hidden" name="id" value="${tvWapTopic?.id}"/>
      <span class="button"><g:actionSubmit class="edit" value="编辑" action="edit"/></span>
      <span class="button"><g:actionSubmit class="delete" onclick="return confirm('确定要删除专题吗?');" value="删除" action="Delete"/></span>
    </g:form>
  </div>
</div>
<div class="body">
  <g:if test="${flash.templateMessage}">
    <div class="${flash.msgType ?: 'message'}">${flash.templateMessage}</div>
  </g:if>
  <g:form name="bthCreateForm">
    <g:hiddenField name="pageValues" value=""/>
    <g:hiddenField name="topicId" id="topicId" value="${tvWapTopic?.id}"/>
    <div>
      <g:link controller="tvWapPage" action="create" params="[tvWapTopicId:tvWapTopic?.id]">+增加模板</g:link>
      |
      <a onclick="createPages();" href="#">重新生成模板文件</a>
      | <a onclick="openUrl();"  href="#">模板上传</a>
      |
      <a onclick="pagesIntoDb();" href="#">更新模板文件至数据库</a>
    </div>
    <div class="list">
      <table>
        <thead>
        <tr>

          <th align="center">&nbsp;<g:checkBox name="allSelect" id="allSelect"/></th>

          <g:sortableColumn property="name" title="名称"/>

          <g:sortableColumn property="title" title="标题"/>

          <g:sortableColumn property="description" title="描述"/>

          <g:sortableColumn property="contentType" title="内容类型"/>

          <g:sortableColumn property="topic" title="专题"/>

          <g:sortableColumn property="isPublish" title="是否发布"/>

          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${tvWapPageList}" status="i" var="tvWapPage">
          <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

            <td align="center"><g:checkBox name="select" class="allSelects" checked="false" value="${tvWapPage?.id}"/></td>

            <td>${fieldValue(bean: tvWapPage, field: 'name')}</td>

            <td>${fieldValue(bean: tvWapPage, field: 'description')}</td>

            <td>${fieldValue(bean: tvWapPage, field: 'title')}</td>

            <td>${fieldValue(bean: tvWapPage, field: 'contentType')}</td>

            <td>${tvWapPage.topic}</td>

            <td>
              <g:if test="${tvWapPage.published == '1'}">
                已发布
              </g:if>
              <g:else>
                未发布
              </g:else>
            </td>

            <td>
              %{--<a target="_blank" href="http://gamepie.g188.net${request.contextPath}/wap/${tvWapPage?.topic?.name}/${tvWapPage?.name}">预览</a>
             --}%
              <input id="tag" type="button" style="width:30px;height:20px; border: #7f9db9 1px solid; vertical-align:middle;color: #054ea8;background: #d9eefd;margin: 0; padding: 0;"
                           onclick="viewPage('wap_${tvWapPage?.topic?.name}_${tvWapPage?.name}');" value="预览"/>
              <g:link controller="tvWapPage" action="show" id="${tvWapPage.id}">查看</g:link>

              <g:link controller="tvWapPage" action="edit" id="${tvWapPage.id}">编辑</g:link>

              <g:link controller="tvWapPage" action="delete" onclick="return confirm('确定删除吗?');" id="${tvWapPage.id}">删除</g:link>

            </td>
          </tr>
        </g:each>
        </tbody>
      </table>
    </div>
    <div class="paginateButtons">

      <g:paginate params="[id:params.id]" total="${tvWapPageList?.totalCount}"/>
    </div>
  </g:form>
</div>
</body>
</html>
