package interpreteurNNO.instructions;

/*  Classe modélisant l'instruction
*  machine NNO traConstr
* 
* 18/04/05
*/




public class instTraConstr extends instInstr2 {
  public instTraConstr(int a1, int a2) {
    codOp=36;
    arg1=a1;
    arg2=a2;
  }
  public String toString(){
    return "traConstr("+arg1+","+arg2+")";
  }
}

