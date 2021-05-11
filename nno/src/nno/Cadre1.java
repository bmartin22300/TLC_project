package nno;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import javax.swing.text.*;
import javax.swing.event.*;

import ptGen.*;
import analyseurLexical.*;
import analyseurLexical.MesExceptions.*;
import analyseurSyntaxique.*;
import interpreteurNNO.instructions.*;
import interpreteurNNO.programmeObjetNNO.*;



/**
 * <p>Titre : nno</p>
 * <p>Description : nilNovi Objet</p>
 * <p>Copyright : Copyright (c) 2003</p>
 * <p>SociÃ©tÃ© : LSI2</p>
 * @author Enssat
 * @version 1.0
 */

public class Cadre1 extends JFrame {
  JPanel contentPane;
  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenuFile = new JMenu();
  JMenuItem jMenuFileExit = new JMenuItem();
  JMenu jMenuHelp = new JMenu();
  JMenuItem jMenuHelpAbout = new JMenuItem();
  JToolBar jToolBar = new JToolBar();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  ImageIcon image1;
  ImageIcon image2;
  ImageIcon image3;
  JPanel jPanel1 = new JPanel();
  JSplitPane jSplitPane1 = new JSplitPane();
  JSplitPane jSplitPane2 = new JSplitPane();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jTextArea1 = new JTextArea();
  JScrollPane jScrollPane3 = new JScrollPane();
  JTextArea jTextArea2 = new JTextArea();
  JButton jButton4 = new JButton();
  JButton jButton5 = new JButton();
  JFileChooser choixFichier = new JFileChooser();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  JButton jButton3 = new JButton();
  String fichierCourant = null;//Chemin accï¿½s fichier courant
  boolean modifie=false;
  Document document1;
  JScrollPane jScrollPane2 = new JScrollPane();
  JTextArea jTextArea3 = new JTextArea(); //le fichier a-t-il ï¿½tï¿½ modifiï¿½?
  anaLex al=new anaLex(jTextArea1,jTextArea2);
  JButton jButton6 = new JButton();
  JButton jButton7 = new JButton();
  interpreteur inter;


  //Construire le cadre
  public Cadre1() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Initialiser le composant
  private void jbInit() throws Exception  {
    image1 = new ImageIcon(nno.Cadre1.class.getResource("openFile.png"));
    image3 = new ImageIcon(nno.Cadre1.class.getResource("help.png"));
    contentPane = (JPanel) this.getContentPane();
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    document1 = jTextArea1.getDocument();
    contentPane.setLayout(gridBagLayout1);
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.setSize(new Dimension(800, 600));
    this.setTitle("nilNovi Objet");
    this.addWindowListener(new Cadre1_this_windowAdapter(this));
    jMenuFile.setText("Fichier");
    jMenuFileExit.setText("Quitter");
    jMenuFileExit.addActionListener(new Cadre1_jMenuFileExit_ActionAdapter(this));
    jMenuHelp.setText("Aide");
    jMenuHelpAbout.setText("A propos");
    jMenuHelpAbout.addActionListener(new Cadre1_jMenuHelpAbout_ActionAdapter(this));
    jButton1.setIcon(image1);
    jButton1.addActionListener(new Cadre1_jButton1_actionAdapter(this));
    jButton1.setMaximumSize(new Dimension(44, 28));
    jButton1.setMinimumSize(new Dimension(44, 28));
    jButton1.setToolTipText("jButton1 : Ouvrir un fichier");
    jButton2.setIcon(image2);
    jButton4.setIcon(new ImageIcon(nno.Cadre1.class.getResource("comp.gif")));
    jButton5.setIcon(new ImageIcon(nno.Cadre1.class.getResource("exec.gif")));
    jButton5.addActionListener(new Cadre1_jButton5_actionAdapter(this));
    jButton4.setText(" ");
    jButton2.addActionListener(new Cadre1_jButton2_actionAdapter(this));
    jButton2.setEnabled(false);
    jButton2.setMaximumSize(new Dimension(44, 28));
    jButton2.setMinimumSize(new Dimension(44, 28));
    jButton2.setToolTipText("jButton2 : Enregistrer");
    jButton2.setDisabledIcon(new ImageIcon(nno.Cadre1.class.getResource("enrDis.gif")));
    jButton2.setIcon(new ImageIcon(nno.Cadre1.class.getResource("enr.gif")));
    jPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
    jPanel1.setLayout(gridBagLayout2);
    jSplitPane1.setEnabled(true);
    jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jSplitPane1.setBorder(titledBorder2);
    jTextArea1.setDoubleBuffered(true);
    jTextArea1.setMinimumSize(new Dimension(40, 40));
    jTextArea1.setToolTipText("jTextArea1 : Texte source nilNovi");
    jTextArea1.setSelectionColor(Color.black);
    jTextArea1.setText("");
    jTextArea1.setLineWrap(false);
    jTextArea1.addKeyListener(new Cadre1_jTextArea1_keyAdapter(this));
    jTextArea1.addMouseListener(new Cadre1_jTextArea1_mouseAdapter(this));
    jScrollPane1.setToolTipText("texte source nilNovi");
    jTextArea2.setToolTipText("jTextArea2 : Messages nilNovi");
    jTextArea2.setEditable(false);
    jTextArea2.setLineWrap(false);
    jButton4.setToolTipText("jButton4 : Compiler");
    jButton4.setDisabledIcon(new ImageIcon(nno.Cadre1.class.getResource("compDis.gif")));
    jButton4.addActionListener(new Cadre1_jButton4_actionAdapter(this));
    jButton4.setEnabled(false);
    jButton4.setMaximumSize(new Dimension(44, 28));
    jButton4.setMinimumSize(new Dimension(44, 28));
    jButton5.setEnabled(false);
    jButton5.setMaximumSize(new Dimension(44, 28));
    jButton5.setMinimumSize(new Dimension(44, 28));
    jButton5.setToolTipText("jButton5 : ExÃ©cuter");
    jButton5.setDisabledIcon(new ImageIcon(nno.Cadre1.class.getResource("execDis.gif")));
    jButton3.setEnabled(false);
    jButton3.setMaximumSize(new Dimension(44, 28));
    jButton3.setMinimumSize(new Dimension(44, 28));
    jButton3.setToolTipText("jButton3 : Enregistrer sous");
    jButton3.setDisabledIcon(new ImageIcon(nno.Cadre1.class.getResource("enrSousDis.gif")));
    jButton3.setIcon(new ImageIcon(nno.Cadre1.class.getResource("enrSous.gif")));
    jButton3.addActionListener(new Cadre1_jButton3_actionAdapter(this));
    document1.addDocumentListener(new Cadre1_document1_documentAdapter(this));
    jTextArea3.setToolTipText("jTextArea3 : EntrÃ©es-Sorties");
    jTextArea3.setText("");
    jSplitPane2.setDividerSize(5);
    choixFichier.setEnabled(true);
    // la boite de dialogue s'ouvre sur le rÃ©pertoire courant :
    choixFichier.setCurrentDirectory(new File("."));
    //on ne propose que des fichiers .nno en lecture et Ã©criture:
    choixFichier.setFileFilter(new nnoFilter());
    choixFichier.setFileFilter(null);
    jButton6.addActionListener(new Cadre1_jButton6_actionAdapter(this));
    jButton6.setEnabled(false);
    jButton6.setMaximumSize(new Dimension(44, 28));
    jButton6.setMinimumSize(new Dimension(44, 28));
    jButton6.setToolTipText("jButton6 : ExÃ©cuter pas Ã  pas");
    jButton6.setDisabledIcon(new ImageIcon(nno.Cadre1.class.getResource("pasDis.gif")));
    jButton6.setIcon(new ImageIcon(nno.Cadre1.class.getResource("pas.gif")));
    jButton6.setText("");
    jButton7.setEnabled(false);
    jButton7.setMaximumSize(new Dimension(44, 28));
    jButton7.setMinimumSize(new Dimension(44, 28));
    jButton7.setToolTipText("jButton7 : DÃ©sassembler");
    jButton7.setDisabledIcon(new ImageIcon(nno.Cadre1.class.getResource("desDis.gif")));
    jButton7.setIcon(new ImageIcon(nno.Cadre1.class.getResource("des.gif")));
    jButton7.setText("");
    jButton7.addActionListener(new Cadre1_jButton7_actionAdapter(this));
    jToolBar.add(jButton1);
    jToolBar.add(jButton2);
    jToolBar.add(jButton3, null);
    jToolBar.add(jButton4, null);
    jToolBar.add(jButton5, null);
    jToolBar.add(jButton6, null);
    jToolBar.add(jButton7, null);
    jMenuFile.add(jMenuFileExit);
    jMenuHelp.add(jMenuHelpAbout);
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenuHelp);
    this.setJMenuBar(jMenuBar1);
    contentPane.add(jToolBar,  new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 217, 0));
    contentPane.add(jPanel1,  new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(6, 0, 10, 0), 402, 253));
    jPanel1.add(jSplitPane1,  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(17, 12, 18, 16), 347, 157));
    jSplitPane1.add(jSplitPane2, JSplitPane.TOP);
    jSplitPane2.add(jScrollPane1, JSplitPane.LEFT);
    jScrollPane1.getViewport().add(jTextArea1, null);
    jSplitPane2.add(jScrollPane2, JSplitPane.RIGHT);
    jScrollPane2.getViewport().add(jTextArea3, null);
    jSplitPane1.add(jScrollPane3, JSplitPane.BOTTOM);
    jScrollPane3.getViewport().add(jTextArea2, null);
    jSplitPane1.setDividerLocation(150);
    jSplitPane2.setDividerLocation(320);
    miseAJourTitre();
  }//jbInit

  //Opï¿½ration Fichier | Quitter effectuï¿½e
  public void jMenuFileExit_actionPerformed(ActionEvent e) {
    if (! modifie){
      System.exit(0);
    }else{
      int value = JOptionPane.showConfirmDialog(this, "Enregistrer les modifications ?",
             "Editeur de texte",JOptionPane.YES_NO_CANCEL_OPTION);
      switch (value){
      case JOptionPane.YES_OPTION:
        enregistrer();break;
      case JOptionPane.NO_OPTION:
        System.exit(0);break;
      case JOptionPane.CANCEL_OPTION:
      default:
      }
    }
  }

  //Opï¿½ration Aide | A propos effectuï¿½e
  public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
    Cadre1_AboutBox dlg = new Cadre1_AboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
  }
  //Supplantï¿½, ainsi nous pouvons sortir quand la fenï¿½tre est fermï¿½e
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      jMenuFileExit_actionPerformed(null);
    }
  }

  void jButton1_actionPerformed(ActionEvent e) {
    //gï¿½re le clic sur le bouton d'ouverture de fichiers
    if (!modifie){
      jButton2.setEnabled(true);
      jButton5.setEnabled(false);
      jButton6.setEnabled(false);
      jButton7.setEnabled(false);
      if (JFileChooser.APPROVE_OPTION == choixFichier.showOpenDialog(this)){
        ouvrirFic(choixFichier.getSelectedFile().getPath());
      }
    }else{
      if (okToAbandon()){
        jTextArea1.setText("");
        fichierCourant=null;
        modifie=false;
      }
    };
    miseAJourTitre();
  }

  void ouvrirFic(String nomFic){
    try{
      File file = new File(nomFic);
      int size = (int)file.length();
      int chars_read =0;
      FileReader in = new FileReader(file);
      char[] data = new char[size];
      while(in.ready()){
        chars_read +=in.read(data,chars_read,size - chars_read);
      }
      in.close();
      jTextArea1.setText(new String(data, 0, chars_read));
		jTextArea2.setText("");
		jTextArea3.setText("");
      //mï¿½morise le fichier courant :
      this.fichierCourant=nomFic;
      //fichier non modifiï¿½:
      this.modifie=false;
      jButton4.setEnabled(true);
      jButton3.setEnabled(true);
    }catch (IOException e){
      Toolkit.getDefaultToolkit().beep(); //bip si erreur
      jTextArea2.setText("erreur d'ouverture du fichier "+nomFic);
    }
  }

  boolean enregistrer(){
    boolean result=false;
    if (fichierCourant==null) {
      result=enregistrerSous();
    }else{
      try {
        File file = new File(fichierCourant);
        FileWriter out = new FileWriter(file);
        String text = jTextArea1.getText();
        out.write(text);
        out.close();
        this.modifie = false;
        jTextArea2.append("EnregistrÃ© sous " + fichierCourant+"\n");
        result= true;
      }catch (IOException e){
        Toolkit.getDefaultToolkit().beep(); //bip si erreur
        jTextArea2.append("erreur en enregistrant "+fichierCourant+"\n");
      }
    }
    return result;
  }

  boolean enregistrerSous(){
     if (JFileChooser.APPROVE_OPTION == choixFichier.showSaveDialog(this)){
       fichierCourant = choixFichier.getSelectedFile().getPath();
       this.repaint();
       return enregistrer();
     }else{
       this.repaint();
       return false;
     }
  }

  boolean okToAbandon(){
    int value = JOptionPane.showConfirmDialog(this, "Enregistrer les modifications ?",
          "Editeur de texte",JOptionPane.YES_NO_CANCEL_OPTION);
    switch (value){
    case JOptionPane.YES_OPTION:
      return enregistrer();
    case JOptionPane.NO_OPTION:
      return true;
    case JOptionPane.CANCEL_OPTION:
    default:
      return false;
    }
  }

  void jButton4_actionPerformed(ActionEvent e) {
    //compilation
    jTextArea2.setText("");  //reset editeur messages de service nno
    anaLex al= new anaLex(jTextArea1,jTextArea2);//création table des mots clés
    al.traiterToutesLesLignes(); //analyse lexicale ppt dite
    inter=new interpreteur(jTextArea2,jTextArea3);

    PointsGeneration tt= new PointsGeneration(jTextArea2,inter);
    analyseurLL1 as= new analyseurLL1(al,tt,jTextArea2);
    try {
      as.parser();  //analyse syntaxique pp dite
      jTextArea2.append("Fin de la compilation\n");
      jButton5.setEnabled(true);
      jButton6.setEnabled(true);
      jButton7.setEnabled(true);
      jTextArea3.setText("");
    }catch (MonException v){
      Toolkit.getDefaultToolkit().beep(); //bip si erreur
      jTextArea1.requestFocus();
      //surlignage des caractÃ¨res Ã  partir de l'erreur :
      jTextArea1.setSelectionColor(Color.red);
      jTextArea1.select(v.positionLineaire()-1,rechercheFinUl(v.positionLineaire()));
      jTextArea2.append("ArrÃªt prÃ©maturÃ© de la compilation - Programme nilNOvi Objet erronÃ©\n");
	 }catch (ArrayIndexOutOfBoundsException f){
      jTextArea2.append("ArrÃªt prÃ©maturÃ© de la compilation - dÃ©bordement du programme objet\n");
      jButton5.setEnabled(false);
      jButton6.setEnabled(false);
      jButton7.setEnabled(false);
    }
  }

  int rechercheFinUl(int i){
    //recherche de la fin de la chaï¿½ne ayant provoquï¿½ l'erreur,
    //pour souligner la chaï¿½ne erronï¿½e.
    int j=i;
    //INV : j n'est pas au delï¿½ du dernier car et t[i..j-1] ne contient pas
       // de caractï¿½res blanc ou tab
    while ((j!=jTextArea1.getText().length()) &&
          (jTextArea1.getText().charAt(j)!=' ') &&
          (jTextArea1.getText().charAt(j)!=(char)10)&&
          (jTextArea1.getText().charAt(j)!=(char)9))
    {
      j=j+1;
    };
    return j;
  }

  void jButton2_actionPerformed(ActionEvent e) {
    //bouton enregistrer fichier sur place
    if (modifie) {
      enregistrer();
    }
  }

  void jButton3_actionPerformed(ActionEvent e) {
    //bouton enregistrer sous
    enregistrerSous();
    jButton2.setEnabled(true);
    miseAJourTitre();
  }

  void jTextArea1_keyPressed(KeyEvent e) {
    //texte source modifiï¿½
    modifie=true;
    miseAJourTitre();
    jTextArea1.setSelectionColor(Color.black);
    jButton5.setEnabled(false);
    jButton6.setEnabled(false);
    jButton7.setEnabled(false);
    jButton5.setEnabled(false);
    jButton3.setEnabled(true);
    jButton4.setEnabled(true);
  }

  void jTextArea1_mouseDragged(MouseEvent e) {
    //texte source modifiï¿½
    modifie=true;
    miseAJourTitre();
    jButton5.setEnabled(false);
    jButton6.setEnabled(false);
    jButton7.setEnabled(false);
    jButton3.setEnabled(true);
    jButton4.setEnabled(true);
  }

  void document1_changedUpdate(DocumentEvent documentEvent) {
    //texte source modifiï¿½
    modifie=true;
    miseAJourTitre();
  }

  void document1_insertUpdate(DocumentEvent documentEvent) {
    //texte source modifiï¿½
    modifie=true;
    miseAJourTitre();
  }

  void document1_removeUpdate(DocumentEvent documentEvent) {
    //texte source modifiï¿½
    modifie=true;
    miseAJourTitre();
  }

  void this_windowClosing(WindowEvent e) {
    //JOptionPane.showMessageDialog(null," Fermeture ");
    /*if (! modifie){
      System.exit(0);
    }else{
      int value = JOptionPane.showConfirmDialog(this, "Enregistrer les modifications ?",
          "Editeur de texte",
          JOptionPane.YES_NO_CANCEL_OPTION);
      switch (value){
      case JOptionPane.YES_OPTION:
         enregistrer();break;
      case JOptionPane.NO_OPTION:
         //System.exit(0);
         break;
      case JOptionPane.CANCEL_OPTION:
      default:
        break;
      }
    }*/
  }

  //met ï¿½ jour la barre de titre de l'application pour afficher le nom
  //du fichier et son ï¿½tat de modification/enregistrement.
  void miseAJourTitre(){
    String titre;
    if (fichierCourant==null) {
      //on constitue le nom "sans titre"
      titre="Sans titre";
    }else{
      titre=fichierCourant;
    };
    //ajoute une * devant si le fichier est "sale"
    if (modifie) {
      titre="*"+titre;
    };
    titre= "nilNovi Objet - Fichier "+titre;
    this.setTitle(titre);
  }

  //void jButton5_actionPerformed(ActionEvent e) throws IOException {
  void jButton5_actionPerformed(ActionEvent e) {
    //excution du code objet
    //dans cette version (pas de compilateur)
    //le code exï¿½cutï¿½ est celui qui est produit manuellement
    // dans le fichier "interpreteur.java
    inter.interpreter();

  }

  Integer lireInteger(String s){
    String val="";
    try {
      val=JOptionPane.showInputDialog(s);
      return new Integer(val);
    }catch (NumberFormatException e) {
      Toolkit.getDefaultToolkit().beep(); //bip si erreur
      //production du message d'erreur et "bouclage" par appel rï¿½cursif :
      return lireInteger(val+" n'est pas un entier correct, entrez une nouvelle valeur :");
    }
  }

  void jTextArea1_mouseEntered(MouseEvent e) {
    jTextArea1.setSelectionColor(Color.black);
  }

  void jTextArea1_mousePressed(MouseEvent e) {
    jTextArea1.setSelectionColor(Color.black);
  }

  void jButton6_actionPerformed(ActionEvent e) {
    JOptionPane.showMessageDialog(this,"Pas ï¿½ pas : option indisponible pour le moment\n\n");
  }

  void jButton7_actionPerformed(ActionEvent e) {
    /*Affichage du code dï¿½sassemblï¿½
    Le code affichï¿½ est celui produit "manuellement"
    par appel au point de gï¿½nï¿½ration afficherIdent (cf afficheTI)*/
    inter.desassembler();

  }

}

// classes produites par JBuilder
// pour rï¿½agir aux ï¿½vï¿½nements

class Cadre1_jMenuFileExit_ActionAdapter implements ActionListener {
  Cadre1 adaptee;

  Cadre1_jMenuFileExit_ActionAdapter(Cadre1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuFileExit_actionPerformed(e);
  }
}

class Cadre1_jMenuHelpAbout_ActionAdapter implements ActionListener {
  Cadre1 adaptee;

  Cadre1_jMenuHelpAbout_ActionAdapter(Cadre1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuHelpAbout_actionPerformed(e);
  }
}

class Cadre1_jButton1_actionAdapter implements java.awt.event.ActionListener {
  Cadre1 adaptee;

  Cadre1_jButton1_actionAdapter(Cadre1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class Cadre1_jButton4_actionAdapter implements java.awt.event.ActionListener {
  Cadre1 adaptee;

  Cadre1_jButton4_actionAdapter(Cadre1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e){
    adaptee.jButton4_actionPerformed(e);
  }
}

class Cadre1_jButton2_actionAdapter implements java.awt.event.ActionListener {
  Cadre1 adaptee;

  Cadre1_jButton2_actionAdapter(Cadre1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class Cadre1_jButton3_actionAdapter implements java.awt.event.ActionListener {
  Cadre1 adaptee;

  Cadre1_jButton3_actionAdapter(Cadre1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}

class Cadre1_document1_documentAdapter implements javax.swing.event.DocumentListener {
  Cadre1 adaptee;

  Cadre1_document1_documentAdapter(Cadre1 adaptee) {
    this.adaptee = adaptee;
  }
  public void insertUpdate(DocumentEvent documentEvent) {
    adaptee.document1_insertUpdate(documentEvent);
  }
  public void removeUpdate(DocumentEvent documentEvent) {
    adaptee.document1_removeUpdate(documentEvent);
  }
  public void changedUpdate(DocumentEvent documentEvent) {
    adaptee.document1_changedUpdate(documentEvent);
  }
}

class Cadre1_this_windowAdapter extends java.awt.event.WindowAdapter {
  Cadre1 adaptee;

  Cadre1_this_windowAdapter(Cadre1 adaptee) {
    this.adaptee = adaptee;
  }
  public void windowClosing(WindowEvent e) {
    adaptee.this_windowClosing(e);
  }
}

class Cadre1_jButton5_actionAdapter implements java.awt.event.ActionListener {
  Cadre1 adaptee;

  Cadre1_jButton5_actionAdapter(Cadre1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton5_actionPerformed(e);
  }
}

class Cadre1_jTextArea1_mouseAdapter extends java.awt.event.MouseAdapter {
  Cadre1 adaptee;

  Cadre1_jTextArea1_mouseAdapter(Cadre1 adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.jTextArea1_mousePressed(e);
  }
}

class Cadre1_jTextArea1_keyAdapter extends java.awt.event.KeyAdapter {
  Cadre1 adaptee;

  Cadre1_jTextArea1_keyAdapter(Cadre1 adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jTextArea1_keyPressed(e);
  }
}

// classe permettant de filtrer les fichiers .nno
// comme fichiers ï¿½ lire ou ï¿½ ï¿½crire
class nnoFilter extends javax.swing.filechooser.FileFilter{

  public boolean accept(File f){
    return f.getName().toLowerCase().endsWith(".nno") || f.isDirectory();
  }
  public String getDescription(){
    return "ficher nilNovi (.nno)";
  }
}

class Cadre1_jButton6_actionAdapter implements java.awt.event.ActionListener {
  Cadre1 adaptee;

  Cadre1_jButton6_actionAdapter(Cadre1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton6_actionPerformed(e);
  }
}

class Cadre1_jButton7_actionAdapter implements java.awt.event.ActionListener {
  Cadre1 adaptee;

  Cadre1_jButton7_actionAdapter(Cadre1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton7_actionPerformed(e);
  }
}