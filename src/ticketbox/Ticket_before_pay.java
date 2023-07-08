package ticketbox;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import c_loginout.Sign_in;
//이걸로 합치기!
//결제정보창, 결제확인창
public class Ticket_before_pay extends JPanel{
	Sign_in sign_in;
	JPanel north,center,south,v_point,con_pay,bt_p ;
	//각 위치별로 구분하기위해 패널들을 선언함.  그리고 포인트패널과 메인인 결제확인내용패널, 버튼이붙을 패널과 
	JLabel point,show_point,pay,show_time,show_name,show_date,show_room,show_peo,show_seat,show_price;
	//포인트와 포인트표시 라벨, 각 영화이름, 날짜, 극장, 인원, 좌석, 금액등은 앞의 매표소에서 선택한 것들이 넘어올수있도록 출력하자.
	JButton pay_bt, back_bt;
	
	
	 
	public Ticket_before_pay(Sign_in signin) {
		this.sign_in = signin;
	
		 
		/*잔여포인트 & 결제확인창 이름 패널*/
		v_point = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//포인트 붙이기위한 패널 생성
		v_point.setBorder(new EmptyBorder(5,5,5,5));
		
		con_pay = new JPanel();
		//결제확인창 붙이기위해 패널 생성
		
		point = new JLabel("잔여포인트");
		show_point = new JLabel(); //포인트 DB에서 불러와서 잔여포인트 동적으로 출력되게 하기

		v_point.add(point);
		v_point.add(show_point);
		
		
		pay = new JLabel("결제 확인창");
		pay.setFont(new Font("굴림",Font.BOLD,25));
		pay.setHorizontalAlignment(SwingConstants.CENTER); 
		pay.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
		//제목인 결제확인창 에 대한 폰트 지정
		
		con_pay.add(pay);
		//제목을 붙이기.
		
		north = new JPanel();
		//북쪽에 자리할 패널을 위해 생성.
		north.setLayout(new BorderLayout());
		
		north.add(v_point,BorderLayout.NORTH);
		north.add(pay,BorderLayout.SOUTH);
		
		
		/*안에 내용물 패널 (여기는 다 DB연동해야함) */
		
		center = new JPanel();
		//중간의 큰패널생성.
		

		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel p5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel p6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel p7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//각 정보를 담을 패널들을 생성함. 그리드 레이아웃으로 넣기위해 각 패널별로 생성.
		
		JLabel jl1 = new JLabel("영화제목:   "); 
		show_name = new JLabel();//매표소에서 선택한 것들이 넘어오게 하기.		 
		p1.add(jl1);
		p1.add(show_name);
		
		JLabel jl2 = new JLabel("날 짜:   "); //매표소에서 선택한 것들이 넘어오게 하기.		
		show_date= new JLabel();
		p2.add(jl2);
		p2.add(show_date);
		
		JLabel ji7 = new JLabel("시 간:   ");
		show_time = new JLabel();
		p7.add(ji7);
		p7.add(show_time);

		JLabel jl3 = new JLabel("상영관:   ");//매표소에서 선택한 것들이 넘어오게 하기.		
		show_room= new JLabel();
		p3.add(jl3);
		p3.add(show_room);
		

		JLabel jl4 = new JLabel("인  원:   ");//매표소에서 선택한 것들이 넘어오게 하기.		
		show_peo= new JLabel();
		//JLabel show_peo2 = new JLabel("아동:  ");
		p4.add(jl4);
		p4.add(show_peo);
		//p4.add(show_peo2);
		
		

		JLabel jl5 = new JLabel("좌석번호:   ");//매표소에서 선택한 것들이 넘어오게 하기.		
		show_seat= new JLabel();
		p5.add(jl5);
		p5.add(show_seat);
		
		

		JLabel jl6 = new JLabel("결제금액:   ");//매표소에서 선택한 것들이 넘어오게 하기.		
		show_price= new JLabel();
		p6.add(jl6);
		p6.add(show_price);
		
		
		center.setLayout(new GridLayout(7,1));
		//중간자리할 큰 패널을 그리드레이아웃으로 설정.
		
		center.setBorder(new EmptyBorder(40,20,20,20));
		center.add(p1);
		center.add(p2);
		center.add(p7);
		center.add(p3);
		center.add(p4);
		center.add(p5);
		center.add(p6);
		//각 정보를 담는 제목들 붙이기.
		
		
	
	    
		/*버튼*/
		bt_p = new JPanel();
		pay_bt = new JButton("결제하기");
		back_bt = new JButton("돌아가기");
		bt_p.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
		bt_p.add(pay_bt);
		bt_p.add(Box.createHorizontalStrut(20));
		bt_p.add(back_bt);
		bt_p.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
		
		
		
		/*모든 판넬 붙이기*/
		add(north,BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(bt_p,BorderLayout.SOUTH);
		
		setSize(350, 600);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setLocationRelativeTo(null);
		//setResizable(false);
		
		pay_bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog payDialog = new JDialog(signin, "결제하기", true);
				payDialog.setSize(350, 250);
				payDialog.setLocationRelativeTo(signin);
				
				JLabel jl1 = new JLabel("  잔여 포인트 :");
				
				payDialog.add(jl1);
				
				payDialog.add(jl1);
			}
		});
		
		
		//돌아가기 버튼을 눌렀을 경우에 다시 좌석확인창으로 넘어가진다. 
		//결제확인창은 숨어진다.
//		back_bt.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				ticket_seat con_seat = new Ticket_seat();
//				con_seat.setVisible(true);
//				setVisible(false);
//				
//			}
//		});
//		
//		
//	}
	



	



//
//	public static void main(String[] args) {
//		new Ticket_before_pay();
	}  //메인
}   //클래스