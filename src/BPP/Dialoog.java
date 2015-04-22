package BPP;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Dialoog extends JDialog implements ActionListener
{


    private JLabel hoogte;
    private JTextField jftHoogte;    
    private JButton annuleren;
    private JButton toevoegen;
    private boolean toegevoegd;
    private Scherm hoofdscherm;
    
    public Dialoog(JFrame frame)
    {
        super(frame, true);
        setTitle("Pakketjes toevoegen");
        setSize(300,200);
        setLayout(new FlowLayout());
        
        hoogte = new JLabel("Hoogte:");
        jftHoogte = new JTextField(15);
        toevoegen = new JButton("Toevoegen");
        annuleren = new JButton("Annuleren");
        
        
        add(hoogte);
        add(jftHoogte);
        add(toevoegen);
        add(annuleren);
        toevoegen.addActionListener(this);
        annuleren.addActionListener(this);
        setResizable(false);
        setVisible(true);
        

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == toevoegen)
        {
            int waarde = Integer.parseInt(jftHoogte.getText());
            System.out.println(waarde);
            
            toegevoegd = true;
            setVisible(false);
            
        }
        else if (e.getSource() == annuleren)
        {
            toegevoegd = false;
            setVisible(false);
        }
    }

    public JTextField getJftHoogte()
    {
        return jftHoogte;
    }
    
    

}