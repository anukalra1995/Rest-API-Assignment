package com.UserProductSecurity.Model;


import javax.persistence.*;

@Entity
@Table(name="users")
public class Users {
	
	@Id
	@Column(name="userid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="fname" , nullable = false)
	private String fname;
	
	@Column(name="lname")
	private String lname;
	
	@Column(name="username", nullable = false, unique = true)
	private String username;
	
	@Column(name="pwd", nullable = false)
	private String pwd;
	
    @Column(name = "role", length = 20)
    private String role;
    
	public Users() {
		
		super();
		
	}

	public Users(long id, String fname, String lname, String username, String pwd, String role) {
		
		super();
		
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.pwd = pwd;
		this.role = role;
		
	}
			
	public long getId() {
		return id;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public String getUsername() {
		return username;
	}

	public String getPwd() {
		return pwd;
	}

	public String getRole() {
		return role;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", fname=" + fname + ", lname=" + lname + ", username=" + username +
						", pwd=" + pwd + ", role=" + role + "]";
	}
	
}


/*
 * 
 * id = 0;
		firstname = "";
		lname = "";
		username = "";
		email = "";
		pwd = "";
		role = "";
		loggedIn = false;
@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
private Set<Roles> role = new HashSet<>();

public Set<Roles> getRole() {
	return role;
}

public void setRole(Set<Roles> role) {
	this.role = role;
	System.out.println(role);
}
*/
