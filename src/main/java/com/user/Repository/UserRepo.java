package com.user.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.user.Entity.UserAccount;

import jakarta.transaction.Transactional;

public interface UserRepo extends JpaRepository<UserAccount, Serializable> {

	
	@Modifying
	@Transactional
	@Query(value = "UPDATE User_TBL SET contact_activesw = :status WHERE user_id = :userId", nativeQuery = true)
	void updateUserStatus(@Param("userId") Integer userId, @Param("status") String status);

}
