package frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{
    private JPanel contentPane;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MainFrame() {
    	setTitle("S-DES");
    	
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(500, 250, 900, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("欢迎使用S-DES算法");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setBounds(350, 50, 200, 30);
        contentPane.add(lblNewLabel);

        JButton button = new JButton("加密");
        button.setBounds(100, 152, 99, 29);
        contentPane.add(button);

        JButton button_1 = new JButton("解密");
        button_1.setBounds(250, 152, 99, 29);
        contentPane.add(button_1);
        
        JButton button_2 = new JButton("ASCII加密");
        button_2.setBounds(400, 152, 99, 29);
        contentPane.add(button_2);
        
        JButton button_3 = new JButton("ASCII解密");
        button_3.setBounds(550, 152, 99, 29);
        contentPane.add(button_3);
        
        JButton button_4 = new JButton("暴力破解");
        button_4.setBounds(700, 152, 99, 29);
        contentPane.add(button_4);
  
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Encrypt().result();
            }
        });
        
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Decrypt().result();
            }
        });
        
        
        button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ASCIIEncrypt().result();
            }
        });
        
        button_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ASCIIDecrypt().result();
            }
        });
        
        button_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new BFFrame().result();
            }
        });
    }
}