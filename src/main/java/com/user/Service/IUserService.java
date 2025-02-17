package com.user.Service;

import java.io.Serializable;
import java.util.List;

import com.user.Binding.UserForm;

public interface IUserService extends Serializable {

	public String upsert(UserForm form);

	public List<UserForm> getAllUser();

	public UserForm getUserbyId(Integer userId);

	public boolean deleteUser(Integer userId);

	public boolean activateUser(Integer userId, String status);
}
