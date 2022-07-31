package main;

import entity.Entity;
import entity.NpcManager;
import entity.Player;
import handler.CollisionHandler;
import handler.KeyHandler;
import handler.SoundHandler;
import object.ObjectManager;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //// Game Settings
    final int FPS = 60;

    // Game States
    public final int playState = 0;
    public final int pauseState = 1;
    public final int resumeState = 2;
    public final int titleState = 3;
    public int currentGameState = titleState;

    //// Instantiate
    Thread gameThread;
    public KeyHandler keyH = new KeyHandler();
    public CollisionHandler collisionH = new CollisionHandler(this);
    public SoundHandler music = new SoundHandler();
    public SoundHandler soundEffects = new SoundHandler();
    public UIManager ui = new UIManager(this);
    public TileManager tileM = new TileManager(this);
    public ObjectManager objHandler = new ObjectManager(this);
    public NpcManager npcHandler = new NpcManager(this);
    public Player player = new Player(this);

    //// Debug
    public long drawTime;

    public GamePanel() {
        this.setPreferredSize(new Dimension(Util.windowX, Util.windowY));
        this.setBackground(Color.black);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        tileM.switchLevel("title");

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

            // Pressing the pause button
            if (keyH.pause && currentGameState == titleState) {
                keyH.pause = false;
                tileM.switchLevel("island");
                currentGameState = playState;
            } else if (keyH.pause && currentGameState == playState) {
                keyH.pause = false;
                currentGameState = pauseState;
            } else if (keyH.pause && currentGameState == pauseState) {
                keyH.pause = false;
                currentGameState = resumeState;
            }

            switch (currentGameState) {
                case playState: update(); break;
                case pauseState: music.stop(); break;
                case resumeState: music.play(); music.loop(); currentGameState = playState; break;
            }

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
        player.update();

        for (Entity npc : npcHandler.NPCs) {
            if (npc != null) {
                npc.update();
            }
        }

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        long startDrawTime = System.nanoTime();

        tileM.draw(g2d, currentGameState == pauseState);
        if (currentGameState != titleState) {
            objHandler.draw(g2d);
            npcHandler.draw(g2d);
            player.draw(g2d);
        }
        ui.draw(g2d);

        drawTime = System.nanoTime() - startDrawTime;

        g2d.dispose();
    }
}
