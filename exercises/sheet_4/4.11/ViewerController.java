import java.awt.event.*;
/**
 * <dl>
 * <dt>Purpose:
 * <dd>Plane set visualisation controller.
 *
 * <dt>Description:
 * <dd>Provides various types of user interaction with the Viewer class.
 * </dl>
 *
 * @author  Tim Cowlishaw
 * @version $Date: 2011/10/03 18:00:00 $
 * 
 */
public class ViewerController {

  private Viewer viewer;

  public ViewerController(Viewer v) {
    viewer = v;    
    viewer.addMouseListener(new MouseAdapter() {
       
      public void mouseReleased(MouseEvent evt) {
        viewer.setCentre(evt.getX(), evt.getY()); 
      }
      
    });

    viewer.addKeyListener(new KeyListener() {
      private final double ZOOM_FACTOR = -0.01;
      private final int RESOLUTION_INCREMENT = -1;

      public void keyPressed(KeyEvent evt) {
        char c = evt.getKeyChar();
        switch(c) {
          case 'a': viewer.zoom(ZOOM_FACTOR); break;
          case 'z': viewer.zoom(-ZOOM_FACTOR); break;
          case 's': viewer.changeResolution(RESOLUTION_INCREMENT); break;
          case 'x': viewer.changeResolution(-RESOLUTION_INCREMENT); break;
        }

      }
      public void keyTyped(KeyEvent evt) {

      }
      public void keyReleased(KeyEvent evt) {
      }
    });
  }


}
