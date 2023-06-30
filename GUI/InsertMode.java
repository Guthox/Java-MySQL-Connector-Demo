package GUI;

import Connection.MySqlDatabase;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertMode extends JFrame implements ActionListener{
    
    private JButton btInsert;
    private JButton btSelect;

    private JLabel lbId;
    private JLabel lbName;

    private JTextField tfId;
    private JTextField tfName;

    private MySqlDatabase database;

    public InsertMode(){
        super("MySQL Connetion Demo");

        initComponents();

        database = new MySqlDatabase(); 

        setVisible(false);
        setSize(350,310);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    // Button actions **********************************************************
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btInsert){
            btInsert();
        }
        else if (e.getSource() == btSelect){
            btSelect();
        }
    }
    // *************************************************************************

    // btInsert actions ********************************************************
    private void btInsert(){
        if ( verifyInput( tfId.getText() ) == false ){
            JOptionPane.showMessageDialog(this, "Error.\nID must be a whole number.");      
            return;
        }

        if ( database.insert( Integer.parseInt( tfId.getText() ), tfName.getText() ) ){
            JOptionPane.showMessageDialog(this, "Insert successfully");
        }
        else{
            JOptionPane.showMessageDialog(this, "Cannot insert to database.\n Check for duplicate entry.");
        }

        tfId.setText("");
        tfName.setText("");

    }
    // *************************************************************************

    // Verify interigy of user input *******************************************
    private boolean verifyInput(String data){
        try{
            Integer.parseInt(data);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    // *************************************************************************

    // btSelect actions ********************************************************
    private void btSelect(){
        btSelect.setEnabled(false);
        SelectMode selectMode = new SelectMode();
        selectMode.setLocation(getLocation());
        dispose();
        selectMode.setVisible(true);
    }
    // *************************************************************************

    // Inicialize components ***************************************************
    public void initComponents(){
        
        Font font = new Font("Arial", Font.BOLD, 16);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("TextArea.font", font);

        btInsert = new JButton("Insert");
        btInsert.addActionListener(this);

        btSelect = new JButton("Select Mode");
        btSelect.addActionListener(this);

        lbId = new JLabel("ID: ");
        lbName = new JLabel("Name: ");

        tfId = new JTextField("", 20);
        tfName = new JTextField("", 20);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JPanel north = new JPanel(new GridLayout(2,2));

        north.add(lbId);
        north.add(tfId);

        north.add(lbName);
        north.add(tfName);

        container.add(north, BorderLayout.NORTH);

        JPanel center = new JPanel(new FlowLayout());

        center.add(btInsert);

        container.add(center, BorderLayout.CENTER);

        JPanel south = new JPanel(new FlowLayout());
        south.add(btSelect);

        container.add(south, BorderLayout.SOUTH);

    }
    // *************************************************************************
    

}
