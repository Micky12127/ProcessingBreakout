package processing.shape;

import processing.core.PApplet;
import processing.operation.Displayable;

public abstract class Shape implements Displayable {
	protected float x, y;
	// isFollowingTheMouse（マウスに追従するかしないかを決めるためのもの）
	private int shapeColor, clickedShapeFlag;
	protected PApplet papplet;
	
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
	
	// 図形がクリックされたことを確認するためのフラグ（0ならクリックされている、1ならクリックされていない）
	public void setClickedShapeFlag(int clickedShapeFlagValue) {
		clickedShapeFlag = clickedShapeFlagValue;
	}
	
	public int getClickedShapeFlag() {
		return clickedShapeFlag;
	}
}