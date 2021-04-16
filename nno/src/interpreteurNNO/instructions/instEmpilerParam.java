package interpreteurNNO.instructions;

/*  Classe modélisant l'instruction
*  machine NNO empilerParam
* 
* 5/11/04
*/




public class instEmpilerParam extends instInstr1 {
  public instEmpilerParam(int a1) {
    codOp=34;
	 arg1=a1;
  }
  public String toString(){
    return "empilerParam("+arg1+")";
  }
}

