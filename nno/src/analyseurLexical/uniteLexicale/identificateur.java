package analyseurLexical.uniteLexicale;

/*
* Unité lexicale de type "identificateur"
* permet de représenter les identificateurs (différents des
* mots clés).
* M. Guyomard
* 11-04-04
*/

/*
* classe Identificateur hérite de El. Méthodes propres ou redéfinies :
*   ch
*   toString
*/

import javax.swing.*;

public class identificateur extends el {
  //classe pour les el qui sont des identificateurs

  private String ident; //chaîne de l'identificateur

  public identificateur(int noL, int noC, String ch, int nPD){
    //nol : numéro de ligne
    //noC : numéro de colonne
    //ch : la chaine constituant l'identificateur
    //nPD : numéro linéaire
    super(noL,noC,nPD);
    ident=ch;
  }//constr Identificateur

  public String ch(){
    return ident;
  }//cC

  public String toString(){
    return("l : "+noLigne+" c : "+noColonne+"/ident/"+ident+"\n");
  }//toString

}//classe Identificateur

