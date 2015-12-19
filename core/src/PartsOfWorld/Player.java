package PartsOfWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import interfaces.IPlayerControls;
import sun.util.resources.cldr.el.TimeZoneNames_el;


public class Player extends MovingObject implements IPlayerControls{
	
	//PORUSZANIE
	private boolean canJump;
	private float gravity;
	public enum Direction {RUN_LEFT, RUN_RIGHT, STAY_LEFT, STAY_RIGHT}; //nie dodalem jumpa, bo wtedy nie mozna //by bylo skakac po skosie
	protected Direction dir;														
	
	//ANIMACJE
	protected TextureAtlas textureAtlas;
	protected Animation GoAnimation;
	protected Animation StayAnimation;
	protected Animation JumpAnimation;
	protected float elapsedTime = 0;
	
	//BRON
    protected Gun gun;
    protected float timePerShoot = 0;
    
    //ZYCIE
    protected Texture healthTexture;
    protected Rectangle healthPoints;
    
	public Player(float x, float y) {
		
		//WARTOSCI POCZATKOWE
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 50;
        
        this.canJump = true;
        this.xSpeed = 300;
        this.ySpeed = 0;
        this.gravity = -1500;
        
        //INICJALIZACJA TEXTUR I ANIMACJI
        textureAtlas = new TextureAtlas(Gdx.files.internal("sprite2.atlas"));
		GoAnimation = new Animation(0.1f, (textureAtlas.findRegion("1")), (textureAtlas.findRegion("2")),
				(textureAtlas.findRegion("3")));
		StayAnimation = new Animation(0.1f, (textureAtlas.findRegions("4")));
		JumpAnimation = new Animation(0.5f, (textureAtlas.findRegion("8")));
        
		//USTAWIANIE KIERUNKU POCZATKWEGO
        this.dir = Direction.STAY_LEFT;
        
        //BRON
        this.gun = new Gun(this);
        
        //ZYCIE
        healthTexture = new Texture("hp.png");
        healthPoints = new Rectangle(this.getX(),this.getY(),width,20);
        
    }
	
    public void update(float delta) {
    	
    	//PORUSZANIE GRACZEM
    	movement(delta);
    	elapsedTime += delta;
    	timePerShoot += delta;
    	
    	//PORUSZANIE POCISKAMI
    	for(Bullet b: gun.getBullets())
    	{
    		b.move();
    	}
   
    	if(timePerShoot >= 0.5)
    	{
    		this.gun.setCanShoot(true);
    		timePerShoot -= 0.5;
    	}
    	
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
    	batch.draw(healthTexture, this.getX(), this.getY() + this.height + 10, healthPoints.width , 5);
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
		if(getGun().isCanShoot())
		{
		gun.shoot();
		getGun().setCanShoot(false);
		}
		
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

