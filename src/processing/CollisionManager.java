package processing;

import java.util.ArrayList;

public class CollisionManager {
	private ArrayList<OperateShape> shapes;
	private ArrayList<CollisionListener> listeners;

	public CollisionManager() {
		this.shapes = new ArrayList<OperateShape>();
		this.listeners = new ArrayList<CollisionListener>();
	}

	public void add(OperateShape shape) {
		shapes.add(shape);
	}
	
	public void addListener(CollisionListener listener) {
		this.listeners.add(listener);
	}

	public void checkCollision() {
		for (OperateShape shape : shapes) {
			boolean isCollision = false;
			// collision check
			
			if (isCollision) {
				// dispatch event
				
				CollisionEvent event = new CollisionEvent();
				event.setTarget(shape);
				
				notifyEvent(event);
			}
		}
	}

	private void notifyEvent(CollisionEvent event) {
		for (CollisionListener listener : listeners) {
			listener.onCollision(event);
		}
	}
}
