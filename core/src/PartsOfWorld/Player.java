package PartsOfWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import Guns.AssaultRifle;
import Guns.Gun;
import Guns.Pistol;
import Guns.Shotgun;
import Guns.SniperRifle;
import interfaces.IPlayerControls;
import sun.util.resources.cldr.el.TimeZoneNames_el;


public abstract class Player extends MovingObject implements IPlayerControls{
	
	//PORUSZANIE
	protected boolean canJump;
	protected float gravity;
	public enum Direction {RUN_LEFT, RUN_RIGHT, STAY_LEFT, STAY_RIGHT}; //nie dodalem jumpa, bo wtedy nie mozna //by bylo skakac po skosie
	protected Direction dir;														
	
	//ANIMACJE
	protected TextureAtlas textureAtlas;
	protected Animation GoAnimation;
	protected Animation StayAnimation;
	protected Animation JumpAnimation;
	protected float elapsedTime = 0;
	
	//BRON
	public enum TypeOfGun {PISTOL,SNIPER_RIFLE,ASSAULT_RIFLE,SHOTGUN,CARBINE};
    protected Gun gun;
    
    //ZYCIE
    protected Texture healthTexture;
    protected Rectangle healthPoints;
    
	public Player(float x, float y, TypeOfGun typeOfGun) {
		
		//WARTOSCI POCZATKOWE
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 50;
        
        this.canJump = true;
        this.xSpeed = 300;
        this.ySpeed = 0;
        this.gravity = -1500;
        
		//USTAWIANIE KIERUNKU POCZATKWEGO
        this.dir = Direction.STAY_LEFT;
        
        //BRON
        switch(typeOfGun)
        {
        case PISTOL:
        	this.gun = new Pistol(this);
        	break;
        case SNIPER_RIFLE:
        	this.gun = new SniperRifle(this);
        	break;
        case ASSAULT_RIFLE:
        	this.gun = new AssaultRifle(this);
        	break;
        case SHOTGUN:
        	this.gun = new Shotgun(this);
        	break;
        }
        
        //ZYCIE
        healthTexture = new Texture("hp.png");
        healthPoints = new Rectangle(this.getX(),this.getY(),width*2,20); //100 punktów ¿ycia
        
    }
	
    public void update(float delta) {
    	
    	//PORUSZANIE GRACZEM
    	movement(delta);
    	elapsedTime += delta;
    	
    }
    
   
    public void movement(float delta) {
    	
    	//GRAWITACJA
    	y += ySpeed * delta;
    	
    	if(y > 0){
    		ySpeed += gravity * delta;
    	}
    	else{
    		y = 0;
    		ySpeed = 0;
    		canJump = true;
    	}
    	
    	//PORUSZANIE
    	if(dir == Direction.RUN_LEFT) moveRight();
    	else if(dir == Direction.RUN_RIGHT)  moveLeft();
    	
    	//WYCHODZENIE POZA EKRAN
    	if(this.x < 0) this.x = 0;
    	else if(this.x > Gdx.graphics.getWidth() - this.width) this.x = Gdx.graphics.getWidth() - this.width;
    }
    
    private void jump(){
    	if(canJump && ySpeed >= -100)
    	ySpeed += 800; // im wieksza wartosc tym wyzej skoczy
    	canJump = false;
    }
    int a = 50;
  
    public void draw(SpriteBatch batch){
    	
    	//ODPOWIEDNIA ANIMACJA W ZALEZNOSCI OD STANU PLAYERA
    	if (canJump) { 
    		switch (dir) {
				case RUN_RIGHT:
					batch.draw(GoAnimation.getKeyFrame(elapsedTime, true), x, y, width, height);
					break;
				case RUN_LEFT:
					batch.draw(GoAnimation.getKeyFrame(elapsedTime, true), x+width, y, -width, height);
					break;
				case STAY_LEFT:
					batch.draw(StayAnimation.getKeyFrame(elapsedTime, true), x+width, y, -width, height);
					break;
				case STAY_RIGHT:
					batch.draw(StayAnimation.getKeyFrame(elapsedTime, true), x, y, width, height);
					break;
	    	}
    	//ANIMACJE SKAKANIA
    	} else {
			if (dir == Direction.STAY_RIGHT || dir == Direction.RUN_RIGHT) {
				batch.draw(JumpAnimation.getKeyFrame(elapsedTime, false), x, y, width, height);
			} else if (dir == Direction.STAY_LEFT || dir == Direction.RUN_LEFT)
				batch.draw(JumpAnimation.getKeyFrame(elapsedTime, false), x+width, y, -width, height);
		}
    	
    	///RYSOWANIE ZYCIA
    	batch.draw(healthTexture, this.getX(), this.getY() + this.height + 10, healthPoints.width/2 , 5);
    }
    
    public abstract void initTextures();
   

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
	
	public Rectangle getHealthPoints() {
			return healthPoints;
	}

	public void takeDamage(int damage){
		this.healthPoints.width -= damage;
	}
	
}

