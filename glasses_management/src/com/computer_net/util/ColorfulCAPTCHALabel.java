package com.computer_net.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

import org.apache.commons.lang.math.RandomUtils;

public class ColorfulCAPTCHALabel extends JLabel {

    private static final long serialVersionUID = -963570191302793615L;
    private String text;// ���ڱ���������֤ͼƬ���ַ���
    private Color[] colors = { Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
            Color.PINK, Color.RED, Color.WHITE, Color.YELLOW };// ���廭����ɫ����

    public ColorfulCAPTCHALabel(String text) {
        this.text = text;
        setPreferredSize(new Dimension(60, 36));// ���ñ�ǩ�Ĵ�С
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);// ���ø���Ĺ��췽��
        g.setFont(new Font("΢���ź�", Font.PLAIN, 16));// ��������
        for (int i = 0; i < text.length(); i++) {
            g.setColor(colors[RandomUtils.nextInt(colors.length)]);
            g.drawString("" + text.charAt(i), 5 + i * 13, 25);// �����ַ���
        }
    }
}
