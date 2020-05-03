import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Paddle extends Rectangle {
	private float movementSpeed;
	private Bounds sceneBounds;
	
	public Paddle(int xLocation, int yLocation, int width, int height, Bounds sceneBounds) {
		super(xLocation, yLocation, width, height);
		movementSpeed = 3;
		this.sceneBounds = sceneBounds;
	}

	@Override
	public void update(Keyboard keyboard) {		
		if (keyboard.isKeyDown(Key.UP)) {
			moveUp(movementSpeed);
		} else if (keyboard.isKeyDown(Key.DOWN)) {
			moveDown(movementSpeed);
		}
		
		if (getY() < sceneBounds.getY1() + 20) {
			setY(sceneBounds.getY1() + 20);
		} else if (getY() + height > sceneBounds.getY2() - 20) {
			setY(sceneBounds.getY2() - 20 - height);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
	}

}
