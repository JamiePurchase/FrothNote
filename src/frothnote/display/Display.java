package frothnote.display;

import frothnote.file.ExtensionFilter;
import frothnote.file.FileManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Display extends JFrame implements ActionListener
{
    // Variables
    private boolean fileOpen;
    private String filePath;
    
    // Components
    private JTextArea area = new JTextArea(20,120);
    private JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
    private JPopupMenu context;
    private MouseListener mouse = new MouseAdapter()
    {
	public void mousePressed (MouseEvent e)
	{
            if(e.getButton() == MouseEvent.BUTTON3)
            {
                context.show(e.getComponent(), e.getX(), e.getY());
                //FileOpen();
            }
	}
    };
    private KeyListener keyboard = new KeyAdapter()
    {
        public void keyPressed(KeyEvent e)
        {
            if(fileOpen == false) {FileOpen();}
        }
    };
    
    public Display()
    {
        // Variables
        this.fileOpen = false;
        this.filePath = "";
        
        // Frame
        this.setPreferredSize(new Dimension(1000, 600));
        java.net.URL iconPath = ClassLoader.getSystemResource("frothnote/display/icon.png");
        Image iconImage = Toolkit.getDefaultToolkit().createImage(iconPath);
        this.setIconImage(iconImage);
        area.setFont(new Font("Monospaced",Font.PLAIN,12));
        
        // Pane
        JScrollPane scroll = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scroll,BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        
        // Context menu
        context = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("New");
        JMenuItem menuItem2 = new JMenuItem("Open");
        JMenuItem menuItem3 = new JMenuItem("Exit");
        menuItem1.addActionListener(this);
        menuItem2.addActionListener(this);
        menuItem3.addActionListener(this);
        context.add(menuItem1);
        context.add(menuItem2);
        context.add(menuItem3);
        
        // Connect the components
        area.addKeyListener(keyboard);
        area.addMouseListener(mouse);
        setTitle("FrothNote");
        this.setLocationRelativeTo(null);
        setVisible(true);
        
        // Open Dialog
        dialog.setCurrentDirectory(new File("C:/Users/Jamie/Documents/NetBeansProjects/Cappuccino/data"));
        FileFilter filter1 = new ExtensionFilter("Froth Files", ".froth");
        dialog.addChoosableFileFilter(filter1);
        dialog.setFileFilter(filter1);
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getActionCommand().equals("New")) {FileNew();}
        if(event.getActionCommand().equals("Open")) {FileOpen();}
        if(event.getActionCommand().equals("Exit")) {System.exit(0);}
    }
    
    public void FileNew()
    {
        area.setText("");
    }
    
    public void FileOpen()
    {
        if(dialog.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            String selectedFileExt = FileManager.getFileExtension(dialog.getSelectedFile());
            if(selectedFileExt.equals("froth"))
            {
                FileOpenRead(dialog.getSelectedFile().getAbsolutePath());
            }
        }
        //SaveAs.setEnabled(true);
    }
    
    public void FileOpenRead(String filePath)
    {
        try
        {
            FileReader r = new FileReader(filePath);
            area.read(r,null);
            r.close();
            /*currentFile = fileName;
            setTitle(currentFile);
            changed = false;*/
            this.fileOpen = true;
            this.filePath = filePath;
        }
        catch(IOException e)
        {
            //
        }
    }
    
}