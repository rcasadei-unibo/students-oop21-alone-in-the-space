package model;

public interface Enemy extends Ship, Entity {
	void setTarget(Ship target);
	String drop();
}
