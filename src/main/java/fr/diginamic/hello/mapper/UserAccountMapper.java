package fr.diginamic.hello.mapper;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import fr.diginamic.hello.entities.UserAccount;

public class UserAccountMapper {
	public static UserDetails asUser(UserAccount userAccount) {
		return User.builder().username(userAccount.getUsername()).password(userAccount.getPassword())
				.authorities(userAccount.getAuthorities()).build();
	}

}
