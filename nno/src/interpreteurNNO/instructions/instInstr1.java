package interpreteurNNO.instructions;

/*  Classes modélisant les instructions
*  machine NNO avec 1 argument
* 
* 5/11/04
*/


public abstract class instInstr1 extends instInstr {
  protected int arg1; 
  
  public instInstr1() {
  }
  
  public void desass() {
    toString();
  }
  
  public int getCodOp() {
    return codOp;
  }
  
  public int getArg1() {
    return arg1;
  }
  
  public void modifArg1(int v) {
    arg1=v;
  }
  
  //abstract String affiche();
}

