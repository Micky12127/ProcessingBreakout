package processing.watcher;

import java.util.ArrayList;
import processing.shape.OperateShape;

public class ShapeWatcher {
	
	private ArrayList<OperateShape> shapes;
	private ArrayList<ShapeCondition> conditions;
	
	public ShapeWatcher() {
		this.shapes = new ArrayList<OperateShape>();
		this.conditions = new ArrayList<ShapeCondition>();
	}
	
	public void addWatchShape(OperateShape shape) {
		this.shapes.add(shape);
	}
	
	public void addCondition(ShapeCondition condition) {
		this.conditions.add(condition);
	}
	
	public void checkCollision() {
		for (ShapeCondition condition : conditions) {
			condition.checkCondition(shapes);
		}
	}
}
