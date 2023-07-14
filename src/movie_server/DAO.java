package movie_server;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.ibatis.session.SqlSession;

//DB처리하는 메서드들을 가지고 있는 클래스
public class DAO {
	// 실제 사용하는 클래스 : SqlSession
	private static SqlSession ss;

	// 싱글턴 패턴 (동기화처리) : 프로그램이 종료될 때 까지 한번 만들어진 객체를 재 사용한다.
	private synchronized static SqlSession getSession() {
		if (ss == null) {
			ss = DBService.getFactory().openSession();
		}
		return ss;
	}

	// DB처리하는 메서드들
	public static List<CustomerVO> getList() {
		List<CustomerVO> list = null;
		list = getSession().selectList("custList");
		return list;
	}

	public static CustomerVO getOne(String cust_id) {
		CustomerVO vo = getSession().selectOne("custOne", cust_id);
		return vo;
	}

	public static int getIns(CustomerVO vo) {
		int result = 0;
		if (!(vo.getCust_id().isEmpty())) {
			result = getSession().insert("custins", vo);
			ss.commit();
			return result;
		} else
			return 0;
	}

	public static int getDelete(CustomerVO vo) {
		int result = getSession().delete("custdel", vo);
		ss.commit();
		return result;
	}

	public static int getUpdate(CustomerVO vo) {
		int result = getSession().update("custupdate", vo);
		ss.commit();
		return result;
	}

	// 회원가입시 customer테이블에 정보 삽입
	public static int signup_getIns(CustomerVO vo) throws Exception {
		int result = 0;
		if (!(vo.getCust_id().isEmpty())) {
			result = getSession().insert("customerIns", vo);
			ss.commit();
			return result;
		} else
			return 0;
	}

	// 회원가입 시 아이디중복 체크
	public static int getIdChk(String cust_id) {
		int result = 0;
		result = getSession().selectOne("idchk", cust_id);
		return result;
	}

	// 로그인한 회원의 회원정보 가져오기
	public static CustomerVO getLogin(CustomerVO c_vo) {
		CustomerVO result = getSession().selectOne("login", c_vo);
		return result;
	}

	// 로그인한 회원 정보 Login_info에 넣기
	public static int loginInfoInsert(CustomerVO c_vo) {
		int result = getSession().insert("loginInfoInsert", c_vo);
		ss.commit();
		return result;
	}

	// 로그아웃하기(lpgininfo 테이블에 로그인한 회원 정보 지우기)
	public static int loginInfoDelete(String cust_id) {
		int result = getSession().delete("logout", cust_id);
		ss.commit();
		return result;
	}

	// 마이페이지 비밀번호 변경
	public static int changePwd(CustomerVO c_vo) {
		int result = getSession().update("changepwd", c_vo);
		ss.commit();
		return result;
	}

	// 마이페이지 전화 번호 변경
	public static int changePhoneNum(CustomerVO c_vo) {
		int result = getSession().update("changephonenum", c_vo);
		ss.commit();
		System.out.println("DAO result:" + result);
		return result;
	}

	// 탈퇴
	public static void custDeleteAndLogout(CustomerVO c_vo) {
		try {
			getSession().update("memberdelete", c_vo);
			getSession().delete("custlogout", c_vo);
			ss.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// TICKET 테이블에 예매 정보 삽입
	public static int getInsert(Pay_VO pay_vo) {
		int result = getSession().insert("movieinsert", pay_vo);
		ss.commit();
		return result;
	}

	// Login_info에서 현재 로그인 한 회원정보 가져오기
	public static CustomerVO getMemberLogin() {
		CustomerVO result = getSession().selectOne("getMemberLogin");
		return result;
	}

	// 해당 회원의 잔여포인트 가져오기
	public static int getRemainingPoints(String custid) {
		int chargepoint = getSession().selectOne("getRemainingPoints", custid);
		return chargepoint;
	}

	// 포인트 충전
	public static void updatePoint(Pay_VO pay_vo) {
		try {
			getSession().update("updateCustomerPoint", pay_vo);
			getSession().update("updateLoginInfoPoint", pay_vo);

			// [0709 지호] Point 테이블 업데이트 안되는 상태
			getSession().update("updateRemainingPoint", pay_vo);
			ss.commit(); // commit 호출
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 로그인한 회원의 예매 내역 리스트
	public static List<MobileTicket_VO> getTicketList(String custid) {
		List<MobileTicket_VO> m_list = getSession().selectList("getTicketList", custid);
		return m_list;
	}

	// 예매 취소하기
	public static int cancelTicket(int ticket_num) {
		int result = getSession().delete("cancelTicket", ticket_num);
		ss.commit();
		return result;
	}

	// 매표소의 영화이름 눌렀을때, 시간 뜨게하는 거 (시작)
	public static List<TicketBox_VO> getStart_time() {
		List<TicketBox_VO> stimeList = getSession().selectList("stimeList");
		return stimeList;
	}

	// 매표소의 영화이름 눌렀을때, 시간 뜨게하는 거 (종료)
	public static List<TicketBox_VO> getEnd_time() {
		List<TicketBox_VO> etimeList = getSession().selectList("etimeList");
		return etimeList;

	}

	// 매표소의 영화이름 눌렀을때, 시간 뜨게하는 거 (시작&종료)
	public static List<TicketBox_VO> getMovieTimes(String movieTitle) {
		List<TicketBox_VO> vo = getSession().selectList("getMovieTimes", movieTitle);
		return vo;
	}

	// 빠른예매 눌렀을때, DB테이블의 영화목록 갖고오기
	public static List<TicketBox_VO> getMovie_name() {
		List<TicketBox_VO> movieList = getSession().selectList("movieList");
		return movieList;
	}

	// 클릭한 포스터만의 영화이름으로 불러오기.
	public static TicketBox_VO getMovieChoice(String movie_name) {
		TicketBox_VO one_movie = getSession().selectOne("getMovieChoice", movie_name);
		return one_movie;
	}

	public static int getDeleteresult(String dID) {
		int result = getSession().delete("custdelete", dID);
		ss.commit();
		return result;
	}

	public static List<CustomerVO> getCustOne(String sId) {
		List<CustomerVO> list = null;
		list = getSession().selectList("custone", sId);
		return list;
	}

	public static int getAdminAddresult(String aID) {
		int result = getSession().update("adminadd", aID);
		ss.commit();
		return result;
	}

	public static int getChangInfoAd(CustomerVO chvo) {
		int result = getSession().update("changeinfo", chvo);
		ss.commit();
		return result;
	}

	public static int getM_movieDelete(String movie_id) {
		int result = getSession().delete("m_movieDelete", movie_id);
		ss.commit();
		return result;
	}

	public static List<M_movieVO> getMMsList() {
		List<M_movieVO> list = null;
		list = getSession().selectList("m_mslist");
		return list;

	}

}