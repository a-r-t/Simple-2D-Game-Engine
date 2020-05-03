import java.awt.Graphics2D;

public class MovingRectangle extends Rectangle {

	public MovingRectangle(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	@Override
	public void update(Keyboard keyboard) {
		if (keyboard.isKeyDown(Key.RIGHT)) {
			moveRight(2);
		} else if (keyboard.isKeyDown(Key.LEFT)) {
			moveLeft(2);
		}
		
		if (keyboard.isKeyDown(Key.UP)) {
			moveUp(2);
		} else if (keyboard.isKeyDown(Key.DOWN)) {
			moveDown(2);
		}
		
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);
	}

}
