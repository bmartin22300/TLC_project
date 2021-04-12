package analyseurLexical.uniteLexicale;

/*
* Unité lexicale de type "motCle"
* permet de représenter mots clés nNO
* M. Guyomard
* 11-04-04
*/

/*
* classe MotCle hérite de el. Méthodes propres ou redéfinies :
    mC
    toString
*/

 import javax.swing.*;



public class motCle extends el {
  //classe pour les El qui sont des mots clés

  private String keyW; //la chaîne constituant le mot clé

  public motCle(int noL, int noC, String cd, int nPD){
    //nol : numéro de ligne
    //noC : numéro de colonne
    //cd : la chaine représentant le mot clé
    //nPD : numéro linéaire
    super(noL,noC,nPD);
    keyW=cd.intern();
  }//constr motCle

  public String mC(){
    return keyW;
  }//mC

  public String toString(){
    return("l : "+noLigne+" c : "+noColonne+"/MC/"+keyW+"\n");
  }//toString

}//classe MotCle
