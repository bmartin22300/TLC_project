package analyseurLexical.TableMotsCles;

/* table des mots clés nilNovi. Permet
* de savoir si une chaîne est un mot clé
* M. Guyomard
* 8/04/04
*/


/*
*	classe TableDesMotsCles
*		char rechercheCodeDepuisMc
*		String rechercheMcDepuisCode
*
*/

public class tableDesMotsCles {
  private String[] tableMc;  //table des mots clés
  private int tailleTableMc;

  public tableDesMotsCles(){
    tableMc=new String[31];

    tableMc[0]="/=".intern();
    tableMc[1]=":=".intern();
    tableMc[2]="<=".intern();
    tableMc[3]=">=".intern();
    tableMc[4]="and".intern();
    tableMc[5]="begin".intern();
    tableMc[6]="class".intern();
    tableMc[7]="constructor".intern();
    tableMc[8]="else".intern();
    tableMc[9]="end".intern();
    tableMc[10]="error".intern();
    tableMc[11]="false".intern();
    tableMc[12]="function".intern();
    tableMc[13]="get".intern();
    tableMc[14]="if".intern();
    tableMc[15]="implementation".intern();
    tableMc[16]="in".intern();
    tableMc[17]="interface".intern();
    tableMc[18]="is".intern();
    tableMc[19]="loop".intern();
    tableMc[20]="nil".intern();
    tableMc[21]="not".intern();
    tableMc[22]="or".intern();
    tableMc[23]="out".intern();
    tableMc[24]="procedure".intern();
    tableMc[25]="put".intern();
    tableMc[26]="return".intern();
    tableMc[27]="then".intern();
    tableMc[28]="true".intern();
    tableMc[29]="type".intern();
    tableMc[30]="while".intern();
    tailleTableMc=31;
  }//constr tableDesMotsCles

  public boolean rechercheMc(String ch){
    //cherche si ch est dans la table des mots clé,
    //délivre true si oui, false sinon
    //dichotomie
    int d,f;
    int m;

    d=0;
    f=tailleTableMc;
    /*INV : ch in tableMc[0..tailleTableMc[ =>
      ch in tableMc[d..f[
      0<=d<f<=tailleTableMc*/
    while (f!= d+1) {
      m=(d+f)/2;
      if (ch.compareTo(tableMc[m])<0){
        f=m;
      }else{
        d=m;
      }
    }
    return ch==tableMc[d];
  }//rechercheMc

}//TableDesMotsCles
