import java.util.*;

public class Stack implements StackInterface {

	private ArrayList<Point> stack;

	public Stack(){
		this.stack = new ArrayList<Point>();
	}
	public Point pop(){
		return this.stack.remove( (this.stack.size() -1) ) ;
	}
	public void push(Point value){
		this.stack.add(value);
	}
	public Point peek(){
		return this.stack.get(this.stack.size() -1);
	}

	public String toString(){
		return this.stack.toString();
	}
}