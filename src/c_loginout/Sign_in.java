package c_loginout;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import movie_server.CustomerVO;
import movie_server.LoginInfo_VO;
import movie_server.M_movieVO;
import movie_server.MobileTicket_VO;
import movie_server.Protocol;
import movie_server.TicketBox_VO;
import pay.Pay;
import pay.PointChargeDialog;
import snackbar.Admin_Customer_change;
import snackbar.Admin_Customer_panel;
import snackbar.Admin_Snack_panel;
import snackbar.Admin_movie_panel;
import snackbar.Admin_order_panel;
import snackbar.Admin_panel;
import snackbar.S_Menu1;
import snackbar.S_Menu2;
import snackbar.S_Menu3;
import ticket.MobileTicket;
import ticket.TicketList;
import ticketbox.Ticket_before_pay;
import ticketbox.Ticket_office_main;
import ticketbox.Ticket_seat;
import ticketbox.Ticket_seat_map;

public class Sign_in extends JFrame implements Runnable {

	JPanel contentPane;
	public JPanel pg; // 다른 패널들을 담을 패널
	public CardLayout card; 

	private JTextField signin_id_tf;
	private JTextField signin_pw_tf;
	private JButton signin_login_bt, signin_signup_bt;
	private JLabel signin_logo_label, signin_id_label, signin_pw_label;

	public String c_id, c_pw;
	public int iddck = 2;
	public int loginRes;
	public int logoutRes;

	public Socket s;
	public ObjectOutputStream out;
	public ObjectInputStream in;

	public Sign_up sign_up;
	public Main_login main_login;
	public MyPage mypage;
	public CustomerVO cvo;
	public LoginInfo_VO lvo;
	public Pay pay;
	public PointChargeDialog pointcharge;
	public MobileTicket m_ticket;
	public TicketList t_list;
	public Ticket_before_pay tb_pay;
	public Ticket_office_main to_main;
	public Ticket_seat_map ts_map;
	public Ticket_seat t_seat;

	public Admin_panel admin;
	public Admin_Customer_panel c_admin;
	public Admin_Customer_change cc_admin;
	public Admin_order_panel o_admin;
	public Admin_Snack_panel s_admin;
	public Admin_movie_panel m_admin;
	public S_Menu1 snack1;
	public S_Menu2 snack2;
	public S_Menu3 snack3;

	public Protocol p; 

	public Sign_in() {
		super("4딸라-필름");

		card = new CardLayout();
		pg = new JPanel();
		pg.setLayout(card);

		setResizable(false);
		setBounds(100, 100, 800, 800);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);

		signin_logo_label = new JLabel("4딸라-필름");
		signin_logo_label.setFont(new Font("맑은 고딕", Font.BOLD, 78));
		signin_logo_label.setBounds(217, 80, 400, 203);
		contentPane.add(signin_logo_label);

		signin_id_tf = new JTextField();
		signin_id_tf.setBounds(419, 310, 222, 28);
		signin_id_tf.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		contentPane.add(signin_id_tf);

		signin_pw_tf = new JPasswordField();
		signin_pw_tf.setBounds(419, 389, 222, 28);
		signin_pw_tf.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		contentPane.add(signin_pw_tf);

		signin_id_label = new JLabel("ID");
		signin_id_label.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		signin_id_label.setBounds(253, 313, 64, 25);
		contentPane.add(signin_id_label);

		signin_pw_label = new JLabel("PASSWORD");
		signin_pw_label.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		signin_pw_label.setBounds(187, 389, 171, 25);
		contentPane.add(signin_pw_label);

		signin_signup_bt = new JButton("회원가입");
		signin_signup_bt.setBounds(429, 506, 134, 37);
		signin_signup_bt.setBorderPainted(false);
		signin_signup_bt.setFocusPainted(false);
		signin_signup_bt.setContentAreaFilled(false);
		signin_signup_bt.setFont(new Font("맑은고딕", Font.BOLD, 20));
		signin_signup_bt.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(signin_signup_bt);

		setLocationRelativeTo(null); 
		setContentPane(contentPane);

		JButton signin_login_bt = new JButton("로그인");
		signin_login_bt.setBounds(236, 506, 134, 37);
		signin_login_bt.setFocusPainted(false);
		signin_login_bt.setContentAreaFilled(false);
		signin_login_bt.setFont(new Font("Dialog", Font.BOLD, 20));
		signin_login_bt.setBorderPainted(false);
		signin_login_bt.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(signin_login_bt);
		setVisible(true);

		//회원가입은 로그인 성공 후에 들어가게 따로 뺐음.
		sign_up = new Sign_up(this);
		setContentPane(pg);

		pg.add(contentPane, "sign_in");
		pg.add(sign_up, "sign_up");

		// 접속
		connected();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (s != null) {
					Protocol p = new Protocol();
					p.setCmd(0); // 종료
					try {
						out.writeObject(p);
						out.flush();
					} catch (Exception e2) {
					}
				} else {
					closed();
				}
			}
		});

		signin_login_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login_go();

			}
		});

		signin_signup_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(pg, "sign_up");

			}
		});

		signin_pw_tf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login_go();
				}
			}
		});

	}

	
	public void LoginSuccess() {

		main_login = new Main_login(this);
		sign_up = new Sign_up(this);
		mypage = new MyPage(this);
		t_list = new TicketList(this);
		// pay = new Pay(this);
		tb_pay = new Ticket_before_pay(this);
		to_main = new Ticket_office_main(this);
		ts_map = new Ticket_seat_map(this);
		t_seat = new Ticket_seat(this);

		c_admin = new Admin_Customer_panel(this);
		cc_admin = new Admin_Customer_change(this);
		o_admin = new Admin_order_panel(this);
		s_admin = new Admin_Snack_panel(this);
		m_admin = new Admin_movie_panel(this);
		admin = new Admin_panel(this);
		snack1 = new S_Menu1(this);
		snack2 = new S_Menu2(this);
		snack3 = new S_Menu3(this);

		setContentPane(pg);

		pg.add(main_login, "main_login");
		pg.add(sign_up, "sign_up");
		pg.add(mypage, "mypage");
		pg.add(t_list, "t_list");

		pg.add(tb_pay, "tb_pay");
		pg.add(to_main, "to_main");
		pg.add(ts_map, "ts_map");
		pg.add(t_seat, "t_seat");

		pg.add(admin, "admin");
		pg.add(s_admin, "s_admin");
		pg.add(c_admin, "c_admin");
		pg.add(cc_admin, "cc_admin");
		pg.add(o_admin, "o_admin");
		pg.add(m_admin, "m_admin");
		pg.add(snack1, "snack1");
		pg.add(snack2, "snack2");
		pg.add(snack3, "snack3");

	}

	// 서버 연결 메서드
	private void connected() {
		try {
			// 혜지-집: 183.96.151.249
			// 혜지-학원: 192.168.0.41
			// 지호-학원: 192.168.0.78
			// 지호-집: 192.168.0.11
			// 192.168.0.80 지혜
			// 192.168.0.34
			s = new Socket("192.168.0.78", 7780);
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());

			new Thread(this).start();
		} catch (Exception e) {
		}
	}

	// 서버 연결 해제 메서드
	private void closed() {
		try {
			in.close();
			out.close();
			s.close();
			System.out.println("프로그램 종료");
			System.exit(0);
		} catch (Exception e) {
		}
	}

	// 초기값 메서드
	private void init() {
		signin_id_tf.setText("");
		signin_pw_tf.setText("");
		signin_id_tf.requestFocus();
	}

	@Override
	public void run() {
		esc: while (true) {
			try {
				Object obj = in.readObject();
				if (obj != null) {
					p = (Protocol) obj;
					switch (p.getCmd()) {
					case 0: // 종료
						break esc;
					case 102: // 현재 접속한 회원정보 조회
						p.setCmd(101);
						out.writeObject(p);
						out.flush();
						break;
					case 104: // 티켓 리스트 가져오기
						List<MobileTicket_VO> m_list = p.getM_list();
						t_list.updateTable(m_list);
						break;
					case 105: // 예매 취소
						int result = p.getResult();
						if (result == 1) {
							JOptionPane.showMessageDialog(getParent(), "예매가 취소되었습니다.");
							MobileTicket_VO m_vo = new MobileTicket_VO();
							m_vo.setCust_id(cvo.getCust_id());
							p.setM_vo(m_vo);
							p.setCmd(104);
							out.writeObject(p);
							out.flush();
						} else
							JOptionPane.showMessageDialog(getParent(), "예매 취소가 실패하였습니다.");
						break;
					case 301:
						List<TicketBox_VO> movieList = p.getT_list();
						System.out.println(movieList);
						to_main.addMovieListToTable(movieList);
						break;
					case 302:
						List<TicketBox_VO> movieTimes = p.getT_list();
						to_main.addTimeListToTable(movieTimes);
						break;
					case 303: // 포스터 클릭시 해당 영화이름 가져가서 table1에 가져오기
						TicketBox_VO movieChoice = p.getT_vo();
						System.out.println("여기까지왔나");
						to_main.updateChoiceTable(movieChoice);
						card.show(pg, "to_main");
						p.setCmd(302);
						out.writeObject(p);
						out.flush();
						break;
					case 401: // 관리자 고객정보 전체 받기
						List<CustomerVO> ad_custList = p.getAd_clist();// VO의 게터세터중 get(DAO에서 지정한 return변수명)
						c_admin.adminCustListToTable(ad_custList);
						break; 
					case 402: // 관리자 고객정보 삭제 후 int로 성공유무 받기
						int ad_result = p.getResult();
						if (ad_result > 0) {
							JOptionPane.showMessageDialog(getParent(), "고객정보 삭제 완료");
						} else {
							JOptionPane.showMessageDialog(getParent(), "삭제 실패");
						}
						break;
					case 403: // 관리자 ID로 검색후 회원정보 받기(List로받음)
						if(p.getAd_clist() != null) {
							List<CustomerVO> ad_custOne = p.getAd_clist();
							c_admin.adminCustListToTable(ad_custOne);
						}else {
							JOptionPane.showMessageDialog(getParent(), "데이터가 없습니다");
						}
						break;
					case 404: // 관리자 권한 추가 성공하면 알림 보내기
						int adminok_result = p.getResult();
						if(adminok_result> 0) {
						JOptionPane.showMessageDialog(getParent(), "관리자 권한이 생성되었습니다");
						}else {
							JOptionPane.showMessageDialog(getParent(), "이미 관리자 권한이 있는 아이디 입니다");
						}
						break;
					case 405: // 고객 정보 받아서 수정하는 패널로 보내기
						List<CustomerVO> ad_ch_custList = p.getAd_clist();
						cc_admin.CustListInfo_admin(ad_ch_custList);
						break;
						
					case 406: // 고객정보 수정후 int값 받음
						int ad_ch_result = p.getResult();
						if (ad_ch_result > 0) {
							JOptionPane.showMessageDialog(getParent(), "고객 정보 수정이 완료되었습니다");
						}else {
							JOptionPane.showMessageDialog(getParent(), "수정할 정보 값이 잘못 입력되었습니다");
						}
						break;
					case 501: // 로그인
						if (p.getC_vo() != null) {
							cvo = p.getC_vo();
							// 화면 초기화
							LoginSuccess();
							if (p.getC_vo().getDelete_yn().equals("1")) {
								JOptionPane.showMessageDialog(getParent(), "탈퇴한 회원 입니다. 관리자에게 문의 하세요");
								init();
							}
							if (p.getC_vo().getAdmin_yn().equals("0") && p.getC_vo().getDelete_yn().equals("0")) {
								// 로그인 성공
								String name = p.getC_vo().getCust_name();
								JOptionPane.showMessageDialog(getParent(), name + " 님 반갑습니다.");
								card.show(pg, "main_login");
							} else if (p.getC_vo().getAdmin_yn().equals("1")) {
								card.show(pg, "admin"); // 관리자 페이지로 이동
								JOptionPane.showMessageDialog(getParent(), "관리자 로그인 성공");
							}
						} else {
							// 로그인 실패
							JOptionPane.showMessageDialog(getParent(), "가입 정보 없음");
							init();
						}
						break;
					case 502: // 회원가입
						loginRes = p.getResult();
						sign_up.loginRes();
						break;
					case 503: // 아이디 중복체크
						iddck = p.getResult();
						sign_up.dupchk();
						break;
					case 504: // 로그아웃
						logoutRes = p.getResult();
						logoutRes();
					case 507: // 탈퇴
						init();
						break;
					case 601:
						m_admin.adminMoiveListToTable(p.getMslist());
						break;
					case 603:
						int ad_resultmv = p.getResult();
						m_admin.adminResultalert(ad_resultmv);
						break;
					}			
				}
			} catch (Exception e) {
			}
		}
		closed();
	}

	public void login_go() {
		if (signin_id_tf.getText().trim().length() > 0 && signin_pw_tf.getText().trim().length() > 0) {

			try {
				CustomerVO c_vo = new CustomerVO();
				Protocol p = new Protocol();

				// 1. CustomerVo에 입력한 아이디와 비번 세팅
				c_vo.setCust_id(signin_id_tf.getText());
				c_vo.setCust_password(signin_pw_tf.getText());

				// 2. id/pw가 담긴 CustomerVO를 Protocol의 c_vo에 세팅
				p.setC_vo(c_vo);

				// 3. cmd 501이라고 라벨를 붙임.
				p.setCmd(501);

				out.writeObject(p);
				out.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} else {
			JOptionPane.showMessageDialog(getParent(), "아이디 / 비밀번호를 입력해주세요.");
			System.out.println("입력하지않았을때 뜬다.");
		}
	}

	public void logoutRes() {
		if (logoutRes == 1) {
			JOptionPane.showMessageDialog(getParent(), "로그아웃되었습니다.");
			card.show(pg, "sign_in");
			init();
		} else {
			JOptionPane.showMessageDialog(getParent(), "로그아웃이 실패했습니다.");
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Sign_in frame = new Sign_in();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}
}