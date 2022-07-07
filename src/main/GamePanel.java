package main;

import entity.Player;
import handler.*;
import object.ObjectManager;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {

    //// Window Settings
    private final int originalTileSize = 16;

    // 64x64 Tile
    private final int scale = 4;
    public final int tileSize = originalTileSize * scale;

    private final int columns = 20;
    private final int rows = 12;

    // 1280x768 Window -> 16:9 Aspect Ratio
    public final int windowX = tileSize * columns;
    public final int windowY = tileSize * rows;

    //// Game Settings
    final int FPS = 60;
    public MapHandler mapH = MapHandler.mapIsland;

    // Game States
    public final int playState = 0;
    public final int pauseState = 1;
    public final int resumeState = 2;

    public int currentGameState = playState;

    //// Instantiate
    private Thread gameThread;
    public final KeyHandler keyH = new KeyHandler();
    public final CollisionHandler collisionH = new CollisionHandler(this);
    public final UI ui = new UI(this);
    public final TileManager tileM = new TileManager(this);
    public final ObjectManager objHandler = new ObjectManager(this);
    public final Player player = new Player(this);
    public final SoundHandler music = new SoundHandler();
    public final SoundHandler soundEffects = new SoundHandler();

    //// Debug
    public long drawTime;

    public GamePanel() {
        this.setPreferredSize(new Dimension(windowX, windowY));
        this.setBackground(Color.black);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        startGameThread();
        startMusic();
    }
    private void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    private void startMusic() {
        music.setFile(mapH.musicID);
        music.play();
        music.loop();
    }


    // Game Loop
    @Override
    @SuppressWarnings("all")
    public void run() {
        double timePerFrame = 1000000000d / FPS; // In nanoseconds
        double endFrameTime = System.nanoTime() + timePerFrame;

        while (gameThread != null) {
            // Step 1: Update
            update();

            // Step 2: Paint
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

    // Update and Draw methods
    private void update() {

        // Pressing the pause button
        if (keyH.pause) {
            currentGameState = pauseState;
        } else if (currentGameState == pauseState){
            currentGameState = resumeState;
        }

        // The actual pausing
        if (currentGameState == playState) { // PLAY
            player.update();

        } else if (currentGameState == pauseState) { // PAUSE
            music.stop();

        } else if (currentGameState == resumeState) { // RESUME (fist frame after resuming)
            music.play();
            currentGameState = playState;

        }

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        long startDrawTime = System.nanoTime();

        tileM.draw(g2d);
        objHandler.draw(g2d);
        player.draw(g2d);
        ui.draw(g2d);

        drawTime = System.nanoTime() - startDrawTime;

        g.dispose();
    }
}
