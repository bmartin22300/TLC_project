package analyseurSynthaxique.MesExceptions;

public class VarGloExisteDejaException extends Exception{

	//17/05/2021
	//var glo doublement declaree
	
	public String identificateur;
	  public VarGloExisteDejaException(){}
	  public VarGloExisteDejaException(String identificateur)
	  {
		  this.identificateur=identificateur;
	  }
}
