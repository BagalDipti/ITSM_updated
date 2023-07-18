package com.itsm.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.itsm.config.AppUser;
import com.itsm.model.User;
import com.itsm.model.UserDto;
import com.itsm.repository.UserRepository;
//import com.itsm.service.RoleService;
import com.itsm.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserRepository userRepository;

//	@Autowired
//    private RoleService roleService;

	@Autowired
    private BCryptPasswordEncoder bcryptEncoder;

	// To create new Roles

	@Override
	public User addRole(User user) {

		return this.userRepository.save(user);
	}

	// To get All Roles

	@Override
	public Set<User> getAll() {
		return new LinkedHashSet<>(this.userRepository.findAll());
	}

	// To update manager Id for created Role

	@Override
	public User setManagerID(User user, Integer userId) {

		User user2 = this.userRepository.findById(userId).get();

		user2.setManagerId(user.getManagerId());
		return this.userRepository.save(user2);
	}
	
	// To set Login_ID and Login_Passwrd for created Role

	@Override
	public User setIdAndPassword(User user, Integer userId) {

		User user2 = this.userRepository.findById(userId).get();
//		user2.setLoginId(user.getLoginId());
		user2.setLoginPassword(user.getLoginPassword());
		
		
		return this.userRepository.save(user2);
	}

	@Override
	public User getUserById(int id) {
		return this.userRepository.findUserById(id);
	}

	@Override
	public String getManagerEmailByUid(int managerId) {
		return this.userRepository.findManagerEmailByUid(managerId);
	}


	@Override
    public User save(UserDto user) {

		User nUser = user.getUserFromDto();
		
        nUser.setLoginPassword(bcryptEncoder.encode(user.getLoginPassword()));

//        com.itsm.model.Role role = roleService.findByName("USER");
//        Set<com.itsm.model.Role> roleSet = new HashSet<>();
//        roleSet.add(role);

		if(nUser.getUserName().startsWith("GS")){
//            role = roleService.findByName("ADMIN");
//            roleSet.add(role);
        }
		if(nUser.getUserName().startsWith("M_")){
//            role = roleService.findByName("MANAGER");
//            roleSet.add(role);
        }

		if(nUser.getUserName().startsWith("A_")){
//            role = roleService.findByName("ACTIONOWNER");
//            roleSet.add(role);
        }

//        nUser.setRoles(roleSet);
        return userRepository.save(nUser);
    }

	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
	}

	@Override
	public User findOne(String username) {
		return userRepository.findByUserName(username);
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        //return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getLoginPassword(), getAuthority(user));

		return new AppUser(user);
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        user.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
//        });
//        return authorities;
    	return null;
    }

}


//	@Override
//	public List<String> allUserEmail() {
//		// TODO Auto-generated method stub
//		return this.userRepository.allUserEmail();
//	}


