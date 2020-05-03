import java.awt.Color;
import java.awt.Graphics2D;

public class GameScene {
	private Bounds sceneBounds;
	private MovingRectangle movingRectangle;
	private Pokemon bulbasaur;
	private Pokemon psyduck;
	private DonkeyKong donkeyKong;
	private SpriteFont helloText;
	private JumpingRectangle jumpingRectangle;

	public void initialize(Bounds sceneBounds) {
		this.sceneBounds = sceneBounds;
		movingRectangle = new MovingRectangle(90, 10, 20, 40);
		movingRectangle.setColor(Color.green);
		bulbasaur = new Pokemon(10, 10, 35, 33, "Bulbasaur.png", new Color(255, 0, 255));
		psyduck = new Pokemon(200, 40, 34, 45, "Psyduck.png", new Color(255, 0, 255));
		donkeyKong = new DonkeyKong(90, 90, 100, 100);
		helloText = new SpriteFont("Hello!", 20, 100, "Comic Sans", 20, Color.red);
		jumpingRectangle = new JumpingRectangle(80, 5600, 20, 20, sceneBounds);
		jumpingRectangle.setColor(Color.red);
	}

	public void update(Keyboard keyboard) {
		movingRectangle.update(keyboard);
		bulbasaur.update(keyboard);
		psyduck.update(keyboard);
		donkeyKong.update(keyboard);
		helloText.update(keyboard);
		jumpingRectangle.update(keyboard);
	}

	public void draw(Graphics2D g) {
		movingRectangle.draw(g);
		bulbasaur.draw(g);
		psyduck.draw(g);
		donkeyKong.draw(g);
		helloText.draw(g);
		jumpingRectangle.draw(g);
	}
}
