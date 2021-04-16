package analyseurLexical.uniteLexicale;

/*
* représentation des unités lexicales. Classe racine
* M. Guyomard
* 11-04-04
*/

/*
*  classe El
*		ligne
*		colonne
*		cC
*		mC
*		ch
*		afficheEL
*/

import javax.swing.*;


//public abstract class el {
public  class el {
  //classe "élément lexical"

  int noLigne;	//ligne ou est située l'élément lexical
  int noColonne;	//colonne où débute l'élément lexical
  int noPosDeb;//position linéaire où débute l'élément lexical

  public el(int noL, int noC,int nPD){
    noLigne=noL;
    noColonne=noC;
    noPosDeb=nPD;
  }//constr el

  public int ligne(){   //délivre le numéro de ligne
    return noLigne;
  }//ligne

  public int colonne(){  //délivre le numéro de colonne
    return noColonne;
  }//colonne

  public int posLineaire(){//délivre la position linéaire du 1er caractère
    return noPosDeb;
  }

  public char cC(){
    return ' ';
  }//cC

  public String mC(){
    return "";
  }//mC

  public String ch(){
    return "";
  }//ch

  public int val(){
    return -1;
  }//val

  //public abstract String toString();
  public  String toString(){
    return "";
  }

}//classe El
