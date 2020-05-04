import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;

public class SpriteFont implements GameObject {
	protected String text;
	protected Font font;
	protected float x;
	protected float y;
	protected Color color;
	protected Color outlineColor;
	protected float outlineThickness = 1f;

	public SpriteFont(String text,int x, int y, String fontName, int fontSize, Color color) {
		this.text = text;
		font = new Font(fontName, Font.PLAIN, fontSize);
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setFontName(String fontName) {
		this.font = new Font(fontName, this.font.getStyle(), this.font.getSize());
	}

	public void setFontStyle(int fontStyle) {
		this.font = new Font(font.getFontName(), fontStyle, this.font.getSize());
	}

	public void setFontSize(int size) {
		this.font = new Font(font.getFontName(), this.font.getStyle(), size);
	}

	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}
	
	public void setOutlineThickness(float outlineThickness) {
		this.outlineThickness = outlineThickness;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void moveRight(float dx) {
		x += dx;
	}
	
	public void moveLeft(float dx) {
		x -= dx;
	}
	
	public void moveDown(float dy) {
		y += dy;
	}
	
	public void moveUp(float dy) {
		y -= dy;
	}
	
	@Override
	public void update(Keyboard keyboard) {

	}

	@Override
	public void draw(Graphics2D g) {
		if (outlineColor != null && !outlineColor.equals(color)) {
			drawStringWithOutline(g);
		} else {
			g.setFont(font);
			g.setColor(color);
			g.drawString(text, Math.round(x), Math.round(y));
		}
	}

	// https://stackoverflow.com/a/35222059 and https://stackoverflow.com/a/31831120
	public void drawStringWithOutline(Graphics2D g) {
		// remember original settings
		Color originalColor = g.getColor();
		Stroke originalStroke = g.getStroke();
		RenderingHints originalHints = g.getRenderingHints();
		g.setStroke(new BasicStroke(outlineThickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

		// create a glyph vector from your text
		GlyphVector glyphVector = font.createGlyphVector(g.getFontRenderContext(), text);
		
		// get the shape object
		Shape textShape = glyphVector.getOutline();
		AffineTransform at = new AffineTransform();
		at.setToTranslation(Math.round(x), Math.round(y));
		textShape = at.createTransformedShape(textShape);
		
		// activate anti aliasing for text rendering (if you want it to look nice)
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g.setColor(outlineColor);
		g.draw(textShape); // draw outline

		g.setColor(color);
		g.fill(textShape); // fill the shape

		// reset to original settings after painting
		g.setColor(originalColor);
		g.setStroke(originalStroke);
		g.setRenderingHints(originalHints);
	}
}
