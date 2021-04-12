package interpreteurNNO.instructions;

/*  Classes modélisant les instructions
*  machine NNO sans arguement
*
* 5/11/04
*/


public abstract class instInstr {
  protected byte codOp; //code opération

  public instInstr() {
  }

  public void desass() {
    toString();
  }

  public int getCodOp() {
    return codOp;
  }

  public abstract String toString();
}

