package ticket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import c_loginout.Sign_in;
import movie_server.MobileTicket_VO;

public class MobileTicket extends JDialog {
	Sign_in sign_in;
	
	JPanel MainP, CenterWrapperPanel, ButtonPanel;
	JLabel ticketNumLabel, NameLabel, DayLabel, TimeLabel, theaterIdLabel, SeatLabel;
	JButton complete;
    private TicketList ticketList;

	public MobileTicket(Frame parent) {
		super(parent, "모바일 티켓", true);
		//this.sign_in = signin;
		this.setModal(true); 
		
		MainP = new JPanel();
		MainP.setLayout(new BoxLayout(MainP, BoxLayout.Y_AXIS));
		add(MainP);

		JPanel NorthPanel = new JPanel();
		JLabel titleLabel = new JLabel("모바일 티켓");
		titleLabel.setFont(new Font("굴림", Font.BOLD, 25));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

		NorthPanel.add(titleLabel);

		// 센터 패널을 감싸는 패널
		CenterWrapperPanel = new JPanel(new BorderLayout());
		CenterWrapperPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		MainP.add(CenterWrapperPanel);

		// 예매번호
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p1.setBackground(Color.WHITE);
		ticketNumLabel = new JLabel();
		ticketNumLabel.setFont(new Font("굴림", Font.BOLD, 18));
		p1.add(ticketNumLabel);
		
		// 영화제목
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p2.setBackground(Color.WHITE);
		NameLabel = new JLabel();
		NameLabel.setFont(new Font("굴림", Font.BOLD, 25));
		p2.add(NameLabel);

		// 상영일자
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p3.setBackground(Color.WHITE);
		DayLabel = new JLabel();
		DayLabel.setFont(new Font("굴림", Font.BOLD, 18));
		p3.add(DayLabel);

		// 상영시간
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p4.setBackground(Color.WHITE);
		TimeLabel = new JLabel();
		TimeLabel.setFont(new Font("굴림", Font.BOLD, 18));
		p4.add(TimeLabel);

		// 상영관
		JPanel p5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p5.setBackground(Color.WHITE);
		theaterIdLabel = new JLabel();
		theaterIdLabel.setFont(new Font("굴림", Font.BOLD, 18));
		p5.add(theaterIdLabel);

		// 좌석정보
		JPanel p6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p6.setBackground(Color.WHITE);
		SeatLabel = new JLabel();
		SeatLabel.setFont(new Font("굴림", Font.BOLD, 18));
		p6.add(SeatLabel);

		JPanel p7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p7.setBackground(Color.WHITE);
		JLabel line = new JLabel("============================================");
		p7.add(line);

		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JPanel p8 = new JPanel(new BorderLayout());
		JTextArea text1 = new JTextArea("- 지연 입장에 의한 관람불편을 최소화하고자 본 영화는 약 10분 후에 시작됩니다.");
		text1.setLineWrap(true);
		text1.setWrapStyleWord(true);
		text1.setEditable(false);
		text1.setMargin(new Insets(0, 5, 0, 0)); // 상, 좌, 하, 우 여백 설정
		p8.add(text1, BorderLayout.CENTER);

		JPanel p9 = new JPanel(new BorderLayout());
		JTextArea text2 = new JTextArea("- 관람에티켓을 위한 사전입장을 부탁드립니다.");
		text2.setLineWrap(true);
		text2.setWrapStyleWord(true);
		text2.setEditable(false);
		text2.setMargin(new Insets(10, 5, 0, 0)); // 상, 좌, 하, 우 여백 설정
		p9.add(text2, BorderLayout.CENTER);
		
		// 한 패널에 붙이기(그리드로 만들기 위해)
		JPanel InfoPanel = new JPanel();
		InfoPanel.setBackground(Color.WHITE);
		InfoPanel.setPreferredSize(new Dimension(300, 300));
		InfoPanel.setLayout(new GridLayout(9, 1));

		InfoPanel.add(p1);
		InfoPanel.add(p2);
		InfoPanel.add(p3);
		InfoPanel.add(p4);
		InfoPanel.add(p5);
		InfoPanel.add(p6);
		InfoPanel.add(p7);
		InfoPanel.add(p8);
		InfoPanel.add(p9);

		// 센터 패널
		JPanel CenterPanel = new JPanel(new BorderLayout());
		CenterPanel.setPreferredSize(new Dimension(250, 430));
		CenterPanel.setBackground(Color.WHITE);
		Border border = BorderFactory.createLineBorder(Color.BLACK); // 테두리 생성
		CenterPanel.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10))); // 테두리와 빈 여백 설정
		MainP.add(CenterPanel);

		// 이미지 크기 조정
		ImageIcon icon = new ImageIcon(getClass().getResource("/images/barcode.png"));
		Image image = icon.getImage().getScaledInstance(300, 80, Image.SCALE_DEFAULT);
		ImageIcon resizedIcon = new ImageIcon(image);
		
		// 이미지를 JLabel에 추가
		JLabel img = new JLabel(resizedIcon);
		img.setHorizontalAlignment(JLabel.CENTER);
		
		CenterPanel.add(InfoPanel, BorderLayout.NORTH);
		CenterPanel.add(img, BorderLayout.SOUTH);
		
		CenterWrapperPanel.add(CenterPanel, BorderLayout.NORTH);
		
		// 버튼 패널
		ButtonPanel = new JPanel();
		complete = new JButton("확인 완료");
		ButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		// ButtonPanel의 테두리에 빈 여백 추가      상, 좌, 하, 우
		ButtonPanel.add(complete);

		MainP.add(NorthPanel, BorderLayout.NORTH);
		MainP.add(CenterWrapperPanel, BorderLayout.CENTER);
		MainP.add(ButtonPanel, BorderLayout.SOUTH);

        setSize(350, 600);
        setLocationRelativeTo(null);  // 화면 중앙에 위치하게 함
		
		// 확인 완료 버튼 -> 창 꺼지고 
        // 완료 버튼 -> 창 꺼지고 
        complete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 모바일 티켓 창 닫기
                dispose();
            }
        });
	}
	
	// TicketList에서 받아온 티켓 정보를 라벨에 세팅해주는 메서드
	public void showMobileTicket(MobileTicket_VO m_vo) {
	    ticketNumLabel.setText("예매번호 : " + String.valueOf(m_vo.getTicket_num()));
	    NameLabel.setText(m_vo.getMovie_name());
	    DayLabel.setText(m_vo.getMovie_date().toString());
	    TimeLabel.setText(m_vo.getStart_time() + " 시작");
	    theaterIdLabel.setText(m_vo.getTheater_id() + "관");
	    SeatLabel.setText(m_vo.getTheater_seat());
	    setVisible(true);
	}

}