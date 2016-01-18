package PartsOfWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.sun.javafx.geom.Vec2f;


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
		jumpWhenTheBulletIsClose();
		AIshoot();
		doKąta();

	}

	public void jumpWhenTheBulletIsClose () {
		for (Vec2f b : bulletsPositions) {
			if (b.y > this.getY() && b.y < (this.getY() + this.getHeight()) && Math.abs(this.getX() - b.x) < 200) {
				AIjump();
			}
		}
	}

	public void doKąta () {
		if (this.getY() != 0 && this.getX() != 0) {
			this.AImoveLeft();
		} else this.setDir(Direction.STAY_RIGHT);

	}
}
