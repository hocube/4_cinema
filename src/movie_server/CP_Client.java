package movie_server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class CP_Client extends Thread{
	Socket s;
	Server_book server;

	// 객체 직렬화(objectInputStream, objectOutputStream)
	ObjectInputStream in;
	ObjectOutputStream out;
	String ip;
	int result;
	
	public CP_Client(Socket s, Server_book server) {
		this.s = s;
		this.server = server;
		try {
			in = new ObjectInputStream(s.getInputStream());
			out = new ObjectOutputStream(s.getOutputStream());
			ip = s.getInetAddress().getHostAddress();
		} catch (Exception e) {
			System.out.println("서버in out 오류" + e);
		}
	}

	@Override
	public void run() {
		esc:while(true) {
			try {
				Object obj = in.readObject();
				if(obj!=null) {
					Protocol p = (Protocol) obj;
					switch(p.getCmd()) {
					case 0:	// 종료
						out.writeObject(p);
						out.flush();
						break esc; // 접속 해제
					case 101: // 현재 로그인한 회원 정보 조회
						CustomerVO loginMemberInfo = DAO.getMemberLogin();
                    	p.setC_vo(loginMemberInfo);
						out.writeObject(p);
						out.flush();
						break;
                    case 102: // 포인트 충전
                    	Pay_VO p_vo = p.getP_vo();
                    	DAO.updatePoint(p_vo);
						out.writeObject(p);
						out.flush();
                        break;

                    case 104: // 티켓 리스트 띄우기
                    	List<MobileTicket_VO> m_list = DAO.getTicketList(p.m_vo.getCust_id());
                    	p.setM_list(m_list);
						out.writeObject(p);
						out.flush();
                    	break;
                    case 105: // 예매 취소
                    	int result = DAO.cancelTicket(p.m_vo.getTicket_num());
                    	p.setResult(result);
						out.writeObject(p);
						out.flush();
                    	break;
                    case 301 :   
                    	//영화 목록 가져오기
                        List<TicketBox_VO> t_list = DAO.getMovie_name(); // 영화 목록을 DB에서 가져옴                  
                        p.setT_list(t_list);
                        for (TicketBox_VO k : t_list) {
						}
                        //영화 이름만 추출하여 문자열 배열로 변환                  
                        out.writeObject(p);
                        out.flush();
                         break;
                     case 302 :
                    	//영화 시간 갖고오기                       
                         List<TicketBox_VO> movieTimes = DAO.getMovieTimes(getName());
                         p.setT_list(movieTimes);
                         out.writeObject(p);
                         out.flush();
                         break;
                     case 303:
                    	//포스터 클릭한 영화 이름 갖고오기
                    	 boolean alreadyExecuted = false;
                    	 if(alreadyExecuted) {
                    		 break;  //계속 무한 루프 돌아서, 이 case가 실행되었으면 멈추라는 뜻의 if문
                    	 }                 	
                    	TicketBox_VO one_movie = DAO.getMovieChoice(p.t_vo.getMovie_name());
                     	p.setT_vo(one_movie);
 						out.writeObject(p);
 						out.flush();
                     	break;		
                     case 401: // 관리자 전체보기
                     	 List<CustomerVO> ad_clist = DAO.getList(); //고객정보 전체 목록
                     	 p.setAd_clist(ad_clist);
                     	 out.writeObject(p); 
                     	 out.flush();
                     	break;
                      case 402: // 관리자 회원정보 삭제
                     	 String d_ID = p.getDel_id(); 
                     	 int d_result = DAO.getDeleteresult(d_ID);
                     	 p.setResult(d_result); 
                     	 out.writeObject(p);
                     	 out.flush();
                     	 break;
                      case 403: // 관리자 회원정보 ID로 검색
                     	 String o_ID = p.getDel_id(); 
                     	 List<CustomerVO> ad_onelist = DAO.getCustOne(o_ID);
                     	 p.setAd_clist(ad_onelist);
                     	 out.writeObject(p);
                     	 out.flush();
                     	 break;
                      case 404: // 관리자 권한 추가
                     	 String add_ID = p.getDel_id();
                     	 int add_result = DAO.getAdminAddresult(add_ID);
                     	 p.setResult(add_result);
                     	 out.writeObject(p);
                     	 out.flush();
                     	 break;
                      case 405: // 관리자 수정 패널에 고객 정보 불러오기
                    	 String ch_ID = p.getDel_id(); 
                    	 List<CustomerVO> ad_ch_list = DAO.getCustOne(ch_ID); 
                    	 p.setAd_clist(ad_ch_list);
                    	 out.writeObject(p);
                    	 out.flush();
                    	 break;
                      case 406: // 관리자 수정할 데이터 DB 수정하기
                    	 int ch_result = DAO.getChangInfoAd(p.getAdminChange_vo()); 
                    	 p.setResult(ch_result); 
                    	 out.writeObject(p);
                    	 out.flush();
                    	 break;
					case 501: // 로그인
						CustomerVO c_vo = p.getC_vo();
						p.setC_vo(DAO.getLogin(c_vo));
						if(DAO.getLogin(c_vo) != null && p.getC_vo().getDelete_yn().equals("0")) {
							DAO.loginInfoInsert(p.getC_vo());
						}
						out.writeObject(p);
						out.flush();
						break;					
					case 502:	// 회원가입
						CustomerVO vo = p.getC_vo();
						int result502 = DAO.signup_getIns(vo);
						if (result502 > 0) {
							Protocol p502 = new Protocol();
							p502.setCmd(502);
							p502.setResult(result502);
							out.writeObject(p502);
							out.flush();
						}
						break;
					case 503: // 아이디 중복 확인
						int result503 = DAO.getIdChk(p.getC_vo().getCust_id());
						
						Protocol p503 = new Protocol();
						p503.setCmd(503);
						p503.setResult(result503);
						out.writeObject(p503);
						out.flush();
						break;
					case 504: // 로그아웃
						int result504 = DAO.loginInfoDelete(p.l_vo.getCust_id());
						p.setResult(result504);
						out.writeObject(p);
						out.flush();
						break;
					case 505: // 마이페이지에서 비밀번호 변경
						int result505 = DAO.changePwd(p.c_vo);
						p.setResult(result505);
						out.writeObject(p);
						out.flush();
						break;
					case 506: // 마이페이지에서 핸드폰 번호 변경
						int result506 = DAO.changePhoneNum(p.c_vo);
						p.setResult(result506);
						out.writeObject(p);
						out.flush();
						break;
					case 507: // 탈퇴
						CustomerVO c_vo1 = p.getC_vo();
						DAO.custDeleteAndLogout(c_vo1);
						out.writeObject(p);
						out.flush();
						break;
					case 601: // 영화 정보
						List<M_movieVO> ad_mlist = DAO.getMMsList();
						p.setMslist(ad_mlist);
						out.writeObject(p);
						out.flush();
						break;
					case 603: // 영화 삭제
						String delMovie = p.getDelMovie();
						int dm_result = DAO.getM_movieDelete(delMovie);
						p.setResult(dm_result);
		                out.writeObject(p);
		                out.flush();
		                break;              	
					}
				}
			} catch (Exception e) {
			}
		}
	}
}