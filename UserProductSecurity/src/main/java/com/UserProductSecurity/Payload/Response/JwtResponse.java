package com.UserProductSecurity.Payload.Response;

public class JwtResponse {
	
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String roles;
	
	public JwtResponse(String token, Long id, String username, String roles) {
		super();
		this.token = token;
		this.id = id;
		this.username = username;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public String getTokenType() {
		return type;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getRoles() {
		return roles;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setTokenType(String tokentype) {
		this.type = tokentype;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
}
