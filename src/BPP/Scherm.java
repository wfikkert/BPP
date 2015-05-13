package BPP;

import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import javax.swing.*;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.*;
import javax.swing.table.*;

public class Scherm extends JFrame implements ActionListener
{
    private JPanel jpLinks;
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
    private JDialog dlg;

    public Scherm()
    {
        setTitle("BPP-Simulator");
        setSize(1280, 720);
        setLayout(new GridLayout(1, 2));

        //Artikellijst arraylist aanmaken
        artikellijst = new ArrayList<>();

        // Linker en rechterpaneel aanmaken
        jpLinks = new JPanel();
        JPanel jpRechts = new JPanel();

        // Tekenpanel
        tp = new Tekenpanel(this);
        jpLinks.add(tp);

        // Rechtervak verdelen in 4 rijen (bij 1)
        jpRechts.setLayout(new GridLayout(4, 1));

        // Rij 1 van het rechtervak met invulling ervan, verdelen in 2 rijen met 1 kolom
        JPanel jpArtikelSelectie = new JPanel();
        jpArtikelSelectie.setLayout(new BorderLayout());
        jlSelectArtikelTitel = new JLabel("Artikelen toevoegen");
        jlSelectArtikelTitel.setFont(new Font("SansSerif", Font.BOLD, 26));

        // Titel 'Artikel toevoegen' positioneren op de BorderLayout.NORTH
        jpArtikelSelectie.add(jlSelectArtikelTitel, BorderLayout.NORTH);

        JPanel jpSelectArtikelInh = new JPanel();
        jpSelectArtikelInh.setLayout(new BorderLayout());
        JPanel jpSelectArtikelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Nummerspinner en 'Genereer artikelten' button aanmaken en toevoegen aan JPannel
        aantal = new JSpinner(new SpinnerNumberModel(1, 0, null, 1));
        genereerArtikelen = new JButton("Genereer artikel");
        jpSelectArtikelButtons.add(aantal);
        jpSelectArtikelButtons.add(genereerArtikelen);

        // JPanel met als inhoud spinner & genereer artikel toevoegen aan BorderLayout.NORTH
        jpSelectArtikelInh.add(jpSelectArtikelButtons, BorderLayout.NORTH);

        // JPanel 'Handmatig artikelen toevoegen', die op BorderLayout.CENTER
        JPanel jpSelectArtikelButtonsHandm = new JPanel(new FlowLayout(FlowLayout.LEFT));
        handmatigArtikelen = new JButton("Handmatig artikelen toevoegen");
        jpSelectArtikelButtonsHandm.add(handmatigArtikelen);
        jpSelectArtikelInh.add(jpSelectArtikelButtonsHandm, BorderLayout.CENTER);
        jpArtikelSelectie.add(jpSelectArtikelInh);

        // JPanel aanmaken voor artikellijst
        JPanel jpArtikellijst = new JPanel();
        jpArtikellijst.setLayout(new BorderLayout());

        // Titel 'Artikellijst's
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

        // JTable en leeg artikellijst button toevoegen
        jpArtikellijst.add(titelArtikellijst, BorderLayout.NORTH);
        jpArtikellijst.add(jtArtikellijst, BorderLayout.CENTER);
        jpArtikellijst.add(leegArtikellijst, BorderLayout.EAST);

        // JScrollPane aanmaken voor JTable
        JScrollPane scrollbarLijst = new JScrollPane();
        scrollbarLijst.setViewportView(jtArtikellijst);

        //JScrollPane toevoegen aan JTable
        jpArtikellijst.add(scrollbarLijst, BorderLayout.CENTER);

        // Vak voor 'algoritme selecteren' en 'acties'
        JPanel jpAlgoEnActies = new JPanel();
        jpAlgoEnActies.setLayout(new GridLayout(1, 2));

        // Vullen van vakken algoritme selecteren en acties
        JPanel jpSelectAlgo = new JPanel(new BorderLayout());
        JLabel algoritmeKiezen = new JLabel("Algoritme kiezen");
        algoritmeKiezen.setFont(new Font("SansSerif", Font.BOLD, 26));
        jpSelectAlgo.add(algoritmeKiezen, BorderLayout.NORTH);

        // DefaultListModel voor algoritmeselectie
        DefaultListModel algoLijstInhoud = new DefaultListModel();
        algoLijstInhoud.addElement("Volledige Enumeratie");
        algoLijstInhoud.addElement("Simpel Gretig");
        algoLijstInhoud.addElement("Almost Worst");

        // Nieuwe JList aanmaken met waardes van DefaultListModel
        algoLijst = new JList(algoLijstInhoud);
        algoLijst.setSelectedIndex(0);
        algoLijst.setBorder(new LineBorder(Color.BLACK));
        algoLijst.setSelectionMode(SINGLE_SELECTION);
        jpSelectAlgo.add(algoLijst, BorderLayout.CENTER);

        //Vak 'acties' een titel geven
        JPanel jpSimulatorActies = new JPanel(new BorderLayout());
        JLabel acties = new JLabel("Acties");
        acties.setFont(new Font("SansSerif", Font.BOLD, 26));
        jpSimulatorActies.add(acties, BorderLayout.NORTH);

        // Inhoud maken voor vak 'acties'
        JPanel jpActieButtons = new JPanel();
        jpActieButtons.setLayout(new GridLayout(2, 1));
        startSimulatie = new JButton("Start simulatie");
        stopSimulatie = new JButton("Stop simulatie");

        // Inhoud daadwerkelijk toevoegen aan vak 'acties'
        jpActieButtons.add(startSimulatie);
        jpActieButtons.add(stopSimulatie);
        disableStopButton();
        jpSimulatorActies.add(jpActieButtons, BorderLayout.CENTER);

        // Overkoepelende JPanel acties en algoritme selecteren toevoegen
        jpAlgoEnActies.add(jpSelectAlgo);
        jpAlgoEnActies.add(jpSimulatorActies);

        // Vak resultaten aanmaken
        JPanel jpResulatenOverzicht = new JPanel(new BorderLayout());
        JLabel resultaten = new JLabel("Resultaten");
        resultaten.setFont(new Font("SansSerif", Font.BOLD, 26));
        jpResulatenOverzicht.add(resultaten, BorderLayout.NORTH);

        // DefaultTableModel aanmaken voor JTable
        model2 = new DefaultTableModel();
        resultatenlijst = new JTable(model2);
        // Kolommen toevoegen
        model2.addColumn("Algoritme");
        model2.addColumn("Aantal pakketten");
        model2.addColumn("Aantal artikelen");
        model2.addColumn("Pakketten gemiddeld % gevuld");

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
            "" + a + "", "" + b + "", "" + c + "", "" + d + "",
        });

    }

    @Override
    public void actionPerformed(ActionEvent e)
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
            // Keuze algoritme ophalen
            int keuze = algoLijst.getSelectedIndex();

            // Zodra op start is gedrukt, startknop disablen en stop knop enablen
            startSimulatie.setEnabled(false);
            stopSimulatie.setEnabled(true);

            try
            {
                // Eerst refreshen van tekenpanel
                // Dit omdat er anders nog oude data in staat.
                if (keuze == 1 || keuze == 2)
                {
                    jpLinks.remove(0);
                    tp = new Tekenpanel(this);
                    jpLinks.add(tp);
                    revalidate();
                }

                if (keuze == 0)
                {
                    jpLinks.remove(0);
                    TekenpanelVE tpVE = new TekenpanelVE();
                    jpLinks.add(tpVE);
                    revalidate();

                    VolledigeEnumeratie ve = new VolledigeEnumeratie(artikellijst, this);

                    // 'Even wachten' dialoog tot VUL() methode klaar is
                    dlg = new JDialog(this, "Even geduld a.u.b.");
                    dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                    dlg.setSize(300, 25);
                    dlg.setLocationRelativeTo(this);

                    dlg.setVisible(true);

                    ve.vul();
                    dlg.setVisible(false);
                    tpVE.artikelAnimatie(ve.getRecordPakketLijst());
                    if (ve.aantalGevuld(ve.getRecordPakketLijst()) > 30)
                    {
                        JOptionPane.showMessageDialog(this, "Er worden maar 30 pakketten getoont in de Volledige Enumeratie simulatie");
                    }

                    startSimulatie.setEnabled(true);
                    stopSimulatie.setEnabled(false);

                }
                else if (keuze == 1)
                {
                    SimpelGretig gr = new SimpelGretig(artikellijst, this);
                    gr.vul();
                    aanleveringVoorAnimatie(gr.getActiePerArtikel());

                }
                else if (keuze == 2)
                {
                    AlmostWorst aw = new AlmostWorst(artikellijst, this);
                    aw.vul();
                    aanleveringVoorAnimatie(aw.getActiePerArtikel());

                }
            }
            catch (ArithmeticException ae)
            {
                jpLinks.remove(0);
                tp = new Tekenpanel(this);
                jpLinks.add(tp);
                revalidate();
                JOptionPane.showMessageDialog(this, "Er zijn geen artikelen meegegeven");
                dlg.setVisible(false);
                disableStopButton();
                enableStartButton();
            }

        }
        else if (e.getSource() == stopSimulatie)
        {
            // Wanneer stop knop ingedrukt is, alle timers stoppen uit JPanel
            for (TimerTask tt : tp.getTimers())
            {
                tt.cancel();

            }
            // Daarna JPanel refreshen en startknop enablen en stopknop disablen
            jpLinks.remove(0);
            tp = new Tekenpanel(this);
            jpLinks.add(tp);
            revalidate();
            enableStartButton();
            disableStopButton();
        }

    }

    public void enableStartButton()
    {
        startSimulatie.setEnabled(true);
    }

    public void enableStopButton()
    {
        stopSimulatie.setEnabled(true);
    }

    public void disableStopButton()
    {
        stopSimulatie.setEnabled(false);
    }

    public void disableStartButton()
    {
        startSimulatie.setEnabled(false);
    }

    // lijst naar table wordt aangeroepen na het uitrekenen van de algoritmes
    // deze converteert data van de arraylist naar een tabel
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

    public void aanleveringVoorAnimatie(ArrayList<ArrayList<String>> a)
    {
        for (int iHoofdArray = 0; iHoofdArray < a.size(); iHoofdArray++)
        {
            // Timers setten per for-loop, zodat elke artikelAnimatie een eigen timer heeft
            Timer timer = new Timer();
            Timer timer2 = new Timer();
            Timer timer3 = new Timer();
            Timer eindTimer = new Timer();
            for (int iSubArray = 0; iSubArray < a.get(iHoofdArray).size(); iSubArray++)
            {
                tp.artikelAnimatie(a.get(iHoofdArray).get(iSubArray), timer, timer2, timer3, eindTimer, artikellijst.get(iHoofdArray));

            }

        }
    }

}
