package main;

import entity.Player;
import handler.CollisionHandler;
import handler.KeyHandler;
import handler.MapHandler;
import object.ObjectManager;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //// Window Settings
    private final int originalTileSize = 16;

    // 64x64 Tile
    private final int scale = 4;
    public final int tileSize = originalTileSize * scale;

    private final int columns = 16;
    private final int rows = 12;

    // 1152x768 Window -> 4:3 Aspect Ratio
    public final int windowX = tileSize * columns;
    public final int windowY = tileSize * rows;

    // FPS / Map
    final int FPS = 60;
    private final MapHandler mapH = MapHandler.mapIsland;

    //// Instantiate
    private Thread gameThread;
    private final KeyHandler keyH = new KeyHandler();
    private final CollisionHandler collisionH = new CollisionHandler(this);

    public UI ui = new UI(this);
    public TileManager tileM = new TileManager(this, mapH);
    public Player player = new Player(this, keyH, mapH, collisionH);
    public ObjectManager objHandler = new ObjectManager(this, mapH);


    public GamePanel() {
        this.setPreferredSize(new Dimension(windowX, windowY));
        this.setBackground(Color.black);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        startGameThread();
    }
    private void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    // Game Loop
    @Override
    @SuppressWarnings("all")
    public void run() {
        double timePerFrame = 1000000000d / FPS; // In nanoseconds
        double endFrameTime = System.nanoTime() + timePerFrame;

        while (gameThread != null) {

            // Step 1: Update data
            update();

            // Step 2: Repaint screen
            repaint();

            // Step 3: Maintain stable FPS
            try {
                double remainingTime = endFrameTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; // Change to milliseconds

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                endFrameTime += timePerFrame;

            } catch (InterruptedException e) {e.printStackTrace();}

        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        draw(g2d);

        g2d.dispose();
    }


    // Update and Draw methods
    private void update() {
        player.update();

    }
    private void draw(Graphics2D g2d) {
        tileM.draw(g2d);
        objHandler.draw(g2d);
        player.draw(g2d);
        ui.draw(g2d);

    }
}
