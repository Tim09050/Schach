package schach.projekt.feld;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGUniverse;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
public class FeldVisualisierer extends JLabel{

    private File datei;
    private final Feld feld;
    public FeldVisualisierer(Feld feld){
        this.feld = feld;
        updateDatei();
    }

    @Override
    protected void paintComponent(Graphics grafik) {
        super.paintComponent(grafik);
        SVGUniverse svgUniverse = new SVGUniverse();
        try {
            SVGDiagram diagram = svgUniverse.getDiagram(svgUniverse.loadSVG(datei.toURI().toURL()));
            try {
                AffineTransform at = new AffineTransform();
                at.setToScale(this.getWidth()/diagram.getWidth(), this.getHeight()/diagram.getWidth());
                Graphics2D grafik2D = (Graphics2D) grafik;
                grafik2D.transform(at);
                diagram.render(grafik2D);
            }
            catch(Exception ex) {System.out.println(ex);}}
        catch (Exception ex2) {System.out.println(ex2);}
    }

    public void updateDatei(){
        this.datei = new File((feld.getFigurAufFeld() != null) ? "schachfiguren/edge_capture.svg" : "schachfiguren/circle_move.svg");
    }
}
