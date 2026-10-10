package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.*;

// this class represents a player's field
public class PlayerFieldPanel extends JPanel {
	// three fields
	private JButton[] fields = { new JButton("1"), new JButton("2"), new JButton("3") };
	private JLabel[] fieldQuantities = { new JLabel("0", SwingConstants.CENTER), new JLabel("0", SwingConstants.CENTER),
			new JLabel("0", SwingConstants.CENTER) };
	private JLabel[] fieldsBackground = { new JLabel(), new JLabel(), new JLabel() };

	// coin pile placeholder
	private JLabel coinPile = new JLabel("Coins", SwingConstants.CENTER);
	private JLabel coinQuantity = new JLabel("0", SwingConstants.CENTER);

	// Player hand
	private Queue<CardButton> playerHand = new LinkedList<>();
	private JPanel playerHandPanel = new JPanel();
	private JScrollPane playerHandPane = new JScrollPane(playerHandPanel);

	private JButton plantButton = new JButton("Plant Top");

	// Player coins
	ArrayList<CardButton> playerCoins = new ArrayList<CardButton>();

	// Player Fields
	ArrayList<CardButton>[] playerField = new ArrayList[3];

	// font
	private Font font = new Font("Times New Roman", Font.BOLD, 30);

	// constructor method
	public PlayerFieldPanel() {
		setSize(1920, 360);
		setLayout(null);
		setOpaque(false);

		for (JButton field : fields) {
			field.setBackground(Color.ORANGE);
			field.setForeground(Color.YELLOW);
			field.setFocusPainted(false);
			field.setOpaque(true);
			field.setBorder(null);
			field.setFont(font);
		}

		for (JLabel fieldQuantity : fieldQuantities) {
			fieldQuantity.setBackground(Color.CYAN);
			fieldQuantity.setForeground(Color.BLACK);
			fieldQuantity.setOpaque(true);
			fieldQuantity.setFont(font);
		}

		fields[0].setBounds(760, 90, 100, 152);
		fieldQuantities[0].setBounds(760, 242, 100, 50);
		fields[1].setBounds(910, 90, 100, 152);
		fieldQuantities[1].setBounds(910, 242, 100, 50);
		fields[2].setBounds(1060, 90, 100, 152);
		fieldQuantities[2].setBounds(1060, 242, 100, 50);

		fields[0].add(fieldsBackground[0]);
		fields[1].add(fieldsBackground[1]);
		fields[2].add(fieldsBackground[2]);

		add(fields[0]);
		add(fieldQuantities[0]);
		add(fields[1]);
		add(fieldQuantities[1]);
		add(fields[2]);
		add(fieldQuantities[2]);

		coinPile.setBackground(Color.ORANGE);
		coinPile.setForeground(Color.YELLOW);
		coinPile.setFont(font);
		coinPile.setOpaque(true);
		coinPile.setBounds(1210, 90, 100, 152);
		add(coinPile);

		coinQuantity.setBackground(Color.CYAN);
		coinQuantity.setForeground(Color.BLACK);
		coinQuantity.setFont(font);
		coinQuantity.setOpaque(true);
		coinQuantity.setBounds(1210, 242, 100, 50);
		add(coinQuantity);

		playerHandPanel.setLayout(new GridLayout(0, 5));
		playerHandPanel.setPreferredSize(new Dimension(500, 152));
		playerHandPanel.setVisible(true);

		playerHandPane.setPreferredSize(new Dimension(500, 152));
		playerHandPane.setBounds(210, 90, 500, 152);
		add(playerHandPane);
		
		plantButton.setBounds(60, 100, 100, 50);
		plantButton.setFocusPainted(false);
		add(plantButton);

		setVisible(true);
	}

	// setters and getters

	public Queue<CardButton> getPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(Queue<CardButton> playerHand) {
		this.playerHand = playerHand;
	}

	public ArrayList<CardButton> getPlayerCoins() {
		return playerCoins;
	}

	public void setPlayerCoins(ArrayList<CardButton> playerCoins) {
		this.playerCoins = playerCoins;
	}

	public JPanel getPlayerHandPanel() {
		return playerHandPanel;
	}

	public void setPlayerHandPanel(JPanel playerHandPanel) {
		this.playerHandPanel = playerHandPanel;
	}

	public JLabel getCoinPile() {
		return coinPile;
	}

	public void setCoinPile(JLabel coinPile) {
		this.coinPile = coinPile;
	}

	public JScrollPane getPlayerHandPane() {
		return playerHandPane;
	}

	public void setPlayerHandPane(JScrollPane playerHandPane) {
		this.playerHandPane = playerHandPane;
	}

	public JLabel getCoinQuantity() {
		return coinQuantity;
	}

	public void setCoinQuantity(JLabel coinQuantity) {
		this.coinQuantity = coinQuantity;
	}

	public ArrayList<CardButton>[] getPlayerField() {
		return playerField;
	}

	public void setPlayerField(ArrayList<CardButton>[] playerField) {
		this.playerField = playerField;
	}

	public JButton[] getFields() {
		return fields;
	}

	public void setFields(JButton[] fields) {
		this.fields = fields;
	}

	public JLabel[] getFieldQuantities() {
		return fieldQuantities;
	}

	public void setFieldQuantities(JLabel[] fieldQuantities) {
		this.fieldQuantities = fieldQuantities;
	}

	public JLabel[] getFieldsBackground() {
		return fieldsBackground;
	}

	public void setFieldsBackground(JLabel[] fieldsBackground) {
		this.fieldsBackground = fieldsBackground;
	}

	public JButton getPlantButton() {
		return plantButton;
	}

	public void setPlantButton(JButton plantButton) {
		this.plantButton = plantButton;
	}

}
