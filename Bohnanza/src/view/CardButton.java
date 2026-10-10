package view;

import java.awt.Dimension;

import javax.swing.*;

// this class represents the visual model of a bean card
public class CardButton extends JButton{
	private JLabel cardIcon;
	private String name;

	// constructor method
	public CardButton(String name) {
		this.name = name;
		setPreferredSize(new Dimension(100, 152));
		setLayout(null);
		cardIcon = new JLabel(new ImageIcon("images/" + name + ".png")); // read in the card image
		cardIcon.setBounds(0, 0, 100, 152);

		setOpaque(true);

		add(cardIcon);
		setVisible(true);

	}

	// accessor and mutator
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JLabel getCardIcon() {
		return cardIcon;
	}

	public void setCardIcon(JLabel cardIcon) {
		this.cardIcon = cardIcon;
	}

}
