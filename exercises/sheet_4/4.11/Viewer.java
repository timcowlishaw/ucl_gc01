import java.awt.Panel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
/**
 * <dl>
 * <dt>Purpose:
 * <dd>Plane set visualiser.
 *
 * <dt>Description:
 * <dd>Provides a visualisation of any function of the type C -&gt; R (or indeed (R, R) -&gt; R), allowing for various colouring strategies to be used to represent the values of the codomain.
 * </dl>
 *
 * @author  Tim Cowlishaw
 * @version $Date: 2011/10/03 18:00:00 $
 * 
 */
public class Viewer extends DoubleBuffer {
 
  //The set that we are going to render 
  private PlaneSet plane;

  //Our strategy for colouring the points of the set, based on its value at that point
  private Colouriser colouriser;
  
  //The lower bound on the real line of our visualisation
  private double xOffset;

  //The lower bound on the imaginary line of our visualisation
  private double yOffset;

  //The range of both axes that we are interested in 
  private double scale;
  
  //The accuracy with which we will render the visualisation.
  private int resolution = 1;

  //The length of each side of the visible area in pixels.
  private int size;

  public Viewer(double x, double y, double sc, int si) {
    super();
    xOffset = x;
    yOffset = y;
    size = si;
    scale = sc;
    setSize(getPreferredSize());
  }

  public void paintBuffer(Graphics g) {
    for(int y=0; y<getHeight(); y+=resolution) {
      for(int x=0; x<getWidth(); x+=resolution) {
        double[] coords = view2Plane(x,y); 
        g.setColor(colouriser.colourPoint(plane.point(coords[0] +xOffset,coords[1] +yOffset)));
        g.fillRect(x,y,resolution,resolution);
      }
    } 
  }
 

  //Converts coordinates within the java viewing area to coordinates within the plane
  private double[] view2Plane(int x, int y) {
    double[] coords = new double[2];
    coords[0] = -scale + x*(scale*2/getWidth());
    coords[1] = -scale + y*(scale*2/getHeight());
    return coords; 
  }

  public Dimension getPreferredSize() {
    return new Dimension(size, size);
  }

  public void setPlane(PlaneSet p) {
    plane = p;
  }

  public void setColouriser(Colouriser c) {
    colouriser = c;
  }

  public void zoom(double f) {
    scale += f;
    repaint();
  }

  //Sets the centre of the visualisation to the supplied point
  public void setCentre(int x, int y) {
    double[] coords = view2Plane(x, y);
    xOffset += coords[0];
    yOffset += coords[1];
    repaint();
  }

  public void changeResolution(int inc) {
    //ensure that the resolution cannot be set to a number less than 1
    if(resolution + inc >= 1) {
      resolution += inc;
      repaint();
    }
  }
}
