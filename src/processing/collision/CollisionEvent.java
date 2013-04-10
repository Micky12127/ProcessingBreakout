package processing.collision;

import processing.shape.OperateShape;

public class CollisionEvent {
	
	private OperateShape target;
	
	public OperateShape getTarget() {
		return target;
	}
	
	public void setTarget(OperateShape targetShape) {
		this.target = targetShape;
	}
}
