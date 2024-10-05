package frame;

import algorithm.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BFFrame extends JFrame{
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public void result(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                	BFFrame frame = new BFFrame();
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
    
        public BFFrame() {
    	setTitle("S-DES");
    	
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(500, 300, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("暴力破解");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setBounds(180, 30, 170, 29);
        contentPane.add(lblNewLabel);

        JLabel label = new JLabel("明  文：");
        label.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        label.setBounds(98, 106, 61, 16);
        contentPane.add(label);

        textField = new JTextField();
        textField.setBounds(171, 103, 132, 26);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel label_1 = new JLabel("密  文：");
        label_1.setBounds(98, 137, 61, 16);
        label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

        contentPane.add(label_1);

        passwordField = new JPasswordField();
        passwordField.setBounds(171, 132, 132, 26);
        contentPane.add(passwordField);

        JButton button = new JButton("破解");
        button.setBounds(93, 192, 99, 29);
        contentPane.add(button);

        JButton button_1 = new JButton("退出");
        button_1.setBounds(253, 192, 99, 29);
        contentPane.add(button_1);

       button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String t1 = textField.getText();
                String p1 = String.valueOf(passwordField.getPassword());
                new Output().result();;
                new BruteForce().b_force(t1,p1);
               // JOptionPane.showMessageDialog(null,"密文为："+new SimpleDES().ciphertext); 
            }
       });
       
       button_1.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.exit(-1);
           }
       });

    }
}