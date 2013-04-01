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
		if (isBallXBarRange && isBallYBarRange) {
			this.setY(670);
			this.setAngle(5 - (this.getAngle()));
		} else if (this.getY() > papplet.width) {
			this.setX(barX);
			this.setY(670);
			this.setIsFollowingMouse(true);
			this.setSpeed(0);
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
}