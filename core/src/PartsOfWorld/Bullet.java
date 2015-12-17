package PartsOfWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet extends MovingObject{
	
	final int height = 5;
	final int width = 5;
	
	Texture texture;
	
	enum Direction {LEFT,RIGHT};
	Direction dir;
	
	Bullet(float _x, float _y, Player player)
	{
		texture = new Texture("bullet.png");
		this.x = _x;
		this.y = _y;
		
		dir = Direction.LEFT;
	}
	
	public void move()
	{
		if(dir == Direction.LEFT) x -= 500 * Gdx.graphics.getDeltaTime();
		else if(dir == Direction.RIGHT) x += 500 * Gdx.graphics.getDeltaTime(); 
	}

	public void draw(SpriteBatch batch)
	{
		batch.draw(texture,this.x,this.y + Player.height/2,this.width,this.height);
	}
}
