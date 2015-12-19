package game.api.desktop;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import game.api.MyGame;


public class DesktopLauncher { 
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		 
		config.title = "Dont kill me bitch"; //tytul okna
        config.width = 800;
        config.height = 600;
        
  		new LwjglApplication(new MyGame(), config);
	}
}