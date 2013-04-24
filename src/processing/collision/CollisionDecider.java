package processing.collision;

import java.util.ArrayList;
import processing.EdgeType;
import processing.breakoutShape.Bar;
import processing.breakoutShape.Block;
import processing.core.PApplet;
import processing.shape.Circle;
import processing.shape.OperateShape;

public class CollisionDecider {
	private ArrayList<OperateShape> shapes;
	private ArrayList<CollisionListener> listeners;
	private CollisionEvent event;
	private boolean isCollision = false;
	private PApplet papplet;
	
	public CollisionDecider(PApplet p) {
		papplet = p;
		this.shapes = new ArrayList<OperateShape>();
		this.listeners = new ArrayList<CollisionListener>();
	}
	
	public void add(OperateShape shape) {
		this.shapes.add(shape);
	}
	
	public void addListener(CollisionListener listener) {
		this.listeners.add(listener);
	}
	
	public void checkCollision() {
		for (OperateShape shape : shapes) {
			if (shape instanceof Circle) {
				for (OperateShape checkShape : shapes) {
					event = new CollisionEvent();
					if (checkShape instanceof Circle) {
					} else if (checkShape instanceof Block) {
					} else if (checkShape instanceof Bar) {
					}

					if (isCollision) {
						event.addTarget(checkShape);
						event.addTarget(shape);
						
						notifyEvent(event);
						isCollision = false;	
					}
				}
			}
		}
	}
	
	
	
	private void notifyEvent(CollisionEvent event) {
		for (CollisionListener listener : listeners) {
			listener.onCollision(event);
		}
	}
}
