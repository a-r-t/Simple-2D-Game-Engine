import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Rectangle {

	private int speed;
	private Direction direction;
	
	public Bullet(float xLocation, float yLocation, Direction direction) {
		super(xLocation, yLocation, 10, 10);
		speed = 3;
		this.direction = direction;
		color = Color.black;
	}
	
	@Override
	public void update(Keyboard keyboard) {		
		if (direction == Direction.LEFT) {
			moveLeft(speed);
		} else if (direction == Direction.RIGHT) {
			moveRight(speed);
		}
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);
	}
	
}
