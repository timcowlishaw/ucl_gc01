import java.awt.*;
import java.awt.event.*;
/**
 * <dl>
 * <dt>Purpose:
 * <dd>A visualisation of the Mandelbrot Set.
 *
 * <dt>Description:
 * <dd>Call 'java Mandelbrot' to launch. Controls: a- zoom in, z- zoom out, s-increase resolution, x-decrease resolution. Clicking anywhere in the frame centers that point. I reccomend lowering the resolution before zooming or panning, then increasing it again once you have found an area of interest, as rendering each frame can become expensive and slow at full res.
 * </dl>
 *
 * @author  Tim Cowlishaw
 * @version $Date: 2011/10/03 18:00:00 $
 * 
 */

public class Mandelbrot extends Frame {

  public static void main(String[] args) {
    Mandelbrot frame = new Mandelbrot();
  }
  
  public Mandelbrot() {
    super("The Mandelbrot set");
    Viewer viewer = new Viewer(0, 0, 2.0, SIZE);
    viewer.setColouriser(new HSLColouriser());
    viewer.setPlane(new MandelbrotSet());
    setLayout(new BorderLayout());
    add("Center",viewer);
    pack();
    setVisible(true);
    ViewerController viewerController = new ViewerController(viewer);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
        quit();
      }
    });
  }
 
  private void quit() {
    System.exit(0);
  }

  private final int SIZE = 600;
}
