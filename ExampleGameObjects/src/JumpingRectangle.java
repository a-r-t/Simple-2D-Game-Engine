import java.awt.Graphics2D;

public class JumpingRectangle extends Rectangle {

	private final float gravity = .5f;
	private float jumpForce = 0;
	private boolean isJumping = false;
	private Bounds sceneBounds;
	
	public JumpingRectangle(float x, float y, float width, float height, Bounds sceneBounds) {
		super(x, y, width, height);
		this.sceneBounds = sceneBounds;
	}

	@Override
	public void update(Keyboard keyboard) {
		if (keyboard.isKeyDown(Key.W) && !isJumping) {
			isJumping = true;
			jumpForce = 20;
		}

		
		if (isJumping) {
			y -= jumpForce;
			jumpForce -= 2;
		}
		
		if (y + height >= sceneBounds.getY2() && isJumping) {
			isJumping = false;
		}
		
		y += gravity;
		if (y + height > sceneBounds.getY2()) {
			y = sceneBounds.getY2() - height;
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
	}
}
