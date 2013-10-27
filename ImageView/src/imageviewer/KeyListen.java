package imageviewer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author C. Jimenez
 */
public class KeyListen implements KeyListener
{

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            ImageViewer.closeFrame();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            ImageViewer.nextImage();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            ImageViewer.previousImage();
        }
    }
}
