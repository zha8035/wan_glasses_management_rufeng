package com.computer_net.frame;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.FlowLayout;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



import javax.swing.JLabel;
import javax.swing.UIManager;

import com.computer_net.model.ImagSave;
import com.computer_net.util.DBHelper1;
//import photo_save.ImageInsert;

public class SaveFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2800257928532998616L;//private JFrame SaveFrame;
	private JTextField cirTextField;
	private JTextField dateTextField;
	private JTextField storeTextField;
	private JTextField usernameTextField;

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
			public void run() {
				try {
					SaveFrame window = new SaveFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SaveFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setTitle("\u56FE\u7247\u5BFC\u5165");
		setBounds(100, 100, 470, 367);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel cirPanel = new JPanel();
		cirPanel.setBounds(0, 68, 452, 34);
		getContentPane().add(cirPanel);
		cirPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel cirLabel = new JLabel("\u7597\u7A0B\u671F\u6570\uFF1A");
		cirLabel.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		cirLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cirPanel.add(cirLabel);
		
		cirTextField = new JTextField();
		cirTextField.setHorizontalAlignment(SwingConstants.LEFT);
		cirTextField.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		cirPanel.add(cirTextField);
		cirTextField.setColumns(10);
		
		JPanel datePanel = new JPanel();
		datePanel.setBounds(0, 102, 452, 34);
		datePanel.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		getContentPane().add(datePanel);
		
		JLabel dateLabel = new JLabel("\u56FE\u7247\u65E5\u671F\uFF1A");
		datePanel.add(dateLabel);
		dateLabel.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		
		dateTextField = new JTextField();
		dateTextField.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		datePanel.add(dateTextField);
		dateTextField.setColumns(10);
		
		JPanel usernamePanel = new JPanel();
		usernamePanel.setBounds(0, 35, 452, 34);
		usernamePanel.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		getContentPane().add(usernamePanel);
		
		JLabel usernameLabel = new JLabel("\u7528\u6237\u59D3\u540D\uFF1A");
		usernamePanel.add(usernameLabel);
		usernameLabel.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		
		usernameTextField = new JTextField();
		usernameTextField .setFont(new Font("΢���ź�", Font.PLAIN, 15));
		usernamePanel.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JPanel storePanel = new JPanel();
		storePanel .setFont(new Font("΢���ź�", Font.PLAIN, 15));
		storePanel.setBounds(0, 135, 452, 41);
		getContentPane().add(storePanel);
		
		JLabel storeLabel = new JLabel("\u56FE\u7247\u4F4D\u7F6E\uFF1A");
		storeLabel .setFont(new Font("΢���ź�", Font.PLAIN, 15));
		storePanel.add(storeLabel);
		
		storeTextField = new JTextField();
		storeTextField .setFont(new Font("΢���ź�", Font.PLAIN, 15));
		storePanel.add(storeTextField);
		storeTextField.setColumns(20);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 227, 452, 47);
		getContentPane().add(buttonPanel);
		
		JButton submitButton = new JButton("\u63D0\u4EA4");
		submitButton.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do_submitButton_actionPerformed(e);
            }
        });
		buttonPanel.add(submitButton);
		
		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		buttonPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do_cancelButton_actionPerformed(e);
            }
        });		
}
protected void do_submitButton_actionPerformed(ActionEvent e) {
    String username = usernameTextField.getText().trim();// ����û�������û���
    String cir= cirTextField.getText().trim();// ����û�������Ƴ�����
    String date = dateTextField.getText().trim();// ����û��������Ƭ����
    String store=storeTextField.getText().trim();
    // ��ʼ���зǿ�У��
    if (username.isEmpty()) {// �ж��û����Ƿ�Ϊ��
        JOptionPane.showMessageDialog(this, "�û�������Ϊ�գ�","������Ϣ",JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (new String(cir).isEmpty()) {// �ж��Ƴ������Ƿ�Ϊ��
        JOptionPane.showMessageDialog(this, "�Ƴ���������Ϊ�գ�","������Ϣ",JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (date.isEmpty()) {// �ж���Ƭ�����Ƿ�Ϊ��
        JOptionPane.showMessageDialog(this, "��Ƭ���ڲ���Ϊ�գ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (store.isEmpty()) {// �ж���Ƭ�洢��ַ�Ƿ�Ϊ��
        JOptionPane.showMessageDialog(this, "��Ƭ�洢��ַ����Ϊ�գ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
        return;
    }
 // У���û����Ƿ�Ϸ�
    if (!Pattern.matches("\\w{5,15}", username)) {
        JOptionPane.showMessageDialog(this, "������Ϸ����û�����", "������Ϣ", JOptionPane.WARNING_MESSAGE);
        return;
    }
    // У���Ƴ������Ƿ�Ϸ�
    if (!Pattern.matches("\\w", cir)) {
        JOptionPane.showMessageDialog(this, "������Ϸ����Ƴ�������", "������Ϣ", JOptionPane.WARNING_MESSAGE);
        return;
    }
    // У����Ƭ�����Ƿ�Ϸ�
    if (!Pattern.matches("\\d*", date)) {
        JOptionPane.showMessageDialog(this, "������Ϸ�����Ƭ���ڣ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
        return;
    }
    // У���û����Ƿ����
    if (DBHelper1.exists(username)) {
        if(DBHelper1.exists(cir)){
        	JOptionPane.showMessageDialog(this, "���û����Ƴ��Ѿ�����", "������Ϣ", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }
    ImagSave IS= new ImagSave();
    IS.setUsername(username);
    IS.setCir(cir);
    IS.setDate(date);
    IS.setStore(store);
    if (DBHelper1.save(IS)) {
        JOptionPane.showMessageDialog(this, "ͼƬ��Ϣ����ɹ���", "��ʾ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
        return;
    } else {
        JOptionPane.showMessageDialog(this, "ͼƬ��Ϣ����ʧ�ܣ�", "������Ϣ", JOptionPane.WARNING_MESSAGE);
        return;
    }
}
protected void do_cancelButton_actionPerformed(ActionEvent e) {
    System.exit(0);// ��ֹ����
}
}
