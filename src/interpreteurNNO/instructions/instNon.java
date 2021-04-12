package interpreteurNNO.instructions;

/*  Classe modélisant l'instruction
*  machine NNO non
* 
* 5/11/04
*/




public class instNon extends instInstr {
  public instNon() {
    codOp=23;
  }
  public String toString(){
    return "non()";
  }
}
