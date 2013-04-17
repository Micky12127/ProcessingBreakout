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
						collisionBlock(shape, checkShape);
					} else if (checkShape instanceof Bar) {
						collisionBar(shape, checkShape);
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
	
	public void collisionBlock(OperateShape ball, OperateShape block) {
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
			EdgeType edgeTypeValue = EdgeType.RIGHT;
			event.setEdgeType(edgeTypeValue);
			isCollision = true;
		} else if (isLeftVertexBlockRange) {
			EdgeType edgeTypeValue = EdgeType.LEFT;
			event.setEdgeType(edgeTypeValue);
			isCollision = true;		
		} else if (isTopVertexBlockRange) {
			EdgeType edgeTypeValue = EdgeType.TOP;
			event.setEdgeType(edgeTypeValue);
			isCollision = true;		
		} else if (isBottomVertexBlockRange) {
			EdgeType edgeTypeValue = EdgeType.BOTTOM;
			event.setEdgeType(edgeTypeValue);
			isCollision = true;		
		}
	}
	
	public void collisionBar(OperateShape ball, OperateShape bar) {
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
				EdgeType edgeTypeValue = EdgeType.LEFTSIDEOFTHEBAR;
				event.setEdgeType(edgeTypeValue);
				isCollision = true;
			} else if (isBallBarRangeCenter && !isBallBarRangeRight) {
				EdgeType edgeTypeValue = EdgeType.MIDDLEOFTHEBAR;
				event.setEdgeType(edgeTypeValue);
				isCollision = true;
			} else if (isBallBarRangeRight) {
				EdgeType edgeTypeValue = EdgeType.RIGHTSIDEOFTHEBAR;
				event.setEdgeType(edgeTypeValue);
				isCollision = true;
			}
		} else if (ball.getY() > papplet.width) {
			ball.setX(bar.getX());
			ball.setY(bar.getY() - ball.getHeightFromCenter());
			ball.setSpeed(0);
			((Circle) ball).setIsFollowingMouse(true);
		}
	}
	
	private void notifyEvent(CollisionEvent event) {
		for (CollisionListener listener : listeners) {
			listener.onCollision(event);
		}
	}
}
