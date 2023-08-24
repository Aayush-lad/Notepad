package com.notepad;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.awt.Font;

public class Main extends JFrame implements ActionListener{

    JMenuBar menubar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu edit = new JMenu("Edit");
    JMenu help = new JMenu("Help");
    JTextArea textArea = new JTextArea();

    
    Main(){
        setTitle("Notepad");
        setBounds(100,100,800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        ImageIcon icon= new ImageIcon(Main.class.getResource("/Icon-notepad.svg_80x80.png"));
        setIconImage(icon.getImage());

        // adding options to menubar
        setJMenuBar(menubar);
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);    

        // options in file
        JMenuItem newdoc = new JMenuItem("New");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem print= new JMenuItem("Print");
        JMenuItem exit = new JMenuItem("Exit");

        // option in edit
        JMenuItem selectall = new JMenuItem("Select all");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
         // option in help
        JMenuItem about = new JMenuItem("About Notepad");

        // adding options to file

        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

        // adding options to edit
        edit.add(selectall);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);

        // adding options to help
        help.add(about);

        // text area implementation

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);  

        textArea.setFont(new Font("SAN_SERIF",Font.PLAIN,20));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // action listener

        newdoc.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        print.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        exit.addActionListener(this);
        about.addActionListener(this);

        // SHORT CUT KEY IMPLEMENTATION
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));




    }

    // ACTION HANDLERS
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equalsIgnoreCase("New")){
            textArea.setText("");

        }
        else if( e.getActionCommand().equalsIgnoreCase("save")){
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("ONLY TEXT FILES","txt","text");
            fileChooser.setFileFilter(filter);
            int action =fileChooser.showOpenDialog(null);

            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }
            else{
                String filename=fileChooser.getSelectedFile().getAbsolutePath().toString();
                if(filename.endsWith(".txt") || filename.endsWith(".text")){
                    System.out.println(filename);
                }
                else{
                    filename=filename+".txt";
                }
                try{

                BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                textArea.write(writer);


                }
                catch(Exception ex){
                    ex.printStackTrace();
                }



            }
        }
        else if( e.getActionCommand().equalsIgnoreCase("print")){
            try{
                textArea.print();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }

        }
        else if (e.getActionCommand().equalsIgnoreCase("open")){
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("ONLY TEXT FILES","txt","text");
            fileChooser.setFileFilter(filter);
            int action =fileChooser.showOpenDialog(null);
            if(action!= JFileChooser.APPROVE_OPTION){
                return;
            }
            else{
                try{
                    BufferedReader reader = new BufferedReader(new java.io.FileReader(fileChooser.getSelectedFile().getAbsolutePath().toString()));
                    textArea.read(reader,null);
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }

        }
        else if (e.getActionCommand().equalsIgnoreCase("cut")){

            textArea.cut();
        }
        else if (e.getActionCommand().equalsIgnoreCase("copy")){

            textArea.copy();
        }
        else if (e.getActionCommand().equalsIgnoreCase("paste")){
            textArea.paste();
        }
        else if (e.getActionCommand().equalsIgnoreCase("exit")){
            System.exit(0);
        }
        else if (e.getActionCommand().equalsIgnoreCase("select all")){
            textArea.selectAll();
        }
        else if (e.getActionCommand().equalsIgnoreCase("about notepad")){
           new About().setVisible(true);


        }

    }


public static void main(String[] args) {
    new Main();
    try{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
}