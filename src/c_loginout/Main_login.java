package c_loginout;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import movie_server.Pay_VO;
import movie_server.Protocol;
import pay.PointChargeDialog;
import pay.PointChargeDialog.PointChargeListener;

public class Main_login extends JPanel {
	private JButton main_point_charge_bt;
	
	Sign_in sign_in;
	// Movie_chart_view1 v1 = new Movie_chart_view1(); //두개 안씀
	// Movie_chart_view2 v2 = new Movie_chart_view2();

	// CardLayout card = new CardLayout();

	public Main_login(Sign_in signin) {
		
		this.sign_in = signin;

		this.setLayout(null);

		// 로고이미지 
		JButton logo_bt = new JButton();
		logo_bt.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		logo_bt.setBounds(99, 126, 575, 131);
		
		this.add(logo_bt);

		// 하단에 액션리스너
		JButton mobile_ticket_bt = new JButton("모바일 티켓");
		mobile_ticket_bt.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		mobile_ticket_bt.setBounds(35, 35, 122, 49);
		this.add(mobile_ticket_bt);

		// 하단에 액션리스너
		JButton mypage_bt = new JButton("마이페이지");
		mypage_bt.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		mypage_bt.setBounds(466, 35, 89, 25);
		this.add(mypage_bt);

		// 하단에 액션리스너
		JButton sign_out_bt = new JButton("로그아웃");
		sign_out_bt.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		sign_out_bt.setBounds(658, 35, 89, 25);
		this.add(sign_out_bt);

		// 로그인시, 로그인한 정보 얻어서, 그사람의 이름 갖고오게 하기.
		// 여기서 런을 안쓰게된다면 어떻게 프로토콜 부르지?
		// sign_in 에서 run으로 명령쓰고, 여기로 xxx.add 였나? 다시 확인하기. ***
		
		// [0709 지호] 
		JLabel login_name_label = new JLabel(signin.p.getC_vo().getCust_name() + " 님");
		login_name_label.setHorizontalAlignment(SwingConstants.CENTER);
		login_name_label.setBounds(330, 22, 57, 15);
		this.add(login_name_label);

		// 위와 동일하게, run 이 필요한, 사람의 포인트.
		// 로그인시, 로그인한 정보 얻어서, 그사람의 포인트 갖고오게 하기.
		// 여기서 런을 안쓰게된다면 어떻게 프로토콜 부르지?
		// sign_in 에서 run으로 명령쓰고, 여기로 xxx.add 였나? 다시 확인하기. ***
		
		// [0709 지호] 
		JLabel login_point_label = new JLabel("잔여포인트 : " + signin.p.getC_vo().getPoint());
		login_point_label.setHorizontalAlignment(SwingConstants.CENTER);
		login_point_label.setBounds(308, 41, 146, 15);
		this.add(login_point_label);

		// 하단에 액션리스너
        // 포인트 충전 버튼 생성 및 추가
        main_point_charge_bt = new JButton("포인트 충전");
        main_point_charge_bt.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        main_point_charge_bt.setBounds(563, 35, 89, 25);
        add(main_point_charge_bt);

		// 이건 제목으로밖에 안쓰기 때문에 생성만.
		JLabel lblNewLabel = new JLabel("절찬 상영중");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblNewLabel.setBounds(12, 316, 194, 41);
		this.add(lblNewLabel);

		// 영화 포스터 버튼들. 각 포스터 버튼 눌렀을때, 매표소로 넘어가고, 클릭한 해당 버튼의
		// cmd 줘서 누른 포스터가 먼저 제일먼저 클릭된 상태로 매표소 뜨게하기
		// => 이렇게하면 시간도 이어놔서, 바로 될거같음
		// 근데, 테이블인데 checked가 되는지 확인하기 ******
		// =>질문의 의도) 프론트배울때처럼, 라운드나 체크박스가 미리 체크되게하는게 테이블 리스트도 가능하냐?
		JButton btnNewButton = new JButton();
		btnNewButton.setBounds(48, 382, 131, 210);
		this.add(btnNewButton);

		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setBounds(211, 381, 131, 210);
		this.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton();
		btnNewButton_2.setBounds(384, 381, 131, 210);
		this.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton();
		btnNewButton_3.setBounds(563, 381, 131, 210);
		this.add(btnNewButton_3);

		// 하단의 액션리스너
		JButton ticketing_bt = new JButton("빠른 예매");
		ticketing_bt.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		ticketing_bt.setBounds(237, 677, 122, 49);
		this.add(ticketing_bt);

		
		//하단의 액션리스너 , 매점팀.
		JButton snack_bt = new JButton("매점");
		snack_bt.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		snack_bt.setBounds(412, 677, 122, 49);
		this.add(snack_bt);

		
		//상영중인 영화 안하기로해서 이건 주석.
		/*
		 * JButton btnNewButton_4 = new JButton("+상영중인 영화 더보기");
		 * btnNewButton_4.setBounds(204, 334, 167, 23); this.add(btnNewButton_4);
		 */

		setVisible(true);

		//이미지 경로, images 파일에 밑의것이 있어야 활성화된다.
		ImageIcon img = new ImageIcon("src/images/반지의제왕.png");
		ImageIcon img1 = new ImageIcon("src/images/해리포터.png");
		ImageIcon img2 = new ImageIcon("src/images/뽀로로.png");
		ImageIcon img3 = new ImageIcon("src/images/엘리멘탈.jpg");
		ImageIcon logo_img = new ImageIcon("src/images/logo.png");

		// 포스터이미지 붙이기.
		btnNewButton.setIcon(img);
		btnNewButton_1.setIcon(img1);
		btnNewButton_2.setIcon(img2);
		btnNewButton_3.setIcon(img3);
		// 로고이미지 붙이기.
		logo_bt.setIcon(logo_img);


		// 버튼 액션리스너 =================================================

		// 1. 모바일티켓 버튼
		mobile_ticket_bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					signin.card.show(signin.pg, "t_list");
					System.out.println("모바일티켓버튼 눌러 티켓리스트 전환 성공");
					Protocol p = new Protocol();
					p.setCmd(104);
					signin.out.writeObject(p);
					signin.out.flush();
				} catch (Exception e2) {
					
				}
				
			}
		});

		// 2. 마이페이지 버튼
		mypage_bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//마이페이지 화면단 구성 필요!!!******

			}
		});

		// 3. 로그아웃 버튼
		sign_out_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		// 4. 포인트충전
		main_point_charge_bt.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        PointChargeDialog dialog = new PointChargeDialog((Frame) SwingUtilities.getWindowAncestor(Main_login.this));
		        dialog.setPointChargeListener(new PointChargeListener() {
		            public void onPointCharge(int amount) {
		            	
		                // PointChargeDialog에서 선택한 포인트
		                System.out.println("충전된 포인트: " + amount);
		                
						try {
							Pay_VO p_vo = new Pay_VO();
							Protocol p = new Protocol();
							
							p_vo.setCust_id(signin.p.getC_vo().getCust_id());
							p_vo.setPoint(amount);
							p.setP_vo(p_vo);
							p.setCmd(102);
							
							signin.out.writeObject(p);
							signin.out.flush();
							
		                    signin.p.getC_vo().setPoint(signin.p.getC_vo().getPoint() + amount);
		                    login_point_label.setText("잔여포인트 : " + signin.p.getC_vo().getPoint());
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
				// 여기엔 제일 밑에 하나의 메서드를 작성해서 동일하게 할수있도록 하자.
				// 각 다른 포스터를 클릭해도, get으로 가져와서 sql문으로 확인할거니.
				// 동일할거라 생각된다. cmd도 찾아달라는 동일명령어일테니...?
				
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 여기엔 제일 밑에 하나의 메서드를 작성해서 동일하게 할수있도록 하자.
				// 각 다른 포스터를 클릭해도, get으로 가져와서 sql문으로 확인할거니.
				// 동일할거라 생각된다. cmd도 찾아달라는 동일명령어일테니...?

			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 여기엔 제일 밑에 하나의 메서드를 작성해서 동일하게 할수있도록 하자.
				// 각 다른 포스터를 클릭해도, get으로 가져와서 sql문으로 확인할거니.
				// 동일할거라 생각된다. cmd도 찾아달라는 동일명령어일테니...?

			}
		});

		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 여기엔 제일 밑에 하나의 메서드를 작성해서 동일하게 할수있도록 하자.
				// 각 다른 포스터를 클릭해도, get으로 가져와서 sql문으로 확인할거니.
				// 동일할거라 생각된다. cmd도 찾아달라는 동일명령어일테니...?

			}
		});
		//여기까지 5번 
		
		
		//6. 빠른예매 버튼 누르면 바로 매표소로 
		ticketing_bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				signin.card.show(signin.pg, "to_main");
				System.out.println("빠른예매버튼 눌러 매표소 전환 성공");
				
			}
		});

		//매점버튼 클릭시 매점화면으로 이동. 이 부분은 우선 매점팀 
		snack_bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//signin.card.show(signin.pg, "snack");
				System.out.println("매점버튼 눌러 매점 전환 성공");
				
			}
		});
	}
	

}