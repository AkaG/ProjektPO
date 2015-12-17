package Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import game.api.GameScreen;
import game.api.MyGame;

public class MenuScreen implements Screen {
	
	private MyGame game;
	private MainMenu mainMenu;
	private MenuRenderer menuRenderer;
	
	OrthographicCamera camera;
	
	public MenuScreen(MyGame myGame){
		game = myGame;
		
		mainMenu = new MainMenu();
		menuRenderer = new MenuRenderer(mainMenu);
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		
		menuRenderer.render();
		
		if(Gdx.input.isTouched()){
			game.setScreen(new GameScreen());
			
			dispose();
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
