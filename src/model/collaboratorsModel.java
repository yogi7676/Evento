package model;

import java.util.Map;

public class collaboratorsModel {
String companyId;
String companyName;
String address;
String phone;
String email;
String imageUrl;
Map<String,Boolean> compatibilityMap;
String userId;
String currentDateTime; 
byte[] image;

public collaboratorsModel(String companyId, String companyName, String address, String phone, String email,
		String imageUrl, Map<String, Boolean> compatibilityMap, String userId, String currentDateTime) {
	super();
	this.companyId = companyId;
	this.companyName = companyName;
	this.address = address;
	this.phone = phone;
	this.email = email;
	this.imageUrl = imageUrl;
	this.compatibilityMap = compatibilityMap;
	this.userId = userId;
	this.currentDateTime = currentDateTime;
}

public collaboratorsModel(String companyId, String companyName, String address, String phone, String email,
		Map<String, Boolean> compatibilityMap, String userId, String currentDateTime, byte[] image) {
	super();
	this.companyId = companyId;
	this.companyName = companyName;
	this.address = address;
	this.phone = phone;
	this.email = email;
	this.compatibilityMap = compatibilityMap;
	this.userId = userId;
	this.currentDateTime = currentDateTime;
	this.image = image;
}



public byte[] getImage() {
	return image;
}

public String getCompanyId() {
	return companyId;
}


public String getCompanyName() {
	return companyName;
}


public String getAddress() {
	return address;
}

 
public String getPhone() {
	return phone;
}


public String getEmail() {
	return email;
}


public String getImageUrl() {
	return imageUrl;
}


public Map<String, Boolean> getCompatibilityMap() {
	return compatibilityMap;
}


public String getUserId() {
	return userId;
}


public String getCurrentDateTime() {
	return currentDateTime;
}

}
