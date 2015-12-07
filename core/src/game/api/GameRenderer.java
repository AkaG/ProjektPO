package game.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameRenderer {

	GameWorld myWorld;
	OrthographicCamera camera;
	ShapeRenderer shapeRenderer;
	
	public GameRenderer(GameWorld world)
	{
		myWorld = world;
		camera = new OrthographicCamera();
		camera.setToOrtho(true);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
		
	}
	
	public void render()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		
		 shapeRenderer.begin(ShapeType.Filled);

	        // Chooses RGB Color of 87, 109, 120 at full opacity
	        shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);

	        
	        
	        // Draws the rectangle from myWorld (Using ShapeType.Filled)
	        shapeRenderer.rect(myWorld.getPlayer().getVelocity().x, myWorld.getPlayer().getVelocity().y,
	                myWorld.getPlayer().getWidth(), myWorld.getPlayer().getHeight());

	        // Tells the shapeRenderer to finish rendering
	        // We MUST do this every time.
	        shapeRenderer.end();

	}
	

}

