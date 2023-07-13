package movie_server;

import java.io.Serializable;

public class LoginInfo_VO implements Serializable{
	// 회원 아이디, 회원이름
	private String cust_id, cust_name;
	// 포인트
	private int point;
	
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
}
