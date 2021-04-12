package interpreteurNNO.instructions;

/*  Classe modélisant l'instructions
*  machine NNO finProg
* 
* 5/11/04
*/



public class instFinProg extends instInstr {
  public instFinProg() {
    codOp=2;
  }
  public String toString(){
    return "finProg()";
  }
}
