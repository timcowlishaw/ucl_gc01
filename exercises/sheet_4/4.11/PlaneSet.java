/**
 * <dl>
 * <dt>Purpose:
 * <dd>An interface representing a function from C to R, or from (R, R) to R.
 *
 * <dt>Description:
 * <dd>This interface is now slightly disingenuously named - initially it encapsulated the notion a function 'point' that took a point in the complex or cartesian planes, and returned a result in {true, false} denoting membership of some bounded plane set. However, in its current form it represents function from C to R - returning a real value for every point in the plane.
 * </dl>
 *
 * @author  Tim Cowlishaw
 * @version $Date: 2011/10/03 18:00:00 $
 * 
 */
public interface PlaneSet {
  public double point(double x, double y);
}
