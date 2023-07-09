package movie_server;

import java.io.Serializable;

public class CustomerVO implements Serializable {
	// 회원아이디, 비밀번호, 회원이름, 회원 생일, 회원 전화번호, 관리자 여부(1:관리자, 0: 회원), 탈퇴 여부(0: 탈퇴안함, 1: 탈퇴함)
	private String cust_id, cust_password, cust_name, cust_birth, admin_yn, delete_yn, cust_phone;
	
	// 회원 전화번호, 회원 보유 포인트
	private int point;

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public String getCust_password() {
		return cust_password;
	}

	public void setCust_password(String cust_password) {
		this.cust_password = cust_password;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCust_birth() {
		return cust_birth;
	}

	public void setCust_birth(String cust_birth) {
		this.cust_birth = cust_birth;
	}

	public String getAdmin_yn() {
		return admin_yn;
	}

	public void setAdmin_yn(String admin_yn) {
		this.admin_yn = admin_yn;
	}

	public String getDelete_yn() {
		return delete_yn;
	}

	public void setDelete_yn(String delete_yn) {
		this.delete_yn = delete_yn;
	}

	public String getCust_phone() {
		return cust_phone;
	}

	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	
	
}