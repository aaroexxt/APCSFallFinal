import java.util.*;
import java.lang.reflect.*;


public class GameEngine {
    //ArrayList of generic Objects so that user can pass in any type of shape
    private ArrayList<Object> shapes = new ArrayList<Object>();

    //Console/screen width and height
    private int width;
    private int height;

    //Internal screen render buffer
    private String[] renderBuffer;

    //Use optimized rendering - faster, but clips incorrectly
    boolean useOptimizedRendering = false;

    //Debug mode
    boolean debugMode = false;
    
    /*
     * CONSTRUCTOR
     */

    public GameEngine(Object ...shapePassIn) {
        //Setup width & height
        width = 30;
        height = 30;

        if (debugMode) {
            System.out.println("Number of shapes: "+shapePassIn.length);
        }

        for (Object s : shapePassIn) { //Add shapes into object array
            shapes.add(s);
        }
        clearTerminal(); //Fix for BlueJ putting a random space in the beginning of every line
    }

    public void setDisplaySize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /*
     * GETTERS/SETTERS
     */
    
    public Object getShape(int index) {
        return shapes.get(index);
    }

    public Position getShapePosition(int index) {
        //First, get the object
        Object shape = getShape(index);
        //Next, get it's class using getClass method
        Class<?> shapeClass = shape.getClass();
        
        //Create returned stringTable
        Position sPos = new Position(-1, -1);
        try {
            //Get getPos method to invoke using java.reflect
            Method getPosMethod = shapeClass.getMethod("getPosition");
            //Invoke stringTable method
            sPos = (Position)getPosMethod.invoke(shape); //explicit cast
        } catch(NoSuchMethodException e) {
            System.out.println("uhhh noSuchMethodException thrown on getPosition; did you pass in a shape?");
        } catch (IllegalAccessException e) {
            System.out.println("uhhh illegalAccessException thrown on getPosition; did you pass in a shape?");    
        } catch (InvocationTargetException e) {
            System.out.println("uhhh invocationTargetException thrown on getPosition; did you pass in a shape?");    
        }
        return sPos;
    }
    
    public String[] getShapeStringTable(int index) {
        //First, get the object
        Object shape = getShape(index);
        //Next, get it's class using getClass method
        Class<?> shapeClass = shape.getClass();
        
        //Create returned stringTable
        String[] stringTable = {"ERR"};
        try {
            //Get getStringTable method to invoke using java.reflect
            Method getStringTableMethod = shapeClass.getMethod("getStringTable");
            //Invoke stringTable method
            stringTable = (String[])getStringTableMethod.invoke(shape); //explicit cast
        } catch(NoSuchMethodException e) {
            System.out.println("uhhh noSuchMethodException thrown on getStringTable; did you pass in a shape?");
        } catch (IllegalAccessException e) {
            System.out.println("uhhh illegalAccessException thrown on getStringTable; did you pass in a shape?");    
        } catch (InvocationTargetException e) {
            System.out.println("uhhh invocationTargetException thrown on getStringTable; did you pass in a shape?");    
        }
        return stringTable;
    }
    
    /*
     * RENDERING/BUFFERING
     */

    public void constructRenderBuffer() {
        renderBuffer = new String[height];
        
        String blankRow = "";
        for (int j=0; j<width; j++) {
            blankRow+=" ";
        }

        for (int i=0; i<height; i++) {
            renderBuffer[i] = blankRow;
            
        }
    }

    public String retreiveRenderBuffer() {
        String output = "";
        for (String s : renderBuffer) {
            output+=s+"\n";
        }
        return output;
    }

    public String render() {
        constructRenderBuffer();

        for (int i=0; i<shapes.size(); i++) {
            if (debugMode) {
                System.out.println("Rendering: "+i);
            }
            //Use janky methods to retreive string table and position
            String[] shapeMesh = getShapeStringTable(i);
            Position shapePos = getShapePosition(i);
            if (debugMode) {
                System.out.println(shapePos);
            }

            for (int j=0; j<shapeMesh.length; j++) { //for every line (row) of mesh
                if (useOptimizedRendering) { //Optimized rendering is far faster, but incorrectly clips shape's boundaries
                    int yWithOff = j+shapePos.y;
                    int xWithOff = shapePos.x;
                    int sWidth = shapeMesh[j].length();
                    if (debugMode) {
                        System.out.println("Width: "+sWidth);
                    }

                    try {
                        String beforeShapeInsertion = renderBuffer[yWithOff].substring(0,xWithOff);
                        String shapeInsertion = shapeMesh[j];
                        String afterShapeInsertion = renderBuffer[yWithOff].substring(xWithOff+sWidth);

                        renderBuffer[yWithOff] = beforeShapeInsertion+shapeInsertion+afterShapeInsertion;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Error rendering shape at index: "+i+" because it's too thicc");
                    }
                } else {
                    int yWithOff = j+shapePos.y;
                    int xWithOff = shapePos.x;
                    int sWidth = shapeMesh[j].length();

                    try {
                        String beforeShapeInsertion = renderBuffer[yWithOff].substring(0,xWithOff);
                        String afterShapeInsertion = renderBuffer[yWithOff].substring(xWithOff+sWidth);

                        String whereShapeWas = renderBuffer[yWithOff].substring(xWithOff, xWithOff+sWidth);
                        String whatShapeIs = shapeMesh[j];

                        String shapeInsertion = "";

                        for (int z = 0; z<shapeMesh[j].length(); z++) {
                            String shapeChar = whatShapeIs.substring(z, z+1);
                            String beforeChar = whereShapeWas.substring(z, z+1);
                            if (shapeChar.equals(" ")) { //Only copy if it's not blank
                                shapeInsertion+=beforeChar;
                            } else {
                                shapeInsertion+=shapeChar;
                            }
                        }
                        renderBuffer[yWithOff] = beforeShapeInsertion+shapeInsertion+afterShapeInsertion;
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Error rendering shape at index: "+i+" because it's too thicc");
                    }
                }
            }
        }
        return retreiveRenderBuffer();
    }

    /*
     * CLEAR TERMINAL
    */

    public void clearTerminal() {
        //System.out.print('\u000C');
    	for (int i=0; i<height; i++) {
    		System.out.println();
    	}
    }
    
    /*
     * TOSTRING
     */
    
    public String toString() {
        String output = "";
        for (Object s : shapes) {
            output+= s+"\n";
        }
        return output;
    }

}