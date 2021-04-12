package interpreteurNNO.instructions;

/*  Classe modélisant l'instruction
*  machine NNO moins
* 
* 5/11/04
*/




public class instMoins extends instInstr {
  public instMoins() {
    codOp=10;
  }
  public String toString(){
    return "moins()";
  }
}
