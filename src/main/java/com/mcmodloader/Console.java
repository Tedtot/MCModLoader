package com.mcmodloader;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Console {
    private JFrame frame;
    private JTextArea console;

    public Console() {
        frame = new JFrame("Console");
        frame.setVisible(true);
        frame.setBounds(100, 100, 600, 600);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());
        console = new JTextArea(30, 70);
        console.setEditable(false);
        frame.getContentPane().add(new JScrollPane(console));
        frame.setResizable(false);

        // Redirect System.out to the console JTextArea
        PrintStream printStream = new PrintStream(new CustomOutputStream(console));
        System.setOut(printStream);
    }

    private class CustomOutputStream extends ByteArrayOutputStream {
        private JTextArea console;

        public CustomOutputStream(JTextArea console) {
            this.console = console;
        }

        @Override
        public synchronized void write(byte[] b, int off, int len) {
            super.write(b, off, len);
            console.append(new String(b, off, len));
            console.setCaretPosition(console.getDocument().getLength());
        }

        @Override
        public synchronized void write(int b) {
            super.write(b);
            console.append(String.valueOf((char) b));
            console.setCaretPosition(console.getDocument().getLength());
        }
    }

    public void setWindowCloseable() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}