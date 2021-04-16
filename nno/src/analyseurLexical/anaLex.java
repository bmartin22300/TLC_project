/*
* analyseur lexical, permet de créer une chaîne codée
* à partir du texte source NNO
* et de l'exploiter dans l'analyseur syntaxique
* M. Guyomard
* 9-04-04
*/

/* classe anaLex
*  traiterToutesLesLignes
*  afficherChaineCodee
*  initLire
*  lire
*  el getUl
*  boolean estIdentificateur
*  boolean estCaractere
*  boolean estEntier
*  boolean estMotCle
*  boolean estFel
*  accepteCaractere
*  accepteMotCle
*  accepteEntier
*  accepteIdentificateur
*  accepteFel
*  erreur
*  classe analyseLigne
*  traiterLigne  (l'analyseur lexical pour une ligne)
*  classe parcoursSequentiel
*  lireSvt
*/

package analyseurLexical;

import javax.swing.*;
import java.util.*;
import analyseurLexical.TableMotsCles.*;
import analyseurLexical.uniteLexicale.*;
import analyseurLexical.MesExceptions.*;


/**
 * <p>Titre : nno</p>
 * <p>Description : nilNovi Objet</p>
 * <p>Copyright : Copyright (c) 2003</p>
 * <p>Société : LSI2</p>
 * @author Enssat
 * @version 1.0
 */

public class anaLex {
  private LinkedList chaineCodee;
  private tableDesMotsCles tableMc;
  JTextArea jTextArea1;
  JTextArea jTextArea2;
  private ListIterator i; //itérateur pour le parcours de la chaine codée
  private el ul;	//unité lexicale lors du pacours de la chaine codée


  public anaLex(JTextArea jTa1, JTextArea jTa2) {
    jTextArea1=jTa1;
    jTextArea2=jTa2;
    chaineCodee= new LinkedList();
    tableMc=new tableDesMotsCles();
  }//anaLex

  public void traiterToutesLesLignes () {
    //analyse lexicale de tout le texte
    String ligne=jTextArea1.getText();
    analyseLigne al=new analyseLigne(chaineCodee,tableMc);//pour l'ana lex d'une ligne
    al.traiterLigne(ligne,chaineCodee);
  }//traiterToutesLesLignes

  public void afficherChaineCodee(){
    ListIterator i= chaineCodee.listIterator(0);
    while (i.hasNext()) {
      el k=(el)i.next();
      jTextArea2.append(k.toString());
    }
  }//afficherChaineCodee

  public void initLire(){
    //initialise la lecture de la chaîne codée
    i=chaineCodee.listIterator(0);
    ul =(el)i.next();  //initialise l'unité lexicale avec le premier elément de la chaine C (qui existe)
  }//initLire

  public void lire(){
    //poursuit la lecture de la chaine codée
    ul=(el)i.next();
  }//lire

  public el getUl(){
    //délivre l'unité lexicale courante
    return ul;
  }//getUl

  public boolean estIdentificateur(){
    //fonction qui détermine si l'unité courante est un identificateur
    return ul instanceof identificateur;
  }//estIdent

  public boolean estCaractere(char c){
    //fonction qui détermine si l'unité courante est le caractère passé en paramètre
    if (ul instanceof caractere) {
      return (ul.cC()==c);
    }else{
      return false;
    }
  }//estCaractere

  public boolean estEntier(){
    //fonction qui détermine si l'unité courante est un entier
    return ul instanceof entier;
  }//estCaractere

  public boolean estMotCle(String mc){
    //fonction qui détermine si l'unité courante est le mot clé mc
    if (ul instanceof motCle) {
      return (ul.mC()==mc);
    }else{
      return false;
    }
  }

  public boolean estFel(){
    //fonction qui détermine si l'unité courante est la marque de fin de chaîne codée
    return ul instanceof fel;
  }//estCaractere

  public void accepteCaractere(char c) throws MonException{
    //fonction qui accepte une occurence du caractère c
    if (estCaractere(c)) {
      lire();
    }else{
      erreur(1,new String(new StringBuffer().append(c)));
    }
  }//accepteCaractere

  public void accepteMotCle(String mc) throws MonException{
    //fonction qui accepte une occurence du caractère c
    if (estMotCle(mc)) {
      lire();
    }else{
      erreur(2,mc);
    }
  }//accepteCaractere

  public void accepteEntier() throws MonException {
    //fonction qui accepte une occurence d'un entier
    if (estEntier()) {
      lire();
    }else{
      erreur(3,"");
    }
  }//accepteEntier

  public void accepteIdentificateur() throws MonException {
    //fonction qui accepte une occurence d'un identificateur
    if (estIdentificateur()) {
      lire();
    }else{
      erreur(4,"");
    }
  }//accepteIdentificateur

  public void accepteFel() throws MonException {
    //fonction qui accepte une occurence d'une marque de fin de chaine codée
    if (! estFel()) {
      erreur(5,"");
    }
  }//accepteFel

  public void erreur(int noE,String mess) throws MonException{
    //affiche une erreur et lève une exception MonExcpetion
    // avec comme paramètre l'indice de début de la chaine
    //qui provoque l'erreur. Ceci permet de surligner
    // le début de l'erreur dans l'éditeur
    el ul=getUl();
    jTextArea2.append("L"+ul.ligne()+" C"+ul.colonne()+" ");
    switch (noE){
    case 1:{jTextArea2.append("Caractere "+mess+" attendu\n");break;}
    case 2:{jTextArea2.append("Erreur mot clé, ou mot clé "+mess+" attendu\n");break;}
    case 3:{jTextArea2.append("Entier attendu\n");break;}
    case 4:{jTextArea2.append("Identificateur attendu\n");break;}
    case 5:{jTextArea2.append("Fin de programme attendue\n");break;}
    case 6:{jTextArea2.append("\n");break;}
    case 7:{jTextArea2.append("Instruction incorrecte\n");break;}
    case 8:{jTextArea2.append("Opérateur relationnel attendu\n");break;}
    case 9:{jTextArea2.append("Opérateur additif attendu\n");break;}
    case 10:{jTextArea2.append("Opérateur multiplicatif attendu\n");break;}
    case 11:{jTextArea2.append("Valeur attendue\n");break;}
    case 12:{jTextArea2.append("Instruction d'entrée/sortie attendue\n");break;}
    case 13:{jTextArea2.append("Il existe du texte après le programme...\n");break;}
    case 14:{jTextArea2.append("Opérateur unaire attendu\n");break;}
    case 36:{jTextArea2.append("Expression incorrecte\n");break;}
    };
    throw new MonException(ul.posLineaire());
    }//erreur
  }

// classe pour l'analyse d'une ligne de texte.
// Dans la version avec interface utilisateur
// est utilisé pour tout le texte
class analyseLigne{
  private StringBuffer lT;
  private LinkedList ll;
  private tableDesMotsCles tableMc;

  public analyseLigne(LinkedList cc,tableDesMotsCles table){
    ll=cc;
    tableMc=table;
  }//constr analyseLigne

  public void traiterLigne(String l,LinkedList chaineCodee){
    int noL;
    lT=new StringBuffer(l+(char) 0);
    parcoursSequentiel ps=new parcoursSequentiel(lT);
    char car;
    int noC;
    int noColDeb;  //Numéro de colonne du début de l'unité lexicale
    el unLex;
    int noPosDeb=0;  //position LINEAIRE du début de l'élément

    //expression régulière + points de production des unités lexicales
    //voir poly projet pour la spécification

    noL=1;
    noC=1;
    noColDeb=1;
    car=ps.lireSvt();
    while (car!=ps.ff){
      noPosDeb=ps.getIndice();
      if (car=='/') {				//début commentaire, /=, ou autre?
        noColDeb=noC;
        car=ps.lireSvt();noC=noC+1;
        if (car=='/') {				//début commentaire
          car=ps.lireSvt();noC=noC+1;  //on élimine le reste de la ligne
          while (car!=(char)10) {
            car=ps.lireSvt();noC=noC+1;
          }
        }else if (car=='=') {		// /=
          ll.add(new motCle(noL,noColDeb,"/=".intern(),noPosDeb));
          car=ps.lireSvt();noC=noC+1;
        }else{						// autre
          ll.add(new caractere(noL,noC,'/',noPosDeb));
        }
      }else if ((car>='0') && (car<='9')) {//début nombre
        noColDeb=noC;
        int nb;						//représentation binaire du nombre lu
        nb=(int) car - (int) '0';  //schéma de Horner
        car=ps.lireSvt();noC=noC+1;
        while ((car>='0') && (car<='9')) {
          nb=nb*10+(int) car - (int) '0'; //schéma de Horner
          car=ps.lireSvt();noC=noC+1;
        }
        ll.add(new entier(noL,noColDeb,nb,noPosDeb));
      }else if (car==' '||car==(char)9){	//espace ou tabulation : à supprimer
        car=ps.lireSvt();noC=noC+1;
      }else if (((car>='a') && (car<='z')) || ((car>='A') && (car<='Z'))) {//symbole
        noColDeb=noC;
        StringBuffer symbole=new StringBuffer();	//symbole (ident ou MC) reconnu
        symbole.append(car);
        car=ps.lireSvt();noC=noC+1;
        while (((car>='a') && (car<='z')) || ((car>='A') && (car<='Z')) || ((car>='0') && (car<='9'))) {
          symbole.append(car);
          car=ps.lireSvt();noC=noC+1;
        }
        String symb=(new String(symbole)).intern();
        boolean existe=tableMc.rechercheMc(symb);
        if (! existe) {
          //identificateur
          ll.add(new identificateur(noL,noColDeb,symb,noPosDeb));
        }else{
          //mot clé
          ll.add(new motCle(noL,noColDeb,symb,noPosDeb));
        }
      }else if (car==':') {				//symbole affectation?
        noColDeb=noC;
        car=ps.lireSvt();noC=noC+1;
        if (car=='='){					//Symbole affectaton
          ll.add(new motCle(noL,noColDeb,":=".intern(),noPosDeb));
          car=ps.lireSvt();noC=noC+1;
        }else{							//caractère ':'
          ll.add(new caractere(noL,noColDeb,':',noPosDeb));
        }
      }else if (car=='<') {				//<=?
        noColDeb=noC;
        car=ps.lireSvt();noC=noC+1;
        if (car=='='){					//Symblol <=
          ll.add(new motCle(noL,noColDeb,"<=".intern(),noPosDeb));
          car=ps.lireSvt();noC=noC+1;
        }else{							//caractère ':'
          ll.add(new caractere(noL,noColDeb,'<',noPosDeb));
        }
      }else if (car=='>') {				//>=??
        noColDeb=noC;
        car=ps.lireSvt();noC=noC+1;
        if (car=='='){					//Symbole >=
          ll.add(new motCle(noL,noColDeb,">=".intern(),noPosDeb));
          car=ps.lireSvt();noC=noC+1;
        }else{							//caractère ':'
          ll.add(new caractere(noL,noColDeb,'>',noPosDeb));
        }
      }else if (car==(char)10) { //changement de ligne
        noL=noL+1;
        noC=1;
        car=ps.lireSvt();
      }else if (car==(char)13) { //parfois (PC) après el changement de ligne
        noC=1;
        car=ps.lireSvt();
      }else{							//tous les autres
        ll.add(new caractere(noL,noC,car,noPosDeb));
        car=ps.lireSvt();noC=noC+1;
      }
    };
    chaineCodee.add(new fel(noL,noColDeb,noPosDeb));
  }//traiterLigne
}//classe analyseLigne

class parcoursSequentiel{
  private StringBuffer ln; //pour manipuler facilement la chaîne
  private int i;//indice de parcours
  public final static char ff=(char) 0;

  public parcoursSequentiel(StringBuffer s){
    ln=s;
    i=0;
  }//constr parcoursSequentiel

  public char lireSvt(){
    char rs=ln.charAt(i);
    if (i<ln.length()) {
      i=i+1;
    };
    return rs;
  }//lireSvt

  public int getIndice(){
    return i;
  }
}//classe parcoursSequentiel
