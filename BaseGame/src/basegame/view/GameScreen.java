package basegame.view;

import basegame.model.MoveDirection;
import basegame.model.Terrain;
import basegame.model.GameSquare;
import basegame.model.Game;
import basegame.model.GameLevel;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

/**
 * Created by Lisa Helm on 09/11/2013 The controlling class for the view of the
 * game.
 */
public class GameScreen implements GLEventListener, KeyListener {

    private JFrame frame;
    private GLU glu;
    private GLCanvas canvas;
    private static int WIDTH = 800, HEIGHT = 600;
    private int gameWidth, gameHeight;
    private Game game;
    int squareWidth, squareHeight;

    public GameScreen(Game game) {
        this();
        this.game = game;
        gameHeight = 500;
        gameWidth = 700;
    }

    public GameScreen() {
        this.glu = new GLU();

        GLCapabilities capabilities = new GLCapabilities();
        canvas = new GLCanvas(capabilities);
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        canvas.addGLEventListener(this);

        // animate the canvas
        Animator animator = new FPSAnimator(canvas, 50);
        animator.add(canvas);
        animator.start();

        // prepare a frame to hold the canvas
        frame = new JFrame("Shape Drawer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(canvas);
        frame.setResizable(false);
        frame.addKeyListener(this);
        frame.pack();

        // position the frame in the middle of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();
        frame.setLocation((screenDimension.width - frameDimension.width) / 2, (screenDimension.height - frameDimension.height) / 2);
        frame.setVisible(true);
    }

    /**
     * Draws a game square in the appropriate location Added by Lisa 10/11/13
     *
     * @param square
     * @param gl
     */
    public void drawSquare(GameSquare square, GL gl) {
        int x = square.getLocation().x;
        int y = square.getLocation().y;
        //System.out.println(x + " " + y);
        
        Color c=square.getTerrain().getColour();
        //System.out.println(c);
        gl.glColor3f(c.getRed(), c.getGreen(), c.getBlue());
        
        
        int xw = x * squareWidth + 50;
        int yh = y * squareHeight + 50;

        gl.glBegin(GL.GL_QUADS);
        gl.glVertex2d(xw, yh);
        gl.glVertex2d(xw + squareWidth, yh);
        gl.glVertex2d(xw + squareWidth, yh + squareHeight);
        gl.glVertex2d(xw, yh + squareHeight);
        gl.glEnd();

        //place player
        if (square.getLocation().equals(game.getPlayerLocation())) {
            //System.out.println("player");
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glColor3f(1.0f, 0.0f, 0.0f);
            gl.glVertex2d(xw, yh);
            gl.glVertex2d(xw + (squareWidth / 2), yh + squareHeight);
            gl.glVertex2d(xw + squareWidth, yh);
            gl.glEnd();
        }
        
        if(game.getCurrentLevel().getOccupants().containsKey(square.getLocation()))
        {
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glColor3f(0.0f, 1.0f, 0.0f);
            gl.glVertex2d(xw, yh);
            gl.glVertex2d(xw + (squareWidth / 2), yh + squareHeight);
            gl.glVertex2d(xw + squareWidth, yh);
            gl.glEnd();
        }
    }

    @Override
    public void init(GLAutoDrawable glad) {
        GL gl = glad.getGL();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glPointSize(1.0f);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(0.0, WIDTH, 0.0, HEIGHT);
        //gl.glViewport(0, 0, WIDTH, HEIGHT);
    }

    @Override
    public void display(GLAutoDrawable glad) {
        GL gl = glad.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        GameLevel level = game.getCurrentLevel();
        Set<GameSquare> squares = level.getSquares();
        squareWidth = gameWidth / level.getWidth();
        squareHeight = gameHeight / level.getHeight();

        for (GameSquare square : squares) {
            drawSquare(square, gl);
        }
        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
        glad.repaint();
    }

    @Override
    public void displayChanged(GLAutoDrawable glad, boolean bln, boolean bln1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        //System.out.println("pressed");
        if (k == KeyEvent.VK_UP) {
            //System.out.println("up");
            game.movePlayer(MoveDirection.UP);
        } else if (k == (KeyEvent.VK_DOWN)) {
            //System.out.println("down");
            game.movePlayer(MoveDirection.DOWN);
        } else if (k == (KeyEvent.VK_LEFT)) {
            //System.out.println("left");
            game.movePlayer(MoveDirection.LEFT);
        } else if (k == (KeyEvent.VK_RIGHT)) {
            //System.out.println("right");
            game.movePlayer(MoveDirection.RIGHT);
        } else if (k == (KeyEvent.VK_D)) {
            //System.out.println("right");
            game.dropItemForPlayer(null);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
