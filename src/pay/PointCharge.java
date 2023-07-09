package pay;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import c_loginout.Sign_in;

public class PointCharge extends JPanel{
	
	Sign_in sign_in;
	JPanel jp1, jp2, jp3, jp4, centerPanel;
	JRadioButton jrb1, jrb2, jrb3, jrb4, jrb5, jrb6;
	JButton pay, cancel;
	ButtonGroup ButtonGroup;
	
	public PointCharge(Sign_in signin) {
		this.sign_in = signin;
		
		jp1 = new JPanel();
		JLabel title = new JLabel("포인트 충전");
		title.setFont(new Font("굴림", Font.BOLD, 20));
		jp1.setBorder(new EmptyBorder(10, 0, 0, 0));
		jp1.add(title);
		
		// 5000원, 10000원, 15000원
		jp2 = new JPanel();
		jp2.setLayout(new BoxLayout(jp2, BoxLayout.Y_AXIS));
		jp2.setBorder(new EmptyBorder(10, 50, 20, 20));
		ButtonGroup = new ButtonGroup();
		jrb1 = new JRadioButton("5,000원");
		jrb1.setFont(new Font("돋움", Font.BOLD, 15));
		jrb2 = new JRadioButton("10,000원");
		jrb2.setFont(new Font("돋움", Font.BOLD, 15));
		jrb3 = new JRadioButton("15,000원");
		jrb3.setFont(new Font("돋움", Font.BOLD, 15));
		ButtonGroup.add(jrb1);
		ButtonGroup.add(jrb2);
		ButtonGroup.add(jrb3);
		jp2.setLayout(new GridLayout(3,0));
		jp2.add(jrb1);
		jp2.add(jrb2);
		jp2.add(jrb3);
		
		// 20000원, 25000원, 30000원
		jp3 = new JPanel();
		jp3.setLayout(new BoxLayout(jp3, BoxLayout.Y_AXIS));
		jp3.setBorder(new EmptyBorder(10, 20, 20, 20));
		jrb4 = new JRadioButton("20,000원");
		jrb4.setFont(new Font("돋움", Font.BOLD, 15));
		jrb5 = new JRadioButton("25,000원");
		jrb5.setFont(new Font("돋움", Font.BOLD, 15));
		jrb6 = new JRadioButton("30,000원");
		jrb6.setFont(new Font("돋움", Font.BOLD, 15));
		ButtonGroup.add(jrb4);
		ButtonGroup.add(jrb5);
		ButtonGroup.add(jrb6);
		jp3.setLayout(new GridLayout(3,0));
		jp3.add(jrb4);
		jp3.add(jrb5);
		jp3.add(jrb6);
		
		//결재, 취소
		jp4 = new JPanel();
		pay = new JButton("충전하기");
		cancel = new JButton("취소하기");
        jp4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10)); 
        // FlowLayout으로 변경하고 간격 조정
		jp4.add(pay);
        jp4.add(Box.createHorizontalStrut(20)); 
        // 가로 간격을 추가하기 위해 사용
		jp4.add(cancel);
		
		// 가운데 패널 생성 및 설정
		centerPanel = new JPanel(new GridLayout(1, 2));
		centerPanel.add(jp2, BorderLayout.CENTER);
        centerPanel.add(jp3, BorderLayout.EAST);
		
		add(jp1, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(jp4, BorderLayout.SOUTH);
		
		setSize(350, 250);
		//setLocationRelativeTo(null);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setResizable(false); // 크기 조절 비활성화
		
		
		// 충전하기 버튼 
//		pay.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int amount = getSelectedAmount(); //getSelectedAmount()의 return값을 amount에 저장
//				System.out.println("amount:" + amount); 
//				if(amount != 0) { // 0이 아닐때만 update = 값이 실제로 있을 때만
//					Pay_VO pay_vo = new Pay_VO();
//					pay_vo.setPoint(amount); //pay_vo에 있는 point에 amount를 setting.
//					//Session.getCurrentUserId의 값을 currentUserId라는 변수에 담았음.
//					String currentUserId = Session.getCurrentUserId();
//					//Cust_id를 currentUserId로 셋팅
//					pay_vo.setCust_id(currentUserId);
//					 System.out.println("actionPerformed: " + pay_vo.getCust_id());
//					//pay_vo.setCust_id(Pay.currentUserId); //현재 로그인한 회원의 ID를 전달
//					Pay_DAO.updatePoint(pay_vo);
//					
//					// 충전 완료 알럿창
//					Object[] paybutton = {"결제하기"}; //"결제하기"로 버튼 이름 바꾸기
//					int option = JOptionPane.showOptionDialog(PointCharge.this, "충전이 완료되었습니다.", "충전 완료", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, paybutton, paybutton[0]);
//					JOptionPane.getRootFrame().dispose(); // 알림창 닫기
//					
//					// 결제 확인창으로 돌아가기
//					if(option == 0) {
//						//dispose(); //현재 창 닫기
//						Pay pay = new Pay(signin); //나중에 결제확인창으로 변경해야함! 확인을 위해 임시로 해둠!
//						pay.setVisible(true);
//					}
//				}
//			}
//		});
//	}
//
//	// 충전 금액 설정 메서드
//	private int getSelectedAmount() {
//		
//		if(jrb1.isSelected())
//			return 5000;
//		
//		if(jrb2.isSelected())
//			return 10000;
//		
//		if(jrb3.isSelected())
//			return 15000;
//		
//		if(jrb4.isSelected())
//			return 20000;
//		
//		if(jrb5.isSelected())
//			return 25000;
//		
//		if(jrb5.isSelected())
//			return 30000;
//		
//		return 0; //버튼 선택 안 했을 때
//		
//	}
	}
}