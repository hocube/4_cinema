package snackbar;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import c_loginout.Sign_in;
import movie_server.LoginInfo_VO;
import movie_server.Protocol;

public class Admin_panel extends JPanel {
	Sign_in sign_in;

		
		public Admin_panel(Sign_in signin) {
			this.sign_in = signin;
			
			this.setLayout(null);
			
			JButton btnNewButton = new JButton("회원 관리");
			btnNewButton.setBounds(12, 230, 180, 325);
			this.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("주문 관리");
			btnNewButton_1.setBounds(211, 230, 180, 325);
			this.add(btnNewButton_1);
			
			JButton btnNewButton_2 = new JButton("영화 관리");
			btnNewButton_2.setBounds(405, 230, 180, 325);
			this.add(btnNewButton_2);
			
			JButton btnNewButton_3 = new JButton("발주 관리");
			btnNewButton_3.setBounds(598, 230, 180, 325);
			this.add(btnNewButton_3);
			
			
			JLabel logo = new JLabel();
			//logo.setFont(new Font("맑은 고딕", Font.BOLD, 16));
			logo.setBounds(95, 31, 575, 180); //131
			
			this.add(logo);
			
			ImageIcon originalIcon4 = new ImageIcon("src/images/logo.png");
			Image originalImage4 = originalIcon4.getImage();
			int lblWidth = logo.getWidth();  // 라벨의 크기를 얻어옵니다.
			int lblHeight = logo.getHeight();
			Image resizedImage4 = originalImage4.getScaledInstance(lblWidth, lblHeight,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon resizedIcon4 = new ImageIcon(resizedImage4);
			logo.setIcon(resizedIcon4);
			
			
			JButton adminout = new JButton("로그아웃");
			adminout.setFont(new Font("맑은 고딕", Font.BOLD, 16));
			adminout.setBounds(330, 680, 122, 49);
			this.add(adminout);
			
			btnNewButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					sign_in.card.show(sign_in.pg, "c_admin");
					
				}
			});
			
			btnNewButton_1.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					sign_in.card.show(sign_in.pg, "o_admin");
				}
			});
			
			btnNewButton_2.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					sign_in.card.show(sign_in.pg, "m_admin");
				}
			});
			
			btnNewButton_3.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					sign_in.card.show(sign_in.pg, "s_admin");
				}
			});
			
			adminout.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
							try {
								System.out.println("다이얼로그 값 받기 완");

								LoginInfo_VO l_vo = new LoginInfo_VO();
								Protocol p = new Protocol();
								System.out.println("로그아웃 프로토콜 생성완");

								l_vo.setCust_id(signin.cvo.getCust_id());
								p.setL_vo(l_vo);
								p.setCmd(504);

								signin.out.writeObject(p);
								signin.out.flush();

							} catch (Exception e2) {
								System.out.println(e2);
							}
						}					
			});
			
		}
	}