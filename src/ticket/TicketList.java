package ticket;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import c_loginout.Sign_in;
import movie_server.DAO;
import movie_server.MobileTicket_VO;
import movie_server.Protocol;

// 원래꺼
public class TicketList extends JPanel{
	Sign_in sign_in;
	JPanel Panel;
	JTable ticketTable;
	JButton ticketButton, cancelButton, backButton;
	String currentUserId;
	
	public TicketList(Sign_in signin) {
		this.sign_in = signin;
		
		setPreferredSize(new Dimension(600, 500));
		setVisible(true);

		Panel = new JPanel(new BorderLayout());
		JPanel headerPanel = new JPanel(new BorderLayout());

		// "티켓 리스트" 라벨 추가
		JLabel titleLabel = new JLabel("티켓 리스트");
		titleLabel.setFont(new Font("굴림", Font.BOLD, 35));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // 가운데 정렬
		titleLabel.setBorder(BorderFactory.createEmptyBorder(70, 10, 10, 10)); // 여백 추가
		headerPanel.add(titleLabel, BorderLayout.NORTH);
		Panel.add(headerPanel, BorderLayout.NORTH);

		// JTable의 컬럼 이름 배열
		String[] columnNames = { "예매번호", "영화제목", "상영일자", "시작시간", "상영관", "좌석정보" };

		// 빈 JTable 생성
		DefaultTableModel model = new DefaultTableModel(new Object[0][columnNames.length], columnNames);
		ticketTable = new JTable(model);

		// JScrollPane을 사용하여 JTable을 스크롤 가능하도록 추가
		JScrollPane scrollPane = new JScrollPane(ticketTable);
		// JScrollPane의 크기를 조정
		scrollPane.setPreferredSize(new Dimension(500, 400));
		// JPanel에 JScrollPane 추가
		Panel.add(headerPanel, BorderLayout.NORTH);
		Panel.add(scrollPane, BorderLayout.CENTER);

		// "티켓 확인" 버튼 생성
		JPanel ButtonPanel = new JPanel();
		ticketButton = new JButton("티켓 확인");
		cancelButton = new JButton("예매 취소");
		backButton = new JButton("뒤로가기");
		ButtonPanel.add(ticketButton);
		ButtonPanel.add(cancelButton);
		ButtonPanel.add(backButton);
		Panel.add(ButtonPanel, BorderLayout.SOUTH);

		add(Panel);

		// "티켓 확인" 버튼
		ticketButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				

				// 현재 선택된 행의 정보 가져오기
				int selectedRow = ticketTable.getSelectedRow();

				// 선택 된 행의 정보 가져오기
				if (selectedRow != -1) {
					//DAO ticketDAO = new DAO();
					//List<MobileTicket_VO> ticket = DAO.getTicketByRow();

					// 해당 모바일 티켓 화면에 띄우기
					new MobileTicket(signin); // TicketList 객체 전달
				
				}
			}
		});


		// 예매 취소 버튼 -> 선택한 행의 티켓 삭제 후 리스트 업데이트
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = ticketTable.getSelectedRow();

				if (selectedRow != -1) {
					int result = JOptionPane.showConfirmDialog(null, "예매를 취소하시겠습니까?", "예매 취소",
							JOptionPane.YES_NO_OPTION);

					// "예" 선택 시
					if (result == JOptionPane.YES_OPTION) {
						// 선택된 행의 티켓 번호 가져오기.
						int ticket_num = Integer.parseInt(ticketTable.getValueAt(selectedRow, 0).toString());
						DAO ticket_dao = new DAO();
						int success = ticket_dao.cancelTicket(ticket_num); // 선택된 티켓 번호로 예매 취소

						// 예매 취소 성공 시
						if (success == 1) {
							JOptionPane.showMessageDialog(null, "예매가 취소되었습니다.");
							// 리스트 업데이트
							//showTicketList();
						} else {
							JOptionPane.showMessageDialog(null, "예매 취소에 실패했습니다.");
						}
					}
				}
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				signin.card.show(signin.pg, "main_login");
			}
		});
	}
	
	
	 public TicketList(String currentUserId) {
	        this.currentUserId = currentUserId;

	    }


	// Table에 리스트 보여주는 메서드
		public void updateTable(List<MobileTicket_VO> tickets) {
			DefaultTableModel model = (DefaultTableModel) ticketTable.getModel();
			model.setRowCount(0); // 기존 테이블 데이터를 초기화
			for (MobileTicket_VO ticket : tickets) {
				Object[] rowData = { ticket.getTicket_num(), ticket.getMovie_name(), ticket.getMovie_date(),
						ticket.getStart_time(), ticket.getTheater_id(), ticket.getTheater_seat() };
				model.addRow(rowData);
			}
		}

	// 취소된 티켓을 제외한 티켓 목록을 가져오는 메서드
	/*public List<MobileTicket_VO> getFilteredTickets() {
		List<MobileTicket_VO> filteredTickets = new ArrayList<>();

		// DAO호출
		DAO ticketDAO = new DAO();
		//String currentUserId = Session.getCurrentUserId();
		ArrayList<MobileTicket_VO> ticketList = ticketDAO.getTicketList(currentUserId);

		// 취소된 티켓을 제외한 티켓 목록 필터링
		// 반환 값이 0인 경우에 해당 티켓은 취소되지 않은 것
		for (MobileTicket_VO ticket : ticketList) {
			if (ticket.getTicket_canceled() == 0) {
				filteredTickets.add(ticket);
			}
		}
		return filteredTickets;
	}*/

	// 티켓 목록 업데이트 메서드
	// 티켓 취소 후 다시 돌아왔을 때 취소된 티켓을 제외한 목록 보여주기 위해
	/*public void refreshTicketList() {
		List<MobileTicket_VO> filteredTickets = getFilteredTickets();

		DefaultTableModel tableModel = (DefaultTableModel) ticketTable.getModel();
		tableModel.setRowCount(0); // 기존 데이터 삭제

		for (MobileTicket_VO ticket : filteredTickets) {
			Object[] rowData = { ticket.getTicket_num(), ticket.getMovie_name(), ticket.getMovie_date(),
					ticket.getStart_time(), ticket.getTheater_id(), ticket.getTheater_seat() };
			tableModel.addRow(rowData);
		}
	}*/
	}