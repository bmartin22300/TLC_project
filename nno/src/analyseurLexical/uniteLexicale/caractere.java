package analyseurLexical.uniteLexicale;

/*
* Unité lexicale de type "Caractere".
* Permet de représenter les caractères autres que les
* chiffres et les lettres
* M. Guyomard
* 11-04-04
*/

/*
* classe Caractere hérite de El. Méthodes propres ou redéfinies :
*		cC
*		toString
*/

import javax.swing.*;


public class caractere extends el {
  //classe pour les El qui sont des caractères

  private char codeCar; //code ascii du caractère

  public caractere(int noL, int noC, char cC, int nPD){
    super(noL,noC,nPD);
    codeCar=cC;
  }//constr caractere

  public char cC(){
    return codeCar;
  }//codeCar

  public String toString(){
    return("l : "+noLigne+" c : "+noColonne+"/car/"+codeCar+"\n");
  }//toString

}//classe Caractere
