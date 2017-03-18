import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

/*******************************************************************************
*                                - SHAPE CLASS -                               *
*******************************************************************************/

public class Shape {

    /***************************************************************************
    *                           - INSTANCE VARIABLES -                         *
    ***************************************************************************/

    public Type type = Type.POLYGON;
    public ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
    public Color fillColor = Color.WHITE;
    public Color outlineColor = Color.BLACK;
    public double outlineWidth = 0.005;

    /***************************************************************************
    *                              - CONSTRUCTORS -                            *
    ***************************************************************************/

    public Shape(Type type, ArrayList<Coordinate> coordinates,
                  Color fillColor, Color outlineColor){
        this.type = type;
        this.coordinates = coordinates;
        this.fillColor = fillColor;
        this.outlineColor = outlineColor;
    }

    public Shape(Type type, ArrayList<Coordinate> coordinates){
        this.type = type;
        this.coordinates = coordinates;
    }

    public Shape(Type type){
        this.type = type;
    }

    // copy shape
    public Shape(Shape another){
        this.type = another.type;
        this.coordinates = new ArrayList<Coordinate>(another.coordinates);
        this.fillColor = new Color(another.fillColor.getRGB());
        this.outlineColor = new Color(another.outlineColor.getRGB());
        this.outlineWidth = another.outlineWidth;
    }

    public Shape(){
    }

    /***************************************************************************
    *                              - DRAW METHODS -                            *
    ***************************************************************************/

    public void draw(){
        switch (this.type){
        case POLYGON: drawPolygon();
            break;
        case CIRCLE: drawCircle();
            break;
        }
    }

    private void drawCircle(){
        if (this.coordinates.size() != 2){
            return; // should only have 2 points
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
        if (outlineWidth > 0){
            StdDraw.setPenRadius(outlineWidth);
            StdDraw.setPenColor(outlineColor);
            StdDraw.circle(x, y, r);
        }


        // set back to original settings
        StdDraw.setPenRadius(previousPenRadius);
        StdDraw.setPenColor(previousPenColor);
    }

    private void drawPolygon(){
        if (this.coordinates.size() < 2){
            return; // no need to draw
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
        if (outlineWidth > 0){
            StdDraw.setPenRadius(outlineWidth);
            StdDraw.setPenColor(outlineColor);
            StdDraw.polygon(x, y);
        }

        // set back to original settings
        StdDraw.setPenRadius(previousPenRadius);
        StdDraw.setPenColor(previousPenColor);
    }

    /***************************************************************************
    *                           - PRINT CODE METHODS -                         *
    ***************************************************************************/

    public void printCode(){
        switch (this.type){
        case POLYGON: printPolygonCode();
            break;
        case CIRCLE: printCircleCode();
            break;
        }
    }

    private void printCircleCode(){
        if (this.coordinates.size() != 2){
            return; // should only have 2 points
        }

        // calculate coordinates
        double x = this.coordinates.get(0).x;
        double y = this.coordinates.get(0).y;
        double dx = this.coordinates.get(1).x - x;
        double dy = this.coordinates.get(1).y - y;
        double r = Math.sqrt(dx*dx+dy*dy);

        // hex colors
        String hexFill = Integer.toHexString(fillColor.getRGB());
        hexFill = "#" + hexFill.substring(2).toUpperCase();
        String hexOutline = Integer.toHexString(outlineColor.getRGB());
        hexOutline = "#" + hexOutline.substring(2).toUpperCase();

        System.out.println("StdDraw.setPenColor(Color.decode(\""+hexFill+"\"));");
        System.out.println("StdDraw.filledCircle("+x+", "+y+", "+r+");");
        if (outlineWidth > 0){
            System.out.println("StdDraw.setPenRadius("+outlineWidth+");");
            System.out.println("StdDraw.setPenColor(Color.decode(\""+hexOutline+"\"));");
            System.out.println("StdDraw.circle("+x+", "+y+", "+r+");");
        }
    }

    private void printPolygonCode(){
        if (coordinates.size() < 2){
            return; // we don't need to draw this
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

        // hex colors
        String hexFill = Integer.toHexString(fillColor.getRGB());
        hexFill = "#" + hexFill.substring(2).toUpperCase();
        String hexOutline = Integer.toHexString(outlineColor.getRGB());
        hexOutline = "#" + hexOutline.substring(2).toUpperCase();

        System.out.println("StdDraw.setPenColor(Color.decode(\""+hexFill+"\"));");
        System.out.println("StdDraw.filledPolygon("+xString+", "+yString+");");
        System.out.println("StdDraw.setPenRadius("+outlineWidth+");");
        System.out.println("StdDraw.setPenColor(Color.decode(\""+hexOutline+"\"));");
        System.out.println("StdDraw.polygon("+xString+", "+yString+");");
    }

    /***************************************************************************
    *                       - SHAPE TYPE ENUMERATION -                         *
    ***************************************************************************/

    public enum Type {
        POLYGON, CIRCLE
    }
}
