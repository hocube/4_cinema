package movie_server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Protocol implements Serializable{

	int cmd;
	int result;
	String msg;
	String result_st;
	
	List<CustomerVO> c_list;
	CustomerVO c_vo;	
	
	Pay_VO p_vo;
	MobileTicket_VO m_vo;
	List<MobileTicket_VO> m_list;
	
	List<TicketBox_VO> t_list;
	TicketBox_VO t_vo;
	
	LoginInfo_VO l_vo;
	
	List<CustomerVO> ad_clist; 
	String del_id;
	CustomerVO adminChange_vo;
	
	String delMovie;
	List<M_movieVO> mslist;
	
	
	public List<CustomerVO> getAd_clist() {
		return ad_clist;
	}
	public void setAd_clist(List<CustomerVO> ad_clist) {
		this.ad_clist = ad_clist;
	}
	public String getDel_id() {
		return del_id;
	}
	public void setDel_id(String del_id) {
		this.del_id = del_id;
	}
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
	public List<MobileTicket_VO> getM_list() {
		return m_list;
	}
	public void setM_list(List<MobileTicket_VO> m_list) {
		this.m_list = m_list;
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
	public String getResult_st() {
		return result_st;
	}
	public void setResult_st(String result_st) {
		this.result_st = result_st;
	}
	public LoginInfo_VO getL_vo() {
		return l_vo;
	}
	public void setL_vo(LoginInfo_VO l_vo) {
		this.l_vo = l_vo;
	}
	public CustomerVO getAdminChange_vo() {
		return adminChange_vo;
	}
	public void setAdminChange_vo(CustomerVO adminChange_vo) {
		this.adminChange_vo = adminChange_vo;
	}
	public String getDelMovie() {
		return delMovie;
	}
	public void setDelMovie(String delMovie) {
		this.delMovie = delMovie;
	}
	public List<M_movieVO> getMslist() {
		return mslist;
	}
	public void setMslist(List<M_movieVO> mslist) {
		this.mslist = mslist;
	}
	
}