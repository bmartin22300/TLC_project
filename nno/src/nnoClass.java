import javax.swing.UIManager;
import java.awt.*;
import nno.*;

/**
 * <p>Titre : nno</p>
 * <p>Description : nilNovi Objet</p>
 * <p>Copyright : Copyright (c) 2003</p>
 * <p>SociÃ©tÃ© : LSI2</p>
 * @author Enssat
 * @version 1.0
 */

public class nnoClass {
  boolean packFrame = false;

  //Construire l'application
  public nnoClass() {
    Cadre1 frame = new Cadre1();
    //Valider les cadres ayant des tailles prÃ©dÃ©finies
    //Compacter les cadres ayant des infos de taille prÃ©fÃ©rÃ©es - ex. depuis leur disposition
    if (packFrame) {
      frame.pack();
    }
    else {
      frame.validate();
    }
    //Centrer la fenï¿½tre
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
  }
  //MÃ©thode main
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    new nnoClass();
  }
}
