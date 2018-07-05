package oc.P6.escalade.WebappHelper;

import javax.inject.Named;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	
	@Named
	public PasswordEncoder() {
	   // return new BCryptPasswordEncoder();
	}

}
