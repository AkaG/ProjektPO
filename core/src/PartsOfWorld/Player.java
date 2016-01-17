<<<<<<< HEAD
package PartsOfWorld;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.sun.javafx.geom.Vec2f;
import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;

import Guns.AssaultRifle;
import Guns.Gun;
import Guns.Pistol;
import Guns.Shotgun;
import Guns.SniperRifle;
import interfaces.IPlayerControls;


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
	public enum TypeOfGun {PISTOL,SNIPER_RIFLE,ASSAULT_RIFLE,SHOTGUN};
    protected Gun gun;
    protected TypeOfGun myGun;
    
    //ZYCIE
    protected Texture healthTexture;
    protected Rectangle healthPoints;
    protected int lifes;
    
    String name;
    
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
        
        this.name = new String();
        
		//USTAWIANIE KIERUNKU POCZATKWEGO
        this.dir = Direction.STAY_LEFT;
        
        //BRON
        myGun = typeOfGun;
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
        this.healthTexture = new Texture("hp.png");
        this.healthPoints = new Rectangle(this.getX(),this.getY(),width*2,20); //100 punktów ¿ycia
        this.lifes = 4;
        
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
	
	public void updateEnemyPosition(ArrayList<Vec2f> otherPos){
		
	}
	
	public void updateEnemyBulletsPositions(ArrayList<Vec2f> bulletsPos)
	{
		
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
	
	public void setGunType(TypeOfGun typeOfGun){
		myGun = typeOfGun;
	}
	
	public TypeOfGun getGunType(){
		return myGun;
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
	
	public void setHealthPoints(int hp){
		healthPoints.width = hp;
	}

	public void takeDamage(int damage){
		this.healthPoints.width -= damage;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Vec2f getPosition()
	{
		return new Vec2f(this.x,this.y);
	}
	
	public void decrementLifes()
	{
		this.lifes --;
	}
	
	public int getLifes()
	{
		return this.lifes;
	}
	
}

=======
package PartsOfWorld;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.sun.javafx.geom.Vec2f;
import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;

import Guns.AssaultRifle;
import Guns.Gun;
import Guns.Pistol;
import Guns.Shotgun;
import Guns.SniperRifle;
import interfaces.IPlayerControls;


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
	public enum TypeOfGun {PISTOL,SNIPER_RIFLE,ASSAULT_RIFLE,SHOTGUN};
    protected Gun gun;
    protected TypeOfGun myGun;
    
    //ZYCIE
    protected Texture healthTexture;
    protected Rectangle healthPoints;
    protected int lifes;
    
    String name;
    
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
        
        this.name = new String();
        
		//USTAWIANIE KIERUNKU POCZATKWEGO
        this.dir = Direction.STAY_LEFT;
        
        //BRON
        myGun = typeOfGun;
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
        this.healthTexture = new Texture("hp.png");
        this.healthPoints = new Rectangle(this.getX(),this.getY(),width*2,20); //100 punktów ¿ycia
        this.lifes = 4;
        
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
	
	public void updateEnemyPosition(ArrayList<Vec2f> otherPos){
		
	}
	
	public void updateEnemyBulletsPositions(ArrayList<Vec2f> bulletsPos)
	{
		
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
	
	public void setGunType(TypeOfGun typeOfGun){
		myGun = typeOfGun;
	}
	
	public TypeOfGun getGunType(){
		return myGun;
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
	
	public void setHealthPoints(int hp){
		healthPoints.width = hp;
	}

	public void takeDamage(int damage){
		this.healthPoints.width -= damage;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Vec2f getPosition()
	{
		return new Vec2f(this.x,this.y);
	}
	
	public void decrementLifes()
	{
		this.lifes --;
	}
	
	public int getLifes()
	{
		return this.lifes;
	}
	
}

>>>>>>> bce19af568227add24c7f80d2def0ce00e558cb9
