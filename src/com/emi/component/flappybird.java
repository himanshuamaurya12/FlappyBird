
package com.emi.component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;


public class flappybird extends javax.swing.JPanel implements KeyListener {

     private final Image backgroundimg;
     private final Image toppipeimg;
     private final Image bottompipeimg;
     private final Image birdimage;
     private bird Bird;
     private Pipes pipe;
     private Animator animator;
     private int birdvelocityY=0;
     private int gravity=1;
     private final int pipevelocityX=-2;
     boolean gameOver=false;
     double score=0;
     Timer timer;
     Random random=new Random();
     
     //as we have  many pipes we will store them in arratlist and call them on by one towrds -4 on  the x axis;
     ArrayList<Pipes>pipes;
     
     
    public flappybird()  {
         try{
             File file=new File("C:/Users/Himan/OneDrive/Desktop/cont/FlappyBird/src/com/emi/component/background.wav");
             AudioInputStream audioStream=AudioSystem.getAudioInputStream(file);
             Clip clip=AudioSystem.getClip();
             
             clip.open(audioStream);
             clip.start();
         }catch(Exception ae){
             ae.printStackTrace();
         }
        initComponents();
        setSize(360,640);
        setFocusable(true);
        addKeyListener(this);
        
        backgroundimg=new ImageIcon(getClass().getResource("/com/emi/icons/flappybirdbg.png")).getImage();
        toppipeimg=new ImageIcon(getClass().getResource("/com/emi/icons/toppipe.png")).getImage();
        bottompipeimg=new ImageIcon(getClass().getResource("/com/emi/icons/bottompipe.png")).getImage();
        birdimage=new ImageIcon(getClass().getResource("/com/emi/icons/flappybird.png")).getImage();
        Bird=new bird(birdimage,new Dimension(34,24),new Point(360/8,640/2));
        pipes=new ArrayList<>();

         timer=new Timer(1500,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
                
            }
        });
        timer.start();

        TimingTarget target=new TimingTargetAdapter(){
            float aplha=0f;
            @Override
           public void timingEvent(float fraction){
                
               move();
                repaint();
                if(gameOver){
                    timer.stop();
                    animator.stop();
                }
            }     
        };
        animator=new Animator(1000/60,target);
        animator.setRepeatCount(Animator.INFINITE);
                animator.start();

    }


    @SuppressWarnings("unchecked")
    
     @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
           draw(g);
    }
    
    public void draw(Graphics g){
          g.drawImage(backgroundimg, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(Bird.getImg(),Bird.getLocation().x,Bird.getLocation().y,Bird.getSize().width,Bird.getSize().height, null);
        
                for(int i=0;i<pipes.size();i++){
            Pipes pipe=pipes.get(i);
            g.drawImage(pipe.getImg(),pipe.x, pipe.y, pipe.width, pipe.height, null);
        }
                g.setColor(new Color(44, 62, 80));
                g.setFont(new Font("sansserif",1,30));
                if(gameOver){
                    g.drawString("Game Over :"+ String.valueOf((int) score), 10,35);
                }
                else{
                    g.drawString(String.valueOf((int)score), 10,35);
                }
    }
    
    public void move() {
        birdvelocityY+=gravity;
        Bird.getLocation().y+=birdvelocityY;
        Bird.getLocation().y=Math.max(Bird.getLocation().y, 0);
        
       for(int i=0;i<pipes.size();i++){
           Pipes pipe=pipes.get(i);
           pipe.x+=pipevelocityX;

            
           if(!pipe.passed&&Bird.getLocation().x>pipe.x+pipe.width){
               pipe.passed=true;
               score+=0.5;
           }
           if(collision(Bird,pipe)){
               gameOver =true;
               
           }
       }
       if(Bird.getLocation().y>640){
           gameOver=true;
       }
    }
    
    public void placePipes(){
         Pipes toppipe=new Pipes(toppipeimg);
         int randompipeY=(int)(0-(512/4)-Math.random()*(512/2));
         int openingspace=512/4;
         toppipe.y=randompipeY;
        pipes.add(toppipe);
        Pipes bottompipe=new Pipes(bottompipeimg);
        bottompipe.y=toppipe.y+openingspace+512;
        pipes.add(bottompipe);
    }
    public boolean collision(bird a,Pipes b){
        return a.getLocation().x<b.x+b.width&&
                a.getLocation().x+a.getSize().width>b.x&&
                a.getLocation().y<b.y+b.height&&
                a.getLocation().y+a.getSize().height>b.y;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
     @Override
     public void keyPressed(KeyEvent e){
         if(e.getKeyCode()==KeyEvent.VK_SPACE){
             birdvelocityY=-9;
         if(gameOver){
             Bird.getLocation().y=640/2;
             birdvelocityY=0;
             pipes.clear();
             score=0;
             gameOver=false;
             timer.start();
             animator.start();
         }  
         }
     }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    

   



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
