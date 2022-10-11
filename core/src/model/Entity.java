package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Entity {
	
	private final Image bufImg;
	private final ImageView img;
	
	public Entity(final String url) {
		bufImg = new Image(url);
		img = new ImageView(bufImg);
	}
	
	public void moveUp() {
		img.setY(img.getY() - 5);
		if(img.getY() <= 0 - img.getFitHeight()) {
			img.setY(300);
		}
	}
	
	public ImageView getImg() {
		return this.img;
	}

}
