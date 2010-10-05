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
      while(t < 2 * Math.PI * rotations) {
        int[] coords = archimedean(spacing, t); 
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
        rotations = val * DrawArea.ROTATIONS_RESOLUTION;
      } else if (adjustable == spacingSlider) {
        spacing = val * DrawArea.SPACING_RESOLUTION;
      }
      logParams();
      repaint();
    }

    public void logParams() {
      System.out.println("Rotations: " + rotations);
      System.out.println("Spacing: " + spacing);
      System.out.println("Step: " + step);
      System.out.println("");
    }

    public int[] archimedean(double a, double t) {
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

    public double rotations;
    public double spacing;
    public double step;

    public static final double MAX_ROTATIONS = 15.0;
    public static final double MIN_ROTATIONS = 1.0;
    public static final double DEFAULT_ROTATIONS = 10.0;
    public static final double ROTATIONS_RESOLUTION = 0.1;

    public static final double MAX_SPACING = 20.0;
    public static final double MIN_SPACING = 0.0;
    public static final double DEFAULT_SPACING = 5.0;
    public static final double SPACING_RESOLUTION = 0.01;

    public static final double MAX_STEP = 1.0;
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

    drawing.stepSlider = new Scrollbar(Scrollbar.HORIZONTAL, (int) (DrawArea.DEFAULT_STEP / DrawArea.STEP_RESOLUTION), 1,  (int) (DrawArea.MIN_STEP / DrawArea.STEP_RESOLUTION),  (int) (DrawArea.MAX_STEP / DrawArea.STEP_RESOLUTION));

    drawing.rotationsSlider = new Scrollbar(Scrollbar.HORIZONTAL, (int) (DrawArea.DEFAULT_ROTATIONS / DrawArea.ROTATIONS_RESOLUTION), 1, (int) (DrawArea.MIN_ROTATIONS / DrawArea.ROTATIONS_RESOLUTION),  (int) (DrawArea.MAX_ROTATIONS / DrawArea.ROTATIONS_RESOLUTION));
    
    Panel controlPanel = new Panel();
    controlPanel.setLayout(new GridLayout(3,1));
    buttonPanel.setLayout(new BorderLayout());
    buttonPanel.add("South", quitButton);
    controlPanel.add(drawing.spacingSlider);
    controlPanel.add(drawing.rotationsSlider);
    controlPanel.add(drawing.stepSlider);
    buttonPanel.add("Center", controlPanel);
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
