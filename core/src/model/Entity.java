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
		if(img.getY() <= 0 - img.getImage().getHeight()) {
			img.setY(600);
		}
	}
	
	public void moveDown() {
		img.setY(img.getY() + 5);
		if(img.getY() >= 600) {
			img.setY(0 - img.getImage().getHeight());
		}
	}
	
	public void moveLeft() {
		img.setX(img.getX() - 5);
		if(img.getX() <= 0 - img.getImage().getWidth()) {
			img.setX(1200);
		}
	}
	
	public void moveRight() {
		img.setX(img.getX() + 5);
		if(img.getX() >= 1200) {
			img.setX(0 - img.getImage().getWidth());
		}
	}
	
	public ImageView getImg() {
		return this.img;
	}

}
