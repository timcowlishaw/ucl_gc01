import java.awt.*;
import java.awt.event.*;

public class DrawSpiral extends Frame {


  protected static class DrawArea extends Panel implements AdjustmentListener {
        
    public void paint(Graphics g) {
      
      Color oldColor = g.getColor();
      g.setColor(getBackground());
      g.drawRect(0,0, WIDTH, HEIGHT);
      g.setColor(oldColor);
      
      double t = 0;
      while(t < Math.PI * rotations) {
        int[] coords = archimedian(spacing, t); 
        g.drawLine(150+ coords[0], 150+ coords[1], 150+ coords[0], 150+coords[1]);
        t+=step;
      }  
    }

    public void adjustmentValueChanged(AdjustmentEvent e) {
      Adjustable adjustable = e.getAdjustable();
      int val = e.getValue();
      if (adjustable == stepSlider) {
        step = val * DrawArea.STEP_RESOLUTION;
      } else if (adjustable == rotationsSlider) {
        rotations = val;
      } else if (adjustable == spacingSlider) {
        spacing = val * DrawArea.SPACING_RESOLUTION;
      }
      repaint();
    }


    public int[] archimedian(double a, double t) {
      int[] retval = {(int) Math.floor(a *t * Math.cos(t)), (int) Math.floor(a * t* Math.sin(t))};
      return retval; 
    }

    public Dimension getPreferredSize() {
      return new Dimension(WIDTH, HEIGHT); 
    }
    
    public DrawArea() { 
      super();
      rotations  = DrawArea.DEFAULT_ROTATIONS;
      spacing = DrawArea.DEFAULT_SPACING;
      step = DrawArea.DEFAULT_STEP;
    };

    public int rotations;
    public double spacing;
    public double step;

    public static final int MAX_ROTATIONS = 15;
    public static final int MIN_ROTATIONS = 1;
    public static final int DEFAULT_ROTATIONS = 10;

    public static final double MAX_SPACING = 20.0;
    public static final double MIN_SPACING = 0.0;
    public static final double DEFAULT_SPACING = 5.0;
    public static final double SPACING_RESOLUTION = 0.01;

    public static final double MAX_STEP = 0.1;
    public static final double MIN_STEP = 0.001;
    public static final double STEP_RESOLUTION = 0.001;
    public static final double DEFAULT_STEP = 0.001; 

    public Scrollbar rotationsSlider;
    public Scrollbar spacingSlider;
    public Scrollbar stepSlider;


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

    drawing.spacingSlider = new Scrollbar(Scrollbar.HORIZONTAL, (int) (DrawArea.DEFAULT_SPACING / DrawArea.SPACING_RESOLUTION), 1,  (int) (DrawArea.MIN_SPACING / DrawArea.SPACING_RESOLUTION),  (int) (DrawArea.MAX_SPACING / DrawArea.SPACING_RESOLUTION));

    drawing.stepSlider = new Scrollbar(Scrollbar.HORIZONTAL, (int) (DrawArea.DEFAULT_STEP / DrawArea.STEP_RESOLUTION), 1,  (int) (DrawArea.MIN_STEP / DrawArea.STEP_RESOLUTION),  (int) (DrawArea.MAX_SPACING / DrawArea.STEP_RESOLUTION));

    drawing.rotationsSlider = new Scrollbar(Scrollbar.HORIZONTAL, DrawArea.DEFAULT_ROTATIONS, 1, DrawArea.MIN_ROTATIONS,  DrawArea.MAX_ROTATIONS);
    
    buttonPanel.add("South", quitButton);
    buttonPanel.add("Center", drawing.spacingSlider);
    buttonPanel.add("Center", drawing.rotationsSlider);
    buttonPanel.add("Center",  drawing.stepSlider);
    frame.setLayout(new BorderLayout());
    frame.add("Center", drawing);
    frame.add("South", buttonPanel);

    drawing.spacingSlider.addAdjustmentListener(drawing);
    drawing.rotationsSlider.addAdjustmentListener(drawing);
    drawing.stepSlider.addAdjustmentListener(drawing);

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
