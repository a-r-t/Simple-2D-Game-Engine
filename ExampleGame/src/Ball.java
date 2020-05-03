import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Ball extends Sprite {
	private float xDirection;
	private float yDirection;
	private float speed;
	private Bounds sceneBounds;
	private Paddle paddleLeft;
	private Paddle paddleRight;
	
	public Ball(float xLocation, float yLocation, float width, float height, Bounds sceneBounds, Paddle paddleLeft, Paddle paddleRight) {
		super(xLocation, yLocation, width, height, "jiggly.png", Color.magenta);
		this.sceneBounds = sceneBounds;
		xDirection = 1;
		yDirection = 1;
		speed = 3;
		this.paddleLeft = paddleLeft;
		this.paddleRight = paddleRight;
	}
	
	public float getXDirection() {
		return xDirection;
	}
	
	public void setXDirection(float xDirection) {
		this.xDirection = xDirection;
	}
	
	public float getYDirection() {
		return yDirection;
	}
	
	public void setYDirection(float yDirection) {
		this.yDirection = yDirection;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	@Override
	public void update(Keyboard keyboard) {	
		moveX(speed * xDirection);
		if (intersects(paddleLeft) || intersects(paddleRight)) {
			xDirection *= -1;
			moveX(speed * xDirection);
		}
		
		moveY(speed * yDirection);
		if (intersects(paddleLeft) || intersects(paddleRight)) {
			yDirection *= -1;
			moveY(speed * xDirection);
		}
		
		if (getY() + height > sceneBounds.getY2()) {
			yDirection = -1;
		} else if (getY() < sceneBounds.getY1()) {
			yDirection = 1;
		}
	}
	
	public boolean isTouchingLeftGoal() {
		return getX() <= sceneBounds.getX1();
	}
	
	public boolean isTouchingRightGoal() {
		return getX() + width >= sceneBounds.getX2();
	}
	

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
	}
}
