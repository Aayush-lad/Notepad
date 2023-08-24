package com.notepad;

import javax.swing.*;
public class About  extends JFrame{
    About(){
        setBounds(100,100,500,400);
        setTitle("About Notepad");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        ImageIcon icon= new ImageIcon(Main.class.getResource("/Icon-notepad.svg_80x80.png"));
        setIconImage(icon.getImage());
        JLabel  iconLabel = new JLabel(new ImageIcon(Main.class.getResource("/Icon-notepad.svg_80x80.png")));
        iconLabel.setBounds(100, 40, 80, 80);
        add(iconLabel);

        JLabel textLabel = new JLabel("<html>  WELCOME TO NOTEPAD <br />\u00A92023 Notepad Technologies. All rights reserved.<br />" +   "Elevate your note-taking experience with Notepad, the ultimate notepad app designed to seamlessly integrate with your modern lifestyle. Whether you're a student, professional, or creative thinker, NoteSwift empowers you to capture, organize, and access your thoughts effortlessly.</html>");
        textLabel.setBounds(100, 50, 350, 300);
        add(textLabel);
        

    }
    public static void main(String[] args) {
        new About().setVisible(true);
    }
    
}

