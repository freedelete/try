package algorithm;

import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;   

public class BruteForce {  
    //private static final String TARGET_PLAINTEXT = "101010101"; // 目标明文（示例）  
    //private static final String TARGET_CIPHERTEXT = "11101110"; // 目标密文（示例）  
    private static long startTime;  
    public static int num=0;

    public static void b_force(String TARGET_PLAINTEXT,String TARGET_CIPHERTEXT) {  
        startTime = System.currentTimeMillis();  

        for (int currentKey = 0; currentKey < Math.pow(2, 10); currentKey++) {  
            String binaryKey = String.format("%10s", Integer.toBinaryString(currentKey)).replace(' ', '0');  
            SimpleDES.getkey(binaryKey); // 设置当前密钥  
            SimpleDES.encrypt(TARGET_PLAINTEXT);
            String ciphertext = SimpleDES.ciphertext; // 加密过程  

            // 检查加密结果  
            if (ciphertext.equals(TARGET_CIPHERTEXT)) {  
                long endTime = System.currentTimeMillis(); // 结束时间  
                //System.out.println("miwen: " + ciphertext);  
                System.out.println("Found key: " + binaryKey);  
                System.out.println("Brute force completed in " + (endTime - startTime) + " milliseconds.");   
                num++;
                continue; 
            }  
            // 可以选择性输出  
            // System.out.println("Tried key: " + binaryKey + ", resulting ciphertext: " + ciphertext);  
        }  
        if(num==0) {
        	System.out.println("Found no key");
        }
        //System.out.println(num);
    }  
}  

