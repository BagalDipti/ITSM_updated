package com.itsm.config;

import java.util.Collection;

import com.itsm.model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class AppUser implements UserDetails {


    private static final long serialVersionUID = 1L;

    User user;

    
    
    
    
    
    
    

    public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public AppUser(User user) {
        this.user = user;
    }


    @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return AuthorityUtils.createAuthorityList(this.user.getRole());
  }

  @Override
  public String getPassword() {
    return this.user.getLoginPassword();
  }

  @Override
  public String getUsername() {
    return this.user.getUserName();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }


   



    
}
