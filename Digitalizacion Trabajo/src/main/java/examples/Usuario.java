package examples;

import java.io.Serializable;

public class Usuario implements Serializable {

	private String name, passwd;
	private boolean admin;

	public Usuario(String name, String passwd, boolean admin) {
		super();
		this.name = name;
		this.passwd = passwd;
		this.admin = admin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


	@Override
	public String toString() {
		return "Usuario [name=" + name + ", passwd=" + passwd + ", admin=" + admin + "]";
	}

}

