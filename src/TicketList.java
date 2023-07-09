import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TicketList extends JPanel {
	private JTable table;
	private JTable table_1;

	public TicketList() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		// 메인 패널
		JPanel mainpanel = new JPanel();
		setBounds(0, 0, 800, 800);
		add(mainpanel);
		mainpanel.setLayout(null);
		
		// 탭 패널
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(26, 130, 747, 560);
		mainpanel.add(tabbedPane);
		
		// 탭1
		JPanel tab1 = new JPanel();
		tabbedPane.addTab("예매 중 티켓", null, tab1, null);
		tab1.setLayout(null);
		
		
		// 탭1 안에 테이블
		// JTable의 컬럼 이름 배열
		String[] columnNames = { "예매번호", "영화제목", "상영일자", "시작시간", "상영관", "좌석정보" };
		DefaultTableModel model = new DefaultTableModel(new Object[0][columnNames.length], columnNames);
		JTable ticket_table = new JTable(model);
		ticket_table.setBounds(0, 0, 740, 530);
		tab1.add(table);
		JPanel tab2 = new JPanel();
		tabbedPane.addTab("취소 티켓", null, tab2, null);
		tab2.setLayout(null);
		
		//탭2 안에 테이블
		String[] columnNames2 = { "예매번호", "영화제목", "상영일자", "시작시간", "상영관", "좌석정보" };
		DefaultTableModel model2 = new DefaultTableModel(new Object[0][columnNames2.length], columnNames2);
		JTable cancel_ticket_table = new JTable(model2);
		cancel_ticket_table.setBounds(0, 50, 740, 530);
		cancel_ticket_table.setEnabled(false);
		tab2.add(table_1);
		
		//버튼 그룹 탭
		Panel buttonGroup = new Panel();
		buttonGroup.setEnabled(false);
		buttonGroup.setBounds(27, 718, 747, 56);
		mainpanel.add(buttonGroup);
		buttonGroup.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// 티켓 확인 버튼
		JButton ticketButton = new JButton("티켓확인");
		ticketButton.setFont(new Font("굴림", Font.PLAIN, 15));
		buttonGroup.add(ticketButton);
		
		// 예매 취소 버튼
		JButton cancelButton = new JButton("예매취소");
		cancelButton.setFont(new Font("굴림", Font.PLAIN, 15));
		buttonGroup.add(cancelButton);
		
		// 뒤로 가기 버튼
		JButton backButton = new JButton("뒤로가기");
		backButton.setFont(new Font("굴림", Font.PLAIN, 15));
		buttonGroup.add(backButton);
		
		// 제목 타이틀 패널
		JPanel titlepanel = new JPanel();
		titlepanel.setBounds(0, 27, 800, 83);
		mainpanel.add(titlepanel);
		
		// 제목 라벨
		JLabel titleLabel = new JLabel("티켓 리스트");
		titleLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 50));
		titlepanel.add(titleLabel);
	}
}
