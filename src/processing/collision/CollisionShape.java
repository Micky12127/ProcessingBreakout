package processing.collision;

import processing.core.PApplet;
import processing.operation.Collisionable;
import processing.shape.MoveShape;
import processing.shape.OperateShape;
import processing.shape.Circle;
import processing.breakoutShape.Block;

public abstract class CollisionShape extends MoveShape implements Collisionable {
	public CollisionShape (PApplet papplet) {
		super(papplet);
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
				ball.setY(670);
				ball.setAngle(30 - (ball.getAngle()));
				ball.setSpeed(8);
			} else if (isBallBarRangeCenter && !isBallBarRangeRight) {
				ball.setY(670);
				ball.setAngle( - (ball.getAngle()));
				ball.setSpeed(7);
			} else if (isBallBarRangeRight) {
				ball.setY(670);
				ball.setAngle(- 30 - (ball.getAngle()));
				ball.setSpeed(8);
			}
		} else if (ball.getY() > papplet.width) {
			ball.setX(bar.getX());
			ball.setY(bar.getY() - ball.getHeightFromCenter());
			ball.setSpeed(0);
			((Circle) ball).setIsFollowingMouse(true);
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
			((Block) block).deleteBlock();
			ball.setAngle(180 - (ball.getAngle()));
		} else if (isLeftVertexBlockRange) {
			((Block) block).deleteBlock();
			ball.setAngle(180 - (ball.getAngle()));
		} else if (isTopVertexBlockRange) {
			((Block) block).deleteBlock();
			ball.setAngle( - (ball.getAngle()));
		} else if (isBottomVertexBlockRange) {
			((Block) block).deleteBlock();
			ball.setAngle( - (ball.getAngle()));
		}
		
	}
}