package game.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class Player {

	private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    
    private int width;
    private int height;
    
    public Player(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 400);
    }
	
    public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));

        if (velocity.y > 600-getHeight()) {
            velocity.y = 600-getHeight();
        }

        position.add(velocity.cpy().scl(delta));

        movement();
    }
    
    public void movement()
    {
    	 if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
    		 velocity.x-=10;
    	 }
    	 else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
    		 velocity.x+=10;
    	 }
    	 else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
    		 velocity.y-=10;
    	 }
    	 else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
    		 velocity.y+=10;
    	 }
    }

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}

