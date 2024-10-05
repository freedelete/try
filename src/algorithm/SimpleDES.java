package algorithm;

import java.awt.EventQueue;
import java.util.Scanner;

import frame.Encrypt;  

public class SimpleDES {  
    public static String key1, key2;  
    public static String ciphertext;
    public static String plaintext;
    public static String encryptedResult;
    public static String decryptedResult;
    public static int[] P10 = new int[]{3, 5, 2, 7, 4, 10, 1, 9, 8, 6};  
    public static int[] P8 = new int[]{6, 3, 7, 4, 8, 5, 10, 9};  
    public static int[] IP = new int[]{2, 6, 3, 1, 4, 8, 5, 7};  
    public static int[] EP = new int[]{4, 1, 2, 3, 2, 3, 4, 1};  
    public static int[] P4 = new int[]{2, 4, 3, 1};  
    public static int[] IP_1 = new int[]{4, 1, 3, 5, 7, 2, 8, 6};  
    public static String[][] S1_box = new String[][]{  
        {"01", "00", "11", "10"},  
        {"11", "10", "01", "00"},  
        {"00", "10", "01", "11"},  
        {"11", "01", "00", "10"}  
    };  
    public static String[][] S2_box = new String[][]{  
        {"00", "01", "10", "11"},  
        {"10", "11", "01", "00"},  
        {"11", "00", "01", "10"},  
        {"10", "01", "00", "11"}  
    };  

    public static String substitue(String str, int[] P) {  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < P.length; i++) {  
            sb.append(str.charAt((P[i]) - 1));  
        }  
        return sb.toString();  
    }  

    public static String xor(String str, String key) {  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < str.length(); i++) {  
            if (str.charAt(i) == key.charAt(i)) {  
                sb.append("0");  
            } else {  
                sb.append("1");  
            }  
        }  
        return sb.toString();  
    }  

    public static String searchSbox(String str, int n) {  
        String row = "" + str.charAt(0) + str.charAt(3);  
        String col = "" + str.charAt(1) + str.charAt(2);  
        int sRow = Integer.parseInt(row, 2);  
        int sCol = Integer.parseInt(col, 2);  
        return n == 1 ? S1_box[sRow][sCol] : S2_box[sRow][sCol];  
    }  

    public static void getkey(String str) {  
        //System.out.println("-----请输入主密钥(10位)------");  
        String mainkey = str;  
        mainkey = substitue(mainkey, P10);  
        String Ls11 = mainkey.substring(0, 5);  
        String Ls12 = mainkey.substring(5, 10);  
        key1 = substitue(move(Ls11, 1) + move(Ls12, 1), P8);  
        //System.out.println("key1= " + key1);  
        key2 = substitue(move(Ls11, 2) + move(Ls12, 2), P8);  
        //System.out.println("key2= " + key2);  
    }  

    public static String move(String str, int n) {  
        // n 为左移的位数  
        return str.substring(n) + str.substring(0, n);  
    }  

    public static void encrypt(String str) {  
        //System.out.println("-----请输入要加密的信息(8位)------");  
        String plaintext =str;  
        plaintext = substitue(plaintext, IP);  
        String L0 = plaintext.substring(0, 4);  
        String R0 = plaintext.substring(4, 8);  

        // 第一轮加密  
        String R0E = substitue(R0, EP);  
        R0E = xor(R0E, key1); // 使用 key1  

        String S1 = R0E.substring(0, 4);  
        String S2 = R0E.substring(4, 8);  
        String S1Output = searchSbox(S1, 1);  
        String S2Output = searchSbox(S2, 2);  
        String SS = S1Output + S2Output;  
        String f1 = substitue(SS, P4);  

        String L1 = xor(f1, L0);   // L1 = L0 ⊕ f1  
        String R1 = R0;            // R1 = R0  

        // 交换 L1 和 R1  
        String LSwap = R1;         // L2 = R1  
        String RSwap = L1;         // R2 = L0  

        // 第二轮加密  
        String R1E = substitue(LSwap, EP);  
        R1E = xor(R1E, key2);      // 使用 key2  
        String S1Second = R1E.substring(0, 4);  
        String S2Second = R1E.substring(4, 8);  
        String S1OutputSecond = searchSbox(S1Second, 1);  
        String S2OutputSecond = searchSbox(S2Second, 2);  
        SS = S1OutputSecond + S2OutputSecond;  
        String f2 = substitue(SS, P4);  
        
        String L2 = xor(f2, RSwap); // L2 = L0 ⊕ f2  
        ciphertext = L2 + LSwap; // 最终的 L2 和 R2  

        ciphertext = substitue(ciphertext, IP_1);  
        //System.out.println("密文: " + ciphertext);  
    }  

    public static void decrypt(String str) {  
        System.out.println("-----请输入要解密的信息(8位)------");  
        String ciphertext = str;  
        ciphertext = substitue(ciphertext, IP);  
        String L0 = ciphertext.substring(0, 4);  
        String R0 = ciphertext.substring(4, 8);  

        // 第一轮解密使用 k2  
        String R0E = substitue(R0, EP);  
        R0E = xor(R0E, key2);  // 使用 key2  

        String S1 = R0E.substring(0, 4);  
        String S2 = R0E.substring(4, 8);  
        String S1Output = searchSbox(S1, 1);  
        String S2Output = searchSbox(S2, 2);  
        String SS = S1Output + S2Output;  
        String f1 = substitue(SS, P4);   

        String L1 = xor(f1, L0);  // L1 = L0 ⊕ f1  
        String R1 = R0;           // R1 = R0  

        // 交换 L1 和 R1  
        String LSwap = R1; // L2 = R1  
        String RSwap = L1; // R2 = L0   

        // 第二轮解密使用 k1  
        String R1E = substitue(LSwap, EP);  
        R1E = xor(R1E, key1);  // 使用 key1  
        String S1Second = R1E.substring(0, 4);  
        String S2Second = R1E.substring(4, 8);  
        String S1OutputSecond = searchSbox(S1Second, 1);  
        String S2OutputSecond = searchSbox(S2Second, 2);  
        SS = S1OutputSecond + S2OutputSecond;  
        String f2 = substitue(SS, P4);  
        
        String L2 = xor(f2, RSwap); // L2 = R0 ⊕ f2  
        plaintext = L2 + LSwap; // 合成 L2 和 R2  

        plaintext = substitue(plaintext, IP_1);  
        System.out.println("明文: " + plaintext);  
    }  

//
    public static String charToBinaryString(char c) {  
        return String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'); // 将字符转换为8位二进制字符串  
    }  

    public static char binaryStringToChar(String binary) {  
        return (char) Integer.parseInt(binary, 2); // 将二进制字符串转换回字符  
    }  

    public static String encryptByte(String byteString) {  
    	encrypt(byteString);
        return ciphertext; // 返回加密结果  
    } 
    
    public static String decryptByte(String byteString) {  
    	decrypt(byteString);
        return plaintext; // 返回解密结果  
    }  


    public static String encryptASCIIString(String input) {  
        StringBuilder encryptedResult = new StringBuilder(); // 用于存储加密结果  
        for (char c : input.toCharArray()) { // 遍历输入字符串中的每个字符  
            String binaryString = charToBinaryString(c); // 转换为二进制字符串  
            String encryptedByte = encryptByte(binaryString); // 加密二进制字符串 
            char r=binaryStringToChar(encryptedByte);	// 转换为对应字符串
            encryptedResult.append(r); // 连接加密后的输出  
        }  
        // 返回整个加密结果  
        return encryptedResult.toString();   
    }  
    
    public static String decryptASCIIString(String input) {  
        StringBuilder decryptedResult = new StringBuilder(); // 用于存储解密结果  
    	for (char c : input.toCharArray()) { 
            String byteString = charToBinaryString(c);	
            String decryptedByte = decryptByte(byteString); // 解密该字节 
            char r=binaryStringToChar(decryptedByte);
            decryptedResult.append(binaryStringToChar(decryptedByte)); // 转换回字符并追加  
    	}  
        return decryptedResult.toString(); // 返回解密得到的字符串  
    }  
    
	public static void encryptASCII(String str) {  
	    System.out.println("-----请输入要加密的信息(ASCII字符串)------"); 
	    String plaintext = str; // 读取用户输入  
	    encryptedResult = encryptASCIIString(plaintext); // 对输入字符串进行加密  
	    System.out.println("密文: " + encryptedResult); // 输出密文  
	}  
	
	public static void decryptASCII(String str) {  
	    System.out.println("-----请输入要解密的信息（无长度限制，直接输入密文）------");    
	    String ciphertext = str; // 读取用户输入  
	    decryptedResult = decryptASCIIString(ciphertext); // 对输入字符串进行解密  
	    System.out.println("明文: " + decryptedResult); // 输出明文  
	}  

}
 