package interpreteurNNO.instructions;

/*  Classe modélisant l'instruction
*  machine NNO egal
* 
* 5/11/04
*/




public class instEgal extends instInstr {
  public instEgal() {
    codOp=15;
  }
  public String toString(){
    return "egal()";
  }
}
