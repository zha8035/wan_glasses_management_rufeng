package com.computer_net.frame;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.apache.commons.lang.RandomStringUtils;

//import com.computer_net.util.CAPTCHALabel;
import com.computer_net.util.ColorfulCAPTCHALabel;
import com.computer_net.util.DBHelper;
//import com.computer_net.util.SwingUtil;
import com.computer_net.frame.RegisterFrame;
import java.awt.Point;

public class LoginFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -4655235896173916415L;
    private JPanel contentPane;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JTextField validateTextField;
    private String randomText;// ����������ɵ��ַ���

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    LoginFrame frame = new LoginFrame();
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
    public LoginFrame() {
        setTitle("\u7CFB\u7EDF\u767B\u5F55");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        JPanel usernamePanel = new JPanel();
        contentPane.add(usernamePanel);

        JLabel usernameLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
        usernameLabel.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        usernamePanel.add(usernameLabel);

        usernameTextField = new JTextField();
        usernameTextField.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        usernamePanel.add(usernameTextField);
        usernameTextField.setColumns(10);

        JPanel passwordPanel = new JPanel();
        contentPane.add(passwordPanel);

        JLabel passwordLabel = new JLabel("\u5BC6    \u7801\uFF1A");
        passwordLabel.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        passwordPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setColumns(10);
        passwordField.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        passwordPanel.add(passwordField);

        JPanel validatePanel = new JPanel();
        contentPane.add(validatePanel);

        JLabel validateLabel = new JLabel("\u9A8C\u8BC1\u7801\uFF1A");
        validateLabel.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        validatePanel.add(validateLabel);

        validateTextField = new JTextField();
        validateTextField.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        validatePanel.add(validateTextField);
        validateTextField.setColumns(5);

        randomText = RandomStringUtils.randomAlphanumeric(4);
        ColorfulCAPTCHALabel label = new ColorfulCAPTCHALabel(randomText);
        label.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        validatePanel.add(label);

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel);

        JButton submitButton = new JButton("\u767B\u5F55");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do_submitButton_actionPerformed(e);
            }
        });
        
        JButton registerButton = new JButton("\u6CE8\u518C");
        registerButton.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        buttonPanel.add(registerButton);
        submitButton.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        buttonPanel.add(submitButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do_registerButton_actionPerformed(e);
            }
        });

        JButton cancelButton = new JButton("\u9000\u51FA");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do_cancelButton_actionPerformed(e);
            }
        });
        cancelButton.setFont(new Font("΢���ź�", Font.PLAIN, 15));
        buttonPanel.add(cancelButton);

        pack();// �Զ����������С
        setLocation(new Point(500, 300));// �ô��������ʾ
    }
    protected void do_registerButton_actionPerformed(ActionEvent e){
    	EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    RegisterFrame frame = new RegisterFrame();// ����������
                    frame.setVisible(true);// ����������ɼ�
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        dispose();// ����¼��������
    }
    protected void do_submitButton_actionPerformed(ActionEvent e) {
        String username = usernameTextField.getText().trim();// ����û�������û���
        char[] password = passwordField.getPassword();// ����û�������� ��
        String validate = validateTextField.getText().trim();// ����û��������֤��
        // ��ʼ���зǿ�У��
        if (username.isEmpty()) {// �ж��û����Ƿ�Ϊ��
            JOptionPane.showMessageDialog(this, "�û�������Ϊ�գ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (new String(password).isEmpty()) {// �ж������Ƿ�Ϊ��
            JOptionPane.showMessageDialog(this, "���벻��Ϊ�գ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (validate.isEmpty()) {// �ж���֤���Ƿ�Ϊ��
            JOptionPane.showMessageDialog(this, "��֤�벻��Ϊ�գ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // ��ʼ���кϷ���У��
        if (!DBHelper.exists(username)) {// ����û����������������ʾ
            JOptionPane.showMessageDialog(this, "�û��������ڣ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!DBHelper.check(username, password)) {// �����������������ʾ
            JOptionPane.showMessageDialog(this, "�������", "������Ϣ", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!validate.equals(randomText)) {// ���У���벻ƥ���������ʾ
            JOptionPane.showMessageDialog(this, "��֤�����", "������Ϣ", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // У����ϣ���ʾ������
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    SaveFrame frame = new SaveFrame();// ����������
                    frame.setVisible(true);// ����������ɼ�
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        dispose();// ����¼��������
    }

    protected void do_cancelButton_actionPerformed(ActionEvent e) {
        System.exit(0);// ��ֹ����
    }
}
