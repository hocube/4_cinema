package snackbar;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import c_loginout.Sign_in;

public class Snack extends JPanel {
	Sign_in sign_in;
	private JPanel panel, subpanel;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_2;
	JButton jb4,jb5,jb6,jb7,jb8,jb9,
	jb10,jb11,jb12,jb13,jb14,jb15,jb16,jb17,jb18;
	private JPanel panel_2;
	
	
	
	public Snack(Sign_in signin) {
		this.sign_in = signin;
		setLayout(null);
		
		
		JButton btnNewButton = new JButton("뒤로가기");
		btnNewButton.setBounds(69, 29, 136, 49);
		add(btnNewButton);
	
		JButton btnNewButton_1 = new JButton("포인트 충전");
		btnNewButton_1.setBounds(537, 29, 156, 49);
		add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("잔여포인트");
		lblNewLabel.setBounds(251, 37, 223, 32);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("4딸_라매점");
		lblNewLabel_1.setBounds(188, 135, 392, 77);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 30));
		add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("팝콘");
		btnNewButton_2.setBounds(197, 282, 165, 57);
		add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("음료");
		btnNewButton_2_1.setBounds(29, 282, 165, 57);
		add(btnNewButton_2_1);
		
		JButton btnNewButton_2_1_1 = new JButton("스넥");
		btnNewButton_2_1_1.setBounds(363, 282, 165, 57);
		add(btnNewButton_2_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("장바구니");
		lblNewLabel_2.setBounds(569, 282, 181, 63);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 20));
		add(lblNewLabel_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(537, 341, 223, 323);
		add(scrollPane_1);
		
		panel_2 = new JPanel();
		scrollPane_1.setViewportView(panel_2);
		panel_2.setLayout(new CardLayout(1, 0));
		
		JLabel lblNewLabel_3 = new JLabel("총 금액은");
		lblNewLabel_3.setBounds(537, 674, 229, 43);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel_3);
		
		JButton btnNewButton_3 = new JButton("결재하기");
		btnNewButton_3.setBounds(288, 741, 266, 49);
		add(btnNewButton_3);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportBorder(UIManager.getBorder("Menu.border"));
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(29, 349, 503, 363);
		add(scrollPane_2);
		
		JPanel panel_1 = new JPanel();
		scrollPane_2.setViewportView(panel_1);
		panel.setLayout(new GridBagLayout());

		
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		jb4 = new JButton("찹쌀떡");

		jb5 = new JButton("메밀묵"); 
		jb6 = new JButton("인절미"); 
		jb7 = new JButton("메밀전병"); 
		jb8 = new JButton("호떡"); 
		jb9 = new JButton("찰떡"); 
		jb10 = new JButton("가래떡"); 
		jb11 = new JButton("감자떡"); 
		
		jb12 = new JButton("수정과"); 
		jb13 = new JButton("식혜"); 
		jb14 = new JButton("소쥬"); 
		jb15 = new JButton("삐루"); 
		
		jb16 = new JButton("조청"); 
		jb17 = new JButton("콩고물"); 
		jb18 = new JButton("고추장");
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel_1.add(jb4);
		jb4.setPreferredSize(new Dimension(230, 240));
	
	
		panel_1.add(jb5);
		jb5.setPreferredSize(new Dimension(230, 240));
		panel_1.add(jb6);
		jb6.setPreferredSize(new Dimension(230, 240));
		panel_1.add(jb7);
		
		panel_1.add(jb8);
	
		panel_1.add(jb9);

		panel_1.add(jb10);

		panel_1.add(jb11);
		
		panel_1.add(jb12);
		
		panel_1.add(jb13);
		
		panel_1.add(jb14);
	
		panel_1.add(jb15);

		panel_1.add(jb16);
	
		panel_1.add(jb17);
	
		panel_1.add(jb18);
	
		subpanel = new JPanel();
		
		JLabel sublblNewLabel = new JLabel("제품명");
		sublblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sublblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		sublblNewLabel.setBounds(12, 22, 176, 45);
		subpanel.add(sublblNewLabel);
		
		JButton subbtnNewButton = new JButton("+");
		subbtnNewButton.setBounds(24, 89, 43, 23);
		subpanel.add(subbtnNewButton);
		
		JLabel sublblNewLabel_1 = new JLabel("1");
		sublblNewLabel_1.setBounds(79, 86, 31, 29);
		subpanel.add(sublblNewLabel_1);
		
		JButton subbtnNewButton_1 = new JButton("-");
		subbtnNewButton_1.setBounds(122, 89, 43, 23);
		subpanel.add(subbtnNewButton_1);
		
		JLabel sublblNewLabel_2 = new JLabel("가격:10000\r\n");
		sublblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		sublblNewLabel_2.setBounds(24, 114, 143, 26);
		subpanel.add(sublblNewLabel_2);
		subpanel.setLayout(null);
		panel_2.add(subpanel);
	}
	}
