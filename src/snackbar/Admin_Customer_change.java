package snackbar;

import javax.swing.JPanel;
import javax.swing.JTextField;

import c_loginout.Sign_in;
import movie_server.CustomerVO;
import movie_server.Protocol;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class Admin_Customer_change extends JPanel {
	Sign_in sign_in;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea_2;
	private JTextArea textArea_3;

	public Admin_Customer_change(Sign_in signin) {
		this.sign_in = signin;
		this.setLayout(null);
			
		JLabel lblNewLabel = new JLabel("이름 :");
		lblNewLabel.setBounds(125, 93, 194, 44);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("생년월일 :");
		lblNewLabel_1.setBounds(125, 188, 194, 55);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		add(lblNewLabel_1);
		
		textArea_2 = new JTextArea();
		textArea_2.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea_2.setBounds(331, 191, 276, 41);
		add(textArea_2);
		
		textArea_3 = new JTextArea();
		textArea_3.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea_3.setBounds(331, 93, 276, 41);
		add(textArea_3);
		
		JLabel lblNewLabel_2 = new JLabel("*비밀번호,핸드폰 번호는 고객에게 마이페이지에서 변경 해달라고 안내 드리면 됩니다");
		lblNewLabel_2.setBounds(86, 28, 649, 25);
		lblNewLabel_2.setForeground(new Color(255, 81, 85));
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 16));
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("변경할 이름 :");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_3.setBounds(125, 408, 194, 44);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_2 = new JLabel("변경할 생년월일 :");
		lblNewLabel_1_2.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(125, 516, 194, 55);
		add(lblNewLabel_1_2);
		
		textField = new JTextField();
		textField.setBounds(331, 408, 276, 35);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(331, 522, 276, 35);
		add(textField_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("::수정할 정보를 입력하세요::");
		lblNewLabel_2_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_2_1.setForeground(new Color(74, 162, 240));
		lblNewLabel_2_1.setFont(new Font("굴림", Font.BOLD, 25));
		lblNewLabel_2_1.setBounds(214, 322, 354, 35);
		add(lblNewLabel_2_1);
		
		JButton btnNewButton = new JButton("변경");
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton.setBounds(202, 674, 159, 41);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("돌아가기");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton_1.setBounds(448, 674, 159, 41);
		add(btnNewButton_1);
		
		
		
		// ===========리스너
		//돌아가기 버튼
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sign_in.card.show(sign_in.pg, "c_admin");
				textArea_2.setText("");
				textArea_3.setText("");
				textField.setText("");
				textField_1.setText("");
			}
		});
		
		//수정하기 버튼
		btnNewButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//Admin_Customer_panel 패널 테이블에 선택된 cust_id를 가져옴;
					String custinfor_a = sign_in.c_admin.selectedName1;
					CustomerVO vo = new CustomerVO();
					Protocol p = new Protocol();
					vo.setCust_id(custinfor_a);//VO에 cust_id, cust_name, cust_birth 세팅 (쿼리문에 사용)
					vo.setCust_name(textField.getText());//이 정보들로 update할것
					vo.setCust_birth(textField_1.getText());
					p.setCmd(406);
					p.setAdminChange_vo(vo);
					sign_in.out.writeObject(p);
					sign_in.out.flush();
				} catch (Exception e2) {
				}
				sign_in.card.show(sign_in.pg, "c_admin");
			}
		});

	}
	
	//고객 정보를 List로 가져온것을 이름,생년월일 jtextarea에 출력하는 메서드
	public void CustListInfo_admin(List<CustomerVO> a_custList) {
		for (CustomerVO k : a_custList) {
			Object[] info = {k.getCust_name(), k.getCust_birth(), k.getPoint() };		
			textArea_3.setText(info[0].toString());
			textArea_2.setText(info[1].toString());			
		}
	}
	
}