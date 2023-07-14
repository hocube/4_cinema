package ticketbox;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;

import c_loginout.Sign_in;

public class Ticket_seat_map extends JPanel {
	Sign_in sign_in;
	ArrayList<String> selectedSeats = new ArrayList<>();
	JCheckBox seatCheckBox;
	String seatName;
	int sum, su2;

	// 체크박스 배열로 만듬.
	public Ticket_seat_map(Sign_in signin) {
		this.sign_in = signin;
		setLayout(new GridLayout(5, 5));

		char rowLabel = 'A';
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				String seatName = String.valueOf(rowLabel) + (col + 1);
				seatCheckBox = new JCheckBox(seatName);
				seatCheckBox.addActionListener(new SeatCheckBoxListener());
				add(seatCheckBox);
			}
			rowLabel++;
		}
	}

	// 좌석선택창에서 콤보벅스 눌러 상영관 변경시 선택좌석 클리어
	public void resetSelectedSeats() {
		selectedSeats.clear();
		updateSelectedSeatsLabel();
	}

	// 좌석선택창에서 콤보벅스 눌러 상영관 변경시 체크박스 클리어
	public void resetCheckBoxes() {
		for (Component component : getComponents()) {
			if (component instanceof JCheckBox) {
				JCheckBox checkBox = (JCheckBox) component;
				checkBox.setSelected(false);
				checkBox.setEnabled(true);
			}
		}
	}

	public int selectedSeats() {
		int su = selectedSeats.size();
		return su;

	}

	private class SeatCheckBoxListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JCheckBox sourceCheckBox = (JCheckBox) e.getSource();
			String seatName = sourceCheckBox.getText();
			boolean isChecked = sourceCheckBox.isSelected();

			if (isChecked) {
				selectedSeats.add(seatName);
			} else {
				selectedSeats.remove(seatName);
			}

			updateSelectedSeatsLabel();
		}
	}

	public void updateSelectedSeatsLabel() {
		StringBuilder sb = new StringBuilder();
		for (String seat : selectedSeats) {
			sb.append(seat).append(",\n");
		}
		if (sb.length() > 0) {
			sb.setLength(sb.length() - 2);
		}
		sign_in.t_seat.lblNewLabel_4.setText(sb.toString());

		su2 = selectedSeats.size();

	}

}