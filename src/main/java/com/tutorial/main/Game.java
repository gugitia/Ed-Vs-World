package com.tutorial.main;

import javax.sound.sampled.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    @Serial
    private static final long serialVersionUID = 4372715600171905470L;

    public static final int WIDTH = 1240, HEIGHT = WIDTH / 12*6;
    private Thread thread;
    private boolean running = false;
    private Random r;
    private Handler handler;
    public static int killCount = 0;
    private Clip backgroundMusic;

    public enum GameState {
        MENU,
        PLAYING,
        GAME_OVER
    }

    static GameState gameState = GameState.MENU;

    public Game(){
        handler = new Handler();
        loadMusic();
        Enemy.loadImages();
        try {
            Explosion.loadExplosionImages();
        } catch (IOException e) {
            e.printStackTrace(); // Mostra o erro no console
        }

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseAdapter() { // Adiciona um MouseListener para reiniciar o jogo

            public void mousePressed(MouseEvent e) {
                if (gameState == GameState.MENU) {
                    gameState = GameState.PLAYING;

                    startGame();
                }
                else if (gameState == GameState.GAME_OVER) {
                    gameState = GameState.MENU;
                    startGame();
                }
            }
        });

        new Window(WIDTH, HEIGHT, "Ed vs World", this);
        r = new Random();


    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try{
            thread.join();
            running = false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
            }
            frames++;
            if(System.currentTimeMillis() - timer > 1000 ){
                timer+= 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
        if (gameState == GameState.PLAYING) {
            if (!backgroundMusic.isRunning()) {
                playMusic(); // Inicia a música se não estiver tocando
            }
        } else {
            stopMusic(); // Para a música se não estiver jogando
            loadMusic();
        }
    }

    private void startGame() {
        handler.clearAllObjects();
        handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, Id.Player, handler));
        handler.addObject(new Enemy(200, 200, Id.Enemy));
        killCount = 0;
    }

    public void loadMusic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/audio/p5lwc.wav")); // Ajuste o caminho
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY); // Toca a música em loop
        }
    }

    public void stopMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop(); // Para a música
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if (gameState == GameState.PLAYING) {
            handler.render(g);
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Abates: " + killCount, 10, 30);

        } else if (gameState == GameState.MENU) {

            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("EDNALDO PEREIRA AGAIST THE WORLD", WIDTH / 2 - 500, HEIGHT / 2 - 100);
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Clique para começar", WIDTH / 2 - 100, HEIGHT / 2);
        }
        else if (gameState == GameState.GAME_OVER) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Game Over", WIDTH / 2 - 100, HEIGHT / 2 - 100);
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Abates: " + killCount, WIDTH / 2 - 60, HEIGHT / 2 - 50);
            g.drawString("Clique para reiniciar", WIDTH / 2 - 100, HEIGHT / 2);
        }

        g.dispose();
        bs.show();
    }

    public static void main(String args[]) {
        new Game();
    }
}
