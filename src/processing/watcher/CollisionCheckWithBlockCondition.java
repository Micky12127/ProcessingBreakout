package processing.watcher;

import java.util.ArrayList;

import processing.EdgeType;
import processing.breakoutShape.Block;
import processing.collision.CollisionEvent;
import processing.shape.Circle;
import processing.shape.OperateShape;

public class CollisionCheckWithBlockCondition implements ShapeCondition {
	
	private boolean isCollision = false;
	private EdgeType edgeType;
	private CollisionEvent event;
	private ArrayList<ShapeAction> actions = new ArrayList<ShapeAction>();
	
	public void addAction(ShapeAction action) {
		this.actions.add(action);
	}

	public void checkCondition(ArrayList<OperateShape> shapes) {
		for (OperateShape ball : shapes) {
			if (ball instanceof Circle) {
				for (OperateShape block : shapes) {
					event = new CollisionEvent();
					if (block instanceof Block) {
						checkCollisionBlock(ball, block);
						
						if (isCollision) {
							event.addTarget(ball);
							event.addTarget(block);
							
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
	
	private void checkCollisionBlock(OperateShape ball, OperateShape block) {
		boolean isRightVertexBlockRange =
				ball.getRightVertex() >= block.getX() - block.getWidthFromCenter()
				&& ball.getRightVertex() <= block.getX() + block.getWidthFromCenter()
				&& ball.getY() >= block.getY() - block.getHeightFromCenter()
				&& ball.getY() <= block.getY() + block.getHeightFromCenter();
				
		boolean isLeftVertexBlockRange =
				ball.getLeftVertex() >= block.getX() - block.getWidthFromCenter()
				&& ball.getLeftVertex() <= block.getX() + block.getWidthFromCenter()
				&& ball.getY() >= block.getY() - block.getHeightFromCenter()
				&& ball.getY() <= block.getY() + block.getHeightFromCenter();
				
		boolean isTopVertexBlockRange =
				ball.getTopVertex() >= block.getY() - block.getHeightFromCenter()
				&& ball.getTopVertex() <= block.getY() + block.getHeightFromCenter()
				&& ball.getX() >= block.getX() - block.getWidthFromCenter()
				&& ball.getX() <= block.getX() + block.getWidthFromCenter();
				
		boolean isBottomVertexBlockRange =
				ball.getBottomVertex() >= block.getY() - block.getHeightFromCenter()
				&& ball.getBottomVertex() <= block.getY() + block.getHeightFromCenter()
				&& ball.getX() >= block.getX() - block.getWidthFromCenter()
				&& ball.getX() <= block.getX() + block.getWidthFromCenter();
				
		if (isRightVertexBlockRange) {
			EdgeType edgeType = EdgeType.RIGHT;
			event.setEdgeType(edgeType);
			isCollision = true;
		} else if (isLeftVertexBlockRange) {
			EdgeType edgeType = EdgeType.LEFT;
			event.setEdgeType(edgeType);
			isCollision = true;
		} else if (isTopVertexBlockRange) {
			EdgeType edgeType = EdgeType.TOP;
			event.setEdgeType(edgeType);
			isCollision = true;
		} else if (isBottomVertexBlockRange) {
			EdgeType edgeType = EdgeType.BOTTOM;
			event.setEdgeType(edgeType);
			isCollision = true;
		}
	}

}