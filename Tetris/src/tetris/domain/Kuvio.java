package tetris.domain;

import java.util.ArrayList;

/**
 *
 * @author Johannes
 */
//kuvio koostuu paloista jotka kootaan ArrayListiin
public class Kuvio implements Kaantyva {

    private ArrayList<Pala> palat;

    public Kuvio() {
        this.palat = new ArrayList<Pala>();
    }

    //lisää kuvioon metodin parametrina annetun palan
    public void lisaaPala(Pala pala) {
        this.palat.add(pala);
    }

    //poistaa palan kuviosta
    public void poistaPala(Pala pala) {
        this.palat.remove(pala);
    }

    //palauttaa kuvion palat sisältävän listan
    public ArrayList<Pala> getPalat() {
        return this.palat;
    }

    //palauttaa kuviossa tietyllä rivillä esiintyvät palat listassa. parametrin isompi arvo tarkoittaa alempaa riviä.
    public ArrayList<Pala> getRivinPalat(int moneskoRivi) {
        ArrayList<Pala> rivinPalat = new ArrayList<Pala>();
        for (Pala pala : this.palat) {
            if (pala.getY() == moneskoRivi) {
                rivinPalat.add(pala);
            }
        }
        return rivinPalat;
    }

    //lisää jokaisen kuvioon kuuluvan palan y-koordinaattiin yhden
    public void pudotaYhdella() {
        for (Pala pala : this.palat) {
            pala.setY(pala.getY() + 1);
        }
    }

    //siirtaa kuviota sivulle, negatiivinen luku tarkoittaa vasempaa ja positiivinen oikeaa. 0 ei siirrä kuviota.
    public void siirraKuviotaSivuttain(int suuntaJaMaara) {
        for (Pala pala : this.palat) {
            if (suuntaJaMaara < 0) {
                pala.setX((pala.getX() - 1));
            } else if (suuntaJaMaara > 0) {
                pala.setX(pala.getX() + 1);
            }
        }
    }

    //ei tee mitään, toteutetaan erikseen aliluokille
    @Override
    public void kaanna() {
    }

    //palauttaa kuviossa esiintyvista paloista eniten oikealla olevan
    public Pala getPalaJollaSuurinXKoordinaatti() {
        Pala suurin = null;
        for (Pala pala : this.palat) {
            if (suurin == null) {
                suurin = pala;
            } else if (pala.getX() > suurin.getX()) {
                suurin = pala;
            }
        }
        return suurin;
    }

    //palauttaa kuviossa esiintyvista paloista eniten vasemmalla olevan
    public Pala getPalaJollaPieninXKoordinaatti() {
        Pala pienin = null;
        for (Pala pala : this.palat) {
            if (pienin == null) {
                pienin = pala;
            } else if (pala.getX() < pienin.getX()) {
                pienin = pala;
            }
        }
        return pienin;
    }

    //palauttaa kuviossa esiintyvista paloista alimpana olevan
    public Pala getPalaJollaSuurinYKoordinaatti() {
        Pala suurin = null;
        for (Pala pala : this.palat) {
            if (suurin == null) {
                suurin = pala;
            } else if (pala.getY() > suurin.getY()) {
                suurin = pala;
            }
        }
        return suurin;
    }

    //palauttaa kuviossa esiintyvista paloista ylimpänä olevan
    public Pala getPalaJollaPieninYKoordinaatti() {
        Pala pienin = null;
        for (Pala pala : this.palat) {
            if (pienin == null) {
                pienin = pala;
            } else if (pala.getY() < pienin.getY()) {
                pienin = pala;
            }
        }
        return pienin;
    }

    public boolean onkoKuviossaTietyssaKoordinaatissaJoPala(int x, int y) {
        for (Pala pala : this.getPalat()) {
            if (pala.getX() == x && pala.getY() == y) {
                return true;
            }
        }
        return false;
    }

    //toteutuksen hoitavat aliluokat
    @Override
    public Pala getRotaatioPisteenaOlevaPala() {
        return null;
    }

    @Override
    public String toString() {
        String merkkijono = "";
        for (Pala pala : this.palat) {
            if (this.palat.indexOf(pala) < this.palat.size() - 1) {
                merkkijono += pala.toString() + ", ";
            } else {
                merkkijono += pala.toString();
            }
        }
        return merkkijono;
    }
}
