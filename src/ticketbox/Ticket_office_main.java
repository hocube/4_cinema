package ticketbox;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import c_loginout.Sign_in;
import movie_server.DAO;
import movie_server.MobileTicket_VO;
import movie_server.Protocol;
import movie_server.TicketBox_VO;

//이걸로 합치기!
//매표소 만들기 
public class Ticket_office_main extends JPanel {
	// 필요한 것들 선언하자
	Sign_in sign_in;

	String selectedMovieName;
	JLabel price1, price2,lblNewLabel_10_3,lblNewLabel,lblNewLabel_1,lblNewLabel_2,lblNewLabel_2_1,lblNewLabel_2_1_1,lblNewLabel_2_1_1_1,
	lblNewLabel_3,lblNewLabel_4,lblNewLabel_5,lblNewLabel_5_1,lblNewLabel_6,lblNewLabel_7,lblNewLabel_7_1,lblNewLabel_7_1_1,lblNewLabel_7_1_1_1,
	lblNewLabel_8,lblNewLabel_8_1,lblNewLabel_8_2,lblNewLabel_8_2_1,lblNewLabel_9,lblNewLabel_10,lblNewLabel_10_1,lblNewLabel_10_2;
	String price_ad, price_ch;
	

	 Icon theater ;
     String adultCount,childCount, date, time, amount ,room;
     //매표소 > 좌석창으로 값 보내기 위한 변수선언
	
	public JTable table_1,table_2,table_3;
	public DefaultTableModel model1,model2,model3;
	
	int aCount, cCount;
	//콤보박스 인원을 체크하기위한 임의변수선언.

	Date now = new Date();

	

	JScrollPane jsp1, jsp2, jsp3;
	JButton btnNewButton_1,btnNewButton_2;
	private JButton more_bt;
	JComboBox<String> comboBox, comboBox_1;
	JCalendar calendar;
	Protocol p;
	
	
	public Ticket_office_main(Sign_in signin) {
		this.sign_in = signin;
		
		this.setLayout(null);

		lblNewLabel_2 = new JLabel("[영화 선택]");
		lblNewLabel_2.setBounds(27, 56, 82, 15);
		this.add(lblNewLabel_2);

		lblNewLabel_2_1 = new JLabel("[극장 선택]");
		lblNewLabel_2_1.setBounds(211, 56, 82, 15);
		this.add(lblNewLabel_2_1);

		lblNewLabel_2_1_1 = new JLabel("[날짜 선택]");
		lblNewLabel_2_1_1.setBounds(400, 56, 82, 15);
		this.add(lblNewLabel_2_1_1);

		lblNewLabel_2_1_1_1 = new JLabel("[선택한 정보]");
		lblNewLabel_2_1_1_1.setBounds(610, 56, 82, 15);
		this.add(lblNewLabel_2_1_1_1);

		calendar = new JCalendar();
		calendar.setBounds(400, 94, 180, 221);
		this.add(calendar);

		
		Calendar today = Calendar.getInstance();
		Calendar maxDate = (Calendar) today.clone();
		maxDate.add(Calendar.WEEK_OF_YEAR, 1);

		calendar.setMaxSelectableDate(maxDate.getTime());
		
		lblNewLabel_3 = new JLabel("[상영 시간]");
		lblNewLabel_3.setBounds(27, 430, 82, 15);
		this.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("[인원 선택]");
		lblNewLabel_4.setBounds(230, 430, 82, 15);
		this.add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("성인:");
		lblNewLabel_5.setBounds(230, 469, 28, 15);
		this.add(lblNewLabel_5);

		lblNewLabel_5_1 = new JLabel("아동:");
		lblNewLabel_5_1.setBounds(324, 469, 34, 15);
		this.add(lblNewLabel_5_1);

		String[] a_peo = { "0", "1", "2", "3", "4", "5"};
		String[] c_peo = { "0", "1", "2", "3", "4", "5"};

		comboBox = new JComboBox(a_peo);
		comboBox.setBounds(270, 465, 42, 23);
		this.add(comboBox);

		comboBox_1 = new JComboBox(c_peo);
		comboBox_1.setBounds(362, 465, 42, 23);
		this.add(comboBox_1);

		lblNewLabel_6 = new JLabel();//영화 포스터 들어갈 공간.

		lblNewLabel_6.setBounds(610, 81, 161, 460);
		this.add(lblNewLabel_6);

		lblNewLabel_7 = new JLabel("극장 :");
		lblNewLabel_7.setBounds(610, 573, 57, 15);
		this.add(lblNewLabel_7);

		lblNewLabel_7_1 = new JLabel("날짜 :");
		lblNewLabel_7_1.setBounds(610, 598, 57, 15);
		this.add(lblNewLabel_7_1);

		lblNewLabel_7_1_1 = new JLabel("시간 :");
		lblNewLabel_7_1_1.setBounds(610, 623, 70, 15);
		this.add(lblNewLabel_7_1_1);

		lblNewLabel_7_1_1_1 = new JLabel("인원");
		lblNewLabel_7_1_1_1.setBounds(610, 648, 34, 15);
		this.add(lblNewLabel_7_1_1_1);

		lblNewLabel_8 = new JLabel("성인:");
		lblNewLabel_8.setBounds(642, 648, 34, 15);
		this.add(lblNewLabel_8);

		lblNewLabel_8_1 = new JLabel("아동:");
		lblNewLabel_8_1.setBounds(706, 648, 34, 15);
		this.add(lblNewLabel_8_1);

		lblNewLabel_8_2 = new JLabel("0명"); //콤보박스 체크시, 인원변동되는 라벨. 기본
		lblNewLabel_8_2.setBounds(681, 648, 32, 15);
		this.add(lblNewLabel_8_2);

		lblNewLabel_8_2_1 = new JLabel("0명");//콤보박스 체크시, 인원변동되는 라벨. 기본 
		lblNewLabel_8_2_1.setBounds(749, 648, 32, 15);
		this.add(lblNewLabel_8_2_1);

		lblNewLabel_9 = new JLabel("금액:");
		lblNewLabel_9.setBounds(610, 673, 57, 15);
		this.add(lblNewLabel_9);
		price1 = new JLabel(); //밑에 계산하기위한 라벨 선언
		price2 = new JLabel(); //밑에 계산하기위한 라벨 선언

		lblNewLabel_10 = new JLabel();
		lblNewLabel_10.setBounds(656, 573, 100, 15);  //극장명 들어갈 공간
		this.add(lblNewLabel_10);
		// db정보로 넣기

		lblNewLabel_10_1 = new JLabel();  //날짜가 들어갈 공간
		lblNewLabel_10_1.setBounds(656, 598, 57, 15);
		this.add(lblNewLabel_10_1);
		// db정보로 넣기

		lblNewLabel_10_2 = new JLabel();  //시간이 들어갈 공간
		lblNewLabel_10_2.setBounds(656, 623, 100, 15);
		this.add(lblNewLabel_10_2);
		// db정보로 넣기

		lblNewLabel_10_3 = new JLabel();  //금액이 들어갈 공간.
		lblNewLabel_10_3.setBounds(656, 673, 57, 15);
		this.add(lblNewLabel_10_3);
		// db정보로 넣기

		model1 = new DefaultTableModel(new Object[] { "영화 목록" }, 0);
		table_1 = new JTable(model1);
		jsp1 = new JScrollPane(table_1);
		// jsp1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jsp1.setBounds(27, 94, 172, 221);
		this.add(jsp1);

		model2 = new DefaultTableModel(new Object[] { "극장 목록" }, 0);
		model2.addRow(new Object[] { "한국 ICT관" });
		table_2 = new JTable(model2);
		jsp2 = new JScrollPane(table_2);	
		// table_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jsp2.setBounds(211, 94, 172, 221);
		this.add(jsp2);
		
		
		model3 = new DefaultTableModel(new Object[] { "상영 시간" }, 0);
		table_3 = new JTable(model3);
		jsp3 = new JScrollPane(table_3);
		// table_3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jsp3.setBounds(27, 469, 172, 221);
		this.add(jsp3);
		

		btnNewButton_1 = new JButton("예매 하기");
		btnNewButton_1.setBounds(450, 714, 115, 35);
		this.add(btnNewButton_1);
		// db정보로 넣기

		btnNewButton_2 = new JButton("홈으로");
		btnNewButton_2.setBounds(235, 714, 115, 35);
		this.add(btnNewButton_2);

		
		//[7/11 추가 -지혜 , 영화포스터만 눌러서 들어갈경우, 한 이름밖에 안떠서 
		//(여러개중에 하나만 자동클릭이 안되어, 한 영화목록만뜨게함)
		//그래서 이 버튼을 추가해서 이자리에서 다른영화 더 볼수있도록 하기로함. 
		more_bt = new JButton("+더보기");
		more_bt.setBounds(100, 50, 95, 22);
		this.add(more_bt);  //이건 포스터 눌렀을때만 클릭가능하게하고, 빠른예매는 비활성화하기.
		
		String adultCountText = lblNewLabel_8_2.getText().replace("명", "");
	    String childCountText = lblNewLabel_8_2_1.getText().replace("명", "");
	    aCount = Integer.parseInt(adultCountText);
	    cCount = Integer.parseInt(childCountText);
		
	    
	    
		
		
		// 예매하기 버튼을 눌렀을경우에 좌석 선택창으로 넘어가진다.
		// 매표소는 숨겨지고, 좌석확인창을 객체 생성하여 보이게 한다.
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					theater = lblNewLabel_6.getIcon(); //매표소의 영화포스터 겟		
			        adultCount = lblNewLabel_8_2.getText(); //매표소의 성인인원 겟
			        childCount = lblNewLabel_8_2_1.getText(); //매표소의 아동인원 겟
			        date = lblNewLabel_10_1.getText(); //매표소의 날짜 겟
			        time = lblNewLabel_10_2.getText(); //매표소의 시간 겟
			        amount = lblNewLabel_10_3.getText();//매표소의 금액 겟	
			        room = lblNewLabel_10.getText();//매표소의 극장관 겟
			        
			        
			        //결제하기로 넘어갈때, 선택한정보 라벨에 빈칸있나없나 확인하려는 
			        Icon lbl6Text = lblNewLabel_6.getIcon();
			        String lbl8_2Text = lblNewLabel_8_2.getText();
			        String lbl8_2_1Text = lblNewLabel_8_2_1.getText();
			        String lbl10_1Text = lblNewLabel_10_1.getText();
			        String lbl10_2Text = lblNewLabel_10_2.getText();
			        String lbl10_3Text = lblNewLabel_10_3.getText();
			        String lbl10Text = lblNewLabel_10.getText();
			        
			        //빈칸이 있다면 채우라는 경고창 뜨기.
			        if (lbl6Text==null|| lbl8_2Text.isEmpty() || lbl8_2_1Text.isEmpty() ||
			                lbl10_1Text.isEmpty() || lbl10_2Text.isEmpty() || lbl10_3Text.isEmpty() || lbl10Text.isEmpty()) {
			            JOptionPane.showMessageDialog(null, "빈칸 없이 선택하십시오.", "경고", JOptionPane.WARNING_MESSAGE);
			        } else {
			        	
			            // 경고창이 뜨지 않은 경우에 수행할 동작 추가
			            sign_in.card.show(sign_in.pg, "pay");
			            
			        sign_in.card.show(sign_in.pg, "t_seat"); //좌석확인창 카드쇼 
			        sign_in.t_seat.lblNewLabel_6.setIcon(theater);  //좌석확인창의 영화아이콘 붙이기
			        sign_in.t_seat.lblNewLabel_8_2.setText(adultCount); //좌석확인창의 성인인원 붙이기
			        sign_in.t_seat.lblNewLabel_8_2_1.setText(childCount); //좌석확인창의 아동인원 붙이기
			        sign_in.t_seat.lblNewLabel_10.setText(room); //좌석확인창의 극장관이름 붙이기
			        sign_in.t_seat.lblNewLabel_10_1.setText(date); //좌석확인창의의 날짜 붙이기
			        sign_in.t_seat.lblNewLabel_10_2.setText(time); //좌석확인창의 시간붙이기
			        sign_in.t_seat.lblNewLabel_10_3.setText(amount); //좌석확인창의 총금액 붙이기
			        }
			       
			}
		});
		
		
		
		//홈으로 버튼을 누르면 메인화면으로 넘어간다. 
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//홈으로 버튼 누르기전에 선택한 값의 정보를 초기화할지 묻는다.
				 int result = JOptionPane.showConfirmDialog(null, "정말 홈으로 가겠습니까? 모든 선택이 초기화됩니다.", 
						 "홈으로", JOptionPane.YES_NO_OPTION);
				 if (result == JOptionPane.YES_OPTION) {
				
					 //홈으로 눌렀을경우 선택했던 모든것 초기화. 
					 sign_in.card.show(sign_in.pg, "main_login");
					 model1.setRowCount(0);
					 model3.setRowCount(0);
					 System.out.println("홈으로 버튼 누르면 초기화,메인");
					 lblNewLabel_6.setIcon(null);
					 lblNewLabel_8_2.setText("");
					 lblNewLabel_8_2_1.setText("");
					 lblNewLabel_10.setText("");
					 lblNewLabel_10_1.setText("");
					 lblNewLabel_10_2.setText("");
					 lblNewLabel_10_3.setText("");
					 table_1.clearSelection();
					 table_2.clearSelection();
					 table_3.clearSelection();
					 comboBox.setSelectedIndex(0);
					 comboBox_1.setSelectedIndex(0);
			}
		}
		});
		

		// 날짜를 선택했을때, 선택한 영화정보 탭의 날짜에 표시하게하는 이벤트
		calendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals("calendar")) {
					Calendar selectedDate = (Calendar) evt.getNewValue();

					// 현재 날짜 가져오기
					Calendar currentDate = Calendar.getInstance();
					currentDate.set(Calendar.HOUR_OF_DAY, 0);
					currentDate.set(Calendar.MINUTE, 0);
					currentDate.set(Calendar.SECOND, 0);
					currentDate.set(Calendar.MILLISECOND, 0);

					// 선택한 날짜와 현재 날짜 비교
					if (selectedDate.before(currentDate)) {
						// 선택한 날짜가 오늘 이전인 경우
						// 이전 날짜는 선택할 수 없도록 처리

						// 이전 날짜로 변경된 경우 현재 날짜로 다시 설정
						calendar.setCalendar(currentDate);

						// "과거의 날짜는 선택할 수 없습니다"라는 알림 창 표시
						SwingUtilities.invokeLater(() -> {
							JOptionPane.showMessageDialog(null, "과거의 날짜는 선택할 수 없습니다.", "경고",
									JOptionPane.WARNING_MESSAGE);
						});
					} else {
						// 선택한 날짜가 오늘 이후인 경우
						SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
						String formattedDate = dateFormat.format(selectedDate.getTime());
						lblNewLabel_10_1.setText(formattedDate);
					}
				}
			}
		});

		// 성인과 아동 인원을 선택하였을 경우, 선택한 영화정보에 동적으로 바뀔수있도록 출력하기.
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			JComboBox<String> combo = (JComboBox<String>) e.getSource();
				String selectedOption = (String) combo.getSelectedItem();
				lblNewLabel_8_2.setText(selectedOption + "명");
				if (selectedOption.equals("0")) {
					lblNewLabel_8_2.setText("0명");
				}

				if (selectedOption != null) {
					int ad = Integer.parseInt(selectedOption);
					int res1 = ad * 10000;
					price_ad = String.valueOf(res1);
					price1 = new JLabel() ;
					price1.setText(price_ad);
					updateTotalPrice();
				}
			}
		});
		
	// 성인과 아동 인원을 선택하였을 경우, 선택한 영화정보에 동적으로 바뀔수있도록 출력하기.
		comboBox_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			JComboBox<String> combo = (JComboBox<String>) e.getSource();
				String selectedOption = (String) combo.getSelectedItem();
				lblNewLabel_8_2_1.setText(selectedOption + "명");
				if (selectedOption.equals("0")) {
					lblNewLabel_8_2_1.setText("0명");
				}
				if (selectedOption != null) {
					int ad = Integer.parseInt(selectedOption);
					int res2 = ad * 5000;
					price_ch = String.valueOf(res2);
					price2 = new JLabel() ;
					price2.setText(price_ch);
					updateTotalPrice();
				}

			}
		});

		// 극장관은 1개만 있으므로, ICT관 을 테이블에 추가하고, 그것을 클릭하였을경우
		// 발생하는 이벤트 리스너 작성.
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int select_theater = table_2.getSelectedRow();
				Object[] rowData = new Object[table_2.getColumnCount()];
				for (int i = 0; i < table_2.getColumnCount(); i++) {
					rowData[i] = table_2.getValueAt(select_theater, i);
				}
				lblNewLabel_10.setText(Arrays.toString(rowData));
				System.out.println("ICT클릭 이벤트 성공");
				// 입력이 잘되었는지 확인하기위한 콘솔출력
			}

		});
		
		
		//table3의 있는 행들을 클릭할때마다 라벨에 붙이기. 
		table_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int select_theater = table_3.getSelectedRow();
				Object[] rowData = new Object[table_3.getColumnCount()];
				for (int i = 0; i < table_3.getColumnCount(); i++) {
					rowData[i] = table_3.getValueAt(select_theater, i);
				}
				lblNewLabel_10_2.setText(Arrays.toString(rowData));
				System.out.println("시간클릭시 삽입성공");
				// 입력이 잘되었는지 확인하기위한 콘솔출력
		}

		});
		
		
		//테이블1인 영화목록을 클릭했을때, 이미지 들어갈수있도록 삽입하는 메서드**
		table_1.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // 선택된 행의 인덱스 가져오기
		        int selectedRow = table_1.getSelectedRow();
		        
		        // 선택된 행에서 영화 제목 가져오기
		        String movieTitle = (String) table_1.getValueAt(selectedRow, 0);
 
		        String imagePath = getMovieImagePath(movieTitle); // 해당 영화 제목에 해당하는 이미지 경로를 가져옴
		        ImageIcon originalIcon = new ImageIcon(imagePath); // 이미지를 로드하여 ImageIcon 객체 생성
		        Image originalImage = originalIcon.getImage(); // ImageIcon에서 Image를 추출

		        // 라벨 크기에 맞게 이미지 크기 조절
		        int lblWidth = lblNewLabel_6.getWidth();
		        int lblHeight = lblNewLabel_6.getHeight();
		        Image resizedImage = originalImage.getScaledInstance(lblWidth, lblHeight, Image.SCALE_SMOOTH);

		        // 조정된 이미지를 이용하여 ImageIcon 객체 생성
		        ImageIcon resizedIcon = new ImageIcon(resizedImage);

		        lblNewLabel_6.setIcon(resizedIcon); // 라벨에 이미지 아이콘 설정
		        
		        String selectedValue = (String) table_1.getValueAt(selectedRow,0);
				sign_in.tb_pay.lblNewLabel_10.setText(selectedValue);

		      
		    }

			private String getMovieImagePath(String movieTitle) {
				String imagesFolder = "src/images/"; // 이미지 폴더 경로
			    
			    // 영화 제목에 따라 이미지 파일 경로 반환
			    if (movieTitle.equals("뽀로로")) {
			        return imagesFolder + "뽀로로.PNG";
			    } else if (movieTitle.equals("반지의제왕")) {
			        return imagesFolder + "반지의제왕.PNG";
			    } else if (movieTitle.equals("해리포터와비밀의방")) {
			        return imagesFolder + "해리포터.PNG";
			    } else if (movieTitle.equals("엘리멘탈")) {
			    	return imagesFolder + "엘리멘탈.JPG";
			    }
			    //이미지 사용할때, 이미지도 같이 보내기, 위치경로 확인하기!!! *********************
			    
			    // 만약 해당하는 영화 제목에 이미지 파일이 없을 경우 null을 반환하거나 기본 이미지 경로를 반환할 수 있습니다.
			    // 예시로 null 반환
			    return null;
		}
		});
		
	
		
		//해당 영화를 눌렀을때, 해당 영화가 가진 시간만을 상영시간표에 출력할수있도록, run과 같은 cmd 설정하여 cp_client에 보내기
		table_1.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	lblNewLabel_8_2.setText("");
		    	lblNewLabel_8_2_1.setText("");
		    	lblNewLabel_10_1.setText("");
		    	lblNewLabel_10_2.setText("");
		    	lblNewLabel_10_3.setText("");
		    	comboBox.setSelectedIndex(0);
				comboBox_1.setSelectedIndex(0);
				calendar.setDate(new Date());
				//calendar.setSelectableDateRange(, null);
		    	try {
		    		Protocol p = new Protocol();
		    		p.setCmd(302);
		    		sign_in.out.writeObject(p);
		    		sign_in.out.flush();
				} catch (Exception e2) {

				}
		   
		        }
		        
		    
		});
		
		
		//[0711] 추가 -지혜
		more_bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//영화포스터만을 눌러서 매표소에 왔을떄 더보기 버튼이 활성화된다. 
				//더보기 버튼 눌렀을때 영화 목록을 가져오는 메서드 실행. 
				//cmd (301) 과 동일.
				try {
					Protocol p = new Protocol();
					p.setCmd(301);
					sign_in.out.writeObject(p);
					sign_in.out.flush();			
					System.out.println("더보기cmd보냈나");
					model1.setRowCount(0);
					
					more_bt.setEnabled(false);

					
				} catch (Exception e2) {
					
				}
				
				 lblNewLabel_6.setIcon(null);
				 lblNewLabel_8_2.setText("");
				 lblNewLabel_8_2_1.setText("");
				 lblNewLabel_10.setText("");
				 lblNewLabel_10_1.setText("");
				 lblNewLabel_10_2.setText("");
				 lblNewLabel_10_3.setText("");
				 table_1.clearSelection();
				 table_2.clearSelection();
				 table_3.clearSelection();
				 comboBox.setSelectedIndex(0);
				 comboBox_1.setSelectedIndex(0);
				 //calendar.setDate(new Date());
			}
		});
		
		
	table_3.addMouseListener( new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			  
			
			
			
		}
		
	});
		
	}// 액션리스너 및 화면단 마지막 괄호

	

	/* 메서드 칸 */

	// 성인과 아동을 각각 콤보박스를 클릭해서 각 가격이 나온것을 합쳐서 나올수있도록.
	private void updateTotalPrice() {
		String price_ad = price1.getText();
		String price_ch = price2.getText();

		if (price_ad.isEmpty()) {
			price_ad = "0";
		}

		if (price_ch.isEmpty()) {
			price_ch = "0";
		}
		int total = Integer.parseInt(price_ad) + Integer.parseInt(price_ch);
		lblNewLabel_10_3.setText(String.valueOf(total));
	}

	// 영화목록을 table1에 추가***

	public void addMovieListToTable(List<TicketBox_VO> movieList) {
		
		for (TicketBox_VO movie : movieList) {
			//model1.addColumn("영화 목록");
			model1.addRow(new Object[] { movie.getMovie_name() });

		}
	}

	// 상영 시간을 table3에 추가하기위한 메서드 **
	public void addTimeListToTable(List<TicketBox_VO> timeList) { 
	
	 int selectedRow = table_1.getSelectedRow();
     if (selectedRow != -1) {
         
         String movieTitle = (String) table_1.getValueAt(selectedRow, 0);

          //해당 영화에 대한 시작 시간과 종료 시간 가져오기
         List<TicketBox_VO> movieTimes = DAO.getMovieTimes(movieTitle); // DAO와 mapper를 사용하여 DB에서 가져옴
         model3.setRowCount(0);
        // 시작 시간과 종료 시간을 한 행으로 만들어 table3에 추가
         for (TicketBox_VO movieTime : movieTimes) {
            
             model3.addRow(new Object[] {movieTime.getStart_time() + " - " + movieTime.getEnd_time()});
             
           
             
            
         }
        	 
         
     }
	}
	

	
	//포스터 선택으로 들어갔을시, 테이블1 초기화후 집어넣기
	public void updateChoiceTable(TicketBox_VO movieChoice) {
		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		model.setRowCount(0); // 기존 테이블 데이터를 초기화
			Object[] rowData = { movieChoice.getMovie_name()};
			model.addRow(rowData);
		}
	
	
	//main_loin에서 빠른예매나 포스터버튼 클릭시, 이 클래스의 more_bt 사용하기 위해서 리턴 선언.
	public JButton getMoreButton() {
        return more_bt;
    }
	

    public int getAdultCount() {
        return aCount;
    }

    public int getChildCount() {
        return cCount;
    }

	}  //클래스의 마지막 괄호.