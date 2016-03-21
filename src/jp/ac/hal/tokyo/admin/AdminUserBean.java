package jp.ac.hal.tokyo.admin;

public class AdminUserBean {
	
	//パラメータ設定
	private String user;
	private String passwd;
	private boolean err;
	private String err_msg;
	
	private int customer_id;
	private String user_id;
	private String password;
	private String email;
	private String last_name;
	private String first_name;
	private String birth_date;
	private int sex;
	private String postal_code;
	private String address;
	private String recode_date;
	
	/***************
	  setter()メソッド 
	 ***************/
	public void setUser(String user) {
		if(user == null || user.isEmpty()) {
			setErr(true);
			setErr_msg("管理者名が入力されていません");
		} else {
			setErr(false);
			this.user = user;
		}
	}
	
	public void setPasswd(String passwd) {
		if (passwd == null || passwd.isEmpty()) {
			setErr(true);
			setErr_msg("パスワードが入力されていません");
		} else {
			setErr(false);
			this.passwd = passwd;
		}
	}
	
	private void setErr(boolean err) {
		this.err = err;
	}
	
	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}
	
	/***************
	  getter()メソッド 
	 ***************/
	public String getUser() {
		return this.user;
	}
	
	public String getPasswd() {
		return this.passwd;
	}
	
	public boolean getErr() {
		return this.err;
	}
	
	public String getErr_msg() {
		return this.err_msg;
	}
	
	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRecode_date() {
		return recode_date;
	}

	public void setRecode_date(String recode_date) {
		this.recode_date = recode_date;
	}


}
