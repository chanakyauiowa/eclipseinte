package swingdemo;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics; 
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter; 

public class Swing {
    
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI(); 
        }
      });
    }

    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
        SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        f.add(new MyPanel());
        f.pack();
        f.setVisible(true);
    } 
}

class MyPanel extends JPanel {

    private int squareX = 50;
    private int squareY = 50;
    private int squareX1=100;
    private int squareY1=100;
    private int squareW = 20;
    private int squareH = 20;
 

    public MyPanel() {

        setBorder(BorderFactory.createLineBorder(Color.black));
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	
                
            	 moveSquare(e.getX(),e.getY());
                 System.out.println("Mouse Pressed,X: " +e.getX() + "Y:" + e.getY());
                if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
                	                  	  
                	  System.out.println("Right click"); //swap colors of the squares 
                	  return; }
               
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if(squareX-10<=e.getX() && e.getX()<=squareX+20 && e.getY()>=squareY-10 &&e.getY()<=squareY+20){
                moveSquare(e.getX(),e.getY());
                System.out.println("Mouse dragged, X:" +e.getX() + "Y:" + e.getY());}
                if(e.getX()>=squareX1-10 && e.getX()<=squareX1+20 && e.getY()>=squareY1-10 && e.getY()<=squareY1+20 ){
                moveSquare0(e.getX(),e.getY());
                System.out.println("Mouse dragged, X:" +e.getX() + "Y:" + e.getY());}
            }
        });
}
    
    private void moveSquare(int x, int y) {
        int OFFSET = 1;
        if ((squareX!=x) || (squareY!=y)) {
            repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
            squareX=x;
            squareY=y;
            repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
        }
       
        }
    
    private void moveSquare0(int x, int y){
    	int OFFSET = 1;
    	 if((squareX1!=x)||(squareY1!=y)){
         	repaint(squareX1,squareY1,squareW+OFFSET,squareH+OFFSET);
             squareX1=x;
             squareY1=y;
             repaint(squareX1,squareY1,squareW+OFFSET,squareH+OFFSET);
         }
    }
    
    

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);       
        g.drawString("This is my custom Panel!",10,20);
        g.setColor(Color.RED);
        g.fillRect(squareX,squareY,squareW,squareH);
        g.setColor(Color.BLACK);
        g.drawRect(squareX,squareY,squareW,squareH);
        
        g.setColor(Color.GREEN);
        g.drawRect(squareX1, squareY1, squareW, squareH);
        g.fillRect(squareX1, squareY1, squareW, squareH);
        
        
    }  
}
