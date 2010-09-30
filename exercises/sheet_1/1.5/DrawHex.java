// We are using AWT so we need to say so.
import java.awt.* ;

// Also using events.
import java.awt.event.* ;

/**
 * <dl>
 * <dt>Purpose:
 * <dd>Example drawing program with exit option.
 *
 * <dt>Description:
 * <dd>Draws a hexagon in a window.
 * </dl>
 *
 * @author  Tim Cowlishaw
 * @version $Date: 2010/09/30 16:31:00 $
 * 
 */
 
// This program will be called DrawHex and must be
// to stored in a file called DrawHex.java.
//
// To create your own program with a different name,
// change *all* the occurrences of DrawHex to the new
// name. Save the program to a new file named using the
// new name with .java appended.
// You should also remove this comment (with //'s), but 
// not the one above (with *'s), which should be altered
// to reflect what your new program does, who wrote it 
// and when.
//
// I DO NOT WANT TO BE TOLD THAT I WROTE YOUR PROGRAM
// OVER A YEAR AGO!
public class DrawHex extends Frame
{
  protected static class DrawArea extends Panel
  { 
    /**
     * Does the actual drawing.
     */
    public void paint(Graphics g)
    {
      // You add/change the statements here to draw
      // the picture you want.
      hexagon(g, 50, 150, 150) ;	
    }

    public void hexagon(Graphics g, int l, int x, int y) {
      double theta = 2/6.0 * Math.PI;
      int a = (int) (l * Math.cos(theta));
      int o = (int) (l * Math.sin(theta));
      g.drawLine(x - l/2 - a, y,     x  -l/2,     y + o);
      g.drawLine(x - l/2,     y + o, x + l/2,     y + o);
      g.drawLine(x + l/2,     y + o, x + l/2 + a, y);
      g.drawLine(x + l/2 + a, y,     x + l/2,     y - o);
      g.drawLine(x + l/2,     y - o, x - l/2,      y - o);
      g.drawLine(x - l/2,     y - o, x - l/2 - a, y);
    }

    /**
     * Makes sure that
     * the window drawing area ends up being the
     * right size. You don't need to change this.
     */
    public Dimension getPreferredSize()
    {
      return new Dimension(WIDTH,HEIGHT) ;
    }

    // These set the size of the drawing area.
    // Change the sizes to suit what you need.
    private int WIDTH = 300 ;
    private int HEIGHT = 300 ;
  }

  /**
   * Creates a new window frame.
   */
  public DrawHex(String name)
  {
    super(name) ;
  } 
  
  /**
   * Terminates the program when the user
   * wants to quit.
   */
  private static void quit()
  {
    System.exit(0) ;
  }

  /**
   * Sets everything up
   * and displays the drawing window.
   */
  public static void main(String[] args)
  {
     // Create the window frame with the label
     // "My DrawHex". Change the text to change
     // the label.
     DrawHex frame = new DrawHex("A Hexagon") ;

     // Create the contents of the frame. The top (or Center)
     // part is the drawing area. The bottom (or South) strip 
     // holds a quit button.
     DrawArea drawing = new DrawArea() ;
     Panel buttonPanel = new Panel() ;
     buttonPanel.setLayout(new BorderLayout()) ;
     Button quitButton = new Button("Quit") ;
     buttonPanel.add("Center",quitButton) ;
     frame.setLayout(new BorderLayout()) ;
     frame.add("Center",drawing) ;
     frame.add("South",buttonPanel) ;

     // The event listeners are set up here to enable the
     // program to respond to events.
     quitButton.addActionListener(new ActionListener()
     {
       public void actionPerformed(ActionEvent evt)
       {
         quit() ;
       }
     }) ;

     frame.addWindowListener(new WindowAdapter()
     {
       public void windowClosing(WindowEvent evt)
       {
         quit() ;
       }
     }) ;

     frame.pack() ;
     frame.setVisible(true) ;
  }
}


