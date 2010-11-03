import java.awt.Color;
/**
 * <dl>
 * <dt>Purpose:
 * <dd>Colouring strategy.
 *
 * <dt>Description:
 * <dd>An abstract class denoting a strategy for mapping a real number to a Color.
 * </dl>
 *
 * @author  Tim Cowlishaw
 * @version $Date: 2011/10/03 18:00:00 $
 * 
 */
public abstract class Colouriser {
  public abstract Color colourPoint(double d);
}
