package com.computer_net.util;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

public class CAPTCHALabel extends JLabel {

    private static final long serialVersionUID = -963570191302793615L;
    private String text;// ���ڱ���������֤ͼƬ���ַ���

    public CAPTCHALabel(String text) {
        this.text = text;
        setPreferredSize(new Dimension(60, 36));// ���ñ�ǩ�Ĵ�С
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);// ���ø���Ĺ��췽��
        g.setFont(new Font("΢���ź�", Font.PLAIN, 16));// ��������
        g.drawString(text, 5, 25);// �����ַ���
    }
}
