package PartsOfWorld;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import Guns.Pistol;

public class AdamAI extends AIPlayer{

	Random random;
	private float timer = 0;
	
	public AdamAI(float x, float y, Player.TypeOfGun gun) {
		super(x, y, gun);
		initTextures();
		
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
		
		AIshoot();
		
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
		case 2: this.AIjump();
		break;
		}
	}
	
}
