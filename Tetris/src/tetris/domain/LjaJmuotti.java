package tetris.domain;

/**
 * Luokka perii Kuvio-luokan ja määrittelee L ja J kuvioille yhteisiä metodeja.
 * 
 * @author Johannes
 */
public abstract class LjaJmuotti extends Kuvio {

    /**
     * Metodi suorittaa L ja J kuvioiden samalla tavalla kääntyvien palojen sijainnin uuden sijainnin asettamisen, 
     * käännyttiinpä vaaka-asennosta pystyyn tai pystystä vaakaan. Käännökset suoritetaan aina 90 vastapäivään.
     * 
     * @see tetris.domain.LjaJmuotti#kaannaPaaAkseliPystyyn() 
     * @see tetris.domain.LjaJmuotti#kaannaPaaAkseliVaakaan() 
     * @see tetris.domain.LjaJmuotti#getAsento() 
     * 
     */
    @Override
    public void kaanna() {
        int asento = this.getAsento();
        if (asento == 0 || asento == 2) {
            this.kaannaPaaAkseliVaakaan();
        } else {
            this.kaannaPaaAkseliPystyyn();
        }
    }

    
    /**
     * Metodi kääntää, eli asettaa L ja J kuvioiden pääakselin (linja jossa paloja on 3 peräkkäin)
     * paikoissa 0 ja 1 olevat palat oikeaan paikkaan kun kuviota käännettään 90 astetta vastapäivään ja ollaan
     * päätymässä "pystyasentoon". Ennen metodin suorittamista kuvio on siis vaaka-asennossa 
     * (näyttäen kirjaimelta käännettynä kyljelleen).
     * Ts. L ja J kuvioiden samalla tavalla pysty-asentoon kääntyvät palat käännetään yhden käännöksen mukaisesti.
     * 
     * @see tetris.domain.LjaJmuotti#getRotaatioPisteenaOlevaPala()  
     * @see tetris.domain.LjaJmuotti#getAsento() 
     * 
     */
    public void kaannaPaaAkseliPystyyn() {
        Pala rp = this.getRotaatioPisteenaOlevaPala();       
        int j=2;
        int k=1;
        if(this.getAsento()==3) {
            j=-2;
            k=-1;
        }
        for (int i = 0; i < 2; i++) {
            this.getPalat().get(i).setSijainti(rp.getX(), rp.getY()+j-k*i);
        }       
    }

    /**
     * Metodi kääntää, eli asettaa L ja J kuvioiden pääakselin (linja jossa paloja on 3 peräkkäin)
     * paikoissa 0 ja 1 olevat palat oikeaan paikkaan kun kuviota käännetään 90 astetta vastapäivään ja ollaan
     * päätymässä "vaaka-asentoon". Ennen metodin suorittamista kuvio on siis pysty-asennossa 
     * (näyttäen joko kirjaimelta oikein, tai väärinpäin).
     * Ts. L ja J kuvioiden samalla tavalla vaaka-asentoon käännettäessä kääntyvät palat käännetään yhden käännöksen mukaisesti.
     * 
     * @see tetris.domain.LjaJmuotti#getRotaatioPisteenaOlevaPala()    
     * @see tetris.domain.LjaJmuotti#getAsento() 
     */
    public void kaannaPaaAkseliVaakaan() {
        Pala rp = this.getRotaatioPisteenaOlevaPala();
        int j=2;
        int k=1;
        if(this.getAsento()==2) {
            j=-2;
            k=-1;           
        }
        for (int i = 0; i < 2; i++) {
            this.getPalat().get(i).setSijainti(rp.getX()+k*i-j, rp.getY());
        }
    }
    
    /**
     * Metodi palauttaa kokonaislukuarvona L tai J kuvion asennon. 
     * 0 Tarkoittaa asentoa jossa kuvio luodaan konstruktorissaan. 
     * Seuraava asento saadaan kääntämällä kuviota vastapäivään 90 astetta.
     * L ja J kuvioilla on 4 erilaista asentoa (0,1,2,3).
     * 
     * @see tetris.domain.LjaJmuotti#getRotaatioPisteenaOlevaPala() 
     * 
     * @return kokonaisluku, joka vastaa asentoa, jossa kuvio on
     */
    public int getAsento() {
        if (this.getRotaatioPisteenaOlevaPala().getY() - 2 == this.getPalat().get(0).getY()) {
            return 0;
        } else if (this.getRotaatioPisteenaOlevaPala().getX() - 2 == this.getPalat().get(0).getX()) {
            return 1;
        } else if (this.getRotaatioPisteenaOlevaPala().getY() + 2 == this.getPalat().get(0).getY()) {
            return 2;
        } else {
            return 3;
        }
    }

    /**
     * Metodi palauttaa L ja J kirjaimien rotaatiopisteenä olevan palan.
     * 
     * @return rotaatiopisteenä oleva pala
     */
    @Override
    public Pala getRotaatioPisteenaOlevaPala() {
        return this.getPalat().get(2);
    }
}
