package game.api;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import PartsOfWorld.Bullet;
import PartsOfWorld.Platform;
import PartsOfWorld.Player;

/*
 * 
 * 	KLASA ODPOWIEDZIALNA ZA RYSOWANIE OBIEKTOW
 * 
 */

public class GameRenderer {

	GameWorld myWorld; // deklaracja �wiata, czyli miejsca gdzie s� te obiekty
						// do rysowania
	OrthographicCamera camera; // camera taka od boku
	ShapeRenderer shapeRenderer; // potrzebne do rysowania
	SpriteBatch batch;
	BitmapFont font;

	public GameRenderer(GameWorld world) {
		myWorld = world;
		camera = new OrthographicCamera();
		camera.setToOrtho(true); // kamera obejmuje ca�y screen
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined); // w sumie nie wiem
		batch = new SpriteBatch();
		font = new BitmapFont();
	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(myWorld.getBackgroundTexture(),0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		// RYSOWANIE PLATFORM
		Iterator<Platform> itr = myWorld.getPlatforms().iterator();
		while (itr.hasNext()) {
			Platform p = itr.next();
			batch.draw(p.getTexture(), p.x, p.y, p.width, p.height);
		}

		// RYSOWANIE GRACZY
		int i = 0;
		for (Player p : myWorld.getPlayers()) {
			p.draw(batch);
			
			// RYSOWANIE BRONI
			p.getGun().draw(batch);
			
			// RYSOWANIE POCISKOW
			for (Bullet b : p.getGun().getBullets()) {
				b.draw(batch);
				}
			
			//RYSOWANIE NAZW
			font.setColor(Color.WHITE);
			font.draw(batch, p.getName(), p.getX(), p.getY()+p.getHeight()+40);
			
			font.setColor(Color.TAN);
			String s = p.getName() +" lifes: "+ p.getLifes();
			font.draw(batch, s, Gdx.graphics.getWidth()-110 , Gdx.graphics.getHeight() - (i+=20) );
		}
		
		
		
		

		batch.end();
	}

}