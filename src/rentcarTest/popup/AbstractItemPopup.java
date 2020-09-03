package rentcarTest.popup;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public abstract class AbstractItemPopup<T> extends JDialog {

	public AbstractItemPopup() {
	}

	public abstract void setItem(T item);

	abstract boolean isValidTf();

	boolean isTfEmpty() {
		for(Component c : getComponents()) {
			if(c instanceof JTextField) {
				if(((JTextField) c).getText().isEmpty()){
					return true;
				}
			}
		}
		return false;
	}

	public void clearTf() {
		for(Component c : getComponents()) {
			if(c instanceof JTextField) {
				((JTextField) c).setText("");
			}
		}
	}
	
	public void setTfEditable(JPanel panel, boolean isEditable) {
		for(Component c : panel.getComponents()) {
			if(c instanceof JTextField) {
				((JTextField)c).setEditable(isEditable);
			}
			if(c instanceof JCheckBox) {
				((JCheckBox)c).setEnabled(isEditable);
			}
		}
	}
	
}
