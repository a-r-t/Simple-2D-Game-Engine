import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite extends Rectangle {
	protected BufferedImage image;
	
	public Sprite(float x, float y, float width, float height, String imageFile) {
		super(x, y, width, height);
		this.setImage(imageFile);
	}
	
	public Sprite(float x, float y, float width, float height, String imageFile, Color transparentColor) {
		super(x, y, width, height);
		this.setImage(imageFile, transparentColor);
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(String fileName) {
		try {
			BufferedImage initialImage = ImageIO.read(new File("Images/" + fileName));
		    image = initialImage;		
		} catch (IOException e) {
			System.out.println("Unable to find file " + fileName);
		}
	}
	
	public void setImage(String fileName, Color transparentColor) {
		try {
			BufferedImage initialImage = ImageIO.read(new File("Images/" + fileName));
		    Image transparentImage = ImageUtils.transformColorToTransparency(initialImage, transparentColor, new Color(255, 255, 255));
		    BufferedImage transparentImageConvert = ImageUtils.convertImageToBufferedImage(transparentImage, transparentImage.getWidth(null), transparentImage.getHeight(null));	
			image = transparentImageConvert;
		} catch (IOException e) {
			System.out.println("Unable to find file " + fileName);
		}
	}
	
	@Override
	public void update(Keyboard keyboard) {
		super.update(keyboard);
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(image, Math.round(getX()), Math.round(getY()), Math.round(width), Math.round(height), null);
	}

}
