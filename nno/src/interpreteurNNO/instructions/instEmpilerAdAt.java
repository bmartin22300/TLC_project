package interpreteurNNO.instructions;

/*  Classe modélisant l'instruction
*  machine 	adresseElement
* 
* 5/11/04
*/




public class instEmpilerAdAt extends instInstr1 {
  public instEmpilerAdAt(int a1) {
    codOp=29;
	 arg1=a1;
  }
  public String toString(){
    return "empilerAdAt("+arg1+")";
  }
}

