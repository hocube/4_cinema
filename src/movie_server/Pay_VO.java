package movie_server;

import java.io.Serializable;
import java.sql.Date;

public class Pay_VO implements Serializable{
	private String movie_id, cust_id, movie_name, theater_id, movie_date, start_time, end_time, theater_seat;
	private String point_date, charge_point, used_point, remaining_point, cust_name;
	private int ticket_num, point;
	
	
	public String getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getTheater_id() {
		return theater_id;
	}
	public void setTheater_id(String theater_id) {
		this.theater_id = theater_id;
	}
	public String getMovie_date() {
		return movie_date;
	}
	public void setMovie_date(String movie_date) {
		this.movie_date = movie_date;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getTheater_seat() {
		return theater_seat;
	}
	public void setTheater_seat(String theater_seat) {
		this.theater_seat = theater_seat;
	}
	public String getPoint_date() {
		return point_date;
	}
	public void setPoint_date(String point_date) {
		this.point_date = point_date;
	}
	public String getCharge_point() {
		return charge_point;
	}
	public void setCharge_point(String charge_point) {
		this.charge_point = charge_point;
	}
	public String getUsed_point() {
		return used_point;
	}
	public void setUsed_point(String used_point) {
		this.used_point = used_point;
	}
	public String getRemaining_point() {
		return remaining_point;
	}
	public void setRemaining_point(String remaining_point) {
		this.remaining_point = remaining_point;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public int getTicket_num() {
		return ticket_num;
	}
	public void setTicket_num(int ticket_num) {
		this.ticket_num = ticket_num;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
}