package interpreteurNNO.instructions;

/*  Classe modélisant l'instruction
*  machine NNO put
* 
* 5/11/04
*/




public class instPut extends instInstr {
  public instPut() {
    codOp=9;
  }
  public String toString(){
    return "put()";
  }
}
