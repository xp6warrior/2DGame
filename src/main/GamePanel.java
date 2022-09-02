package main;

import entity.Player;
import entity.npc.NPC;
import entity.npc.NpcManager;
import handler.CollisionHandler;
import handler.KeyHandler;
import handler.SoundHandler;
import object.ObjectManager;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Game Settings
    final int FPS = 60;

    // GameStates
    public final int titleState = -1;
    public final int playState = 0;
    public final int pauseState = 1;
    private int currentGameState = titleState;

    // Instantiate
    Thread gameThread;
    public KeyHandler keyH = new KeyHandler(this);
    public CollisionHandler collisionH = new CollisionHandler(this);
    public SoundHandler music = new SoundHandler();
    public SoundHandler soundEffects = new SoundHandler();
    public UIManager ui = new UIManager(this);
    public TileManager tileM = new TileManager(this);
    public ObjectManager objHandler = new ObjectManager(this);
    public NpcManager npcHandler = new NpcManager(this);
    public Player player = new Player(this);

    // Debug
    public long drawTime;


    //// Constructor
    public GamePanel() {
        this.setPreferredSize(new Dimension(Util.windowX, Util.windowY));
        this.setBackground(Color.black);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        tileM.switchLevel("title");

        gameThread = new Thread(this);
        gameThread.start();
    }


    //// Changing game-states
    public int getGameState() {
        return currentGameState;
    }
    public void setGameState(int state) {
        // Before GameState change
        if (currentGameState == titleState) {
            ui.displayTitleMenu = false;
            tileM.switchLevel("island");
        }
        currentGameState = state;

        // After GameState change
        switch (currentGameState) {
            case playState: music.play(); music.loop(); ui.displayPauseMenu = false; break;
            case pauseState: music.stop(); ui.displayPauseMenu = true; break;
        }
    }


    //// Game loop
    @Override
    @SuppressWarnings("all")
    public void run() {
        double timePerFrame = 1000000000d / FPS; // timePerFrame in nanoseconds
        double endFrameTime = System.nanoTime() + timePerFrame;

        while (gameThread != null) {
            // 1. Update
            if (currentGameState == playState) {
                update();
            }
            // 2. Repaint
            repaint();

            // 3. Maintain FPS
            try {
                double remainingTime = (endFrameTime - System.nanoTime()) / 1000000; // Change to milliseconds
                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                endFrameTime += timePerFrame;

            } catch (InterruptedException e) {e.printStackTrace();}
        }
    }

    //// Update and Draw
    private void update() {
        // 1. Player
        player.update();

        // 2. NPCs
        for (NPC npc : npcHandler.NPCs) {
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
