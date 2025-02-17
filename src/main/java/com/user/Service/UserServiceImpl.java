package com.user.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.Binding.UserForm;
import com.user.Entity.UserAccount;
import com.user.Repository.UserRepo;

@Service
public class UserServiceImpl implements IUserService, Serializable {

	private static final long serialVersionUID = 1L;


	@Autowired
	private UserRepo repository;
	UserForm form = new UserForm();
	
	@Override
	public String upsert(UserForm form) {
		UserAccount entity;

		if (form.getContactId() != null && repository.existsById(form.getContactId())) {
			// Update existing user
			entity = repository.findById(form.getContactId()).orElse(new UserAccount());
			BeanUtils.copyProperties(form, entity, "userId"); // Exclude userId from overwriting
		} else {
			// Insert new user
			entity = new UserAccount();
			BeanUtils.copyProperties(form, entity);
		}

		//entity.setActiveSw("Y"); // Ensure activeSw is set to "Y"
		entity = repository.save(entity); // Save the entity

		if (entity.getContactId() != null) {
			return "SUCCESS";
		}
		return "FAILURE";
	}

	@Override
	public List<UserForm> getAllUser() {
		List<UserForm> datalist = new ArrayList<>();

		List<UserAccount> all = repository.findAll();
		for (UserAccount entity : all) {
			UserForm form = new UserForm(); 
			BeanUtils.copyProperties(entity, form);
			datalist.add(form);
		}
		return datalist;
	}

	@Override
	public UserForm getUserbyId(Integer userId) {
		
		Optional<UserAccount> byId = repository.findById(userId);
		if (byId.isPresent()) {
			BeanUtils.copyProperties(byId.get(), form);
			return form;
		}
		return null;

	}

	@Override
	public boolean deleteUser(Integer userId) {
		boolean existsById = repository.existsById(userId);
		if (existsById) {
			repository.deleteById(userId);
			return true;
		}
		return false;
	}

	public boolean activateUser(Integer userId, String status) {
		try {
			System.out.println("User ID: " + userId + ", Status: " + status);
			repository.updateUserStatus(userId, status);
			return true;
		} catch (Exception e) {
			// log.error("Error updating user status: {}", e.getMessage());
			return false;
		}
	}
}
