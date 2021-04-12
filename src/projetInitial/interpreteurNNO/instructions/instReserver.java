package interpreteurNNO.instructions;

/*  Classe modélisant l'instruction
*  machine NNO reserver
* 
* 5/11/04
*/




public class instReserver extends instInstr1 {
  public instReserver(int a1) {
    codOp=3;
	 arg1=a1;
  }
  public String toString(){
    return "reserver("+arg1+")";
  }
}

