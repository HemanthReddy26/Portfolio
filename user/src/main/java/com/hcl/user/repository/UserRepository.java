package com.hcl.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User findByUserNameAndPassword(String userName,String password);

	User findByUserName(String userName);
	

}
