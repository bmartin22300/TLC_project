package interpreteurNNO.instructions;

/*  Classe modélisant l'instruction
*  machine NNO diff
* 
* 5/11/04
*/




public class instDiff extends instInstr {
  public instDiff() {
    codOp=16;
  }
  public String toString(){
    return "diff()";
  }
}

