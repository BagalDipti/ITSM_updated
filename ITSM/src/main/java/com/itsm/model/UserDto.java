package com.itsm.model;

public class UserDto {
	private String userName;

	private String userEmail;

	private long userMobileNo;
	
	
	private int managerId;
	
	private String role;

	private String loginPassword;


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public long getUserMobileNo() {
        return userMobileNo;
    }

    public void setUserMobileNo(long userMobileNo) {
        this.userMobileNo = userMobileNo;
    }

    

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
  
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUserFromDto(){
        User user = new User();
        user.setUserName(userName);
        user.setLoginPassword(loginPassword);
        user.setUserEmail(userEmail);
        user.setUserMobileNo(userMobileNo);
        user.setRole(role);
        
        return user;
    }



    
}
