package PartsOfWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import java.util.Random;

public class MikolajAI extends AIPlayer {

	private float time = 0;
	Random rand = new Random();
	
	public MikolajAI(float x, float y, TypeOfGun gun) {
		super(x, y, gun);
		initTextures();
		name = "Mikolaj";
	}
	
	@Override
	public void initTextures() {
		 textureAtlas = new TextureAtlas(Gdx.files.internal("PlayerSprites/player2.atlas"));
			GoAnimation = new Animation(0.1f, (textureAtlas.findRegion("1")), (textureAtlas.findRegion("2")),
					(textureAtlas.findRegion("3")));
			StayAnimation = new Animation(0.1f, (textureAtlas.findRegions("4")));
			JumpAnimation = new Animation(0.5f, (textureAtlas.findRegion("8")));

	}

	@Override
	public void AIUpdate(float delta) {
		// TODO Auto-generated method stub
		int tmp = rand.nextInt(20);
		
		time += delta;
		if (time >= 1) {
			randomMove();
			time = 0;
		}
		if (tmp == 19) AIjump(); 
		else this.AIshoot();
	}
	
	public void randomMove() {
		int tmp = rand.nextInt(2);

		
		if(this.x <= 5) {
			this.AImoveRight();
		} else if (this.x > 800) {
			this.AImoveLeft();
		} else {
			switch(tmp){
			case 0 : this.AImoveLeft(); break;
			case 1 : this.AImoveRight(); break;
			default : break;
			}
		}
	}
}