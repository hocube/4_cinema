package ticketbox;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import c_loginout.Sign_in;
import movie_server.Protocol;
import movie_server.Server_book;
import movie_server.DAO;
import movie_server.TicketBox_VO;



//이걸로 합치기!
//매표소 만들기 
public class Ticket_office_main extends JPanel {
	// 필요한 것들 선언하자
	Sign_in sign_in;
	JPanel v_point, c_movie, c_room, c_date, c_time, c_people, info_movie;
	// 포인트표시 패널과 영화선택표시 , 상영관선택, 날짜 선택칸, 시간 선택칸, 인원선택칸, 선택한 영화표시패널을 선언함.
	JButton ticket_bt;
	// 예매하기버튼.
	JComboBox<String> adult, child;
	// 인원선택에 필요한 숫자 콤보벅스 선언,
	JLabel point, show_point, show_room, show_peo1, show_peo2, show_price1, show_price2, show_price3, show_date,
			show_time;
	// 각 타이틀과 화면에 선택한 내용들이 선택한 영화 정보 밑에 출력 될수 있기위한 라벨을 선언함.
	JTextArea show_movie;
	// 영화 포스터를 위한 자리 **나중에 area 말고, 다른 것으로 하게되면 변경해야함**
	JScrollPane jsp1, jsp2, jsp3;
	// **날짜 선택칸에 Jcalendar를 이용하여 만들었기에 객체를 생성하여 선언함.
	// 내용은 Jcalendar 클래스로 넘어가서 확인하자. - 윈도우빌더로 만든 클래스.
	JCalendar cal = new JCalendar();
	// 위는 날짜캘린더를 위한 객체생성
	int res1, res2;
	String price_ad, price_ch;
	// 위는 성인아동 가격을 계산하기위한 변수선언

	JTable table1, table2, table3;
	DefaultTableModel model1, model2, model3;
	// 위는 테이블의 모델화, 데이터를 집어넣기 위함.

	// 서버연결를 위한 변수
	Socket s;
	ObjectOutputStream out;
	ObjectInputStream in;
	Server_book server;
	String ip;

	public Ticket_office_main(Sign_in signin) {
		this.sign_in = signin;
		

		/*
		 * 제일 위의 잔여포인트 패널 잔여포인트는 라벨명이고, 포인트칸은 라벨로, 나중에 DB연동시 이 JLabel point에 붙여서 보일수 있도록
		 * 하자..
		 */
		v_point = new JPanel();
		v_point.setLayout(new FlowLayout(FlowLayout.RIGHT));
		v_point.setBorder(new EmptyBorder(10, 10, 10, 10));
		/* 각 패널별로 공간띄어주기함 */
		point = new JLabel("잔여포인트:       ");
		show_point = new JLabel(); // 포인트 DB에서 가져올 잔여포인트자리 .

		v_point.add(point);
		v_point.add(show_point);

		/*
		 * 선택한 영화정보가 보이는 패널 JList안에 있는 리스트들은 DB연동이 되어 보이는 것들로, 마우스로 선택한것들이 이 곳에는 동적으로 변할
		 * 수 있도록 나중에 DB연결하면 보일 수 있도록 하자.
		 */
		info_movie = new JPanel();
		// 선택한 영화정보 패널 선언.
		info_movie.setLayout(new BorderLayout());
		info_movie.setPreferredSize(new Dimension(200, 350));
		info_movie.add(new JLabel(" [선택한 영화]"), BorderLayout.NORTH);

		//show_movie = new JTextArea(200, 210);
		JLabel show_poster = new JLabel();  
		/* 선택된대로 영화 이미지 가 보일 공간 */
		// 영화 포스터를 위한 자리 **나중에 area 말고, 다른 것으로 하게되면 변경해야함  > 라벨로 생성해서 아이콘으로 붙임**

		show_room = new JLabel();
		show_date = new JLabel();
		show_time = new JLabel();
		show_peo1 = new JLabel();
		show_peo2 = new JLabel();
		show_peo1.setText("성인 :   " + " 명"); // default 로 보여줄 문구
		show_peo2.setText("아동 :   " + " 명"); // default 로 보여줄 문구
		show_price1 = new JLabel();
		show_price2 = new JLabel();
		show_price3 = new JLabel("0");

		// 각 라벨을 선언함. 이 위의 것들은 매표소에서 마우스로 클릭하여 선택한것들이
		// 보여지는 라벨이다. 동적으로 변하게 액션리스너 사용하자.

		JLabel j1 = new JLabel("극       장:    ");
		JLabel j2 = new JLabel("날       짜:    ");
		JLabel j3 = new JLabel("인       원:    ");
		JLabel j4 = new JLabel("시       간: ");
		JLabel j5 = new JLabel("총 금 액:    ");
		// 탭 선언.

		// 각탭들을 그리드레이아웃하기 위해 각각 패널로 선언하여 집어넣음.
		JPanel pg4 = new JPanel();
		pg4.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel pg5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pg6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pg7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pg9 = new JPanel(new FlowLayout(FlowLayout.LEFT));

		pg4.add(j1);
		pg4.add(show_room);

		pg5.add(j2);
		pg5.add(show_date);
		pg7.add(j4);
		pg7.add(show_time);

		pg6.add(j3);
		pg6.add(show_peo1);
		pg6.add(show_peo2);

		pg9.add(j5);
		pg9.add(show_price3);

		// 각 탭을 그리드레이아웃할 패널 생성 하고붙이기.
		JPanel pg8 = new JPanel(new GridLayout(5, 1));
		pg8.add(pg4);
		pg8.add(pg5);
		pg8.add(pg7);
		pg8.add(pg6);
		pg8.add(pg9);

		// 선택한 영화패널인 info_movie에 포스터공간과 각 정보탭을 붙이기.
		info_movie.add(show_poster, BorderLayout.CENTER);
		info_movie.add(pg8, BorderLayout.SOUTH);

		/*
		 * 이 공간은 영화 목록이 나오고, 선택을 할 수 있는 패널 리스트 이용하여 DB랑 연동하면 예약가능한 리스트만 보이게 하자.
		 */

		c_movie = new JPanel();
		c_movie.setLayout(new BorderLayout());

		c_movie.setPreferredSize(new Dimension(40, 60)); /* 각 패널의 크기 제한 */
		c_movie.add(new JLabel(" [영화 선택] "), BorderLayout.NORTH);

		/* *****나중에 여기에 db연동으로 집어넣기***** */
		String[] columnNames = { "영화제목" };

		model1 = new DefaultTableModel(new Object[] { "영화 목록" }, 0);
		table1 = new JTable(model1);
		jsp1 = new JScrollPane(table1);
		c_movie.add(jsp1);

		/* ***jsp1이 테이블내용에 넣어야하는 변수이다. */

		/* 극장 선택할 수 있는 패널 */
		c_room = new JPanel();
		c_room.setLayout(new BorderLayout());

		c_room.setPreferredSize(new Dimension(40, 60)); /* 각 패널의 크기 제한 */
		c_room.add(new JLabel(" [극장 선택] "), BorderLayout.NORTH);

		model2 = new DefaultTableModel(new Object[] { "극장 목록" }, 0);
		model2.addRow(new Object[] { "한국 ICT관" });

		table2 = new JTable(model2);
		jsp2 = new JScrollPane(table2);

		c_room.add(jsp2);

		/* 상영시간표 패널 */
		c_time = new JPanel();
		c_time.setLayout(new BorderLayout());
		c_time.setPreferredSize(new Dimension(40, 60)); /* 각 패널의 크기 제한 */
		c_time.add(new JLabel(" [상영 시간표] "), BorderLayout.NORTH);

		model3 = new DefaultTableModel(new Object[] { "상영시간" }, 0);
		table3 = new JTable(model3);
		JScrollPane scrollPane3 = new JScrollPane(table3);
		jsp3 = new JScrollPane(table3);
		/* *****나중에 여기에 db연동으로 집어넣기***** */
		c_time.add(jsp3);
		/* ***jsp3이 테이블내용에 넣어야하는 변수이다. */

		/* 날짜 선택 하는 패널 */

		c_date = new JPanel();
		c_date.setLayout(new BorderLayout());
		c_date.setPreferredSize(new Dimension(250, 300));
		c_date.add(new JLabel(" [날짜 선택] "), BorderLayout.NORTH);

		/* *******날짜 API 갖고오기******* */

		c_date.add(cal, BorderLayout.CENTER);
		// 파일보낼때 캘린더jar파일 같이보내기
		// 날짜를 선택한것이 바로 선택한영화정보 밑의 날짜에 출력될수있도록
		// ****캘린더+마우스리스너(또는 해당 리스너 또는 액션리스너 이용하기. 날짜캘린더 변수는 "cal"이다)******

		/* 날짜 선택시 일주일 이상 선택하지 못하게 하기. */
		/* 컴퓨터날짜 그대로 동적으로 변한다. */
		Calendar today = Calendar.getInstance();
		Calendar maxDate = (Calendar) today.clone();
		maxDate.add(Calendar.WEEK_OF_YEAR, 1);

		cal.setMaxSelectableDate(maxDate.getTime());

		/* 인원선택하는 패널 */
		c_people = new JPanel();
		c_people.setLayout(new BorderLayout());
		c_people.add(new JLabel(" [인원 선택] "), BorderLayout.NORTH);

		JLabel ad = new JLabel(" 성 인 ");
		JLabel ch = new JLabel(" 아 동 ");
		String[] a_peo = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		String[] c_peo = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

		JPanel pg1 = new JPanel();
		// 성인, 아동 라벨과 각 콤보를 하나로 만들기위한 패널 생성.
		pg1.setLayout(new FlowLayout());
		// 한줄로 출력될수있도록 만들기.

		adult = new JComboBox<>(a_peo);
		child = new JComboBox<>(c_peo);

		pg1.add(ad);
		pg1.add(adult);
		pg1.add(ch);
		pg1.add(child);
		c_people.add(pg1);

		/* 선택하는 공간 전체 패널 하나로 묶기 */
		JPanel pack = new JPanel();
		pack.setLayout(new GridLayout(2, 3));

		pack.add(c_movie);
		pack.add(c_room);
		pack.add(c_date);
		pack.add(c_time);
		pack.add(c_people);

		/* 예매하기 버튼 */
		ticket_bt = new JButton("예매하기");

		JPanel pg2 = new JPanel();
		// 버튼을 보더레이아웃으로 만들어 오른쪽에 붙이기 위한 패널 생성.

		pg2.setLayout(new BorderLayout());
		pg2.add(ticket_bt, BorderLayout.EAST);

		/* 여백남기기 위해서 모든 패널 하나로 붙이기. */
		JPanel packall = new JPanel();
		packall.setLayout(new BorderLayout());
		packall.setBorder(new EmptyBorder(0, 10, 10, 10));
		// 여백주기

		// 모든 패널들 프레임에 붙이기
		packall.add(v_point, BorderLayout.NORTH); // 제일위의 포인트패널
		packall.add(info_movie, BorderLayout.EAST); // 오른쪽의 영화정보
		packall.add(pack, BorderLayout.CENTER); // 가운데의 그리드레이아웃들 (선택할 것들, 영화선택, 상영관 날짜, 상영시간표, 인원등)
		packall.add(pg2, BorderLayout.SOUTH); // 하단의버튼

		add(packall);

		// 매표소의 창 크기 지정.
		setSize(800, 800);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setLocationRelativeTo(null);
		//setResizable(false);

//		connected();
//
////		addWindowListener(new WindowAdapter() {
////			@Override
////			public void windowClosing(WindowEvent e) {
////				if (s != null) {
////					try {
////						Protocol p = new Protocol();
////						p.setCmd(300);
////						out.writeObject(p);
////						out.flush();
////					} catch (Exception e2) {
////
////					}
////				} else {
////					closed();
////				}
////			}
////		});
////
////		addWindowListener(new WindowAdapter() {
////			public void windowOpened(WindowEvent e) {
////				try {
////					//영화목록은 성공, 건들지말자.
////					Protocol p = new Protocol();
////					p.setCmd(301);
////					out.writeObject(p);
////					out.flush();					
////
////				} catch (Exception e2) {
////					e2.printStackTrace();
////
////				}
////			}
////		});
////
////		addWindowListener(new WindowAdapter() {
////			public void windowOpened(WindowEvent e) {
////				try {
////
////					Protocol p = new Protocol();
////					p.setCmd(302);
////					out.writeObject(p);
////					out.flush();
////					System.out.println("window 창 오픈이벤트 성공 302일");
////
////				} catch (Exception e2) {
////					e2.printStackTrace();
////
////				}
////			}
////		});
////		

//
//		// 예매하기 버튼을 눌렀을경우에 좌석 선택창으로 넘어가진다.
//		// 매표소는 숨겨지고, 좌석확인창을 객체 생성하여 보이게 한다.
//		ticket_bt.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Ticket_seat con_seat = new Ticket_seat(signin);
//				setVisible(false);
//
//			}
//		});
//
//		// 날짜를 선택했을때, 선택한 영화정보 탭의 날짜에 표시하게하는 이벤트
//		cal.addPropertyChangeListener("calendar", new PropertyChangeListener() {
//			@Override
//			public void propertyChange(PropertyChangeEvent evt) {
//				if (evt.getPropertyName().equals("calendar")) {
//					Calendar selectedDate = (Calendar) evt.getNewValue();
//
//					// 현재 날짜 가져오기
//					Calendar currentDate = Calendar.getInstance();
//					currentDate.set(Calendar.HOUR_OF_DAY, 0);
//					currentDate.set(Calendar.MINUTE, 0);
//					currentDate.set(Calendar.SECOND, 0);
//					currentDate.set(Calendar.MILLISECOND, 0);
//
//					// 선택한 날짜와 현재 날짜 비교
//					if (selectedDate.before(currentDate)) {
//						// 선택한 날짜가 오늘 이전인 경우
//						// 이전 날짜는 선택할 수 없도록 처리
//
//						// 이전 날짜로 변경된 경우 현재 날짜로 다시 설정
//						cal.setCalendar(currentDate);
//
//						// "과거의 날짜는 선택할 수 없습니다"라는 알림 창 표시
//						SwingUtilities.invokeLater(() -> {
//							JOptionPane.showMessageDialog(null, "과거의 날짜는 선택할 수 없습니다.", "경고",
//									JOptionPane.WARNING_MESSAGE);
//						});
//					} else {
//						// 선택한 날짜가 오늘 이후인 경우
//						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//						String formattedDate = dateFormat.format(selectedDate.getTime());
//						show_date.setText(formattedDate);
//					}
//				}
//			}
//		});
//
//		// 성인과 아동 인원을 선택하였을 경우, 선택한 영화정보에 동적으로 바뀔수있도록 출력하기.
//		adult.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JComboBox<String> combo = (JComboBox<String>) e.getSource();
//				String selectedOption = (String) combo.getSelectedItem();
//				show_peo1.setText("성인 : " + selectedOption + " 명");
//				if (selectedOption.equals("0")) {
//					show_peo1.setText("성인 :   " + " 명");
//				}
//
//				if (selectedOption != null) {
//					int ad = Integer.parseInt(selectedOption);
//					res1 = ad * 10000;
//					price_ad = String.valueOf(res1);
//					show_price1.setText(price_ad);
//					updateTotalPrice();
//				}
//
//			}
//		});
//		// 성인과 아동 인원을 선택하였을 경우, 선택한 영화정보에 동적으로 바뀔수있도록 출력하기.
//		child.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JComboBox<String> combo = (JComboBox<String>) e.getSource();
//				String selectedOption = (String) combo.getSelectedItem();
//				show_peo2.setText("아동 : " + selectedOption + " 명");
//				if (selectedOption.equals("0")) {
//					show_peo2.setText("아동 :   " + " 명");
//				}
//				if (selectedOption != null) {
//					int ad = Integer.parseInt(selectedOption);
//					res2 = ad * 5000;
//					price_ch = String.valueOf(res2);
//					show_price2.setText(price_ch);
//					updateTotalPrice();
//				}
//
//			}
//		});
//
//		// 극장관은 1개만 있으므로, ICT관 을 테이블에 추가하고, 그것을 클릭하였을경우
//		// 발생하는 이벤트 리스너 작성.
//		table2.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				int select_theater = table2.getSelectedRow();
//				Object[] rowData = new Object[table2.getColumnCount()];
//				for (int i = 0; i < table2.getColumnCount(); i++) {
//					rowData[i] = table2.getValueAt(select_theater, i);
//				}
//				show_room.setText(Arrays.toString(rowData));
//				System.out.println("ICT클릭 이벤트 성공");
//				// 입력이 잘되었는지 확인하기위한 콘솔출력
//			}
//
//		});
//		
//		table3.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				int select_theater = table3.getSelectedRow();
//				Object[] rowData = new Object[table3.getColumnCount()];
//				for (int i = 0; i < table3.getColumnCount(); i++) {
//					rowData[i] = table3.getValueAt(select_theater, i);
//				}
//				show_time.setText(Arrays.toString(rowData));
//				System.out.println("시간클릭시 삽입성공");
//				// 입력이 잘되었는지 확인하기위한 콘솔출력
//			}
//
//		});
//		
//		
//		//테이블1인 영화목록을 클릭했을때, 이미지 들어갈수있도록 삽입하는 메서드**
//		table1.addMouseListener(new MouseAdapter() {
//		    @Override
//		    public void mouseClicked(MouseEvent e) {
//		        // 선택된 행의 인덱스 가져오기
//		        int selectedRow = table1.getSelectedRow();
//		        
//		        // 선택된 행에서 영화 제목 가져오기
//		        String movieTitle = (String) table1.getValueAt(selectedRow, 0);
// 
//		        String imagePath = getMovieImagePath(movieTitle); // 해당 영화 제목에 해당하는 이미지 경로를 가져옴
//		        ImageIcon imageIcon = new ImageIcon(imagePath); // 이미지를 로드하여 ImageIcon 객체 생성
//		       
//		        
//		        show_poster.setIcon(imageIcon);
//		      
//		    }
//
//			private String getMovieImagePath(String movieTitle) {
//				String imagesFolder = "src/images/"; // 이미지 폴더 경로
//			    
//			    // 영화 제목에 따라 이미지 파일 경로 반환
//			    if (movieTitle.equals("뽀로로")) {
//			        return imagesFolder + "뽀로로.PNG";
//			    } else if (movieTitle.equals("반지의제왕")) {
//			        return imagesFolder + "반지의제왕.PNG";
//			    } else if (movieTitle.equals("해리포터와비밀의방")) {
//			        return imagesFolder + "해리포터.PNG";
//			    } 
//			    //이미지 사용할때, 이미지도 같이 보내기, 위치경로 확인하기!!! *********************
//			    
//			    // 만약 해당하는 영화 제목에 이미지 파일이 없을 경우 null을 반환하거나 기본 이미지 경로를 반환할 수 있습니다.
//			    // 예시로 null 반환
//			    return null;
//			}
//		});
//		
//	
//		
//		//해당 영화를 눌렀을때, 해당 영화가 가진 시간만을 상영시간표에 출력할수있도록, run과 같은 cmd 설정하여 cp_client에 보내기
//		table1.addMouseListener(new MouseAdapter() {
//		    @Override
//		    public void mouseClicked(MouseEvent e) {
//		    	
//		    	try {
//		    		Protocol p = new Protocol();
//		    		p.setCmd(302);
//		    		out.writeObject(p);
//		    		out.flush();
//				} catch (Exception e2) {
//
//				}
//		   
//		        }
//		        
//		    
//		});
//	}
//
//	
//
		}// 액션리스너 및 화면단 마지막 괄호
//
//	/* 메서드 칸 */
//
//	// 성인과 아동을 각각 콤보박스를 클릭해서 각 가격이 나온것을 합쳐서 나올수있도록.
//	private void updateTotalPrice() {
//		String price_ad = show_price1.getText();
//		String price_ch = show_price2.getText();
//
//		if (price_ad.isEmpty()) {
//			price_ad = "0";
//		}
//
//		if (price_ch.isEmpty()) {
//			price_ch = "0";
//		}
//		int total = Integer.parseInt(price_ad) + Integer.parseInt(price_ch);
//		show_price3.setText(String.valueOf(total));
//	}
//
	// 영화목록을 table1에 추가***

	public void addMovieListToTable(List<TicketBox_VO> movieList) {

		for (TicketBox_VO movie : movieList) {
			model1.addRow(new Object[] { movie.getMovie_name() });

		}
	}

	// 상영 시간을 table3에 추가하기위한 메서드 **
	 public void addTimeListToTable(List<TicketBox_VO> timeList) { 
	
	 int selectedRow = table1.getSelectedRow();
     if (selectedRow != -1) {
         
         String movieTitle = (String) table1.getValueAt(selectedRow, 0);

          //해당 영화에 대한 시작 시간과 종료 시간 가져오기
         List<TicketBox_VO> movieTimes = DAO.getMovieTimes(movieTitle); // DAO와 mapper를 사용하여 DB에서 가져옴
         model3.setRowCount(0);
        // 시작 시간과 종료 시간을 한 행으로 만들어 table3에 추가
         for (TicketBox_VO movieTime : movieTimes) {
            
             model3.addRow(new Object[] {movieTime.getStart_time() + " - " + movieTime.getEnd_time()});
         }
      }
	 
     
//
//	// 접속
//	public void connected() {
//		try {
//			s = new Socket("192.168.0.34", 7780);
//			// 집에서 연습할떄 이건 포트번호를 바꾸자
//			// 집 192.168.0.34
//			// 학원 192.168.0.80
//			out = new ObjectOutputStream(s.getOutputStream());
//			in = new ObjectInputStream(s.getInputStream());
//
//			new Thread(this).start();
//		} catch (Exception e) {
//			System.err.println("연결실패" + e);
//		}
//	}
//	
//	
//	
//	private String getMovieImagePath(String movieTitle) {
//	    String imagesFolder = "src/images/"; // 이미지 폴더 경로
//	    
//	    // 영화 제목에 따라 이미지 파일 경로 반환
//	    if (movieTitle.equals("뽀로로")) {
//	        return imagesFolder + "뽀로로.PNG";
//	    } else if (movieTitle.equals("반지의제왕")) {
//	        return imagesFolder + "반지의제왕.PNG";
//	    } else if (movieTitle.equals("해리포터와비밀의방")) {
//	        return imagesFolder + "해리포터.PNG";
//	    } 
//	    //이미지 사용할때, 이미지도 같이 보내기, 위치경로 확인하기!!! *********************
//	    
//	    // 만약 해당하는 영화 제목에 이미지 파일이 없을 경우 null을 반환하거나 기본 이미지 경로를 반환할 수 있습니다.
//	    // 예시로 null 반환
//	    return null;
//	}
//
//	// 끝내기
//	public void closed() {
//		try {
//			out.close();
//			in.close();
//			s.close();
//			System.exit(0);
//			System.out.println("종료성공");
//		} catch (Exception e) {
//
//		}
//	}
//
//	
//	
//	
//	public void run() {
//		esc: while (true) {
//			// cmd : 0 : 종료, 301:영화목록 출력
//			// 302: 상영시간표 시작 시간 출력 303 :잔여포인트
//	
//			
//			try {
//				Object obj = in.readObject();
//
//				TicketBox_Protocol p = (TicketBox_Protocol) obj;
//
//				if (obj != null) {
//					p = (TicketBox_Protocol) obj;
//					switch (p.getCmd()) {
//					case 0:
//						break esc;
//
//					case 301:
//						List<TicketBox_VO> movieList = p.getList();
//						System.out.println(movieList);
//						addMovieListToTable(movieList);
//						//영화목록은 성공, 건들지말자.					
//						break;
//					case 302:
//						List<TicketBox_VO> movieTimes= p.getList();
//						addTimeListToTable(movieTimes);
//						//상영시간표는 성공, 건들지말자.
//						break;
//					case 303:
//						
//						
//						break;
//					}
//				}
//
//			} catch (Exception e) {
//
//			}
//		} // 무한반복
//		closed();

	
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Ticket_office_main();

			}
		});  
		*/


	}

	 // 메인

} // 클래스