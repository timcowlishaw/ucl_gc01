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
 * <dd>Draws a diagonal line in a window.
 * </dl>
 *
 * @author  Danny Alexander
 * @version $Date: 2003/09/24 17:30:28 $
 * 
 */
 
// This program will be called DrawLine and must be
// to stored in a file called DrawLine.java.
//
// To create your own program with a different name,
// change *all* the occurrences of DrawLine to the new
// name. Save the program to a new file named using the
// new name with .java appended.
// You should also remove this comment (with //'s), but 
// not the one above (with *'s), which should be altered
// to reflect what your new program does, who wrote it 
// and when.
//
// I DO NOT WANT TO BE TOLD THAT I WROTE YOUR PROGRAM
// OVER A YEAR AGO!
public class DrawLine extends Frame
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
      g.drawLine(0,0,300,300) ;	
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
  public DrawLine(String name)
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
     // "My DrawLine". Change the text to change
     // the label.
     DrawLine frame = new DrawLine("My DrawLine") ;

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


