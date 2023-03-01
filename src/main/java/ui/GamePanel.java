/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import board.Board;
import cards.KeyHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author pawel
 */
public class GamePanel extends JPanel implements Runnable {
    
    
    final int orgiginalTileSize = 16;
    final int scale = 3;
    
    int FPS = 60;
    
    final int tileSize = orgiginalTileSize * scale;
    final int maxScreenCol = 11;
    final int maxScreenRow = 11;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    
    Thread gameThread = new Thread();
    Board board;
    KeyHandler keyH = new KeyHandler();
    
    int playerX = 0;
    int playerY = 0;
    int playerSpeed = 4;
    
    public GamePanel(Board board){
        this.board = board;
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setDoubleBuffered(true);
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while(gameThread!=null){
            
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            
            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
           
                   
        }        
    }
    
    public void update(){
       if(keyH.upPressed){
           playerY -= playerSpeed;
       }  
       else if(keyH.downPressed)
           playerY += playerSpeed;
       else if(keyH.leftPressed)
           playerX -= playerSpeed;
       else if(keyH.rightPressed)
           playerX += playerSpeed;
    }
      
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
       
        int test[][] = board.getBoardLayout();
        for (int row = 0; row < test.length; row++) {    
            for (int col = 0; col < test[row].length; col++) {
                
                switch(test[row][col]){
                    case 0:
                        g2.setColor(Color.red);
                        g2.fillRect(col*tileSize, row*tileSize, tileSize, tileSize);
                        break;
                    case 1:
                        g2.setColor(Color.green);
                        g2.fillRect(col*tileSize, row*tileSize, tileSize, tileSize);
                        break;
                    case 2:
                        g2.setColor(Color.CYAN);
                        g2.fillRect(col*tileSize, row*tileSize, tileSize, tileSize);
                        break;
                    case 3:
                        g2.setColor(Color.BLUE);
                        g2.fillRect(col*tileSize, row*tileSize, tileSize, tileSize);
                        break;
                    case 4:
                        g2.setColor(Color.PINK);
                        g2.fillRect(col*tileSize, row*tileSize, tileSize, tileSize);
                        break;
                    case 5:
                        g2.setColor(Color.WHITE);
                        g2.fillRect(col*tileSize, row*tileSize, tileSize, tileSize);
                        break;
                    case 6:
                        g2.setColor(Color.YELLOW);
                        g2.fillRect(col*tileSize, row*tileSize, tileSize, tileSize);
                        break;
                }
            }
        }
    }
}
                
                  
                        
                        
                
                
            
                /*
                if(test[row][col]== 0){
                    
                    g2.setColor(Color.red);
                    g2.fillRect(col*tileSize, row*tileSize, tileSize, tileSize);
                }
                else{
                    g2.setColor(Color.green);
                    g2.fillRect(col*tileSize, row*tileSize, tileSize, tileSize);
                }
                */
                    
            
    
    

