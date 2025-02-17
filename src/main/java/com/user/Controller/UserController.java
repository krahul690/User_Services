package com.user.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.user.Binding.UserForm;
import com.user.Service.IUserService;

@Controller
public class UserController {

	@Autowired
	private IUserService service;

	/*
	 * @GetMapping("/") public String readyForm(Model m) { m.addAttribute("user",
	 * new UserForm()); return "index"; }
	 * 
	 * @PostMapping("/submitUserForm") public String
	 * SubmitForm(@ModelAttribute("user") UserForm form, Model m) { String upsert =
	 * service.upsert(form); m.addAttribute("upsert", upsert);
	 * m.addAttribute("user", new UserForm()); return "index"; }
	 */

	@GetMapping("/")
	public String readyForm(Model m) {
		m.addAttribute("user", new UserForm()); // Add new UserForm to the model
		return "index"; // Return the view name
	}

	@PostMapping("/submitUserForm")
	public String SubmitForm(@ModelAttribute("user") UserForm form, RedirectAttributes redirectAttributes) {
		String upsert = service.upsert(form); // Call service method to save or update the user
		redirectAttributes.addFlashAttribute("upsertMessage", upsert); // Add flash attribute for the success/failure
																		// message
		return "redirect:/"; // Redirect to the form page to avoid re-submission
	}

	@GetMapping("/viewUsers")
	private String getAllUsers(Model m) {
		List<UserForm> allUser = service.getAllUser();
		System.out.println("Fetched Users: " + allUser); // Print users for debugging
		m.addAttribute("users", allUser);
		return "viewUser";

	}

	/*
	 * @GetMapping("/deleteuser") public String deleteUser(@RequestParam("id")
	 * Integer id, Model m) { boolean isDeleted = service.deleteUser(id);
	 * List<UserForm> allUsers = service.getAllUser(); // Refresh the user list
	 * m.addAttribute("users", allUsers); m.addAttribute("message", isDeleted ?
	 * "User deleted successfully!" : "Failed to delete user."); return "viewUser";
	 * // Return the updated view }
	 */

	@PostMapping("/deleteuser")
	public String deleteUser(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
		boolean deleteUser = service.deleteUser(id);
		if (deleteUser) {
			redirectAttributes.addFlashAttribute("delete", "User deleted successfully!");
		} else {
			redirectAttributes.addFlashAttribute("delete", "Failed to delete user!");
		}
		return "redirect:/viewUsers"; // Redirect to ensure a new request is made
	}

	@GetMapping("/edit")
	public String editUser(@RequestParam("id") Integer id, Model m) {
		UserForm byid = service.getUserbyId(id);
		m.addAttribute("user", byid);

		return "index";
	}

	@GetMapping("/update")
	public String statusUpdate(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes,
			@RequestParam("status") String status) {
		boolean activateUser = service.activateUser(id, status);
		if ("Y".equals(status)) {
			redirectAttributes.addFlashAttribute("active", "User Activates successfully!");
		} else {
			redirectAttributes.addFlashAttribute("active", "User De-Activates successfully!");
		}

		System.out.println(status);
		return "redirect:/viewUsers";
	}
}
