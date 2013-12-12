var CONTEXT_PATH;

function initContextPath(contextPath) {
    CONTEXT_PATH = contextPath;
}

function upload(inputs,baseDir){
     createResourceUpload(inputs,baseDir)
}

function createResourceUpload(inputs,baseDir,rename,ignoreExtension,ignoreDate){
    var filePathInput = $(inputs.filePathInput);
    var uploadButton = $("<a class=\"ovalbutton\"><span style=\"cursor:pointer\">上传文件</span></a>");
    var callerOptions = {};
    var options = {rename:false,ignoreExtension:true,ignoreDate:true};
    if($.isPlainObject(rename))
        callerOptions = rename;
    else if (rename != null )
        options.rename = rename;
    if(baseDir != null)
        options.baseDir = baseDir;
    if(ignoreExtension!=null)
        options.ignoreExtension = ignoreExtension;
    if(ignoreDate!=null)
        options.ignoreDate = ignoreDate;
    $.extend(options,callerOptions);

    filePathInput.after(uploadButton);
    createUpload(uploadButton,options,function(file, response){
       filePathInput.val(response.uri);
       if(inputs.fileSizeInput)
        $(inputs.fileSizeInput).val(response.size);
    });
}
function createUpload(divId, data, completeCallback) {
    new AjaxUpload(divId,
    {
        action: CONTEXT_PATH + '/uploadFile'
        ,data:data
        ,onSubmit: function(file, extension) {
            var result = confirm("确定上传文件：" + file + "?", "确定");
            if(result){
                var uploadingDiv = getUploadingInfo();
                uploadingDiv.css("top",($(document).height()-uploadingDiv.height())/2);
                uploadingDiv.css("left",($(document).width()-uploadingDiv.width())/2);
                uploadingDiv.show();
            }
            return result;
        }
        ,responseType:"json"
        ,onComplete: function(file, response) {
            setTimeout(function(){getUploadingInfo().hide();},200);
            if(!response.success){
                alert("上传文件出错(错误消息:" + response.msg + ")");
                return;
            }
            if(response.overlay){
                 jAlert("success","上传文件成功(覆盖同名文件文件)！","文件上传结果");
            }else{
                jAlert("success","上传文件成功！","文件上传结果");
            }
            completeCallback(file, response);
        }
    });
}
function getUploadingInfo(){
    var contentId = "jquery_UploadingInfo_div";
    if($("#" + contentId).length>0)return $("#" +contentId);
    var content = $("<div id='"+contentId+"'><span>正在处理...</span></div>");
    $(document.body).append(content);
    return content;
}