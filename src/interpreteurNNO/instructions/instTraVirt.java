package interpreteurNNO.instructions;

/*  Classe modélisant l'instruction
*  machine NNO traVirt
* 
* 5/11/04
*/




public class instTraVirt extends instInstr2 {
  public instTraVirt(int a1, int a2) {
    codOp=35;
    arg1=a1;
    arg2=a2;
  }
  public String toString(){
    return "traVirt("+arg1+","+arg2+")";
  }
}

