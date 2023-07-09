package c_loginout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import movie_server.CustomerVO;
import movie_server.Protocol;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Sign_up extends JPanel{
	private JTextField signup_id_tf;
	private JTextField signup_pw_tf;
	private JTextField signup_name_tf;
	private JTextField signup_birth_tf;
	private JTextField signup_phone_tf;
	
	Sign_in sign_in;
	
	public Sign_up(Sign_in signin) {
		
		this.sign_in = signin;
		
		setSize(800, 800);
		//setLocationRelativeTo(null);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		//getContentPane().setLayout(null);
		
		JPanel signup_signup_panel = new JPanel();
		signup_signup_panel.setBounds(0, 0, 784, 112);
		//getContentPane().add(signup_signup_panel);
		signup_signup_panel.setLayout(null);
		
		JLabel signup_label = new JLabel("회원가입");
		signup_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		signup_label.setHorizontalAlignment(SwingConstants.CENTER);
		signup_label.setBounds(298, 38, 165, 44);
		signup_signup_panel.add(signup_label);
		
		JPanel signup_signup_w_panel = new JPanel();
		signup_signup_w_panel.setBounds(0, 111, 784, 253);
		//getContentPane().add(signup_signup_w_panel);
		signup_signup_w_panel.setLayout(null);
		
		JLabel signup_id_label = new JLabel("아이디");
		signup_id_label.setHorizontalAlignment(SwingConstants.CENTER);
		signup_id_label.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		signup_id_label.setBounds(200, 21, 90, 30);
		signup_signup_w_panel.add(signup_id_label);
		
		JLabel signup_pw_label = new JLabel("비밀번호");
		signup_pw_label.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		signup_pw_label.setHorizontalAlignment(SwingConstants.CENTER);
		signup_pw_label.setBounds(200, 60, 90, 30);
		signup_signup_w_panel.add(signup_pw_label);
		
		signup_id_tf = new JTextField();
		signup_id_tf.setBounds(286, 29, 220, 21);
		signup_signup_w_panel.add(signup_id_tf);
		signup_id_tf.setColumns(10);
		
		signup_pw_tf = new JTextField();
		signup_pw_tf.setBounds(289, 68, 220, 21);
		signup_signup_w_panel.add(signup_pw_tf);
		signup_pw_tf.setColumns(10);
		
		JLabel signup_name_label = new JLabel("이름");
		signup_name_label.setHorizontalAlignment(SwingConstants.CENTER);
		signup_name_label.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		signup_name_label.setBounds(202, 100, 90, 30);
		signup_signup_w_panel.add(signup_name_label);
		
		JLabel signup_birth_label = new JLabel("생년월일");
		signup_birth_label.setHorizontalAlignment(SwingConstants.CENTER);
		signup_birth_label.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		signup_birth_label.setBounds(205, 143, 90, 30);
		signup_signup_w_panel.add(signup_birth_label);
		
		JLabel signup_phone_label = new JLabel("핸드폰 번호");
		signup_phone_label.setHorizontalAlignment(SwingConstants.CENTER);
		signup_phone_label.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		signup_phone_label.setBounds(189, 183, 90, 30);
		signup_signup_w_panel.add(signup_phone_label);
		
		signup_name_tf = new JTextField();
		signup_name_tf.setBounds(289, 105, 220, 21);
		signup_signup_w_panel.add(signup_name_tf);
		signup_name_tf.setColumns(10);
		
		signup_birth_tf = new JTextField();
		signup_birth_tf.setColumns(10);
		signup_birth_tf.setBounds(289, 151, 220, 21);
		signup_signup_w_panel.add(signup_birth_tf);
		
		signup_phone_tf = new JTextField();
		signup_phone_tf.setColumns(10);
		signup_phone_tf.setBounds(289, 191, 220, 21);
		signup_signup_w_panel.add(signup_phone_tf);
		
		JButton signup_idcheck_bt = new JButton("중복확인");
		signup_idcheck_bt.setBounds(528, 28, 97, 23);
		signup_signup_w_panel.add(signup_idcheck_bt);
		
		JPanel signup_bt_panel = new JPanel();
		signup_bt_panel.setBounds(0, 364, 784, 82);
		//getContentPane().add(signup_bt_panel);
		signup_bt_panel.setLayout(null);
		
		JButton signup_signup_bt = new JButton("회원가입");
		signup_signup_bt.setBounds(252, 22, 97, 23);
		signup_bt_panel.add(signup_signup_bt);
		
		JButton signup_cancel_bt = new JButton("취소");
		signup_cancel_bt.setBounds(406, 23, 97, 23);
		signup_bt_panel.add(signup_cancel_bt);
		
		// 생일 입력칸에 6자리로 입력할 수 있게 도와주는 텍스트칸(필드안에 회색 글씨)
		signup_birth_tf.addFocusListener(new FocusListener() {
			String txt = "6자리로 입력하세요([ex]981216)";
			@Override
			public void focusLost(FocusEvent e) {
				if(!signup_birth_tf.getText().trim().equalsIgnoreCase(txt)&&signup_birth_tf.getText().trim().length()==0) {
					signup_birth_tf.setText(txt);
					signup_birth_tf.setForeground(Color.LIGHT_GRAY);
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if(signup_birth_tf.getText().trim().equalsIgnoreCase(txt)) {
					signup_birth_tf.setText("");
					signup_birth_tf.setForeground(Color.BLACK);
				}
			}	
		});
		
		
		signup_idcheck_bt.addActionListener(new ActionListener() {	// 아이디 중복 확인 버튼
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
//		signup_signup_bt.addActionListener(new ActionListener() {	// 회원가입 버튼 누르면 customer 테이블에 정보가 삽입하게 한다.
//			public void actionPerformed(ActionEvent e) {
//				int res = JOptionPane.showConfirmDialog(getParent(), "가입 하시겠습니까?", "가입", JOptionPane.YES_NO_OPTION);
//				if(res == 0) {
//					if(All()) {	// 모든 정보 입력 확인 후
//						// DB customer 테이블에 데이터 삽입
//						String id = signup_id_tf.getText().trim();
//						String pw = signup_pw_tf.getText().trim();
//						String name = signup_name_tf.getText().trim();
//						String birth = signup_birth_tf.getText().trim();
//						
//						CustomerVO vo = new CustomerVO();
//						vo.setCust_id(id);
//						vo.setCust_password(pw);
//						vo.setCust_name(name);
//						vo.setCust_birth(birth);
//						
//						Protocol p = new Protocol();
//						p.setCmd(3);
//						p.setVo(vo);
//						
//						try {
//							//c_loginout.out.writeObject(p);
//							//c_loginout.out.flush();
//						} catch (Exception e2) {
//						}
//						init();
//					}else {
//						JOptionPane.showMessageDialog(getParent(), "필수 정보를 입력해주세요.");
//					}
//				}
//			}
//		});
//		
//		signup_cancel_bt.addActionListener(new ActionListener() {	// 취소 버튼 => 누르면 로그인 전 메인화면으로 돌아간다.
//			public void actionPerformed(ActionEvent e) {
//				init();
//				//new Main_login();
//				setVisible(false);
//			}
//		});
//		
//	}
//	
//
//
//
//	private boolean All() {	// 모든 정보를 입력했는지 확인해야하기 위해
//		boolean result;
//		
//		if(signup_id_tf.getText().trim().length()>0 && signup_pw_tf.getText().trim().length()>0 && 
//				signup_name_tf.getText().trim().length()>0 && signup_birth_tf.getText().trim().length()>0 && 
//				signup_phone_tf.getText().trim().length()>0 && !signup_id_tf.isEditable()) {
//				result = true;
//		} else result = false;
//		
//		return result;
//	}
//	
//	// 텍스트 필드 초기화
//	private void init() {
//		signup_id_tf.setText("");
//		signup_id_tf.setEditable(true);
//		signup_pw_tf.setText("");
//		signup_name_tf.setText("");
//		signup_birth_tf.setText("6자리로 입력하세요([ex]981216)");
//		signup_birth_tf.setForeground(Color.LIGHT_GRAY);
//		signup_phone_tf.setText("");
//		signup_phone_tf.setText("");
//	}
//	
//	public void dupchk() {
//		/*
//		if(c_loginout.idDup != 3) {
//			if(c_loginout.idDup == 0) {
//				int r = JOptionPane.showConfirmDialog(getParent(), "사용하실 수 있는 아이디입니다.\n사용하시겠습니까?", "중복확인", JOptionPane.YES_NO_OPTION);
//				if(r ==0) {
//					id_tf.setEditable(false);
//				}
//			} else {
//				JOptionPane.showMessageDialog(getParent(), "이미 사용중인 아이디입니다.");
//			}
//		}
//		*/
		
	}
}