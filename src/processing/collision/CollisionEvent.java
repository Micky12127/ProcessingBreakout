package processing.collision;

import java.util.ArrayList;
import processing.shape.OperateShape;
import processing.EdgeType;

public class CollisionEvent {
	
	private ArrayList<OperateShape> target = new ArrayList<OperateShape>();
	private EdgeType edgeType;
	
	public ArrayList<OperateShape> getTarget() {
		return this.target;
	}
	
	public void addTarget(OperateShape targetShape) {
		this.target.add(targetShape);
	}
	
	public void setEdgeType(EdgeType edgeTypeValue) {
		this.edgeType = edgeTypeValue;
	}
	
	public EdgeType getEdgeType() {
		return this.edgeType;
	}
}
