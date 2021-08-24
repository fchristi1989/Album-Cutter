import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JActionListener implements ActionListener {
	AlbumCutter frame;
	JButton btn;

	public JActionListener(AlbumCutter ytFullAlbumCutter, JButton btn) {
		frame = ytFullAlbumCutter;
		this.btn = btn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.actionPerformed(btn);
	}

}
