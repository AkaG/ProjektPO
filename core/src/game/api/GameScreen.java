package game.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import Menu.MenuScreen;

/*
 * 
 *  TU W ZASADZIE NARAZIE BEZ ZMIAN
 * 
 */


public class GameScreen implements Screen{

	private GameWorld world;
	private GameRenderer renderer;
	private MyGame myGame;
	
	public GameScreen(MyGame game)
	{
		myGame = game;
		world = new GameWorld();
        renderer = new GameRenderer(world);
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		world.update(delta);
		if(world.getGameOver() == true){
			myGame.winnerName = world.getWinnerName();
			myGame.setScreen(new MenuScreen(myGame));
		}
		renderer.render();
		
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
