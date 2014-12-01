package com.computer_net.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.computer_net.util.SwingUtil;
import javax.swing.UIManager;

public class MainFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -7360122511568073830L;
    private JPanel contentPane;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置单击关闭窗体按钮时执行的操作
        setSize(450, 300);// 设置窗体大小
        contentPane = new JPanel();// 创建面板
        contentPane.setLayout(new BorderLayout(0, 0));// 设置面板布局使用边界布局
        setContentPane(contentPane);// 应用面板

        JLabel tipLabel = new JLabel("恭喜您成功登录系统！");// 创建标签
        tipLabel.setFont(new Font("微软雅黑", Font.PLAIN, 40));// 设置标签字体
        contentPane.add(tipLabel, BorderLayout.CENTER);// 应用标签

        setLocation(SwingUtil.centreContainer(getSize()));// 让窗体居中显示
    }

}
