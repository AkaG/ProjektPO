package PartsOfWorld;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.sun.javafx.geom.Vec2f;

public final class KubaAI extends AIPlayer {

	public KubaAI(float x, float y, Player.TypeOfGun gun) { 
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
	
	private Vec2f findNearestEnemy(){
		Vec2f tmp = new Vec2f();
		for(Vec2f enemy : enemyPosition) {
			if(tmp == null){
				tmp = enemy;
				continue;
			}
			
/*			else{
				if(enemy.x != x && enemy.y != y){
					if(enemy.x < x){
						if(x < tmp.x){
							if(x - enemy.x < tmp.x - x){
								tmp = enemy;
							}
						}else{
							if(x - enemy.x < x - tmp.x){
								tmp = enemy;
							}
						}
					}else{
						if(x < tmp.x){
							if(enemy.x - x < tmp.x - x){
								tmp = enemy;
							}
						}else{
							if(enemy.x - x < x - tmp.x){
								tmp = enemy;
							}
						}
					}
				}
			}*/
		}
		return tmp;
	}
	
	public void AIUpdate(float delta) { 
		Vec2f tmp = null;// = findNearestEnemy();
		if (tmp != null){
			if(tmp.x < x)
				AImoveLeft();
			else
				AImoveRight();
		}
/*		if(enemyPosition != null){
			System.out.println(enemyPosition.get(0).x);
		}*/
	}
	
}
