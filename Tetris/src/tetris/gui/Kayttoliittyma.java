package tetris.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import tetris.peli.Tetris;

/**
 * Luokassa määritellään minkälainen käyttöliittymä pelissä on.
 *
 * @author Johannes
 */
public class Kayttoliittyma implements Runnable {

    /**
     * Käyttöliittymän kehys.
     */
    private JFrame frame;
    /**
     * Käyttöliittymään liittyvä peli
     */
    private Tetris tetris;
    /**
     * Yksittäisen palan sivun pituus, joka määrittää osaltaan miten iso
     * käyttöliittymästä luodaan
     */
    private int palanPituus;
    /**
     * Piirtoalusta johon piirretään pelissä tarvittavat objektit.
     */
    private Piirtoalusta piirtoalusta;
    private Nappaimistonkuuntelija kuuntelija;

    public Kayttoliittyma(Tetris tetris, int sivunPituus) {
        this.tetris = tetris;
        this.palanPituus = sivunPituus;
        this.kuuntelija = new Nappaimistonkuuntelija(tetris);
    }

    /**
     * Metodi määrittelee minkälainen käyttöliittymä luodaan, kun peli
     * käynnistetään.
     *
     */
    @Override
    public void run() {
        frame = new JFrame("Tetris");
        frame.setLocation(640, 60);
        int leveys = (tetris.getLeveys() + 8) * palanPituus;
        int korkeus = (tetris.getKorkeus() + 2) * palanPituus;
        frame.setPreferredSize(new Dimension(leveys, korkeus));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Metodi joka luo piirtoalustan ja lisää sen käyttöliittymäkehykseen.
     * Lisäksi kehykseen asetetaan näppäimistönkuuntelija.
     *
     * @param container objekti johon piirtoalusta lisätään
     * 
     * @see tetris.gui.Kayttoliittyma#luoPiirtoalusta() 
     * @see tetris.gui.Kayttoliittyma#luoValikko() 
     */
    private void luoKomponentit(Container container) {
        JPanel osat = new JPanel();
        osat.setLayout(new BoxLayout(osat, BoxLayout.X_AXIS));
        osat.add(this.luoPiirtoalusta());
        osat.add(this.luoValikko());
        container.add(osat);
        frame.setFocusable(true);
        frame.addKeyListener(kuuntelija);
    }

    /**
     * Metodi palauttaa paluuarvonaan tekstikentän pelaajan nimen syöttämistä varten
     * 
     * @return pelaajan nimelle tarkoitettu tekstikenttä 
     */
    private JTextField luoPelaajaNimelleKentta() {
        JTextField pelaajaNimi = new JTextField("Anonyymi");
        pelaajaNimi.setMaximumSize(new Dimension(300, 20));
        kuuntelija.setPelaajanimi(pelaajaNimi);
        pelaajaNimi.addActionListener(kuuntelija);
        return pelaajaNimi;
    }

    /**
     * Metodi palauttaa paluuarvonaan tekstikentän pistetilanteen näyttämistä varten.
     * 
     * @return pistetilanteelle tarkoitettu tekstikenttä 
     */
    private JTextField luoPisteilleKentta() {
        JTextField pistekentta = new JTextField("0");
        pistekentta.setMaximumSize(new Dimension(300, 20));
        pistekentta.setEnabled(false);
        kuuntelija.setPistekentta(pistekentta);
        pistekentta.addActionListener(kuuntelija);
        return pistekentta;
    }

    /**
     * Metodi palauttaa vaikeustason näyttämistä varten tarkoitetun tekstikentän.
     * 
     * @return vaikeustasolle tarkoitettu tekstikenttä 
     */
    private JTextField luoVaikeustasolleKentta() {
        JTextField vaikeustaso = new JTextField("-");
        vaikeustaso.setMaximumSize(new Dimension(300, 20));
        vaikeustaso.setEnabled(false);
        kuuntelija.setVaikeustasokentta(vaikeustaso);
        vaikeustaso.addActionListener(kuuntelija);
        return vaikeustaso;
    }

    /**
     * Metodi palauttaa paluuarvonaan JPanel-olion, 
     * jossa on Tetriksen valikkoon kuuluvat komponentit aseteltuna oikein.
     * 
     * @return JPanel-olio, joka toimii tetriksen valikkona
     * 
     * @see tetris.gui.Kayttoliittyma#muodostaVaikeustasoNappulat() 
     */
    private JPanel luoValikko() {
        JPanel valikko = new JPanel();
        valikko.setLayout(new BoxLayout(valikko, BoxLayout.Y_AXIS));
        JButton pelaa = new JButton("Pelaa");
        kuuntelija.setPelaaPainike(pelaa);
        pelaa.addActionListener(kuuntelija);
        valikko.add(new JLabel("Anna pelaajanimesi: "));
        valikko.add(this.luoPelaajaNimelleKentta());
        valikko.add(new JLabel("Pisteet:"));
        valikko.add(this.luoPisteilleKentta());
        valikko.add(new JLabel("Vaikeustaso:"));
        valikko.add(this.luoVaikeustasolleKentta());
        valikko.add(new JLabel("Valitse vaikeustaso: "));
        ArrayList<JCheckBox> valinnat = this.muodostaVaikeustasoNappulat();
        ButtonGroup ryhma = new ButtonGroup();
        for (JCheckBox boksi : valinnat) {
            ryhma.add(boksi);
            valikko.add(boksi);
            boksi.addActionListener(kuuntelija);
        }
        kuuntelija.setVaikeustasoPainikkeet(valinnat);
        valikko.add(pelaa);
        return valikko;
    }

    public Paivitettava getPaivitettava() {
        return this.piirtoalusta;
    }

    /**
     * Metodi palauttaa pelin piirtoalustana toimivan piirtoalustan
     *
     * @return pelin piirtoalustana toimiva piirtoalusta
     */
    private Piirtoalusta luoPiirtoalusta() {
        piirtoalusta = new Piirtoalusta(tetris, palanPituus);
        piirtoalusta.setPreferredSize(new Dimension((tetris.getLeveys() + 1) * palanPituus, (tetris.getKorkeus() + 2) * palanPituus));
        return piirtoalusta;
    }

    /**
     * Metodi palauttaa paluuarvonaan listan joka sisältää yhden
     * JCheckBox-olioin kutakin pelin vaikeustasoa kohti.
     *
     * @return lista JCheckBox-olioita, joiden tekstikentissä on vaikeustasojen
     * nimet
     */
    private ArrayList<JCheckBox> muodostaVaikeustasoNappulat() {
        ArrayList<JCheckBox> valinnat = new ArrayList<JCheckBox>();
        valinnat.add(new JCheckBox("Aloittelija"));
        valinnat.add(new JCheckBox("Helppo"));
        valinnat.add(new JCheckBox("Normaali"));
        valinnat.add(new JCheckBox("Edistynyt"));      
        valinnat.add(new JCheckBox("Vaikea"));
        valinnat.add(new JCheckBox("Erittäin vaikea"));
        valinnat.add(new JCheckBox("Kovaksi keitetty"));
        valinnat.add(new JCheckBox("Nolife (Pro)"));
        valinnat.add(new JCheckBox("Uber (Pro)"));
        valinnat.add(new JCheckBox("Jumalmoodi (Pro)"));
        return valinnat;
    }
}
