package processing.breakoutShape;

import java.awt.event.MouseEvent;

import processing.core.PApplet;
import processing.shape.Rectangle;

public class Bar extends Rectangle {

	public Bar(PApplet papplet, float _rectWidth, float _rectHeight) {
		super(papplet, _rectWidth, _rectHeight);
	}
	
	public void mouseMoved(MouseEvent e) {
		this.setX(papplet.mouseX);
	}
}