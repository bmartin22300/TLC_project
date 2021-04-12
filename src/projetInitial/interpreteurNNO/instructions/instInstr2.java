package interpreteurNNO.instructions;

/*  Classes modélisant les instructions
*  machine NNO avec 2 arguments
* 
* 5/11/04
*/


public abstract class instInstr2 extends instInstr1 {
  protected int arg2; 
  
  public instInstr2() {
  }
  
  public void desass() {
    toString();
  }
  
  public int getCodOp() {
    return codOp;
  }
  
  public int getArg2() {
    return arg2;
  }
  
  //abstract String affiche();
}

