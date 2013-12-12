<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title><g:message code="model.fileRequestList"/></title>
  <g:javascript library="jquery"/>
  <script type="text/javascript">
    function checkZipUpload() {
      var fileName = $("#file").val();
      if (fileName == '') {
        alert("请选择文件！");
        return true;
      } else {
        if (fileName.indexOf(".zip") > 0) {
          var anw = confirm("你上传了一个压缩文件，是否自动将内容解压？");
          if (anw) {
            $("#autoext").val("true");
          }
        }

        if (fileName.indexOf(".gsp") > 0||fileName.indexOf(".jsp") > 0||  fileName.indexOf(".java") > 0 || fileName.indexOf(".class")>0) {
          alert("文件非法");
        } else {
          $("#fileup").submit();
        }
      }
    }
  </script>
</head>
<body>

<div class="body">
  <h1><g:message code="model.subDeriectoryList"/></h1>
  <h2>
    <g:set var="sss" value=""/>
    <g:link action="list">${userPath}</g:link>/
    <g:each in="${path.tokenize('/')}" var="cat">
      <g:link action="list" params="[path:sss+cat]">${cat}</g:link>/
      <g:set var="sss" value="${sss+ cat + '/'}"></g:set>
    </g:each>
  </h2>
  <g:if test="${flash.message}">
    <div class="message">
      ${flash.message}
    </div>
  </g:if>
  <div class="list">
    <table>
      <thead>
      <tr>
        <th><g:message code="model.dirName" default="Directory Name"/></th>
        <th><g:message code="model.fileCount"/></th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <g:each in="${dirList}" var="dir">
        <tr>
          <td><g:link action="list" params="[path:path + dir.name]">${dir.name}</g:link></td>
          <td>${dir.listFiles().size()}${path}</td>
          <td><g:link action="delete" params="[name:dir.name,path:path]" onclick="return confirm('确认要删除该文件夹吗？该文件夹下面的文件也将被同时删除，请小心操作!');">删除</g:link></td>
        </tr>
      </g:each>
      </tbody>
    </table>
  </div>
  <g:form method="post" action="save">
    <input type="text" name="name"/>
    <input type="hidden" name="path" value="${path}"/>
    <input type="submit" value="创建"/>
  </g:form>
  <g:form method="post" action="upload" enctype="multipart/form-data" id="fileup" name="fileup">
    <input type="file" name="file" id="file"/>
    <input type="hidden" name="path" value="${path}"/>
    <input type="hidden" name="autoext" id="autoext" value="false"/>
    <input type="button" value="${message(code: 'model.upload')}" onClick="checkZipUpload();"/>
    &nbsp;&nbsp;<span>支持zip压缩格式文件自动解压（间接支持批量上传）。</span>
  </g:form>

  <h1>File List</h1>
  <div class="list">
    <table>
      <thead>
      <tr>
        <th><g:message code="model.fileName" default="File name"/></th>
        <th><g:message code="model.size"/></th>
        <th><g:message code="model.lastUpdateTime"/></th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <g:each in="${fileList}" var="file">
        <tr>
          <td><a target="_blank" href="http://gamepie.g188.net${grailsApplication.config.mag.webPath}/uploads/${userPath}/${path}${file.name}">${file.name}</a></td>
          <td>${file.length()}</td>
          <td>${new Date(file.lastModified())}</td>
          <td><g:link action="delete" params="[path:path,name:file?.name]" onclick="return confirm('确认要删除文件吗？此操作不可恢复，请小心操作!');">删除</g:link></td>
        </tr>
      </g:each>
      </tbody>
    </table>
  </div>

</div>
</div>
</body>
</html>
