package processing;

import processing.core.PApplet;

public class Rectangle extends OperateShape {
	// 四角形の�?��高さ
	private float rectX, rectY;
	float rectWidth, rectHeight;
	
	private boolean isMouseXRectRange; // マウスのX座標が四角形のX座標から四角形の横�??間に存在するのかをチェ�?��する
	private boolean isMouseYRectRange; // マウスのY座標が四角形のY座標から四角形の横�??間に存在するのかをチェ�?��する
	
	Rectangle(PApplet papplet, float _rectWidth, float _rectHeight) {
		super(papplet);
		rectWidth = _rectWidth;
		rectHeight = _rectHeight;
	}
	
	public float getWidthFromCenter() {
		return rectWidth / 2;
	}
	
	public float getHeightFromCenter() {
		return rectHeight / 2;
	}
	
	public float getCenterPointX() {
		return getX() + getWidthFromCenter();
	}
	public void setCenterPointX(float xValue) {
		setX(xValue - getWidthFromCenter());
	}
	
	public float getCenterPointY() {
		return getY() + getHeightFromCenter();
	}
	public void setCenterPointY(float yValue) {
		setY(yValue - getHeightFromCenter());
	}
	
	public float getRightVertex() {
		return getX() + getWidthFromCenter();
	}
	
	public float getLeftVertex() {
		return getX() - getWidthFromCenter();
	}
	
	public float getTopVertex() {
		return getY() - getHeightFromCenter();
	}
	
	public float getBottomVertex() {
		return getY() + getHeightFromCenter();
	}
	
	public boolean getMouseInShape() {
		// マウスが四角形の�?��に存在するかをチェ�?��する
		isMouseXRectRange = papplet.mouseX >= getX() - getWidthFromCenter() && papplet.mouseX <= getX() + getWidthFromCenter();
		isMouseYRectRange = papplet.mouseY >= getY() - getHeightFromCenter() && papplet.mouseY <= getY() + getHeightFromCenter();
		if (isMouseXRectRange && isMouseYRectRange) {
			return true;
		} else {
			return false;
		}
	}
	
	public void display() {
		papplet.fill(getColor());
		rectX = getX() - getWidthFromCenter();
		rectY = getY() - getHeightFromCenter();
		papplet.rect(rectX, rectY, rectWidth, rectHeight);
	}
}