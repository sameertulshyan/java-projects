package word.analysis;
import java.util.ArrayList;
/**
 * An interface for all classes that are sequentially ordered- will be implemented by Sentence and Word classes
 * @author sameertulshyan
 * @version 0.1
 */
public interface SequentiallyOrdered {
	public OrderedThing getFirst();
	public OrderedThing getLast();
	public ArrayList<OrderedThing> getSequence();
}
