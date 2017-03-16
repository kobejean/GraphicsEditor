import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class Shape {
	public Type type = Type.POLYGON;
	public ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
	public Color fillColor = Color.WHITE;
	public Color outlineColor = Color.BLACK;
	public double border = 0.005;

	public Shape(Type type, ArrayList<Coordinate> coordinates, Color fillColor, Color outlineColor, double border){
		this.type = type;
		this.coordinates = coordinates;
		this.fillColor = fillColor;
		this.outlineColor = outlineColor;
		this.border = border;
	}

	public Shape(Type type, ArrayList<Coordinate> coordinates){
		this.type = type;
		this.coordinates = coordinates;
	}

	public Shape(Type type){
		this.type = type;
	}

	public Shape(Shape another){
		this.type = another.type;
		this.coordinates = new ArrayList<Coordinate>(another.coordinates);
		this.fillColor = new Color(another.fillColor.getRGB());
		this.outlineColor = new Color(another.outlineColor.getRGB());
		this.border = another.border;
	}

	public Shape(){
	}

	public void draw(){
		switch (this.type){
		case POLYGON:
			drawPolygon();
			break;
		case CIRCLE:
			drawCircle();
			break;
		}
	}

	private void drawCircle(){
		if (this.coordinates.size() != 2){
			return;
		}

		// save settings
		Color previousPenColor = StdDraw.getPenColor();
		double previousPenRadius = StdDraw.getPenRadius();

		// calculate coordinates
		double x = this.coordinates.get(0).x;
		double y = this.coordinates.get(0).y;
		double dx = this.coordinates.get(1).x - x;
		double dy = this.coordinates.get(1).y - y;
		double r = Math.sqrt(dx*dx+dy*dy);

		// draw filled polygon
		StdDraw.setPenColor(fillColor);
		StdDraw.filledCircle(x, y, r);
		// draw outline polygon
		StdDraw.setPenRadius(border);
		StdDraw.setPenColor(outlineColor);
		StdDraw.circle(x, y, r);

		// set back to original settings
		StdDraw.setPenRadius(previousPenRadius);
		StdDraw.setPenColor(previousPenColor);
	}

	private void drawPolygon(){
		if (this.coordinates.size() < 2){
			return;
		}

		// save settings
		Color previousPenColor = StdDraw.getPenColor();
		double previousPenRadius = StdDraw.getPenRadius();

		// calculate coordinates
		double[] x = new double[this.coordinates.size()];
		double[] y = new double[this.coordinates.size()];
		for (int i = 0; i < this.coordinates.size(); i++){
			Coordinate coordinate = this.coordinates.get(i);
			x[i] = coordinate.x;
			y[i] = coordinate.y;
		}
		// draw filled polygon
		StdDraw.setPenColor(fillColor);
		StdDraw.filledPolygon(x, y);
		// draw outline polygon
		StdDraw.setPenRadius(border);
		StdDraw.setPenColor(outlineColor);
		StdDraw.polygon(x, y);

		// set back to original settings
		StdDraw.setPenRadius(previousPenRadius);
		StdDraw.setPenColor(previousPenColor);
	}

	public void printCode(){
		switch (this.type){
		case POLYGON:
			printPolygonCode();
			break;
		case CIRCLE:
			printCircleCode();
			break;
		}
	}

	private void printCircleCode(){
		// calculate coordinates
		double x = this.coordinates.get(0).x;
		double y = this.coordinates.get(0).y;
		double dx = this.coordinates.get(1).x - x;
		double dy = this.coordinates.get(1).y - y;
		double r = Math.sqrt(dx*dx+dy*dy);

		String hexFill = "#"+Integer.toHexString(fillColor.getRGB()).substring(2).toUpperCase();
		System.out.println("StdDraw.setPenColor(Color.decode(\""+hexFill+"\"));");
		System.out.println("StdDraw.filledCircle("+x+", "+y+", "+r+");");

		String hexOut = "#"+Integer.toHexString(outlineColor.getRGB()).substring(2).toUpperCase();
		System.out.println("StdDraw.setPenRadius("+border+");");
		System.out.println("StdDraw.setPenColor(Color.decode(\""+hexOut+"\"));");
		System.out.println("StdDraw.circle("+x+", "+y+", "+r+");");
	}

	private void printPolygonCode(){
		if (coordinates.size() < 2){
			return;
		}

		// calculate coordinates
		double[] x = new double[coordinates.size()];
		double[] y = new double[coordinates.size()];
		for (int i = 0; i < coordinates.size(); i++){
			Coordinate coordinate = coordinates.get(i);
			x[i] = coordinate.x;
			y[i] = coordinate.y;
		}
		String xString = "new double[]"+Arrays.toString(x).replace("[", "{").replace("]", "}");
		String yString = "new double[]"+Arrays.toString(y).replace("[", "{").replace("]", "}");

		int r = fillColor.getRed();
		int g = fillColor.getGreen();
		int b = fillColor.getBlue();
		String hex = String.format("#%02X%02X%02X", r, g, b);
		System.out.println("StdDraw.setPenColor(Color.decode(\""+hex+"\"));");
		System.out.println("StdDraw.filledPolygon("+xString+", "+yString+");");

		r = outlineColor.getRed();
		g = outlineColor.getGreen();
		b = outlineColor.getBlue();
		hex = String.format("#%02X%02X%02X", r, g, b);
		System.out.println("StdDraw.setPenRadius("+border+");");
		System.out.println("StdDraw.setPenColor(Color.decode(\""+hex+"\"));");
		System.out.println("StdDraw.polygon("+xString+", "+yString+");");
	}

	/******************************************************************
	 * Shape type enumeration                                         *
	 ******************************************************************/
	public enum Type {
	    POLYGON, CIRCLE
	}
}
