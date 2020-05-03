import java.awt.*;

public class DonkeyKong extends AnimatedSprite {
	public DonkeyKong(float xLocation, float yLocation, float width, float height) {
		super(xLocation, yLocation, width, height, new SpriteSheet("DonkeyKong.png", 46, 32, new Color(255, 0, 255)));
		loadAnimations();
		currentAnimation = "STAND_STILL";
		image = getCurrentFrame().getFrameImage();
	}

	public void loadAnimations() {
		animations.put("BEAT_CHEST", new Frame[] { 
				new Frame(spriteSheet.getSprite(0, 0, false), 200),
				new Frame(spriteSheet.getSprite(0, 1, false), 200) });

		animations.put("STAND_STILL", new Frame[] { new 
				Frame(spriteSheet.getSprite(1, 0, false), 0) });
	}

	@Override
	public void update(Keyboard keyboard) {
		super.update(keyboard);
		if (keyboard.isKeyDown(Key.SPACE)) {
			currentAnimation = "BEAT_CHEST";
		} else if (keyboard.isKeyUp(Key.SPACE)) {
			currentAnimation = "STAND_STILL";
		}
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
	}
}