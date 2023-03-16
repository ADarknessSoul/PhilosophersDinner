/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Philosophers implements Runnable {
    
    private final JLabel leftFork;
    private final JLabel rightFork;
    private final Fork ForkRClass;
    private final Fork ForkLClass;
    private final JLabel[] food;
    private final int numPhilo;
    private final JLabel [] thoughts;
    private final JLabel[] forkImages;
    private final JTextField[] philoActions;
    
    public Philosophers(Fork left, Fork right, JLabel leftFork, JLabel rightFork, JLabel[] food, int numPhilo, JLabel[] thoughts, JLabel[] forkImages, JTextField[] philoActions) {
        
        this.ForkRClass = right;
        this.ForkLClass = left;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.food = food;
        this.numPhilo = numPhilo;
        this.thoughts = thoughts;
        this.forkImages = forkImages;
        this.philoActions = philoActions;
        
    }

    @Override
    public void run() {
        
        try{
            
            while(true) {
                
                doAction(philoActions[numPhilo], "Thinking...");
                synchronized(ForkLClass) {
                    
                    pickFork("left");
                    doAction(philoActions[numPhilo], "Picked left fork");    
                    
                    synchronized(ForkRClass) {
                          
//                          pickFork(this.rightFork);
                          doAction(philoActions[numPhilo], "Eating...");    
                          eat();
                          doAction(philoActions[numPhilo], "Put down right fork");    
                          letFork("left");
                        
                    }
                    
                }
                
                think();
                
                doAction(philoActions[numPhilo], "Put down left fork");    
//                  letFork(this.leftFork);
                  
                
            }
            
        } catch(InterruptedException e) {
            
            Thread.currentThread().interrupt();
            
        }
        
    }
    
    private void doAction(JTextField philo, String action) throws InterruptedException {
        
        philo.setText(action);
        Thread.sleep(((int)(Math.random() * 500)));
        
    }
    
    private void eat() throws InterruptedException {
        
        ForkRClass.decrease();
        pickFork("right");
        thoughts[numPhilo].setVisible(false);
        food[numPhilo].setVisible(true);
//        if(numPhilo == 0) Thread.sleep(500);
        Thread.sleep(((int)(Math.random() * 3000)));
        food[numPhilo].setVisible(false);
        
    }
    
    private void think() throws InterruptedException {
        
        ForkRClass.increase();
        letFork("right");
        thoughts[numPhilo].setVisible(true);
        Thread.sleep(((int)(Math.random() * 2000)));

    }
    
    private void pickFork(String fork) throws InterruptedException {
                  
          int place = numPhilo;
        
          if("left".equals(fork)) {
              
//                forkImages[place*2].setVisible(true);
                forkImages[place*2].setBorder(BorderFactory.createLineBorder(Color.RED));
//              System.out.print("Valor de numPhilo después: " + numPhilo);
        
          } else if("right".equals(fork)) {
              
//                forkImages[(place*2) + 1].setVisible(true);
                forkImages[(place*2) + 1].setBorder(BorderFactory.createLineBorder(Color.RED));
              
          }
        
    }
    
    private void letFork(String fork) throws InterruptedException {
        
          int place = numPhilo;
        
          if("left".equals(fork)) {
              
//                forkImages[place*2].setVisible(false);
                forkImages[place*2].setBorder(BorderFactory.createLineBorder(Color.GREEN));
//              System.out.print("Valor de numPhilo después: " + numPhilo);
        
          } else if("right".equals(fork)) {
              
//            forkImages[(place*2) + 1].setVisible(false);
            forkImages[place*2 + 1].setBorder(BorderFactory.createLineBorder(Color.GREEN));
              
          }
        
    }
    
}
