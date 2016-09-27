package com.ziroom.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * 
* @ClassName ShiroUtil 
* @Description TODO shiro工具类
* @author dfx
* @date 2016年6月15日 下午5:56:13 
*
 */
public class ShiroUtil {

	//登录
	public static Subject login(String fileName, String username, String password){
		//1.获取工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(fileName);
		//2.创建工厂实例
		SecurityManager manager = factory.getInstance();
		//3.初始化实例
		SecurityUtils.setSecurityManager(manager);
		//4.TOKEN认证
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		//5.获取登录用户
		Subject subject = SecurityUtils.getSubject();
		//6.登录
		try {
			subject.login(token);
			System.out.println("登陆成功");
		} catch (AuthenticationException e) {
			e.printStackTrace();
			System.out.println("登录失败");
		}
		return subject;
	}
	
	//退出登录
	public static void logout(Subject subject){
		subject.logout();
	}
	
	@Test
	public void testLogin(){
		Subject subject = ShiroUtil.login("classpath:shirotest.ini", "java", "123456");
		ShiroUtil.logout(subject);
	}
}
