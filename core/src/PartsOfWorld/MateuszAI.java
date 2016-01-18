package PartsOfWorld;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.sun.javafx.geom.Vec2f;

public class MateuszAI extends AIPlayer {

	Random random;
	private float timer = 0;
	private boolean movingLeft;
	private boolean movingRight;
	private boolean jumping;
	
	public MateuszAI(float x, float y, TypeOfGun gun) {
		super(x, y, gun);
		initTextures();
		name = "Mateusz";
		
		random = new Random();
	}
	
	@Override
	public void initTextures() {
		 textureAtlas = new TextureAtlas(Gdx.files.internal("PlayerSprites/player.atlas"));
			GoAnimation = new Animation(0.1f, (textureAtlas.findRegion("1")), (textureAtlas.findRegion("2")),
					(textureAtlas.findRegion("3")));
			StayAnimation = new Animation(0.1f, (textureAtlas.findRegions("4")));
			JumpAnimation = new Animation(0.5f, (textureAtlas.findRegion("8")));

	}

	@Override
	public void AIUpdate(float delta) {
		// TODO Auto-generated method stub
		timer += delta;
		if (timer >= 0.5){
			randomMove();
			timer = 0;
		}
		AIshoot();
		
		if(this.x >= Gdx.graphics.getWidth() - this.width) AImoveLeft();
		else if(this.x <= 0) AImoveRight();
	}

	public void randomMove()
	{
		switch (random.nextInt(3)){
		case 0:
			AImoveLeft();
			break;
		case 1:
			AImoveRight();
			break;
		case 2:
			AIjump();
			break;
		}
	}
}
