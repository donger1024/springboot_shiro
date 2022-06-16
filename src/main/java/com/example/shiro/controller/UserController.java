package com.example.shiro.controller;

import com.example.shiro.pojo.User;
import com.example.shiro.service.UserService;
import com.example.shiro.utils.VerifyCodeUtil;
import org.apache.jasper.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Security;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public String login(String username, String password, String verifyCode, HttpSession session) {
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        // 校验验证码
        String verifyCodes = (String) session.getAttribute("verifyCode");
        try {
            if (verifyCodes.equalsIgnoreCase(verifyCode)) {
                //执行登录操作
                subject.login(new UsernamePasswordToken(username, password));
                //认证通过后直接跳转index.jsp
                return "redirect:/index.jsp";
            }else {
                throw new RuntimeException("验证码错误");
            }
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误~");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误~");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果认证失败仍然回到登录页面
        return "redirect:/login.jsp";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        //退出后仍然回到登陆页面
        return "redirect:/login.jsp";
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public String register(User user){

        try {
            userService.register(user);
            return "redirect:/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/register.jsp";
    }
    @RequestMapping("getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        //生成验证码
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        //验证码放入session
        session.setAttribute("verifyCode",verifyCode);
        //验证码存入图片
        ServletOutputStream os = response.getOutputStream();
        response.setContentType("image/png");
        VerifyCodeUtil.outputImage(180,40,os,verifyCode);
    }
}
