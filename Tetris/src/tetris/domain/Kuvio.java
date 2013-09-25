package tetris.domain;

import java.util.ArrayList;

/**
 *Luokan oliot koostuvat Paloista. Luokan sisällä määritellään kaikille erilaisille
 * kuvioille yhteinen toiminnallisuus.
 * 
 * @see tetris.domain.Pala
 * 
 * @author Johannes
 */

public class Kuvio implements Kaantyva {

    /**
     * Kuvion palat tallennetaan ArrayList-olioon
     */
    private ArrayList<Pala> palat;

    public Kuvio() {
        this.palat = new ArrayList<Pala>();
    }

    /**
     * Metodi lisää kuvioon metodin parametrina annetun palan.
     * 
     * @param pala 
     */
    public void lisaaPala(Pala pala) {
        this.palat.add(pala);
    }

    /**
     * Metodi poistaa palan kuviosta.
     * 
     * @param pala 
     */
    public void poistaPala(Pala pala) {
        this.palat.remove(pala);
    }

    /**
     * Metodi palauttaa kuvion palat sisältävän listan.
     * 
     * @return kuvion palat sisältävä lista
     */
    public ArrayList<Pala> getPalat() {
        return this.palat;
    }

    /**
     * Metodi palauttaa kuviossa parametriksi annetulla rivillä olevat palat listana. 
     * 
     * @param moneskoRivi rivin järjestysnumero, isompi tarkoittaa alempaa riviä
     * @return lista, joka sisältää parametrina annetun rivin palat kuviossa.
     */
    public ArrayList<Pala> getRivinPalat(int moneskoRivi) {
        ArrayList<Pala> rivinPalat = new ArrayList<Pala>();
        for (Pala pala : this.palat) {
            if (pala.getY() == moneskoRivi) {
                rivinPalat.add(pala);
            }
        }
        return rivinPalat;
    }

    /**
     *Metodi asettaa jokaiselle kuvion palalle sijainnin y-koordinaatiksi arvon, joka on yhtä pienempi kuin koordinaatin sen hetkinen arvo.
     */
    public void pudotaYhdella() {
        for (Pala pala : this.palat) {
            pala.setY(pala.getY() + 1);
        }
    }

    /**
     * Metodi siirtää kuviota sivuttain.     
     * 
     * @param suuntaJaMaara negatiivinen negatiiviseen ja positiivinen positiiviseen suuntaan. Määrä kertoo paljonko siirretään. Parametrin arvo 0 ei siirrä kuviota.
     */    
    public void siirraKuviotaSivuttain(int suuntaJaMaara) {
        for (Pala pala : this.palat) {
            if (suuntaJaMaara < 0) {
                pala.setX((pala.getX() - 1));
            } else if (suuntaJaMaara > 0) {
                pala.setX(pala.getX() + 1);
            }
        }
    }

    /**
     * Rajapinnalta Kaantyva peritty metodi. 
     * Toteutetaan erikseen tämän luokan aliluokille.
     * 
     * @see  tetris.domain.LjaJmuotti#kaanna() 
     * @see  tetris.domain.Palkki#kaanna() 
     * @see  tetris.domain.sKirjain#kaanna() 
     * @see  tetris.domain.tKirjain#kaanna() 
     * @see  tetris.domain.zKirjain#kaanna() 
     */
    @Override
    public void kaanna() {
    }

    /**
     * Metodi palauttaa kuviossa esiintyvistä paloista sen, jolla on suurin x-koordinaatin arvo. 
     * Jos näitä paloja on usempia, palautetaan jokin niistä.
     * 
     * @return jokin pala, jonka x-koordinaatti on suurempi tai yhtäsuuri kuin kuvion muilla paloilla
     */   
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

    /**
     * Metodi palauttaa kuviossa esiintyvistä paloista sen, jolla on pienin x-koordinaatin arvo. 
     * Jos näitä paloja on usempia, palautetaan jokin niistä.
     * 
     * @return jokin pala, jonka x-koordinaatti on pienempi tai yhtäsuuri kuin kuvion muilla paloilla
     */   
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

    /**
     * Metodi palauttaa kuviossa esiintyvistä paloista sen, jolla on suurin y-koordinaatin arvo.
     * Jos näitä paloja on useampia, palautetaan jokin niistä.
     * 
     * @return jokin pala, jonka y-koordinaatti on suurempi tai yhtäsuuri kuin kuvion muilla paloilla
     */   
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

    /**
     * Metodi palauttaa kuviossa esiintyvistä paloista sen, jolla on pienin y-koordinaatin arvo.
     * Jos näitä paloja on useampia, palautetaan jokin niistä.
     * 
     * @return jokin pala, jonka y-koordinaatti on pienempi tai yhtäsuuri kuin kuvion muilla paloilla
     */
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

    /**
     * Metodi saa parametrinaan x-ja y-koordinaattien arvot ja kertoo onko kuviossa pala, jolla on tämä sijainti.
     * 
     * @param x x-koorinaatin arvo
     * @param y y-koordinaatin arvo
     * @return totuusarvo sille, onko kuviossa palaa parametrina annettavassa koordinaatissa.
     */
    public boolean onkoKuviossaTietyssaKoordinaatissaJoPala(int x, int y) {
        for (Pala pala : this.getPalat()) {
            if (pala.getX() == x && pala.getY() == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * Rajapinnalta Kaantyva perittävä metodi, joka palauttaa rotaatiopisteenä olevan palan.
     * Toteutetaan aliluokille.
     * 
     * @return palauttaa rotaatiopisteenä olevan palan, oletuksena null
     * 
     * @see  tetris.domain.LjaJmuotti#getRotaatioPisteenaOlevaPala() 
     * @see  tetris.domain.Palkki#getRotaatioPisteenaOlevaPala() 
     * @see  tetris.domain.sKirjain#getRotaatioPisteenaOlevaPala() 
     * @see  tetris.domain.tKirjain#getRotaatioPisteenaOlevaPala() 
     * @see  tetris.domain.zKirjain#getRotaatioPisteenaOlevaPala() 
     */
    @Override
    public Pala getRotaatioPisteenaOlevaPala() {
        return null;
    }

    /**
     * Metodi palauttaa kuvion merkkijonoesityksen.
     * 
     * @return kuvion merkkijonoesitys
     */
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
