package snackbar;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import c_loginout.Sign_in;
import movie_server.M_movieVO;
import movie_server.MovieVO;
import movie_server.Protocol;

public class Admin_movie_panel extends JPanel {

	Sign_in sign_in;
	
	private JTable m_table;
	private JScrollPane m_sc;
	private DefaultTableModel m_tableModel;
	private Object ob[][] = new Object[0][7];
	private String[] m_title = { "영화ID", "영화제목", "상영날짜", "시작시간", "종료시간", "포스터", "상영관" };

	private JButton m_insert, m_delete, m_update, m_search, m_all, m_back;

	private JTextField m_textFiled;

	private String[] select = { "영화ID", "영화제목", "상영날짜", "시작시간", "종료시간", "상영관" };
	private JComboBox<String> m_box;

	public String selectedName2;

	public Admin_movie_panel(Sign_in signin) {
		this.sign_in = signin;
		this.setLayout(null);
		
		m_tableModel = new DefaultTableModel(ob, m_title);
		m_table = new JTable(m_tableModel);
		m_sc = new JScrollPane(m_table);
		m_sc.setSize(770,620);
		m_sc.setLocation(6, 10);
		add(m_sc);
		
//		m_table.setEnabled(false);
		m_table.getTableHeader().setReorderingAllowed(false);
		m_table.getTableHeader().setResizingAllowed(false); 
				
		
		
		m_insert = new JButton("영화 올리기");
		m_insert.setBounds(6, 640, 172, 35);
		add(m_insert);
		
		m_delete = new JButton("삭 제");
		m_delete.setBounds(209, 640, 172, 35);
		add(m_delete);
		m_delete.setEnabled(false); 
		
		m_all = new JButton("전체보기");
		m_all.setBounds(610, 640, 172, 35);
		add(m_all);
		
		m_update = new JButton("수 정");
		m_update.setBounds(415, 640, 172, 35);
		add(m_update);
		
		m_textFiled = new JTextField();
		m_textFiled.setBounds(209, 703, 172, 30);
		add(m_textFiled);
		m_textFiled.setColumns(10);
		
//		m_box = new JComboBox<>(select);
//		m_box.setBounds(6, 703, 171, 30);
//		add(m_box);
		
		JLabel jlabel = new JLabel("  영화ID로 정보 검색");
		jlabel.setBounds(6, 703, 171, 30);
		jlabel.setFont(new Font("굴림", Font.BOLD, 15));
		add(jlabel);
		
		m_search = new JButton("검 색");
		m_search.setBounds(415, 696, 172, 35);
		add(m_search);
		
		m_back = new JButton("돌아가기");
		m_back.setBounds(610, 696, 172, 35);
		add(m_back);
		
		
		
		m_insert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			//	AdminMovieAdd adminMovieAdd = new AdminMovieAdd();		
			}
		});
		
		// 영화 정보 전체보기
		m_all.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					m_tableModel.setNumRows(0);
					System.out.println("p601 요청!!!!");
					Protocol p = new Protocol();
					p.setCmd(601);
					sign_in.out.writeObject(p);
					sign_in.out.flush();
				} catch (Exception e2) {
					System.out.println(e2+"601 p 에러");
				}
			}
		});
		
		m_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					m_tableModel.setNumRows(0);
					System.out.println("p602 요청!!!!");
					Protocol p = new Protocol();
					p.setCmd(602);
					sign_in.out.writeObject(p);
					sign_in.out.flush();
				} catch (Exception e2) {
					System.out.println(e2+"602 p 에러");
				}
				
			}
		});
		
		
		
		

//		// 콤보박스 검색
//		m_search.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				try {
//					m_tableModel.setNumRows(0);
//					System.out.println("p602 요청!!!!");
//					Protocol p = new Protocol();
//					p.setCmd(602);
//					sign_in.out.writeObject(p);
//					sign_in.out.flush();
//				} catch (Exception e2) {
//					System.out.println(e2+"602 p 에러");
//				}
//				
//			}
//		});

		
		m_delete.setEnabled(false); // 테이블 열을 선택하지 않으면 삭제버튼 비활성화
		//테이블에서 열 선택해서 정보를 담는 이벤트
		m_table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				//선택된 열이 있는지 확인
				if(!e.getValueIsAdjusting() && m_table.getSelectedRow() != -1) {
					int selectedRow = m_table.getSelectedRow();
					// 선택된 열의 Name 데이터를 변수에 저장
                    selectedName2 = m_table.getValueAt(selectedRow, 0).toString();
                    m_delete.setEnabled(true); // 삭제 버튼 활성화
				}
			}
		});
		
		//버튼을 누르면 회원 정보 삭제
		m_delete.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// 선택된 Name 값을 이용하여 데이터 삭제
		                if (selectedName2 != null) {
		                    int rowCount = m_table.getRowCount();
		                    for (int i = 0; i < rowCount; i++) {
		                        String name = m_table.getValueAt(i, 0).toString();
		                        if (name.equals(selectedName2)) {
		                            m_tableModel.removeRow(i); // 선택된 Name과 일치하는 열 삭제
		                            break; 
		                }
					}	
		                try {
							Protocol p = new Protocol();
							System.out.println("p603 요청!!!@@");
							p.setCmd(603); //프로토콜로 CP_client한테 요청
							p.setDelMovie(selectedName2); // 삭제할 id 프로토콜에 set
							sign_in.out.writeObject(p);
							sign_in.out.flush();
						} catch (Exception e2) {
							System.out.println(e2 +"m_delete err");
						}
		                selectedName2 = null; // 변수 초기화
		                m_delete.setEnabled(false); // 삭제 후 버튼 비활성화
		              }
		           }
				});
		
		
		// 돌아가기
		m_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				m_tableModel.setNumRows(0);
				signin.card.show(signin.pg, "admin");
			}
		});
		
		
		
	}
	public void adminMoiveListToTable(List<M_movieVO> m_movieList) {
		for(M_movieVO k : m_movieList) {
			Object data[] = {k.getMovie_id(),k.getMovie_name(),k.getMovie_date(),k.getStart_time(),k.getEnd_time(),k.getPoster_img(),k.getTheater_id()};
			m_tableModel.addRow(data);
		}
	}
	public void adminResultalert(int result) {
		if(result >= 0) {
			JOptionPane.showMessageDialog(sign_in.m_admin, "삭제 완료 되었습니다", "알림", JOptionPane.WARNING_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(sign_in.m_admin, "실패 했습니다", "알림", JOptionPane.WARNING_MESSAGE);
		}	
	}
	
	
}
