import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import acm.graphics.*;
import acm.program.*;
import acm.util.RandomGenerator;

//นายเอ็มอิร์สัด บาฮีอาร์กัน 6210742281
//นายณัฐดนัย หล่อนิมิตดี 6210742224
public class CarrotGame extends GraphicsProgram {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// A new type of variable that can make random numbers
	private static RandomGenerator rg = new RandomGenerator();

	// object in game
	// size of carrot plot 40
	private static final int CARROT_PLOT = 40;
	// number of carrot 5 piece
	private static final int NUM_CARROT = 5;

	// set up height & width
	private static final int HEIGHT = 800;
	private static final int WIDTH = 600;

	// set up height & width for plot table
	private final int X_PLOT = WIDTH / CARROT_PLOT;
	private final int Y_PLOT = HEIGHT / CARROT_PLOT;

	private int countCarrot = 0;

	// add object to game
	private GRect carrot1, carrot2, carrot3, carrot4, carrot5 = null;

	// add object to game
	private GImage noticeCarrot1, noticeCarrot2, noticeCarrot3, noticeCarrot4, noticeCarrot5;
	private GImage findACarrotLabel, button_solution, tryAgainLabel;

	public void run() {
		// set up the width & height
		setWidth(WIDTH);
		setHeight(HEIGHT + CARROT_PLOT * 3);

		// for listen that user click the plot carrot
		addKeyListeners();

		// call plot carrot table for find a carrot
		plotCarrotTable();

		// add item to game
		itemGame();
		while (countCarrot != NUM_CARROT) {
			pause(0);
		}

	}

	// item game
	public void itemGame() {
		addCarrots();
		makeButtonSolution();
		makefindACarrotLabel();
		makeNoticeCarrot1();
		makeNoticeCarrot2();
		makeNoticeCarrot3();
		makeNoticeCarrot4();
		makeNoticeCarrot5();
	}

	// check if user click plot table for find carrot
	public void mouseClicked(MouseEvent e) {
		GObject obj = getElementAt(e.getX(), e.getY());
		// if user click plot table then found first carrot
		if (obj == carrot1) {
			carrot1.setFillColor(Color.ORANGE);
			remove(noticeCarrot1);
		}
		// if user click plot table then found second carrot
		if (obj == carrot2) {
			carrot2.setFillColor(Color.ORANGE);
			remove(noticeCarrot2);
		}
		// if user click plot table then found third carrot
		if (obj == carrot3) {
			carrot3.setFillColor(Color.ORANGE);
			remove(noticeCarrot3);
		}
		// if user click plot table then found fourth carrot
		if (obj == carrot4) {
			carrot4.setFillColor(Color.ORANGE);
			remove(noticeCarrot4);
		}
		// if user click plot table then found fifth carrot
		if (obj == carrot5) {
			carrot5.setFillColor(Color.ORANGE);
			remove(noticeCarrot5);
		}

		// if user click solution button
		if (obj == button_solution) {
			carrot1.setFillColor(Color.ORANGE);
			carrot2.setFillColor(Color.ORANGE);
			carrot3.setFillColor(Color.ORANGE);
			carrot4.setFillColor(Color.ORANGE);
			carrot5.setFillColor(Color.ORANGE);
			remove(noticeCarrot1);
			remove(noticeCarrot2);
			remove(noticeCarrot3);
			remove(noticeCarrot4);
			remove(noticeCarrot5);
			button_solution = null;
			makeTryAgainLabel();
			countCarrot = 5;
		}
	}

	// Create a plot table
	private void plotCarrotTable() {
		for (int i = 0; i < X_PLOT * Y_PLOT; i++) {
			int x = (i % X_PLOT) * CARROT_PLOT;
			int y = (i / X_PLOT) * CARROT_PLOT;
			GRect plotTable = new GRect(CARROT_PLOT, CARROT_PLOT);
			plotTable.setFilled(true);
			plotTable.setFillColor(Color.GREEN);
			add(plotTable, x, y);
		}
	}

	// method for make a carrot 1 piece
	private GRect makeACarrot() {
		//rg.setSeed(0);
		int x = rg.nextInt(0, X_PLOT - 1) * CARROT_PLOT;
		int y = rg.nextInt(0, Y_PLOT - 1) * CARROT_PLOT;
		GRect carrot = new GRect(CARROT_PLOT, CARROT_PLOT);
		carrot.setFilled(true);
		carrot.setFillColor(Color.GREEN);
		add(carrot, x, y);
		return carrot;
	}

	// method for make carrot 5 piece
	private void addCarrots() {
		// make first carrot
		carrot1 = makeACarrot();
		int locationX1 = (int) carrot1.getX();
		int locationY1 = (int) carrot1.getY();

		// make second carrot
		carrot2 = makeACarrot();
		int locationX2 = (int) carrot2.getX();
		int locationY2 = (int) carrot2.getY();

		// check the second carrot and first carrot aren't the same location
		while (locationX1 == locationX2 && locationY1 == locationY2) {
			remove(carrot2);
			carrot2 = makeACarrot();
			locationX2 = (int) carrot2.getX();
			locationY2 = (int) carrot2.getY();
		}

		// make third carrot
		carrot3 = makeACarrot();
		int locationX3 = (int) carrot3.getX();
		int locationY3 = (int) carrot3.getY();

		// check the third carrot, the second carrot and first carrot aren't the same
		// location
		while (locationX3 == locationX2 && locationY3 == locationY2 && locationX3 == locationX1
				&& locationY3 == locationY1) {
			remove(carrot3);
			carrot3 = makeACarrot();
			locationX3 = (int) carrot3.getX();
			locationY3 = (int) carrot3.getY();
		}

		// make fourth carrot
		carrot4 = makeACarrot();
		int locationX4 = (int) carrot4.getX();
		int locationY4 = (int) carrot4.getY();

		// check the fourth carrot, the third carrot, the second carrot and first carrot
		// aren't the same location
		while (locationX4 == locationX3 && locationY4 == locationY3 && locationX4 == locationX2
				&& locationY4 == locationY2 && locationX4 == locationX1 && locationY4 == locationY1) {
			remove(carrot4);
			carrot4 = makeACarrot();
			locationX4 = (int) carrot4.getX();
			locationY4 = (int) carrot4.getY();
		}

		// make fifth carrot
		carrot5 = makeACarrot();
		int locationX5 = (int) carrot5.getX();
		int locationY5 = (int) carrot5.getY();

		// check the fifth carrot, the fourth carrot, the third carrot, the second
		// carrot and first carrot aren't the same location
		while (locationX5 == locationX4 && locationY5 == locationY4 && locationX5 == locationX3
				&& locationY5 == locationY3 && locationX5 == locationX2 && locationY5 == locationY2
				&& locationX5 == locationX1 && locationY5 == locationY1) {
			remove(carrot5);
			carrot5 = makeACarrot();
			locationX5 = (int) carrot5.getX();
			locationY5 = (int) carrot5.getY();
		}
	}

	// make a button solution for give up
	private GImage makeButtonSolution() {
		int x = (getWidth() / 2) - (CARROT_PLOT * 2);
		int y = getHeight() - (CARROT_PLOT * 2);
		button_solution = new GImage("solution1.png");
		add(button_solution, x, y);
		return button_solution;
	}

	// Create an icon carrot for notice that user must the carrot on plot
	private GImage makeNoticeCarrot1() {
		int x = CARROT_PLOT / 4;
		int y = getHeight() - (CARROT_PLOT * 2);
		noticeCarrot1 = new GImage("carrot.png");
		add(noticeCarrot1, x, y);
		return noticeCarrot1;
	}

	// Create an icon carrot for notice that user must the carrot on plot
	private GImage makeNoticeCarrot2() {
		int x = CARROT_PLOT / 2 + 10;
		int y = getHeight() - (CARROT_PLOT * 2);
		noticeCarrot2 = new GImage("carrot.png");
		add(noticeCarrot2, x, y);
		return noticeCarrot2;
	}

	// Create an icon carrot for notice that user must the carrot on plot
	private GImage makeNoticeCarrot3() {
		int x = CARROT_PLOT / 2 + 30;
		int y = getHeight() - (CARROT_PLOT * 2);
		noticeCarrot3 = new GImage("carrot.png");
		add(noticeCarrot3, x, y);
		return noticeCarrot3;
	}

	// Create an icon carrot for notice that user must the carrot on plot
	private GImage makeNoticeCarrot4() {
		int x = CARROT_PLOT / 2 + 50;
		int y = getHeight() - (CARROT_PLOT * 2);
		noticeCarrot4 = new GImage("carrot.png");
		add(noticeCarrot4, x, y);
		return noticeCarrot4;
	}

	// Create an icon carrot for notice that user must the carrot on plot
	private GImage makeNoticeCarrot5() {
		int x = CARROT_PLOT / 2 + 70;
		int y = getHeight() - (CARROT_PLOT * 2);
		noticeCarrot5 = new GImage("carrot.png");
		add(noticeCarrot5, x, y);
		return noticeCarrot5;
	}

	// create a label for notice user to find 5 carrots
	private GImage makefindACarrotLabel() {
		int x = (getWidth() / 2) + CARROT_PLOT * 4;
		int y = getHeight() - (CARROT_PLOT * 2);
		findACarrotLabel = new GImage("find a carrot1.png");
		add(findACarrotLabel, x, y);
		return findACarrotLabel;
	}

	// create a label for notice user to try again
	private GImage makeTryAgainLabel() {
		int x = (getWidth() / 2) - (CARROT_PLOT * 2);
		int y = getHeight() - (CARROT_PLOT * 2);
		tryAgainLabel = new GImage("try again1.png");
		add(tryAgainLabel, x, y);
		return tryAgainLabel;
	}

}
