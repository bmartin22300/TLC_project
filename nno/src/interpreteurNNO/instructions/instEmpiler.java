package interpreteurNNO.instructions;

/*  Classe modélisant l'instruction
*  machine NNO empiler
* 
* 5/11/04
*/




public class instEmpiler extends instInstr1 {
  public instEmpiler(int a1) {
    codOp=4;
	 arg1=a1;
  }
  public String toString(){
    return "empiler("+arg1+")";
  }
}

