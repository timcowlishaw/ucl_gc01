/**
 * <dl>
 * <dt>Purpose:
 * <dd>Complex number representation.
 *
 * <dt>Description:
 * <dd>A representation of complex numbers - supports addition, subtraction, multiplication, division and integer exponentiation, as well as modulus and conjugation.
 * </dl>
 *
 * @author  Tim Cowlishaw
 * @version $Date: 2011/10/03 18:00:00 $
 * 
 */
public class Complex {
  private double real;
  private double imaginary;

  public Complex(double r, double i) {
    real = r;
    imaginary = i;
  }
  public double getReal() {
    return real;
  }

  public double getImaginary() {
    return imaginary;
  }

  public Complex add(Complex other) {
    return new Complex(getReal() + other.getReal(), getImaginary() + other.getImaginary());
  }

  public Complex subtract(Complex other) { 
    return new Complex(getReal() - other.getReal(), getImaginary() - other.getImaginary());
  }

  public Complex multiply(Complex other) {
    return new Complex(getReal() * other.getReal() - getImaginary() * other.getImaginary(), getImaginary() * other.getReal() + getReal() * other.getImaginary());
  }

  public Complex divide(Complex other) {
    double denominator = Math.pow(other.getReal(), 2) + Math.pow(other.getImaginary(),2);
    return new Complex((getReal()* other.getReal() + getImaginary() * other.getImaginary())/denominator, (getImaginary() * other.getReal() - getReal() * other.getImaginary())/denominator);
  }

  public Complex pow(int exponent) { //for natural exponents
    return exponent <= 0 ? Complex.REAL_UNIT : this.multiply(this.pow(exponent - 1));
  }

  public double modulus() {
    return Math.sqrt(Math.pow(getReal(), 2) + Math.pow(getImaginary(), 2)); 
  }

  public Complex conjugate() {
    return new Complex(getReal(), 0 - getImaginary());
  }

  public static final Complex ZERO = new Complex(0,0);
  public static final Complex REAL_UNIT = new Complex(1,0);
  public static final Complex IMAGINARY_UNIT = new Complex(0,1);


}
