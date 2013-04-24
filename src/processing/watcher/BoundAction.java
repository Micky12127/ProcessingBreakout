package processing.watcher;

import java.util.ArrayList;

import processing.collision.CollisionEvent;
import processing.shape.Circle;
import processing.shape.MoveShape;
import processing.shape.OperateShape;

public class BoundAction implements ShapeAction {
	
	public void handle(CollisionEvent event) {
		ArrayList<OperateShape> shapes = event.getTarget();
		for (MoveShape shape : shapes) {
			if (shape instanceof Circle) {
				switch (event.getEdgeType()) {
				case RIGHT:
					shape.setAngle(180 - (shape.getAngle()));
					break;
				case LEFT:
					shape.setAngle(180 - (shape.getAngle()));
					break;
				case TOP:
					shape.setAngle( - (shape.getAngle()));
					break;
				case BOTTOM:
					shape.setAngle( - (shape.getAngle()));
					break;
				case LEFTSIDEOFTHEBAR:
					shape.setY(670);
					shape.setAngle(30 - (shape.getAngle()));
					shape.setSpeed(8);
				case MIDDLEOFTHEBAR:
					shape.setY(670);
					shape.setAngle(-(shape.getAngle()));
					shape.setSpeed(7);
				case RIGHTSIDEOFTHEBAR:
					shape.setY(670);
					shape.setAngle( - 30 - (shape.getAngle()));
					shape.setSpeed(8);
				}
			}
		}
	}
}