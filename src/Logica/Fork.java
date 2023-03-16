/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;


public class Fork {
    private int value = 1;
            
            public Fork() {
                
                value = 1;
                
            }
            
        synchronized void decrease() throws InterruptedException {
            
//            System.out.println(value);
            
            while(value  <= 0) {
                
                wait();
                
            }
            
            value--;
            
//            System.out.println(value);
            
        }
        
        synchronized void increase() {
            
            System.out.println(value);
            
            value++;
            notify();
            
        }
        
        public int Valor() {
            
            return value;
            
        }
    
}
