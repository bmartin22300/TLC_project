package analyseurLexical.uniteLexicale;

/*
* Unité lexicale de type "Entier"
* permet de représenter les caractères entiers
* dans leur représentation binaire
* M. Guyomard
* 11-04-04
*/

/*
* classe Entier hérite de El. Méthodes propres ou redéfinies :
*   val
*   afficheEL?????
*/

import javax.swing.*;


public class entier extends el {
  //classe pour les El qui sont des entiers

  private int valeur; //valeur de l'entier

  public entier(int noL, int noC, int val, int nPD){
    super(noL,noC,nPD);
    valeur=val;
  }//constr entier

  public int val(){
    return valeur;
  }//val

  public String toString(){
    return("l : "+noLigne+" c : "+noColonne+"/ent/"+valeur+"\n");
  }//toString

}//classe Entier
