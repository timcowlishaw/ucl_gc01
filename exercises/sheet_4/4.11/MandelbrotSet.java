/**
 * <dl>
 * <dt>Purpose:
 * <dd>A representation of the Mandelbrot Set.
 *
 * <dt>Description:
 * <dd>A PlaneSet implementation representing the mandelbrot set, with the defining function returning the proportion of iterations needed (relative to the limit) to determine the point's membership of the set.
 * </dl>
 *
 * @author  Tim Cowlishaw
 * @version $Date: 2011/10/03 18:00:00 $
 * 
 */
public class MandelbrotSet implements PlaneSet {

  public MandelbrotSet() {
  }

  public double point(double realC, double imagC) {
    Complex c = new Complex(realC, imagC);
    return boundedUnderIteration(c);
  }
  

  private double boundedUnderIteration(Complex c) {
    Complex z = c;
    int i = 0;
    while(i < iterationLimit && z.modulus() <= 2.0) {
      z = z.pow(2).add(c);
      i++;
    }
    return i / (double) iterationLimit;
  }


  private Complex ZERO = new Complex(0.0,0.0);
  private int iterationLimit = 100;
}
