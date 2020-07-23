package com.demo.shiro;

import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Test;

public class Test1 {


    @Test
    public void sha(){
        DefaultHashService hashService = new DefaultHashService();
        hashService.setHashAlgorithmName(hashService.getHashAlgorithmName());

        hashService.setPrivateSalt(new SimpleByteSource("123"));
        hashService.setGeneratePublicSalt(true);
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());

        hashService.setHashIterations(1);

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();


        session.getHost();


        session.touch();


        SecurityManager securityManager = SecurityUtils.getSecurityManager();

    }



    @Test
    public void testHelloworld() {
//1、获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        defaultSecurityManager.setRealm(iniRealm);


//2、得到 SecurityManager 实例 并绑定给 SecurityUtils
        SecurityUtils.setSecurityManager(defaultSecurityManager);
//3、得到 Subject 及创建用户名/密码身份验证 Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
//4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
//5、身份验证失败
        }
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
//6、退出
        subject.logout();






    }


}
