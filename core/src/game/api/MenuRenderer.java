/**
 * 
 */
package game.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Jakub
 *
 */
public class MenuRenderer {
	
	private MainMenu menu;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	public BitmapFont font;
	
	public MenuRenderer(MainMenu mainMenu){
		menu = mainMenu;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		
		batch = new SpriteBatch();
		font  = new BitmapFont();
	}
	
	public void render(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		font.draw(batch, "hello", 100, 200);
		font.draw(batch, "click on screen to start game", 100, 150);
		batch.end();
	}
}
