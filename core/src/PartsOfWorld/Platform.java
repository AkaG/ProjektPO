package PartsOfWorld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Platform extends Rectangle {
	
	private Texture texture;
	
	public Platform(float _x, float _y, float _width, float _height)
	{
		texture = new Texture("platform.png");
		this.width = _width;
		height = _height;
		x = _x;
		y = _y;
	}
	
	
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	//float width = 100;
	//float height = 50;
	

}
