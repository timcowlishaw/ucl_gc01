import java.awt.*;
import java.awt.event.*;

public class DrawSpiral extends Frame {
  protected static class DrawArea extends Panel {
    public void paint(Graphics g) {
      int a = 5;
      int b = 6;
      
      int x = 0;
      while(x < WIDTH) {
        int y = 0;
        while( y < height) {
          int[] coords = archimedean(x,y,a,b);
          drawPoint(coords[0], coords[1]);
          y++;
        }
        x++;
      }  
    }

    public int[] archimedian(int x, int y, int a, int b) {
       double[] polar = cart2pol(x, y);
       polar[0] = a + b * polar[1];
       return(pol2cart(polar[0], polar[1]));
    }

    public double[] cart2pol(int x, int y) {
      int[] retval = {Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)), Math.atan(x/y)};
      return(retval);
    }

    public int[] pol2cart(double r, double theta) {
      int[] retval = {Math.sin(theta) * r, Math.cos(theta) * r};
      return (retval);
    }

    public Dimension getPreferredSize() {
      return new Dimension(WIDTH, HEIGHT); 
    }

    private int WIDTH =300;
    private int HEIGHT = 300;
  }
  
  public DrawSpiral(String name) {
    super(name);
  }

  private static void quit() {
    System.exit(0);
  }

  public static void main(String[] args) {
    DrawSpiral frame = new DrawSpiral("My DrawSpiral");
    DrawArea drawing = new DrawArea();
    Panel buttonPanel = new Panel();
    Button quitButton = new Button("Quit");
    buttonPanel.add("Center", quitButton);
    frame.setLayout(new BorderLayout());
    frame.add("Center", drawing);
    frame.add("South", buttonPanel);

    quitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        quit();
      }
    });
    
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
        quit();
      }
    });

    frame.pack();
    frame.setVisible(true);

  }
}
