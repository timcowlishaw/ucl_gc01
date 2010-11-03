import java.awt.Panel;
import java.awt.Graphics;
import java.awt.Image;
/**
 * <dl>
 * <dt>Purpose:
 * <dd>Double-buffered AWT Panel.
 *
 * <dt>Description:
 * <dd>An AWT panel that supports double-buffering when being updated, avoiding unsightly flickr or visible 'line-by-line' refreshing when rendering the contents is computationally expensive. Inspired by the tutorial at http://www.codeproject.com/KB/graphics/javadoublebuffer.aspx, but refactored for simplicity. Override paintBuffer() in subclasses, instead of paint() as you would in a regular panel. call repaint() to refresh.

 * </dl>
 *
 * @author  Tim Cowlishaw
 * @version $Date: 2011/10/03 18:00:00 $
 * 
 */

public abstract class DoubleBuffer extends Panel {
  public DoubleBuffer() {
    super();
  }

  private Image bufferImage;
  private int bufferWidth;
  private int bufferHeight;


  public abstract void paintBuffer(Graphics g);

  public void paint(Graphics g) {
    if(bufferImage == null || bufferHeight != getHeight() || bufferWidth != getWidth()) {
      resetBuffer();  
    }
    Graphics bg = bufferImage.getGraphics();
    bg.clearRect(0,0,bufferWidth, bufferHeight);
    paintBuffer(bg);
    g.drawImage(bufferImage,0,0,this);
  }

  private void resetBuffer() {
    bufferWidth = getWidth();
    bufferHeight = getHeight();
    if(bufferImage != null) {
      bufferImage.flush();
    }
    bufferImage = createImage(bufferWidth, bufferHeight);
  }

}
