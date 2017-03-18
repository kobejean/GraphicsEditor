import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;

/*******************************************************************************
*                          - GRAPHICS EDITOR CLASS -                           *
*******************************************************************************/

public class GraphicsEditor {
    public static Graphic graphic = new Graphic();

    // state variables
    private static Mode _mode = Mode.CURVED_POLY;
    private static boolean _isEditing = false;
    private static boolean _isMousePressed = false;
    private static boolean _wasMousePressed = false;
    private static boolean _isFirstPoint = true;
    private static boolean _isKeyPressed = false;
    private static Coordinate _mouseCoord = new Coordinate(0,0);
    private static Shape _currentShape = new Shape();
    private static Shape _background = new Shape();
    private static Color _fillColor = _currentShape.fillColor;
    private static Color _outlineColor = _currentShape.outlineColor;
    private static Color _previewColor = StdDraw.BOOK_BLUE;
    private static double _previewLineWidth = 0.001;
    private static double _outlineWidth = _currentShape.outlineWidth;

    /***************************************************************************
    *                             - MAIN METHOD -                              *
    ***************************************************************************/

    public static void main(String args[]){
        int width = 600, height = 600;
        StdDraw.setCanvasSize(width, height);  //default is 512 x 512

        //Set the drawing scale to be 1 pixel per coordinate
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        // control when to show to save running time
        StdDraw.enableDoubleBuffering();

        // temporary draw settings
        StdDraw.setPenRadius(_previewLineWidth);
        StdDraw.setPenColor(_previewColor);

        // help menu
        help();

        // set background
        _background.outlineWidth = 0;
        _background.type = Shape.Type.POLYGON;
        _background.coordinates.add(new Coordinate(0,0));
        _background.coordinates.add(new Coordinate(width,0));
        _background.coordinates.add(new Coordinate(width,height));
        _background.coordinates.add(new Coordinate(0,height));
        graphic.shapes.add(_background);

        // program loop
        while (true){
            _mouseCoord = new Coordinate(StdDraw.mouseX(),StdDraw.mouseY());
            _isMousePressed = StdDraw.mousePressed();
            _isKeyPressed = StdDraw.hasNextKeyTyped();
            if (_isKeyPressed){
                char currentKey = StdDraw.nextKeyTyped();
                keyPressed(currentKey);
            }

            plot();
            StdDraw.show();
            StdDraw.pause(20);
            _wasMousePressed = _isMousePressed;
        }
    }

    /***************************************************************************
    *                           - USER CONTROLS -                              *
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
            case 'S': setShape();
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
        String hex = Integer.toHexString(_background.fillColor.getRGB());
        hex = "#" + hex.substring(2).toUpperCase();
        String inputMsg = "Type in background color:";
        String colorString = JOptionPane.showInputDialog(null, inputMsg, hex);
        try {
            _background.fillColor = Color.decode(colorString);
        }catch(Exception e){
            if (colorString != null){ // cancel was not pressed
                String confirmMsg = "Bad input. Would you like to try again?";
                String title = "Could not set background color";
                int option = JOptionPane.YES_NO_OPTION;
                int reply = JOptionPane.showConfirmDialog(null, confirmMsg, title, option);
                if (reply == JOptionPane.YES_OPTION){
                    setBackgroundColor();
                }
            }
        }
        redraw();
    }

    public static void setOutlineWidth(){
        String inputMsg = "Type in outline width:";
        String outlineString = JOptionPane.showInputDialog(null, inputMsg, _outlineWidth);
        try {
            _outlineWidth = Double.parseDouble(outlineString);
        }catch(Exception e){
            if (outlineString != null){ // cancel was not pressed
                String message = "Bad input. Would you like to try again?";
                String title = "Could not set outline width";
                int option = JOptionPane.YES_NO_CANCEL_OPTION;
                int reply = JOptionPane.showConfirmDialog(null, message, title, option);
                if (reply == JOptionPane.YES_OPTION){
                    setOutlineWidth();
                }
            }
        }
    }

    public static void setFillColor(){
        String hex = Integer.toHexString(_fillColor.getRGB());
        hex = "#" + hex.substring(2).toUpperCase();
        String inputMsg = "Type in fill color:";
        String colorString = JOptionPane.showInputDialog(null, inputMsg, hex);
        try {
            _fillColor = Color.decode(colorString);
        }catch(Exception e){
            if (colorString != null){ // cancel was not pressed
                String message = "Bad input. Would you like to try again?";
                String title = "Could not set fill color";
                int option = JOptionPane.YES_NO_CANCEL_OPTION;
                int reply = JOptionPane.showConfirmDialog(null, message, title, option);
                if (reply == JOptionPane.YES_OPTION){
                    setFillColor();
                }
            }
        }
    }

    public static void setOutlineColor(){
        String hex = Integer.toHexString(_outlineColor.getRGB());
        hex = "#" + hex.substring(2).toUpperCase();
        String inputMsg = "Type in outline color:";
        String colorString = JOptionPane.showInputDialog(null, inputMsg, hex);
        try {
            _outlineColor = Color.decode(colorString);
        }catch(Exception e){
            if (colorString != null){ // cancel was not pressed
                String message = "Bad input. Would you like to try again?";
                String title = "Could not set outline color";
                int option = JOptionPane.YES_NO_CANCEL_OPTION;
                int reply = JOptionPane.showConfirmDialog(null, message, title, option);
                if (reply == JOptionPane.YES_OPTION){
                    setOutlineColor();
                }
            }
        }
    }

    public static void toggleEditing(){
        _isEditing = !_isEditing;
        String message = "EDITING: "+ Boolean.toString(_isEditing).toUpperCase();
        System.out.println(message);
    }

    public static void setShape(){
        String input = JOptionPane.showInputDialog(null, "Type in SP, CP, or C:");
        if (input == null) {
            return; // cancel
        }
        String shapeString = input.toUpperCase();
        switch (shapeString){
            case "SP":
                _mode = Mode.STRAIGHT_POLY;
                break;
            case "CP":
                _mode = Mode.CURVED_POLY;
                break;
            case "C":
                _mode = Mode.CIRCLE;
                break;
            default:
                String message = "Bad input. Would you like to try again?";
                String title = "Not valid input";
                int option = JOptionPane.YES_NO_CANCEL_OPTION;
                int reply = JOptionPane.showConfirmDialog(null, message, title, option);
                if (reply == JOptionPane.YES_OPTION){
                    setShape();
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
            if (_isEditing && coords.size() > 1){
                coords.remove(coords.size()-1);
            }else{
                graphic.shapes.remove(lastShape);
            }
        }
        redraw();
    }

    /***************************************************************************
    *                              - PLOT METHODS -                            *
    ***************************************************************************/

    private static void plot(){
        switch (_mode){
            case CURVED_POLY: plotCurvedPolygon();
                break;
            case STRAIGHT_POLY: plotStraightPolygon();
                break;
            case CIRCLE: plotCircle();
                break;
            default:
                break;
        }
    }

    private static void plotCurvedPolygon(){
        if (!_wasMousePressed && _isMousePressed){
            // first mouse press
            _currentShape = new Shape(Shape.Type.POLYGON);
            _currentShape.coordinates.add(_mouseCoord);
            setCurrentShapeProperties();
            _isFirstPoint = false;
        }else if (_wasMousePressed && _isMousePressed) {
            // dragging
            ArrayList<Coordinate> coords = _currentShape.coordinates;
            coords.add(_mouseCoord);

            // draw preview line
            double lastX = coords.get(coords.size()-2).x;
            double lastY = coords.get(coords.size()-2).y;
            StdDraw.line(lastX, lastY, _mouseCoord.x, _mouseCoord.y);
        }else if (_wasMousePressed && !_isMousePressed){
            // first mouse unpress
            graphic.shapes.add(new Shape(_currentShape));
            redraw();
            _isFirstPoint = true;
        }
    }

    private static void plotStraightPolygon(){
        if (_wasMousePressed && !_isMousePressed){ // unclick
            if (_isFirstPoint){
                _isFirstPoint = false;

                _currentShape = new Shape(Shape.Type.POLYGON);
                _currentShape.coordinates.add(_mouseCoord);
                setCurrentShapeProperties();
            }else{
                ArrayList<Coordinate> coords = _currentShape.coordinates;
                coords.add(_mouseCoord);
                double lastX = coords.get(coords.size()-2).x;
                double lastY = coords.get(coords.size()-2).y;
                // draw line
                StdDraw.line(lastX, lastY, _mouseCoord.x, _mouseCoord.y);
            }
        }

        if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER) && _isKeyPressed){
            // enter key pressed
            _isFirstPoint = true;
            graphic.shapes.add(new Shape(_currentShape));
            redraw();
        }

        if (!_isFirstPoint){
            redraw();

            // calculate coordinates
            ArrayList<Coordinate> coords = _currentShape.coordinates;
            double[] x = new double[coords.size()];
            double[] y = new double[coords.size()];
            for (int i = 0; i < coords.size(); i++){
                Coordinate coord = coords.get(i);
                x[i] = coord.x;
                y[i] = coord.y;

                if (i>0){
                    StdDraw.line(x[i-1], y[i-1], x[i], y[i]);
                }
            }

            double lastX = coords.get(coords.size()-1).x;
            double lastY = coords.get(coords.size()-1).y;
            // draw line
            StdDraw.line(lastX, lastY, _mouseCoord.x, _mouseCoord.y);
        }
    }

    private static void plotCircle(){
        if (_wasMousePressed && !_isMousePressed){ // unclick
            if (_isFirstPoint){
                _currentShape = new Shape(Shape.Type.CIRCLE);
                _currentShape.coordinates.add(_mouseCoord);
                setCurrentShapeProperties();
                _isFirstPoint = false;
            }else{
                _currentShape.coordinates.add(_mouseCoord);
                graphic.shapes.add(new Shape(_currentShape));
                redraw();
                _isFirstPoint = true;
            }
        }else if (!_isFirstPoint){
            redraw();
            // draw preview circle
            double x = _currentShape.coordinates.get(0).x;
            double y = _currentShape.coordinates.get(0).y;
            double dx = _mouseCoord.x - x;
            double dy = _mouseCoord.y - y;
            double r = Math.sqrt(dx*dx+dy*dy);
            StdDraw.circle(x, y, r);
        }
    }

    private static void setCurrentShapeProperties(){
        _currentShape.fillColor = _fillColor;
        _currentShape.outlineColor = _outlineColor;
        _currentShape.outlineWidth = _outlineWidth;
    }

    private static void redraw(){
        StdDraw.clear();
        graphic.draw();
    }

    /***************************************************************************
    *                           - MODE ENUMERATION -                           *
    ***************************************************************************/

    public enum Mode {
        CURVED_POLY, STRAIGHT_POLY, CIRCLE
    }

}
