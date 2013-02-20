package fi.jasteroids.states;

import fi.cebylfwk.math.Point2D;
import fi.cebylfwk.state.State;

import fi.jasteroids.entities.AsteroidEntity;
import fi.jasteroids.entities.ImageEntity;
import fi.jasteroids.entities.ShipEntity;

import fi.jasteroids.entities.ShotEntity;

import java.io.IOException;

import java.net.URL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 * MainGameState is the actual state where the game is played.
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 * @see         State
 */
public class MainGameState extends State {
    //Game statistics variables
    private int level;
    private int score;

    private float shotDelayInSeconds = 0.75f;
    private long lastShotTimeNanos;
    
    //Game entities
    private ShipEntity player;
    private List<AsteroidEntity> activeAsteroids;
    private List<AsteroidEntity> inactiveAsteroids;

    //GameOver variables
    private boolean gameOver;
    private final float gameOverDelayInSeconds = 3.0f;
    private long gameOverStartNanos;

    //HUD variables
    private ImageEntity scoreEntity;
    private HashMap<String, ImageEntity> numberEntityMap;
    private HashMap<String, ImageEntity> scoreDigits;

    public MainGameState(String name) throws IOException {
        super(name);
        player = new ShipEntity(new URL(this.getClass().getResource(".") + "data/ship.png"));
        this.setPlayerStartingPosition();

        activeAsteroids = new ArrayList<AsteroidEntity>();
        inactiveAsteroids = new ArrayList<AsteroidEntity>();
        createAsteroids(4, 4, null, new Point2D(1.0f, 1.0f));
        this.addEntity(player);
        level = 1;
        score = 0;
        gameOver = false;

        scoreEntity = new ImageEntity(new URL(this.getClass().getResource(".") + "data/score.png"));
        this.addEntity(scoreEntity);
        
        numberEntityMap = new HashMap<String, ImageEntity>();
        scoreDigits = new HashMap<String, ImageEntity>();
        initializeNumberEntities();
    
    }
    
    private void updateScoreUI() {
        String scoreStr = String.valueOf(score);
        
        for(int i=scoreStr.length();i>0;i--) {
            ImageEntity number = numberEntityMap.get(String.valueOf(scoreStr.charAt(i - 1)));
            ImageEntity curDigit = scoreDigits.get(String.valueOf(scoreStr.length() + 1 - i));
            curDigit.setImage(number.getImage());
        }
    }
    
    private void initializeNumberEntities() throws IOException {
        //Load all the number images
        for(int i=0;i<10;i++) {
            String number = String.valueOf(i);
            numberEntityMap.put(number, new ImageEntity(new URL(this.getClass().getResource(".") + "data/numbers/"+number+".png")));
        }
        
        //Initialize digit characters shown on screen
        for(int i=5;i>0;i--) {
            ImageEntity digit = new ImageEntity(numberEntityMap.get("0"));
            digit.setPosition(new Point2D(400+digit.getWidth()*(5-i),42));
            scoreDigits.put(String.valueOf(i),digit);
            
            this.addEntity(digit);
        }
    }
    
    /**
     * Player starting position is set to the middle of the screen.
     */
    private void setPlayerStartingPosition() {
        player.setPosition(new Point2D(Display.getWidth() / 2, Display.getHeight() / 2));
    }

    private float fromNanosToSeconds(long timeInNanos) {
        return timeInNanos / 1000000000.0f;
    }

    private void playerShoots() {
        ShotEntity shot = null;
        try {
            shot = new ShotEntity(new URL(this.getClass().getResource(".") + "data/shot.png"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        shot.setShotSpeed(player.getCurSpeed());
        shot.setPosition(player.getPosition());
        shot.setRotation(player.getRotation());

        this.addEntity(shot);
    }

    @Override
    public void processKeyboardInput() {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            this.finished = true;
        }
        if (!gameOver) {
            if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                player.moveUp(0.1);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                player.moveLeft(1.0);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
                player.moveRight(1.0);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT)) {
                shotDelayInSeconds -= 0.01f;
                if(shotDelayInSeconds < 0.0f)
                    shotDelayInSeconds = 0.0f;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_ADD)) {
                shotDelayInSeconds += 0.01f;
            }
            
            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                if (fromNanosToSeconds(System.nanoTime() - lastShotTimeNanos) > shotDelayInSeconds) {
                    playerShoots();
                    lastShotTimeNanos = System.nanoTime();
                }
            }
        }
    }

    @Override
    public void initialize() {
        //Set blending states so images alpha is used also.
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glFrontFace(GL11.GL_CCW);
        GL11.glCullFace(GL11.GL_BACK);
        GL11.glEnable(GL11.GL_CULL_FACE);
    }

    private void createAsteroids(int numMin, int numMax, Point2D startPos, Point2D scale) throws IOException {
        int num = 0;

        if (numMin == numMax) {
            num = numMax;
        } else {
            num = (int)(numMin + Math.random() * numMax);
        }

        for (int i = 0; i < num; i++) {
            AsteroidEntity a = null;
            boolean intersectsWithPlayer = false;
            do {
                Point2D randPos = null;
                if (startPos != null) {
                    randPos = startPos;
                } else {
                    randPos =
                            new Point2D(2 * Math.random() * Math.random() * Display.getDisplayMode().getWidth(), 2 * Math.random() *
                                        Math.random() * Display.getDisplayMode().getHeight());
                }
                float rotation = (float)(Math.random() * 359.0f);
                float rotSpeed = (float)((1.5f + Math.random() * Math.random() % 3f) - (Math.random() % 6f));
                float moveSpeed = (float)(3f + Math.random() * Math.random() % 2f);
                
                a = new AsteroidEntity(new URL(this.getClass().getResource(".") + "data/asteroidi.png"));
    
                a.setPosition(randPos);
                a.setMoveSpeed(moveSpeed);
                a.setRotationSpeed(rotSpeed);
                a.setRotation(rotation);
                a.scale(scale);
                
               if(startPos == null)
                 intersectsWithPlayer =  a.getShape().getBoundingObject().intersects(player.getShape().getBoundingObject());
            
            } while(intersectsWithPlayer);
            this.addEntity(a);

            activeAsteroids.add(a);
        }
    }

    @Override
    public void update(long time) {
        super.update(time);

        if (!gameOver()) {

            for (AsteroidEntity a : activeAsteroids) {
                if (!a.isActive()) {
                    inactiveAsteroids.add(a);
                }
            }

            checkAliveAsteroids();
            updateScoreUI();
        } else {
            //After the delay finish this state and change to next one.
            if (fromNanosToSeconds(System.nanoTime() - gameOverStartNanos) >= gameOverDelayInSeconds) {
                this.finished = true;
            }

        }
    }

    /**
     * Checks if asteroids are alive and generates new ones.
     *
     */
    private void checkAliveAsteroids() {
        activeAsteroids.removeAll(inactiveAsteroids);

        for (AsteroidEntity a : inactiveAsteroids) {
            try {
                Point2D scale = a.getScale();
                scale.setX(scale.getX() * 0.5f);
                scale.setY(scale.getY() * 0.5f);
                if (scale.getX() > 0.25f)
                    createAsteroids(2, 3, a.getPosition(), scale);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        this.score += inactiveAsteroids.size();
        inactiveAsteroids.clear();

        if (activeAsteroids.isEmpty())
            advanceLevel();
    }

    /**
     * Advances to next level inside this state.
     */
    private void advanceLevel() {
        level++;
        this.setPlayerStartingPosition();
        try {
            createAsteroids(4, 4, null, new Point2D(1.0f, 1.0f));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private boolean gameOver() {
        if (!player.isActive() && !gameOver) {
            System.out.println("You got " + score + " scores and reached level " + level);
            gameOver = true;
            gameOverStartNanos = System.nanoTime();

            this.setFPS(10);

            return gameOver;
        }

        return gameOver;

    }
}
