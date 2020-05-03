import java.awt.Color;
import java.awt.Graphics2D;

public class Pokemon extends Sprite {
	public Pokemon(float x, float y, float width, float height, String imageFile, Color transparentColor) {
		super(x, y, width, height, imageFile, transparentColor);
	}

	@Override
	public void update(Keyboard keyboard) {
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);

	}
}