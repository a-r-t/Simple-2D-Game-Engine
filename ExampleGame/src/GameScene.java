import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;

public class GameScene {
	private Bounds sceneBounds;
	private Paddle paddleLeft;
	private Paddle paddleRight;
	private Ball ball;
	private Felix felix;
	private int leftScore;
	private int rightScore;
	private SpriteFont scoreDisplay;
	private SpriteFont targetHits;
	private GameState gameState;
	private SpriteFont startGameText;
	private SpriteFont gameOverText;
	private long beforeTime = System.currentTimeMillis();

	public void initialize(Bounds sceneBounds) {
		this.sceneBounds = sceneBounds;

		leftScore = 0;
		rightScore = 0;

		paddleLeft = new Paddle(20, 220, 30, 90, sceneBounds);
		paddleLeft.setBorderColor(Color.black);
		paddleLeft.setBorderThickness(2);

		paddleRight = new Paddle(740, 220, 30, 90, sceneBounds);
		paddleRight.setBorderColor(Color.black);
		paddleRight.setBorderThickness(2);

		ball = new Ball(380, 250, 50, 50, sceneBounds, paddleLeft, paddleRight);

		scoreDisplay = new SpriteFont(String.format("%s - %s", leftScore, rightScore), 390, 20, "Arial", 15, Color.white);
		scoreDisplay.setFontStyle(Font.BOLD);

		SpriteSheet felixSpriteSheet = new SpriteSheet("felix.png", 32, 32, Color.magenta);
		felix = new Felix(50, 50, 32, 32, felixSpriteSheet, sceneBounds, ball);

		targetHits = new SpriteFont(String.format("Target Hits: %s", felix.getTargetHits()), 360, 40, "Arial", 15, Color.white);
		targetHits.setFontStyle(Font.BOLD);

		gameOverText = new SpriteFont("", 130, 280, "Times New Roman", 25, Color.white);
		gameOverText.setOutlineColor(Color.black);
		gameOverText.setOutlineThickness(5.0f);

		startGameText = new SpriteFont("Press Space to start the game!", 245, 280, "Times New Roman", 25, Color.white);
		startGameText.setOutlineColor(Color.black);
		startGameText.setOutlineThickness(5.0f);

		gameState = GameState.START;
	}

	public void update(Keyboard keyboard) {
		if (gameState == GameState.START) {
			if (keyboard.isKeyDown(Key.SPACE)) {
				gameState = GameState.PLAYING;
			}
		} else if (gameState == GameState.PLAYING) {
			paddleLeft.update(keyboard);
			paddleRight.update(keyboard);
			ball.update(keyboard);
			
			long currentMillis = System.currentTimeMillis() - beforeTime;
			if (currentMillis > 5000) {
				ball.setSpeed(ball.getSpeed() + .5f);
				beforeTime = System.currentTimeMillis();
			}
			
			boolean scoreUpdate = false;
			if (ball.isTouchingLeftGoal()) {
				rightScore++;
				scoreUpdate = true;

			} else if (ball.isTouchingRightGoal()) {
				leftScore++;
				scoreUpdate = true;
			}

			if (scoreUpdate) {
				ball.setLocation(380, 250);
				int random1 = new Random().nextInt(2);
				int random2 = new Random().nextInt(2);
				int xDirection = random1 == 0 ? 1 : -1;
				int yDirection = random2 == 0 ? 1 : -1;
				ball.setXDirection(ball.getXDirection() * xDirection);
				ball.setYDirection(ball.getYDirection() * yDirection);
				ball.setSpeed(3f);
				scoreDisplay.setText(String.format("%s - %s", leftScore, rightScore));
			}

			targetHits.setText(String.format("Target Hits: %s", felix.getTargetHits()));

			felix.update(keyboard);

			if (leftScore >= 5 || rightScore >= 5 || ball.intersects(felix)) {
				gameState = GameState.END;
				gameOverText.setText(String.format("Game Over! You scored %s. Press Space to play again.", felix.getTargetHits()));
			}
		} else if (gameState == GameState.END) {
			if (keyboard.isKeyDown(Key.SPACE)) {
				initialize(sceneBounds);
			}
		}
	}

	public void draw(Graphics2D g) {
		scoreDisplay.draw(g);
		targetHits.draw(g);
		paddleLeft.draw(g);
		paddleRight.draw(g);
		ball.draw(g);
		felix.draw(g);
		if (gameState == GameState.START) {
			startGameText.draw(g);
		} else if (gameState == GameState.END) {
			gameOverText.draw(g);
		}
	}

	private enum GameState {
		START, PLAYING, END
	}
}
