package interpreteurNNO.instructions;

/*  Classe modélisant l'instruction
*  machine NNO get
* 
* 5/11/04
*/




public class instGet extends instInstr {
  public instGet() {
    codOp=8;
  }
  public String toString(){
    return "get()";
  }
}
