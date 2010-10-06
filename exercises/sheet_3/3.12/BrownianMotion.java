import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * <dl>
 * <dt>Purpose:
 * <dd>Spiral-drawing program.
 *
 * <dt>Description:
 * <dd>Draws a spiral, based on various parameters passed in through a UI.
 * </dl>
 *
 * @author  Tim Cowlishaw
 * @version $Date: 2010/10/06 10:25:28 $
 * 
 */
public class BrownianMotion extends Frame {

  protected static class BrownianMotionSimulator {
    private Random random;
    private int[] coords;

    public BrownianMotionSimulator(int x, int y) {
      random = new Random();
      coords = new int[2];
      coords[0] = x;
      coords[1] = y;
    }

    public int[] next() {
      coords[0] += random.nextGaussian();
      coords[1] += random.nextGaussian();
      return coords;
    }
  }
  
  protected static class DrawArea extends Panel implements AdjustmentListener {

    public void paint(Graphics g) {
      Color oldColor = g.getColor();
      g.setColor(getBackground());
      g.drawRect(0,0, WIDTH, HEIGHT);
      g.setColor(oldColor);

      BrownianMotionSimulator bms = new BrownianMotionSimulator(initialCoords[0], initialCoords[1]);
      int[] lastCoords = initialCoords;
      for(int i = 0; i < steps; i++) {
        int[] nextCoords = bms.next();
        g.drawLine(lastCoords[0], lastCoords[1], nextCoords[0], nextCoords[1]);
        lastCoords = nextCoords;
      }    
    }

    public void adjustmentValueChanged(AdjustmentEvent e) {
      Adjustable adjustable = e.getAdjustable();
      int val = e.getValue();
      if (adjustable == xSlider) {
        initialCoords[0] = val;
      } else if (adjustable == ySlider) {
        initialCoords[1] = val;
      } else if (adjustable == stepsSlider) {
        steps = val;
      }
      repaint();
    }

    public Dimension getPreferredSize() {
      return new Dimension(WIDTH, HEIGHT);
    }

    public DrawArea() {
      super();
      steps = DrawArea.DEFAULT_STEPS;
      initialCoords = new int[2];
      initialCoords[0] = WIDTH/2;
      initialCoords[1] = HEIGHT/2;
    }
    private int WIDTH = 300;
    private int HEIGHT = 300;
    
    public int steps;
    public int[] initialCoords;

    public static final int MAX_STEPS = 1000;
    public static final int MIN_STEPS = 0;
    public static final int DEFAULT_STEPS = 500;

    public Scrollbar xSlider;
    public Scrollbar ySlider;
    public Scrollbar stepsSlider;

  }

  public BrownianMotion(String name) {
    super(name);

  }

  private static void quit() {
    System.exit(0);
  }

  public static void main(String[] args) {
    BrownianMotion frame = new BrownianMotion("Brownian Motion Simulator");
    DrawArea drawing = new DrawArea();
    Panel buttonPanel = new Panel();
    buttonPanel.setLayout(new BorderLayout());
    Button quitButton = new Button("Quit");
    drawing.xSlider = new Scrollbar(Scrollbar.HORIZONTAL, drawing.WIDTH/2, 1, 0, drawing.WIDTH);
    drawing.ySlider = new Scrollbar(Scrollbar.HORIZONTAL, drawing.WIDTH/2, 1, 0, drawing.WIDTH);
    drawing.stepsSlider = new Scrollbar(Scrollbar.HORIZONTAL, DrawArea.DEFAULT_STEPS,1, DrawArea.MIN_STEPS, DrawArea.MAX_STEPS);
    Panel controlPanel = new Panel();
    controlPanel.setLayout(new GridLayout(3,1));
    controlPanel.add(drawing.xSlider);
    controlPanel.add(drawing.ySlider);
    controlPanel.add(drawing.stepsSlider);
    buttonPanel.add("Center", controlPanel);
    buttonPanel.add("South",quitButton);
    frame.setLayout(new BorderLayout());
    frame.add("Center",drawing);
    frame.add("South",buttonPanel);
    drawing.xSlider.addAdjustmentListener(drawing);
    drawing.ySlider.addAdjustmentListener(drawing);
    drawing.stepsSlider.addAdjustmentListener(drawing);
    // The event listeners are set up here to enable the
    // program to respond to events.
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
