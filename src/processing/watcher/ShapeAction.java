package processing.watcher;

import processing.collision.CollisionEvent;
import processing.shape.MoveShape;

public interface ShapeAction {
	void handle(CollisionEvent event);
}