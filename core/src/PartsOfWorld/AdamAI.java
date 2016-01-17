package PartsOfWorld;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.sun.javafx.geom.Vec2f;

import Guns.Pistol;

public class AdamAI extends AIPlayer{

	Random random;
	private float timer = 0;
	
	public AdamAI(float x, float y, Player.TypeOfGun gun) {
		super(x, y, gun);
		initTextures();
		name = "Adam";
		
		this.gun = new Pistol(this);
		random = new Random();
		// TODO Auto-generated constructor stub
	}
	
	public void initTextures() {
		// TODO Auto-generated method stub
		 //INICJALIZACJA TEXTUR I ANIMACJI
        textureAtlas = new TextureAtlas(Gdx.files.internal("PlayerSprites/player4.atlas"));
		GoAnimation = new Animation(0.1f, (textureAtlas.findRegion("1")), (textureAtlas.findRegion("2")),
				(textureAtlas.findRegion("3")));
		StayAnimation = new Animation(0.1f, (textureAtlas.findRegions("4")));
		JumpAnimation = new Animation(0.5f, (textureAtlas.findRegion("8")));
	}
	
	

	@Override
	public void AIUpdate(float delta) {
		// TODO Auto-generated method stub
		timer += delta;
		if(timer >= 1)
		{
			randomMove();
			timer -=1 ;
		}
		findPlayerOnSameLevel();
		jumpWhenBulletIsNear();
		jumpWhenEnemyIsTooClose();
		//AIshoot();
		underOthers();
		
		if(this.x >= Gdx.graphics.getWidth() - this.width) AImoveLeft();
		else if(this.x <= 0) AImoveRight();
	}

	public void randomMove()
	{
		int temp = random.nextInt(3);
		switch(temp)
		{
		case 0: this.AImoveRight();
		break;
		case 1: this.AImoveLeft();
		break;
		//case 2: this.AIjump();
		//break;
		}
	}
	
	public void findPlayerOnSameLevel()
	{
		int numberOfEnemiesOnSameLevel = 0;
		boolean enemyOnLeftSide = false;
		boolean enemyOnRightSide = false;
		for(Vec2f v: enemyPosition)
		{
			if(Math.abs(v.y - this.getY()) < 20)
			{
				numberOfEnemiesOnSameLevel ++;
				
				if(v.x < this.getX())
				{
					//AImoveLeft();
					enemyOnLeftSide = true;
				}
				else if(v.x >= this.getX())
				{
					//AImoveRight();
					enemyOnRightSide = true;
				}
			}
		}
		
		if(numberOfEnemiesOnSameLevel == 0)
		{
			//AIStay();
		}
		else if((numberOfEnemiesOnSameLevel >= 1) && !enemyOnRightSide)
		{
			AImoveLeft();
			AIStay();
			AIshoot();
		}
		else if((numberOfEnemiesOnSameLevel >= 1) && !enemyOnLeftSide)
		{
			AImoveRight();
			AIStay();
			AIshoot();
		}
		else
		{
			AIshoot();
		}
		
	}
	
	public void jumpWhenBulletIsNear()
	{
		for(Vec2f v: bulletsPositions)
		{
			if(Math.abs(this.getX() - v.x) < 150 && (v.y > this.getY() && v.y < this.getY() + this.getHeight()))
			{
				AIjump();
			}
			
		}
	}
	
	public void jumpWhenEnemyIsTooClose()
	{
		for(Vec2f v: enemyPosition)
		{
			if(v.y == this.getY() && Math.abs(v.x - this.x) < 100)
			{
					AIjump();
					randomMove();
				
			}
		}
	}
	
	public void underOthers()
	{
		float min = this.getY()+20;
		boolean nethermost = true;
		for(Vec2f v: enemyPosition)
		{
			if(v.y <= min)
			{
				nethermost = false;
			}
		}
		if(nethermost) AIjump();
	}
}