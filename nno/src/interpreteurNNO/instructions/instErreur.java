package interpreteurNNO.instructions;

/*  Classe modélisant l'instruction
*  machine NNO erreur
* 
* 5/11/04
*/




public class instErreur extends instInstr {
  public instErreur() {
    codOp=26;
  }
  public String toString(){
    return "erreur()";
  }
}
