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

    //lisää jokaisen kuvioon kuuluvan palan y-koordinaattiin yhden
    public void pudotaYhdella() {
        for (Pala pala : this.palat) {
            pala.setY(pala.getY() + 1);
        }
    }

    //vähentää jokaisen kuvioon kuuluvan palan x-koordinaattia yhdellä
    public void siirraVasemmalle() {
        for (Pala pala : this.palat) {
            pala.setX(pala.getX() - 1);
        }
    }

    //lisää jokaisen kuvioon kuuluvan palan x-koordinaattia yhdellä
    public void siirraOikealle() {
        for (Pala pala : this.palat) {
            pala.setX(pala.getX() + 1);
        }
    }

    //ei tee mitään, toteutetaan erikseen aliluokille
    @Override
    public void kaanna() {
    }

    //palauttaa kuviossa esiintyvista paloista eniten oikealla olevan
    public Pala getSuurimmanXKoordinaatinPala() {
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
    public Pala getPienimmänXKoordinaatinPala() {
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
    public Pala getSuurimmanYKoordinaatinPala() {
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
    public Pala getPienimmänYKoordinaatinPala() {
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
