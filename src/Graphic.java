import java.util.ArrayList;

public class Graphic {
	public ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	public Graphic(ArrayList<Shape> shapes){
		this.shapes = shapes;
	}
	
	public Graphic(){
		
	}
	
	public void draw(){
		for (Shape shape : shapes){
			shape.draw();
		}
	}
	
	public void printCode(){
		for (Shape shape : shapes){
			shape.printCode();
		}
	}

}
