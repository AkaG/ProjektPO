package game.api;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Platform extends Rectangle {
	
	Texture texture;
	//float width = 100;
	//float height = 50;
	Platform()
	{
		texture = new Texture("platform.png");
		width = 100;
		height = 20;
		x = 250;
		y = 150;
	}
	

}
