package game.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/*
 * 
 * 	KLASA ODPOWIEDZIALNA ZA RYSOWANIE OBIEKTOW
 * 
 */


public class GameRenderer {

	GameWorld myWorld; //deklaracja œwiata, czyli miejsca gdzie s¹ te obiekty do rysowania
	OrthographicCamera camera; //camera taka od boku
	ShapeRenderer shapeRenderer; //potrzebne do rysowania 
	
	public GameRenderer(GameWorld world)
	{
		myWorld = world; 
		camera = new OrthographicCamera();
		camera.setToOrtho(true); //kamera obejmuje ca³y screen
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined); //w sumie nie wiem
		
	}
	
	public void render()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		
		 shapeRenderer.begin(ShapeType.Filled); 
	     shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);

	     shapeRenderer.rect(myWorld.getPlayer().getVelocity().x, myWorld.getPlayer().getVelocity().y,
	                myWorld.getPlayer().getWidth(), myWorld.getPlayer().getHeight()); //rysowany player

	      
	     shapeRenderer.end();

	}
	

}

