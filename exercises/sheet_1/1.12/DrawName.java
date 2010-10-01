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
 * <dd>Draws a circle and an ellipse in a window.
 * </dl>
 *
 * @author  Tim Cowlishaw
 * @version $Date: 2010/09/30 17:30:28 $
 * 
 */
 
// This program will be called DrawName and must be
// to stored in a file called DrawName.java.
//
// To create your own program with a different name,
// change *all* the occurrences of DrawName to the new
// name. Save the program to a new file named using the
// new name with .java appended.
// You should also remove this comment (with //'s), but 
// not the one above (with *'s), which should be altered
// to reflect what your new program does, who wrote it 
// and when.
//
// I DO NOT WANT TO BE TOLD THAT I WROTE YOUR PROGRAM
// OVER A YEAR AGO!
public class DrawName extends Frame
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
      int y = 0;
      int x = 0;
      int step = 27;
      int size = 25;
      c(g, x+=step, y+=step, size);
      o(g, x+=step, y+=step, size);
      w(g, x+=step, y+=step, size);
      l(g, x+=step, y+=step, size);
      i(g, x+=step, y+=step, size);
      s(g, x+=step, y+=step, size);
      h(g, x+=step, y+=step, size);
      a(g, x+=step, y+=step, size);  
      w(g, x+=step, y+=step, size);
    }
    public void c(Graphics g, int x, int y, int size) {
      int d = size / 2; 
      g.drawLine(x-d, y-d, x+d, y-d);
      g.drawLine(x+d, y+d, x-d, y+d);
      g.drawLine(x-d, y+d, x-d, y-d); 
    }

    public void o(Graphics g, int x, int y, int size) {
      int d = size / 2;
      g.drawLine(x-d, y-d, x+d, y-d);
      g.drawLine(x+d, y-d, x+d, y+d);
      g.drawLine(x+d, y+d, x-d, y+d);
      g.drawLine(x-d, y+d, x-d, y-d); 
    }
    
    public void w(Graphics g, int x, int y, int size) {
      int d = size / 2;
      g.drawLine(x-d, y+d, x-d, y-d);
      g.drawLine(x, y+d, x, y-d);
      g.drawLine(x+d, y+d, x+d, y-d);
      g.drawLine(x-d, y+d, x+d, y+d); 
    }

    
    public void l(Graphics g, int x, int y, int size) {
      int d = size / 2;
      g.drawLine(x-d, y-d, x-d, y+d);
      g.drawLine(x-d, y+d, x+d, y+d); 
    }


    public void i(Graphics g, int x, int y, int size) {
      int d = size / 2;
      g.drawLine(x-d, y-d, x+d, y-d);
      g.drawLine(x-d, y+d, x+d, y+d);
      g.drawLine(x, y-d, x, y+d); 
    }

    
    public void s(Graphics g, int x, int y, int size) {
      int d = size / 2;
      g.drawLine(x-d, y-d, x+d, y-d);
      g.drawLine(x-d, y-d, x-d, y);
      g.drawLine(x-d, y+d, x+d, y+d);
      g.drawLine(x+d, y, x+d, y+d);
      g.drawLine(x-d, y, x+d, y); 
    }


    public void h(Graphics g, int x, int y, int size) {
      int d = size / 2;
      g.drawLine(x-d, y-d, x-d, y+d);
      g.drawLine(x+d, y-d, x+d, y+d);
      g.drawLine(x-d, y, x+d, y); 
    }

    public void a(Graphics g, int x, int y, int size) {
      int d = size / 2;
      g.drawLine(x-d, y-d, x+d, y-d);
      g.drawLine(x-d, y-d, x-d, y+d);
      g.drawLine(x+d, y-d, x+d, y+d);
      g.drawLine(x-d, y, x+d, y); 
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
  public DrawName(String name)
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
     // "My DrawName". Change the text to change
     // the label.
     DrawName frame = new DrawName("My DrawName") ;

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


