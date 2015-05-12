package BPP;

public class Artikel
{
    
    private int hoogte;
    private int artikelnummer;
    private boolean geplaatst;
    
    public Artikel(int hoogte)
    {
        this.hoogte = hoogte;
    }

    public int getHoogte()
    {
        return hoogte;
    }
    
    public boolean isGeplaatst()
    {
        return geplaatst;
    }
    
    public void setGeplaatst(boolean b)
    {
        geplaatst = b;
    }
    
    public String toString()
    {
        return "Dit artikel heeft een hoogte van: " + hoogte;
    }
}
