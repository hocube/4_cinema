package ticketbox;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	JPanel bt_p, v_point, seat_p, con_seat, info_movie;
	// 버튼 패널, 포인트자리페널, 좌석선택패널, 좌석확인패널, 영화정보패널
	JLabel point, show_point, show_price3, show_peo, show_date, show_room, show_time, show_peo1, show_peo2,
			show_theater;
	// 포인트 표사라벨, 가격라벨, 인원표시라벨, 날짜표시 라벨, 선택극장 표시레벨 ** 여기는 DB와 연결해서 동적으로 변해야함. **
	JButton re_bt, pay_bt;
	// 다시 선택버튼 , 결제하기 버튼
	JComboBox<String> room;
	// 극장관을 바꾸기 위한 콤보박스.
	JButton seat_bt;
	// 좌석표에 스크린 표시를 하기위한 스크린 버튼 ** 버튼이 눌리지않도록 Enabled 해야함. **
	JPanel seat_p3;
	JPanel seat_p4;
	Ticket_seat_map test = new Ticket_seat_map(sign_in);
	// 그리고, 배열로 뽑아낸 5*5 좌석표를 객체 생성함. **이 객체를 생성하기위해서 seat_test는 항상 같이있어야함. **
	ArrayList<String> show_seatnum = new ArrayList<>();
	JLabel seatLabel;

	public Ticket_seat(Sign_in signin) {
		this.sign_in = signin;
	

		/* 잔여 "포인트자리" 패널 */
		v_point = new JPanel();
		v_point.setLayout(new FlowLayout(FlowLayout.RIGHT));
		v_point.setBorder(new EmptyBorder(10, 10, 10, 10));
		/* 각 패널별로 공간띄어주기함 */

		point = new JLabel("잔여포인트:       ");
		show_point = new JLabel(); // **포인트 표시 라벨로, 포인트 DB와 연결하여 잔여 포인트 보이게 해야함**

		v_point.add(point);
		v_point.add(show_point);
		// 포인트와 , 잔여포인트를 패널에 붙이기.

		/* "좌석선택" 자리 패널 */
		seat_p = new JPanel();
		seat_p.setLayout(new BorderLayout());
		seat_p.add(new JLabel(" [상영관 선택] "), BorderLayout.NORTH);

		String[] room_name = { "미나리", "개나리", "빛나리" };
		// 콤보박스에 집어넣을 극장이름 선언하기위해 배열로 집어넣음.

		room = new JComboBox<>(room_name);
		// room인 콤보박스에 극장이름 집어넣기

		JPanel seat_p2 = new JPanel();
		// 지역변수, 좌석선택탭과 상영관을 선택할 수 있는 콤보박스를 하나의 패널로 붙이기위한 패널.
		seat_p2.add(seat_p);
		seat_p2.add(room);

		JPanel seat_pg = new JPanel();
		// 좌석확인탭과, 스크린화면과 좌석체크박스를 붙이기위한 총 패널.

		seat_pg.setLayout(new BorderLayout());
		seat_pg.add(seat_p2, BorderLayout.NORTH);

		seat_bt = new JButton();

		// **좌석선택창에서 화면위치를 보여주기위해 버튼, 그리고 활동하지못하게 false
		seat_bt.setText("SCREEN");
		seat_bt.setVerticalTextPosition(SwingConstants.CENTER);
		seat_bt.setHorizontalTextPosition(SwingConstants.CENTER);
		seat_bt.setEnabled(false);
		seat_bt.setPreferredSize(new Dimension(100, 100));

		seat_p3 = new JPanel();
		// 스크린 화면 버튼을 보더로 만들기위해 선언.
		seat_p3.setLayout(new BorderLayout());
		seat_p3.add(seat_bt, BorderLayout.NORTH); // 화면자리를 보여주는 버튼
		seat_bt.add(Box.createVerticalStrut(50)); // 화면자리를 보여주는 버튼(스크린) 의 크기 지정.

		seat_p3.add(test, BorderLayout.CENTER); // 스크린 화면 버튼 밑으로 좌석 체크박스 붙여주기.
		seat_pg.add(seat_p3, BorderLayout.CENTER);
		// **스크린 화면자리와, 체크박스 자리를 한 보더로 만들어서 붙이기.

		/* 좌석확인페널 */
		con_seat = new JPanel();
		con_seat.setLayout(new BorderLayout());
		con_seat.setPreferredSize(new Dimension(150, 80));
		con_seat.setBorder(BorderFactory.createTitledBorder(" [좌석확인] "));
		seatLabel = new JLabel();

		// 선택된 좌석 목록을 show_seatnum 라벨에 붙이기
		//test.addSeatChangeListener(new SeatChangeListener());
		

		/* 매표소에 있는 선택되어진 "영화정보 표시"하는 패널 */
		info_movie = new JPanel();
		info_movie.setLayout(new BorderLayout());
		info_movie.setPreferredSize(new Dimension(200, 350));
		/* 이것이 이 선택한 영화패널만의 크기이므로, 이 안으로 보일수있게 하자. */
		info_movie.add(new JLabel(" [선택한 영화]"), BorderLayout.NORTH);
		/* **** JScrollPane를 넣어서 보이게할지 textarea 을 붙여야할지 고민해야햠! ***** */

		JTextArea s_movie = new JTextArea(200, 210);
		/* 선택된대로 영화 이미지 가 보일 공간 */
		// 우선 area로 붙여넣음.

		/* 포스터 보이는 영화정보들 출력을 위해 그리드 레이아웃 위해 만듦. */

		show_room = new JLabel();
		show_date = new JLabel();
		show_peo = new JLabel();
		show_price3 = new JLabel();
		show_time = new JLabel();
		show_theater = new JLabel();

		show_theater.setText("미나리"); // theater 가 상영관 room이 ict들어가야함
		// 극장 기본 default 인 미니라가 기본값으로 뜨게

		show_peo1 = new JLabel();
		show_peo2 = new JLabel();
		show_peo1.setText("성인 :   " + " 명"); // default 로 보여줄 문구
		show_peo2.setText("아동 :   " + " 명"); // default 로 보여줄 문구

		JLabel j1 = new JLabel("극       장:    ");
		JLabel j6 = new JLabel("상 영 관:    ");
		JLabel j2 = new JLabel("날       짜:    ");
		JLabel j3 = new JLabel("인       원:    ");
		JLabel j5 = new JLabel("시       간:    ");
		JLabel j4 = new JLabel("총 금 액:    ");

		JPanel pg1 = new JPanel();
		// 선택한 영화정보들을 하나의패널로 관리하기 위해 선언.

		pg1.setLayout(new FlowLayout(FlowLayout.LEFT));
		pg1.add(j1);
		pg1.add(show_room);

		JPanel pg2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pg2.add(j2);
		pg2.add(show_date); // Jcalendar에서 선택한 날짜 출력되게하기.
		JPanel pg6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pg6.add(j5);
		pg6.add(show_time);

		JPanel pg3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pg3.add(j3);
		pg3.add(show_peo1); // 콤보박스에서 선택한 인원수가 뜨도록 출력되게하기
		pg3.add(show_peo2); // 콤보박스에서 선택한 인원수가 뜨도록 출력되게하기

		JPanel pg4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pg4.add(j4);
		pg4.add(show_price3); // 인원수 * 가격으로 처리하여, 인원 눌렀을경우 가격이 뜨게하기

		JPanel pg7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pg7.add(j6);
		pg7.add(show_theater);
		JPanel i_pack = new JPanel(new GridLayout(6, 1));

		// 위의 정보들을 그리드로 4/1 로 한줄로 만들기위한 패널선언.
		i_pack.add(pg1);
		i_pack.add(pg7);
		i_pack.add(pg2);
		i_pack.add(pg6);
		i_pack.add(pg3);
		i_pack.add(pg4);

		info_movie.add(s_movie, BorderLayout.CENTER);
		info_movie.add(i_pack, BorderLayout.SOUTH);
		// 선택한 영화표시패널에, 영화포스터공간과 정보들을 패널에 붙이기.

		JPanel pg5 = new JPanel();
		// 좌석확인패널과 선택한 영화 표시창을 하나의 패널로 관라히기위한 패널선언
		pg5.setLayout(new BorderLayout());

		pg5.add(con_seat, BorderLayout.CENTER);
		pg5.add(info_movie, BorderLayout.EAST);

		/* 마지막 버튼 패널 */
		bt_p = new JPanel();
		pay_bt = new JButton("예매하기");
		re_bt = new JButton("다시선택");
		bt_p.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
		bt_p.add(re_bt);
		bt_p.add(Box.createHorizontalStrut(20));
		bt_p.add(pay_bt);
		bt_p.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

		// 위의 모든 최종 패널을 보더로 각각의 자리의 배치하기위해 큰 팩 선언.
		JPanel pack = new JPanel(new BorderLayout());
		pack.setBorder(new EmptyBorder(10, 10, 10, 10));

		pack.add(v_point, BorderLayout.NORTH);
		pack.add(bt_p, BorderLayout.SOUTH);
		pack.add(seat_pg, BorderLayout.CENTER);
		pack.add(pg5, BorderLayout.EAST);

		add(pack);

		setSize(800, 800);
		setVisible(true);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setLocationRelativeTo(null);
		//setResizable(false);

		// 결제하기 버튼을 눌렀을 경우, 결제확인창으로 넘어가도록 액션리스너 만듦.
		// 결제하기 버튼을누름과 동시에 매표소 화면은 숨겨진다.
		// 그리고, 결제확인 창이 객체 생성되면서 화면에 보여진다.
//		pay_bt.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Ticket_before_pay con_pay = new Ticket_before_pay(signin);
//				setVisible(false);
//			}
//		});
//
//		// 각 극장관에서 좌석선택후 극장을 바꾸고자할때, 선택된 체크박스의 체크가 초기화되게.
//		// resetSeatSelection()은 seat_test 안에 메서드로 구현하였음.
//		room.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// 콤보박스가 변경되었을 때 체크박스 초기화
//				test.resetSeatSelection(); // seat_test 클래스에 resetSeatSelection() 메서드
//
//				updateSeatButtonPosition();
//			}
//		});
//
//		// 콤보박스 상영관을 누르면, 상영관 라벨에 동적으로 입력
//		room.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JComboBox<String> combo = (JComboBox<String>) e.getSource();
//				String selectedOption = (String) combo.getSelectedItem();
//				show_theater.setText(selectedOption);
//			}
//		});
//
//		
//		
//				
//	} // 마지막괄호
//
//	// room 콤보박스인 개나리, 빛나리, 미나리를 선택했을 경우 좌석도와 스크린위치가 변하게 하는
//	// method 추가함.
//	private void updateSeatButtonPosition() {
//		String selectedRoom = (String) room.getSelectedItem();
//		if (selectedRoom.equals("개나리")) {
//			seat_p3.remove(seat_bt); // 버튼을 패널에서 제거
//			seat_p3.remove(test);
//			seat_p3.add(seat_bt, BorderLayout.EAST); // 버튼을 오른쪽 위치에 추가합
//			seat_bt.setSize(200, 200);
//			seat_p3.add(test, BorderLayout.CENTER);
//			test.setSize(200, 200);
//			if (selectedRoom.equals("개나리")) {
//				seat_bt.setText("SCREEN");
//				seat_bt.setVerticalTextPosition(SwingConstants.CENTER);
//				seat_bt.setHorizontalTextPosition(SwingConstants.CENTER);
//				seat_bt.setEnabled(false);
//				seat_bt.setPreferredSize(new Dimension(100, 100));
//			} else {
//				seat_bt.setText("SCREEN");
//				seat_bt.setEnabled(false);
//			}
//		} else if (selectedRoom.equals("빛나리")) {
//			seat_p3.remove(seat_bt);
//			seat_p3.add(test, BorderLayout.CENTER);
//			seat_p3.add(seat_bt, BorderLayout.SOUTH); // 버튼을 남쪽 위치에 추가
//			if (selectedRoom.equals("빛나리")) {
//				seat_bt.setText("SCREEN");
//				seat_bt.setVerticalTextPosition(SwingConstants.CENTER);
//				seat_bt.setHorizontalTextPosition(SwingConstants.CENTER);
//				seat_bt.setEnabled(false);
//				seat_bt.setPreferredSize(new Dimension(100, 100));
//			} else {
//				seat_bt.setText("SCREEN");
//				seat_bt.setEnabled(false);
//			}
//		} else if (selectedRoom.equals("미나리")) {
//			seat_p3.remove(seat_bt);
//			seat_p3.add(test, BorderLayout.CENTER);
//			seat_p3.add(seat_bt, BorderLayout.NORTH); // 버튼을 북쪽 위치에 추가
//			if (selectedRoom.equals("미나리")) {
//				seat_bt.setText("SCREEN");
//				seat_bt.setVerticalTextPosition(SwingConstants.CENTER);
//				seat_bt.setHorizontalTextPosition(SwingConstants.CENTER);
//				seat_bt.setEnabled(false);
//				seat_bt.setPreferredSize(new Dimension(100, 100));
//			} else {
//				seat_bt.setText("SCREEN");
//				seat_bt.setEnabled(false);
//			}
//		}
//		seat_p3.revalidate(); // 패널을 다시 유효화하여 변경 사항을 적용.
//		seat_p3.repaint(); // 패널을 다시 그려서 레이아웃을 업데이트
//	}
//
//	
//	//체크박스 선택시 메서드
//	private class SeatCheckBoxListener implements ActionListener {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			JCheckBox sourceCheckBox = (JCheckBox) e.getSource();
//			String seatName = sourceCheckBox.getText();
//			boolean isChecked = sourceCheckBox.isSelected();
//			// seatName 이 선택된 좌석 이름이니, 호출하거나 set 사용할 때 이 이름으로 사용하자.
//			/*
//			 * // 선택된 좌석 목록을 show_seatnum 라벨에 붙이기 ArrayList<String> selectedSeats =
//			 * test.getShowSeatnum(); String labelText = "선택된 좌석: "; for (String seat :
//			 * selectedSeats) { labelText += seat + ", "; } // 마지막에 쉼표와 공백 제거 labelText =
//			 * labelText.substring(0, labelText.length() - 2);
//			 * 
//			 * // show_seatnum 라벨에 선택된 좌석 목록 설정 // show_seatnum.setText(labelText);
//			 */		}
//	}
//
//	private class SeatChangeListener implements ChangeListener {
//		@Override
//		public void stateChanged(ChangeEvent e) {
//			JCheckBox sourceCheckBox = (JCheckBox) e.getSource();
//			String seatName = sourceCheckBox.getText();
//			boolean isChecked = sourceCheckBox.isSelected();
//
//			if (isChecked) {
//				show_seatnum.add(seatName);
//			} else {
//				show_seatnum.remove(seatName);
//			}
//
//			updateSeatLabel();
//		}
//	}
//
//	private void updateSeatLabel() {
//		StringBuilder sb = new StringBuilder();
//		for (String seat : show_seatnum) {
//			sb.append(seat).append(", ");
//		}
//		String labelText = sb.toString();
//		if (labelText.endsWith(", ")) {
//			labelText = labelText.substring(0, labelText.length() - 2);
//		}
//		seatLabel.setText(labelText);
//	}
	}
}