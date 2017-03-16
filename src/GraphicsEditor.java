import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class GraphicsEditor {
	public static Graphic graphic = new Graphic();

	// state variables
	private static Mode mode = Mode.CURVED_POLY;
	private static boolean isEditing = false;
	private static boolean isMousePressed = false;
	private static boolean wasMousePressed = false;
	private static boolean isFirstPoint = true;
	private static boolean isKeyPressed = false;
	private static Coordinate mouseCoord = new Coordinate(0,0);
	private static Shape currentShape = new Shape();
	private static Color shapeColor = currentShape.fillColor;
	private static double border = currentShape.border;

	public static void main(String args[]){
	    int width = 600, height = 600;
		StdDraw.setCanvasSize(width, height);  //default is 512 x 512

	    //Set the drawing scale to be 1 pixel per coordinate
	    StdDraw.setXscale(0, width);
	    StdDraw.setYscale(0, height);

	    // control when to show to save running time
	    StdDraw.enableDoubleBuffering();

	    int short_delay = 20;

	    // temporary draw settings
	    StdDraw.setPenRadius(.001);
	    StdDraw.setPenColor(StdDraw.BOOK_BLUE);

		keyPressed('H'); // help menu

	    // program loop
	    while (true){
			mouseCoord = new Coordinate(StdDraw.mouseX(),StdDraw.mouseY());
			isMousePressed = StdDraw.mousePressed();
	    	isKeyPressed = StdDraw.hasNextKeyTyped();
	    	if (isKeyPressed){
	    		char currentKey = Character.toUpperCase(StdDraw.nextKeyTyped());
				keyPressed(currentKey);
	    	}

	    	switch (mode){
	    	case CURVED_POLY: plotCurvedPolygon();
	    		break;
	    	case STRAIGHT_POLY: plotStraightPolygon();
	    		break;
	    	case CIRCLE: plotCircle();
	    		break;
	    	default:
	    		break;
	    	}
	    	StdDraw.show();
	    	StdDraw.pause(short_delay);
			wasMousePressed = isMousePressed;
	    }
	}

	private static void keyPressed(char currentKey){
		switch (currentKey){
		case 'B':
			String borderString = JOptionPane.showInputDialog(null, "Type in border width:",border);
			border = Double.parseDouble(borderString);
			break;
		case 'C':
			String hex = "#"+Integer.toHexString(shapeColor.getRGB()).substring(2).toUpperCase();
			String colorString = JOptionPane.showInputDialog(null, "Type in fill color:", hex);
			shapeColor = Color.decode(colorString);
			break;
		case 'E':
			isEditing = !isEditing;
			break;
		case 'S':
			String shapeString = JOptionPane.showInputDialog(null, "Type in SP, CP, or C:");
			shapeString = shapeString.toUpperCase();
			switch (shapeString){
			case "SP":
				mode = Mode.STRAIGHT_POLY;
				break;
			case "CP":
				mode = Mode.CURVED_POLY;
				break;
			case "C":
				mode = Mode.CIRCLE;
				break;
			default:
				break;
			}
			break;
		case 'P':
			graphic.printCode();
			break;
		case 'H':
			System.out.println("+------------------HELP------------------+");
			System.out.println("|  (B) Set border width                  |");
			System.out.println("|  (D) Set fill color                    |");
			System.out.println("|  (E) Toggle editing                    |");
			System.out.println("|  (S) Change shape type                 |");
			System.out.println("|  (P) Print graphic code                |");
			System.out.println("|  (H) Help                              |");
			System.out.println("+----------------------------------------+");
			break;
		default:
			if (StdDraw.isKeyPressed(KeyEvent.VK_BACK_SPACE)){
				// delete shape/point
				if (graphic.shapes.size() > 0){
					Shape lastShape = graphic.shapes.get(graphic.shapes.size()-1);
					ArrayList<Coordinate> coords = lastShape.coordinates;
					if (isEditing && coords.size() > 1){
						coords.remove(coords.size()-1);
					}else{
						graphic.shapes.remove(lastShape);
					}
				}
				StdDraw.clear();
				graphic.draw();

			}
			break;
		}
	}

	private static void plotCurvedPolygon(){
		if (isMousePressed && isFirstPoint){
			isFirstPoint = false;
			currentShape = new Shape(Shape.Type.POLYGON);
			currentShape.coordinates.add(mouseCoord);
			currentShape.fillColor = shapeColor;
			currentShape.border = border;
		}else if (wasMousePressed) {
			currentShape.coordinates.add(mouseCoord);

			double lastX = currentShape.coordinates.get(currentShape.coordinates.size()-2).x;
			double lastY = currentShape.coordinates.get(currentShape.coordinates.size()-2).y;

			// draw line
			StdDraw.line(lastX, lastY, mouseCoord.x, mouseCoord.y);

			// if no longer dragging
			if (!isMousePressed){
				graphic.shapes.add(new Shape(currentShape));
				StdDraw.clear();
				graphic.draw();
				isFirstPoint = true;
			}
		}
	}

	private static void plotStraightPolygon(){
		if (wasMousePressed && !isMousePressed){ // unclick
			if (isFirstPoint){
				isFirstPoint = false;

				currentShape = new Shape(Shape.Type.POLYGON);
				currentShape.coordinates.add(mouseCoord);
				currentShape.fillColor = shapeColor;
				currentShape.border = border;
			}else{
				currentShape.coordinates.add(mouseCoord);
				double lastX = currentShape.coordinates.get(currentShape.coordinates.size()-2).x;
				double lastY = currentShape.coordinates.get(currentShape.coordinates.size()-2).y;
				// draw line
				StdDraw.line(lastX, lastY, mouseCoord.x, mouseCoord.y);
			}
		}

		if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER) && isKeyPressed){
			isFirstPoint = true;
			graphic.shapes.add(new Shape(currentShape));
			StdDraw.clear();
			graphic.draw();
		}

		if (!isFirstPoint){
			StdDraw.clear();
			graphic.draw();

			// calculate coordinates
			double[] x = new double[currentShape.coordinates.size()];
			double[] y = new double[currentShape.coordinates.size()];
			for (int i = 0; i < currentShape.coordinates.size(); i++){
				Coordinate coordinate = currentShape.coordinates.get(i);
				x[i] = coordinate.x;
				y[i] = coordinate.y;

				if (i>0){
					StdDraw.line(x[i-1], y[i-1], x[i], y[i]);
				}
			}

			double lastX = currentShape.coordinates.get(currentShape.coordinates.size()-1).x;
			double lastY = currentShape.coordinates.get(currentShape.coordinates.size()-1).y;
			// draw line
			StdDraw.line(lastX, lastY, mouseCoord.x, mouseCoord.y);
		}
	}

	private static void plotCircle(){
		if (wasMousePressed && !isMousePressed){ // unclick
			if (isFirstPoint){
				currentShape = new Shape(Shape.Type.CIRCLE);
				currentShape.fillColor = shapeColor;
				currentShape.border = border;
				currentShape.coordinates.add(new Coordinate(mouseCoord.x,mouseCoord.y));
				isFirstPoint = false;
			}else{
				currentShape.coordinates.add(new Coordinate(mouseCoord.x,mouseCoord.y));
				graphic.shapes.add(new Shape(currentShape));
				StdDraw.clear();
				graphic.draw();
				isFirstPoint = true;
			}
		}else if (!isFirstPoint){
			StdDraw.clear();
			graphic.draw();

			// draw preview circle
			double x = currentShape.coordinates.get(0).x;
			double y = currentShape.coordinates.get(0).y;
			double dx = mouseCoord.x - x;
			double dy = mouseCoord.y - y;
			double r = Math.sqrt(dx*dx+dy*dy);
			StdDraw.circle(x, y, r);
		}
	}

	public enum Mode {
	    CURVED_POLY, STRAIGHT_POLY, CIRCLE
	}

}
