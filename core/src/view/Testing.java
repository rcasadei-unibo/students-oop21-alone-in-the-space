package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.collisionDetection.Collision;
import controller.collisionDetection.CollisionImpl;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Entity;

public class Testing extends Application {

	private final List<Rectangle> ret = new ArrayList<>();
	private final Random rnd = new Random();
	private final String path = "/view/pix.jpg";
	private final Entity entity = new Entity(path);
	private final Collision c = new CollisionImpl();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		 
	    entity.getImg().setX(330);
	    entity.getImg().setY(300);
	    
	    
	    for(int i = 0; i < 5; i++) {
	    	ret.add(new Rectangle());
	    }
	    
	    for(Rectangle r : ret) {
	    	r.setX(rnd.nextInt(600)); 
			r.setY(rnd.nextInt(300)); 
		    r.setWidth(100.0f); 
		    r.setHeight(50.0f);
		    r.setFill(Color.RED);   
	    }
	    
	    
	    EventHandler<KeyEvent> movement = new EventHandler<KeyEvent>() {
	    	
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.UP)) {
					entity.moveUp();
				}
				if(event.getCode().equals(KeyCode.DOWN)) {
					entity.moveDown();
				}
				if(event.getCode().equals(KeyCode.LEFT)) {
					entity.moveLeft();
				}
				if(event.getCode().equals(KeyCode.RIGHT)) {
					entity.moveRight();
				}
				for(Rectangle r : ret) {
					c.checkEnemiesCollision(entity, r);
				}
			}
	    	
	    };

	    entity.getImg().setFocusTraversable(true);
	    entity.getImg().setOnKeyPressed(movement);
	    
	    
		Group root = new Group();
		root.getChildren().addAll(ret);
		root.getChildren().add(entity.getImg());
		Scene scene = new Scene(root, 1200, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("JavaFX application");
		primaryStage.show();
	}
	
		
	public static void main(String[] args) {
		launch();
	}
}


