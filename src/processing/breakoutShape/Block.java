package processing.breakoutShape;

import processing.core.PApplet;
import processing.shape.Rectangle;

public class Block extends Rectangle {

	public Block(PApplet papplet, float _rectWidth, float _rectHeight) {
		super(papplet, _rectWidth, _rectHeight);
	}
	
	public void deleteBlock() {
		this.setX(-1000);
		this.setY(-1000);
	}
}