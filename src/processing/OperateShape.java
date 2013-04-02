package processing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import processing.core.PApplet;

public abstract class OperateShape extends MoveShape implements MouseMotionListener, MouseListener {
	public OperateShape(PApplet papplet) {
		super(papplet);
		papplet.addMouseListener(this);
		papplet.addMouseMotionListener(this);
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
				this.setSpeed(15);
			} else if (isBallBarRangeCenter && !isBallBarRangeRight) {
				this.setY(670);
				this.setAngle( - (this.getAngle()));
				this.setSpeed(10);
			} else if (isBallBarRangeRight) {
				this.setY(670);
				this.setAngle(- 30 - (this.getAngle()));
				this.setSpeed(15);
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
	
	// -------------PApplet MouseListener----------------
	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}
	
	public void mouseExited(MouseEvent e) {
	}
	// ---------------------------------------------------
	
	// ---------- PApplet MouseMotionListener -------
	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
		if (this.getIsFollowingMouse()) {
			this.setX(papplet.mouseX);
		}
	}
	// ---------------------------------------------------
	
	public abstract boolean getMouseInShape(); 
	public abstract float getRightVertex();
	public abstract float getLeftVertex();
	public abstract float getTopVertex();
	public abstract float getBottomVertex();
}