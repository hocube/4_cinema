package pay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import c_loginout.Sign_in;
import movie_server.CustomerVO;
import movie_server.Pay_VO;

// 포인트 충분할 때 나오는 결제창~
public class Pay extends JDialog {

	Sign_in sign_in;
	
	JLabel remainingLabel_2;

	int chargepoint;
	Pay_VO pay_vo;

	public Pay(Frame parent) {
		super(parent, "결제창", true);
		//this.sign_in = signin;

		this.setModal(true);

		JPanel contentPanel = new JPanel();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel remainingLabel = new JLabel("잔여포인트 : ");
			remainingLabel.setBounds(106, 68, 134, 24);
			remainingLabel.setHorizontalAlignment(SwingConstants.CENTER);
			remainingLabel.setFont(new Font("굴림", Font.BOLD, 20));
			contentPanel.add(remainingLabel);
		}
		{
			remainingLabel_2 = new JLabel();
			remainingLabel_2.setBackground(new Color(255, 255, 255));
			remainingLabel_2.setBounds(252, 68, 80, 25);
			remainingLabel_2.setFont(new Font("굴림", Font.BOLD, 20));
			contentPanel.add(remainingLabel_2);
		}
		{
			JLabel payLabel = new JLabel("결제포인트 : ");
			payLabel.setHorizontalAlignment(SwingConstants.CENTER);
			payLabel.setFont(new Font("굴림", Font.BOLD, 20));
			payLabel.setBounds(106, 132, 134, 24);
			contentPanel.add(payLabel);
		}
		{
			JLabel payLabel_2 = new JLabel("30000원");
			payLabel_2.setFont(new Font("굴림", Font.BOLD, 20));
			payLabel_2.setBounds(252, 132, 80, 25);
			contentPanel.add(payLabel_2);
		}
			JButton payButton = new JButton("결제하기");
			payButton.setBounds(117, 215, 85, 25);
			contentPanel.add(payButton);
			payButton.setActionCommand("Pay");
		{
			JButton cancelButton = new JButton("취소하기");
			cancelButton.setBounds(252, 215, 85, 25);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
 
		payButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Pay.this, "결제 완료 되었습니다.", "안내", JOptionPane.INFORMATION_MESSAGE);
                dispose();
			}
		});
		
		// setSize(350, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		//setVisible(true);

	}
	
	public void showPoint(int point) {
		remainingLabel_2.setText(String.valueOf(point)+"원");
		System.out.println("현재 회원의 잔여포인트:"+point);
		setVisible(true);
	}
	

//        payButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    System.out.println("===결제하기 버튼 클릭!===");
//
//                    Pay_VO pay_vo = new Pay_VO();
//
//                    pay_vo.setMovie_id("2");
//                    pay_vo.setCust_id(currentUserId);
//                    pay_vo.setMovie_name("제발!!!");
//                    pay_vo.setTheater_id("미나리");
//                    pay_vo.setMovie_date("2023-07-02");
//                    pay_vo.setStart_time("13:30");
//                    pay_vo.setEnd_time("15:50");
//                    pay_vo.setTheater_seat("E열1");
//
//                    Protocol p = new Protocol();
//                    p.setCmd(103);
//                    p.setPay_vo(pay_vo);
//
//                    System.out.println("영화 정보가 입력되었습니다.");
//                } catch (Exception e2) {
//                }
//            }
//        });
//            }

//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                if (network.getS() != null) {
//                    System.out.println("결제하기창 닫기 클릭! p.setCmd(100)");
//                    Protocol p = new Protocol();
//                    p.setCmd(100);
//                    try {
//                        network.sendProtocol(p);
//                    } catch (Exception e2) {
//
//                    }
//                } else {
//                    System.out.println("결제하기창 닫기 클릭! network.closed()");
//                    network.closed();
//                }
//            }
//        });
//    }

//    private void loadRemainingPoint() {
//        try {
//            System.out.println("===loadRemainingPoint 실행===");
//
//            Pay_VO pay_vo = new Pay_VO();
//            currentUserId = Session.getCurrentUserId();
//            pay_vo.setCust_id(currentUserId);
//
//            System.out.println("===잔여포인트 가져오기 실행===");
//
//            Protocol p = new Protocol();
//            p.setCmd(102);
//            p.setPay_vo(pay_vo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void run() {
//        esc: while (true) {
//            try {
//                Object obj = network.getIn().readObject();
//                if (obj != null) {
//                    Protocol p = (Protocol) obj;
//                    switch (p.getCmd()) {
//                        case 100:
//                            break esc;
//                        case 102:
//                            System.out.println("===Pay.java의 case1===");
//                            chargepoint = p.getResult();
//                            jl2.setText(" " + Integer.toString(chargepoint) + "원");
//                            break;
//                        case 103:
//                            Reservation_completed reservationCompleted = new Reservation_completed(sign_in);
//                            setVisible(false);
//                            break;
//                        case 3:
//                            System.out.println("===Pay.java의 case3===");
//                            break;
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
	}