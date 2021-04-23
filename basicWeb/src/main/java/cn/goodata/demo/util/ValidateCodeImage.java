package cn.goodata.demo.util;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class ValidateCodeImage {
    private static char[] ch = "abcdefghjkmnpqrstuvwxyABCDEFGHJKLMNPQRSTUVWXY3456789".toCharArray();
    private static int width = 70;
    private static int height = 23;
    //生成图片
    public static void makeImageSb(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 得到该图片的绘图对象
        Graphics g = img.getGraphics();
        Random r = new Random();
        g.setColor(getRandColor(200,250));
        // 填充整个图片的颜色
        g.fillRect(1, 1, width, height);
        // 向图片中输出数字和字母
        g.setColor(new Color(102,102,102));
        g.drawRect(0, 0, width-1, height-1);
        Font mFont = new Font("Arial Black", Font.PLAIN, 16);
        g.setFont(mFont);
        g.setColor(getRandColor(160,200));
        for (int i=0;i<155;i++)
        {
            int x = r.nextInt(width - 1);
            int y = r.nextInt(height - 1);
            int xl = r.nextInt(6) + 1;
            int yl = r.nextInt(12) + 1;
            g.drawLine(x,y,x + xl,y + yl);
        }
        for (int i = 0;i < 70;i++)
        {
            int x = r.nextInt(width - 1);
            int y = r.nextInt(height - 1);
            int xl = r.nextInt(12) + 1;
            int yl = r.nextInt(6) + 1;
            g.drawLine(x,y,x - xl,y - yl);
        }
        StringBuffer sb = new StringBuffer();
        int index, len = ch.length;
        for (int i = 0; i < 4; i++) {
            index = r.nextInt(len);
            g.setColor(new Color(20+r.nextInt(110),20+r.nextInt(110),20+r.nextInt(110)));
            g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));// 输出的字体和大小
            g.drawString("" + ch[index], (i * 15) + 3, 18);// 写什么数字，在图片的什么位置画
            sb.append(ch[index]);
        }
        request.getSession().setAttribute("validatecode", sb.toString());
        ImageIO.write(img, "JPG", response.getOutputStream());
    }

    private static Color getRandColor(int fc, int bc)
    {
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+SystemUtil.random.nextInt(bc-fc);
        int g=fc+SystemUtil.random.nextInt(bc-fc);
        int b=fc+SystemUtil.random.nextInt(bc-fc);
        return new Color(r,g,b);
    }

}
