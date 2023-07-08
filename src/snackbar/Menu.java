//package snackbar;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.SwingConstants;
//
//import c_loginout.Sign_in;
//import movie_server.Point_VO;
//
//
//public class Menu extends JPanel {
//	
//	Sign_in sign_in;
//	
//	JPanel jp1, jp11, jp12, jp2, jp21,jp211,jp212,jp22,jp23, jp3, s_menujp, s_jp1,s_jp2,s_jp3, s_jp;
//	
//	JButton jb1, jb2, jb21,jb22,jb23,jb4,jb5,jb6,jb7,jb8,jb9,
//	jb10,jb11,jb12,jb13,jb14,jb15,jb16,jb17,jb18, jb30, s_jb1,s_jb2,s_jb3;
//	
//	JLabel jlb1,jlb2, jlb3, jlb4, s_jlb1,s_jlb2,s_jlb3;
//	
//	JScrollPane jsp,jsp1;
//	JTextArea jta;
//	int su= 0;
//		public Menu(Sign_in signin) {
//			this.sign_in = signin;
//			
//			
//	// 패널 만들기
//	jp1 = new JPanel();
//	jp1.setPreferredSize(new Dimension(800, 160));
//	jp11 = new JPanel();
//	jp11.setPreferredSize(new Dimension(800, 80));
//	jp11.setLayout(new FlowLayout(FlowLayout.CENTER, 130, 0));
//	jp12 = new JPanel();
//	jp12.setPreferredSize(new Dimension(800, 80));
//	jp2 = new JPanel();
//	jp2.setPreferredSize(new Dimension(600, 550));
//	jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
//	jp21 = new JPanel();
//	jp21.setPreferredSize(new Dimension(550, 500));
//	jp211 = new JPanel();
//	jp211.setLayout(new GridLayout(0, 3));
//	jp211.setPreferredSize(new Dimension(510, 60));
//	jp212 = new JPanel();
//	jp212.setLayout(new GridLayout(9, 2));
//	jp212.setPreferredSize(new Dimension(500, 500));
//	jsp = new JScrollPane(jp212, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//	jsp.setPreferredSize(new Dimension(500, 400));
//	
//	jp22 = new JPanel(); //200,550
//	jp22.setPreferredSize(new Dimension(200,500));
//	jp3 = new JPanel();
//	
//	s_menujp = new JPanel();
//	s_menujp.setPreferredSize(new Dimension(185, 90));
//	s_menujp.setBackground(Color.WHITE);
//	s_jp1 = new JPanel();
//	s_jp1.setPreferredSize(new Dimension(180, 30));
//	s_jp2 = new JPanel();
//	s_jp2.setPreferredSize(new Dimension(180, 30));
//	s_jp3 = new JPanel();
//	s_jp3.setPreferredSize(new Dimension(180, 30));
//	
//	// 버튼 만들기
//	jb1 = new JButton("뒤로가기");
//	jb2 = new JButton("포인트 충전");
//	
//	jb21 = new JButton("주전부리");
//	jb22 = new JButton("음료");
//	jb23 = new JButton("양념");
//	
//	jb4 = new JButton("찹쌀떡");
//	jb5 = new JButton("메밀묵"); 
//	jb6 = new JButton("인절미"); 
//	jb7 = new JButton("메밀전병"); 
//	jb8 = new JButton("호떡"); 
//	jb9 = new JButton("찰떡"); 
//	jb10 = new JButton("가래떡"); 
//	jb11 = new JButton("감자떡"); 
//	
//	jb12 = new JButton("수정과"); 
//	jb13 = new JButton("식혜"); 
//	jb14 = new JButton("소쥬"); 
//	jb15 = new JButton("삐루"); 
//	
//	jb16 = new JButton("조청"); 
//	jb17 = new JButton("콩고물"); 
//	jb18 = new JButton("고추장"); 
//
//	jb30 = new JButton("결제 하기");
//	
//	s_jb1 = new JButton("+");
//	s_jb2 = new JButton("-");
//	s_jb3 = new JButton("X");
//	
//	
//	//라벨 만들기
//	Point_VO vo = new Point_VO();
//	jlb1 = new JLabel(vo.getCUST_ID()+"님 잔여포인트"+vo.getREMAINING_POINT());
//	jlb2 = new JLabel("4딸_라 매점");
//	jlb2.setFont(new Font("돋움",	 Font.BOLD, 40));
//	jlb3 = new JLabel("장바구니");
//	jlb3.setFont(new Font("굴림",	 Font.BOLD, 25));
//	jlb4= new JLabel("총 금액은");
//	jlb4.setFont(new Font("굴림",	 Font.BOLD, 25));
//	
//	s_jp = new JPanel();
//	s_jp.setPreferredSize(new Dimension(180,370));
//	s_jp.setBackground(Color.WHITE);
//	s_jp.setLayout(new GridLayout(0, 3));
//	jsp1 = new JScrollPane(s_jp, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//	
//	
//	s_jlb1 = new JLabel("");
//	s_jlb1.setPreferredSize(new Dimension(150, 30));
//	s_jlb1.setHorizontalAlignment(SwingConstants.CENTER);
//	s_jlb2 = new JLabel("0");
//	
//	s_jlb2.setPreferredSize(new Dimension(50, 30));
//	s_jlb2.setHorizontalAlignment(SwingConstants.CENTER);
//	s_jlb3 = new JLabel("10,000원");
//	s_jlb3.setPreferredSize(new Dimension(140, 30));
//	s_jlb3.setHorizontalAlignment(SwingConstants.CENTER);
//	
//	//버튼 붙이기
//	jp11.add(jb1);
//	jp11.add(jlb1);
//	jp11.add(jb2);
//	
//	jp12.add(jlb2);
//	
//	jp211.add(jb21);
//	jp211.add(jb22);
//	jp211.add(jb23);
//	
//	jp22.add(jlb3, BorderLayout.NORTH);
//	jp22.add(jsp1, BorderLayout.CENTER);
//	jp22.add(jlb4, BorderLayout.SOUTH);
//	
//	s_jp1.add(s_jlb1);
//	
//	s_jp2.add(s_jb1);
//	s_jp2.add(s_jlb2);
//	s_jp2.add(s_jb2);
//	
//	s_jp3.add(s_jlb3);
//	s_jp3.add(s_jb3);
//	
//	// 사진 넣기
//	ImageIcon icon = new ImageIcon("src/images/java1.png");
//	
//	
//	jp212.add(jb4);
//	jb4.setPreferredSize(new Dimension(230, 240));
//	jb4.setIcon(icon);
//	jp212.add(jb5);
//	jb5.setPreferredSize(new Dimension(230, 240));
//	jp212.add(jb6);
//	jb6.setPreferredSize(new Dimension(230, 240));
//	jp212.add(jb7);
//	jb7.setPreferredSize(new Dimension(230, 240));
//	jp212.add(jb8);
//	jb8.setPreferredSize(new Dimension(230, 240));
//	jp212.add(jb9);
//	jb9.setPreferredSize(new Dimension(230, 240));
//	jp212.add(jb10);
//	jb10.setPreferredSize(new Dimension(230, 240));
//	jp212.add(jb11);
//	jb11.setPreferredSize(new Dimension(230, 240));
//	jp212.add(jb12);
//	jb12.setPreferredSize(new Dimension(230, 240));
//	jp212.add(jb13);
//	jb13.setPreferredSize(new Dimension(230, 240));
//	jp212.add(jb14);
//	jb14.setPreferredSize(new Dimension(230, 240));
//	jp212.add(jb15);
//	jb15.setPreferredSize(new Dimension(230, 240));
//	jp212.add(jb16);
//	jb16.setPreferredSize(new Dimension(230, 240));
//	jp212.add(jb17);
//	jb17.setPreferredSize(new Dimension(230, 240));
//	jp212.add(jb18);
//	jb18.setPreferredSize(new Dimension(230, 240));
//	
//	jp3.add(jb30);
//	jb30.setPreferredSize(new Dimension(240, 80));
//	
//	s_menujp.add(s_jp1, BorderLayout.NORTH);
//	s_menujp.add(s_jp2, BorderLayout.CENTER);
//	s_menujp.add(s_jp3, BorderLayout.SOUTH);
//	
//	// 패널 붙이기
//	jp1.add(jp11, BorderLayout.NORTH);
//	jp1.add(jp12, BorderLayout.CENTER);
//	jp2.add(jp21);
//	jp2.add(jp22);
//	jp21.add(jp211, BorderLayout.NORTH);
//	jp21.add(jsp, BorderLayout.CENTER);
//	
//		
//	add(jp1, BorderLayout.NORTH);
//	add(jp2, BorderLayout.CENTER);
//	add(jp3, BorderLayout.SOUTH);
//			
//	setSize(800, 800);
//	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	//setLocationRelativeTo(null); // 화면 가운데에 표시
//	setVisible(true);
//
//	
//	jb2.addActionListener(new ActionListener() {
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			PointCharge point = new PointCharge();
//			
//			
//		}
//	});
//	jb4.addActionListener(new ActionListener() {
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			 jsp1.setViewportView(s_menujp);
//			  int value = Integer.parseInt(s_jlb2.getText()); // 현재 값 가져오기
//		        value++; // 값 증가
//			  
//		        s_jlb2.setText(Integer.toString(value)); // 값 업데이트
//		        JButton button = (JButton) e.getSource(); // 클릭된 버튼 가져오기
//		        String buttonText = button.getText(); // 버튼의 이름 가져오기
//		        s_jlb1.setText(buttonText); // 라벨에 버튼 이름 설정
//		        
//		       
//			
//		}
//	});
//	 s_jb1.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int value = Integer.parseInt(s_jlb2.getText()); // 현재 값 가져오기
//		        value++; // 값 증가
//		        s_jlb2.setText(String.valueOf(value));
//				
//			}
//		});
//	jb5.addActionListener(new ActionListener() {
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			 // 새로운 s_menujp 패널 생성
//	        JPanel newMenuPanel = new JPanel();
//	        newMenuPanel.setPreferredSize(new Dimension(185, 90));
//	        // 패널에 원하는 구성 요소 추가
//	        
//	        // jsp1 패널에 새로운 s_menujp 패널 추가
//	        jsp1.setViewportView(newMenuPanel);
//		          }
//			
//		
//	});
//	
//	}
//}