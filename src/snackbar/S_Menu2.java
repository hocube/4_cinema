package snackbar;
import javax.swing.JPanel;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import c_loginout.Sign_in;
import movie_server.Pay_VO;
import movie_server.Protocol;
import pay.PointChargeDialog;
import pay.PointChargeDialog.PointChargeListener;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class S_Menu2 extends JPanel {
	Sign_in sign_in;
	private JTable table;

	public S_Menu2(Sign_in signin) {
		this.sign_in = signin;
		this.setLayout(null);
		
		JButton back_bt = new JButton("뒤로가기");
		back_bt.setBounds(41, 31, 158, 55);
		this.add(back_bt);
		
		JButton snack2point_charge_bt = new JButton("포인트충전");
		snack2point_charge_bt.setBounds(596, 31, 158, 55);
		this.add(snack2point_charge_bt);
		
		JLabel lblNewLabel = new JLabel(signin.p.getC_vo().getCust_name() + " 님");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(284, 31, 158, 29);
		this.add(lblNewLabel);
		
		JLabel snack2_point_label = new JLabel("잔여포인트 : " + signin.p.getC_vo().getPoint());
		snack2_point_label.setHorizontalAlignment(SwingConstants.CENTER);
		snack2_point_label.setBounds(284, 57, 158, 29);
		this.add(snack2_point_label);
		
		JButton btnNewButton_2 = new JButton("팝콘");
		btnNewButton_2.setBounds(199, 119, 131, 42);
		this.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("음료");
		btnNewButton_2_1.setBounds(331, 119, 131, 42);
		this.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_1_1 = new JButton("사이드");
		btnNewButton_2_1_1.setBounds(463, 119, 131, 42);
		this.add(btnNewButton_2_1_1);
		
		JButton btnNewButton_3 = new JButton("음료1");
		btnNewButton_3.setBounds(92, 175, 203, 135);
		this.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("음료2");
		btnNewButton_3_1.setBounds(295, 175, 203, 135);
		this.add(btnNewButton_3_1);
		
		JButton btnNewButton_3_1_1 = new JButton("음료3");
		btnNewButton_3_1_1.setBounds(500, 175, 203, 135);
		this.add(btnNewButton_3_1_1);
		
		JButton btnNewButton_3_1_1_1 = new JButton("음료6");
		btnNewButton_3_1_1_1.setBounds(500, 312, 203, 135);
		this.add(btnNewButton_3_1_1_1);
		
		JButton btnNewButton_3_1_2 = new JButton("음료5");
		btnNewButton_3_1_2.setBounds(295, 312, 203, 135);
		this.add(btnNewButton_3_1_2);
		
		JButton btnNewButton_3_2 = new JButton("음료4");
		btnNewButton_3_2.setBounds(92, 312, 203, 135);
		this.add(btnNewButton_3_2);
		
		JButton btnNewButton_3_1_1_1_1 = new JButton("음료9");
		btnNewButton_3_1_1_1_1.setBounds(500, 450, 203, 135);
		this.add(btnNewButton_3_1_1_1_1);
		
		JButton btnNewButton_3_1_2_1 = new JButton("음료8");
		btnNewButton_3_1_2_1.setBounds(295, 450, 203, 135);
		this.add(btnNewButton_3_1_2_1);
		
		JButton btnNewButton_3_2_1 = new JButton("음료7");
		btnNewButton_3_2_1.setBounds(92, 450, 203, 135);
		this.add(btnNewButton_3_2_1);
		
		JButton btnNewButton_4 = new JButton("구매하기");
		btnNewButton_4.setBounds(295, 734, 193, 42);
		this.add(btnNewButton_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(91, 595, 612, 135);
		this.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		

		back_bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				signin.card.show(signin.pg, "main_login");
				
			}
		});
				
	
		
	snack2point_charge_bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PointChargeDialog dialog = new PointChargeDialog((Frame) SwingUtilities.getWindowAncestor(S_Menu2.this));
		        dialog.setPointChargeListener(new PointChargeListener() {
		            public void onPointCharge(int amount) {
		            	
		                // PointChargeDialog에서 선택한 포인트
		                System.out.println("충전된 포인트: " + amount);
		                
						try {
							Pay_VO p_vo = new Pay_VO();
							Protocol p = new Protocol();
							
							p_vo.setCust_id(signin.p.getC_vo().getCust_id());
							p_vo.setPoint(amount);
							p.setP_vo(p_vo);
							p.setCmd(102);
							
							signin.out.writeObject(p);
							signin.out.flush();
							
		                    signin.p.getC_vo().setPoint(signin.p.getC_vo().getPoint() + amount);
		                    snack2_point_label.setText("잔여포인트 : " + signin.p.getC_vo().getPoint());
						} catch (Exception e2) {
							e2.printStackTrace();
						}
		            }
		        });
		        dialog.setVisible(true);
				
			}
		});
		
	btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				signin.card.show(signin.pg, "snack1");
				
			}
		});

		btnNewButton_2_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				signin.card.show(signin.pg, "snack2");
				
			}
		});
		
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				signin.card.show(signin.pg, "snack3");
				
			}
		});
	}
}
