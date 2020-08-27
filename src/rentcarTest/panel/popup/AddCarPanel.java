package rentcarTest.panel.popup;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;

public class AddCarPanel extends JDialog {

	
	private AddCarPanel2 panel;
	
	public AddCarPanel() {
		initComponents();
	}
	private void initComponents() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("차량 추가\r\n");
		
		panel = new AddCarPanel2();
		setBounds(100, 100, 450, 370);
		getContentPane().add(panel, BorderLayout.NORTH);
	}

	
}
