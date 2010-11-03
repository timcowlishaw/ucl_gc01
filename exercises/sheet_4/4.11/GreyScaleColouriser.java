import java.awt.Color;
/**
 * <dl>
 * <dt>Purpose:
 * <dd>Greyscale colouriser.
 *
 * <dt>Description:
 * <dd>A colouriser implementation that maps points between 0.0 and 1.0 to shades of grey, from white to black. 
 * </dl>
 *
 * @author  Tim Cowlishaw
 * @version $Date: 2011/10/03 18:00:00 $
 * 
 */
public class GreyScaleColouriser extends Colouriser {
  public Color colourPoint(double d) {
    int g = floor + (int) Math.floor((1-d)*(ceiling - floor));
    return new Color(g,g,g);
  }

  private int floor = 0;
  private int ceiling = 254;

}
