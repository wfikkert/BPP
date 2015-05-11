package BPP;

import java.util.ArrayList;

public class VolledigeEnumeratie
{
    private Scherm scherm;
    private ArrayList<Artikel> artikellijst;
    private ArrayList<Pakket> pakketlijst;
    private ArrayList<Pakket> vollePakketten;
    private ArrayList<Pakket> recordPakketLijst;
    private int record;
    private int mogelijkheid = 0;

    public VolledigeEnumeratie(ArrayList<Artikel> pl, Scherm scherm)
    {
        artikellijst = new ArrayList<>();
        pakketlijst = new ArrayList<>();
        recordPakketLijst = new ArrayList();
        vollePakketten = new ArrayList<>();
        artikellijst = pl;
        this.scherm = scherm;

        // Voor elk artikel in iedergeval een pakket aanmaken (Worst scenario)
        for (Artikel a : artikellijst)
        {
            pakketlijst.add(new Pakket());
        }

        // Stel: oplossing == worst scenario, dan moet hij dit wel als record zien.
        record = artikellijst.size() + 1;

    }

    public void vul()
    {
        // Record bekijken
        if (allesGeplaatst())
        {
            // Mogelijkheid teller
            mogelijkheid++;

            // Kijken hoeveel pakketten er gevuld zijn met deze mogelijkheid
            int gevuldePakketten = aantalGevuld();
            // Checken of het een record is
            checkRecord(gevuldePakketten);

        }

        for (Artikel a : artikellijst)
        {
            if (!a.isGeplaatst() && mogelijkheid < 10000000)
            {
                for (Pakket p : pakketlijst)
                {

                    if (p.getOvergeblevenHoogte() >= a.getHoogte())
                    {

                        p.voegArtikelToe(a);
                        a.setGeplaatst(true);
                        vul();

                        p.verwijder(a);
                        a.setGeplaatst(false);
                    }

                }
            }

        }

    }

    public boolean allesGeplaatst()
    {
        boolean b = true;
        for (Artikel a : artikellijst)
        {
            if (!a.isGeplaatst())
            {
                b = false;
            }
        }
        return b;
    }

    public int aantalGevuld()
    {
        int gevuldePakketten = 0;
        for (Pakket p : pakketlijst)
        {
            if (p.getOvergeblevenHoogte() < p.getHoogte())
            {
                gevuldePakketten++;
            }
        }

        return gevuldePakketten;

    }

    public void checkRecord(int i)
    {
        // Check of er een record is, zo ja welke
        if (i < record)
        {
            record = i;

            for (Pakket p : pakketlijst)
            {
                Pakket nieuwPakket = new Pakket();

                if (p.getOvergeblevenHoogte() < p.getHoogte())
                {
                    int artikelnr = 1;
                    for (Artikel a : p.getInhoudPakket())
                    {
                        Artikel nieuwArtikel = new Artikel(a.getHoogte());
                        nieuwPakket.voegArtikelToe(nieuwArtikel);
                    }
                }
                recordPakketLijst.add(nieuwPakket);
            }
            resultaatNaarModel();
        }

    }

    public void resultaatNaarModel()
    {
        int teller = 0;
        int som = 0;
        for (Pakket p : recordPakketLijst)
        {
            if (p.getOvergeblevenHoogte() < p.getHoogte())
            {
                int percentage = (10 - p.getOvergeblevenHoogte()) * 100 / 10;
                teller++;
                som = som + percentage;
            }
        }

        int gemiddelde = som / teller;

        scherm.addResultaten("Volledige Enumeratie", teller, recordPakketLijst.size(), gemiddelde);

    }

    public ArrayList<Pakket> getRecordPakketLijst()
    {
        return recordPakketLijst;
    }
}
