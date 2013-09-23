package tetris.Peli;

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
 *
 * @author Johannes
 */
public class Tetris extends Timer implements ActionListener {

    private Rivit rivit;
    private int leveys;
    private int korkeus;
    private Kuvio kuvio;
    private boolean peliKaynnissa;
    private Paivitettava paivitettava;
    private PalojenKaantoLogiikka kaanto;

    public Tetris(int leveys, int korkeus) {       
        super(150, null);
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.rivit = new Rivit(leveys, korkeus);
        this.kuvio = this.arvoKuvio();
        this.peliKaynnissa = true;
        this.kaanto = new PalojenKaantoLogiikka(rivit, leveys, korkeus);

        addActionListener(this);
        setInitialDelay(500);       
        

    }

    //arpoo ja palauttaa uuden kuvion
    public Kuvio arvoKuvio() {
        int arpa = new Random().nextInt(7);
        if (arpa == 0) {
            return new Nelio(this.leveys / 2, -2);
        } else if (arpa == 1) {
            return new Palkki(this.leveys / 2, -3);
        } else if (arpa == 2) {
            return new zKirjain(this.leveys / 2, -1);
        } else if (arpa == 1) {
            return new sKirjain(this.leveys / 2, -1);
        } else if (arpa == 4) {
            return new jKirjain(this.leveys / 2, -1);
        } else if (arpa ==5) {
            return new lKirjain(this.leveys / 2, -1);
        } else {
            return new tKirjain(this.leveys / 2, -1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.peliKaynnissa) {
            if (!this.putoavaKuvioAlimmallaRivilla(kuvio) && !this.kuvioKiinniJossainRivissa(kuvio)) {
                kuvio.pudotaYhdella();
            } else {
                for (Pala pala : this.kuvio.getPalat()) {
                    this.rivit.lisaaPala(new Pala(pala.getX(), pala.getY(), pala.getVarinNimi()));
                }
                this.tuhoaTaydetRivitJaTarvittaessaPudotaYlempanaOleviaPalojaAlaspain(); //muokattava vielä, jos tippuneet palat täyttää rivin niin se ei tuhoudu
                this.kuvio = this.arvoKuvio();
            }

            if (this.kuvioKiinniYlimmassaRivissa(kuvio)) {
                this.peliKaynnissa = false;
                this.stop();
            }
            paivitettava.paivita();
        }
    }

    //tuhoaa täydet rivit ja kutsuu metodia pudotaYlempanaOlevienRivienPalojaNiinPaljonKuinPystyy.
    public void tuhoaTaydetRivitJaTarvittaessaPudotaYlempanaOleviaPalojaAlaspain() {
        for (int i = 0; i < this.korkeus; i++) {
            if (this.rivit.riviTaysi(i, leveys)) {
                this.rivit.tuhoaRivi(i);               
                this.pudotaYlempanaOlevienRivienPalojaNiinPaljonKuinPystyy(i);               
            }
        }
    }

    //pudottaa annetun rivin yläpuolisia paloja alaspäin niin kauan kunnes ne ovat kiinni jossain (vajaassa) rivissä tai lattiassa. 
    public void pudotaYlempanaOlevienRivienPalojaNiinPaljonKuinPystyy(int riviJonkaYlapuoleltaPudotetaan) {    
        for (int j = riviJonkaYlapuoleltaPudotetaan - 1; j > 0; j--) {
            for (Pala pala : this.rivit.getRivinPalat(j)) {
                    pala.pudotaYhdella();             
            }
        }
    }

    //palauttaa true, jos kuvio on kiinni "lattiassa" ja muuten false.
    public boolean putoavaKuvioAlimmallaRivilla(Kuvio putoavaKuvio) {
        if (putoavaKuvio.getPalaJollaSuurinYKoordinaatti().getY() == this.korkeus - 1) {
            return true;
        }
        return false;
    }

    //palauttaa true jos putoava kuvio on kiinni "katossa" eikä voi enää pudota alaspäin.
    public boolean kuvioKiinniYlimmassaRivissa(Kuvio kuvio) {
        if (kuvio.getPalaJollaPieninYKoordinaatti().getY() <= 0 && this.kuvioKiinniJossainRivissa(kuvio)) {
            return true;
        }
        return false;
    }

    //kuin metodi putoavaKuvioKiinniTietyssaRivissa, mutta käy läpi tetriksen jokaisen rivin.
    public boolean kuvioKiinniJossainRivissa(Kuvio kuvio) {
        for (int i = 0; i < this.korkeus; i++) {
            if (this.kuvioKiinniTietyssaRivissa(i, kuvio)) {
                return true;
            }
        }
        return false;
    }

    //palauttaa true jos putoavan kuvion jokin pala seuraavalla pudotaYhdella metodin kutsulla olisi tämän metodin parametrina annettavan rivin jonkin palan kanssa samassa sijainnissa.
    public boolean kuvioKiinniTietyssaRivissa(int riviNro, Kuvio putoavaKuvio) {
        for (Pala pala : this.rivit.getRivinPalat(riviNro)) {
            for (Pala kuvionPala : putoavaKuvio.getPalat()) {
                if (pala.getY() == kuvionPala.getY() + 1 && pala.getX() == kuvionPala.getX()) {
                    return true;
                }
            }
        }
        return false;
    }

    //tarkistaa voiko palaa siirtää oikealle tai vasemmalle osumatta jossakin rivissä jo olevaan palaan.
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

    public void liikutaKuviotaVasemmalle() {
        if (this.kuvio.getPalaJollaPieninXKoordinaatti().getX() != 0 && this.kuviotaVoiSiirtaaSuuntaanOsumattaRiviin(-1)) {
            this.getKuvio().siirraKuviotaSivuttain(-1);
        }
    }

    public void pudotaKuviotaAlasYhdellaJosVoi(Kuvio kuvio) {
        if (!this.putoavaKuvioAlimmallaRivilla(kuvio) && !this.kuvioKiinniJossainRivissa(kuvio)) {
            this.getKuvio().pudotaYhdella();
        }
    }

    public void liikutaKuviotaOikealle() {
        if (this.kuvio.getPalaJollaSuurinXKoordinaatti().getX() != this.leveys - 1 && this.kuviotaVoiSiirtaaSuuntaanOsumattaRiviin(1)) {
            this.getKuvio().siirraKuviotaSivuttain(1);
        }
    }

    public void kaannaKuviota() {
        if (this.kaanto.putoavaaKuviotaVoiKaantaa(this.kuvio)) {
            this.kuvio.kaanna();
        }
    }

    public void pudotaKuvioNiinAlasKuinVoi(Kuvio kuvio) {
        while (true) {
            if (!this.putoavaKuvioAlimmallaRivilla(kuvio) && !this.kuvioKiinniJossainRivissa(kuvio)) {
                this.pudotaKuviotaAlasYhdellaJosVoi(kuvio);
            } else {
                break;
            }
        }
    }

    public boolean peliKaynnissa() {
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
