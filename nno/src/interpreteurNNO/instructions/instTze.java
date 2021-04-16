package interpreteurNNO.instructions;

/*  Classe modélisant l'instruction
*  machine NNO tze
* 
* 5/11/04
*/




public class instTze extends instInstr1 {
  public instTze(int a1) {
    codOp=25;
	 arg1=a1;
  }
  public String toString(){
    return "tze("+arg1+")";
  }
}

