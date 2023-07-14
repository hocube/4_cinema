package snackbar;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import c_loginout.Sign_in;
import movie_server.CustomerVO;
import movie_server.Protocol;

public class Admin_Customer_panel extends JPanel {
	Sign_in sign_in;
	private JTable table;
	private JScrollPane jscroll;
	private DefaultTableModel tableModel;
	Object ob[][] = new Object[0][4];
	String[] title = { "아이디", "이름", "생년월일", "전화" };
	private JTextField textField;
	public String selectedName1; // 선택된 열의 ID를 저장할 변수, 보내야 해서 public으로 설정

	public Admin_Customer_panel(Sign_in signin) {

		this.sign_in = signin;
		this.setLayout(null);

		/*
		 * 원래 jtable 코드 table = new JTable(); table.setBounds(6, 10, 776, 620);
		 * add(table);
		 */

		tableModel = new DefaultTableModel(ob, title);
		table = new JTable(tableModel);
		jscroll = new JScrollPane(table);
		jscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jscroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jscroll.setSize(776, 620);
		jscroll.setLocation(6, 10);
		// table.setEnabled(false); // 테이블수정 불가 메서드. 삭제버튼 누를때 확인
		add(jscroll);

		JButton btnNewButton = new JButton("관리자 권한 추가");
		btnNewButton.setBounds(6, 640, 172, 35);
		add(btnNewButton);

		JButton b_delete = new JButton("삭 제");
		b_delete.setBounds(209, 640, 172, 35);
		add(b_delete);

		JButton b_all = new JButton("전체보기");
		b_all.setBounds(610, 640, 172, 35);
		add(b_all);

		JButton b_sel = new JButton("고객정보 수정");
		b_sel.setBounds(415, 640, 172, 35);
		add(b_sel);

		textField = new JTextField();
		textField.setBounds(209, 703, 172, 21);
		add(textField);
		textField.setColumns(10);

		JLabel jlabel_a = new JLabel("회원 아이디로 정보 검색");
		jlabel_a.setFont(new Font("굴림", Font.BOLD, 15));
		jlabel_a.setBounds(6, 706, 171, 15);
		add(jlabel_a);

		JButton b_search = new JButton("검 색");
		b_search.setBounds(415, 696, 172, 35);
		add(b_search);

		JButton b_back = new JButton("돌아가기");
		b_back.setBounds(610, 696, 172, 35);
		add(b_back);

		// 버튼리스너=========================================

		b_delete.setEnabled(false); // 테이블 열을 선택하지 않으면 삭제버튼 비활성화
		btnNewButton.setEnabled(false); // 테이블 열을 선택하지 않으면 관리자 추가버튼 비활성화
		b_sel.setEnabled(false); // 테이블 열을 선택하지 않으면 고객수정 버튼 비활성화

		// 돌아가기
		b_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sign_in.card.show(sign_in.pg, "admin");
				tableModel.setNumRows(0);
				b_delete.setEnabled(false); 
				btnNewButton.setEnabled(false);
				b_sel.setEnabled(false);
			}
		});

		// 회원 정보 전체보기 (아이디,이름,생년월일,전화)
		b_all.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					tableModel.setNumRows(0); // 테이블 초기화
					// 프로토콜에 cmd(401:전체보기)를 담아서 요청
					Protocol p = new Protocol();
					p.setCmd(401);
					sign_in.out.writeObject(p);
					sign_in.out.flush();
				} catch (Exception e2) {
				}
			}
		});

		// 테이블에서 열 선택해서 정보를 담는 이벤트
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// 선택된 열이 있는지 확인
				if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
					int selectedRow = table.getSelectedRow();
					// 선택된 열의 Name 데이터를 변수에 저장
					selectedName1 = table.getValueAt(selectedRow, 0).toString();
					b_delete.setEnabled(true); // 삭제 버튼 활성화
					btnNewButton.setEnabled(true); // 관리자 추가버튼 활성화
					b_sel.setEnabled(true); // 수정버튼 활성화
				}else {
					b_delete.setEnabled(false);  // 선택되지 않으면 비활성화
					btnNewButton.setEnabled(false); 
					b_sel.setEnabled(false); 
				}
			}
		});

		// 버튼을 누르면 회원 정보 삭제
		b_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 선택된 Name 값을 이용하여 데이터 삭제
				if (selectedName1 != null) {
					int rowCount = table.getRowCount();
					for (int i = 0; i < rowCount; i++) {
						String name = table.getValueAt(i, 0).toString();
						if (name.equals(selectedName1)) {
							tableModel.removeRow(i); // 선택된 Name과 일치하는 열 삭제
							break;
						}
					}
					try {
						Protocol p = new Protocol();
						p.setCmd(402); // 프로토콜로 CP_client한테 요청
						p.setDel_id(selectedName1); // 삭제할 id 프로토콜에 set
						sign_in.out.writeObject(p);
						sign_in.out.flush();
					} catch (Exception e2) {
					}
					selectedName1 = null; // 변수 초기화
					b_delete.setEnabled(false); 
					btnNewButton.setEnabled(false); 
					b_sel.setEnabled(false); 
				}
			}
		});

		// 관리자 권한 추가 버튼 리스너. 선택된 열 저장하는 변수 공유
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					tableModel.setNumRows(0); // 테이블 초기화
					Protocol p = new Protocol();
					p.setCmd(404);
					p.setDel_id(selectedName1);// 관리자 추가할 아이디
					sign_in.out.writeObject(p);
					sign_in.out.flush();
				} catch (Exception e2) {
				}
				selectedName1 = null; // 변수 초기화
				b_delete.setEnabled(false); 
				btnNewButton.setEnabled(false); 
				b_sel.setEnabled(false); 
			}
		});

		// ID로 검색 버튼 리스너
		b_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					tableModel.setNumRows(0); // 테이블 초기화
					Protocol p = new Protocol();
					p.setCmd(403);
					p.setDel_id(textField.getText());
					sign_in.out.writeObject(p);
					sign_in.out.flush();
				} catch (Exception e2) {
				}
				textField.setText(""); // textField 초기화				
			}
		});
		// 수정하기 패널로 넘어가는 버튼
		b_sel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sign_in.card.show(sign_in.pg, "cc_admin");
				try {
					tableModel.setNumRows(0); // 테이블 초기화
					Protocol p = new Protocol();
					p.setCmd(405);
					p.setDel_id(selectedName1);//고객 정보를 띄울 아이디를 보냄
					sign_in.out.writeObject(p);
					sign_in.out.flush();
				} catch (Exception e2) {
				}
				textField.setText(""); // textField 초기화
				b_delete.setEnabled(false); 
				btnNewButton.setEnabled(false); 
				b_sel.setEnabled(false); 
			}
		});

	}
	// 생성자 끝

	// 고객목록 테이블에 출력하는 메서드
	public void adminCustListToTable(List<CustomerVO> a_custList) {
		for (CustomerVO k : a_custList) {
			Object data[] = { k.getCust_id(), k.getCust_name(), k.getCust_birth(), k.getCust_phone() };
			tableModel.addRow(data);
		}
	}
	
}