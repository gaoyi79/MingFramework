package cn.org.mingframework.system.shiro;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import cn.org.mingframework.dao.interfaces.SecurityService;
import cn.org.mingframework.model.entity.system.User;

public class MingJdbcAuthorizingRealm extends AuthorizingRealm {

	@Autowired
	SecurityService securityService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = token.getPrincipal().toString();
		
		User user = securityService.findByUserName(userName);
		if(null == user){
			throw new UnknownAccountException();
		}
		if(Boolean.TRUE.equals(user.getLocked())){
			throw new LockedAccountException();
		}
		
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getUserName(),
				user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()),
				getName());
		return authenticationInfo;
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles((Set<String>)securityService.getRolesString(userName));
		authorizationInfo.setStringPermissions((Set<String>)securityService.getPermissions(userName));
		return authorizationInfo;
	}
}
