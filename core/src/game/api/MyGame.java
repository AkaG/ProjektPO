package game.api;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends Game {
	
	@Override
	public void create() {
		
		setScreen(new MenuScreen(this));
	}
	
}
