package PartsOfWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MarcinAI extends AIPlayer {

	public MarcinAI(float x, float y, TypeOfGun gun) {
		super(x, y, gun);
		initTextures();
		name = "Marcin";
	}
	
	@Override
	public void initTextures() {
		 textureAtlas = new TextureAtlas(Gdx.files.internal("PlayerSprites/player5.atlas"));
			GoAnimation = new Animation(0.1f, (textureAtlas.findRegion("1")), (textureAtlas.findRegion("2")),
					(textureAtlas.findRegion("3")));
			StayAnimation = new Animation(0.1f, (textureAtlas.findRegions("4")));
			JumpAnimation = new Animation(0.5f, (textureAtlas.findRegion("8")));

	}

	@Override
	public void AIUpdate(float delta) {
		// TODO Auto-generated method stub

	}

}
