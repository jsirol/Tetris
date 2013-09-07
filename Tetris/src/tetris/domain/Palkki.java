
package tetris.domain;

import tetris.Vari;

/**
 *
 * @author Johannes
 */

//Palkki on 5 peräkkäin laitetun palan muodostama kuvio.  Palkki luodaan pystysuuntaisena ja konstruktorin parametrina annetaan rotaatiopisteen koordinaatit.
public class Palkki extends Kuvio {
    
        public Palkki(int x, int y) {
        super();
        Vari punainen=Vari.PUNAINEN;
        super.lisaaPala(new Pala(x,y-2,punainen));     //ylin tai vasen pala
        super.lisaaPala(new Pala(x,y-1, punainen));
        super.lisaaPala(new Pala(x,y, punainen));      //rotaatiopiste tämän palan kohdalla 
        super.lisaaPala(new Pala(x,y+1, punainen));
        super.lisaaPala(new Pala(x, y+2, punainen));   //alin tai oikea pala
    }
        
        //kääntösuunta pysty-asennosta vaakaan tapahtuu vastapäivään. vaaka-asennosta pysty-asentoon myötäpäivään.
        @Override
        public void kaanna() {
            if(this.onVaakaAsennossa()) {
                this.kaannaVaastaPystyyn();
            } else {
                this.kaannaPystystaVaakaan();
            }
        }
        
        //palauttaa true jos palkki on "vaaka-asennossa", muuten false
        public boolean onVaakaAsennossa() {
            int rotaatioPisteenYKoordinaatti= this.getRotaatioPisteenaOlevaPala().getY();
            if(this.getPalat().get(0).getY()==rotaatioPisteenYKoordinaatti) {
                return true;
            }
            return false;
        }
        
        //kääntää palkin pysty-asennosta vaaka-asentoon
        public void kaannaVaastaPystyyn() {
            int i=-2;
            for(Pala pala : this.getPalat()) {
                pala.setX(this.getRotaatioPisteenaOlevaPala().getX());
                pala.setY(this.getRotaatioPisteenaOlevaPala().getY()+i);
                i++;
            }
        }
        
        //kääntää palkin vaaka-asennosta pysty-asentoon
        public void kaannaPystystaVaakaan() {
            int i=-2;                                      
            for(Pala pala : this.getPalat()) {
                pala.setX(this.getRotaatioPisteenaOlevaPala().getX()+i);
                pala.setY(this.getRotaatioPisteenaOlevaPala().getY());
                i++;
            }
        }
            
        @Override
        public Pala getRotaatioPisteenaOlevaPala() {
            return this.getPalat().get(2);
        }
    
}
