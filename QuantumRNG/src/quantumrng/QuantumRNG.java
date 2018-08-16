/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quantumrng;

/**
 *
 * @author Sean
 */
public class QuantumRNG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AnuRandom anuRandom = new AnuRandom(2048);
        byte[] temp = anuRandom.getBytes();
        for (byte b : temp){
            System.out.println(b);
        }
    }
    
}
