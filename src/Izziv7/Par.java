package APS1.Izziv7;

public class Par {
    private Sequence<Oseba> zaporedje;
    private int indeks;

    public Par(Sequence<Oseba> zaporedje, int indeks){
        this.zaporedje = zaporedje;
        this.indeks = indeks;
    }

    public int getIndeks() {
        return indeks;
    }

    public Sequence<Oseba> getZaporedje() {
        return zaporedje;
    }
}
