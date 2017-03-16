import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class GraphicsEditor {
	public static Graphic graphic = new Graphic();
	
	public static void main(String args[]){
	    int width = 600, height = 600;
		StdDraw.setCanvasSize(width, height);  //default is 512 x 512
	    
	    //Set the drawing scale to be 1 pixel per coordinate
	    StdDraw.setXscale(0, width);
	    StdDraw.setYscale(0, height);
	    
	    // control when to show to save running time
	    StdDraw.enableDoubleBuffering();
	    
	    int short_delay = 20;
	    
	    // revolved zig-zag
	    StdDraw.setPenRadius(.001);
	    StdDraw.setPenColor(StdDraw.BOOK_BLUE);
	    
	    Shape firstShape = new Shape();
	    graphic.shapes.add(firstShape);
	    Color shapeColor = firstShape.fillColor;
	    double border = firstShape.border;
	    
	    Mode mode = Mode.CURVED_POLY;
	    boolean editing = false;
		boolean previouslyPressed = false;
		boolean firstPoint = true;
		Shape currentShape = new Shape();
	    
	    // keep running till x is pressed
	    while (!StdDraw.isKeyPressed(KeyEvent.VK_X)){
	    	boolean newKey = StdDraw.hasNextKeyTyped();
	    	if (newKey){
	    		char currentKey = Character.toUpperCase(StdDraw.nextKeyTyped());
	    		
	    		switch (currentKey){
	    		case 'B':
		    		String borderString = JOptionPane.showInputDialog(null, "Type in border width:",border);
		    		border = Double.parseDouble(borderString);
	    			break;
	    		case 'C':
	    			String hex = "#"+Integer.toHexString(shapeColor.getRGB()).substring(2).toUpperCase();
		    		String colorString = JOptionPane.showInputDialog(null, "Type in fill color:",hex);
		    		shapeColor = Color.decode(colorString);
	    			break;
	    		case 'E':
		    		editing = !editing;
		    		System.out.println(editing);
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
	    		default:
	    			if (StdDraw.isKeyPressed(KeyEvent.VK_BACK_SPACE)){
			    		if (graphic.shapes.size() > 0){
			    			Shape lastShape = graphic.shapes.get(graphic.shapes.size()-1);
			    			if (!editing){
				    			graphic.shapes.remove(graphic.shapes.size()-1);
				    		}else if (editing && lastShape.coordinates.size() > 0){
				    			lastShape.coordinates.remove(lastShape.coordinates.size()-1);
				    			graphic.shapes.set(graphic.shapes.size()-1, lastShape);
				    			if  (editing && lastShape.coordinates.size() == 0){
				    				graphic.shapes.remove(graphic.shapes.size()-1);
				    			}
				    		}
			    		}
			    		StdDraw.clear();
			    		graphic.draw();
			    	}
	    			break;
	    		}
	    	}
	    	

    		double newX = StdDraw.mouseX();
    		double newY = StdDraw.mouseY();
    		boolean currentlyPressed = StdDraw.mousePressed();
    		
	    	switch (mode){
	    	case CURVED_POLY:
	    		if (previouslyPressed) {
	        		currentShape.coordinates.add(new Coordinate(newX,newY));

	        		double lastX = currentShape.coordinates.get(currentShape.coordinates.size()-2).x;
	        		double lastY = currentShape.coordinates.get(currentShape.coordinates.size()-2).y;
	        		
	        		// draw line
	        		StdDraw.line(lastX, lastY, newX, newY);
	        		
	        		// if no longer dragging
	        		if (!currentlyPressed){
			    	    graphic.shapes.add(new Shape(currentShape));
	        			StdDraw.clear();
			    		graphic.draw();
	        		}
	        	}else if (currentlyPressed){
	        		currentShape = new Shape(Shape.Type.POLYGON);
	        		currentShape.coordinates.add(new Coordinate(newX,newY));
	        		currentShape.fillColor = shapeColor;
	        		currentShape.border = border;
	        	}
	    		break;
	    	case STRAIGHT_POLY:
        	    if (previouslyPressed && !currentlyPressed){ // unclick
        	    	if (firstPoint){
            	    	firstPoint = false;
            	    	
            	    	currentShape = new Shape(Shape.Type.POLYGON);
            	    	currentShape.coordinates.add(new Coordinate(newX,newY));
            	    	currentShape.fillColor = shapeColor;
            	    	currentShape.border = border;
            	    }else{
    	        		currentShape.coordinates.add(new Coordinate(newX,newY));
    	        		double lastX = currentShape.coordinates.get(currentShape.coordinates.size()-2).x;
    	        		double lastY = currentShape.coordinates.get(currentShape.coordinates.size()-2).y;
    	        		// draw line
    	        		StdDraw.line(lastX, lastY, newX, newY);
            	    }
        	    }
        	    
	    		if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER) && newKey){
	    			firstPoint = true;
		    	    graphic.shapes.add(new Shape(currentShape));
		    		StdDraw.clear();
		    		graphic.draw();
		    	}
	    		
	    		if (!firstPoint){
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
	        		StdDraw.line(lastX, lastY, newX, newY);
        	    }
	    		
	    		break;
	    	case CIRCLE:
	    		if (previouslyPressed && !currentlyPressed){ // unclick
        	    	if (firstPoint){
            	    	firstPoint = false;
            	    	
            	    	currentShape = new Shape(Shape.Type.CIRCLE);
            	    	currentShape.coordinates.add(new Coordinate(newX,newY));
            	    	currentShape.fillColor = shapeColor;
            	    	currentShape.border = border;
            	    }else{
    	        		currentShape.coordinates.add(new Coordinate(newX,newY));
    		    	    graphic.shapes.add(new Shape(currentShape));

    	        		firstPoint = true;
    		    		StdDraw.clear();
    		    		graphic.draw();
            	    }
        	    }else if (!firstPoint){
        	    	StdDraw.clear();
		    		graphic.draw();
		    		double x = currentShape.coordinates.get(0).x;
		    		double y = currentShape.coordinates.get(0).y;
		    		double dx = newX - x;
		    		double dy = newY - y;
		    		double r = Math.sqrt(dx*dx+dy*dy);
        	    	// draw line
	        		StdDraw.circle(x, y, r);
        	    }
	    		break;
	    	default:
	    		break;
	    	}
	    	
	    	previouslyPressed = currentlyPressed;
	    	StdDraw.show();
	    	StdDraw.pause(short_delay);
	    }
	    System.out.println("Done");
	}
	
	public enum Mode {
	    CURVED_POLY, STRAIGHT_POLY, CIRCLE
	}

}
