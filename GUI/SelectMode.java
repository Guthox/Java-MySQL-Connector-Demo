package GUI;

import Connection.MySqlDatabase;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectMode extends JFrame implements ActionListener {
    
    private JButton btInsert;
    private JButton btSelect;
    
    private JLabel lbId;
    
    private JTextField tfId;

    private JTextArea taResult;
    private JScrollPane scroll;

    private MySqlDatabase database;

    public SelectMode(){
        super("MySQL Connetion Demo");

        database = new MySqlDatabase();

        initComponents();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350,310);
        setVisible(false);

    }

    // Button Events ***********************************************************
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btSelect){
            btSelect();
        }
        else if(e.getSource() == btInsert){
            btInsert();
        }
    }
    // *************************************************************************

    // Button Insert Action ****************************************************
    private void btInsert(){
        btInsert.setEnabled(false);
        InsertMode insertMode = new InsertMode();
        insertMode.setLocation(getLocation());
        dispose();
        insertMode.setVisible(true);
    }
    // *************************************************************************

    // Button Select Action ****************************************************
    private void btSelect(){

        taResult.setText("");
    
        if (verifyInput(tfId.getText()) == false){
            taResult.setText("Error");
            return;
        }

        if (tfId.getText().equals("")){
            taResult.setText( database.select() );
        }
        else{
            taResult.setText( database.select( Integer.parseInt(tfId.getText()) ) );
        }
        
    }
    // *************************************************************************

    // Verify if the user input on tfId is valid *******************************
    private boolean verifyInput(String input){
        
        if (input.equals("")){
            return true;
        }

        try{
            Integer.parseInt(input);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    // *************************************************************************

    // Inicialize swing components *********************************************
    public void initComponents(){
        
        // Font settings

        Font font = new Font("Arial", Font.BOLD, 16);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("TextArea.font", font);

        // *************

        // Buttons *****
        btInsert = new JButton("Insert mode");
        btInsert.addActionListener(this);

        btSelect = new JButton("Select");
        btSelect.addActionListener(this);
        // *************

        lbId = new JLabel("ID: ");
        
        tfId =  new JTextField("", 10);

        taResult = new JTextArea(10,20);
        taResult.setLineWrap(true);
        taResult.setWrapStyleWord(true);
        taResult.setEditable(false);

        scroll = new JScrollPane(taResult);
        
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JPanel search = new JPanel(new FlowLayout());
        search.add(lbId);
        search.add(tfId);
        search.add(btSelect);
        search.add(scroll);

        JPanel insert = new JPanel(new FlowLayout());
        insert.add(btInsert);

        container.add(search, BorderLayout.CENTER);
        container.add(insert, BorderLayout.SOUTH);

    }
    // *************************************************************************
}
