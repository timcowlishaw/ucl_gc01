import java.awt.Color;
/**
 * <dl>
 * <dt>Purpose:
 * <dd>Saturation/Luminescence colouriser.
 *
 * <dt>Description:
 * <dd>A colouriser implementation that maps points between 0.0 and 1.0 to varying shades of a pleasant cyan colour, in a non-linear fashion. 
 * </dl>
 *
 * @author  Tim Cowlishaw
 * @version $Date: 2011/10/03 18:00:00 $
 * 
 */
public class HSLColouriser extends Colouriser {
  public Color colourPoint(double d) {
    return Color.getHSBColor(hue(d),sat(d), lum(d));
  }

  private float hue(double d) {
    return hue;
  }

  private float sat(double d) {
    return satFloor + (float) Math.pow(1-d, 0.1) * (lumCeiling - lumFloor);
  }

  private float lum(double d) {
    return lumFloor + (float) Math.pow(d,0.7) * (lumCeiling - lumFloor);
  }

  private float hue = 0.5f;
  private float satFloor = 0.05f;
  private float satCeiling = 1f;
  private float lumFloor = 0.05f;
  private float lumCeiling = 1f;

}
