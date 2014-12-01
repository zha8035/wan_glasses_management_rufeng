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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���õ����رմ��尴ťʱִ�еĲ���
        setSize(450, 300);// ���ô����С
        contentPane = new JPanel();// �������
        contentPane.setLayout(new BorderLayout(0, 0));// ������岼��ʹ�ñ߽粼��
        setContentPane(contentPane);// Ӧ�����

        JLabel tipLabel = new JLabel("��ϲ���ɹ���¼ϵͳ��");// ������ǩ
        tipLabel.setFont(new Font("΢���ź�", Font.PLAIN, 40));// ���ñ�ǩ����
        contentPane.add(tipLabel, BorderLayout.CENTER);// Ӧ�ñ�ǩ

        setLocation(SwingUtil.centreContainer(getSize()));// �ô��������ʾ
    }

}
