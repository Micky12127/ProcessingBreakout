package processing;

import processing.core.PApplet;

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
	
	public void hitBar(float barX, float barY, float barHalfWidth, float barHalfHeight) {
		boolean isBallXBarRange = this.getX() >= barX - barHalfWidth && getX() <= barX + barHalfWidth;
		boolean isBallYBarRange = this.getY() >= barY - barHalfHeight && getY() <= barY + barHalfHeight; 
		boolean isBallBarRangeLeft = this.getX() < (((barHalfWidth * 2) / 3) + (barX - barHalfWidth));
		boolean isBallBarRangeCenter = this.getX() >= (((barHalfWidth * 2) / 3) + (barX - barHalfWidth));
		boolean isBallBarRangeRight = this.getX() >= ((((barHalfWidth * 2) / 3) * 2) + (barX - barHalfWidth));
		if (isBallXBarRange && isBallYBarRange) {
			if (isBallBarRangeLeft) {
				this.setY(670);
				this.setAngle(30 - (this.getAngle()));
				this.setSpeed(8);
			} else if (isBallBarRangeCenter && !isBallBarRangeRight) {
				this.setY(670);
				this.setAngle( - (this.getAngle()));
				this.setSpeed(7);
			} else if (isBallBarRangeRight) {
				this.setY(670);
				this.setAngle(- 30 - (this.getAngle()));
				this.setSpeed(8);
			}
		} else if (this.getY() > papplet.width) {
			this.setX(barX);
			this.setY(670);
			this.setIsFollowingMouse(true);
			this.setSpeed(0);
		}
	}
	
	public boolean hitBlock(float blockX, float blockY, float blockHalfWidth, float blockHalfHeight) {
		
		boolean isRightVertexBlockRange = this.getRightVertex() >= blockX - blockHalfWidth && this.getRightVertex() <= blockX + blockHalfWidth
				&& this.getY() >= blockY - blockHalfHeight && this.getY() <= blockY + blockHalfHeight;
		boolean isLeftVertexBlockRange = this.getLeftVertex() >= blockX - blockHalfWidth && this.getLeftVertex() <= blockX + blockHalfWidth
				&& this.getY() >= blockY - blockHalfHeight && this.getY() <= blockY + blockHalfHeight;
		boolean isTopVertexBlockRange  = this.getTopVertex() >= blockY - blockHalfHeight && this.getTopVertex() <= blockY + blockHalfHeight
				&& this.getX() >= blockX - blockHalfWidth && this.getX() <= blockX + blockHalfWidth;
		boolean isBottomVertexBlockRange = this.getBottomVertex() >= blockY - blockHalfHeight && this.getBottomVertex() <= blockY + blockHalfHeight
				&& this.getX() >= blockX - blockHalfWidth && this.getX() <= blockX + blockHalfWidth;

		if (isRightVertexBlockRange) {
			this.setAngle(180 - (this.getAngle()));
			return true;
		} else if (isLeftVertexBlockRange) {
			this.setAngle(180 - (this.getAngle()));
			return true;
		} else if (isTopVertexBlockRange) {
			this.setAngle( - (this.getAngle()));
			return true;
		} else if (isBottomVertexBlockRange) {
			this.setAngle( - (this.getAngle()));
			return true;
		} else {
			return false;
		}
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