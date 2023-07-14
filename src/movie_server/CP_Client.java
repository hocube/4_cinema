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
                    case 103: // 결제 완료 후 티켓 INSERT
                    	System.out.println("===CP_Client의 case 103===");
//                    	Pay_VO p_vo = p.getP_vo();
//                    	result = DAO.getInsert(p_vo);
//                    	p.setResult(result); // DB 삽입 작업의 결과를 설정
//                    	System.out.println(result + "예매 완료!");
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
						System.out.println("CP의 105번");
                    	break;
                    case 301 :   
                    	//영화목록가져오라는 cmd   -지혜
                    	  System.out.println("CP9 301입니다.");
                        List<TicketBox_VO> t_list = DAO.getMovie_name(); // 영화 목록을 DB에서 가져옴                  
                        System.out.println("301담았다!!");
                        p.setT_list(t_list);
                        for (TicketBox_VO k : t_list) {
							System.out.println(k);
							System.out.println("301마지막, 여기까지왔는가? 지건!");
						}
                        //영화 이름만 추출하여 문자열 배열로 변환                  
                        out.writeObject(p); // 클라이언트에게 프로토콜 전송
                        out.flush();
                         break;
                     case 302 :
                    	//영화 시간 갖고오는 cmd   -지혜
                         System.out.println("CP9 302입니다.");                         
                         List<TicketBox_VO> movieTimes = DAO.getMovieTimes(getName());
                         System.out.println("담아서 메인인으로 보내자");
                         p.setT_list(movieTimes);
                         out.writeObject(p);
                         out.flush();
                         System.out.println("302마지막이올시다!");
                         break;
                     case 303:
                    	//포스터 클릭한 영화 이름 갖고오기  -지혜
                    	 boolean alreadyExecuted = false;
                    	 if(alreadyExecuted) {
                    		 break;  //계속 무한 루프 돌아서, 이 case가 실행되었으면 멈추라는 뜻의 if문
                    	 }
                    	System.out.println("CP9 303입니다.");                     	
                    	TicketBox_VO one_movie = DAO.getMovieChoice(p.t_vo.getMovie_name());
                     	p.setT_vo(one_movie);
 						out.writeObject(p);
 						out.flush();
 						System.out.println("303마지막이올시다! - 303호사는 쿠잔");
                     	break;		
                     	//민서
                     case 401: // 전체보기 요청
                     	 List<CustomerVO> ad_clist = DAO.getList(); //고객정보 전체 목록
                     	 p.setAd_clist(ad_clist);
                     	 out.writeObject(p); // 요청한게 Sign_in을 상속받은 패널의 프로토콜이므로 받을때 sign_in으로 간다
                     	 out.flush();
                     	break;
                      case 402: // 회원정보 삭제
                     	 String d_ID = p.getDel_id(); // 프로토콜로 받은 삭제할 아이디 변수에 저장
                     	 int d_result = DAO.getDeleteresult(d_ID);// DAO로 sql실행하고 나온 정보(delete 결과는 int)를 저장;
                     	 p.setResult(d_result); // 정보는 프로토콜을 통해 Sign_in으로 보냄 
                     	 out.writeObject(p);
                     	 out.flush();
                     	 break;
                      case 403: //회원정보 ID로 검색
                     	 String o_ID = p.getDel_id(); //고객 1명의 정보를 select할 쿼리문 조건의 cust_id 저장
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
                      case 405: // 수정 패널에 고객 정보 불러오기
                    	 String ch_ID = p.getDel_id(); 
                    	 List<CustomerVO> ad_ch_list = DAO.getCustOne(ch_ID); // 
                    	 p.setAd_clist(ad_ch_list);
                    	 out.writeObject(p);
                    	 out.flush();
                    	 break;
                      case 406: // 수정할 데이터 DB 수정하기
                    	 int ch_result = DAO.getChangInfoAd(p.getAdminChange_vo()); //프로토콜로 받은 vo를 get에서 세팅
                    	 p.setResult(ch_result); // 반환된 결과 int를 프로토콜담음
                    	 out.writeObject(p);
                    	 out.flush();
                    	 break;
					case 501: // 로그인
						//지호
						// 1. CustomerVo에 Protocol의 c_vo를 세팅.(Sign_in에서 입력받은 id/pw)
						CustomerVO c_vo = p.getC_vo();
						// 2. DB에서 받아온 결과값을 Protocol의 c_vo에 세팅.(Sign_in에서 입력받은 id/pw와 일치하는 회원정보)
						p.setC_vo(DAO.getLogin(c_vo));
						if(DAO.getLogin(c_vo) != null && p.getC_vo().getDelete_yn().equals("0")) {
							// 로그인 성공시에만 Login_info 테이블에 삽입
							DAO.loginInfoInsert(p.getC_vo());
						}
						out.writeObject(p);
						out.flush();
						break;					
					case 502:	// 회원가입
						System.out.println("cpclient 502 도촥~");
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
					case 503:	// 아이디 중복 확인
						System.out.println("cp_client cmd503 왔음");
						
						int result503 = DAO.getIdChk(p.getC_vo().getCust_id());
						System.out.println(result503);
						
						Protocol p503 = new Protocol();
						p503.setCmd(503);
						p503.setResult(result503);
						out.writeObject(p503);
						out.flush();
						break;
					case 504:	// 로그아웃하기
						System.out.println("cp_client cmd504 왔음");
						
						int result504 = DAO.loginInfoDelete(p.l_vo.getCust_id());
						p.setResult(result504);
						out.writeObject(p);
						out.flush();
						System.out.println("cpc504나가기");
						break;
					case 505: // 마이페이지에서 비밀번호 변경
						int result505 = DAO.changePwd(p.c_vo);
						System.out.println(result505);
						p.setResult(result505);
						out.writeObject(p);
						out.flush();
						break;
					case 506: // 마이페이지에서 핸드폰 번호 변경
						System.out.println("cp_client cmd 506 도착");
						int result506 = DAO.changePhoneNum(p.c_vo);
						System.out.println("DAO 다녀옴");
						System.out.println(result506);
						p.setResult(result506);
						out.writeObject(p);
						out.flush();
						System.out.println("cpc506나가기");
						break;
					case 507: // 탈퇴
						System.out.println("cp_client cmd 507 도착");
						CustomerVO c_vo1 = p.getC_vo();
						DAO.custDeleteAndLogout(c_vo1);
						out.writeObject(p);
						out.flush();
						break;
					case 601: // 영화 정보
						System.out.println("cp_client cmd601");
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

/*
// 마이바티스 셋팅하기 
// 1. config.xml  만들기 - DB 접속
// 2. mapper.xml 만들기 - 실제 SQL 작성하는파일 (실제 DB에 갖다와서 결과를 내보내는 파일)
// 3. VO.java   - DB 에 들어갈 파라미터 및 결과를 저장을 담당하는 파일 (테이블의 컬럼명과 일치)
// 4. DAO.java  - mapper와 연결되서 자바에서  DB 실행 시키는 파일 (DB 처리흔 클래스들을 모아놓은 파일)
// 5. DBService - config.xml 파일을 읽고 MyBatis을 실행할 수 있도록 하는 파일  
*/