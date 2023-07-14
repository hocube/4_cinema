package c_loginout;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import movie_server.LoginInfo_VO;
import movie_server.MobileTicket_VO;
import movie_server.Pay_VO;
import movie_server.Protocol;
import movie_server.TicketBox_VO;
import pay.PointChargeDialog;
import pay.PointChargeDialog.PointChargeListener;

public class Main_login extends JPanel {
	private JButton main_point_charge_bt;

	Sign_in sign_in;

	public Main_login(Sign_in signin) {

		this.sign_in = signin;

		this.setLayout(null);

		// 로고이미지
		JLabel logo = new JLabel();
		logo.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		logo.setBounds(99, 126, 575, 180);

		this.add(logo);

		JButton mobile_ticket_bt = new JButton("티켓리스트");
		mobile_ticket_bt.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		mobile_ticket_bt.setBounds(35, 35, 122, 49);
		this.add(mobile_ticket_bt);

		JButton mypage_bt = new JButton("마이페이지");
		mypage_bt.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		mypage_bt.setBounds(466, 35, 89, 25);
		this.add(mypage_bt);

		JButton sign_out_bt = new JButton("로그아웃");
		sign_out_bt.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		sign_out_bt.setBounds(658, 35, 89, 25);
		this.add(sign_out_bt);

		JLabel login_name_label = new JLabel(signin.cvo.getCust_name() + " 님");
		login_name_label.setHorizontalAlignment(SwingConstants.CENTER);
		login_name_label.setBounds(330, 22, 57, 15);
		this.add(login_name_label);

		JLabel login_point_label = new JLabel("잔여포인트 : " + signin.cvo.getPoint());
		login_point_label.setHorizontalAlignment(SwingConstants.CENTER);
		login_point_label.setBounds(308, 41, 146, 15);
		this.add(login_point_label);

		// 포인트 충전 버튼 생성 및 추가
		main_point_charge_bt = new JButton("포인트 충전");
		main_point_charge_bt.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		main_point_charge_bt.setBounds(563, 35, 89, 25);
		add(main_point_charge_bt);

		JLabel lblNewLabel = new JLabel("절찬 상영중");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblNewLabel.setBounds(48, 316, 194, 41);
		this.add(lblNewLabel);

		JButton btnNewButton = new JButton();
		btnNewButton.setBounds(48, 382, 131, 210);
		this.add(btnNewButton);

		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setBounds(223, 382, 131, 210);
		this.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton();
		btnNewButton_2.setBounds(402, 382, 131, 210);
		this.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton();
		btnNewButton_3.setBounds(585, 382, 131, 210);
		this.add(btnNewButton_3);

		JButton ticketing_bt = new JButton("빠른 예매");
		ticketing_bt.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		ticketing_bt.setBounds(237, 677, 122, 49);
		this.add(ticketing_bt);

		JButton snack_bt = new JButton("매점");
		snack_bt.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		snack_bt.setBounds(412, 677, 122, 49);
		this.add(snack_bt);

		setVisible(true);

		// 포스터 이미지
		ImageIcon originalIcon = new ImageIcon("src/images/반지의제왕.png");
		Image originalImage = originalIcon.getImage();
		Image resizedImage = originalImage.getScaledInstance(btnNewButton.getWidth(), btnNewButton.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImage);

		ImageIcon originalIcon1 = new ImageIcon("src/images/해리포터.png");
		Image originalImage1 = originalIcon1.getImage();
		Image resizedImage1 = originalImage1.getScaledInstance(btnNewButton.getWidth(), btnNewButton.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedIcon1 = new ImageIcon(resizedImage1);

		ImageIcon originalIcon2 = new ImageIcon("src/images/뽀로로.png");
		Image originalImage2 = originalIcon2.getImage();
		Image resizedImage2 = originalImage2.getScaledInstance(btnNewButton.getWidth(), btnNewButton.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);

		ImageIcon originalIcon3 = new ImageIcon("src/images/엘리멘탈.jpg");
		Image originalImage3 = originalIcon3.getImage();
		Image resizedImage3 = originalImage3.getScaledInstance(btnNewButton.getWidth(), btnNewButton.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		ImageIcon resizedIcon3 = new ImageIcon(resizedImage3);

		// 포스터이미지 붙이기.
		btnNewButton.setIcon(resizedIcon);
		btnNewButton_1.setIcon(resizedIcon1);
		btnNewButton_2.setIcon(resizedIcon2);
		btnNewButton_3.setIcon(resizedIcon3);

		// 로고이미지.
		// 이미지 로딩 및 크기 조절
		ImageIcon originalIcon4 = new ImageIcon("src/images/logo.png");
		Image originalImage4 = originalIcon4.getImage();
		int lblWidth = logo.getWidth(); 
		int lblHeight = logo.getHeight();
		Image resizedImage4 = originalImage4.getScaledInstance(lblWidth, lblHeight, java.awt.Image.SCALE_SMOOTH);

		// 조정된 이미지를 이용하여 ImageIcon 객체 생성
		ImageIcon resizedIcon4 = new ImageIcon(resizedImage4);

		// 라벨에 이미지 아이콘 설정
		logo.setIcon(resizedIcon4); 

		// 1. 모바일티켓 버튼
		mobile_ticket_bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MobileTicket_VO m_vo = new MobileTicket_VO();
					Protocol p = new Protocol();

					m_vo.setCust_id(signin.cvo.getCust_id());
					p.setM_vo(m_vo);
					p.setCmd(104);

					signin.out.writeObject(p);
					signin.out.flush();

					signin.card.show(signin.pg, "t_list");
				} catch (Exception e2) {

				}
			}
		});

		// 2. 마이페이지 버튼
		mypage_bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPasswordField passwordField = new JPasswordField();
				int option = JOptionPane.showOptionDialog(null, passwordField, "비밀번호를 입력해주세요",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

				if (option == JOptionPane.OK_OPTION) {
					char[] password = passwordField.getPassword();
					String mypage_inpw = new String(password);
					if (mypage_inpw.equals(signin.cvo.getCust_password())) {
						signin.card.show(signin.pg, "mypage");
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.", "알림", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		// 3. 로그아웃 버튼
		sign_out_bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int r = JOptionPane.showConfirmDialog(getParent(), "로그아웃 하시겠습니까 ?", "로그아웃", JOptionPane.YES_NO_OPTION);
				if (r == 0) {

					try {
						LoginInfo_VO l_vo = new LoginInfo_VO();
						Protocol p = new Protocol();
						l_vo.setCust_id(signin.cvo.getCust_id());
						p.setL_vo(l_vo);
						p.setCmd(504);

						signin.out.writeObject(p);
						signin.out.flush();

					} catch (Exception e2) {
						System.out.println(e2);
					}
				}
			}
		});

		// 4. 포인트충전
		main_point_charge_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PointChargeDialog dialog = new PointChargeDialog(
						(Frame) SwingUtilities.getWindowAncestor(Main_login.this));
				dialog.setPointChargeListener(new PointChargeListener() {
					public void onPointCharge(int amount) {

						try {
							Pay_VO p_vo = new Pay_VO();
							Protocol p = new Protocol();

							p_vo.setCust_id(signin.cvo.getCust_id());
							p_vo.setPoint(amount);
							p.setP_vo(p_vo);
							p.setCmd(102);

							signin.out.writeObject(p);
							signin.out.flush();

							// 오류나면 빼기
							JOptionPane.showMessageDialog(null, "충전이 완료되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);

							signin.cvo.setPoint(signin.cvo.getPoint() + amount);
							login_point_label.setText("잔여포인트 : " + signin.cvo.getPoint());
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				});
				dialog.setVisible(true);
			}
		});

		// 5. 각 포스터 누르면 매표소로 각 이름 체크되서가져가기.
		btnNewButton.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {
				String m_1 = "반지의제왕";

				try {
					TicketBox_VO t_vo = new TicketBox_VO();
					Protocol p = new Protocol();
					t_vo.setMovie_name(m_1);
					p.setT_vo(t_vo);
					p.setCmd(303);

					signin.out.writeObject(p);
					signin.out.flush();

					sign_in.card.show(sign_in.pg, "to_main");
					sign_in.to_main.getMoreButton().setEnabled(true);
				} catch (Exception e2) {

				}
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {
				String m_2 = "해리포터와비밀의방";

				try {
					TicketBox_VO t_vo = new TicketBox_VO();
					Protocol p = new Protocol();
					t_vo.setMovie_name(m_2);
					p.setT_vo(t_vo);
					p.setCmd(303);
					signin.out.writeObject(p);
					signin.out.flush();

					sign_in.card.show(sign_in.pg, "to_main");
					sign_in.to_main.getMoreButton().setEnabled(true);
				} catch (Exception e2) {

				}

			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {
				String m_3 = "뽀로로";

				try {
					TicketBox_VO t_vo = new TicketBox_VO();
					Protocol p = new Protocol();
					t_vo.setMovie_name(m_3);
					p.setT_vo(t_vo);
					p.setCmd(303);

					signin.out.writeObject(p);
					signin.out.flush();

					sign_in.card.show(sign_in.pg, "to_main");
					sign_in.to_main.getMoreButton().setEnabled(true);
				} catch (Exception e2) {

				}

			}
		});

		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String m_4 = "엘리멘탈";

				try {
					TicketBox_VO t_vo = new TicketBox_VO();
					Protocol p = new Protocol();
					t_vo.setMovie_name(m_4);
					p.setT_vo(t_vo);
					p.setCmd(303);

					signin.out.writeObject(p);
					signin.out.flush();

					sign_in.card.show(sign_in.pg, "to_main");
					sign_in.to_main.getMoreButton().setEnabled(true);
				} catch (Exception e2) {

				}

			}
		});

		// 6. 빠른예매 버튼 누르면 바로 매표소로
		ticketing_bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					sign_in.card.show(signin.pg, "to_main");

					sign_in.to_main.model1.setRowCount(0);

				

					Protocol p = new Protocol();
					p.setCmd(301);
					sign_in.out.writeObject(p);
					sign_in.out.flush();

					sign_in.to_main.getMoreButton().setEnabled(false);
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});

		// 매점으로 버튼
		snack_bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sign_in.card.show(signin.pg, "snack1");

			}
		});

	}
}