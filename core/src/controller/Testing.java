package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

	private final Rectangle rectangle = new Rectangle();
	private final List<Rectangle> ret = new ArrayList<>();
	private final Random rnd = new Random();
	private final String s = System.getProperty("file.separator");
	private final String path = "/controller/pix.jpg";
	private final Entity entity = new Entity(path);
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		System.out.println(path);
		rectangle.setX(50); 
		rectangle.setY(100); 
	    rectangle.setWidth(100.0f); 
	    rectangle.setHeight(50.0f); 
	    
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
					/*rectangle.setY(rectangle.getY() - 5);
					if(rectangle.getY() <= 0 - rectangle.getHeight()) {
						rectangle.setY(300);
					}*/
					System.out.println("up");
					entity.moveUp();
					System.out.println(entity.getImg().getY());
				}
				if(event.getCode().equals(KeyCode.DOWN)) {
					rectangle.setY(rectangle.getY() + 5);
					if(rectangle.getY() >= 300) {
						rectangle.setY(0 - rectangle.getHeight());
					}
				}
				if(event.getCode().equals(KeyCode.LEFT)) {
					rectangle.setX(rectangle.getX() - 5);
					if(rectangle.getX() <= 0 - rectangle.getWidth()) {
						rectangle.setX(600);
					}
				}
				if(event.getCode().equals(KeyCode.RIGHT)) {
					rectangle.setX(rectangle.getX() + 5);
					if(rectangle.getX() >= 600) {
						rectangle.setX(0 - rectangle.getWidth());
					}
				}
				for(Rectangle r : ret) {
					if(rectangle.getBoundsInParent().intersects(r.getBoundsInParent())) {
						System.out.println("Collision");
					}
				}
			}
	    	
	    };

	    entity.getImg().setFocusTraversable(true);
	    entity.getImg().setOnKeyPressed(movement);
	    
	    rectangle.setFocusTraversable(true);
	    rectangle.setOnKeyPressed(movement);
	    
		Group root = new Group();
		//root.getChildren().add(rectangle);
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


