package movie_server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Protocol implements Serializable{
	/*
		cmd 0 -> 종료(접속해제, 로그아웃..?)
		
		cmd 501 -> 로그인
		cmd 502 -> 회원가입
		cmd 503 -> 아이디중복(result, 0 = 없음 / 1이상 = 있음)
		
		100: 종료
	 	101: 로그인 한 회원 찾기 (나중에)
	 	102: 로그인한 회원 잔여포인트 가져오기
	 	103: 결제 완료 후 티켓 INSERT
	 	104: 현재 로그인 회원의 티켓 리스트 출력
	 	
	 	302: 상영시간표 시작 시간 출력 
	 	303 :잔여포인트

		
	*/
	int cmd;
	int result;
	String msg;
	
	List<CustomerVO> c_list;
	CustomerVO c_vo;	
	
	Pay_VO p_vo;
	MobileTicket_VO m_vo;
	ArrayList<MobileTicket_VO> p_list;
	
	List<TicketBox_VO> t_list;
	TicketBox_VO t_vo;
	List<Seat_VO> s_list;
	
	public int getCmd() {
		return cmd;
	}
	public void setCmd(int cmd) {
		this.cmd = cmd;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<CustomerVO> getC_list() {
		return c_list;
	}
	public void setC_list(List<CustomerVO> c_list) {
		this.c_list = c_list;
	}
	public CustomerVO getC_vo() {
		return c_vo;
	}
	public void setC_vo(CustomerVO c_vo) {
		this.c_vo = c_vo;
	}
	public Pay_VO getP_vo() {
		return p_vo;
	}
	public void setP_vo(Pay_VO p_vo) {
		this.p_vo = p_vo;
	}
	public MobileTicket_VO getM_vo() {
		return m_vo;
	}
	public void setM_vo(MobileTicket_VO m_vo) {
		this.m_vo = m_vo;
	}
	public ArrayList<MobileTicket_VO> getP_list() {
		return p_list;
	}
	public void setP_list(ArrayList<MobileTicket_VO> p_list) {
		this.p_list = p_list;
	}
	public List<TicketBox_VO> getT_list() {
		return t_list;
	}
	public void setT_list(List<TicketBox_VO> t_list) {
		this.t_list = t_list;
	}
	public TicketBox_VO getT_vo() {
		return t_vo;
	}
	public void setT_vo(TicketBox_VO t_vo) {
		this.t_vo = t_vo;
	}
	public List<Seat_VO> getS_list() {
		return s_list;
	}
	public void setS_list(List<Seat_VO> s_list) {
		this.s_list = s_list;
	}
	
}
