
package com.liu.qinziyou.web.action.userlogin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.liu.qinziyou.common.util.LoginInfoUtil;
import com.liu.qinziyou.web.action.BaseAction;

public class ValidatecodeAction extends BaseAction {
	private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
//		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc;
		int g = fc;
		int b = fc;
		return new Color(r, g, b);
	}

	public void index() {
		HttpServletResponse response = super.getResponse();
		// 设置页面不缓存
		response.setHeader(
						"P3P",
						"CP=\"NON DSP COR CURa ADMa DEVa TAIa PSAa PSDa IVAa IVDa CONa HISa TELa OTPa OUR UNRa IND UNI COM NAV INT DEM CNT PRE LOC\"");
		// response.getWriter().clear();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 在内存中创建图象
		int width = 65, height = 22;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(255, 255));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		// 画边框
		// g.setColor(new Color());
		// g.drawRect(0,0,width-1,height-1);
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(0, 0));
		for (int i = 0; i < 39; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 取随机产生的认证码(4位数字)
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(0,0,0));
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 13 * i + 6, 16);
		}
		// 将认证码存入SESSION
		// session.setAttribute("verifyCode",sRand);
		// CookieUtils.setCookie(request, response, "VerifyCode", sRand, -1);
		// 将验证码旅客加密保存到cookie中。
		System.out.println(sRand);
		LoginInfoUtil.setValidateCode(super.getRequest(), super.getResponse(),sRand);

		// 图象生效
		g.dispose();
		// 输出图象到页面
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
			//return new MsgBean().setMsg("生成验证码成功").setMsgCode(MsgBean.MsgCode.SUCCESS);
		} catch (IOException e) {
			//return new MsgBean().setMsg("生成验证码异常").setMsgCode(MsgBean.MsgCode.FAILURE);
		}
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getModel() {
		return null;
	}
}
