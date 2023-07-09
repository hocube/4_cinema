import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

public class Point extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Point dialog = new Point();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Point() {
		setBounds(100, 100, 500, 400);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel title = new JPanel();
			getContentPane().add(title);
		}
		{
			JPanel radiopanel = new JPanel();
			getContentPane().add(radiopanel);
			radiopanel.setLayout(new GridLayout(0, 1, 0, 0));
			{
				JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
				radiopanel.add(rdbtnNewRadioButton);
			}
		}
		{
			JPanel buttonpanel = new JPanel();
			getContentPane().add(buttonpanel);
			buttonpanel.setLayout(new GridLayout(0, 1, 0, 0));
			{
				JLabel lblNewLabel = new JLabel("포인트 충전");
				buttonpanel.add(lblNewLabel);
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 35));
			}
		}
	}

}
