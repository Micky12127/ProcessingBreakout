package processing;

import java.awt.event.MouseEvent;

import processing.core.PApplet;

public class Circle extends OperateShape {
	float diameter;
	private float radius, circleCenterX, circleCenterY, mouseAndCircleDistance;
	
	Circle(PApplet papplet, float _diameter) {
		super(papplet);
		diameter = _diameter;
		papplet.addMouseListener(this);
	}
	
	public float getWidthFromCenter() {
		return diameter / 2;
	}
	
	public float getHeightFromCenter() {
		return getWidthFromCenter();
	}
	
	public float getCenterPointX() {
		return getX();
	}
	public void setCenterPointX(float xValue) {
		setX(xValue);
	}
	
	public float getCenterPointY() {
		return getY();
	}
	public void setCenterPointY(float yValue) {
		setY(yValue);
	}
	
	public boolean getMouseInShape() {
		radius = getWidthFromCenter();
		circleCenterX = getCenterPointX();
		circleCenterY = getCenterPointY();
		mouseAndCircleDistance = PApplet.dist(papplet.mouseX, papplet.mouseY, circleCenterX, circleCenterY);
		
		if (mouseAndCircleDistance < radius) {
			return true;
		} else {
			return false;
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (this.getIsFollowingMouse()) {
			this.setIsFollowingMouse(false);
			this.setAngle(-45);
			this.setSpeed(5);
		}
	}
	
	public void display() {
		papplet.fill(getColor());
		papplet.ellipse(getX(), getY(), diameter, diameter);
	}
}
