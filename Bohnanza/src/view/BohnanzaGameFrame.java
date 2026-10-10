package view;

import java.awt.CardLayout;

import javax.swing.*;

// this class is the general game frame
public class BohnanzaGameFrame extends JFrame {

	private MainMenuPanel mainMenu = new MainMenuPanel();
	private GameFieldPanel gameField = new GameFieldPanel(); // actual game

	private JPanel cardLayoutPanel = new JPanel();

	private CardLayout cardLayout = new CardLayout(0, 0);

	// constructor method
	public BohnanzaGameFrame() {
		setSize(1920, 1080);
		setResizable(false);
		setTitle("Bean Game");
		setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// setting up a container panel for a card layout (easier to switch between main
		// menu and game field)
		cardLayoutPanel.setBounds(0, 0, 1920, 1080);
		cardLayoutPanel.setLayout(cardLayout);
		cardLayoutPanel.add(mainMenu, "0");
		cardLayoutPanel.add(gameField, "1");
		cardLayout.first(cardLayoutPanel);
		cardLayoutPanel.setVisible(true);

		add(cardLayoutPanel);

		setVisible(true);
	}

	// accessors and mutators

	public GameFieldPanel getGameField() {
		return gameField;
	}

	public MainMenuPanel getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(MainMenuPanel mainMenu) {
		this.mainMenu = mainMenu;
	}

	public void setGameField(GameFieldPanel gameField) {
		this.gameField = gameField;
	}

	public JPanel getCardLayoutPanel() {
		return cardLayoutPanel;
	}

	public void setCardLayoutPanel(JPanel cardLayoutPanel) {
		this.cardLayoutPanel = cardLayoutPanel;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

}
