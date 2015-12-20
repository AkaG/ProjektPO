/**
 * 
 */
package Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * @author Jakub
 *
 */
public class MainMenu {
	
	private FirstMenu firstMenu;
	private Stage stage;
	
	public MainMenu(){
		// tworzenie sceny
		stage = new Stage();
		// tworzenie wstepnego menu
		firstMenu = new FirstMenu(stage);
	}
	
	public void update(){
		
	}
	
	public void draw(float delta){
		//rysowanie sceny
		stage.act(delta);
		stage.draw();
	}
	
	
	//gettery i settery
	
	public FirstMenu getFirstMenu() {
		return firstMenu;
	}
	
	public Stage getStage(){
		return stage;
	}
}
