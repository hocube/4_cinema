package c_loginout;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import movie_server.CustomerVO;
import movie_server.Protocol;
import movie_server.Server_book;
import javax.swing.JFormattedTextField;

public class Sign_up extends JPanel{
	Sign_in sign_in;
	
	Socket s;
	ObjectOutputStream out;
	ObjectInputStream in;
	Server_book server;
	String ip;
	
	private JTextField signup_id_tf;
	private JPasswordField signup_pw_tf;
	private JPasswordField signup_pw2_tf;
	private JTextField signup_name_tf;
	private JTextField signup_birth_tf;
	private JTextField signup_phone_tf;
	
	public Sign_up(Sign_in signin) {
		this.sign_in = signin;
		
		this.setLayout(null);
		
		setBackground(new Color(255, 255, 255));
		setSize(800, 800);
		
		JLabel signup_lable = new JLabel("회 원 가 입");
		signup_lable.setHorizontalAlignment(SwingConstants.CENTER);
		signup_lable.setFont(new Font("돋움", Font.BOLD, 40));
		signup_lable.setBounds(239, 81, 336, 64);
		add(signup_lable);
		
		JLabel signup_id_label = new JLabel("아이디");
		signup_id_label.setFont(new Font("굴림", Font.PLAIN, 20));
		signup_id_label.setBounds(133, 210, 70, 33);
		add(signup_id_label);
		
		JLabel signup_pw_label = new JLabel("비밀번호");
		signup_pw_label.setFont(new Font("굴림", Font.PLAIN, 20));
		signup_pw_label.setBounds(133, 281, 96, 27);
		add(signup_pw_label);
		
		JLabel signup_pw2_label = new JLabel("비밀번호 확인");
		signup_pw2_label.setFont(new Font("굴림", Font.PLAIN, 20));
		signup_pw2_label.setBounds(133, 346, 127, 27);
		add(signup_pw2_label);
		
		JLabel signup_name_label = new JLabel("이름");
		signup_name_label.setFont(new Font("굴림", Font.PLAIN, 20));
		signup_name_label.setBounds(133, 407, 59, 34);
		add(signup_name_label);
		
		JLabel signup_birth_label = new JLabel("생년월일");
		signup_birth_label.setFont(new Font("굴림", Font.PLAIN, 20));
		signup_birth_label.setBounds(133, 472, 85, 34);
		add(signup_birth_label);
		
		JLabel signup_phone_label = new JLabel("전화번호");
		signup_phone_label.setFont(new Font("굴림", Font.PLAIN, 20));
		signup_phone_label.setBounds(133, 543, 96, 34);
		add(signup_phone_label);
		
		JButton signup_idcheck_bt = new JButton("중복확인");
		signup_idcheck_bt.setFont(new Font("굴림", Font.PLAIN, 15));
		signup_idcheck_bt.setBounds(563, 211, 91, 34);
		add(signup_idcheck_bt);
		
		JButton signup_pwcheck_bt = new JButton("확인");
		signup_pwcheck_bt.setFont(new Font("굴림", Font.PLAIN, 15));
		signup_pwcheck_bt.setBounds(563, 344, 91, 34);
		add(signup_pwcheck_bt);
		
		JButton signup_signup_bt = new JButton("회원가입");
		signup_signup_bt.setBounds(273, 672, 91, 33);
		add(signup_signup_bt);
		
		JButton signup_cancel_bt = new JButton("취소");
		signup_cancel_bt.setBounds(457, 672, 91, 33);
		add(signup_cancel_bt);
		
		
		signup_pw_tf = new JPasswordField();
		signup_pw_tf.setColumns(10);
		signup_pw_tf.setBounds(292, 281, 240, 29);
		add(signup_pw_tf);
		
		signup_pw2_tf = new JPasswordField();
		signup_pw2_tf.setColumns(10);
		signup_pw2_tf.setBounds(292, 344, 240, 29);
		add(signup_pw2_tf);
		
		signup_name_tf = new JTextField();
		signup_name_tf.setColumns(10);
		signup_name_tf.setBounds(292, 408, 240, 33);
		add(signup_name_tf);
		
		// 생년월일
		signup_birth_tf = new JTextField();
		signup_birth_tf.setColumns(10);
		signup_birth_tf.setBounds(292, 472, 240, 33);
		add(signup_birth_tf);
		signup_birth_tf.setText("6자리로 입력하세요 ([ex]981216)");
		// 포커스 얻기/잃기 이벤트 처리
		signup_birth_tf.addFocusListener(new FocusListener() {
					@Override
					public void focusGained(FocusEvent e) {
						 // 텍스트 필드가 선택될 때, 힌트 텍스트가 사라집니다.
						if(signup_birth_tf.getText().equals("6자리로 입력하세요 ([ex]981216)")) {
							signup_birth_tf.setText("");
						}
					}
					@Override
					public void focusLost(FocusEvent e) {
						if(signup_birth_tf.getText().isEmpty()) {
							signup_birth_tf.setText("6자리로 입력하세요 ([ex]981216)");
							signup_birth_tf.setForeground(Color.LIGHT_GRAY);
						}
					}		
				});
		
		// 전화번호
		signup_phone_tf = new JTextField();
		signup_phone_tf.setColumns(10);
		signup_phone_tf.setBounds(292, 543, 240, 33);
		add(signup_phone_tf);
		signup_phone_tf.setText("[ex]01055665207");
		// 포커스 얻기,잃기 이벤트 처리
		signup_phone_tf.addFocusListener(new FocusListener() {
					@Override
					public void focusGained(FocusEvent e) {
						 // 텍스트 필드가 선택될 때, 힌트 텍스트가 사라집니다.
						if(signup_phone_tf.getText().equals("[ex]01055665207")) {
							signup_phone_tf.setText("");
						}
					}
					@Override
					public void focusLost(FocusEvent e) {
						if(signup_phone_tf.getText().isEmpty()) {
							signup_phone_tf.setText("[ex]01055665207");
							signup_phone_tf.setForeground(Color.LIGHT_GRAY);
						}
					}		
				});

		// 아이디
		signup_id_tf = new JTextField();
		signup_id_tf.setBounds(292, 212, 240, 33);
		add(signup_id_tf);
		signup_id_tf.setColumns(10);
		signup_id_tf.setText("아이디 중복확인을 해주세요");
		
		
		// 포커스 얻기,잃기 이벤트 처리
		signup_id_tf.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				 // 텍스트 필드가 선택될 때, 힌트 텍스트가 사라집니다.
				if(signup_id_tf.getText().equals("아이디 중복확인을 해주세요")) {
					signup_id_tf.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(signup_id_tf.getText().isEmpty()) {
					signup_id_tf.setText("아이디 중복확인을 해주세요");
					signup_id_tf.setForeground(Color.LIGHT_GRAY);
				}
			}		
		});
		
		setVisible(true);
		
		// 아이디 중복확인 버튼
		signup_idcheck_bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					CustomerVO vo = new CustomerVO();
					String idcheck = signup_id_tf.getText().trim();
					System.out.println(idcheck);
					
					vo.setCust_id(idcheck);
					
					System.out.println("생성완");
					
					Protocol p = new Protocol();
					
					System.out.println("프로토콜 받앗니");
					
					// customerVO에 입력한 id 삽입
					
					p.setC_vo(vo);
					
					p.setCmd(503);
					
					signin.out.writeObject(p);
					signin.out.flush();
				} catch (Exception e2) {
					System.out.println(e2);
				}
				
			}
		});
		
		// 비밀번호 확인 버튼
		signup_pwcheck_bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String pw1 = new String(signup_pw_tf.getPassword());
				String pw2 = new String(signup_pw2_tf.getPassword());
				
				if (! pw1.equals(pw2)) {
					JOptionPane.showMessageDialog(getParent(), "비밀번호를 잘못 입력하셨습니다.", "오류", JOptionPane.ERROR_MESSAGE);
					
					// 비밀번호 필드 초기화 및 포커스 설정
					signup_pw_tf.setText("");
			        signup_pw2_tf.setText("");
			        signup_pw_tf.requestFocusInWindow();
				}else if(pw1.equals(pw2)) {
					JOptionPane.showMessageDialog(getParent(), "비밀번호가 일치합니다. 계속 진행하세요.", "확인", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		// 회원가입 버튼
		signup_signup_bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(getParent(), "가입 하시겠습니까?", "가입", JOptionPane.YES_NO_OPTION);
				if(res == JOptionPane.YES_OPTION) {
					if (signup_pw_tf.getPassword().length == 0 || signup_pw2_tf.getPassword().length == 0) {
		                JOptionPane.showMessageDialog(getParent(), "비밀번호 확인 검사를 하세요.", "오류", JOptionPane.ERROR_MESSAGE);
					}else if (All()) {
						// CustomerDB에 삽입
						
						String id = signup_id_tf.getText().trim();
						String pw = signup_pw_tf.getText().trim();
						String name = signup_name_tf.getText().trim();
						String birth = signup_birth_tf.getText().trim();
						String phone = signup_phone_tf.getText().trim();
						
						// 값은 들어갔고
						System.out.println(id);
						System.out.println(pw);
						System.out.println(name);
						System.out.println(birth);
						System.out.println(phone);
						
						CustomerVO vo = new CustomerVO();
						vo.setCust_id(id);
						vo.setCust_password(pw);
						vo.setCust_name(name);
						vo.setCust_birth(birth);
						vo.setCust_phone(phone);
						vo.setAdmin_yn("0");
						vo.setDelete_yn("0");
						vo.setPoint(0);
						
						try {
							Protocol p = new Protocol();
							System.out.println("프로토콜 받앗니");
						
							p.setCmd(502);
							p.setC_vo(vo);
						
							sign_in.out.writeObject(p);
							sign_in.out.flush();
						} catch (Exception e2) {
							// TODO: handle exception
						}
						init();
					}else {
						JOptionPane.showMessageDialog(getParent(), "필수 정보를 입력해주세요");
					}
				}
			}
		});
		
		// 취소 버튼
		signup_cancel_bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				init();
				signin.card.show(signin.pg, "sign_in");
			}
		});
	}
	
	// 모든 정보를 입력했는지 확인하기
	private boolean All() {	
		boolean result;
		if(signup_id_tf.getText().trim().length()>0 && signup_pw_tf.getText().trim().length()>0 &&
			signup_pw2_tf.getText().trim().length()>0 &&signup_name_tf.getText().trim().length()>0 && signup_birth_tf.getText().trim().length()>0 &&
			signup_phone_tf.getText().trim().length()>0) {
			result = true;
		}else {
			result = false;
		}
		
		return result;
	}
	
	// 텍스트 필드 초기화(화면 이동을 할때 textfield 비우기)
	private void init() {
		signup_id_tf.setText("아이디 중복확인을 해주세요");
		signup_id_tf.setEditable(true);	// 아이디 중복 체크할때 중복확인되면 false로 바꾸려고 하기 때문이다
		signup_pw_tf.setText("");
		signup_pw2_tf.setText("");
		signup_name_tf.setText("");
		signup_birth_tf.setText("6자리로 입력하세요([ex]981216)");
		signup_phone_tf.setText("-(하이픈없이 입력하세요)([ex]01055665207)");
	}
	
	// 아이디 중복 체크 메서드
	public void dupchk() {
		if(sign_in.iddck != 2) {
			if(sign_in.iddck == 0) {
				int r = JOptionPane.showConfirmDialog(getParent(), "사용하실 수 있는 아이디입니다.\n사용하시겠습니까?", "중복확인", JOptionPane.YES_NO_OPTION);
				if(r==0) {
					signup_id_tf.setEditable(false);
				}
		}else {
			JOptionPane.showMessageDialog(getParent(), "이미 사용중인 아이디입니다.");
			}
		}
	}
	
	public void loginRes() {
		if(sign_in.loginRes == 1) {
			JOptionPane.showMessageDialog(getParent(), "회원가입이 완료되었습니다.");
			sign_in.card.show(sign_in.pg, "sign_in");
		} else {
			JOptionPane.showMessageDialog(getParent(), "회원가입이 실패했습니다.");
		}
	}
}