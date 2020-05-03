import java.awt.Graphics2D;
import java.util.ArrayList;

public class Felix extends AnimatedSprite {

	private Direction facingDirection;
	private float speed;
	private boolean isMoving;
	private boolean isShooting;
	private boolean canShoot;
	private Bounds sceneBounds;
	private ArrayList<Bullet> bullets = new ArrayList<>();
	private Ball target;
	private int targetHits;

	public Felix(float xLocation, float yLocation, float width, float height, SpriteSheet spritesheet, Bounds sceneBounds, Ball target) {
		super(xLocation, yLocation, width, height, spritesheet);
		loadAnimations();
		currentAnimation = "WAIT_RIGHT";
		facingDirection = Direction.RIGHT;
		speed = 2;
		isMoving = false;
		isShooting = false;
		canShoot = true;
		this.sceneBounds = sceneBounds;
		this.target = target;
		targetHits = 0;
		hurtbox = new Bounds(xLocation + 11, yLocation - 1, 12, height - 1);
		image = getCurrentFrame().getFrameImage();
	}

	private void loadAnimations() {
		animations.put("WAIT_LEFT", new Frame[] {
				new Frame(spriteSheet.getSprite(0, 0, true), 0) });

		animations.put("WAIT_RIGHT", new Frame[] {
				new Frame(spriteSheet.getSprite(0, 0, false), 0) });

		animations.put("WALK_LEFT", new Frame[] {
				new Frame(spriteSheet.getSprite(1, 0, true), 100),
				new Frame(spriteSheet.getSprite(1, 1, true), 100),
				new Frame(spriteSheet.getSprite(1, 2, true), 100),
				new Frame(spriteSheet.getSprite(1, 3, true), 100) });

		animations.put("WALK_RIGHT", new Frame[] {
				new Frame(spriteSheet.getSprite(1, 0, false), 100),
				new Frame(spriteSheet.getSprite(1, 1, false), 100),
				new Frame(spriteSheet.getSprite(1, 2, false), 100),
				new Frame(spriteSheet.getSprite(1, 3, false), 100) });

		animations.put("SHOOT_LEFT", new Frame[] {
				new Frame(spriteSheet.getSprite(2, 0, true), 200) });

		animations.put("SHOOT_RIGHT", new Frame[] {
				new Frame(spriteSheet.getSprite(2, 0, false), 200) });
	}

	@Override
	public void update(Keyboard keyboard) {	
		super.update(keyboard);
		
		isMoving = false;

		if (keyboard.isKeyDown(Key.D)) {
			facingDirection = Direction.RIGHT;
			moveRight(speed);
			isMoving = true;
		} else if (keyboard.isKeyDown(Key.A)) {
			facingDirection = Direction.LEFT;
			moveLeft(speed);
			isMoving = true;
		}

		if (keyboard.isKeyDown(Key.W)) {
			moveUp(speed);
			isMoving = true;
		} else if (keyboard.isKeyDown(Key.S)) {
			moveDown(speed);
			isMoving = true;
		}
		
		if (getX() < sceneBounds.getX1()) {
			setX(sceneBounds.getX1());
		} else if (getX() + width > sceneBounds.getX2()) {
			setX(sceneBounds.getX2() - width);
		}

		if (getY() < sceneBounds.getY1()) {
			setY(sceneBounds.getY1());
		} else if (getY() + height > sceneBounds.getY2()) {
			setY(sceneBounds.getY2() - height);
		}

		if (isShooting && hasAnimationFinished) {
			isShooting = false;
		}

		if (keyboard.isKeyDown(Key.SPACE) && !isShooting && canShoot) {
			isShooting = true;
			canShoot = false;
			bullets.add(new Bullet(getX(), getY(), facingDirection));
		}

		if (keyboard.isKeyUp(Key.SPACE)) {
			canShoot = true;
		}

		if (!isShooting) {
			if (!isMoving) {
				if (facingDirection == Direction.RIGHT) {
					currentAnimation = "WAIT_RIGHT";
				} else {
					currentAnimation = "WAIT_LEFT";
				}
			} else {
				if (facingDirection == Direction.RIGHT) {
					currentAnimation = "WALK_RIGHT";
				} else {
					currentAnimation = "WALK_LEFT";
				}
			}
		} else {
			if (facingDirection == Direction.RIGHT) {
				currentAnimation = "SHOOT_RIGHT";
			} else {
				currentAnimation = "SHOOT_LEFT";
			}
		}
		
		for (int i = bullets.size() - 1; i >= 0; i--) {
			bullets.get(i).update(keyboard);
			if (bullets.get(i).getX2() < sceneBounds.getX1() || bullets.get(i).getX1() > sceneBounds.getX2()) {
				bullets.remove(i);
			} else if (bullets.get(i).intersects(target)) {
				targetHits++;
				bullets.remove(i);
			}
		}
	}
	
	public int getTargetHits() {
		return targetHits;
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		for (Bullet bullet: bullets) {
			bullet.draw(g);
		}
	}

}
