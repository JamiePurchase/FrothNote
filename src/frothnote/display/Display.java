package frothnote.display;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Display extends JFrame
{
    // Variables
    private boolean fileOpen;
    
    // Components
    private JTextArea area = new JTextArea(20,120);
    private JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
    private KeyListener keyboard = new KeyAdapter()
    {
        public void keyPressed(KeyEvent e)
        {
            if(fileOpen == false)
            {
                FileOpen();
            }
        }
    };
    
    public Display()
    {
        this.setPreferredSize(new Dimension(1000, 600));
        area.setFont(new Font("Monospaced",Font.PLAIN,12));
        JScrollPane scroll = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scroll,BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        area.addKeyListener(keyboard);
        setTitle("FrothNote");
        this.setLocationRelativeTo(null);
        setVisible(true);
        this.fileOpen = false;
    }
    
    public void FileOpen()
    {
        if(dialog.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            FileOpenRead(dialog.getSelectedFile().getAbsolutePath());
        }
        //SaveAs.setEnabled(true);
    }
    
    public void FileOpenRead(String fileName)
    {
        try
        {
            FileReader r = new FileReader(fileName);
            area.read(r,null);
            r.close();
            /*currentFile = fileName;
            setTitle(currentFile);
            changed = false;*/
            this.fileOpen = true;
        }
        catch(IOException e)
        {
            //
        }
    }
    
}