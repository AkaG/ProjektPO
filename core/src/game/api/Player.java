package game.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;


/*
 * 
 * 	NAPISANA TYLKO JAKO PRZYKLAD (w sumie ja tego nie pisa³em)
 * 
 */

public class Player extends Rectangle implements InputProcessor{

	private boolean canJump;
	private float velocityX; //predkosc w plaszczyznie x
	private float velocityY; // jak ujemne to leci w góre, a jak dodatnie to œci¹ga go w dó³
	
	private float gravity; //
	
	////////////////// POTRZEBNE DO ANIMACJI
	public enum Direction {RUN_LEFT, RUN_RIGHT, STAY_LEFT, STAY_RIGHT}; //nie dodalem jumpa, bo wtedy nie mozna
	Direction dir;														//by bylo skakac po skosie
	
	private TextureAtlas textureAtlas;
    private Animation GoLeftAnimation;
    private Animation GoRightAnimation;
    private Animation StayRightAnimation;
    private Animation StayLeftAnimation;
    private Animation JumpAnimation;
    
    public float elapsedTime = 0; //to jest potrzebne do animacji
   
    /////////////////////////////////
		
    public Player(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        this.x = Gdx.graphics.getWidth()/2 - this.width/2;
        this.y = 0;
        
        canJump = true;
        velocityX = 5;
        velocityY = 0;
        gravity = -1500; //takie wartoœci bo jest mno¿one przez delte(¿eby na ka¿dym kompie dzia³alo tak samo)
        				//ale nie wiem jak to zrobiæ lepiej 
        
        textureAtlas = new TextureAtlas(Gdx.files.internal("contra.atlas"));
        
        GoLeftAnimation = new Animation(0.1f,
                (textureAtlas.findRegion("1")),
                (textureAtlas.findRegion("2")),
                (textureAtlas.findRegion("3")),
                (textureAtlas.findRegion("4")),
                (textureAtlas.findRegion("5"))
               );
        
        GoRightAnimation = new Animation(0.1f,
                (textureAtlas.findRegion("6")),
                (textureAtlas.findRegion("7")),
                (textureAtlas.findRegion("8")),
                (textureAtlas.findRegion("9")),
                (textureAtlas.findRegion("10"))
               );
        
        StayLeftAnimation = new Animation(0.1f,(textureAtlas.findRegions("4")));
        StayRightAnimation = new Animation(0.1f,(textureAtlas.findRegions("7")));
        
        dir = Direction.STAY_LEFT;
        
    }
	
    public void update(float delta) {
    	
    	movement(delta);
    	elapsedTime += delta;
    	
    }
    
   //w movement jest zrobiona grawitacja(sciaga playera w dol) i poruszanie w lewo/prawo  
   
    public void movement(float delta) {
    	
    	y += velocityY * delta; //œci¹ga go w dó³
    	
    	if(y > 0){ //jezeli jest na ekranie
    		velocityY += gravity * delta; //dodajemy grawitacje zeby zwiekszyæ prêdkoœæ spadania
    	}
    	else{
    		y = 0; //zeby nie spadl ponizej ekranu
    		velocityY = 0;
    		canJump = true;
    	}
    	
    	if(dir == Direction.RUN_LEFT) x -= velocityX;
    	else if(dir == Direction.RUN_RIGHT) x+=velocityX;
    	
    }
    
    public void jump()
    {
    	if(canJump && velocityY >= -100)
    	velocityY += 800; // im wieksza wartosc tym wyzej skoczy
    	canJump = false;
    }
    
    //w zaleznosci od tego w jakim stanie to odpowiednia animacja
    public void draw(SpriteBatch batch)
    {
    	switch(dir)
     	{
     	case RUN_RIGHT:
     		batch.draw(GoRightAnimation.getKeyFrame(elapsedTime, true), x, y);
     		break;
     	case RUN_LEFT:
     		batch.draw(GoLeftAnimation.getKeyFrame(elapsedTime, true), x,y);
     		break;
     	case STAY_LEFT:
     		batch.draw(StayLeftAnimation.getKeyFrame(elapsedTime, true), x, y);
     		break;
     	case STAY_RIGHT: 
     		batch.draw(StayRightAnimation.getKeyFrame(elapsedTime, true), x, y);
     		break;
     		
     	}
    }

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if(keycode == Input.Keys.LEFT){
			dir = Direction.RUN_LEFT;
		}
		else if(keycode == Input.Keys.RIGHT){
			dir = Direction.RUN_RIGHT;
		}
		if(keycode == Input.Keys.UP){
			jump();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		if(dir != Direction.RUN_RIGHT && keycode == Input.Keys.LEFT)
			dir = Direction.STAY_LEFT;
		if(dir != Direction.RUN_LEFT && keycode == Input.Keys.RIGHT)
			dir = Direction.STAY_RIGHT;
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
    
    
    
    ////////////////////////////////////// 	GETTERY I SETTERY
	
	
	public boolean isCanJump() {
		return canJump;
	}

	public void setCanJump(boolean canJump) {
		this.canJump = canJump;
	}

	public float getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(float velocityX) {
		this.velocityX = velocityX;
	}

	public float getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(float velocityY) {
		this.velocityY = velocityY;
	}

	

	
}

