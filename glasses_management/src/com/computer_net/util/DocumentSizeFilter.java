package com.computer_net.util;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

// 用于限制文档可以输入的字符串长度，当超过指定长度时将发出提示声音

public class DocumentSizeFilter extends DocumentFilter {

    private int maxSize;// 获得文本的最大长度

    public DocumentSizeFilter(int maxSize) {
        this.maxSize = maxSize;// 获得用户输入的最大长度
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if ((fb.getDocument().getLength() + string.length()) <= maxSize) {// 如果插入操作完成后小于最大长度
            super.insertString(fb, offset, string, attr);// 调用父类中的方法
        } else {
            Toolkit.getDefaultToolkit().beep();// 发出提示声音
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if ((fb.getDocument().getLength() + text.length() - length) <= maxSize) {// 如果替换操作完成后小于最大长度
            super.replace(fb, offset, length, text, attrs);// 调用父类中的方法
        } else {
            Toolkit.getDefaultToolkit().beep();// 发出提示声音
        }
    }
}