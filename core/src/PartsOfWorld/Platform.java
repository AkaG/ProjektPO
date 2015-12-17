package PartsOfWorld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Platform extends Rectangle {
	
	private Texture texture;
	
	public Platform()
	{
		texture = new Texture("platform.png");
		width = 100;
		height = 20;
		x = 250;
		y = 150;
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
