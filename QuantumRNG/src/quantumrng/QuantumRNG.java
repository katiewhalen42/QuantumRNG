/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quantumrng;

import java.util.Random;
import java.util.Stack;
import java.lang.Math;

/**
 *
 * @author Sean
 */
public class QuantumRNG {
    static final int BYTE_SIZE = 2048;
    static final int MAX_ITER = 100;
    static Stack<Byte> bytes = new Stack();
    static AnuRandom anuRandom = new AnuRandom(BYTE_SIZE);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 = 0;
        int count7 = 0;
        int count8 = 0;
        int count9 = 0;
        int count10 = 0;
        for (int i = 0; i < 10000; i++){
            int result = qRoll(1, 10);
            switch(result){
                case 1: count1++;
                        break;
                case 2: count2++;
                        break;
                case 3: count3++;
                        break;
                case 4: count4++;
                        break;
                case 5: count5++;
                        break;
                case 6: count6++;
                        break;
                case 7: count7++;
                        break;
                case 8: count8++;
                        break;
                case 9: count9++;
                        break;
                case 10: count10++;
                        break;
            }
        }
        System.out.println("1: " + count1);
        System.out.println("2: " + count2);
        System.out.println("3: " + count3);
        System.out.println("4: " + count4);
        System.out.println("5: " + count5);
        System.out.println("6: " + count6);
        System.out.println("7: " + count7);
        System.out.println("8: " + count8);
        System.out.println("9: " + count9);
        System.out.println("10: " + count10);
    }
    
    static int rejectionSampling(int range){
        Random random = new Random();
        if (range > 255){
            System.out.println("This application does not currently support ranges greater than 255 for quantum randomness. Using pseudorandom number generator.");
            return random.nextInt(range);
        }
        int sample;
        int iterCount = 0;
        do {
            sample = nextByte();
            if(iterCount >= MAX_ITER){
                System.out.println("Max iterators reached. Check that server is working. Using psedorandom nuber generator.");
                return random.nextInt(range);
            }
            iterCount++;
        } while (sample > range);
        return sample;
    }
    
    static int qRoll(int min, int max){
        if (min <= max){
            int range = (max - min) + 1;
            int expandedRange = (int) (Math.floor(256/range) * range);
            //System.out.println("Expended range: " + expandedRange);
            int sample = rejectionSampling(expandedRange);
            //System.out.println("Sample: " + sample);
            return (sample % range) + min;
        }
        else {
            return -1;
        }
    }
    
    static void loadBytes(){
        bytes.clear();
        byte[] byteArray = anuRandom.getBytes();
        for (byte b : byteArray){
            bytes.add(b);
        }
    }
    
    static byte nextByte(){
        if (bytes.empty()){
            loadBytes();
        }
        return bytes.pop();
    }
}

class RandIterException extends Exception{
    public RandIterException(){
        super();
    }
    
    public RandIterException(String message){
        super(message);
    }
}
