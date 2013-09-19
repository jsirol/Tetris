package tetris.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import tetris.domain.Kuvio;
import tetris.domain.Nelio;
import tetris.domain.Pala;
import tetris.domain.Palkki;
import tetris.domain.Rivit;
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

    public Tetris(int leveys, int korkeus) {
        super(200, null);
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.rivit = new Rivit(leveys, korkeus);
        this.kuvio = this.arvoKuvio();
        this.peliKaynnissa = true;

        addActionListener(this);
        setInitialDelay(500);

    }

    //arpoo ja palauttaa uuden kuvion
    public Kuvio arvoKuvio() {
        int arpa = new Random().nextInt(4);
        if (arpa == 0) {
            return new Nelio(this.leveys / 2, -3);
        } else {
            return new Palkki(this.leveys / 2, -3);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.peliKaynnissa) {
            if (!this.putoavaKuvioAlimmallaRivilla(kuvio) && !this.putoavaKuvioKiinniJossainRivissa(kuvio)) {
                kuvio.pudotaYhdella();
            } else {
                for (Pala pala : this.kuvio.getPalat()) {
                    this.rivit.lisaaPala(new Pala(pala.getX(), pala.getY(), pala.getVarinNimi()));
                }
                this.kuvio = this.arvoKuvio();
            }
            this.tuhoaTaydetRivitJaTarvittaessaPudotaYlempanaOleviaPalojaAlaspain();
            if (this.putoavaKuvioKiinniYlimmassaRivissa(kuvio)) {
                this.peliKaynnissa = false;
                this.stop();
            }
            paivitettava.paivita();
        }
    }

    //tuhoaa täydet rivit ja kutsuu metodia pudotaYlempanaOlevienRivienPalojaNiinPaljonKuinPystyy
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
        for (int j = riviJonkaYlapuoleltaPudotetaan + 1; j > 0; j--) {
            for (Pala pala : this.rivit.getRivinPalat(j)) {
                Kuvio pudotettavaPala = new Kuvio();
                pudotettavaPala.lisaaPala(pala);
                while (!this.putoavaKuvioAlimmallaRivilla(pudotettavaPala) && !this.putoavaKuvioKiinniJossainRivissa(pudotettavaPala)) {
                    pala.pudotaYhdella();
                }
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
    public boolean putoavaKuvioKiinniYlimmassaRivissa(Kuvio putoavaKuvio) {
        if (putoavaKuvio.getPalaJollaPieninYKoordinaatti().getY() <= 0 && this.putoavaKuvioKiinniJossainRivissa(putoavaKuvio)) {
            return true;
        }
        return false;
    }

    //kuin metodi putoavaKuvioKiinniTietyssaRivissa, mutta käy läpi tetriksen jokaisen rivin.
    public boolean putoavaKuvioKiinniJossainRivissa(Kuvio putoavaKuvio) {
        for (int i = 0; i < this.korkeus; i++) {
            if (this.putoavaKuvioKiinniTietyssaRivissa(i, putoavaKuvio)) {
                return true;
            }
        }
        return false;
    }

    //palauttaa true jos putoavan kuvion jokin pala seuraavalla pudotaYhdella metodin kutsulla olisi tämän metodin parametrina annettavan rivin jonkin palan kanssa samassa sijainnissa.
    public boolean putoavaKuvioKiinniTietyssaRivissa(int riviNro, Kuvio putoavaKuvio) {
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

    public void pudotaKuviotaAlasYhdellaJosVoi() {
        if (!this.putoavaKuvioAlimmallaRivilla(this.getKuvio()) && !this.putoavaKuvioKiinniJossainRivissa(this.getKuvio())) {
            this.getKuvio().pudotaYhdella();
        }
    }

    public void liikutaKuviotaOikealle() {
        if (this.kuvio.getPalaJollaSuurinXKoordinaatti().getX() != this.leveys - 1 && this.kuviotaVoiSiirtaaSuuntaanOsumattaRiviin(1)) {
            this.getKuvio().siirraKuviotaSivuttain(1);
        }
    }

    public void kaannaKuviota() {
        if (this.putoavaaKuviotaVoiKaantaa(this.kuvio)) {
            this.getKuvio().kaanna();
        }
    }

    public void pudotaPutoavaKuvioNiinAlasKuinVoi() {
        while (true) {
            if (!this.putoavaKuvioAlimmallaRivilla(this.getKuvio()) && !this.putoavaKuvioKiinniJossainRivissa(this.getKuvio())) {
                this.getKuvio().pudotaYhdella();
            } else {
                break;
            }
        }
    }
    
    

    //päivittyy
    public boolean putoavaaKuviotaVoiKaantaa(Kuvio kuvio) {     
        if(kuvio.getClass()==Palkki.class) {
            return this.palkkiaVoiKaantaa((Palkki) kuvio);
        }
        return false;
    }
    
    //tarkistaa voiko palkkia kääntää. Palkkia v
    public boolean palkkiaVoiKaantaa(Palkki palkki) {
        int x=palkki.getRotaatioPisteenaOlevaPala().getX();
        int y=palkki.getRotaatioPisteenaOlevaPala().getY();    
        if(x-2<0 || x+2>this.leveys-1) {
            return false;
        }
        for(int i=1;i<3;i++) {        
            for(int j=1;j<3;j++) {
                if(this.rivit.onkoRivissaTietyssaKoordinaatissaJoPala(y-i, x-j, y-i) || this.rivit.onkoRivissaTietyssaKoordinaatissaJoPala(y+i, x+j, y+i)) {
                    return false;
                }
            }
        }
        return true;      
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
