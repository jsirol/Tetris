package tetris.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import tetris.Vaikeustaso;
import tetris.peli.Tetris;

/**
 * Luokka määrittelee näppäimistönkuuntelijan ja sen toiminnallisuuden.
 * Lisäksi määritellään päävalikon kuuntelija ja sen toiminnallisuus.
 *
 * @author Johannes
 */
public class Nappaimistonkuuntelija implements KeyListener, ActionListener {

    /**
     * kuunneltava objekti
     */
    private Tetris tetris;
    /**
     * muuttuja sisältää tiedon siitä, onko peli keskeytetty (huom! eri kuin
     * loppunut)
     */
    private JTextField nimiKentta;
    /**
     * vaikeustasolle varattu tekstikenttä
     */
    private JTextField vaikeustasoKentta;
    /**
     * pelaa-painike
     */
    private JButton pelaaPainike;
    /**
     * pisteiden näyttämiseen tarkoitettu tekstikenttä
     */
    private JTextField pisteKentta;
    /**
     * vaikeustason valitsemiseen tarkoitetut painikkeet listana
     */
    private ArrayList<JCheckBox> vaikeustasoPainikkeet;
    /**
     * kertoo viimeistä edellisen pelin keskeyttämiseen johtaneen painikkeen painalluksen ajankohdan
     */
    private long viimeKeskeytys;
    /**
     * kertoo viimeisimmän pelin keskeyttämiseen johtaneen painikkeen painalluksen ajankohdan
     */
    private long uusiKeskeytys;

    public Nappaimistonkuuntelija(Tetris tetris) {
        this.tetris = tetris;
        tetris.addActionListener(this);     
        viimeKeskeytys = System.currentTimeMillis();
        uusiKeskeytys = System.currentTimeMillis();
    }

    /**
     * Metodi määrittelee mitä tapahtuu kun tiettyjä näppäimiä painetaan
     * näppäimistöllä.
     *
     * @param e näppäimen painallus
     *
     * @see tetris.peli.Tetris#kaannaKuviota()
     * @see tetris.peli.Tetris#pudotaKuvioNiinAlasKuinVoi(tetris.domain.Kuvio)
     * @see tetris.peli.Tetris#liikutaKuviotaVasemmalle()
     * @see tetris.peli.Tetris#liikutaKuviotaOikealle()
     * @see tetris.peli.Tetris#pudotaKuvioNiinAlasKuinVoi(tetris.domain.Kuvio)
     * @see
     * tetris.peli.Tetris#pudotaKuviotaAlasYhdellaJosVoi(tetris.domain.Kuvio)
     * @see tetris.gui.Nappaimistonkuuntelija#keskeytaTaiJatkaPelia()
     * @see tetris.gui.Nappaimistonkuuntelija#asetaValikkopainikkeilleTila(boolean) 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (!this.tetris.getPeliPaattynyt()) {
            if (!this.tetris.getKeskeytetty()) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    this.tetris.kaannaKuviota();
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    this.tetris.pudotaKuvioNiinAlasKuinVoi(tetris.getKuvio());
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    this.tetris.liikutaKuviotaVasemmalle();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    this.tetris.liikutaKuviotaOikealle();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    this.tetris.pudotaKuviotaAlasYhdellaJosVoi(tetris.getKuvio());
                }
                this.tetris.getPaivitettava().paivita();
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                this.keskeytaTaiJatkaPelia();
            }
        } else {
            this.vaihdaVaikeustasoRastitunRuudunMukaan();
            this.asetaValikkopainikkeilleTila(true);
        }
    }

    /**
     * Metodi asettaa Päävalikon käytettäväksi tarkoitetut painikkeet
     * parametrina annettuun tilaan
     *
     * @param tila true=päällä, false=pois päältä
     *
     * @see
     * tetris.gui.Nappaimistonkuuntelija#vaihdaCheckBoxienTila(java.util.ArrayList,
     * boolean)
     */
    private void asetaValikkopainikkeilleTila(boolean tila) {
        this.pelaaPainike.setEnabled(tila);
        this.vaihdaCheckBoxienTila(vaikeustasoPainikkeet, tila);
        this.nimiKentta.setEnabled(tila);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Metodi keskeyttää tetriksen laskurin, jos se on käynnissä. Jos laskuri ei
     * ole käynnissä, metodi käynnistää sen.
     */
    private void keskeytaTaiJatkaPelia() {
        this.uusiKeskeytys = System.currentTimeMillis();
        if (!this.tetris.getKeskeytetty() && this.uusiKeskeytys - this.viimeKeskeytys > 2000) {
            this.tetris.stop();
            this.tetris.setKeskeytetty(true);
            this.viimeKeskeytys = this.uusiKeskeytys;
        } else {
            this.tetris.start();
            this.tetris.setKeskeytetty(false);
        }
    }

    /**
     * Metodi tarkkailee Päävalikossa tapahtuvien tapahtumien aiheuttamat
     * toimenpiteet. Lisäksi metodi tarkkailee Tetriksen Timer luokan
     * lähettämien tapahtumien kohdalla, tuleeko Päävalikon reaaliaikaisia
     * tietoja pisteistä ja vaikeustasosta päivittää.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == this.valittuCheckBox()) {
                this.vaihdaPelinVaikeustasoJaPaivitaVaikeustasoKentta();
            } else if (e.getSource() == pelaaPainike) {
                this.asetaValikkopainikkeilleTila(false);
                if (tetris.getPeliPaattynyt()) {
                    this.alustaJaKaynnistaUusiPeli();
                } else {
                    tetris.start();
                }
            }
            this.paivitaTekstikentat();
        } catch (Exception ex) {
        }
    }

    /**
     * Metodi päivittää pistekentän ja vaikeustasokentän tiedot kulloisenkin
     * pelitilanteen mukaan.
     */
    private void paivitaTekstikentat() {
        pisteKentta.setText(this.pisteetMerkkijonona());
        vaikeustasoKentta.setText(tetris.getTasosysteemi().getVaikeustaso().toString());
    }

    /**
     * Metodi alustaa pelitilanteen uutta peliä varten ja käynnistää uuden
     * pelin.
     *
     * @see tetris.peli.Tetris#tuhoaKaikkiRivit()
     * @see tetris.peli.Tetris#nollaaPisteet()
     * @see tetris.peli.Tetris#alustaJaAloitaUusiPeli() 
     */
    private void alustaJaKaynnistaUusiPeli() {
        this.tetris.alustaJaAloitaUusiPeli();
        pisteKentta.setText(this.pisteetMerkkijonona());

    }

    /**
     * Metodi vaihtaa pelin vaikeustasoa valitun valintaruudun mukaan 
     * ja päivittää tiedon vaikeustasosta vaikeustason ilmaisevaan tekstikenttään.
     */
    private void vaihdaPelinVaikeustasoJaPaivitaVaikeustasoKentta() {
        this.vaihdaVaikeustasoRastitunRuudunMukaan();
        vaikeustasoKentta.setText(this.valittuCheckBox().getText());
    }

    /**
     * Metodi palauttaa paluuarvonaan Tetriksen pistemäärän merkkijonona.
     *
     * @return Tetriksen pistemäärä merkkijonona
     */
    public String pisteetMerkkijonona() {
        String pisteet = "";
        pisteet += tetris.getPisteet();
        return pisteet;
    }

    /**
     * Metodi saa parametrinaan listan JCheckBox-olioita ja niiden tilan
     * parametrina annettavan totuusarvon mukaiseen tilaan.
     *
     * @param nappulat JCheckBox-oliot joiden tila halutaan muuttaa
     * @param paalla totuusarvo, johon kertoo onko tila päällä vai pois päältä
     */
    public void vaihdaCheckBoxienTila(ArrayList<JCheckBox> nappulat, boolean paalla) {
        for (JCheckBox boksi : nappulat) {
            boksi.setEnabled(paalla);
        }
    }

    /**
     * Metodi vaihtaa Tetriksen vaikeustason sen mukaan, mikä
     * näppäimistönkuuntelijan kuuntelemista JCheckBox-olioista on valittuna.
     * Jos mikään ei ole valittuna, vaikeustasoksi asetetaan helpoin.
     */
    public void vaihdaVaikeustasoRastitunRuudunMukaan() {
        if (this.valittuCheckBox() == null || this.valittuCheckBox().getText().equals("Aloittelija")) {
            tetris.getTasosysteemi().setVaikeustaso(Vaikeustaso.ALOITTELIJA);
        } else if (this.valittuCheckBox().getText().equals("Helppo")) {
            tetris.getTasosysteemi().setVaikeustaso(Vaikeustaso.HELPPO);
        } else if (this.valittuCheckBox().getText().equals("Normaali")) {
            tetris.getTasosysteemi().setVaikeustaso(Vaikeustaso.NORMAALI);
        } else if (this.valittuCheckBox().getText().equals("Edistynyt")) {
            tetris.getTasosysteemi().setVaikeustaso(Vaikeustaso.EDISTYNYT);
        } else if (this.valittuCheckBox().getText().equals("Vaikea")) {
            tetris.getTasosysteemi().setVaikeustaso(Vaikeustaso.VAIKEA);
        } else if (this.valittuCheckBox().getText().equals("Erittäin vaikea")) {
            tetris.getTasosysteemi().setVaikeustaso(Vaikeustaso.ERITTAINVAIKEA);
        } else if (this.valittuCheckBox().getText().equals("Kovaksi keitetty")) {
            tetris.getTasosysteemi().setVaikeustaso(Vaikeustaso.KOVAKSIKEITETTY);
        } else if (this.valittuCheckBox().getText().equals("Nolife (Pro)")) {
            tetris.getTasosysteemi().setVaikeustaso(Vaikeustaso.NOLIFE);
        } else if (this.valittuCheckBox().getText().equals("Uber (Pro)")) {
            tetris.getTasosysteemi().setVaikeustaso(Vaikeustaso.UBER);
        } else if (this.valittuCheckBox().getText().equals("Nirvana (Pro)")) {
            tetris.getTasosysteemi().setVaikeustaso(Vaikeustaso.NIRVANA);
        }
    }

    /**
     * Metodi palauttaa paluuarvonaan näppäimistönkuuntelijan kuunteleman
     * JCheckBox-olion, joka metodin kutsuhetkellä on valittuna.
     *
     * @return JCheckBox-olio joka on valittuna
     */
    public JCheckBox valittuCheckBox() {
        for (JCheckBox boksi : vaikeustasoPainikkeet) {
            if (boksi.isSelected()) {
                return boksi;
            }
        }
        return null;
    }

    public String getPisteet() {
        return this.pisteKentta.getText();
    }

    public String getPelaajaNimi() {
        return this.nimiKentta.getText();
    }

    public void setPelaajanimi(JTextField nimikentta) {
        this.nimiKentta = nimikentta;
    }

    public void setVaikeustasokentta(JTextField vaikeustasokentta) {
        this.vaikeustasoKentta = vaikeustasokentta;
    }

    public void setPelaaPainike(JButton nappula) {
        this.pelaaPainike = nappula;
    }

    public void setPistekentta(JTextField pisteet) {
        this.pisteKentta = pisteet;
    }

    public void setVaikeustasoPainikkeet(ArrayList<JCheckBox> painikkeet) {
        this.vaikeustasoPainikkeet = painikkeet;
    }
}
