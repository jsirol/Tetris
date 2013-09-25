
package tetris.domain;

import tetris.Vari;

/**
 * Luokka perii Kuvio-luokan ja määrittelee tarkemmin minkälainen on Palkki-kuvio.
 * Palkki koostuu viidestä "palkin" muotoon asetellusta palasta 
 * (ts. joko y- koordinaatti tai x-koordinaatti on palkin paloilla sama, riippuen palkin asennosta).
 * Konstruktorin parametrit määrittävät rotaatiopisteenä olevan palan sijainnin.
 * 
 * @author Johannes
 */

public class Palkki extends Kuvio {   
        public Palkki(int x, int y) {
        super();
        Vari punainen=Vari.PUNAINEN;
        super.lisaaPala(new Pala(x,y-2,punainen));     
        super.lisaaPala(new Pala(x,y-1, punainen));
        super.lisaaPala(new Pala(x,y, punainen));       
        super.lisaaPala(new Pala(x,y+1, punainen));
        super.lisaaPala(new Pala(x, y+2, punainen));   
    }
        
        /**
         * Metodi suorittaa palkin kääntämisen. Palkkia käännetään pysty-asennosta vaaka-asentoon vastapäivään.
         * Vaaka-asennosta pystyasentoon kääntäminen suoritetaan myötäpäivään.
         * Käännös kääntää palkkia 90 astetta.
         * 
         * @see tetris.domain.Palkki#kaannaPystystaVaakaan() 
         * @see tetris.domain.Palkki#kaannaVaastaPystyyn() 
         * 
         */
        @Override
        public void kaanna() {
            if(this.onVaakaAsennossa()) {
                this.kaannaVaastaPystyyn();
            } else {
                this.kaannaPystystaVaakaan();
            }
        }
        
        /**
         * Metodi kertoo onko palkki vaaka-asennossa palauttamalla tätä tilannetta vastaavan totuusarvon.
         * 
         * @return totuusarvo, onko palkki vaaka-asennossa 
         */      
        public boolean onVaakaAsennossa() {           
            if(super.onkoKuviossaTietyssaKoordinaatissaJoPala(this.getRotaatioPisteenaOlevaPala().getX()-2, this.getRotaatioPisteenaOlevaPala().getY())) {
                return true;
            }
            return false;
        }
        
        /**
         * Metodi kääntää palan vaaka-asennosta pystyasentoon.
         * Kääntäminen tapahtuu myötäpäivään ja kääntökulma on 90 astetta.
         * 
         * @see tetris.domain.Palkki#getRotaatioPisteenaOlevaPala() 
         * 
         */      
        public void kaannaVaastaPystyyn() {
            int i=-2;
            for(Pala pala : this.getPalat()) {
                pala.setX(this.getRotaatioPisteenaOlevaPala().getX());
                pala.setY(this.getRotaatioPisteenaOlevaPala().getY()+i);
                i++;
            }
        }
        
        /**
         * Metodi kääntää palan pystyasennosta vaaka-asentoon.
         * Kääntäminen tapahtuu myötäpäivään ja kääntökulma on 90 astetta.
         * 
         * @see tetris.domain.Palkki#getRotaatioPisteenaOlevaPala() 
         * 
         */
        public void kaannaPystystaVaakaan() {
            int i=-2;                                      
            for(Pala pala : this.getPalat()) {
                pala.setX(this.getRotaatioPisteenaOlevaPala().getX()+i);
                pala.setY(this.getRotaatioPisteenaOlevaPala().getY());
                i++;
            }
        }
            
        /**
         * Metodi palauttaa palkin rotaatiopisteenä olevan palan.
         * 
         * @return rotaatiopisteenä oleva pala 
         */
        @Override
        public Pala getRotaatioPisteenaOlevaPala() {
            return this.getPalat().get(2);
        }
    
}
