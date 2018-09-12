package com.zxy.hrbcu.meeting.controller;

import com.zxy.hrbcu.meeting.exception.BaseException;
import com.zxy.hrbcu.meeting.service.LoginService;
import com.zxy.hrbcu.meeting.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by zxy on 2017/6/26.
 *
 * @author zxy.
 * @version 1.0
 * @since 2018.02.27
 */
@Controller
@RequestMapping("/login")
@Scope("prototype")
public class LoginController {
    @Resource
    private LoginService loginService;
    /**
     * The Logger.
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @RequestMapping(value = "/authCode")
    public void getAuthCode(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
        int width = 63;
        int height = 37;
        Random random = new Random();
        //设置response头信息
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //生成缓冲区image类
        BufferedImage image = new BufferedImage(width, height, 1);
        //产生image类的Graphics用于绘制操作
        Graphics g = image.getGraphics();
        //Graphics类的样式
        g.setColor(this.getRandColor(200, 250));
        g.setFont(new Font("Times New Roman",0,28));
        g.fillRect(0, 0, width, height);
        //绘制干扰线
        for(int i=0;i<40;i++){
            g.setColor(this.getRandColor(130, 200));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }

        //绘制字符
        String strCode = "";
        for(int i=0;i<4;i++){
            String rand = String.valueOf(random.nextInt(10));
            strCode = strCode + rand;
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            g.drawString(rand, 13*i+6, 28);
        }
        //将字符保存到session中用于前端的验证
        session.setAttribute("strCode", strCode);

        g.dispose();

        ImageIO.write(image, "JPEG", response.getOutputStream());
        response.getOutputStream().flush();
    }

    //创建颜色
    Color getRandColor(int fc,int bc){
        Random random = new Random();
        if(fc>255) {
            fc = 255;
        }
        if(bc>255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r,g,b);
    }

    /**
     * To login model and view.
     *
     * @param request the request
     * @param model   the model
     * @return the model and view
     * @since 2018.02.27
     */
    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public ModelAndView toLogin(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView("admin/login");
        return mv;
    }

    /**
     * Login result vo.
     * 点击登录
     * @param request  the request
     * @return the result vo
     * @throws Exception the exception
     * @since 2018.02.27
     */

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo login(HttpServletRequest request) throws Exception {
        try {
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            String strCode = request.getParameter("strCode");
            ResultVo resultVo = loginService.login(phone, password,strCode,request);
            return resultVo;
        } catch (BaseException be) {
            logger.info("LoginController login BaseException :{}", be);
            be.printStackTrace();
            ResultVo resultVo = new ResultVo();
            resultVo.setStatus(0);
            resultVo.setMsg(be.getMessage());
            return resultVo;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("LoginController login error :{}", e);
            ResultVo resultVo = new ResultVo();
            resultVo.setStatus(0);
            resultVo.setMsg("登录异常!");
            return resultVo;
        }
    }

    /**
     * Login out result vo.
     * 点击退出
     * @param request  the request
     * @return the result vo
     * @throws Exception the exception
     * @since 2018.02.27
     */

    @RequestMapping(value = "/loginOut", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVo loginOut(HttpServletRequest request) throws Exception {
        try {
            ResultVo resultVo = loginService.loginOut(request);
            return resultVo;
        } catch (BaseException be) {
            ResultVo resultVo = new ResultVo();
            resultVo.setStatus(0);
            resultVo.setMsg(be.getMessage());
            return resultVo;
        } catch (Exception e) {
            logger.info("LoginController loginOut error :{}", e);
            ResultVo resultVo = new ResultVo();
            resultVo.setStatus(0);
            resultVo.setMsg("退出异常!");
            return resultVo;
        }
    }

}
