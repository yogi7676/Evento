package model;

public class modelUser {
String username;
String email;
String password;
String usertype;

public modelUser(String username, String email, String password,String usertype) {
	super();
	this.username = username;
	this.email = email;
	this.password = password;
	this.usertype=usertype;
}

public String getUsername() {
	return username;
}

public String getEmail() {
	return email;
}

public String getPassword() {
	return password;
}

public String getUsertype() {
	return usertype;
}
}
