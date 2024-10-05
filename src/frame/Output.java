package frame;

import javax.swing.*;
import java.io.OutputStream;
import java.io.PrintStream;

public class Output {
    public static void result() {
        JFrame frame = new JFrame("暴力破解密钥结果以及时间");
        JTextArea textArea = new JTextArea(20, 50);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                textArea.append(String.valueOf((char) b));
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }
        });
        System.setOut(printStream);
        System.setErr(printStream);
        
        frame.add(scrollPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
              
    }
}
