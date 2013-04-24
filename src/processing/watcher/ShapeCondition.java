package processing.watcher;

import java.util.ArrayList;
import processing.shape.OperateShape;

public interface ShapeCondition {
	void checkCondition(ArrayList<OperateShape> shapes);
	void addAction(ShapeAction action);
}
