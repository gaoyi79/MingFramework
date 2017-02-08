package cn.org.mingframework.system.shiro;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import cn.org.mingframework.model.entity.system.User;

public class PasswordHelper {
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private String algorithmName = "md5";
	private int hashIterations;
	
	public void encryptPassword(User user){
		this.hashIterations = (int) (Math.random()*100);
		user.setSalt(randomNumberGenerator.nextBytes(this.hashIterations).toHex());
		user.setHashIterations(this.hashIterations);
		String password = new SimpleHash(
				this.algorithmName,
				user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()),
				hashIterations).toHex();
		user.setPassword(password);
	}
}
