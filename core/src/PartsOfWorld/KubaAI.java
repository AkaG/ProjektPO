package PartsOfWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class KubaAI extends AIPlayer {

	public KubaAI(float x, float y, Player.TypeOfGun gun) { // konstruktor musi byc
		super(x, y, gun);
		initTextures();
		name = "Kuba";
		// TODO Auto-generated constructor stu
	}
	
	public void initTextures() {
		// TODO Auto-generated method stub
		 //INICJALIZACJA TEXTUR I ANIMACJI
        textureAtlas = new TextureAtlas(Gdx.files.internal("PlayerSprites/player3.atlas"));
		GoAnimation = new Animation(0.1f, (textureAtlas.findRegion("1")), (textureAtlas.findRegion("2")),
				(textureAtlas.findRegion("3")));
		StayAnimation = new Animation(0.1f, (textureAtlas.findRegions("4")));
		JumpAnimation = new Animation(0.5f, (textureAtlas.findRegion("8")));
	}

	
	public void AIUpdate(float delta) { // przykladowe uzycie funkcji, nie zapomniec o ai przed funkcja
		AIjump();
	}
	
}
