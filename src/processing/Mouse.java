package processing;

import java.util.ArrayList;
import processing.core.*;
import processing.shape.Circle;
import processing.shape.OperateShape;

public class Mouse extends PApplet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<OperateShape> shapes = new ArrayList<OperateShape>();
	
	// 図形の数
	private float randomShapesNum = random(2, 50);
	private int shapesNum = (int) randomShapesNum;
	
	public void setup() {
		size(1200, 700);
		smooth();
		frameRate(1000);
		for (int i = 0; i < shapesNum; i++) {
			OperateShape os = new Circle(this, 50);
			os.setX(random(50, 1150));
			os.setY(random(50, 650));
			os.setColor(color(random(0, 255), random(0, 255), random(0, 255)));
			os.setClickedShapeFlag(0);
			shapes.add(os);
		}
	}
	public void draw() {
		clearShape();
		
		for (OperateShape os : shapes) {
			if (os.getSpeed() > 0) {
				os.move();
			} else {
				os.display();
			}
		}
	}
	
	public void clearShape() {
		stroke(255, 255, 255);
		fill(255, 255, 255);
		rect(0, 0, width, height);
	}
}
