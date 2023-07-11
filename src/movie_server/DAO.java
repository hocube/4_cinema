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
	// customer 테이블 전체보기
	// select , 결과는 여러개 , 파라미터가 없음
	public static List<CustomerVO> getList() {
		List<CustomerVO> list = null;
		// selectList() : 결과가 하나이상일때
		// selectOne() : 반드시 결과가 하나일때
		// 파라미터가 있는 메서드와 파라미터가 없는메서드로 나눈다.
		// 파라미터가 있는 메서드 : selectList("mepper의 id",파라미터);
		// 파라미터가 없는 메서드 : selectList("mepper의 id")
		list = getSession().selectList("custList");
		return list;
	}

	// select, 결과는 하나, 파라미터 있음(String)
	public static CustomerVO getOne(String cust_id) {
		CustomerVO vo = getSession().selectOne("custOne", cust_id);
		return vo;
	}

	// insert, delete, update 결과 int, 파라미터있음
	// 반드시 commit를 해야 된다.
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
	
	// 회원가입시 customer테이블에 정보를 삽입할 것이다 = 혜지
	public static int signup_getIns(CustomerVO vo) throws Exception{
		int result = 0;
		if(!(vo.getCust_id().isEmpty())) {
			result = getSession().insert("customerIns", vo);
			ss.commit();
			return result;
		}else
			return 0;
	}
	// 회원가입시 아이디중복 체크 = 혜지
		public static int getIdChk(String cust_id) {
			int result = 0;
			result = getSession().selectOne("idchk", cust_id);
			return result;
		}
	

	// 로그인한 회원의 회원정보 가져오기
	// 지호
	public static CustomerVO getLogin(CustomerVO c_vo) {
		System.out.println("DAO getLogin");
		CustomerVO result = getSession().selectOne("login", c_vo);
		return result;
	}

	// 로그인한 회원 정보 Login_info에 넣기
	// 지호
	public static int loginInfoInsert(CustomerVO c_vo) {
		System.out.println("DAO loginInfoInsert : " + c_vo.getCust_name());
		int result = getSession().insert("loginInfoInsert", c_vo);
		ss.commit();
		return result;
	}

//	public static List<CustomerVO> getLogin(List<CustomerVO> loginchk) {
//		List<CustomerVO> vo = getSession().selectOne("login", loginchk);
//		return vo;
//	}

	/*
	 * public static List<Loginout_VO> getList(){ List<Loginout_VO> list = null; //
	 * selectList() : 결과가 하나이상일때 // selectOne() : 반드시 결과가 하나일때 // 파라미터가 있는 메서드와
	 * 파라미터가 없는메서드로 나눈다. // 파라미터가 있는 메서드 : selectList("mepper의 id",파라미터); // 파라미터가
	 * 없는 메서드 : selectList("mepper의 id") list = getSession().selectList("custList");
	 * return list; } // select, 결과는 하나, 파라미터 있음(String) public static Loginout_VO
	 * getOne(String cust_id) { Loginout_VO vo = getSession().selectOne("custOne",
	 * cust_id); return vo; }
	 */

	// 지호
	// TICKET 테이블에 예매 정보 삽입
	public static int getInsert(Pay_VO pay_vo) {
		int result = getSession().insert("movieinsert", pay_vo);
		ss.commit();
		return result;
	}

	// Login_info에서 현재 로그인 한 회원정보 가져오기
	public static CustomerVO getMemberLogin() {
		CustomerVO result = getSession().selectOne("getMemberLogin");
		System.out.println("[DAO] getMemberLogin");
		return result;
	}

	// 해당 회원의 잔여포인트 가져오기
	public static int getRemainingPoints(String custid) {
		System.out.println("[DAO] getRemainingPoints" + custid);
		int chargepoint = getSession().selectOne("getRemainingPoints", custid);
		return chargepoint;
	}

	// 포인트 충전
	public static void updatePoint(Pay_VO pay_vo) {
		System.out.println("Updating point: " + pay_vo.getPoint());
		System.out.println("Updating Cust_id: " + pay_vo.getCust_id());

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

	//로그인한 회원의 예매 내역 리스트
	public static List<MobileTicket_VO> getTicketList(String custid) {
		System.out.println("104의 DAO");
		List<MobileTicket_VO> m_list = getSession().selectList("getTicketList", custid);
		return m_list;
	}

	// 리스트에서 선택된 티켓 정보 가져오기
	/* public List<MobileTicket_VO> getTicketByRow() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String ticketNum = model.getValueAt(row, 0).toString();
		String movieName = model.getValueAt(row, 1).toString();
		String movieDate = model.getValueAt(row, 2).toString();
		String startTime = model.getValueAt(row, 3).toString();
		String theaterId = model.getValueAt(row, 4).toString();
		String theaterSeat = model.getValueAt(row, 5).toString();

		MobileTicket_VO ticket = new MobileTicket_VO();
		ticket.setTicket_num(Integer.parseInt(ticketNum));
		ticket.setMovie_name(movieName);
		ticket.setMovie_date(movieDate);
		ticket.setStart_time(startTime);
		ticket.setTheater_id(theaterId);
		ticket.setTheater_seat(theaterSeat);
		
		 List<MobileTicket_VO> vo = getSession().selectList("movieinsert");
		return vo;
	}  */

	// 예매 취소하기
	public int cancelTicket(int ticket_num) {
		int result = getSession().delete("cancelTicket", ticket_num);
		return result;
	}

	// 지혜
	// mapper 로가서 prn_time sql문 실행하고 값을 가져오자.
		public static List<TicketBox_VO> getStart_time() {
			System.out.println("DAO와서 sql문 하고올게요11");
			List<TicketBox_VO> stimeList = getSession().selectList("stimeList");
			System.out.println("mapper다녀왔어요11");
			return stimeList;
		}

		public static List<TicketBox_VO> getEnd_time() {
			System.out.println("DAO와서 sql문 하고올게요22");
			List<TicketBox_VO> etimeList = getSession().selectList("etimeList");
			System.out.println("mapper다녀왔어요22");
			return etimeList;

		}

		public static List<TicketBox_VO> getMovieTimes(String movieTitle) {
			List<TicketBox_VO> vo = getSession().selectList("getMovieTimes", movieTitle);

			return vo;
		}

		public static List<TicketBox_VO> getMovie_name() {
			System.out.println("DAO와서 sql문 하고올게요33");

			List<TicketBox_VO> movieList = getSession().selectList("movieList");
			System.out.println("mapper다녀왔어요33" + movieList);
			return movieList;
		}
		
	
}