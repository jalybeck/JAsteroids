package fi.jasteroids.states;

import fi.cebylfwk.graphics.Color;
import fi.cebylfwk.graphics.Font;
import fi.cebylfwk.graphics.Renderer;
import fi.cebylfwk.lwjgl.FontLWJGL;
import fi.cebylfwk.math.Point2D;
import fi.cebylfwk.state.State;

import fi.jasteroids.entities.AsteroidEntity;

import fi.jasteroids.entities.ImageEntity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import java.util.Map;

import org.lwjgl.input.Keyboard;

/**
 * HighScoreState is shown after game ends.
 * There user may write his name and see the score
 *
 * @author      Jari Lybeck
 * @version     %I%, %G%
 * @see         State
 */
public class HighScoreState extends State {
    /**
     * Inner class which is used for sortin scores. 
     */
    private class Score {
        public Score(String name, int score, boolean current) {
            this.name = name;
            this.score = score;
            this.current = current;
        }
        String name;
        int score;
        boolean current;
    }
    /** Caret On character. */
    private final char CARET_ON;
    /** Caret off character. */
    private final char CARET_OFF;
    /** Maximum number of names in highscore list. */
    private final int  MAX_NAMES_IN_LIST;
    /** High score filename. */
    private final String HIGH_SCORE_FILENAME;
    /** Maximun name length. */
    private final int  MAX_NAME_LENGTH;
    /** Score length. */
    private final int  SCORE_LENGTH;
    
    /** Font to be used. */
    private Font verdanaFont;
    /**Players name. */
    private String playerName;
    
    /** Current caret. */
    private char curCaret;
    /**Score entites are in this list. */
    private List<Score> highScoreList;
    /** Caret blink time in nanos. */
    private long caretBlinkTime;
    
    /** Players score. */
    private String playerScore;
    
    /** High score text shown on screen. */
    private ImageEntity highScoreText;
    /** High score text update movement. */
    private int updateMovement;
    
    /** High score list Y position on screen. */
    private float scoreYPosition;
    
    public HighScoreState(String name) throws IOException {
        super(name);
        CARET_ON = '_';
        CARET_OFF = '.';
        MAX_NAMES_IN_LIST = 10;
        HIGH_SCORE_FILENAME = "hiscore.txt";
        MAX_NAME_LENGTH = 30;
        SCORE_LENGTH = 5;
        
        playerName = "";
        curCaret = CARET_ON;
        
        highScoreList = new ArrayList<Score>();
        highScoreText = new ImageEntity(this.getClass().getResource("data/highscore.png"));
        this.addEntity(highScoreText);
        highScoreText.setPosition(new Point2D(-100,100));
        
        scoreYPosition = 800;
    }

    @Override
    public void initialize(Map<String, String> parameters) {
        verdanaFont = new FontLWJGL(new java.awt.Font("Courier", java.awt.Font.BOLD,32), false);
        Keyboard.enableRepeatEvents(true);
        loadHighScoreFile(HIGH_SCORE_FILENAME);
        
        this.playerScore = parameters.get("Score");
        
        highScoreList.add(new Score("",Integer.parseInt(this.playerScore),true));
        this.sortScoreList();
        highScoreList.subList(10, highScoreList.size()).clear();

    }

    @Override
    public void processKeyboardInput() {
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
            this.finished = true;
            saveHighScoreFile(HIGH_SCORE_FILENAME);
            return;
        }
        
        while(Keyboard.next()) {
            if(Keyboard.getEventKeyState()) {
                if(Keyboard.getEventKey() == Keyboard.KEY_BACK) {
                    if(playerName.length() > 0)
                        playerName = playerName.substring(0, playerName.length() - 1);
                } else {
                    switch(Keyboard.getEventKey()) {
                        case Keyboard.KEY_LSHIFT:
                        case Keyboard.KEY_RSHIFT:
                        case Keyboard.KEY_TAB:
                        case Keyboard.KEY_RETURN:
                        case Keyboard.KEY_HOME:
                        case Keyboard.KEY_DELETE:
                        case Keyboard.KEY_PAUSE:
                        case Keyboard.KEY_F1:
                        case Keyboard.KEY_F2:
                        case Keyboard.KEY_F3:
                        case Keyboard.KEY_F4:
                        case Keyboard.KEY_F5:
                        case Keyboard.KEY_F6:
                        case Keyboard.KEY_F7:
                        case Keyboard.KEY_F8:
                        case Keyboard.KEY_F9:
                        case Keyboard.KEY_F10:
                        case Keyboard.KEY_F11:
                        case Keyboard.KEY_F12:
                        case Keyboard.KEY_INSERT:
                        case Keyboard.KEY_LCONTROL:
                        case Keyboard.KEY_RCONTROL:
                        case Keyboard.KEY_UP:
                        case Keyboard.KEY_LEFT:
                        case Keyboard.KEY_RIGHT:
                        case Keyboard.KEY_DOWN:
                        case Keyboard.KEY_END:
                            break;
                        default:
                            checkPlayerNameLength(Keyboard.getEventCharacter(), MAX_NAME_LENGTH);
                    }
                   
                }
            }
        }
    }
    
    /**
     * Inserts prefix padding to string.
     * 
     * @param str
     * @param prefix
     * @param count
     * @return
     */
    private String padString(String str, char prefix, int count) {
        StringBuffer sb = new StringBuffer();
        
        for(int i=0;i<count - str.length();i++) {
            sb.append(prefix);
        }
        
        sb.append(str);
        
        return sb.toString();
    }
    
    /**
     * Creates and formats score line shown on screen.
     * 
     * @param lineNum
     * @param playerName
     * @param playerScore
     * @param showCaret
     * @return
     */
    private String createScoreLine(int lineNum,String playerName, String playerScore, boolean showCaret) {
        StringBuffer sb = new StringBuffer();
        int showCaret_num = showCaret ? -1 : 0;
        
        sb.append(lineNum);
        sb.append(".");
        if(lineNum == 10) {
            sb.append(" ");
        } else {
            sb.append("  ");
        }
        sb.append(playerName.toUpperCase());
        if(showCaret) {
            sb.append(this.curCaret);
        }
        for(int i=0;i<45-playerName.length()+showCaret_num;i++) {
            sb.append(".");
        }
        sb.append(padString(playerScore,'0',SCORE_LENGTH));

        return sb.toString();
        
        
    }
    /**
     * Restricts player name to given length.
     * 
     * @param charToAdd
     * @param length
     */
    private void checkPlayerNameLength(char charToAdd,int length) {
        if(playerName.length() < length) {
            playerName += charToAdd;
        }
    }
    
    @Override
    public void update(long time) {
        super.update(time);
        
        if(highScoreText.getPosition().getX() < 340) {
            highScoreText.getPosition().addTo(Math.sin(updateMovement++ / 40.0) * 100.0,0);
        }
        
        if(scoreYPosition > 130) {
            scoreYPosition-= 8;
        }
        
        if(this.fromNanosToSeconds(System.nanoTime() - caretBlinkTime) >= 0.5f) {
            if(curCaret == CARET_ON) {
                curCaret = CARET_OFF;
            }
            else {
                curCaret = CARET_ON;
            }
            caretBlinkTime = System.nanoTime();
        }
        
    }
    
    @Override
    public void render(Renderer r, long time) {
        super.render(r, time);
        
        int i = 1;
        for(Score sc : highScoreList) {
            boolean drawCaret = false;
            if(sc.current) {
                drawCaret = true;
                sc.name = this.playerName;
            }
            r.drawString(createScoreLine(i++,sc.name, String.valueOf(sc.score), drawCaret), 150, (int)scoreYPosition+i*30, verdanaFont, new Color(1.0f,1.0f,0.0f, 1.0f));
        }
        
    }
    
    /**
     * Sorts high score list so highest is first.
     */
    private void sortScoreList() {
        Collections.sort(highScoreList, new Comparator<Score>() {

            public int compare(Score o1, Score o2) {
                int val = 0;
                if(o1.score > o2.score)
                    val = -1;
                else if(o1.score < o2.score) 
                    val = 1;
                return val;
            }
        });
    }
    
    /**
     * Adds empty lines to high score list.
     * Maximum is 10.
     * 
     */
    private void addEmptyLinesToScoreList() {
        int listSize = highScoreList.size();
        for(int i=0;i< MAX_NAMES_IN_LIST - listSize;i++) {
            highScoreList.add(new Score("",0,false));
        }        
    }
    
    /**
     * Loads high score list from file.
     * 
     * @param fileName
     */
    private void loadHighScoreFile(String fileName) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String []split = line.split(";");
                Score sc = new Score(split[0] == null ? "" : split[0], Integer.parseInt(split[1]),false);
                highScoreList.add(sc);
            }
        } catch (IOException e) {
            System.err.println("Cannot read highscore file: "+fileName+"\n Error: "+e.getMessage());
        } finally {
            try {
                br.close();
            } catch(Exception e) {
                //No can do!
            }
        }
        
        addEmptyLinesToScoreList();
       
    }
    
    /**
     * Saves high scores to file.
     * 
     * @param fileName
     */
    private void saveHighScoreFile(String fileName) {
        FileWriter fw = null;

        try {
            fw = new FileWriter(fileName);
            for(Score sc : highScoreList) {
                fw.write(sc.name+";"+sc.score);
                fw.write(System.getProperty("line.separator"));
            }
            fw.flush();
        } catch (IOException e) {
            System.err.println("Cannot write to highscore file: "+fileName+"\n Error: "+e.getMessage());
        } finally {
            try {
                fw.close();
            } catch(Exception e) {
                //No can do!
            }
        }
    }

    @Override
    public Map<String, String> getParametersForNextState() {
        return Collections.emptyMap();
    }
}
