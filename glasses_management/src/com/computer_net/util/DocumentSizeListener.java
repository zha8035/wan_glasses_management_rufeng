package com.computer_net.util;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

//  ������ʾ�ĵ���ʹ�����

public class DocumentSizeListener implements DocumentListener {

    private JLabel tipLabel;
    private int maxSize;

    public DocumentSizeListener(JLabel tipLabel, int maxSize) {
        this.tipLabel = tipLabel;
        this.maxSize = maxSize;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        setTipText(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        setTipText(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        setTipText(e);
    }

    private void setTipText(DocumentEvent e) {
        Document doc = e.getDocument();// ����ĵ�����
        tipLabel.setForeground(Color.BLACK);// ����������ɫ
        if (doc.getLength() > (maxSize * 4 / 5)) {// ����������ַ����ȴ�����󳤶ȵ�80%
            tipLabel.setForeground(Color.RED);// ʹ�ú�ɫ��ʾ��ʾ��Ϣ
        } else {
            tipLabel.setForeground(Color.BLACK);// ʹ�ú�ɫ��ʾ��ʾ��Ϣ
        }
        tipLabel.setText("��ʾ��Ϣ��" + doc.getLength() + "/" + maxSize);
    }

}