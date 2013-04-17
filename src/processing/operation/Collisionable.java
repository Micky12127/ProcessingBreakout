package processing.operation;

import processing.shape.OperateShape;

public interface Collisionable {
	void collisionBar(OperateShape ball, OperateShape bar);
	void collisionBlock(OperateShape ball, OperateShape block);
}
