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

    private JButton artikelToevoegen;
    private JLabel jlSelectArtikelTitel;
    private JButton stopSimulatie;
    private JButton startSimulatie;
    private Tekenpanel tp;
    private JSpinner aantal;
    private JButton genereerArtikelen;
    private JButton handmatigArtikelen;
    private JLabel titelArtikellijst;
    private JTable jtArtikellijst;
    private DefaultTableModel model;
    private DefaultTableModel model2;
    private ArrayList<Artikel> artikellijst;
    private JButton leegArtikellijst;
    private JTable resultatenlijst;
    private JList algoLijst;

    public Scherm()
    {
        setTitle("BPP-Simulator");
        setSize(1280, 720);
        setLayout(new GridLayout(1, 2));

        //Artikellijst aanmaken
        artikellijst = new ArrayList<>();

        // Linker en rechterpaneel aanmaken
        JPanel jpLinks = new JPanel();
        JPanel jpRechts = new JPanel();

        // Tekenpanel
        tp = new Tekenpanel();
        jpLinks.add(tp);

        // Rechtervak verdelen in 4 rijen (bij 1)
        jpRechts.setLayout(new GridLayout(5, 1));

        // Rij 1 van het rechtervak met invulling ervan, verdelen in 2 rijen met 1 kolom
        JPanel jpArtikelSelectie = new JPanel();
        jpArtikelSelectie.setLayout(new BorderLayout());
        jlSelectArtikelTitel = new JLabel("Artikelen toevoegen");
        jlSelectArtikelTitel.setFont(new Font("SansSerif", Font.BOLD, 26));

        // Eerste rij van de GridLayout vullen, van vak 1
        jpArtikelSelectie.add(jlSelectArtikelTitel, BorderLayout.NORTH);

        // JPanel aanmaken voor het vak 1.2, onderverdelen in 2 rijen en 1 kolom
        JPanel jpSelectArtikelInh = new JPanel();
        jpSelectArtikelInh.setLayout(new BorderLayout());
        JPanel jpSelectArtikelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Nummerspinner en 'Genereer artikelten' button aanmaken en toevoegen aan JPannel
        aantal = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
        genereerArtikelen = new JButton("Genereer artikel");
        jpSelectArtikelButtons.add(aantal);
        jpSelectArtikelButtons.add(genereerArtikelen);

        // JPanel 1.2.1 toevoegen aan vak 1.2
        jpSelectArtikelInh.add(jpSelectArtikelButtons, BorderLayout.NORTH);

        // JPanel aanmaken voor 1.2.2, button 'Handmatig artikelen toevoegen'
        JPanel jpSelectArtikelButtonsHandm = new JPanel(new FlowLayout(FlowLayout.LEFT));
        handmatigArtikelen = new JButton("Handmatig artikelen toevoegen");
        jpSelectArtikelButtonsHandm.add(handmatigArtikelen);

        // Vak 1.2.2 toevoegen aan 1.2
        jpSelectArtikelInh.add(jpSelectArtikelButtonsHandm, BorderLayout.CENTER);

        // Vak 1.2 toevoegen aan vak 1
        jpArtikelSelectie.add(jpSelectArtikelInh);

        // JPanel aanmaken voor vak 2
        JPanel jpArtikellijst = new JPanel();
        jpArtikellijst.setLayout(new BorderLayout());

        // Titel aanmaken voor vak 2
        titelArtikellijst = new JLabel("Artikellijst");
        titelArtikellijst.setFont(new Font("SansSerif", Font.BOLD, 26));

        // JTable aanmaken voor de artikellijst
        model = new DefaultTableModel();
        JTable jtArtikellijst = new JTable(model);
        // Kolommen toevoegen
        model.addColumn("Artikelnr.");
        model.addColumn("Hoogte");

        jtArtikellijst.getColumn("Artikelnr.").setMaxWidth(100);
        jtArtikellijst.setEnabled(false);
        jtArtikellijst.getTableHeader().setReorderingAllowed(false);
        jtArtikellijst.getTableHeader().setResizingAllowed(false);

        // Leeg artikellijst button
        leegArtikellijst = new JButton("Leeg artikellijst");

        // JTable toevoegen aan vak 2
        jpArtikellijst.add(titelArtikellijst, BorderLayout.NORTH);
        jpArtikellijst.add(jtArtikellijst, BorderLayout.CENTER);
        jpArtikellijst.add(leegArtikellijst, BorderLayout.EAST);

        // JScrollPane aanmaken
        JScrollPane scrollbarLijst = new JScrollPane();
        scrollbarLijst.setViewportView(jtArtikellijst);

        //JScrollPane toevoegen aan vak 2
        jpArtikellijst.add(scrollbarLijst, BorderLayout.CENTER);

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

        algoLijst = new JList(algoLijstInhoud);
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
        model2.addColumn("Aantal pakketten");
        model2.addColumn("Aantal artikelen");
        model2.addColumn("Pakketten gemiddeld % gevuld");
       

        //resultatenlijst.getColumn("Artikelnr.").setMaxWidth(100);
        resultatenlijst.setEnabled(false);
        resultatenlijst.getTableHeader().setReorderingAllowed(false);
        resultatenlijst.getTableHeader().setResizingAllowed(false);

        jpResulatenOverzicht.add(resultatenlijst, BorderLayout.CENTER);

        scrollbarLijst = new JScrollPane();
        scrollbarLijst.setViewportView(resultatenlijst);

        jpResulatenOverzicht.add(scrollbarLijst);

        // Vakken toevoegen aan rechterpaneel
        jpRechts.add(jpArtikelSelectie);
        jpRechts.add(jpArtikellijst);
        jpRechts.add(jpAlgoEnActies);
        jpRechts.add(jpResulatenOverzicht);

        // Linker & Rechterpaneel toevoegen
        add(jpLinks);
        add(jpRechts);

        // actionlisteners toevoegen
        genereerArtikelen.addActionListener(this);
        handmatigArtikelen.addActionListener(this);
        startSimulatie.addActionListener(this);
        stopSimulatie.addActionListener(this);
        leegArtikellijst.addActionListener(this);

        // Onresizable en zichtbaar maken
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addResultaten(String a, int b, int c, int d)
    {

        model2.addRow(new Object[]
        {
            "" + a + "", "" + b + "","" + c + "", "" + d + "",
        });

    }

    @Override
    public void actionPerformed(ActionEvent e
    )
    {
        if (e.getSource() == handmatigArtikelen)
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
                        Artikel artikel = new Artikel(intHoogte);
                        artikellijst.add(artikel);
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
        else if (e.getSource() == genereerArtikelen)
        {
            int aantalKeer = (Integer) aantal.getValue();

            // For-loop aan de hand van het aantal artikelen dat je wilt genereren.          
            for (int teller = 0; teller < aantalKeer; teller++)
            {
                int waarde = (int) (Math.random() * (11 - 1) + 1);
                Artikel artikel = new Artikel(waarde);
                artikellijst.add(artikel);
            }

            lijstNaarTable();
            model.fireTableDataChanged();
        }
        else if (e.getSource() == leegArtikellijst)
        {
            //Voorgaande rijen removen uit JTable
            int aantalRijen = model.getRowCount();
            for (int i = aantalRijen - 1; i >= 0; i--)
            {
                model.removeRow(i);
            }

            //ArrayList legen
            artikellijst.removeAll(artikellijst);
        }
        else if (e.getSource() == startSimulatie)
        {
            int keuze = algoLijst.getSelectedIndex();
            if (keuze == 0)
            {

            }
            else if (keuze == 1)
            {
                SimpelGretig gr = new SimpelGretig(artikellijst,this);
                gr.vul();
            }
            else if (keuze == 2)
            {

            }

//            int aantalJtable = jtArtikellijst.getSize();
//            resultatenlijst.setValueAt(aantalJtable, 0, 1);
//            SimpelGretig sg = new SimpelGretig(artikellijst);
//            sg.berekenVolgorde();
        }
        else if (e.getSource() == stopSimulatie)
        {

        }
    }

    public void lijstNaarTable()
    {
        if (artikellijst.size() > 0)
        {
            // Eerst rijen legen
            int aantalRijen = model.getRowCount();
            for (int i = aantalRijen - 1; i >= 0; i--)
            {
                model.removeRow(i);
            }

            // Rijen vullen            
            int artikelTeller = 1;
            for (Artikel artikel : artikellijst)
            {
                int ph = artikel.getHoogte();
                model.addRow(new Object[]
                {
                    "" + artikelTeller + "", "" + ph + "",
                });
                artikelTeller++;
            }
        }
    }

}
