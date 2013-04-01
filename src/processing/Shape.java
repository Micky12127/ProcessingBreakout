package processing;

import processing.core.PApplet;

public abstract class Shape implements Displayable {
	protected float x, y;
	// isFollowingTheMouse（マウスに追従するかしないかを決めるためのもの）
	private int shapeColor;
	protected PApplet papplet;
	private boolean isFollowingMouse = false;
	
	public Shape(PApplet papplet) {
		this.papplet = papplet;
	}
	public float getX() {
		return x;
	}
	public void setX(float xValue) {
		x = xValue;
	}
	
	public float getY() {
		return y;
	}
	public void setY(float yValue) {
		y = yValue;
	}
	
	public void setColor(int colorValue) {
		shapeColor = colorValue;
	}
	public int getColor() {
		return shapeColor;
	}
	
	public void setIsFollowingMouse(boolean isFollowingMouseValue) {
		isFollowingMouse = isFollowingMouseValue;
	}
	public boolean getIsFollowingMouse() {
		return isFollowingMouse;
	}
}