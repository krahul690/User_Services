package com.user.Binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserForm {

	private Integer contactId;
	private String contactName;
	private String contactEmail;
	private Long contactNumber;
	private String gender;
	private String activeSw;
	private LocalDate creatDate;
	private LocalDate updateDate;
}
