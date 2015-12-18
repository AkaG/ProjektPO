package game.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import PartsOfWorld.Bullet;

/*
 * 
 * 	KLASA ODPOWIEDZIALNA ZA RYSOWANIE OBIEKTOW
 * 
 */


public class GameRenderer {

	GameWorld myWorld; //deklaracja �wiata, czyli miejsca gdzie s� te obiekty do rysowania
	OrthographicCamera camera; //camera taka od boku
	ShapeRenderer shapeRenderer; //potrzebne do rysowania 
	SpriteBatch batch;
	
	
	
	public GameRenderer(GameWorld world)
	{
		myWorld = world; 
		camera = new OrthographicCamera();
		camera.setToOrtho(true); //kamera obejmuje ca�y screen
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined); //w sumie nie wiem
		batch = new SpriteBatch();
		
		
		
	}
	
	public void render()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		
	     	batch.begin();
	     	
	     	//RYSOWANIE PLATFORMY
	     	batch.draw(myWorld.platform.getTexture(), myWorld.platform.x, myWorld.platform.y, myWorld.platform.width, myWorld.platform.height);
	     	
	     	//RYSOWANIE GRACZA
	     	myWorld.getPlayer().draw(batch);
	     	myWorld.getAI().draw(batch);
	     	
	     	//RYSOWANIE POCISKOW
	     	for(Bullet b: myWorld.getPlayer().getGun().getBullets())
	     	{
	     		b.draw(batch);
	     	}
	     	
	        batch.end();
	}
	

}

