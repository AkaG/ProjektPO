package PartsOfWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.media.jfxmedia.events.PlayerStateEvent.PlayerState;

public class Bullet extends MovingObject{
	
	Texture texture;
	float bulletSpeed;
	
	enum Direction {LEFT,RIGHT};
	Direction dir;


	public Bullet(float _x, float _y, Player player, Texture _texture, float _bulletSpeed, float gunWidth)
	{
		this.texture = _texture;
		this.y = _y + 20;
		this.width = 15;
		this.height = 15;
		this.bulletSpeed = _bulletSpeed;
		
		
		//USTAWIANIE KIERUNKU POCISKU + DODANIE DLUGOSCI BRONI
		Player.Direction pDirection = player.getDir();
		
		if(pDirection == Player.Direction.RUN_LEFT || pDirection == Player.Direction.STAY_LEFT){
			dir = Direction.LEFT;
			this.x = _x - gunWidth/2;
		}
		
		if(pDirection == Player.Direction.RUN_RIGHT || pDirection == Player.Direction.STAY_RIGHT){
			dir = Direction.RIGHT;
			this.x = _x + gunWidth;
		}

	
	}
	
	public void move()
	{
		if(dir == Direction.LEFT) x -= bulletSpeed * Gdx.graphics.getDeltaTime();
		else if(dir == Direction.RIGHT) x += bulletSpeed * Gdx.graphics.getDeltaTime(); 
	}

	public void draw(SpriteBatch batch)
	{
		batch.draw(texture,this.x,this.y,this.width,this.height);
	}
}
