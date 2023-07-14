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

		setLocationRelativeTo(null);
		setResizable(false);

	}

	public void showPoint(int point) {
		remainingLabel_2.setText(String.valueOf(point) + "원");
		setVisible(true);
	}

}