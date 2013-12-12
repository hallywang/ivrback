package com.emag.gamecms.controllers

import java.text.DecimalFormat
import org.apache.commons.io.FilenameUtils

/**
 * 附件上传处理(ajax jquery upload处理)
 * <ul>
 * <li>参数 ignoreDate(true|false) :是否忽略日期创建目录</li>
 * <li>参数 ignoreExtension(true|false) :是否忽略根据附件扩展名创建目录</li>
 * <li>参数 rename(true|false) :是否重新随机命名附件文件名</li>
 * <li>参数 baseDir : 上传相对目录</li>
 * </ul>
 */
class UploadFileController {
  private final static java.text.DecimalFormat DF = new DecimalFormat("0.0#");

  private static final FileExtensionMap = ["*": "attachments",
          "gif": "images", "jpg": "images", "jpeg": "images", "png": "images", "bmp": "images",
          "jad": "java", "jar": "java", "sis": "symbian", "sisx": "symbian", "apk": "android", "xls": "excel", "swf": "flash"
  ]
  public static final def DeniedFileType = ['html', 'htm', 'php', 'php2', 'php3', 'php4', 'php5', 'phtml', 'pwml', 'inc', 'asp', 'aspx', 'ascx', 'jsp', 'gsp',
          'cfm', 'cfc', 'pl', 'bat', 'exe', 'com', 'dll', 'vbs', 'js', 'reg',
          'cgi', 'htaccess', 'asis', 'sh', 'shtml', 'shtm', 'phtm']
  def index = {

    try {
      request.fileNames.each {
        def file = request.getFile(it);
        String extension = FilenameUtils.getExtension(file.originalFilename);
        if (extension && DeniedFileType.contains(extension.toLowerCase())) {
          render(text: "<pre>{success:false,msg:'文件不合法'}</pre>");
          return;
        }
        saveFile(file);
        return null;
      }
    } catch (Exception e) {
      e.printStackTrace();
      render(text: "<pre>{success:false,msg:'$e.message'}</pre>");
    }

  }

  private def saveFile(def uploadFile) {
    boolean rename = params.boolean("rename") ?: false;
    boolean ignoreExtension = params.boolean("ignoreExtension") ?: false;
    boolean ignoreDate = params.boolean("ignoreDate") ?: false;

    String baseDir = params.baseDir;

    if (!baseDir) baseDir = "gamehall/images";

    String fileUri = createFileUri(rename, ignoreExtension,ignoreDate, uploadFile.originalFilename, baseDir);

    String filePath= servletContext.getRealPath(fileUri);

    fileUri = fileUri.replace('\\', "/")

    File serverFile = new File(filePath);
    log.info "upload file: $filePath,uri:$fileUri,size:${uploadFile.size}"

    serverFile.getParentFile().mkdirs();
    boolean overlay = serverFile.exists();
    uploadFile.transferTo(serverFile);
    
    render(text: "<pre>{success:true,uri:'$fileUri',size:'${uploadFile.size}',overlay:$overlay}</pre>")
  }

  private static String getDirByExtension(String extension) {
    if (!extension) return FileExtensionMap["*"];
    String dirName = FileExtensionMap[extension.toLowerCase()];
    return dirName ?: FileExtensionMap["*"];
  }

  private static String createFileUri(boolean rename, boolean ignoreExtension,ignoreDate, String fileName, String baseDir) {
    String extension = FilenameUtils.getExtension(fileName)
    if (!ignoreExtension)
      baseDir = FilenameUtils.concat(baseDir, getDirByExtension(extension));
    if(!ignoreDate)
      baseDir = FilenameUtils.concat(baseDir, new Date().format("yyMMdd"));
    if (rename)
      fileName = UUID.randomUUID().toString().replace("-", "") + "." + extension;
    return FilenameUtils.concat(baseDir, fileName);
  }


}
