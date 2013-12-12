package com.vivame.groovy.util

import com.gif4j.GifDecoder
import com.gif4j.GifEncoder
import com.gif4j.GifImage
import com.gif4j.GifTransformer
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

/**
 * viavame All Rights Reserved@2008-2009
 * 作者: 王海利
 * 日期: 2009-6-3
 * 时间: 10:36:31
 * 说明: 该类实现。。。。
 * 修改人:王海利
 * 修改时间: 2009-6-3 10:36:31
 */

public class ImgUtil {

  //从src将图片缩放为to，设置宽高 preferWidth，保持原有宽高比

  def static resizeImg(def src, def to, int preferWidth, String imageFormat) {

    BufferedImage image = null;

    if (src instanceof String) {
      image = javax.imageio.ImageIO.read(new File(src));
    } else {
      image = javax.imageio.ImageIO.read(src);
    }
    int width = image.getWidth();
    int height = image.getHeight();

    if (width > preferWidth) {//图片宽度大于 需要缩放的大小才做处理

      int mWidth = preferWidth;
      int mHeight = preferWidth * height / width;
      BufferedImage dest = new BufferedImage(mWidth, mHeight, BufferedImage.TYPE_INT_RGB);

      if ("BMP".equalsIgnoreCase(imageFormat) || "PNG".equalsIgnoreCase(imageFormat) ||
              "JPG".equalsIgnoreCase(imageFormat) || "JPEG".equalsIgnoreCase(imageFormat)) {
        dest.getGraphics().drawImage(image, 0, 0, mWidth, mHeight, null);
        FileOutputStream out = new FileOutputStream(to);
        ImageIO.write(dest, "jpeg", out);


        out.close()
        if (src instanceof InputStream) {
          src.close()
        }

      } else if ("GIF".equalsIgnoreCase(imageFormat)) {
        GifImage gifImage = null;
        if (src instanceof String) {
          gifImage = GifDecoder.decode(new FileInputStream(src));
        } else {
          gifImage = GifDecoder.decode(src);
        }


        GifImage resizedGifImage = GifTransformer.resize(gifImage, mWidth, -mHeight, false);
        FileOutputStream out = new FileOutputStream(to);
        GifEncoder.encode(resizedGifImage, out);
        out.close()
        if (src instanceof InputStream) {
          src.close()
        }

      }


    }


  }

  public static void main(String[] args) {
    ImgUtil.resizeImg("E:\\home\\www\\htdocs\\cms\\Image\\p0.jpg", "E:\\home\\www\\htdocs\\cms\\Image\\p0126.jpg", 126)

    ImgUtil.resizeImg("E:\\home\\www\\htdocs\\cms\\Image\\1.gif", "E:\\home\\www\\htdocs\\cms\\Image\\1126.gif", 126)


  }

}