package analyseurLexical.MesExceptions;


//classe exception qui délivre la position linéaire où s'est
//produite l'erreur lors de l'analyse (stx, sémantique, etc.)
public class MonException extends Exception
{
  int vv;

  public MonException(){}
  public MonException(int v)
  {
    vv=v;
  }

  public int positionLineaire(){
    return vv;
  }

}
