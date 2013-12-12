package com.emag.gamecms.controllers

import org.grails.plugins.springsecurity.service.AuthenticateService
import org.springframework.util.FileCopyUtils
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile
import org.codehaus.groovy.grails.validation.Validateable

@Validateable
class FileController {


  AuthenticateService authenticateService
  def grailsApplication

  def index = {redirect(action: list, params: params)}

  def list = {
    def pathname = grailsApplication.config.video.publishPath

    def p = authenticateService.userDomain()
    def userPath = p.username
    def curDir = new File(pathname + "uploads/" + userPath)
    if (!curDir.exists()) curDir.mkdir()
    def path = ""
    if (params.path) {
      path = params.path
      if (!path.endsWith("/")) path = path + "/"

      curDir = new File(curDir.getAbsolutePath() + "/" + params.path)
      if (!curDir.exists()) {
        return render(text: "not exist.")
      }
    }


    def dirList = []
    def fileList = []
    for (dir in curDir.listFiles()) {
      if (dir.isDirectory()) {
        dirList.add(dir)
      } else if (dir.isFile()) {
        fileList.add(dir)
      }
    }
    [dirList: dirList, fileList: fileList, userPath: userPath, path: path]
  }

  def upload = {
    if (!(request instanceof MultipartHttpServletRequest)) {
      log.warn("file control upload no multipart")
    }
    MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request
    CommonsMultipartFile file = (CommonsMultipartFile) multiRequest.getFile("file")
    def path = ""
    def p = authenticateService.userDomain()
    def userPath = p.username
    if (file == null || file == "") {
      flash.message = "Please chose a file to upload before submiting"
      redirect(action: list, params: ['path': params.path?.trim()])
    } else {
      def pathname = grailsApplication.config.video.publishPath
      def curDir = new File(pathname + "uploads/" + userPath)
      //def userPath =  session["ACEGI_SECURITY_LAST_USERNAME"]
      if (!curDir.exists()) curDir.mkdirs()
      String sep = System.getProperty("file.separator");
      if (params.path) {
        path = params.path
        if (!path.endsWith("/")) path = path + "/"
      }
      def originalFileName = file.getOriginalFilename()
      def uploadFileName = curDir.getAbsolutePath() + "/" + path + file.getOriginalFilename()
      def outputDirectory = curDir.getAbsolutePath() + "/" + path
      //String uploadFileName = "D:\\test\\test.zip";
      //String outputDirectory = "D:\\test\\unzip";

      def checkname = originalFileName?.toLowerCase()
      if (checkname.endsWith("jsp") || checkname.endsWith("gsp")
              || checkname.endsWith("java") || checkname.endsWith("class")) {
        log.warn "file upload file is not allowed"
        redirect(action: list, params: ['path': params.path?.trim()])
        return true
      }

      File uploadedFile = new File(uploadFileName);
      FileCopyUtils.copy(file.bytes, uploadedFile);
      //Uzip begins
      if (originalFileName.endsWith(".zip") && params.autoext == "true") {
        try {
          File outFile = new File(outputDirectory);
          if (!outFile.exists()) {
            outFile.mkdirs();
          }
          org.apache.tools.zip.ZipFile zipFile = new org.apache.tools.zip.ZipFile(uploadFileName);
          java.util.Enumeration e = zipFile.getEntries();
          org.apache.tools.zip.ZipEntry zipEntry = null;
          while (e.hasMoreElements()) {
            zipEntry = (org.apache.tools.zip.ZipEntry) e.nextElement();
            def zipEntryName = zipEntry.getName()
            if (!zipEntry.isDirectory()) {
              //mkdir entryDirectory
              if (-1 != zipEntry.getName().indexOf("/")) {
                def fileDirName = zipEntryName.substring(0, zipEntryName.lastIndexOf("/"))
                File fDir = new File(outFile.getPath() + File.separator + fileDirName)
                if (!fDir.exists()) fDir.mkdirs()
              }
              File f = new File(outputDirectory + File.separator + zipEntry.getName());
              if (!f.exists()&&!(zipEntry.getName().toLowerCase().endsWith("jsp")
                      || zipEntry.getName().toLowerCase().endsWith("gsp")
                      || zipEntry.getName().toLowerCase().endsWith("class")
                      || zipEntry.getName().toLowerCase().endsWith("java")
                      || zipEntry.getName().toLowerCase().endsWith("jar")
              )) {
                f.createNewFile()
              }
              if (!(zipEntry.getName().toLowerCase().endsWith("jsp")
                      || zipEntry.getName().toLowerCase().endsWith("gsp")
                      || zipEntry.getName().toLowerCase().endsWith("class")
                      || zipEntry.getName().toLowerCase().endsWith("java")
                      || zipEntry.getName().toLowerCase().endsWith("jar")
              )) {
                InputStream fin = zipFile.getInputStream(zipEntry);
                FileOutputStream out = new FileOutputStream(f);
                int c;
                byte[] b = new byte[1024];
                while ((c = fin.read(b)) != -1) {
                  out.write(b, 0, c);
                }
                out.close();
                fin.close();
              }
            }
          }
          zipFile.close();
        } catch (Exception ex) {
          ex.printStackTrace();
        }
        new File(uploadFileName).delete()
      }
      //The ends
    }
    redirect(action: list, params: ['path': params.path?.trim()])
  }

  def save = {
    def pathname = grailsApplication.config.video.publishPath
    def p = authenticateService.userDomain()
    def userPath = p.username
    def curDir = new File(pathname + "uploads/" + userPath)
    if (!curDir.exists()) curDir.mkdirs()
    String sep = System.getProperty("file.separator");
    def path = ""
    if (params.path) {
      path = params.path
      if (!path.endsWith("/")) path = path + "/"
    }
    def newDirName = curDir.getAbsolutePath() + "/" + path + params.name
    def newDir = new File(newDirName)
    if (!newDir.exists()) newDir.mkdir()
    redirect(action: list, params: ['path': params.path?.trim()])
  }

  def delete = {
    def path = ""
    def pathname = grailsApplication.config.video.publishPath
    def p = authenticateService.userDomain()
    def userPath = p.username
    def curDir = new File(pathname + "uploads/" + userPath)
    if (!curDir.exists()) curDir.mkdir()
    if (params.path) {
      path = params.path
      if (!path.endsWith("/")) path = path + "/"
    }
    def checkPath = path + params.name
    if (checkPath == "" || checkPath.indexOf("..") > 0) {
      flash.message = "Invalid path or filename."
      return redirect(action: list, params: ['path': params.path?.trim()])
    } else {
      def fileName = pathname + "uploads/" + userPath + "/" + path + params.name
      deleteFile(new File(fileName))
      redirect(action: list, params: ['path': params.path?.trim()])
    }
  }

  def deleteFile(def theFile) {
    if (!theFile.isDirectory()) {
      theFile.delete()
    } else {
      def fileList = theFile.listFiles()
      if (fileList) {
        fileList.each() {
          if (it.isDirectory()) {
            deleteFile(it)
          } else {
            it.delete()
          }
        }
      }
      theFile.delete()
    }
  }
}