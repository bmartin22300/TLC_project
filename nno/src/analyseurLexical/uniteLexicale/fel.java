package analyseurLexical.uniteLexicale;


/*
* Unité lexicale de type "Fel"
* (fin de la chaine codée)
* Cette unité est produite dans l'analyse lexicale après le traitement du
* dernier caractère. Elle joue, dans l'analyse syntaxique,
* le rôle de sentinelle. Elle permet en particulier de
* déterminer s'il existe du texte après le programme analysé.
* M. Guyomard
* 11-04-04
*/

/*
* classe Fel hérite de El. Méthodes propres ou redéfinies :
*   toString
*/

import javax.swing.*;


public class fel extends el {
  //classe fin de liste unité lexicale

  public fel(int noL, int noC, int nPD){
    //nol : numéro de ligne
    //noC : numéro de colonne
    //nPD : numéro linéaire
    super(noL,noC,nPD);
  }//constr Fel

  public String toString(){
    return(" Fel \n");
  }//toString
}//classe Fel
