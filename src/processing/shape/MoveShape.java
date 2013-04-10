package processing.shape;

import processing.core.PApplet;
import processing.operation.Movable;

public abstract class MoveShape extends Shape implements Movable {
	private float speed, angle;
	public final double FRICTION = 1;
	
	public MoveShape(PApplet papplet) {
		super(papplet);
	}
	
	public void setSpeed(float speedValue) {
		speed = speedValue;
	}
	public float getSpeed() {
		return speed;
	}
	
	public void setAngle(float angleValue) {
		angle = angleValue;
	}
	public float getAngle() {
		return angle;
	}
	
	public void calcSpeed() {
		if (speed != 0) {
			speed *= FRICTION;
		}
		x += PApplet.cos(PApplet.radians(angle)) * speed;
		y += PApplet.sin(PApplet.radians(angle)) * speed;
	}
	
	public void move() {
		calcSpeed();
		
		float widthFromCenter = getWidthFromCenter();
		float heightFromCenter = getHeightFromCenter();
		
		if (getX() >= papplet.width - widthFromCenter) {
			setX(papplet.width - widthFromCenter);
			angle = 180 - angle;
		} else if(getX() <= widthFromCenter) {
			setX(widthFromCenter);
			angle = 180 - angle;
		} else if(getY() <= heightFromCenter) {
			setY(heightFromCenter);
			angle = -angle;
		}
		
		display();
	}

	public abstract float getWidthFromCenter();
	public abstract float getHeightFromCenter();
	public abstract float getCenterPointX();
	public abstract void setCenterPointX(float xValue);
	public abstract float getCenterPointY();
	public abstract void setCenterPointY(float yValue);
	public abstract float getRightVertex();
	public abstract float getLeftVertex();
	public abstract float getTopVertex();
	public abstract float getBottomVertex();
}