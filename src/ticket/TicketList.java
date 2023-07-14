package ticket;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
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
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import c_loginout.Main_login;
import c_loginout.Sign_in;
import movie_server.DAO;
import movie_server.MobileTicket_VO;
import movie_server.Pay_VO;
import movie_server.Protocol;
import pay.PointChargeDialog;

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
		String[] columnNames = { "예매번호", "영화제목", "상영일자", "상영시간", "상영관", "좌석정보" };

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
		
		// 티켓 확인 버튼 - 프로토콜 사용 안함.
		ticketButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MobileTicket dialog = new MobileTicket((Frame) SwingUtilities.getWindowAncestor(TicketList.this));

				// 현재 선택된 행의 정보 가져오기
				int selectedRow = ticketTable.getSelectedRow();
				
				if (selectedRow != -1) {
					try {
						// 선택 된 행의 정보 가져오기
			            int ticketNumber = (int) ticketTable.getValueAt(selectedRow, 0);
			            String movieTitle = (String) ticketTable.getValueAt(selectedRow, 1);
			            String screeningDate = (String) ticketTable.getValueAt(selectedRow, 2);
			            String startTime = (String) ticketTable.getValueAt(selectedRow, 3);
			            String theater = (String) ticketTable.getValueAt(selectedRow, 4);
			            String seatInfo = (String) ticketTable.getValueAt(selectedRow, 5);
			            
		                // MobileTicket_VO에 정보 저장
		                MobileTicket_VO m_vo = new MobileTicket_VO();
		                m_vo.setTicket_num(ticketNumber);
		                m_vo.setMovie_name(movieTitle);
		                m_vo.setMovie_date(screeningDate);
		                m_vo.setStart_time(startTime);
		                m_vo.setTheater_id(theater);
		                m_vo.setTheater_seat(seatInfo);
		                
		                // showMobileTicket 메서드 호출하여 파라미터로 전달
		                dialog.showMobileTicket(m_vo);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(getParent(), "티켓을 선택해 주세요.");
				}
			}
		});


		// 예매 취소 버튼 -> 선택한 행의 티켓 삭제 후 리스트 업데이트
		cancelButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = ticketTable.getSelectedRow();
		        if (selectedRow != -1) {
		            int ticketNumber = (int) ticketTable.getValueAt(selectedRow, 0);
		            String movieTitle = (String) ticketTable.getValueAt(selectedRow, 1);

		            int result = JOptionPane.showConfirmDialog(null, "'" + movieTitle + "'" + " 예매를 취소하시겠습니까?", "예매 취소",
		                    JOptionPane.YES_NO_OPTION);

		            // "예" 선택 시
		            if (result == JOptionPane.YES_OPTION) {
		                try {
		                    MobileTicket_VO m_vo = new MobileTicket_VO();
		                    Protocol p = new Protocol();        
		                    m_vo.setTicket_num(ticketNumber);
		                    p.setM_vo(m_vo);
		                    p.setCmd(105);

		                    signin.out.writeObject(p);
		                    signin.out.flush();
		                } catch (Exception e2) {
		                    e2.printStackTrace();
		                }

		            }
		        } else {
		            JOptionPane.showMessageDialog(getParent(), "티켓을 선택해 주세요.");
		        }
		    }
		});
		
		// 뒤로 가기 버튼
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				signin.card.show(signin.pg, "main_login");
			}
		});
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
}