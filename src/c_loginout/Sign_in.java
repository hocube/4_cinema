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
import movie_server.DAO;
import movie_server.Protocol;
import movie_server.TicketBox_VO;
import pay.Pay;
import pay.PointCharge;
import pay.Reservation_completed;
//import snackbar.Menu;
import ticket.MobileTicket;
import ticket.TicketList;
import ticketbox.Ticket_before_pay;
import ticketbox.Ticket_office_main;
import ticketbox.Ticket_seat;
import ticketbox.Ticket_seat_map;

public class Sign_in extends JFrame implements Runnable {

	JPanel contentPane;
	public JPanel pg; // 다른 패널들을 담을 패널
	public CardLayout card; // 형식

	private JTextField signin_id_tf;
	private JTextField signin_pw_tf;
	private JButton signin_login_bt, signin_signup_bt;
	private JLabel signin_logo_label, signin_id_label, signin_pw_label;

	public String c_id, c_pw;

	public Socket s;
	public ObjectOutputStream out;
	public ObjectInputStream in;

	public Sign_up sign_up;
	public Main_login main_login;
	public CustomerVO cvo;
	public Pay pay;
	public PointCharge pointcharge;
	public Reservation_completed r_completed;
	// public Menu menu;
	public MobileTicket m_ticket;
	public TicketList t_list;
	public Ticket_before_pay tb_pay;
	public Ticket_office_main to_main;
	public Ticket_seat_map ts_map;
	public Ticket_seat t_seat;
	// public Menu snack;

	public Sign_in() {
		super("4딸라-필름");

		pg = new JPanel();
		pg.setLayout(card = new CardLayout());

		setResizable(false);
		setBounds(100, 100, 800, 800);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);

		signin_logo_label = new JLabel("4딸라-필름");
		signin_logo_label.setFont(new Font("맑은 고딕", Font.BOLD, 78));
		signin_logo_label.setBounds(217, 79, 400, 203);
		contentPane.add(signin_logo_label);

		signin_id_tf = new JTextField();
		signin_id_tf.setBounds(395, 277, 222, 28);
		signin_id_tf.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		contentPane.add(signin_id_tf);

		signin_pw_tf = new JPasswordField();
		signin_pw_tf.setBounds(395, 315, 222, 28);
		signin_pw_tf.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		contentPane.add(signin_pw_tf);

		signin_id_label = new JLabel("ID");
		signin_id_label.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		signin_id_label.setBounds(199, 277, 107, 25);
		contentPane.add(signin_id_label);

		signin_pw_label = new JLabel("PASSWORD");
		signin_pw_label.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		signin_pw_label.setBounds(199, 323, 171, 25);
		contentPane.add(signin_pw_label);

		signin_signup_bt = new JButton("회원가입");
		signin_signup_bt.setBounds(425, 385, 134, 37);
		signin_signup_bt.setBorderPainted(false);
		signin_signup_bt.setFocusPainted(false);
		signin_signup_bt.setContentAreaFilled(false);
		signin_signup_bt.setFont(new Font("맑은고딕", Font.BOLD, 20));
		signin_signup_bt.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(signin_signup_bt);

		signin_login_bt = new JButton("로그인");
		signin_login_bt.setBounds(269, 378, 144, 50);
		signin_login_bt.setBorderPainted(false);
		signin_login_bt.setFocusPainted(false);
		signin_login_bt.setContentAreaFilled(false);
		signin_login_bt.setFont(new Font("Dialog", Font.BOLD, 20));
		signin_login_bt.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(signin_login_bt);

		main_login = new Main_login(this);
		sign_up = new Sign_up(this);
		// cvo = new CustomerVO(this);
		pay = new Pay(this);
		pointcharge = new PointCharge(this);
		r_completed = new Reservation_completed(this);
		// public Menu menu;
		m_ticket = new MobileTicket(this);
		t_list = new TicketList(this);
		tb_pay = new Ticket_before_pay(this);
		to_main = new Ticket_office_main(this);
		ts_map = new Ticket_seat_map(this);
		t_seat = new Ticket_seat(this);

		pg.add(contentPane, "sign_in");
		pg.add(main_login, "main_login");
		pg.add(sign_up, "sign_up");
		pg.add(pay, "pay");
		pg.add(pointcharge, "pointcharge");
		pg.add(r_completed, "r_completed");
		pg.add(t_list, "t_list");
		pg.add(tb_pay, "tb_pay");
		pg.add(to_main, "to_main");
		pg.add(ts_map, "ts_map");
		pg.add(t_seat, "t_seat");
		// pg.add(snack, "snack");

		setContentPane(pg);

		// 접속
		connected();

		// Closing
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

		// 로그인 버튼
		signin_login_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login_go();
			}
		});

		// 회원가입 버튼
		signin_signup_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(pg, "sign_up");
				init();
			}
		});

		// 비밀번호 텍스트 필드
		signin_pw_tf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login_go();
				}
			}
		});

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
			s = new Socket("192.168.0.11", 7780);
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

	// 초기값 메서드(로그인 실패 시 id/pw 텍스트 필드 비우고 Focus)
	private void init() {
		signin_id_tf.setText("");
		signin_pw_tf.setText("");
		signin_id_tf.requestFocus();
	}

	// 로그인 메서드
	public void login_go() {
		if (signin_id_tf.getText().trim().length() > 0 && signin_pw_tf.getText().trim().length() > 0) {

			try {
				CustomerVO c_vo = new CustomerVO();
				Protocol p = new Protocol();

				// 콘솔 확인용
				System.out.println("아이디:" + signin_id_tf.getText());
				System.out.println("비번:" + signin_pw_tf.getText());

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

	@Override
	public void run() {
		esc: while (true) {
			try {
				Object obj = in.readObject();
				if (obj != null) {
					Protocol p = (Protocol) obj;
					switch (p.getCmd()) {
					case 0: // 종료
						break esc;
					case 104:
//						System.out.println("sign_in의 104");
//						List<MobileTicket_VO> ticketList = p.getP_list();
//						System.out.println(ticketList);
//						t_list.showTicketList(ticketList);
						break;
					case 301:
						List<TicketBox_VO> movieList = p.getT_list();
						System.out.println(movieList);
						to_main.addMovieListToTable(movieList);
						// 영화목록은 성공, 건들지말자.
						break;
					case 302:
						List<TicketBox_VO> movieTimes = p.getT_list();
						to_main.addTimeListToTable(movieTimes);
						// 상영시간표는 성공, 건들지말자.
						break;
					case 303:

						break;

					case 501: // 로그인
						// 지호
						System.out.println("Sign_in의 501");

						if (p.getC_vo() != null) {
							if (p.getC_vo().getAdmin_yn().equals("0")) {
								// 로그인 성공
								// 로그인 테스트용으로 전화번호 가져와봄.
								String name = p.getC_vo().getCust_name();
								System.out.println(name + " 님 로그인 성공");

								JOptionPane.showMessageDialog(getParent(), name + " 님 반갑습니다.");

								// 로그인 성공을 해서 확인버튼을 누르면 main_login으로 이동
								card.show(pg, "main_login");
								System.out.println("메인창 화면 전환 성공");
							} else {
								JOptionPane.showMessageDialog(getParent(), "관리자 로그인 성공");
								// card.show(pg, ""); // 관리자 페이지로 이동
								// 관리자팀은 먼저 카드선언하는위에 관리자 화면단 선언해주고 여기에 써주세요.

							}
						} else {
							// 로그인 실패
							JOptionPane.showMessageDialog(getParent(), "가입 정보 없음");
							// card.show(pg, "sign_in");
							// 로그인 실패해도, 이 카드 안보여줘도 다시 원래 화면으로 돌아가기
							// 때문에 이건 주석처리함.
							init();// 텍스트필드 초기화
						}
						break;

					case 502: // 회원가입

					case 503: // 아이디 중복체크
					}
				}
			} catch (Exception e) {
			}
		}
		closed();
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