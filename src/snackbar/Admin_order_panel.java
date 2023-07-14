package snackbar;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import c_loginout.Sign_in;

public class Admin_order_panel extends JPanel {

	Sign_in sign_in;
	private JTable table;
	private JScrollPane jscroll;
	private DefaultTableModel tableModel;
	Object ob[][] = new Object[0][4];
	String[] title = { "주문번호", "이름", "주문품목", "가격" };
	private JTextField textField;
	public String selectedName1; // 선택된 열의 ID를 저장할 변수, 보내야 해서 public으로 설정

	public Admin_order_panel(Sign_in signin) {

		this.sign_in = signin;
		this.setLayout(null);

		tableModel = new DefaultTableModel(ob, title);
		table = new JTable(tableModel);
		jscroll = new JScrollPane(table);
		jscroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jscroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jscroll.setSize(776, 620);
		jscroll.setLocation(6, 10);
		// table.setEnabled(false); // 테이블수정 불가 메서드. 삭제버튼 누를때 확인
		add(jscroll);

		JButton btnNewButton = new JButton("주문 취소");
		btnNewButton.setBounds(6, 640, 375, 35);
		add(btnNewButton);

		JButton b_all = new JButton("주문 전체보기");
		b_all.setBounds(415, 640, 367, 35);
		add(b_all);

		textField = new JTextField();
		textField.setBounds(209, 703, 172, 21);
		add(textField);
		textField.setColumns(10);

		JLabel jlabel_a = new JLabel("회원 이름으로 정보 검색");
		jlabel_a.setFont(new Font("굴림", Font.BOLD, 15));
		jlabel_a.setBounds(6, 703, 171, 18);
		add(jlabel_a);

		JButton b_search = new JButton("검 색");
		b_search.setBounds(415, 696, 172, 35);
		add(b_search);

		JButton b_back = new JButton("돌아가기");
		b_back.setBounds(610, 696, 172, 35);
		add(b_back);

		// 돌아가기
		b_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sign_in.card.show(sign_in.pg, "admin");
			}
		});
	}

}
