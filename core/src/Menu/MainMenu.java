/**
 * 
 */
package Menu;

import com.badlogic.gdx.scenes.scene2d.Stage;

import game.api.MyGame;

/**
 * @author Jakub
 *
 */
public class MainMenu {
	
	private FirstMenu firstMenu;
	private Stage stage;
	
	public MainMenu(MyGame game){
		// tworzenie sceny
		stage = new Stage();
		// tworzenie wstepnego menu
		firstMenu = new FirstMenu(stage, game);
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
