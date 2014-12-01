package com.computer_net.frame;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.AbstractDocument;

import com.computer_net.model.User;
import com.computer_net.util.DBHelper;
import com.computer_net.util.DocumentSizeFilter;
import com.computer_net.util.DocumentSizeListener;
import com.computer_net.util.SwingUtil;

public class RegisterFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 2491294229716316338L;
    private JPanel contentPane;
    private JTextField usernameTextField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField emailTextField;
    private JLabel tipLabel = new JLabel();// 显示提示信息

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
                    RegisterFrame frame = new RegisterFrame();
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
    public RegisterFrame() {
        setTitle("\u7528\u6237\u6CE8\u518C");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        JPanel usernamePanel = new JPanel();
        contentPane.add(usernamePanel);

        JLabel usernameLabel = new JLabel("\u7528  \u6237  \u540D\uFF1A");
        usernameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        usernamePanel.add(usernameLabel);

        usernameTextField = new JTextField();
        usernameTextField.setToolTipText("\u8BF7\u8F93\u51655~15\u4E2A\u7531\u5B57\u6BCD\u6570\u5B57\u4E0B\u5212\u7EBF\u7EC4\u6210\u7684\u5B57\u7B26\u4E32");
        AbstractDocument doc = (AbstractDocument) usernameTextField.getDocument();
        doc.setDocumentFilter(new DocumentSizeFilter(15));// 限制文本域内可以输入字符长度为15
        doc.addDocumentListener(new DocumentSizeListener(tipLabel, 15));
        usernameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        usernamePanel.add(usernameTextField);
        usernameTextField.setColumns(10);

        JPanel passwordPanel1 = new JPanel();
        contentPane.add(passwordPanel1);

        JLabel passwordLabel1 = new JLabel("\u8F93\u5165\u5BC6\u7801\uFF1A");
        passwordLabel1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        passwordPanel1.add(passwordLabel1);

        passwordField1 = new JPasswordField();
        doc = (AbstractDocument) passwordField1.getDocument();
        doc.setDocumentFilter(new DocumentSizeFilter(20));// 限制密码域内可以输入字符长度为20
        doc.addDocumentListener(new DocumentSizeListener(tipLabel, 20));
        passwordField1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        passwordField1.setColumns(10);
        passwordPanel1.add(passwordField1);

        JPanel passwordPanel2 = new JPanel();
        contentPane.add(passwordPanel2);

        JLabel passwordLabel2 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
        passwordLabel2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        passwordPanel2.add(passwordLabel2);

        passwordField2 = new JPasswordField();
        doc = (AbstractDocument) passwordField2.getDocument();
        doc.setDocumentFilter(new DocumentSizeFilter(20));// 限制密码域内可以输入字符长度为20
        doc.addDocumentListener(new DocumentSizeListener(tipLabel, 20));
        passwordField2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        passwordField2.setColumns(10);
        passwordPanel2.add(passwordField2);

        JPanel emailPanel = new JPanel();
        contentPane.add(emailPanel);

        JLabel emailLabel = new JLabel("\u7535\u5B50\u90AE\u7BB1\uFF1A");
        emailLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        emailPanel.add(emailLabel);

        emailTextField = new JTextField();
        doc = (AbstractDocument) emailTextField.getDocument();
        doc.setDocumentFilter(new DocumentSizeFilter(45));// 限制文本域内可以输入字符长度为45
        doc.addDocumentListener(new DocumentSizeListener(tipLabel, 45));
        emailTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        emailPanel.add(emailTextField);
        emailTextField.setColumns(10);

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel);

        JButton submitButton = new JButton("\u63D0\u4EA4");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do_submitButton_actionPerformed(e);
            }
        });
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

        tipLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(tipLabel);

        Component glue = Box.createGlue();
        buttonPanel.add(glue);
        submitButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(submitButton);

        JButton cancelButton = new JButton("\u53D6\u6D88");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do_cancelButton_actionPerformed(e);
            }
        });
        cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(cancelButton);

        pack();// 自动调整窗体大小
        setLocation(SwingUtil.centreContainer(getSize()));// 让窗体居中显示
    }

    protected void do_submitButton_actionPerformed(ActionEvent e) {
        String username = usernameTextField.getText().trim();// 获得用户输入的用户名
        char[] password1 = passwordField1.getPassword();// 获得用户输入的密 码
        char[] password2 = passwordField2.getPassword();// 获得用户输入的密 码
        String email = emailTextField.getText().trim();// 获得用户输入的电子邮箱
        // 开始进行非空校验
        if (username.isEmpty()) {// 判断用户名是否为空
            JOptionPane.showMessageDialog(this, "用户名不能为空！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (new String(password1).isEmpty()) {// 判断密码是否为空
            JOptionPane.showMessageDialog(this, "密码不能为空！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (new String(password2).isEmpty()) {// 判断确认密码是否为空
            JOptionPane.showMessageDialog(this, "确认密码不能为空！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (email.isEmpty()) {// 判断电子邮箱是否为空
            JOptionPane.showMessageDialog(this, "电子邮箱不能为空！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // 校验用户名是否合法
        if (!Pattern.matches("\\w{5,15}", username)) {
            JOptionPane.showMessageDialog(this, "请输入合法的用户名！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // 校验两次输入的密码是否相同
        if (!Arrays.equals(password1, password2)) {
            JOptionPane.showMessageDialog(this, "两次输入的密码不同！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // 校验电子邮箱是否合法
        if (!Pattern.matches("\\w+@\\w+\\.\\w+", email)) {
            JOptionPane.showMessageDialog(this, "请输入合法的电子邮箱！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // 校验用户名是否存在
        if (DBHelper.exists(username)) {
            JOptionPane.showMessageDialog(this, "用户名已经存在", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(new String(password1));
        user.setEmail(email);
        Arrays.fill(password1, '0');// 清空保存密码的字符数组
        Arrays.fill(password2, '0');// 清空保存密码的字符数组
        if (DBHelper.save(user)) {
            JOptionPane.showMessageDialog(this, "用户注册成功！", "提示信息", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(this, "用户注册失败！", "警告信息", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
    }

    protected void do_cancelButton_actionPerformed(ActionEvent e) {
        dispose();
    }
}
