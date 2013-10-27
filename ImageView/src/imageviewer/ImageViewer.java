package imageviewer;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author C. Jimenez
 */
public class ImageViewer
{

    private static String path;
    private static final String message = "Enter the path where the pictures are located.";
    private static File[] directory;
    private static int directoryIndex = 0;
    private static Dimension screenSize;
    private static JFrame frame;
    private static JLabel label;

    /**
     * Handles the main viewer
     *
     * @param args
     */
    public static void main(String[] args)
    {
        path = JOptionPane.showInputDialog(null, message, "Image Paths", 3);
        //Get file Directory
        directory = new File(path).listFiles();

        //Get Screen Resolution & Set Frame to ScreenSize
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.height = screenSize.height/2;
        screenSize.width = screenSize.width/2;
        
        frame = new JFrame("Image Viewer");
        label = new JLabel();
        initFrame();
        nextImage();
    }

    /*
     * Initialize the frame where the photos are viewed.
     */
    private static void initFrame()
    {
        frame.setSize(screenSize);
        frame.setResizable(true);

        //Set borderless frame and exit on close
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set KeyListener for frame
        KeyListen keyListen = new KeyListen();
        frame.addKeyListener(keyListen);
    }

    /*
     * Updates the JLabel where images are displayed with the next JPG in the 
     * directory.
     */
    private static void displayImage(File imagePath)
    {
        BufferedImage image;
        ImageIcon icon = null;
        try
        {
            image = ImageIO.read(imagePath);
            icon = new ImageIcon(image.getScaledInstance(screenSize.width, screenSize.height, 0));
        } catch (IOException ex)
        {
            throw new RuntimeException();
        }
        //Set Initial Image
        label.setIcon(icon);

        frame.add(label);
        frame.setVisible(true);
    }

    /**
     * Closes the frame and ends the program
     */
    public static void closeFrame()
    {
        frame.dispose();
    }

    /**
     * Loads the next image in the directory.
     * This method is public so it can be accessed by the KeyListener implementation.
     */
    public static void nextImage()
    {
        if (directoryIndex < directory.length)
        {
            //Only display if it is a JPG extension file
            if (directory[directoryIndex].toString().toUpperCase().endsWith(".JPG"))
            {
                displayImage(directory[directoryIndex]);
            } else
            {
                directoryIndex++;
                displayImage(directory[directoryIndex]);
            }

        } else
        {
            directoryIndex = 0;
            if (directory[directoryIndex].toString().toUpperCase().endsWith(".JPG"))
            {
                displayImage(directory[directoryIndex]);
            } else
            {
                directoryIndex++;
                displayImage(directory[directoryIndex]);
            }
        }
        directoryIndex++;
    }
    /**
     * Loads the previous image in the directory.
     * This method is public so it can be accessed by the KeyListener implementation.
     */
    public static void previousImage()
    {
        directoryIndex--;
        if (directoryIndex < directory.length && directoryIndex >= 0)
        {
            //Only display if it is a JPG extension file
            if (directory[directoryIndex].toString().toUpperCase().endsWith(".JPG"))
            {
                displayImage(directory[directoryIndex]);
            } else
            {
                directoryIndex--;
                displayImage(directory[directoryIndex]);
            }

        } else
        {
            directoryIndex = directory.length - 1;
            if (directory[directoryIndex].toString().toUpperCase().endsWith(".JPG"))
            {
                displayImage(directory[directoryIndex]);
            } else
            {
                directoryIndex--;
                displayImage(directory[directoryIndex]);
            }
        }
    }
}
