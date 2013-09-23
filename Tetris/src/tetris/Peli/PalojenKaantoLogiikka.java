package tetris.Peli;

import tetris.domain.Kuvio;
import tetris.domain.LjaJmuotti;
import tetris.domain.Palkki;
import tetris.domain.Rivit;
import tetris.domain.jKirjain;
import tetris.domain.lKirjain;
import tetris.domain.sKirjain;
import tetris.domain.tKirjain;
import tetris.domain.zKirjain;

/**
 *
 * @author Johannes
 */
public class PalojenKaantoLogiikka {

    private Rivit rivit;
    private int leveys;
    private int korkeus;

    public PalojenKaantoLogiikka(Rivit rivit, int leveys, int korkeus) {
        this.rivit = rivit;
        this.leveys = leveys;
        this.korkeus = korkeus;
    }

    public boolean putoavaaKuviotaVoiKaantaa(Kuvio kuvio) {
        if (kuvio.getClass() == Palkki.class) {
            return this.palkkiaVoiKaantaa((Palkki) kuvio);
        } else if (kuvio.getClass() == sKirjain.class) {
            return this.sKirjaintaVoiKaantaa((sKirjain) kuvio);
        } else if (kuvio.getClass() == zKirjain.class) {
            return this.zKirjaintaVoiKaantaa((zKirjain) kuvio);
        } else if (kuvio.getClass() == lKirjain.class || kuvio.getClass() == jKirjain.class) {
            return this.LtaiJkirjaintaVoiKaantaa((LjaJmuotti) kuvio);
        } else if (kuvio.getClass() == tKirjain.class) {
            return this.tKirjaintaVoiKaantaa((tKirjain) kuvio);
        }
        return true;
    }

    public boolean tKirjaintaVoiKaantaa(tKirjain kirjain) {
        if (this.tKirjaimenKaantoAiheuttaaSeinaanTaiLattiaanOsumisen(kirjain) || this.tKirjaimenKaantoOsuuRiviin(kirjain)) {
            return false;
        }
        return true;
    }

    public boolean tKirjaimenKaantoAiheuttaaSeinaanTaiLattiaanOsumisen(tKirjain kirjain) {
        if ((kirjain.getAsento() == 3 && kirjain.getRotaatioPisteenaOlevaPala().getX() - 1 < 0) || (kirjain.getAsento() == 1 && kirjain.getRotaatioPisteenaOlevaPala().getX() + 1 > this.leveys - 1)) {
            return true;
        }
        if (kirjain.getAsento() == 0 && kirjain.getRotaatioPisteenaOlevaPala().getY() + 1 > this.korkeus - 1) {
            return true;
        }
        return false;
    }

    public boolean tKirjaimenKaantoOsuuRiviin(tKirjain kirjain) {
        int x = kirjain.getRotaatioPisteenaOlevaPala().getX();
        int y = kirjain.getRotaatioPisteenaOlevaPala().getY();
        if (this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y) || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y)
                || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x, y - 1) || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x, y + 1)) {
            return true;
        }
        if ((kirjain.getAsento() == 0 && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y + 1))
                || (kirjain.getAsento() == 1 && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y + 1))
                || (kirjain.getAsento() == 2 && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y - 1))
                || (kirjain.getAsento() == 3 && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y + 1))) {
            return true;
        }
        return false;
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
        if ((!kirjain.onVaakaAsennossa() && kirjain.getPalaJollaSuurinXKoordinaatti().getX() + 1 > this.leveys - 1)
                || (kirjain.onVaakaAsennossa() && kirjain.getRotaatioPisteenaOlevaPala().getY() >= this.korkeus - 1)) {
            return false;
        }
        if (this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y) || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y + 1)
                || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y - 1)
                || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y) || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y + 1)) {
            return false;
        }
        return true;
    }

    public boolean sKirjaintaVoiKaantaa(sKirjain kirjain) {
        int x = this.getRotaatioPisteenaOlevanPalanXKoordinaatti(kirjain);
        int y = this.getRotaatioPisteenaOlevanPalanYKoordinaatti(kirjain);
        if ((!kirjain.onVaakaAsennossa() && kirjain.getPalaJollaPieninXKoordinaatti().getX() - 1 < 0)
                || (kirjain.onVaakaAsennossa() && kirjain.getRotaatioPisteenaOlevaPala().getY() >= this.korkeus - 1)) {
            return false;
        }
        if (this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y + 1) || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y)
                || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y + 1)
                || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y) || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y - 1)) {
            return false;
        }
        return true;
    }

    //kertoo voiko lkirjainta kääntää
    public boolean LtaiJkirjaintaVoiKaantaa(LjaJmuotti kirjain) {
        int x = this.getRotaatioPisteenaOlevanPalanXKoordinaatti(kirjain);
        int y = this.getRotaatioPisteenaOlevanPalanYKoordinaatti(kirjain);
        if (kirjain.getAsento() == 0 || kirjain.getAsento() == 2) {
            return this.LtaiJkirjaintaVoiKaantaaPystystaVaakaan(kirjain);
        } else if (kirjain.getAsento() == 1 || kirjain.getAsento() == 3) {
            return this.LtaiJkirjaintaVoiKaantaaVaastaPystyyn(kirjain);
        }
        return false;
    }

    //kertoo voiko lKirjainta kääntää pystyasennosta vaakaan (pystyasennot= asennot 0 ja 2)
    public boolean LtaiJkirjaintaVoiKaantaaPystystaVaakaan(LjaJmuotti kirjain) {
        int x = this.getRotaatioPisteenaOlevanPalanXKoordinaatti(kirjain);
        int y = this.getRotaatioPisteenaOlevanPalanYKoordinaatti(kirjain);
        if (this.LtaiJkirjaimenKaantoOttaaKiinniLattiaan(kirjain) || this.LtaiJkirjaimenKaantoOttaaKiinniSeiniin(kirjain)
                || this.LtaiJkirjaimenKaantoAsennosta0OttaaRiveihin(kirjain) || this.LtaiJkirjaimenKaantoAsennosta2OttaaRiveihin(kirjain)) {
            return false;
        }
        return true;
    }

    //kertoo voika lkirjainta kääntää vaasta pystyasentoon (vaaka-asennot= 1 ja 3)
    public boolean LtaiJkirjaintaVoiKaantaaVaastaPystyyn(LjaJmuotti kirjain) {
        int x = this.getRotaatioPisteenaOlevanPalanXKoordinaatti(kirjain);
        int y = this.getRotaatioPisteenaOlevanPalanYKoordinaatti(kirjain);
        if (this.LtaiJkirjaimenKaantoOttaaKiinniLattiaan(kirjain)
                || this.LtaiJkirjaimenKaantoAsennosta1OttaaRiveihin(kirjain) || this.LtaiJkirjaimenKaantoAsennosta3OttaaRiveihin(kirjain)) {
            return false;
        }
        return true;
    }

    //kertoo tapahtuuko L tai J kirjainta kääntäessä osuma seiniin
    public boolean LtaiJkirjaimenKaantoOttaaKiinniSeiniin(LjaJmuotti kirjain) {
        if (kirjain.getClass() == jKirjain.class && kirjain.getRotaatioPisteenaOlevaPala().getX() + 1 > this.leveys - 1) {
            return true;
        }
        if ((kirjain.getAsento() == 2 && kirjain.getRotaatioPisteenaOlevaPala().getX() + 2 > this.leveys - 1)
                || (kirjain.getAsento() == 0 && kirjain.getRotaatioPisteenaOlevaPala().getX() - 2 < 0)) {
            return true;
        }
        return false;
    }

    //kertoo tapahtuuko L tai J kirjainta kääntäessä osuma lattiaan
    public boolean LtaiJkirjaimenKaantoOttaaKiinniLattiaan(LjaJmuotti kirjain) {
        if ((kirjain.getAsento() == 1 && (kirjain.getRotaatioPisteenaOlevaPala().getY() + 2 > this.korkeus - 1))
                || (kirjain.getClass() == jKirjain.class && kirjain.getAsento() == 0 && kirjain.getRotaatioPisteenaOlevaPala().getY() + 1 > this.korkeus - 1)) {
            return true;
        }
        return false;
    }

    public boolean LtaiJkirjaimenKaantoAsennosta0OttaaRiveihin(LjaJmuotti kirjain) {
        int x = this.getRotaatioPisteenaOlevanPalanXKoordinaatti(kirjain);
        int y = this.getRotaatioPisteenaOlevanPalanYKoordinaatti(kirjain);
        for (int i = 0; i < 3; i++) {
            if (this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y - i)
                    || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 2, y - i)) {
                return true;
            }
        }
        if (kirjain.getClass() == lKirjain.class && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y - 1)) {
            return true;
        }
        if (kirjain.getClass() == jKirjain.class && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y + 1)
                || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x, y + 1)) {
            return true;
        }
        return false;
    }

    public boolean LtaiJkirjaimenKaantoAsennosta1OttaaRiveihin(LjaJmuotti kirjain) {
        int x = this.getRotaatioPisteenaOlevanPalanXKoordinaatti(kirjain);
        int y = this.getRotaatioPisteenaOlevanPalanYKoordinaatti(kirjain);
        for (int i = 0; i < 3; i++) {
            if (this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - i, y + 1)
                    || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - i, y + 2)) {
                return true;
            }
        }
        if (kirjain.getClass() == lKirjain.class && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y)) {
            return true;
        }
        if (kirjain.getClass() == jKirjain.class && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y + 1)
                || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y)) {
            return true;
        }
        return false;
    }

    public boolean LtaiJkirjaimenKaantoAsennosta2OttaaRiveihin(LjaJmuotti kirjain) {
        int x = this.getRotaatioPisteenaOlevanPalanXKoordinaatti(kirjain);
        int y = this.getRotaatioPisteenaOlevanPalanYKoordinaatti(kirjain);
        for (int i = 0; i < 3; i++) {
            if (this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y + i)
                    || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 2, y + i)) {
                return true;
            }
        }
        if (kirjain.getClass() == lKirjain.class && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y + 1)) {
            return true;
        }
        if (kirjain.getClass() == jKirjain.class && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x, y - 1)
                || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y - 1)) {
            return true;
        }
        return false;
    }

    public boolean LtaiJkirjaimenKaantoAsennosta3OttaaRiveihin(LjaJmuotti kirjain) {
        int x = this.getRotaatioPisteenaOlevanPalanXKoordinaatti(kirjain);
        int y = this.getRotaatioPisteenaOlevanPalanYKoordinaatti(kirjain);
        for (int i = 0; i < 3; i++) {
            if (this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + i, y - 1)
                    || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + i, y - 2)) {
                return true;
            }
        }
        if (kirjain.getClass() == lKirjain.class && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y)) {
            return true;
        }
        if (kirjain.getClass() == jKirjain.class && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y - 1)
                || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y)) {
            return true;
        }
        return false;
    }

    public int getRotaatioPisteenaOlevanPalanXKoordinaatti(Kuvio kuvio) {
        return kuvio.getRotaatioPisteenaOlevaPala().getX();
    }

    public int getRotaatioPisteenaOlevanPalanYKoordinaatti(Kuvio kuvio) {
        return kuvio.getRotaatioPisteenaOlevaPala().getY();
    }
}
