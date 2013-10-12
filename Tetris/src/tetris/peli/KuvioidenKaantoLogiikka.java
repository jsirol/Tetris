package tetris.peli;

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
 * Luokka määrittelee kullekin kuviolle säännöt milloin sitä saa kääntää.
 * Säännöt ottavat huomioon kuvion vieressä mahdollisesti olevat palat ja onko
 * kuvio kiinni pelialueen rajoissa.
 *
 * @author Johannes
 */
public class KuvioidenKaantoLogiikka {

    /**
     * Sisältää tiedon pelialueella olevista paloista poislukien pala jota
     * pelaaja ohjaa (tippuva pala).
     */
    private Rivit rivit;
    /**
     * Pelialueen leveys.
     */
    private int leveys;
    /**
     * pelialueen korkeus.
     */
    private int korkeus;

    public KuvioidenKaantoLogiikka(Rivit rivit, int leveys, int korkeus) {
        this.rivit = rivit;
        this.leveys = leveys;
        this.korkeus = korkeus;
    }

    /**
     * Metodi palauttaa totuusarvona tiedon voiko sille parametrina annettua
     * kuviota kääntää. Huomautus: Neliötä voi aina kääntää (oikeammin: se ei
     * käänny).
     *
     * @param kuvio käännettävä kuvio
     *
     * @return totuusarvo sille, voiko kuviota kääntää
     *
     * @see
     * tetris.peli.KuvioidenKaantoLogiikka#LtaiJkirjaintaVoiKaantaa(tetris.domain.LjaJmuotti)
     * @see
     * tetris.peli.KuvioidenKaantoLogiikka#palkkiaVoiKaantaa(tetris.domain.Palkki)
     * @see
     * tetris.peli.KuvioidenKaantoLogiikka#sKirjaintaVoiKaantaa(tetris.domain.sKirjain)
     * @see
     * tetris.peli.KuvioidenKaantoLogiikka#tKirjaintaVoiKaantaa(tetris.domain.tKirjain)
     * @see
     * tetris.peli.KuvioidenKaantoLogiikka#zKirjaintaVoiKaantaa(tetris.domain.zKirjain)
     */
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

    /**
     * Metodi palauttaa totuusarvon sille, voiko parametrina annettua
     * T-kirjainta kääntää.
     *
     * @param kirjain käännettävä Z-kirjain
     *
     * @return totuusarvo sille, voiko z-kirjainta kääntää
     *
     * @see
     * tetris.peli.KuvioidenKaantoLogiikka#tKirjaimenKaantoAiheuttaaSeinaanTaiLattiaanOsumisen(tetris.domain.tKirjain)
     * @see
     * tetris.peli.KuvioidenKaantoLogiikka#tKirjaimenKaantoOsuuRiviin(tetris.domain.tKirjain)
     */
    public boolean tKirjaintaVoiKaantaa(tKirjain kirjain) {
        if (this.tKirjaimenKaantoAiheuttaaSeinaanTaiLattiaanOsumisen(kirjain) || this.tKirjaimenKaantoOsuuRiviin(kirjain)) {
            return false;
        }
        return true;
    }

    /**
     * Metodi palauttaa totuusarvon sille, osuuko paramatrina annettu T-kirjain
     * pelialueen reunoihin käännettäessä.
     *
     * @param kirjain käännettävä T-kirjain.
     *
     * @return totuusarvo sille, osuuko T-kirjain pelialueen reunoihin
     * käännettäessä
     *
     * @see tetris.domain.tKirjain#getRotaatioPisteenaOlevaPala()
     * @see tetris.domain.tKirjain#getAsento()
     */
    public boolean tKirjaimenKaantoAiheuttaaSeinaanTaiLattiaanOsumisen(tKirjain kirjain) {
        if ((kirjain.getAsento() == 3 && kirjain.getRotaatioPisteenaOlevaPala().getX() - 1 < 0) || (kirjain.getAsento() == 1 && kirjain.getRotaatioPisteenaOlevaPala().getX() + 1 > this.leveys - 1)) {
            return true;
        }
        if (kirjain.getAsento() == 0 && kirjain.getRotaatioPisteenaOlevaPala().getY() + 1 > this.korkeus - 1) {
            return true;
        }
        return false;
    }

    /**
     * Metodi palauttaa totuusarvon sille, osuuko parametrina annettava
     * T-kirjain pelin riveihin käännettäessä.
     *
     * @param kirjain käännettävä kirjain
     *
     * @return totuusarvo sille, osuuko T-kirjain johonkin riviin sitä
     * käännettäessä
     *
     * @see tetris.domain.tKirjain#getRotaatioPisteenaOlevaPala()
     * @see tetris.domain.tKirjain#getAsento()
     * @see tetris.domain.Kuvio#onkoKuviossaTietyssaKoordinaatissaJoPala(int,
     * int)
     */
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

    /**
     * Metodi palauttaa totuusarvon sille, voiko parametrina annettua palkkia
     * kääntää.
     *
     * @param palkki käännettävä palkki
     *
     * @return totuusarvo sille, voiko palkkia kääntää
     *
     * @see
     * tetris.peli.KuvioidenKaantoLogiikka#palkkiOsuuRiveihinKaannettaessa(tetris.domain.Palkki)
     * @see
     * tetris.peli.KuvioidenKaantoLogiikka#palkkiOsuuSeinaanTaiLattiaanKaannettaessa(tetris.domain.Palkki)
     *
     */
    public boolean palkkiaVoiKaantaa(Palkki palkki) {
        if (this.palkkiOsuuRiveihinKaannettaessa(palkki) || this.palkkiOsuuSeinaanTaiLattiaanKaannettaessa(palkki)) {
            return false;
        }
        return true;
    }

    /**
     * Metodi palauttaa paluuarvonaan totuusarvon sille, osuuko parametrina
     * annettava palkki käännöksessä pelialueen seiniin tai riveihin.
     *
     * @param palkki palkki jota oltaisiin kääntämässä
     * @return totuusarvo, osuuko palkki seinään tai lattiaan käännettäessä
     *
     * @see tetris.domain.Palkki#onVaakaAsennossa()
     */
    public boolean palkkiOsuuSeinaanTaiLattiaanKaannettaessa(Palkki palkki) {
        int x = palkki.getRotaatioPisteenaOlevaPala().getX();
        int y = palkki.getRotaatioPisteenaOlevaPala().getY();
        if (x - 1 < 0 || x + 2 > this.leveys - 1 || (palkki.onVaakaAsennossa() && palkki.getRotaatioPisteenaOlevaPala().getY() + 2 > this.korkeus - 1)) {
            return true;
        }
        return false;
    }

    /**
     * Metodi palauttaa paluuarvonaan totuusarvon sille, osuuko parametrina
     * sille annettava palkki käännettäessä johonkin pelialueen riviin.
     *
     * @param palkki käännettävä palkki
     * @return totuusarvo, osuuko palkki riveihin
     *
     * @see tetris.domain.Kuvio#onkoKuviossaTietyssaKoordinaatissaJoPala(int,
     * int)
     */
    public boolean palkkiOsuuRiveihinKaannettaessa(Palkki palkki) {
        int x = palkki.getRotaatioPisteenaOlevaPala().getX();
        int y = palkki.getRotaatioPisteenaOlevaPala().getY();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + i, y + j)) {
                    return true;
                }
            }
        }
        if (this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x, y + 1)
                || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y + 1)
                || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y)) {
            return false;
        }
        return false;
    }

    /**
     * Metodi palauttaa totuusarvon sille, voiko parametrina annettavaa
     * Z-kirjainta kääntää.
     *
     * @param kirjain käännettävä Z-kirjain
     *
     * @return totuusarvo sille, voiko Z-kirjainta kääntää
     *
     * @see tetris.domain.zKirjain#getRotaatioPisteenaOlevaPala()
     * @see tetris.domain.zKirjain#onVaakaAsennossa()
     * @see tetris.domain.zKirjain#getPalaJollaSuurinXKoordinaatti()
     * @see tetris.domain.Kuvio#onkoKuviossaTietyssaKoordinaatissaJoPala(int,
     * int)
     */
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

    /**
     * Metodi palauttaa totuusarvon sille, voiko parametrina annettavaa
     * S-kirjainta kääntää.
     *
     * @param kirjain käännettävä S-kirjain
     *
     * @return totuusarvo sille, voiko S-kirjainta kääntää
     *
     * @see tetris.domain.sKirjain#getRotaatioPisteenaOlevaPala()
     * @see tetris.domain.sKirjain#onVaakaAsennossa()
     * @see tetris.domain.sKirjain#getPalaJollaPieninXKoordinaatti()
     * @see tetris.domain.Kuvio#onkoKuviossaTietyssaKoordinaatissaJoPala(int,
     * int)
     */
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

    /**
     * Metodi palauttaa totuusarvon sille, voiko paramentrina annettavaa
     * LjaJmuotti luokan edustajaa (L- tai J-kirjainta) kääntää.
     *
     * @param kirjain käännettävä L- tai J-kirjain
     *
     * @return totuusarvo sille, voiko L- tai J-kirjainta kääntää
     *
     * @see tetris.domain.LjaJmuotti#getRotaatioPisteenaOlevaPala()
     * @see tetris.domain.LjaJmuotti#getAsento()
     * @see
     * tetris.peli.KuvioidenKaantoLogiikka#LtaiJkirjaintaVoiKaantaaPystystaVaakaan(tetris.domain.LjaJmuotti)
     * @see
     * tetris.peli.KuvioidenKaantoLogiikka#LtaiJkirjaintaVoiKaantaaVaastaPystyyn(tetris.domain.LjaJmuotti)
     */
    public boolean LtaiJkirjaintaVoiKaantaa(LjaJmuotti kirjain) {
        if (kirjain.getAsento() == 0 || kirjain.getAsento() == 2) {
            return this.LtaiJkirjaintaVoiKaantaaPystystaVaakaan(kirjain);
        } else if (kirjain.getAsento() == 1 || kirjain.getAsento() == 3) {
            return this.LtaiJkirjaintaVoiKaantaaVaastaPystyyn(kirjain);
        }
        return false;
    }

    /**
     * Metodi palauttaa totuusarvon sille, voiko parametrina annettavaa
     * LjaJmuotti luokan edustajaa (L- tai J-kirjainta) kääntää pystyasennosta
     * (asennot 0 ja 2) vaaka-asentoon (asennot 1 ja 3).
     *
     * @param kirjain käännettävä L- tai J-kirjain
     *
     * @return totuusarvo sille, voiko L- tai J-kirjainta kääntää pystyasennosta
     * vaaka-asentoon
     *
     * @see tetris.domain.LjaJmuotti#getRotaatioPisteenaOlevaPala()
     * @see
     * tetris.peli.KuvioidenKaantoLogiikka#LtaiJkirjaimenKaantoOttaaKiinniLattiaan(tetris.domain.LjaJmuotti)
     * @see
     * tetris.peli.KuvioidenKaantoLogiikka#LtaiJkirjaimenKaantoOttaaKiinniSeiniin(tetris.domain.LjaJmuotti)
     * @see
     * tetris.peli.KuvioidenKaantoLogiikka#LtaiJkirjaimenKaantoAsennosta0OttaaRiveihin(tetris.domain.LjaJmuotti)
     * @see
     * tetris.peli.KuvioidenKaantoLogiikka#LtaiJkirjaimenKaantoAsennosta2OttaaRiveihin(tetris.domain.LjaJmuotti)
     */
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

    /**
     * Metodi palauttaa totuusarvon sille, voiko parametrina annettavaa
     * LjaJmuotti luokan edustajaa (L- tai J-kirjainta) kääntää vaaka-asennosta
     * pystyasentoon.
     *
     * @param kirjain käännettävä L- tai J-kirjain
     *
     * @return totuusarvo sille, voiko L- tai J-kirjainta kääntää
     * vaaka-asennosta pystyasentoon
     *
     * @see tetris.domain.LjaJmuotti#getRotaatioPisteenaOlevaPala()
     * @see
     * tetris.peli.KuvioidenKaantoLogiikka#LtaiJkirjaimenKaantoOttaaKiinniLattiaan(tetris.domain.LjaJmuotti)
     * @see
     * tetris.peli.KuvioidenKaantoLogiikka#LtaiJkirjaimenKaantoAsennosta1OttaaRiveihin(tetris.domain.LjaJmuotti)
     * @see
     * tetris.peli.KuvioidenKaantoLogiikka#LtaiJkirjaimenKaantoAsennosta3OttaaRiveihin(tetris.domain.LjaJmuotti)
     * @see
     * tetris.peli.KuvioidenKaantoLogiikka#LtaiJkirjaimenKaantoOttaaKiinniSeiniin(tetris.domain.LjaJmuotti)
     */
    public boolean LtaiJkirjaintaVoiKaantaaVaastaPystyyn(LjaJmuotti kirjain) {
        int x = this.getRotaatioPisteenaOlevanPalanXKoordinaatti(kirjain);
        int y = this.getRotaatioPisteenaOlevanPalanYKoordinaatti(kirjain);
        if (this.LtaiJkirjaimenKaantoOttaaKiinniLattiaan(kirjain)
                || this.LtaiJkirjaimenKaantoAsennosta1OttaaRiveihin(kirjain)
                || this.LtaiJkirjaimenKaantoAsennosta3OttaaRiveihin(kirjain)
                || this.LtaiJkirjaimenKaantoOttaaKiinniSeiniin(kirjain)) {
            return false;
        }
        return true;
    }

    /**
     * Metodi palauttaa totuusarvon sille, osuuko parametrina annettava
     * LjaJmuotti luokan edustaja (L- tai J-kirjain) seinään käännettäessä.
     *
     * @param kirjain käännettävä L- tai J-kirjain
     *
     * @return totuusarvo sille, osuuko L- tai J-kirjain käännettäessä seinään
     *
     * @see tetris.domain.LjaJmuotti#getRotaatioPisteenaOlevaPala()
     * @see tetris.domain.LjaJmuotti#getAsento()
     */
    public boolean LtaiJkirjaimenKaantoOttaaKiinniSeiniin(LjaJmuotti kirjain) {
        if (kirjain.getClass() == jKirjain.class
                && ((kirjain.getRotaatioPisteenaOlevaPala().getX() + 1 > this.leveys - 1)
                || (kirjain.getAsento() == 3 && kirjain.getRotaatioPisteenaOlevaPala().getX() - 1 < 0))) {

            return true;
        }
        if ((kirjain.getAsento() == 2 && kirjain.getRotaatioPisteenaOlevaPala().getX() + 2 > this.leveys - 1)
                || (kirjain.getAsento() == 0 && kirjain.getRotaatioPisteenaOlevaPala().getX() - 2 < 0)) {
            return true;
        }
        return false;
    }

    /**
     * Metodi palauttaa totuusarvon sille, osuuko parametrina annettava
     * LjaJmuotti luokan edustaja (L- tai J-kirjain) lattiaan käännettäessä.
     *
     * @param kirjain käännettävä L- tai J-kirjain
     *
     * @return totuusarvo sille, osuuko L- tai J-kirjain käännettäessä lattiaan
     *
     * @see tetris.domain.LjaJmuotti#getRotaatioPisteenaOlevaPala()
     * @see tetris.domain.LjaJmuotti#getAsento()
     */
    public boolean LtaiJkirjaimenKaantoOttaaKiinniLattiaan(LjaJmuotti kirjain) {
        if ((kirjain.getAsento() == 1 && (kirjain.getRotaatioPisteenaOlevaPala().getY() + 2 > this.korkeus - 1))
                || (kirjain.getClass() == jKirjain.class
                && kirjain.getAsento() == 0 && kirjain.getRotaatioPisteenaOlevaPala().getY() + 1 > this.korkeus - 1)) {

            return true;
        }
        return false;
    }

    /**
     * Metodi palauttaa totuusarvon sille, osuuko parametrina annettava
     * LjaJmuotti luokan edustaja (L- tai J-kirjain) johonkin riviin
     * käännettäessä kuviota asennosta 0 asentoon 1.
     *
     * @param kirjain käännettävä L- tai J-kirjain
     *
     * @return totuusarvo sille, osuuko L- tai J-kirjain riveihin käännettäessä
     * kuviota asennosta 0 asentoon 1
     *
     * @see tetris.domain.LjaJmuotti#getRotaatioPisteenaOlevaPala()
     * @see tetris.domain.Kuvio#onkoKuviossaTietyssaKoordinaatissaJoPala(int,
     * int)
     */
    public boolean LtaiJkirjaimenKaantoAsennosta0OttaaRiveihin(LjaJmuotti kirjain) {
        int x = this.getRotaatioPisteenaOlevanPalanXKoordinaatti(kirjain);
        int y = this.getRotaatioPisteenaOlevanPalanYKoordinaatti(kirjain);
        for (int i = 0; i < 3; i++) {
            if (this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y - i)
                    || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 2, y - i)) {
                return true;
            }
        }
        if (kirjain.getClass() == lKirjain.class
                && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y - 1)) {
            return true;
        }
        if (kirjain.getClass() == jKirjain.class
                && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y + 1)
                || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x, y + 1)) {
            return true;
        }
        return false;
    }

    /**
     * Metodi palauttaa totuusarvon sille, osuuko parametrina annettava
     * LjaJmuotti luokan edustaja (L- tai J-kirjain) johonkin riviin
     * käännettäessä kuviota asennosta 1 asentoon 2.
     *
     * @param kirjain käännettävä L- tai J-kirjain
     *
     * @return totuusarvo sille, osuuko L- tai J-kirjain riveihin käännettäessä
     * kuviota asennosta 1 asentoon 2
     *
     * @see tetris.domain.LjaJmuotti#getRotaatioPisteenaOlevaPala()
     * @see tetris.domain.Kuvio#onkoKuviossaTietyssaKoordinaatissaJoPala(int,
     * int)
     */
    public boolean LtaiJkirjaimenKaantoAsennosta1OttaaRiveihin(LjaJmuotti kirjain) {
        int x = this.getRotaatioPisteenaOlevanPalanXKoordinaatti(kirjain);
        int y = this.getRotaatioPisteenaOlevanPalanYKoordinaatti(kirjain);
        for (int i = 0; i < 3; i++) {
            if (this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - i, y + 1)
                    || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - i, y + 2)) {
                return true;
            }
        }
        if (kirjain.getClass() == lKirjain.class
                && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y)) {
            return true;
        }
        if (kirjain.getClass() == jKirjain.class
                && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y + 1)
                || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y)) {
            return true;
        }
        return false;
    }

    /**
     * Metodi palauttaa totuusarvon sille, osuuko parametrina annettava
     * LjaJmuotti luokan edustaja (L- tai J-kirjain) johonkin riviin
     * käännettäessä kuviota asennosta 2 asentoon 3.
     *
     * @param kirjain käännettävä L- tai J-kirjain
     *
     * @return totuusarvo sille, osuuko L- tai J-kirjain riveihin käännettäessä
     * kuviota asennosta 2 asentoon 3
     *
     * @see tetris.domain.LjaJmuotti#getRotaatioPisteenaOlevaPala()
     * @see tetris.domain.Kuvio#onkoKuviossaTietyssaKoordinaatissaJoPala(int,
     * int)
     */
    public boolean LtaiJkirjaimenKaantoAsennosta2OttaaRiveihin(LjaJmuotti kirjain) {
        int x = this.getRotaatioPisteenaOlevanPalanXKoordinaatti(kirjain);
        int y = this.getRotaatioPisteenaOlevanPalanYKoordinaatti(kirjain);
        for (int i = 0; i < 3; i++) {
            if (this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y + i)
                    || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 2, y + i)) {
                return true;
            }
        }
        if (kirjain.getClass() == lKirjain.class
                && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y + 1)) {
            return true;
        }
        if (kirjain.getClass() == jKirjain.class
                && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x, y - 1)
                || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y - 1)) {
            return true;
        }
        return false;
    }

    /**
     * Metodi palauttaa totuusarvon sille, osuuko parametrina annettava
     * LjaJmuotti luokan edustaja (L- tai J-kirjain) johonkin riviin
     * käännettäessä kuviota asennosta 3 asentoon 0.
     *
     * @param kirjain käännettävä L- tai J-kirjain
     *
     * @return totuusarvo sille, osuuko L- tai J-kirjain riveihin käännettäessä
     * kuviota asennosta 3 asentoon 4
     *
     * @see tetris.domain.LjaJmuotti#getRotaatioPisteenaOlevaPala()
     * @see tetris.domain.Kuvio#onkoKuviossaTietyssaKoordinaatissaJoPala(int,
     * int)
     */
    public boolean LtaiJkirjaimenKaantoAsennosta3OttaaRiveihin(LjaJmuotti kirjain) {
        int x = this.getRotaatioPisteenaOlevanPalanXKoordinaatti(kirjain);
        int y = this.getRotaatioPisteenaOlevanPalanYKoordinaatti(kirjain);
        for (int i = 0; i < 3; i++) {
            if (this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + i, y - 1)
                    || this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + i, y - 2)) {
                return true;
            }
        }
        if (kirjain.getClass() == lKirjain.class
                && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x + 1, y)) {
            return true;
        }
        if (kirjain.getClass() == jKirjain.class
                && this.rivit.onkoKuviossaTietyssaKoordinaatissaJoPala(x - 1, y - 1)
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
