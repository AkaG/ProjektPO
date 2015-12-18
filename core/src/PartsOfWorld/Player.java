package PartsOfWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import interfaces.IPlayerControls;


/*
 * 
 * 	NAPISANA TYLKO JAKO PRZYKLAD (w sumie ja tego nie pisa³em)
 * 
 */

public class Player extends MovingObject implements IPlayerControls{
	
	public static final int width = 50;
	public static final int height = 50;

	private boolean canJump;
	private float gravity;
	public enum Direction {RUN_LEFT, RUN_RIGHT, STAY_LEFT, STAY_RIGHT}; //nie dodalem jumpa, bo wtedy nie mozna //by bylo skakac po skosie
	protected Direction dir;														
	
	protected TextureAtlas textureAtlas;
	protected Animation GoAnimation;
	protected Animation StayAnimation;
	protected Animation JumpAnimation;
	protected float elapsedTime = 0;
		
    private Gun gun;
    
    
    
	public Player(float x, float y) {
		
        this.x = x;
        this.y = y;
        
        this.canJump = true;
        this.xSpeed = 300;
        this.ySpeed = 0;
        this.gravity = -1500; //takie wartoœci bo jest mno¿one przez delte(¿eby na ka¿dym kompie dzia³alo tak samo)
        				//ale nie wiem jak to zrobiæ lepiej 
        
        textureAtlas = new TextureAtlas(Gdx.files.internal("sprite2.atlas"));
		GoAnimation = new Animation(0.1f, (textureAtlas.findRegion("1")), (textureAtlas.findRegion("2")),
				(textureAtlas.findRegion("3")));

		StayAnimation = new Animation(0.1f, (textureAtlas.findRegions("4")));

		JumpAnimation = new Animation(0.5f, (textureAtlas.findRegion("8")));
        
        this.dir = Direction.STAY_LEFT;
        
        this.gun = new Gun(this);
        
    }
	
    public void update(float delta) {
    	
    	movement(delta);
    	elapsedTime += delta;
    	
    	//POCISKI
    	for(Bullet b: gun.getBullets())
    	{
    		b.move();
    	}
    	
    }
    
   //w movement jest zrobiona grawitacja(sciaga playera w dol) i poruszanie w lewo/prawo  
   
    public void movement(float delta) {
    	
    	y += ySpeed * delta; //œci¹ga go w dó³
    	
    	if(y > 0){ //jezeli jest na ekranie
    		ySpeed += gravity * delta; //dodajemy grawitacje zeby zwiekszyæ prêdkoœæ spadania
    	}
    	else{
    		y = 0; //zeby nie spadl ponizej ekranu
    		ySpeed = 0;
    		canJump = true;
    	}
    	
    	if(dir == Direction.RUN_LEFT) moveRight();
    	else if(dir == Direction.RUN_RIGHT)  moveLeft();
    	
    }
    
    private void jump(){
    	if(canJump && ySpeed >= -100)
    	ySpeed += 800; // im wieksza wartosc tym wyzej skoczy
    	canJump = false;
    }
    
  
    public void draw(SpriteBatch batch){
    	if (canJump) { // jak moze skacze to znaczy, ze nie jest w powietrzu,
			// narazie nie zmieniam
    		
	    	switch (dir) {
				case RUN_RIGHT:
					batch.draw(GoAnimation.getKeyFrame(elapsedTime, true), x - width / 2, y, width, height);
					break;
				case RUN_LEFT:
					batch.draw(GoAnimation.getKeyFrame(elapsedTime, true), x + width / 2, y, -width, height);
					break;
				case STAY_LEFT:
					batch.draw(StayAnimation.getKeyFrame(elapsedTime, true), x + width / 2, y, -width, height);
					break;
				case STAY_RIGHT:
					batch.draw(StayAnimation.getKeyFrame(elapsedTime, true), x - width / 2, y, width, height);
					break;
	    	}
    	} else {
			if (dir == Direction.STAY_RIGHT || dir == Direction.RUN_RIGHT) {
				batch.draw(JumpAnimation.getKeyFrame(elapsedTime, false), x - width / 2, y, width, height);
			} else if (dir == Direction.STAY_LEFT || dir == Direction.RUN_LEFT)
				batch.draw(JumpAnimation.getKeyFrame(elapsedTime, false), x + width / 2, y, -width, height);
		}
    }
    
    
    private void moveLeft() {
		// TODO Auto-generated method stub
    	x += xSpeed * Gdx.graphics.getDeltaTime();
    	
	}

	private void moveRight() {
		// TODO Auto-generated method stub
		x -= xSpeed * Gdx.graphics.getDeltaTime();
	}

	@Override
	public void Pshoot() {
		// TODO Auto-generated method stub
		gun.shoot();
	}
	
	@Override
	public void Pjump() {
		jump();
	}
	
	@Override
	public void PmoveLeft() {
		// TODO Auto-generated method stub
		dir = Direction.RUN_LEFT;
	}
	
	@Override
	public void PmoveRight() {
		// TODO Auto-generated method stub
		dir = Direction.RUN_RIGHT;
	}
	
//////////////////////////////////////GETTERY I SETTERY
	
	
	public Direction getDir() {
		return dir;
	}

	public Gun getGun() {
		return gun;
	}

	public void setGun(Gun gun) {
		this.gun = gun;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	
	public boolean isCanJump() {
	return canJump;
	}

	public void setCanJump(boolean canJump) {
	this.canJump = canJump;
	}
	
}

