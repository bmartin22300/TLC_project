package interpreteurNNO.instructions;

/*  Classe modélisant l'instruction
*  machine NNO tra
* 
* 5/11/04
*/




public class instTra extends instInstr1 {
  public instTra(int a1) {
    codOp=24;
	 arg1=a1;
  }
  public String toString(){
    return "tra("+arg1+")";
  }
}

