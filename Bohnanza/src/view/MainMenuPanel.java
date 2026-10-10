package view;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;

import javax.swing.*;

// Main Menu Window
// Coded by Brandon Lam
public class MainMenuPanel extends JPanel implements ActionListener {
	// Buttons
	private String[] difficulty = { "PvE Selection", "Easy", "Medium", "Hard" };
	private JButton PvPButton = new JButton("Player VS Player");
	private JButton quitButton = new JButton("Quit");
	private JButton tutorialButton = new JButton("How to Play");
	private JComboBox<String> PvESelection = new JComboBox<>(difficulty);

	// Layering components
	private final JLayeredPane layeredPane = new JLayeredPane();
	private final JLabel backgroundP = new JLabel();

	// Image holders (now with individual position control)
	private final JLabel[] imageHolders = new JLabel[5];
	private final String[] cardImages = { "images/Blue.png", "images/Chili.png", "images/Green.png", "images/Soy.png",
			"images/bohnanza.png" };

	// Individual position settings for each image (x, y)
	private final Point[] imagePositions = { new Point(200, 200), // Blue
			new Point(200, 600), // Chili
			new Point(1400, 200), // Green
			new Point(1400, 600), // Soy (second row)
			new Point(700, 50) // Bohnanza (second row)
	};
	
	// Access links
	private String tutorialURL = "https://www.youtube.com/watch?v=aLSzFhIKuK4";

	// Scaling control
	private final int scaleMultiplier = 2;

	// constructor method
	public MainMenuPanel() {
		// Window setup
		configureWindow();
		setupBackground();
		setupImageHolders();
		setupInteractiveComponents();
		this.setVisible(true);
	}

	private void openTutorial(String url) {
		// https://stackoverflow.com/questions/5226212/how-to-open-url-in-default-webbrowser-using-java
		if(Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(new URI(url));
			} catch (IOException | URISyntaxException exception) {
				exception.printStackTrace();
			}
		} else {
			Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec("xdg-open " + url);
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		
	}

	private void configureWindow() {
		setSize(1920, 1080);
		setLayout(new BorderLayout());
	}

	private void setupBackground() {
		ImageIcon backgroundImage = new ImageIcon("images/background.png");
		backgroundP
				.setIcon(new ImageIcon(backgroundImage.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH)));
		backgroundP.setBounds(0, 0, 1920, 1080);
		layeredPane.add(backgroundP, JLayeredPane.DEFAULT_LAYER);
	}

	private void setupImageHolders() {
		for (int i = 0; i < imageHolders.length; i++) {
			ImageIcon originalIcon = new ImageIcon(cardImages[i]);
			Image scaledImage = originalIcon.getImage().getScaledInstance(originalIcon.getIconWidth() * scaleMultiplier,
					originalIcon.getIconHeight() * scaleMultiplier, Image.SCALE_SMOOTH);

			imageHolders[i] = new JLabel(new ImageIcon(scaledImage));

			// Set position from imagePositions array
			imageHolders[i].setBounds(imagePositions[i].x, imagePositions[i].y,
					originalIcon.getIconWidth() * scaleMultiplier, originalIcon.getIconHeight() * scaleMultiplier);

			layeredPane.add(imageHolders[i], JLayeredPane.PALETTE_LAYER);
		}
	}

	private void setupInteractiveComponents() {
		// Button and combo box setup
		PvPButton.setBounds(700, 650, 400, 60);
		PvPButton.addActionListener(this);

		PvESelection.setBounds(700, 735, 400, 60);
		PvESelection.addActionListener(this);

		tutorialButton.setBounds(700, 820, 400, 60);
		tutorialButton.addActionListener(this);
		
		quitButton.setBounds(700, 905, 400, 60);
		quitButton.addActionListener(this);
		

		// Add to layered pane
		layeredPane.add(PvPButton, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(PvESelection, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(quitButton, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(tutorialButton, JLayeredPane.PALETTE_LAYER);

		this.add(layeredPane, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == PvESelection) {
			System.out.println("Difficulty: " + PvESelection.getSelectedItem());
		}
		if (e.getSource() == PvPButton) {
			System.out.println("played");
		}
		if (e.getSource() == quitButton) {
			System.out.println("quited");
			System.exit(0);
		}
		if(e.getSource() == tutorialButton) {
			openTutorial(tutorialURL);
		}
	}

	// Helper methods to adjust image positions programmatically
	public void setImagePosition(int index, int x, int y) {
		if (index >= 0 && index < imagePositions.length) {
			imagePositions[index].setLocation(x, y);
			imageHolders[index].setLocation(x, y);
			repaint();
		}
	}

	public Point getImagePosition(int index) {
		return (index >= 0 && index < imagePositions.length) ? new Point(imagePositions[index]) : null;
	}

	// accessors and mutators
	public JButton getPvPButton() {
		return PvPButton;
	}

	public void setPvPButton(JButton playerButton) {
		this.PvPButton = playerButton;
	}

	public String[] getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String[] difficulty) {
		this.difficulty = difficulty;
	}

	public JComboBox<String> getPvESelection() {
		return PvESelection;
	}

	public void setPvESelection(JComboBox<String> PvESelection) {
		this.PvESelection = PvESelection;
	}

	public JButton getQuitButton() {
		return quitButton;
	}

	public void setQuitButton(JButton quitButton) {
		this.quitButton = quitButton;
	}
}