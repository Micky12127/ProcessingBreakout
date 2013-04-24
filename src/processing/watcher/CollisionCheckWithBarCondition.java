package processing.watcher;

import java.util.ArrayList;
import processing.EdgeType;
import processing.breakoutShape.Block;
import processing.breakoutShape.Bar;
import processing.collision.CollisionEvent;
import processing.shape.Circle;
import processing.shape.OperateShape;

public class CollisionCheckWithBarCondition implements ShapeCondition {
	
	private boolean isCollision = false;
	private EdgeType edgeType;
	private CollisionEvent event;
	private ArrayList<ShapeAction> actions = new ArrayList<ShapeAction>();
	
	
	public void addAction(ShapeAction action) {
		this.actions.add(action);
	}
	
	public void checkCondition(ArrayList<OperateShape> shapes) {
		event = new CollisionEvent();
		for (OperateShape ball : shapes) {
			if (ball instanceof Circle) {
				for (OperateShape bar : shapes) {
					event = new CollisionEvent();
					if (bar instanceof Bar) {
						checkCollisionBar(ball, bar);
						if (isCollision) {
							event.addTarget(ball);
							event.addTarget(bar);
							
							for (ShapeAction action : actions) {
								action.handle(event);
							}
							
							isCollision = false;
						}
					}
				}
			}
		}
	}

	private void checkCollisionBar(OperateShape ball, OperateShape bar) {
		boolean isBallXBarRange = ball.getX() >= bar.getX() - bar.getWidthFromCenter()
				&& ball.getX() <= bar.getX() + bar.getWidthFromCenter();
		boolean isBallYBarRange = ball.getY() >= bar.getY() - bar.getHeightFromCenter()
				&& ball.getY() <= bar.getY() + bar.getHeightFromCenter();
		boolean isBallBarRangeLeft = ball.getX() < (((bar.getWidthFromCenter() * 2) / 3)
				+ (bar.getX() - bar.getWidthFromCenter()));
		boolean isBallBarRangeCenter = ball.getX() >= (((bar.getWidthFromCenter() * 2) / 3)
				+ (bar.getX() - bar.getWidthFromCenter()));
		boolean isBallBarRangeRight = ball.getX() >= ((((bar.getWidthFromCenter() * 2) / 3) * 2)
				+ (bar.getX() - bar.getWidthFromCenter()));
		
		if (isBallXBarRange && isBallYBarRange) {
			if (isBallBarRangeLeft) {
				EdgeType edgeType = EdgeType.LEFTSIDEOFTHEBAR;
				event.setEdgeType(edgeType);
				isCollision = true;
			} else if (isBallBarRangeCenter && !isBallBarRangeRight) {
				EdgeType edgeType = EdgeType.MIDDLEOFTHEBAR;
				event.setEdgeType(edgeType);
				isCollision = true;
			} else if (isBallBarRangeRight) {
				EdgeType edgeType = EdgeType.RIGHTSIDEOFTHEBAR;
				event.setEdgeType(edgeType);
				isCollision = true;
			}
		}
	}
}
