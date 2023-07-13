package snackbar;
import javax.swing.JPanel;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import c_loginout.Main_login;
import c_loginout.Sign_in;
import movie_server.Pay_VO;
import movie_server.Protocol;
import pay.PointChargeDialog;
import pay.PointChargeDialog.PointChargeListener;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;

public class S_Menu1 extends JPanel {
	Sign_in sign_in;
	private JTable table;
	private JScrollPane jscroll;
    private DefaultTableModel tableModel;
    Object ob[][] = new Object[0][3];
    String[] title = {"총주문내역","수량","가격"};
    private JLabel lblNewLabel_1;
	
	public S_Menu1(Sign_in signin) {
		this.sign_in = signin;
		this.setLayout(null);
		
		JButton back_bt = new JButton("뒤로가기");
		back_bt.setBounds(41, 31, 158, 42);
		this.add(back_bt);
		
		JButton snack1point_charge_bt = new JButton("포인트충전");
		snack1point_charge_bt.setBounds(596, 31, 158, 42);
		this.add(snack1point_charge_bt);
		
		JLabel lblNewLabel = new JLabel(signin.p.getC_vo().getCust_name() + " 님");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(284, 31, 158, 29);
		this.add(lblNewLabel);
		
		JLabel snack1_point_label = new JLabel("잔여포인트 : " + signin.p.getC_vo().getPoint());
		snack1_point_label.setHorizontalAlignment(SwingConstants.CENTER);
		snack1_point_label.setBounds(284, 57, 158, 29);
		this.add(snack1_point_label);
		
		JButton btnNewButton_2 = new JButton("팝콘");
		btnNewButton_2.setBounds(199, 91, 131, 42);
		this.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("음료");
		btnNewButton_2_1.setBounds(331, 91, 131, 42);
		this.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_1_1 = new JButton("사이드");
		btnNewButton_2_1_1.setBounds(463, 91, 131, 42);
		this.add(btnNewButton_2_1_1);
		
		JButton snack_pop1 = new JButton("팝콘1");
		snack_pop1.setBounds(92, 147, 203, 135);
		this.add(snack_pop1);
		
		JButton snack_pop2 = new JButton("팝콘2");
		snack_pop2.setBounds(295, 147, 203, 135);
		this.add(snack_pop2);
		
		JButton snack_pop3 = new JButton("팝콘3");
		snack_pop3.setBounds(500, 147, 203, 135);
		this.add(snack_pop3);
		
		JButton snack_pop4 = new JButton("팝콘4");
		snack_pop4.setBounds(92, 284, 203, 135);
		this.add(snack_pop4);
		
		JButton snack_pop5 = new JButton("팝콘5");
		snack_pop5.setBounds(295, 284, 203, 135);
		this.add(snack_pop5);
		
		JButton snack_pop6 = new JButton("팝콘6");
		snack_pop6.setBounds(500, 284, 203, 135);
		this.add(snack_pop6);
		
		JButton snack_pop7 = new JButton("팝콘7");
		snack_pop7.setBounds(92, 422, 203, 135);
		this.add(snack_pop7);
		
		JButton snack_pop8 = new JButton("팝콘8");
		snack_pop8.setBounds(295, 422, 203, 135);
		this.add(snack_pop8);
		
		JButton snack_pop9 = new JButton("팝콘9");
		snack_pop9.setBounds(500, 422, 203, 135);
		this.add(snack_pop9);
		
	
		
		
		
		JButton btnNewButton_4 = new JButton("구매하기");
		btnNewButton_4.setBounds(510, 706, 193, 42);
		this.add(btnNewButton_4);
		
		tableModel = new DefaultTableModel(ob, title);
		table = new JTable(tableModel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(91, 567, 612, 135);
		this.add(scrollPane);
		
		scrollPane.setViewportView(table);
		
		lblNewLabel_1 = new JLabel("합계금액 : 0");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1.setBounds(92, 706, 243, 42);
		add(lblNewLabel_1);
		
		JButton reset_bt = new JButton("비우기");
		reset_bt.setBounds(331, 706, 173, 42);
		add(reset_bt);
		
		
		snack1point_charge_bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PointChargeDialog dialog = new PointChargeDialog((Frame) SwingUtilities.getWindowAncestor(S_Menu1.this));
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
		                    snack1_point_label.setText("잔여포인트 : " + signin.p.getC_vo().getPoint());
						} catch (Exception e2) {
							e2.printStackTrace();
						}
		            }
		        });
		        dialog.setVisible(true);
				
			}
		});
		
		
		back_bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				signin.card.show(signin.pg, "main_login");
				
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
		snack_pop1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String snackName = "팝콘1";
		        int price = 1000;

		        // 배열에 이미 해당 정보가 있는지 확인
		        int existingRowIndex = -1;
		        for (int i = 0; i < tableModel.getRowCount(); i++) {
		            String existingSnackName = (String) tableModel.getValueAt(i, 0);
		            if (existingSnackName.equals(snackName)) {
		                existingRowIndex = i;
		                break;
		            }
		        }

		        if (existingRowIndex >= 0) {
		            // 이미 해당 정보가 배열에 존재하는 경우, 수량을 증가시킴
		            int existingQuantity = (int) tableModel.getValueAt(existingRowIndex, 1);
		            int newQuantity = existingQuantity + 1;
		            tableModel.setValueAt(newQuantity, existingRowIndex, 1);
		        } else {
		            // 해당 정보가 배열에 존재하지 않는 경우, 새로운 행을 추가
		            int quantity = 1;
		            Object[] row = { snackName, quantity, price };
		            tableModel.addRow(row);
		            
		        }

		        // 총 가격 업데이트
		        updateTotalPrice();
		    }
		});
		snack_pop2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String snackName = "팝콘2";
		        int price = 1500;

		        // 배열에 이미 해당 정보가 있는지 확인
		        int existingRowIndex = -1;
		        for (int i = 0; i < tableModel.getRowCount(); i++) {
		            String existingSnackName = (String) tableModel.getValueAt(i, 0);
		            if (existingSnackName.equals(snackName)) {
		                existingRowIndex = i;
		                break;
		            }
		        }

		        if (existingRowIndex >= 0) {
		            // 이미 해당 정보가 배열에 존재하는 경우, 수량을 증가시킴
		            int existingQuantity = (int) tableModel.getValueAt(existingRowIndex, 1);
		            int newQuantity = existingQuantity + 1;
		            tableModel.setValueAt(newQuantity, existingRowIndex, 1);
		        } else {
		            // 해당 정보가 배열에 존재하지 않는 경우, 새로운 행을 추가
		            int quantity = 1;
		            Object[] row = { snackName, quantity, price };
		            tableModel.addRow(row);
		            
		        }

		        // 총 가격 업데이트
		        updateTotalPrice();
		    }
		});
		snack_pop3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String snackName = "팝콘3";
		        int price = 1500;

		        // 배열에 이미 해당 정보가 있는지 확인
		        int existingRowIndex = -1;
		        for (int i = 0; i < tableModel.getRowCount(); i++) {
		            String existingSnackName = (String) tableModel.getValueAt(i, 0);
		            if (existingSnackName.equals(snackName)) {
		                existingRowIndex = i;
		                break;
		            }
		        }

		        if (existingRowIndex >= 0) {
		            // 이미 해당 정보가 배열에 존재하는 경우, 수량을 증가시킴
		            int existingQuantity = (int) tableModel.getValueAt(existingRowIndex, 1);
		            int newQuantity = existingQuantity + 1;
		            tableModel.setValueAt(newQuantity, existingRowIndex, 1);
		        } else {
		            // 해당 정보가 배열에 존재하지 않는 경우, 새로운 행을 추가
		            int quantity = 1;
		            Object[] row = { snackName, quantity, price };
		            tableModel.addRow(row);
		            
		        }

		        // 총 가격 업데이트
		        updateTotalPrice();
		    }
		});
		snack_pop4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String snackName = "팝콘4";
		        int price = 1500;

		        // 배열에 이미 해당 정보가 있는지 확인
		        int existingRowIndex = -1;
		        for (int i = 0; i < tableModel.getRowCount(); i++) {
		            String existingSnackName = (String) tableModel.getValueAt(i, 0);
		            if (existingSnackName.equals(snackName)) {
		                existingRowIndex = i;
		                break;
		            }
		        }

		        if (existingRowIndex >= 0) {
		            // 이미 해당 정보가 배열에 존재하는 경우, 수량을 증가시킴
		            int existingQuantity = (int) tableModel.getValueAt(existingRowIndex, 1);
		            int newQuantity = existingQuantity + 1;
		            tableModel.setValueAt(newQuantity, existingRowIndex, 1);
		        } else {
		            // 해당 정보가 배열에 존재하지 않는 경우, 새로운 행을 추가
		            int quantity = 1;
		            Object[] row = { snackName, quantity, price };
		            tableModel.addRow(row);
		            
		        }

		        // 총 가격 업데이트
		        updateTotalPrice();
		    }
		});
		snack_pop5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String snackName = "팝콘5";
		        int price = 1500;

		        // 배열에 이미 해당 정보가 있는지 확인
		        int existingRowIndex = -1;
		        for (int i = 0; i < tableModel.getRowCount(); i++) {
		            String existingSnackName = (String) tableModel.getValueAt(i, 0);
		            if (existingSnackName.equals(snackName)) {
		                existingRowIndex = i;
		                break;
		            }
		        }

		        if (existingRowIndex >= 0) {
		            // 이미 해당 정보가 배열에 존재하는 경우, 수량을 증가시킴
		            int existingQuantity = (int) tableModel.getValueAt(existingRowIndex, 1);
		            int newQuantity = existingQuantity + 1;
		            tableModel.setValueAt(newQuantity, existingRowIndex, 1);
		        } else {
		            // 해당 정보가 배열에 존재하지 않는 경우, 새로운 행을 추가
		            int quantity = 1;
		            Object[] row = { snackName, quantity, price };
		            tableModel.addRow(row);
		            
		        }

		        // 총 가격 업데이트
		        updateTotalPrice();
		    }
		});
		snack_pop6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String snackName = "팝콘6";
		        int price = 1500;

		        // 배열에 이미 해당 정보가 있는지 확인
		        int existingRowIndex = -1;
		        for (int i = 0; i < tableModel.getRowCount(); i++) {
		            String existingSnackName = (String) tableModel.getValueAt(i, 0);
		            if (existingSnackName.equals(snackName)) {
		                existingRowIndex = i;
		                break;
		            }
		        }

		        if (existingRowIndex >= 0) {
		            // 이미 해당 정보가 배열에 존재하는 경우, 수량을 증가시킴
		            int existingQuantity = (int) tableModel.getValueAt(existingRowIndex, 1);
		            int newQuantity = existingQuantity + 1;
		            tableModel.setValueAt(newQuantity, existingRowIndex, 1);
		        } else {
		            // 해당 정보가 배열에 존재하지 않는 경우, 새로운 행을 추가
		            int quantity = 1;
		            Object[] row = { snackName, quantity, price };
		            tableModel.addRow(row);
		            
		        }

		        // 총 가격 업데이트
		        updateTotalPrice();
		    }
		});
		snack_pop7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String snackName = "팝콘7";
		        int price = 1500;

		        // 배열에 이미 해당 정보가 있는지 확인
		        int existingRowIndex = -1;
		        for (int i = 0; i < tableModel.getRowCount(); i++) {
		            String existingSnackName = (String) tableModel.getValueAt(i, 0);
		            if (existingSnackName.equals(snackName)) {
		                existingRowIndex = i;
		                break;
		            }
		        }

		        if (existingRowIndex >= 0) {
		            // 이미 해당 정보가 배열에 존재하는 경우, 수량을 증가시킴
		            int existingQuantity = (int) tableModel.getValueAt(existingRowIndex, 1);
		            int newQuantity = existingQuantity + 1;
		            tableModel.setValueAt(newQuantity, existingRowIndex, 1);
		        } else {
		            // 해당 정보가 배열에 존재하지 않는 경우, 새로운 행을 추가
		            int quantity = 1;
		            Object[] row = { snackName, quantity, price };
		            tableModel.addRow(row);
		            
		        }

		        // 총 가격 업데이트
		        updateTotalPrice();
		    }
		});
		snack_pop8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String snackName = "팝콘8";
		        int price = 1500;

		        // 배열에 이미 해당 정보가 있는지 확인
		        int existingRowIndex = -1;
		        for (int i = 0; i < tableModel.getRowCount(); i++) {
		            String existingSnackName = (String) tableModel.getValueAt(i, 0);
		            if (existingSnackName.equals(snackName)) {
		                existingRowIndex = i;
		                break;
		            }
		        }

		        if (existingRowIndex >= 0) {
		            // 이미 해당 정보가 배열에 존재하는 경우, 수량을 증가시킴
		            int existingQuantity = (int) tableModel.getValueAt(existingRowIndex, 1);
		            int newQuantity = existingQuantity + 1;
		            tableModel.setValueAt(newQuantity, existingRowIndex, 1);
		        } else {
		            // 해당 정보가 배열에 존재하지 않는 경우, 새로운 행을 추가
		            int quantity = 1;
		            Object[] row = { snackName, quantity, price };
		            tableModel.addRow(row);
		            
		        }

		        // 총 가격 업데이트
		        updateTotalPrice();
		    }
		});
		snack_pop9.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String snackName = "팝콘9";
		        int price = 1500;

		        // 배열에 이미 해당 정보가 있는지 확인
		        int existingRowIndex = -1;
		        for (int i = 0; i < tableModel.getRowCount(); i++) {
		            String existingSnackName = (String) tableModel.getValueAt(i, 0);
		            if (existingSnackName.equals(snackName)) {
		                existingRowIndex = i;
		                break;
		            }
		        }

		        if (existingRowIndex >= 0) {
		            // 이미 해당 정보가 배열에 존재하는 경우, 수량을 증가시킴
		            int existingQuantity = (int) tableModel.getValueAt(existingRowIndex, 1);
		            int newQuantity = existingQuantity + 1;
		            tableModel.setValueAt(newQuantity, existingRowIndex, 1);
		        } else {
		            // 해당 정보가 배열에 존재하지 않는 경우, 새로운 행을 추가
		            int quantity = 1;
		            Object[] row = { snackName, quantity, price };
		            tableModel.addRow(row);
		            
		        }

		        // 총 가격 업데이트
		        updateTotalPrice();
		    }
		});
		reset_bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 테이블 데이터 초기화
                tableModel.setRowCount(0);
                // 총 가격 초기화
                updateTotalPrice();
				
			}
		});
	}
	
		// 총 가격 업데이트 메소드
	private void updateTotalPrice() {
		    int total = 0;
		    for (int i = 0; i < tableModel.getRowCount(); i++) {
		        int quantity = (int) tableModel.getValueAt(i, 1);
		        int price = (int) tableModel.getValueAt(i, 2);
		        total += quantity * price;
		    }
	
		    lblNewLabel_1.setText("합계금액 : " + total);
	}
}

