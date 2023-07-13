package c_loginout;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;

import movie_server.CustomerVO;
import movie_server.Protocol;

public class MyPage extends JPanel {
	Sign_in sign_in;

	// 탈퇴하기, 비밀번호 변경, 핸드폰 번호 변경 버튼
	JButton cust_delete_bt, mp_pw_ch_bt, mp_phone_ch_bt, mp_back_bt;

	public MyPage(Sign_in signin) {
		this.sign_in = signin;

		setBackground(new Color(255, 255, 255));
		setSize(800, 800);
		setLayout(null);

		JLabel mp_mypage_Label = new JLabel("마 이 페 이 지");
		mp_mypage_Label.setBounds(249, 94, 317, 79);
		mp_mypage_Label.setHorizontalAlignment(SwingConstants.CENTER);
		mp_mypage_Label.setFont(new Font("맑은 고딕", Font.BOLD, 44));

		this.add(mp_mypage_Label);

		cust_delete_bt = new JButton("탈퇴하기");
		cust_delete_bt.setBounds(441, 657, 97, 23);
		cust_delete_bt.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		this.add(cust_delete_bt);

		JLabel mp_name_label = new JLabel("이 름");
		mp_name_label.setBounds(181, 240, 97, 23);
		mp_name_label.setHorizontalAlignment(SwingConstants.RIGHT);
		mp_name_label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		this.add(mp_name_label);

		mp_pw_ch_bt = new JButton("비밀번호 변경");
		mp_pw_ch_bt.setBounds(375, 381, 121, 27);
		this.add(mp_pw_ch_bt);

		mp_phone_ch_bt = new JButton("핸드폰 번호 변경");
		mp_phone_ch_bt.setBounds(547, 532, 137, 27);
		this.add(mp_phone_ch_bt);

		JLabel mp_an_name_label = new JLabel(signin.p.getC_vo().getCust_name());
		mp_an_name_label.setBounds(375, 240, 121, 23);
		mp_an_name_label.setHorizontalAlignment(SwingConstants.LEFT);
		mp_an_name_label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		add(mp_an_name_label);

		JLabel mp_id_label = new JLabel("아 이 디");
		mp_id_label.setBounds(181, 315, 97, 23);
		mp_id_label.setHorizontalAlignment(SwingConstants.RIGHT);
		mp_id_label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		this.add(mp_id_label);

		JLabel mp_pw_label = new JLabel("비 밀 번 호");
		mp_pw_label.setBounds(191, 381, 97, 23);
		mp_pw_label.setHorizontalAlignment(SwingConstants.RIGHT);
		mp_pw_label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		this.add(mp_pw_label);

		JLabel mp_birth_label = new JLabel("생 년 월 일");
		mp_birth_label.setBounds(191, 459, 97, 23);
		mp_birth_label.setHorizontalAlignment(SwingConstants.RIGHT);
		mp_birth_label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		this.add(mp_birth_label);

		JLabel mp_phone_label = new JLabel("핸드폰번호");
		mp_phone_label.setBounds(191, 530, 97, 23);
		mp_phone_label.setHorizontalAlignment(SwingConstants.RIGHT);
		mp_phone_label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		this.add(mp_phone_label);

		// 마이페이지에서는 현재 로그인 되어있는 사람의 정보를 볼 수 있다.
		JLabel mp_an_id_label = new JLabel(signin.p.getC_vo().getCust_id());
		mp_an_id_label.setBounds(377, 315, 131, 23);
		mp_an_id_label.setHorizontalAlignment(SwingConstants.LEFT);
		mp_an_id_label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		this.add(mp_an_id_label);

		JLabel mp_an_birth_label = new JLabel(signin.p.getC_vo().getCust_birth());
		mp_an_birth_label.setBounds(375, 459, 121, 23);
		mp_an_birth_label.setHorizontalAlignment(SwingConstants.LEFT);
		mp_an_birth_label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		this.add(mp_an_birth_label);

		JLabel mp_an_phone_label = new JLabel(signin.p.getC_vo().getCust_phone());
		mp_an_phone_label.setBounds(375, 530, 121, 23);
		mp_an_phone_label.setHorizontalAlignment(SwingConstants.LEFT);
		mp_an_phone_label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		this.add(mp_an_phone_label);

		mp_back_bt = new JButton("메인으로");
		mp_back_bt.setBounds(271, 657, 97, 23);
		mp_back_bt.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		add(mp_back_bt);

		// 비밀 번호 변경 버튼
		mp_pw_ch_bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				while (true) {
					String ch_pw = JOptionPane.showInputDialog("변경할 비밀번호를 입력해주세요");
					System.out.println("마이페이지 입장 비밀번호 입력" + ch_pw);
					
					// 취소 버튼을 누르면 아무 작업도 수행하지 않고 반복문을 종료합니다.
		            if (ch_pw == null) {
		                break;
		            }

		            try {
		                // 4글자 미만 수정 안됨.
		                if (ch_pw.length() < 4) {
							JOptionPane.showMessageDialog(null, "비밀번호를 4글자 이상 입력해주세요.", "알림",
									JOptionPane.WARNING_MESSAGE);
						} else {
							CustomerVO c_vo = new CustomerVO();
							Protocol p = new Protocol();

							// 여기에 값이 없다고함 signin.lvo.getCust_id()
							c_vo.setCust_id(signin.cvo.getCust_id());
							c_vo.setCust_password(ch_pw);
							System.out.println("받은 값(아이디): " + c_vo.getCust_id());
							System.out.println("받은 값(비번): " + c_vo.getCust_password());

							p.setC_vo(c_vo);
							System.out.println(p.getC_vo().getCust_id());
							System.out.println(p.getC_vo().getCust_password());

							p.setCmd(505);

							signin.out.writeObject(p);
							signin.out.flush();

							signin.cvo.setCust_password(ch_pw);
							// 알림 창 띄우기
							JOptionPane.showMessageDialog(null, "비밀번호 변경 성공", "알림", JOptionPane.INFORMATION_MESSAGE);
							
							break;
						}

					} catch (Exception e2) {
						System.out.println(e2);
					}
				}
			}
		});

		// 핸드폰 번호 변경
		mp_phone_ch_bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				while (true) {
					String ch_phone = JOptionPane.showInputDialog("변경할 핸드폰 번호를 입력해주세요");

					// 취소 버튼을 누르면 아무 작업도 수행하지 않고 반복문을 종료합니다.
		            if (ch_phone == null) {
		                break;
		            }

		            try {
		                // 4글자 미만 수정 안됨.
		                if (ch_phone.length() < 6) {
							JOptionPane.showMessageDialog(null, "핸드폰 번호를 정확하게 입력해주세요.", "알림",
									JOptionPane.WARNING_MESSAGE);
						} else {
							CustomerVO c_vo = new CustomerVO();
							Protocol p = new Protocol();

							c_vo.setCust_id(signin.cvo.getCust_id());
							c_vo.setCust_phone(ch_phone);
							System.out.println("받은 값(아이디): " + c_vo.getCust_id());
							System.out.println("받은 값(폰번호): " + c_vo.getCust_phone());

							p.setC_vo(c_vo);
							System.out.println(p.getC_vo().getCust_id());
							System.out.println(p.getC_vo().getCust_phone());

							p.setCmd(506);

							signin.out.writeObject(p);
							signin.out.flush();

							System.out.println("프로토콜506 보내기");
							// 알림 창 띄우기
							JOptionPane.showMessageDialog(null, "핸드폰 번호 변경 성공", "알림", JOptionPane.INFORMATION_MESSAGE);

							// 핸드폰 번호 변경 후 레이블 업데이트
							mp_an_phone_label.setText(ch_phone);

							break;
						}

					} catch (Exception e2) {
						System.out.println(e2);
					}
				}
			}
		});

		// 탈퇴하기 버튼
		cust_delete_bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "정말 탈퇴하시겠습니까?", "알림", JOptionPane.YES_NO_OPTION);
				try {
					if (choice == JOptionPane.YES_OPTION) {
						CustomerVO c_vo = new CustomerVO();
						Protocol p = new Protocol();

						c_vo.setCust_id(signin.cvo.getCust_id());
						c_vo.setDelete_yn("1");

						p.setC_vo(c_vo);
						System.out.println(p.getC_vo().getCust_id());
						System.out.println(p.getC_vo().getDelete_yn());

						p.setCmd(507);
						signin.out.writeObject(p);
						signin.out.flush();
						System.out.println("프로토콜507 보내기");
						
						JOptionPane.showMessageDialog(null, "탈퇴가 완료되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
						sign_in.card.show(sign_in.pg, "sign_in");
					}
				} catch (Exception e2) {
				}
			}
		});

		// 메인으로 버튼
		mp_back_bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				signin.card.show(signin.pg, "main_login");
			}
		});
	}
}
