package schach.projekt.schachFiguren;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGUniverse;
import schach.projekt.feld.Feld;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;

public abstract class Schachfiguren extends JLabel {

    private final File datei;
    private final Teams team;
    private final Feld jetzigesFeld;

    public Schachfiguren(String PfadZurDatei, Teams team, Feld startFeld) {
        datei = new File(PfadZurDatei);
        this.team = team;
        this.jetzigesFeld = startFeld;
    }

    @Override
    protected void paintComponent(Graphics grafik){
        SVGUniverse svgUniverse = new SVGUniverse();
        try {
            SVGDiagram diagramm = svgUniverse.getDiagram(svgUniverse.loadSVG(datei.toURI().toURL()));
            try {
                AffineTransform at = new AffineTransform();
                at.setToScale(this.getWidth()/diagramm.getWidth(), this.getHeight()/diagramm.getHeight());
                Graphics2D grafik2D = (Graphics2D) grafik;
                grafik2D.transform(at);
                diagramm.render(grafik2D);
            }
            catch (Exception ex){
                System.out.println("ex");
            }
        }
        catch (Exception ex2){
            System.out.println("ex2");
        }

    }

    public Feld getJetzigesFeld() {
        return jetzigesFeld;
    }
}
