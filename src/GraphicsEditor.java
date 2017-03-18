import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;

/*******************************************************************************
*					      - GRAPHICS EDITOR CLASS -							   *
*******************************************************************************/

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
	private static Shape background = new Shape();
	private static Color fillColor = currentShape.fillColor;
	private static Color outlineColor = currentShape.outlineColor;
	private static double outlineWidth = currentShape.outlineWidth;

	/***************************************************************************
	*						     - MAIN METHOD -							   *
	***************************************************************************/

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

		// help menu
		help();

		// set background
		background.outlineWidth = 0;
		background.type = Shape.Type.POLYGON;
		background.coordinates.add(new Coordinate(0,0));
		background.coordinates.add(new Coordinate(width,0));
		background.coordinates.add(new Coordinate(width,height));
		background.coordinates.add(new Coordinate(0,height));
		graphic.shapes.add(background);

	    // program loop
	    while (true){
			mouseCoord = new Coordinate(StdDraw.mouseX(),StdDraw.mouseY());
			isMousePressed = StdDraw.mousePressed();
	    	isKeyPressed = StdDraw.hasNextKeyTyped();
	    	if (isKeyPressed){
	    		char currentKey = StdDraw.nextKeyTyped();
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

	/***************************************************************************
	*						      - USER CONTROLS -							   *
	***************************************************************************/

	private static void keyPressed(char currentKey){
		currentKey = Character.toUpperCase(currentKey);
		switch (currentKey){
			case 'B': setBackgroundColor();
				break;
			case 'L': setOutlineWidth();
				break;
			case 'F': setFillColor();
				break;
			case 'O': setOutlineColor();
				break;
			case 'E': toggleEditing();
				break;
			case 'S': changeShape();
				break;
			case 'P': printCode();
				break;
			case 'H': help();
				break;
			default: // other keys that don't have typable chars
				if (StdDraw.isKeyPressed(KeyEvent.VK_BACK_SPACE)){
					delete();
				}
				break;
		}
	}

	public static void setBackgroundColor(){
		String hex = "#"+Integer.toHexString(background.fillColor.getRGB()).substring(2).toUpperCase();
		String colorString = JOptionPane.showInputDialog(null, "Type in background color:", hex);
		try {
			background.fillColor = Color.decode(colorString);
		}catch(Exception e){
			if (colorString != null){ // cancel was not pressed
				String message = "Background color could not be set. Would you like to try again?";
				String title = "Could not set background color";
				int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION){
					setBackgroundColor();
				}
			}
		}
		StdDraw.clear();
		graphic.draw();
	}

	public static void setOutlineWidth(){
		String outlineString = JOptionPane.showInputDialog(null, "Type in outline width:", outlineWidth);
		try {
			outlineWidth = Double.parseDouble(outlineString);
		}catch(Exception e){
			if (outlineString != null){ // cancel was not pressed
				String message = "Border could not be set. Would you like to try again?";
				String title = "Could not set outline width";
				int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION){
					setOutlineWidth();
				}
			}
		}
	}

	public static void setFillColor(){
		String hex = "#"+Integer.toHexString(fillColor.getRGB()).substring(2).toUpperCase();
		String colorString = JOptionPane.showInputDialog(null, "Type in fill color:", hex);
		try {
			fillColor = Color.decode(colorString);
		}catch(Exception e){
			if (colorString != null){ // cancel was not pressed
				String message = "Fill color could not be set. Would you like to try again?";
				String title = "Could not set fill color";
				int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION){
					setFillColor();
				}
			}
		}
	}

	public static void setOutlineColor(){
		String hex = "#"+Integer.toHexString(outlineColor.getRGB()).substring(2).toUpperCase();
		String colorString = JOptionPane.showInputDialog(null, "Type in outline color:", hex);
		try {
			outlineColor = Color.decode(colorString);
		}catch(Exception e){
			if (colorString != null){ // cancel was not pressed
				String message = "Outline color could not be set. Would you like to try again?";
				String title = "Could not set outline color";
				int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION){
					setOutlineColor();
				}
			}
		}
	}

	public static void toggleEditing(){
		isEditing = !isEditing;
		String message = "EDITING: "+ Boolean.toString(isEditing).toUpperCase();
		System.out.println(message);
	}

	public static void changeShape(){
		String input = JOptionPane.showInputDialog(null, "Type in SP, CP, or C:");
		if (input == null) {
			return; // cancel
		}
		String shapeString = input.toUpperCase();
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
			String message = "That was not a valid input. Would you like to try again?";
			String title = "Not valid input";
			int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION){
				changeShape();
			}
			break;
		}
	}

	public static void printCode(){
		System.out.println("+------------- GRAPHIC CODE -------------+");
		graphic.printCode();
		System.out.println("+----------------------------------------+");
	}

	public static void help(){
		System.out.println("+------------------HELP------------------+");
		System.out.println("|  (B) Set background color              |");
		System.out.println("|  (E) Toggle editing                    |");
		System.out.println("|  (F) Set fill color                    |");
		System.out.println("|  (L) Set outline width                 |");
		System.out.println("|  (O) Set outline color                 |");
		System.out.println("|  (P) Print graphic code                |");
		System.out.println("|  (S) Change shape type                 |");
		System.out.println("|  (H) Help                              |");
		System.out.println("+----------------------------------------+");
	}

	public static void delete(){
		if (graphic.shapes.size() > 1){ // first shape is background
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

	/***************************************************************************
	*						      - PLOT METHODS -							   *
	***************************************************************************/

	private static void plotCurvedPolygon(){
		if (!wasMousePressed && isMousePressed){
			// first mouse press
			currentShape = new Shape(Shape.Type.POLYGON);
			currentShape.coordinates.add(mouseCoord);
			setCurrentShapeProperties();
			isFirstPoint = false;
		}else if (wasMousePressed && isMousePressed) {
			// dragging
			ArrayList<Coordinate> coords = currentShape.coordinates;
			currentShape.coordinates.add(mouseCoord);

			// draw preview line
			double lastX = currentShape.coordinates.get(currentShape.coordinates.size()-2).x;
			double lastY = currentShape.coordinates.get(currentShape.coordinates.size()-2).y;
			StdDraw.line(lastX, lastY, mouseCoord.x, mouseCoord.y);
		}else if (wasMousePressed && !isMousePressed){
			// first mouse unpress
			graphic.shapes.add(new Shape(currentShape));
			StdDraw.clear();
			graphic.draw();
			isFirstPoint = true;
		}
	}

	private static void plotStraightPolygon(){
		if (wasMousePressed && !isMousePressed){ // unclick
			if (isFirstPoint){
				isFirstPoint = false;

				currentShape = new Shape(Shape.Type.POLYGON);
				currentShape.coordinates.add(mouseCoord);
				setCurrentShapeProperties();
			}else{
				currentShape.coordinates.add(mouseCoord);
				double lastX = currentShape.coordinates.get(currentShape.coordinates.size()-2).x;
				double lastY = currentShape.coordinates.get(currentShape.coordinates.size()-2).y;
				// draw line
				StdDraw.line(lastX, lastY, mouseCoord.x, mouseCoord.y);
			}
		}

		if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER) && isKeyPressed){
			// enter key pressed
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
				currentShape.coordinates.add(new Coordinate(mouseCoord.x,mouseCoord.y));
				setCurrentShapeProperties();
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

	private static void setCurrentShapeProperties(){
		currentShape.fillColor = fillColor;
		currentShape.outlineColor = outlineColor;
		currentShape.outlineWidth = outlineWidth;
	}

	/***************************************************************************
	*						   - MODE ENUMERATION -							   *
	***************************************************************************/

	public enum Mode {
	    CURVED_POLY, STRAIGHT_POLY, CIRCLE
	}

}
