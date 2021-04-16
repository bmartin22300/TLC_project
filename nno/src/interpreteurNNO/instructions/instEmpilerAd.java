package interpreteurNNO.instructions;

/*  Classe modélisant l'instruction
*  machine NNO empilerAd
* 
* 5/11/04
*/




public class instEmpilerAd extends instInstr1 {
  public instEmpilerAd(int a1) {
    codOp=5;
	 arg1=a1;
  }
  public String toString(){
    return "empilerAd("+arg1+")";
  }
}

