package game.api;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Menu.MenuScreen;
import game.api.MyGame.GameMode;

public class MyGame extends Game {
	
	public enum GameMode {PLAYER_VS_PLAYER,PLAYER_VS_CPU,CPU_VS_CPU}; 
	public static GameMode mode;
	public String winnerName;
	
	@Override
	public void create() {
		
		setScreen(new MenuScreen(this));
	}	
	
}
