package fund.dto;

import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Email;

public class User {
	int id;
	@Null(message="아이디를 입력하세요!")
	String loginName;
	
	@Null(message="비밀번호를 입력하세요!")
	String password;
	
	@Null(message="이름을 입력하세요!")
	String name;
	
	@Null(message="부서를 입력하세요!")
	@Email(message="형식에 맞게 입력해주세요!")
	String email;
	
	boolean admin;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
	
	

}
