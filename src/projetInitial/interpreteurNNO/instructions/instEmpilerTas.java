package interpreteurNNO.instructions;

/*  Classe modélisant l'instruction
*  machine NNO empilerTas
* 
* 5/11/04
*/




public class instEmpilerTas extends instInstr1 {
  public instEmpilerTas(int a1) {
    codOp=27;
	 arg1=a1;
  }
  public String toString(){
    return "empilerTas("+arg1+")";
  }
}

