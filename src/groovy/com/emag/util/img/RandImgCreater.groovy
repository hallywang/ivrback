package com.emag.util.img

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
/**
 * Created by IntelliJ IDEA.
 * User: hp
 * Date: 2010-9-15
 * Time: 9:29:20
 * To change this template use File | Settings | File Templates.
 */
class RandImgCreater {
  private static final String CODE_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
  private HttpServletResponse response = null;
  private static final int HEIGHT = 20;
  private static final int FONT_NUM = 4;
  private int width = 0;
  private int iNum = 0;
  private String codeList = "";
  private boolean drawBgFlag = false;

  private int rBg = 0;
  private int gBg = 0;
  private int bBg = 0;

  /**
   * 构造函数
   * @param response HttpServletResponse响应对象
   */
  public RandImgCreater(HttpServletResponse response) {
    this.response = response;
    this.width = 13 * FONT_NUM + 12;
    this.iNum = FONT_NUM;
    this.codeList = CODE_LIST;
  }

  /**
   * 构造函数
   * @param response HttpServletResponse响应对象
   * @param iNum 生成验证码的位数，一般是4位验证码
   * @param codeList 验证码的生成元字符 
   */
  public RandImgCreater(HttpServletResponse response, int iNum, String codeList) {
    this.response = response;
    this.width = 13 * iNum + 12;
    this.iNum = iNum;
    this.codeList = codeList;
  }

  /**
   * 生成验证码图片
   */
  public String createRandImage() {
    String sRand = "";
    try {
      BufferedImage image = new BufferedImage(width, HEIGHT, BufferedImage.TYPE_INT_RGB);
      OutputStream os = response.getOutputStream();
      Graphics g = image.getGraphics();
      Random random = new Random();

      if (drawBgFlag) {
        g.setColor(new Color(rBg, gBg, bBg));
        g.fillRect(0, 0, width, HEIGHT);
      } else {
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, HEIGHT);

        for (int i = 0; i < 155; i++) {
          g.setColor(getRandColor(140, 200));
          int x = random.nextInt(width);
          int y = random.nextInt(HEIGHT);
          int xl = random.nextInt(12);
          int yl = random.nextInt(12);
          g.drawLine(x, y, x + xl, y + yl);
        }
      }

      g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
      for (int i = 0; i < iNum; i++) {
        int rand = random.nextInt(codeList.length());
        String strRand = codeList.substring(rand, rand + 1);
        sRand += strRand;
        g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
        g.drawString(strRand, 13 * i + 6, 16);
      }

      g.dispose();
      ImageIO.write(image, "JPEG", os);
      os.flush();
      os.close();
      os = null;
      response.flushBuffer();
    } catch (IOException e) {

    }

    return sRand;
  }

  public void setBgColor(int r, int g, int b) {
    drawBgFlag = true;
    this.rBg = r;
    this.gBg = g;
    this.bBg = b;
  }

  private Color getRandColor(int fc, int bc) {
    Random random = new Random();
    if (fc > 255)
      fc = 255;
    if (bc > 255)
      bc = 255;
    int r = fc + random.nextInt(bc - fc);
    int g = fc + random.nextInt(bc - fc);
    int b = fc + random.nextInt(bc - fc);
    return new Color(r, g, b);
  }
}
