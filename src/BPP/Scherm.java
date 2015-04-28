package BPP;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.*;
import javax.swing.table.*;

public class Scherm extends JFrame implements ActionListener
{

    private JButton pakketToevoegen;
    private JLabel jlSelectPakketTitel;
    private JButton stopSimulatie;
    private JButton startSimulatie;
    private Tekenpanel tp;
    private JSpinner aantal;
    private JButton genereerPakketten;
    private JButton handmatigPakketten;
    private JLabel titelPakketlijst;
    private JTable jtPakketlijst;
    private DefaultTableModel model;
    private DefaultTableModel model2;
    private ArrayList<Artikel> pakketlijst;
    private JButton leegPakketLijst;
    private JTable resultatenlijst;

    public Scherm()
    {
        setTitle("BPP-Simulator");
        setSize(1280, 720);
        setLayout(new GridLayout(1, 2));

        //Pakketlijst aanmaken
        pakketlijst = new ArrayList<>();

        // Linker en rechterpaneel aanmaken
        JPanel jpLinks = new JPanel();
        JPanel jpRechts = new JPanel();

        // Tekenpanel
        tp = new Tekenpanel();
        jpLinks.add(tp);

        // Rechtervak verdelen in 4 rijen (bij 1)
        jpRechts.setLayout(new GridLayout(5, 1));

        // Rij 1 van het rechtervak met invulling ervan, verdelen in 2 rijen met 1 kolom
        JPanel jpPakketSelectie = new JPanel();
        jpPakketSelectie.setLayout(new BorderLayout());
        jlSelectPakketTitel = new JLabel("Pakketten toevoegen");
        jlSelectPakketTitel.setFont(new Font("SansSerif", Font.BOLD, 26));

        // Eerste rij van de GridLayout vullen, van vak 1
        jpPakketSelectie.add(jlSelectPakketTitel, BorderLayout.NORTH);

        // JPanel aanmaken voor het vak 1.2, onderverdelen in 2 rijen en 1 kolom
        JPanel jpSelectPakketInh = new JPanel();
        jpSelectPakketInh.setLayout(new BorderLayout());
        JPanel jpSelectPakketButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Nummerspinner en 'Genereer pakketten' button aanmaken en toevoegen aan JPannel
        aantal = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
        genereerPakketten = new JButton("Genereer pakketten");
        jpSelectPakketButtons.add(aantal);
        jpSelectPakketButtons.add(genereerPakketten);

        // JPanel 1.2.1 toevoegen aan vak 1.2
        jpSelectPakketInh.add(jpSelectPakketButtons, BorderLayout.NORTH);

        // JPanel aanmaken voor 1.2.2, button 'Handmatig pakketten toevoegen'
        JPanel jpSelectPakketButtonsHandm = new JPanel(new FlowLayout(FlowLayout.LEFT));
        handmatigPakketten = new JButton("Handmatig pakketten toevoegen");
        jpSelectPakketButtonsHandm.add(handmatigPakketten);

        // Vak 1.2.2 toevoegen aan 1.2
        jpSelectPakketInh.add(jpSelectPakketButtonsHandm, BorderLayout.CENTER);

        // Vak 1.2 toevoegen aan vak 1
        jpPakketSelectie.add(jpSelectPakketInh);

        // JPanel aanmaken voor vak 2
        JPanel jpPakketlijst = new JPanel();
        jpPakketlijst.setLayout(new BorderLayout());

        // Titel aanmaken voor vak 2
        titelPakketlijst = new JLabel("Pakketlijst");
        titelPakketlijst.setFont(new Font("SansSerif", Font.BOLD, 26));

        // JTable aanmaken voor de pakketlijst
        model = new DefaultTableModel();
        JTable jtPakketlijst = new JTable(model);
        // Kolommen toevoegen
        model.addColumn("Pakketnr.");
        model.addColumn("Hoogte");

        jtPakketlijst.getColumn("Pakketnr.").setMaxWidth(100);
        jtPakketlijst.setEnabled(false);
        jtPakketlijst.getTableHeader().setReorderingAllowed(false);
        jtPakketlijst.getTableHeader().setResizingAllowed(false);

        // Leeg pakketlijst button
        leegPakketLijst = new JButton("Leeg pakketlijst");

        // JTable toevoegen aan vak 2
        jpPakketlijst.add(titelPakketlijst, BorderLayout.NORTH);
        jpPakketlijst.add(jtPakketlijst, BorderLayout.CENTER);
        jpPakketlijst.add(leegPakketLijst, BorderLayout.EAST);

        // JScrollPane aanmaken
        JScrollPane scrollbarLijst = new JScrollPane();
        scrollbarLijst.setViewportView(jtPakketlijst);

        //JScrollPane toevoegen aan vak 2
        jpPakketlijst.add(scrollbarLijst, BorderLayout.CENTER);

        // Rechterpaneeel Vak 3 aanmaken
        JPanel jpAlgoEnActies = new JPanel();
        jpAlgoEnActies.setLayout(new GridLayout(1, 2));

        //Vak 3.1 aanmaken
        JPanel jpSelectAlgo = new JPanel(new BorderLayout());
        JLabel algoritmeKiezen = new JLabel("Algoritme kiezen");
        algoritmeKiezen.setFont(new Font("SansSerif", Font.BOLD, 26));
        jpSelectAlgo.add(algoritmeKiezen, BorderLayout.NORTH);

        DefaultListModel algoLijstInhoud = new DefaultListModel();
        algoLijstInhoud.addElement("Volledige Enumeratie");
        algoLijstInhoud.addElement("Simpel Gretig");
        algoLijstInhoud.addElement("Almost Worst");

        JList algoLijst = new JList(algoLijstInhoud);
        algoLijst.setSelectedIndex(0);
        algoLijst.setBorder(new LineBorder(Color.BLACK));
        algoLijst.setSelectionMode(SINGLE_SELECTION);
        jpSelectAlgo.add(algoLijst, BorderLayout.CENTER);

        //Vak 3.2 aanmaken
        JPanel jpSimulatorActies = new JPanel(new BorderLayout());
        JLabel acties = new JLabel("Acties");
        acties.setFont(new Font("SansSerif", Font.BOLD, 26));
        jpSimulatorActies.add(acties, BorderLayout.NORTH);

        // Vak 3.2 inhoud (actiebuttons)
        JPanel jpActieButtons = new JPanel();
        jpActieButtons.setLayout(new GridLayout(2, 1));
        startSimulatie = new JButton("Start simulatie");
        stopSimulatie = new JButton("Stop simulatie");

        jpActieButtons.add(startSimulatie);
        jpActieButtons.add(stopSimulatie);

        jpSimulatorActies.add(jpActieButtons, BorderLayout.CENTER);

        // Vak 3.1 en 3.2 aanmaken aan vak 3
        jpAlgoEnActies.add(jpSelectAlgo);
        jpAlgoEnActies.add(jpSimulatorActies);

        // Vak 4 aanmaken
        JPanel jpResulatenOverzicht = new JPanel(new BorderLayout());
        JLabel resultaten = new JLabel("Resultaten");
        resultaten.setFont(new Font("SansSerif", Font.BOLD, 26));
        jpResulatenOverzicht.add(resultaten, BorderLayout.NORTH);

        model2 = new DefaultTableModel();
        resultatenlijst = new JTable(model2);
        // Kolommen toevoegen
        model2.addColumn("Algoritme");
        model2.addColumn("Aantal containers");
        model2.addColumn("Aantal pakketten");
        model2.addColumn("Containers gemiddeld % gevuld");
        resultatenlijst.getColumn("Algoritme").setMaxWidth(125);

        //resultatenlijst.getColumn("Pakketnr.").setMaxWidth(100);
        resultatenlijst.setEnabled(false);
        resultatenlijst.getTableHeader().setReorderingAllowed(false);
        resultatenlijst.getTableHeader().setResizingAllowed(false);

        jpResulatenOverzicht.add(resultatenlijst, BorderLayout.CENTER);

        scrollbarLijst = new JScrollPane();
        scrollbarLijst.setViewportView(resultatenlijst);

        jpResulatenOverzicht.add(scrollbarLijst);

        // Vakken toevoegen aan rechterpaneel
        jpRechts.add(jpPakketSelectie);
        jpRechts.add(jpPakketlijst);
        jpRechts.add(jpAlgoEnActies);
        jpRechts.add(jpResulatenOverzicht);

        // Linker & Rechterpaneel toevoegen
        add(jpLinks);
        add(jpRechts);

        // actionlisteners toevoegen
        genereerPakketten.addActionListener(this);
        handmatigPakketten.addActionListener(this);
        startSimulatie.addActionListener(this);
        stopSimulatie.addActionListener(this);
        leegPakketLijst.addActionListener(this);

        // Onresizable en zichtbaar maken
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e
    )
    {
        if (e.getSource() == handmatigPakketten)
        {
            String hoogte = JOptionPane.showInputDialog("Hoogte in cm invoeren:", JOptionPane.OK_CANCEL_OPTION);

            if (hoogte != null && hoogte.length() > 0)
            {

                try
                {
                    int intHoogte;
                    intHoogte = Integer.parseInt(hoogte);

                    // kijken of hoogte kleiner is dan 10, zo niet dan een joptionpane met een waarschuwing
                    if (intHoogte > 10 || intHoogte <= 0)
                    {
                        JOptionPane.showMessageDialog(this, "Hoogte moet kleiner dan 10 of groter dan 0 zijn!");
                    }
                    else
                    {
                        Artikel pakket = new Artikel(intHoogte);
                        pakketlijst.add(pakket);
                        model.fireTableDataChanged();
                    }
                }
                catch (NumberFormatException nfe)
                {
                    JOptionPane.showMessageDialog(this, "Geen nummer ingevoerd");
                }
            }
            lijstNaarTable();
            model.fireTableDataChanged();
        }
        else if (e.getSource() == genereerPakketten)
        {
            int aantalKeer = (Integer) aantal.getValue();

            // For-loop aan de hand van het aantal pakketten dat je wilt genereren.          
            for (int teller = 0; teller < aantalKeer; teller++)
            {
                int waarde = (int) (Math.random() * (11 - 1) + 1);
                Artikel pakket = new Artikel(waarde);
                pakketlijst.add(pakket);
            }

            lijstNaarTable();
            model.fireTableDataChanged();
        }
        else if (e.getSource() == leegPakketLijst)
        {
            //Voorgaande rijen removen uit JTable
            int aantalRijen = model.getRowCount();
            for (int i = aantalRijen - 1; i >= 0; i--)
            {
                model.removeRow(i);
            }

            //ArrayList legen
            pakketlijst.removeAll(pakketlijst);
        }
        else if (e.getSource() == startSimulatie)
        {
//            int aantalJtable = jtPakketlijst.getSize();
//            resultatenlijst.setValueAt(aantalJtable, 0, 1);

//            SimpelGretig sg = new SimpelGretig(pakketlijst);
//            sg.berekenVolgorde();
            VolledigeEnummeratie ve = new VolledigeEnummeratie(pakketlijst);
            ve.vul();

        }
        else if (e.getSource() == stopSimulatie)
        {

        }
    }

    public void lijstNaarTable()
    {
        if (pakketlijst.size() > 0)
        {
            // Eerst rijen legen
            int aantalRijen = model.getRowCount();
            for (int i = aantalRijen - 1; i >= 0; i--)
            {
                model.removeRow(i);
            }

            // Rijen vullen            
            int pakketTeller = 1;
            for (Artikel pakket : pakketlijst)
            {
                int ph = pakket.getHoogte();
                model.addRow(new Object[]
                {
                    "" + pakketTeller + "", "" + ph + "",
                });
                pakketTeller++;
            }
        }
    }

}
