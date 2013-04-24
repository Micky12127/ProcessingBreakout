package processing.watcher;

import java.util.ArrayList;

import processing.breakoutShape.Block;
import processing.collision.CollisionEvent;
import processing.shape.MoveShape;
import processing.shape.OperateShape;

public class RemoveAction implements ShapeAction {
	
	public void handle(CollisionEvent event) {
		ArrayList<OperateShape> shapes = event.getTarget();
		for (MoveShape shape : shapes) {
			if (shape instanceof Block) {
				((Block) shape).deleteBlock();
			}
		}
	}
}