package ticketbox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import c_loginout.Sign_in;

//이걸로 합치기!
public class Ticket_seat extends JPanel {
	Sign_in sign_in;

	Ticket_seat_map map;

	JLabel lblNewLabel, lblNewLabel_1, lblNewLabel_2_1_1_1, lblNewLabel_6, lblNewLabel_7, lblNewLabel_7_1,
			lblNewLabel_7_1_1, lblNewLabel_7_1_1_1, lblNewLabel_8, lblNewLabel_8_1, lblNewLabel_8_2, lblNewLabel_8_2_1,
			lblNewLabel_9, lblNewLabel_10, lblNewLabel_11, lblNewLabel_10_1, lblNewLabel_10_2, lblNewLabel_10_3,
			lblNewLabel_2, lblNewLabel_3, lblNewLabel_5; //+lblNewLabel_4
	JTextArea lblNewLabel_4;
	JButton btnNewButton, btnNewButton_1, btnNewButton_1_1;

	private int totalSelectedCount = 0; // 좌석 체크를 위한 변수선언
	private JCheckBox[] checkboxArray;

	// Icon ;
	String theater, adultCount, childCount, date, time, amount, room, seat;
	// 좌석선택창 정보 > 결제확인창으로 값 보내기 위한 변수선언

	JComboBox comboBox;

	public Ticket_seat(Sign_in signin) {
		this.sign_in = signin;

		map = new Ticket_seat_map(signin);

		this.setLayout(null);

		lblNewLabel_2_1_1_1 = new JLabel("[선택한 정보]");
		lblNewLabel_2_1_1_1.setBounds(610, 56, 82, 15);
		this.add(lblNewLabel_2_1_1_1);

		lblNewLabel_6 = new JLabel(); // 앞에서 선택한 정보 여기로 끌어오기/영화포스터
		lblNewLabel_6.setBackground(new Color(255, 255, 255));
		lblNewLabel_6.setBounds(610, 81, 161, 460);
		this.add(lblNewLabel_6);

		lblNewLabel_7 = new JLabel("극장 :");
		lblNewLabel_7.setBounds(610, 573, 57, 15);
		this.add(lblNewLabel_7);

		lblNewLabel_7_1 = new JLabel("날짜 :");
		lblNewLabel_7_1.setBounds(610, 623, 57, 15);
		this.add(lblNewLabel_7_1);

		lblNewLabel_7_1_1 = new JLabel("시간 :");
		lblNewLabel_7_1_1.setBounds(610, 648, 57, 15);
		this.add(lblNewLabel_7_1_1);

		lblNewLabel_7_1_1_1 = new JLabel("인원");
		lblNewLabel_7_1_1_1.setBounds(610, 673, 34, 15);
		this.add(lblNewLabel_7_1_1_1);

		lblNewLabel_8 = new JLabel("성인:");
		lblNewLabel_8.setBounds(642, 673, 34, 15);
		this.add(lblNewLabel_8);

		lblNewLabel_8_1 = new JLabel("아동:");
		lblNewLabel_8_1.setBounds(706, 673, 34, 15);
		this.add(lblNewLabel_8_1);

		lblNewLabel_8_2 = new JLabel(); // 앞에서 선택한 정보 여기로 끌어오기/성인인원
		lblNewLabel_8_2.setBounds(681, 673, 32, 15);
		this.add(lblNewLabel_8_2);

		lblNewLabel_8_2_1 = new JLabel(); // 앞에서 선택한 정보 여기로 끌어오기/아동인원
		lblNewLabel_8_2_1.setBounds(749, 673, 32, 15);
		this.add(lblNewLabel_8_2_1);

		lblNewLabel_9 = new JLabel("금액:");
		lblNewLabel_9.setBounds(610, 698, 57, 15);
		this.add(lblNewLabel_9);

		lblNewLabel_10 = new JLabel(); // ict 이름이라는 극장이름뜨게하기
		lblNewLabel_10.setBounds(656, 573, 100, 15);
		this.add(lblNewLabel_10);
		// db정보로 넣기

		lblNewLabel_11 = new JLabel("미나리"); // 관선택시 라벨 동적으로 default 는 미나리
		lblNewLabel_11.setBounds(656, 598, 57, 15);
		this.add(lblNewLabel_11);

		lblNewLabel_10_1 = new JLabel(); // 앞에서 선택한 정보 여기로 끌어오기/날짜
		lblNewLabel_10_1.setBounds(656, 623, 57, 15);
		this.add(lblNewLabel_10_1);
		// db정보로 넣기

		lblNewLabel_10_2 = new JLabel(); // 앞에서 선택한 정보 여기로 끌어오기/시간
		lblNewLabel_10_2.setBounds(656, 648, 100, 15);
		this.add(lblNewLabel_10_2);
		// db정보로 넣기

		lblNewLabel_10_3 = new JLabel(); // 앞에서 선택한 정보 여기로 끌어오기/금액
		lblNewLabel_10_3.setBounds(656, 698, 57, 15);
		this.add(lblNewLabel_10_3);

		lblNewLabel_2 = new JLabel("[상영관 목록]");
		lblNewLabel_2.setBounds(57, 56, 82, 15);
		this.add(lblNewLabel_2);

		String[] thearter_name = { "미나리", "개나리", "빛나리" };
		comboBox = new JComboBox(thearter_name);
		comboBox.setBounds(151, 52, 69, 23);
		this.add(comboBox);

		btnNewButton = new JButton("SCREEN");
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(32, 138, 297, 83);
		this.add(btnNewButton);

		lblNewLabel_3 = new JLabel("[좌석 확인]");
		lblNewLabel_3.setBounds(411, 56, 82, 15);
		this.add(lblNewLabel_3);

		/*lblNewLabel_4 = new JLabel();
		lblNewLabel_4.setBounds(411, 105, 134, 436);
		this.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 12));*/
		// lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		
		//lblNewLabel_4 = new JLabel();
		lblNewLabel_4 = new JTextArea();
		lblNewLabel_4.setBounds(411, 105, 134, 436);
		this.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 12));
		
		System.out.println(lblNewLabel_4);

		btnNewButton_1 = new JButton("뒤로 가기");
		btnNewButton_1.setBounds(235, 714, 115, 35);
		this.add(btnNewButton_1);

		btnNewButton_1_1 = new JButton("예매 하기");
		btnNewButton_1_1.setBounds(450, 714, 115, 35);
		this.add(btnNewButton_1_1);

		lblNewLabel_5 = new JLabel("상영관:");
		lblNewLabel_5.setBounds(610, 598, 57, 15);
		this.add(lblNewLabel_5);

		map.setBounds(32, 250, 300, 300);
		this.add(map);

		// 뒤로가기 버튼을 눌렀을 경우
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sign_in.card.show(sign_in.pg, "to_main");
				lblNewLabel_4.setText("");
				map.resetSelectedSeats();
				map.resetCheckBoxes();

			}
		});

		// 결제하기 버튼을 눌렀을 경우, 결제확인창으로 넘어가도록 액션리스너 만듦.
		btnNewButton_1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				
				 String adultCountText = lblNewLabel_8_2.getText().replace("명", ""); 
				 String childCountText = lblNewLabel_8_2_1.getText().replace("명", ""); 
				 int aCount = Integer.parseInt(adultCountText); 
				 int cCount =Integer.parseInt(childCountText); 
				 //인원라벨에서 "명" 을 뻬고 나온 숫자를 인티저로 정수로 변환
				 				 
				 int totalSelectedCount = aCount + cCount;
				 //성인과 아동 인원의 합
				 
				 int count2 = map.su2;
				 //체크박스의 개수. 
				 
				 
				 System.out.println("제대로된 인원이 나오나? "+ aCount + cCount + totalSelectedCount +count2 );
				 if (count2 > totalSelectedCount) {
					 JOptionPane.showMessageDialog(null," 인원만큼 좌석을 선택하세요.", "경고",JOptionPane.WARNING_MESSAGE); 
					 
					 
				 } else if (count2 <totalSelectedCount) { 
					 JOptionPane.showMessageDialog(null, " 인원만큼 좌석을 선택하세요.","경고", JOptionPane.WARNING_MESSAGE); 
					 
				 }  else if( count2 == totalSelectedCount) { 
					 int res = JOptionPane.showOptionDialog(getParent(), "선택하신 좌석으로 예약할까요?", "좌석확인",
								JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
						if (res == 0) {
							// 실제저장하는 코드
							adultCount = lblNewLabel_8_2.getText(); // 성인인원 겟
							childCount = lblNewLabel_8_2_1.getText(); // 아동인원 겟
							date = lblNewLabel_10_1.getText(); // 날짜 겟
							time = lblNewLabel_10_2.getText(); // 시간 겟
							theater = lblNewLabel_11.getText(); // 상영관 겟
							amount = lblNewLabel_10_3.getText(); // 금액 겟
							seat = lblNewLabel_4.getText(); // 좌석 겟
							
							sign_in.card.show(sign_in.pg, "tb_pay"); // 결제확인창 뜨기
							
							sign_in.tb_pay.lblNewLabel_6.setText(adultCount); // 겟한거 집어넣기
							sign_in.tb_pay.lblNewLabel_7.setText(childCount); // 겟한거 집어넣기
							sign_in.tb_pay.lblNewLabel_11.setText(date); // 겟한거 집어넣기
							sign_in.tb_pay.lblNewLabel_13.setText(seat);// 겟한거 집어넣기
							sign_in.tb_pay.lblNewLabel_14.setText(amount);// 겟한거 집어넣기
							sign_in.tb_pay.lblNewLabel_12.setText(theater);// 겟한거 집어넣기
							

						} else if (res == 1) {
							return;
						}
							
					 
			}
				 
			}
		});

		// 콤보박스 상영관을 누르면, 상영관 라벨에 동적으로 입력 되고, 콤보박스 클릭시 스크린위치가 바뀐다.
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> combo = (JComboBox<String>) e.getSource();
				String selectedOption = (String) combo.getSelectedItem();
				lblNewLabel_11.setText(selectedOption);
				lblNewLabel_4.setText("");
				map.resetSelectedSeats();
				map.resetCheckBoxes();
				
				
				if (selectedOption.equals("개나리")) {

					map.setBounds(32, 250, 300, 300);
					btnNewButton.setBounds(32, 560, 297, 83); // 개나리 선택 시 버튼 위치 설정

				} else if (selectedOption.equals("빛나리")) {

					btnNewButton.setVerticalTextPosition(SwingConstants.CENTER);
					map.setBounds(130, 200, 230, 230);
					btnNewButton.setBounds(32, 180, 83, 297); // 빛나리 선택 시 버튼 위치 설정

				} else if (selectedOption.equals("미나리")) {
					btnNewButton.setEnabled(false);
					btnNewButton.setBounds(32, 138, 297, 83);
					map.setBounds(32, 250, 300, 300);
				}

				// 화면 갱신
				revalidate();
				repaint();
			}
		});

	} // 액션리스너의 마지막괄호

	// 메서드칸 =======================

} // 클래스의 마지막 괄호
