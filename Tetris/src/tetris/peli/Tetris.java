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
    private Kuvio arvoKuvio() {
        int arpa = new Random().nextInt(4);
        if (arpa == 0) {
            return new Nelio(this.leveys / 2, 0);
        } else {
            return new Palkki(this.leveys / 2, 0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.peliKaynnissa) {
            if (!this.PutoavaKuvioKiinniLattiassa(kuvio) && !this.putoavaKuvioKiinniJossainRivissa(kuvio)) {
                kuvio.pudotaYhdella();
            } else {
                for (Pala pala : this.kuvio.getPalat()) {
                    this.rivit.lisaaPala(new Pala(pala.getX(), pala.getY(), pala.getVarinNimi()));
                }
                this.kuvio = this.arvoKuvio();
            }
            this.tuhoaTaydetRivitJaTarvittaessaPudotaYlempanaOleviaPalojaAlaspain();

            if (this.PutoavaKuvioKiinniYlimmassaRivissa(kuvio)) {
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
                while (!this.PutoavaKuvioKiinniLattiassa(pudotettavaPala) && !this.putoavaKuvioKiinniJossainRivissa(pudotettavaPala)) {
                    pala.pudotaYhdella();
                }
            }
        }
    }

    //palauttaa true, jos kuvio on kiinni "lattiassa" ja muuten false.
    public boolean PutoavaKuvioAlimmallaRivilla(Kuvio putoavaKuvio) {
        if (putoavaKuvio.getPalaJollaSuurinYKoordinaatti().getY() == this.korkeus - 1) {
            return true;
        }
        return false;
    }

    //palauttaa true jos putoava kuvio on kiinni "katossa" eikä voi enää pudota alaspäin.
    public boolean PutoavaKuvioKiinniYlimmassaRivissa(Kuvio putoavaKuvio) {
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

    //palauttaa true jos putoava kuvio on kiinni "lattiassa", muuten palauttaa false.
    public boolean PutoavaKuvioKiinniLattiassa(Kuvio putoavaKuvio) {
        if (this.PutoavaKuvioAlimmallaRivilla(putoavaKuvio)) {
            return true;
        }
        return false;
    }

    //päivittyy
    public boolean PutoavaaKuviotaVoiKaantaa() {
        //to do
        return true;
    }

    
    //päivittyy
    public boolean KuviotaVoiSiirtaaVasemmalle() {
        if (this.kuvio.getPalaJollaPieninXKoordinaatti().getX() == 0) {
            return false;
        }
        return true;
    }

    //päivittyy
    public boolean KuviotaVoiSiirtaaOikealle() {
        if (this.kuvio.getPalaJollaSuurinXKoordinaatti().getX() == this.leveys - 1) {
            return false;
        }
        return true;
    }

    public boolean peliKaynnissa() {
        return this.peliKaynnissa;
    }

    public Kuvio getKuvio() {
        return this.kuvio;
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

    public ArrayList<Pala> getRivit() {
        return this.rivit.getPalat();
    }

    public void setPelikaynnissa(boolean totuusarvo) {
        this.peliKaynnissa = totuusarvo;
    }
}
