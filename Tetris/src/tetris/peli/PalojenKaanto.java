package tetris.peli;

import tetris.domain.Kuvio;
import tetris.domain.Palkki;
import tetris.domain.Rivit;
import tetris.domain.Rivit;

import tetris.domain.sKirjain;
import tetris.domain.zKirjain;

/**
 *
 * @author Johannes
 */
public class PalojenKaanto {

    private Rivit rivit;
    private int leveys;
    private int korkeus;

    public PalojenKaanto(Rivit rivit, int leveys, int korkeus) {
        this.rivit = rivit;
        this.leveys = leveys;
        this.korkeus = korkeus;
    }

    public boolean palkkiaVoiKaantaa(Palkki palkki) {
        int x = palkki.getRotaatioPisteenaOlevaPala().getX();
        int y = palkki.getRotaatioPisteenaOlevaPala().getY();
        if (x - 2 < 0 || x + 2 > this.leveys - 1 || (palkki.onVaakaAsennossa() && palkki.getRotaatioPisteenaOlevaPala().getY() + 2 >= this.korkeus - 1)) {
            return false;
        }
        for (int i = 1; i < 3; i++) {
            for (int j = 1; j < 3; j++) {
                if (this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - j, y - i) || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + j, y + i)) {
                    return false;
                }
            }
        }
        return true;
    }
    
        public boolean zKirjaintaVoiKaantaa(zKirjain kirjain) {
        int x = this.getRotaatioPisteenaOlevanPalanXKoordinaatti(kirjain);
        int y = this.getRotaatioPisteenaOlevanPalanYKoordinaatti(kirjain);
        if ((!kirjain.onVaakaAsennossa() && kirjain.getPalaJollaSuurinXKoordinaatti().getX() + 1 > this.leveys - 1) || (kirjain.onVaakaAsennossa() && kirjain.getRotaatioPisteenaOlevaPala().getY() >= this.korkeus - 1)) {
            return false;
        } 
        if(this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y) || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y + 1) || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y - 1) ||
             this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y) || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y + 1)) {
            return false;
        }
        return true;
    }

    public boolean sKirjaintaVoiKaantaa(sKirjain kirjain) {
        int x = this.getRotaatioPisteenaOlevanPalanXKoordinaatti(kirjain);
        int y = this.getRotaatioPisteenaOlevanPalanYKoordinaatti(kirjain);
        if ((!kirjain.onVaakaAsennossa() && kirjain.getPalaJollaPieninXKoordinaatti().getX() - 1 < 0) || (kirjain.onVaakaAsennossa() && kirjain.getRotaatioPisteenaOlevaPala().getY() >= this.korkeus - 1)) {
            return false;
        }
        if(this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y+1) || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y) || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y + 1) ||
             this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y) || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y - 1)) {
            return false;
        }
        return true;
    }
    

    public boolean putoavaaKuviotaVoiKaantaa(Kuvio kuvio) {
        if (kuvio.getClass() == Palkki.class) {
            return this.palkkiaVoiKaantaa(
                    (Palkki) kuvio);
        } else if (kuvio.getClass() == sKirjain.class) {
            return this.sKirjaintaVoiKaantaa(
                    (sKirjain) kuvio);
        } else if (kuvio.getClass() == zKirjain.class) {
            return this.zKirjaintaVoiKaantaa(
                    (zKirjain) kuvio);
        }
        return true;
    }


    public int getRotaatioPisteenaOlevanPalanXKoordinaatti(Kuvio kuvio) {
        return kuvio.getRotaatioPisteenaOlevaPala().getX();
    }

    public int getRotaatioPisteenaOlevanPalanYKoordinaatti(Kuvio kuvio) {
        return kuvio.getRotaatioPisteenaOlevaPala().getY();
    }
}
