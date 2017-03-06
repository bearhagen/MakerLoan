package gui;

import gui.branding.Colors;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.UIManager;

public class Button extends JButton {
	public Button(String string) {
		setText(string);
		setForeground(Colors.WHITE);
		setFont(new Font("Segoe UI Bold", Font.PLAIN, 15));
		setForeground(Colors.WHITE);
		setBackground(Colors.PRIMARY);
		setBorder(BorderFactory.createEmptyBorder());
		setFocusPainted(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		addMouseListener(new java.awt.event.MouseAdapter() {
			Color originalFgColor;
			Color originalBgColor;

		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	originalFgColor = getForeground();
		    	originalBgColor = getBackground();
		    	setForeground(Colors.WHITE);
		    	setBackground(Colors.SECONDARY);
		    	UIManager.put("Button.select", Colors.SECONDARY);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	setForeground(originalFgColor);
		    	setBackground(originalBgColor);
		    }
		});
	}
}
