package com.qf.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class TestAuthorization {
    public static void main(String[] args) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin2","1234");
        try{
            subject.login(usernamePasswordToken);
            if(subject.isAuthenticated()){
                System.out.println("登录成功");
                //isPermitted判断用户是否具有某个权限
                System.out.println(subject.isPermitted("user:add"));
                //判断用户是否具有某些权限
                //boolean[] bs = subject.isPermitted("user:add","user:select");
               // for (boolean b:bs){
                //    System.out.println(b);
                //}
                //checkPermission和isPermitted的区别：
                //当检测账户没有某个权限时，isPermitted返回false，checkPermission抛出异常
                //subject.checkPermission("user:delete");
                //判断账户具有的角色
                System.out.println(subject.hasRole("role1"));
                //checkRole和hasRole的区别同上
                //subject.checkRole("role2");

            }
        }catch (AuthenticationException e){
            System.out.println("登录失败");
        }
    }
}
