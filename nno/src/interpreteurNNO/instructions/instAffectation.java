package interpreteurNNO.instructions;

/*  Classe modélisant l'instruction
*  machine affectation
* 
* 5/11/04
*/



public class instAffectation extends instInstr {
  public instAffectation() {
    codOp=6;
  }
  public String toString(){
    return "affectation()";
  }
}
