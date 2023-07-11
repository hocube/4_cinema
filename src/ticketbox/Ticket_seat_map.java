package ticketbox;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import c_loginout.Sign_in;
//이걸로 합치기!
//좌석선택창에 필요한 좌석 배치도 배열을위해 하나의 Jpanel 클래스를 만듦.
//ticket_seat에 가서 객체생성후 화면에불러오자.
public class Ticket_seat_map extends JPanel {
	Sign_in sign_in;
	private ArrayList<String> selectedSeats = new ArrayList<>();
	JCheckBox seatCheckBox,sourceCheckBoxm;
	String seatName;

	public Ticket_seat_map(Sign_in signin) {

		
		this.sign_in = signin;
		
		setLayout(new GridLayout(5, 5)); // 5x5 그리드 레이아웃 설정

		char rowLabel = 'A';
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				String seatName = String.valueOf(rowLabel) + (col + 1);

				seatCheckBox = new JCheckBox(seatName);
				seatCheckBox.addActionListener(new SeatCheckBoxListener()); // 리스너 추가
				add(seatCheckBox);
			}
			rowLabel++;
		}
		
	

	}

	
	public void resetSelectedSeats() {
		selectedSeats.clear();
		updateSelectedSeatsLabel();
	}
	public void resetCheckBoxes() {
	    Component[] components = getComponents();
	    for (Component component : components) {
	        if (component instanceof JCheckBox) {
	            JCheckBox checkBox = (JCheckBox) component;
	            checkBox.setSelected(false);
	        }
	    }
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
	        sb.append(seat).append(", ");
	    }
	    if (sb.length() > 0) {
	        sb.setLength(sb.length() - 2);
	    }
	    sign_in.t_seat.lblNewLabel_4.setText(sb.toString());
	}
}