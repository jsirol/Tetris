package tetris.peli;

import tetris.peli.PalojenKaantoLogiikka;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import tetris.domain.Kuvio;
import tetris.domain.Nelio;
import tetris.domain.Pala;
import tetris.domain.Palkki;
import tetris.domain.Rivit;
import tetris.domain.jKirjain;
import tetris.domain.lKirjain;
import tetris.domain.sKirjain;
import tetris.domain.tKirjain;
import tetris.domain.zKirjain;
import tetris.gui.Paivitettava;

/**
 * Luokka muodostaa Tetriksen rungon. Luokka perii luokan Timer ja toteuttaa
 * rajapinnan ActionListener, joiden avulla pelin tapahtumat jaksotetaan. Luokka
 * sisältää tiedot pelialueesta, pelin riveistä, kuviosta jota pelaaja ohjaa, ja
 * säännöt kuinka kuviota liikutetaan.
 *
 * @author Johannes
 */
public class Tetris extends Timer implements ActionListener {

    /**
     * pelin rivit, eli palat joita pelaaja ei pysty ohjaamaan ja joita pelaajan
     * on tarkoitus tuhota
     */
    private Rivit rivit;
    /**
     * pelialueen leveys
     */
    private int leveys;
    /**
     * pelialueen korkeus
     */
    private int korkeus;
    /**
     * pelaajan ohjaama kuvio, joka putoaa tasaisesti pelialueen yläosasta
     * alaosaa kohti.
     */
    private Kuvio kuvio;
    /**
     * totuusarvo sille, onko peli käynnissä
     */
    private boolean peliKaynnissa;
    /**
     * oliomuuttujaan asetetaan piirtoalusta, eli olio jota päivitetään aina
     * jokaisen tapahtuman jälkeen (ts. piirretään pelialue uudelleen)
     */
    private Paivitettava paivitettava;
    /**
     * palojen kääntölogiikan sisältävä oliomuuttuja
     */
    private PalojenKaantoLogiikka kaanto;

    public Tetris(int leveys, int korkeus) {
        super(120, null);
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.rivit = new Rivit(leveys, korkeus);
        this.kuvio = this.arvoKuvio();
        this.peliKaynnissa = true;
        this.kaanto = new PalojenKaantoLogiikka(rivit, leveys, korkeus);

        addActionListener(this);
        setInitialDelay(500);
    }

    /**
     * Metodi arpoo ja palauttaa uuden Kuvio-olion, joka voi olla Nelio, Palkki,
     * zKirjain, sKirjain, jKirjain, lKirjain tai tKirjain.
     *
     * @return kuvio
     */
    public Kuvio arvoKuvio() {
        int arpa = new Random().nextInt(7);
        if (arpa == 0) {
            return new Nelio(this.leveys / 2, -2);
        } else if (arpa == 1) {
            return new Palkki(this.leveys / 2, -3);
        } else if (arpa == 2) {
            return new zKirjain(this.leveys / 2, -1);
        } else if (arpa == 3) {
            return new sKirjain(this.leveys / 2, -1);
        } else if (arpa == 4) {
            return new jKirjain(this.leveys / 2, -1);
        } else if (arpa == 5) {
            return new lKirjain(this.leveys / 2, -1);
        } else {
            return new tKirjain(this.leveys / 2, -1);
        }
    }

    /**
     * Metodi määrittää mitä tapahtuu kun ohjelman käsiteltäväksi tulee uusi
     * tahtuma. Uusien tapahtumien syntymistiheyden määrä luokalle asetettu
     * viive.
     *
     * @param e tapahtuma
     *
     * @see tetris.peli.Tetris#kuvioAlimmallaRivilla(tetris.domain.Kuvio)
     * @see tetris.peli.Tetris#kuvioKiinniYlimmassaRivissa(tetris.domain.Kuvio)
     * @see tetris.peli.Tetris#kuvioKiinniJossainRivissa(tetris.domain.Kuvio)
     * @see
     * tetris.peli.Tetris#tuhoaTaydetRivitJaPudotaYlempanaOleviaPalojaAlaspain()
     * @see tetris.peli.Tetris#arvoKuvio()
     * @see tetris.domain.Kuvio#pudotaYhdella()
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.peliKaynnissa) {
            if (!this.kuvioAlimmallaRivilla(kuvio) && !this.kuvioKiinniJossainRivissa(kuvio)) {
                kuvio.pudotaYhdella();
            } else {
                for (Pala pala : this.kuvio.getPalat()) {
                    this.rivit.lisaaPala(new Pala(pala.getX(), pala.getY(), pala.getVarinNimi()));
                }
                this.tuhoaTaydetRivitJaPudotaYlempanaOleviaPalojaAlaspain();
                this.kuvio = this.arvoKuvio();
            }
            if (this.kuvioKiinniYlimmassaRivissa(kuvio)) {
                this.peliKaynnissa = false;
                this.stop();
            }
            paivitettava.paivita();
        }
    }

    /**
     * Metodi tuhoaa täydet rivit ja pudottaa ylempien rivien paloja alaspäin
     * yhden koordinaatin verran.
     *
     * @see tetris.peli.Tetris#pudotaYlempanaOlevienRivienPalojaYhdella(int)
     * @see tetris.domain.Rivit#tuhoaRivi(int)
     * @see tetris.domain.Rivit#riviTaysi(int, int)
     */
    public void tuhoaTaydetRivitJaPudotaYlempanaOleviaPalojaAlaspain() {
        for (int i = 0; i < this.korkeus; i++) {
            if (this.rivit.riviTaysi(i, leveys)) {
                this.rivit.tuhoaRivi(i);
                this.pudotaYlempanaOlevienRivienPalojaYhdella(i);
            }
        }
    }

    /**
     * Metodi pudottaa parametrina annettua riviä ylempien rivien paloja
     * alaspäin yhden koordinaatin verran.
     *
     * @param riviJonkaYlapuoleltaPudotetaan rivinumero jonka yläpuolelta paloja
     * pudotetaan
     *
     * @see tetris.domain.Kuvio#getRivinPalat(int)
     * @see tetris.domain.Pala#pudotaYhdella()
     */
    public void pudotaYlempanaOlevienRivienPalojaYhdella(int riviJonkaYlapuoleltaPudotetaan) {
        for (int j = riviJonkaYlapuoleltaPudotetaan - 1; j > 0; j--) {
            for (Pala pala : this.rivit.getRivinPalat(j)) {
                pala.pudotaYhdella();
            }
        }
    }

    /**
     * Metodi palauttaa totuusarvon sille, onko parametrina annettava kuvio
     * kiinni pelialueen alimmassa (y-koordinaatiltaan suurimassa) rivissä.
     *
     * @param kuvio kuvio jolle tarkistus tehdään
     *
     * @return totuusarvo sille, onko kuvio kiinni alimmassa rivissä
     *
     * @see tetris.domain.Kuvio#getPalaJollaSuurinYKoordinaatti()
     */
    public boolean kuvioAlimmallaRivilla(Kuvio kuvio) {
        if (kuvio.getPalaJollaSuurinYKoordinaatti().getY() == this.korkeus - 1) {
            return true;
        }
        return false;
    }

    /**
     * Metodi palauttaa totuusarvon sille, onko parametrina annettava kuvio
     * kiinni pelialueen ylimmässä (y-koordinaatiltaan pienimmässä,
     * oletusarvoisesti 0.) rivissä.
     *
     * @param kuvio kuvio jolle tarkistus tehdään
     *
     * @return totuusarvo sille, onko kuvio kiinni ylimmässä rivissä
     *
     * @see tetris.domain.Kuvio#getPalaJollaPieninYKoordinaatti()
     * @see tetris.peli.Tetris#kuvioKiinniJossainRivissa(tetris.domain.Kuvio)
     */
    public boolean kuvioKiinniYlimmassaRivissa(Kuvio kuvio) {
        if (kuvio.getPalaJollaPieninYKoordinaatti().getY() <= 0 && this.kuvioKiinniJossainRivissa(kuvio)) {
            return true;
        }
        return false;
    }

    /**
     * Metodi palauttaa totuusarvon sille, onko parametrina annettava kuvio
     * kiinni jossakin pelialueen rivissä.
     *
     * @param kuvio tarkastettava kuvio
     *
     * @return totuusarvo sille, onko kuvio kiinni jossakin rivissä
     *
     * @see tetris.peli.Tetris#kuvioKiinniTietyssaRivissa(int,
     * tetris.domain.Kuvio)
     */
    public boolean kuvioKiinniJossainRivissa(Kuvio kuvio) {
        for (int i = 0; i < this.korkeus; i++) {
            if (this.kuvioKiinniTietyssaRivissa(i, kuvio)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi palauttaa totuusarvon sille, onko paramentrina annettava kuvio
     * kiinni ensimmäisenä parametrina annettavan rivinumeron määräämässä
     * rivissä (ts. onko kuviossa pala jonka y-koordinaatin arvo on yhtä
     * pienempi ja x-koordinaatin arvo sama, kuin jollain tarkastettavan rivin
     * palalla).
     *
     * @param riviNro rivin järjestysnumero
     * @param kuvio tarkistettava kuvio
     *
     * @return totuusarvo sille, onko kuvio kiinni tietyssä rivissä
     *
     * @see tetris.domain.Kuvio#getRivinPalat(int)
     * @see tetris.domain.Kuvio#getPalat()
     */
    public boolean kuvioKiinniTietyssaRivissa(int riviNro, Kuvio kuvio) {
        for (Pala pala : this.rivit.getRivinPalat(riviNro)) {
            for (Pala kuvionPala : kuvio.getPalat()) {
                if (pala.getY() == kuvionPala.getY() + 1 && pala.getX() == kuvionPala.getX()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metodi palauttaa totuusarvon sille, osuuko kuvio johonkin riviin
     * siirrettäessä sitä parametrin osoittamaan suuntaan (negatiivinen arvo
     * vasemmalle, positiivinen oikealle).
     *
     * @param negatiivinenOnVasenSuuntaMuutenOikea siirtosuunta
     *
     * @return totuusarvo sille, osuuko kuvio riviin siirrettäessä
     *
     * @see tetris.domain.Kuvio#getPalat()
     * @see tetris.domain.Pala#vieressaOnPala(int, tetris.domain.Pala)
     */
    public boolean kuviotaVoiSiirtaaSuuntaanOsumattaRiviin(int negatiivinenOnVasenSuuntaMuutenOikea) {
        for (Pala pala : this.kuvio.getPalat()) {
            for (Pala rivinPala : this.rivit.getPalat()) {
                if (pala.vieressaOnPala(negatiivinenOnVasenSuuntaMuutenOikea, rivinPala)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Metodi tarkistaa voiko kuviota liikuttaa vasemmalle ja jos voi, liikuttaa
     * sitä vasemmalle.
     *
     * @see tetris.domain.Kuvio#getPalaJollaPieninXKoordinaatti()   
     * @see tetris.domain.Kuvio#siirraKuviotaSivuttain(int)
     * @see tetris.peli.Tetris#kuviotaVoiSiirtaaSuuntaanOsumattaRiviin(int) 
     */
    public void liikutaKuviotaVasemmalle() {
        if (this.kuvio.getPalaJollaPieninXKoordinaatti().getX() != 0 && this.kuviotaVoiSiirtaaSuuntaanOsumattaRiviin(-1)) {
            this.getKuvio().siirraKuviotaSivuttain(-1);
        }
    }

     /**
     * Metodi tarkistaa voiko kuviota liikuttaa oikealle ja jos voi, 
     * liikuttaa sitä oikealle.
     *     
     * @see tetris.domain.Kuvio#getPalaJollaSuurinXKoordinaatti()
     * @see tetris.domain.Kuvio#siirraKuviotaSivuttain(int)
     * @see tetris.peli.Tetris#kuviotaVoiSiirtaaSuuntaanOsumattaRiviin(int) 
     */
    public void liikutaKuviotaOikealle() {
        if (this.kuvio.getPalaJollaSuurinXKoordinaatti().getX() != this.leveys - 1 && this.kuviotaVoiSiirtaaSuuntaanOsumattaRiviin(1)) {
            this.kuvio.siirraKuviotaSivuttain(1);
        }
    }

    /**
     * Metodi tarkistaa voiko parametrina annettavaa kuviota pudottaa alaspäin yhden koordinaatin verran ja jos voi,
     * pudottaa sitä yhden alaspäin.
     * 
     * @param kuvio pudotettava kuvio
     * 
     * @see tetris.domain.Kuvio#pudotaYhdella() 
     * @see tetris.peli.Tetris#kuvioKiinniJossainRivissa(tetris.domain.Kuvio) 
     * @see tetris.peli.Tetris#kuvioAlimmallaRivilla(tetris.domain.Kuvio) 
     */
    public void pudotaKuviotaAlasYhdellaJosVoi(Kuvio kuvio) {
        if (!this.kuvioAlimmallaRivilla(kuvio) && !this.kuvioKiinniJossainRivissa(kuvio)) {
            this.getKuvio().pudotaYhdella();
        }
    }

    /**
     * Metodi tarkistaa voiko kuviota kääntää ja jos voi, kääntää sitä.
     * 
     * @see tetris.peli.PalojenKaantoLogiikka#putoavaaKuviotaVoiKaantaa(tetris.domain.Kuvio) 
     * @see tetris.domain.Kuvio#kaanna() 
     */
    public void kaannaKuviota() {
        if (this.kaanto.putoavaaKuviotaVoiKaantaa(this.kuvio)) {
            this.kuvio.kaanna();
        }
    }

    /**
     * Metodi pudottaa parametrina annettavan kuvion niin alas pelialueella kuin voi
     * (ts. kunnes kuvio osuu johonkin riviin tai pelialueen alalaitaan).
     * 
     * @param kuvio pudotettava kuvio
     * 
     * @see tetris.peli.Tetris#kuvioAlimmallaRivilla(tetris.domain.Kuvio) 
     * @see tetris.peli.Tetris#kuvioKiinniJossainRivissa(tetris.domain.Kuvio) 
     * @see tetris.peli.Tetris#pudotaKuviotaAlasYhdellaJosVoi(tetris.domain.Kuvio) 
     */
    public void pudotaKuvioNiinAlasKuinVoi(Kuvio kuvio) {
        while (true) {
            if (!this.kuvioAlimmallaRivilla(kuvio) && !this.kuvioKiinniJossainRivissa(kuvio)) {
                this.pudotaKuviotaAlasYhdellaJosVoi(kuvio);
            } else {
                break;
            }
        }
    }

    public boolean getPeliKaynnissa() {
        return this.peliKaynnissa;
    }

    public Kuvio getKuvio() {
        return this.kuvio;
    }

    public void setKuvio(Kuvio kuvio) {
        this.kuvio = kuvio;
    }

    public int getKorkeus() {
        return this.korkeus;
    }

    public int getLeveys() {
        return this.leveys;
    }

    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
    }

    public Rivit getRivit() {
        return this.rivit;
    }

    public void setPelikaynnissa(boolean totuusarvo) {
        this.peliKaynnissa = totuusarvo;
    }
}
