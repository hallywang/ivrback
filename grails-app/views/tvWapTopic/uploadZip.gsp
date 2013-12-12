<%--
  Created by IntelliJ IDEA.
  User: hezhejing
  Date: 2011-3-25
  Time: 9:34:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head >
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title>模板批量上传</title>
  <base target="_self">
  <g:javascript library="jquery"/>
  <script type="text/javascript">
    window.onload = function() {
      var flag = document.getElementById("flag");
      if (flag.value == "true") {
        document.getElementById("result").style.display = "block";
      } else {
        document.getElementById("result").style.display = "none";
      }
    }
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

        if (fileName.indexOf(".gsp") > 0 || fileName.indexOf(".jsp") > 0 || fileName.indexOf(".java") > 0 || fileName.indexOf(".class") > 0) {
          alert("文件非法");
        } else {
          $("#fileup").submit();
        }
      }
    }
  </script>
</head>
<body>
<g:if test="${flash.message}">
  <h5 align="center"><div class="message">${flash.message}</div></h5>
</g:if>
<g:form method="post" action="upload" enctype="multipart/form-data" id="fileup" name="fileup">
  <input type="file" name="file" id="file"/>
  <input type="hidden" name="topicId" id="topicId" value="${params.topicId}"/>
  <input type="hidden" name="path" value="${path}"/>
  <input type="hidden" name="autoext" id="autoext" value="false"/>
  <input type="button" value="${message(code: 'model.upload')}" onClick="checkZipUpload();"/>
</g:form>
<input type="hidden" name="flag" id="flag" value="${flag}">
<div>
  <h4>上传文件须知：</h4>
  <h5>1:只能上传ZIP包</h5>
  <h5>2:ZIP包中不能包含文件夹</h5>
  <h5>3:ZIP包中必须包含一个upload.csv文件</h5>
  <h5>4:ZIP包中必须包含已".gsp"结尾的模板文件，且名称以'_'开头</h5>
  <h5>5:ZIP包中upload.csv文件格式为：模板名称|标题|内容类型|描述|头文件 模板名称必须与上传的模板文件名称一一对应，但不能带'_'开头，并且不能".gsp"结尾</h5>
  <h5>6:内容类型只能是auto, 自定义html, text/plain, text/xml, wml/card, text/vnd.wap.wml, text/html, application/vnd.wap.xhtml+xml, application/asp_cs, noneContentType</h5>
</div>

<div id="result" style="display:none">
  <table>
    <tr>
      <td>操作结果：</td>
    </tr>
    <tr>
      <td>成功${sucs}条</td>
    </tr>
    <tr>
      <td>失败${fails}条</td>
    </tr>
    <tr>
      <td>
        失败原因:${infos}
      </td>
    </tr>
  </table>
</div>
</body>
</html>