package analyseurSyntaxique;

/*
* analyse syntaxique (de type LL(1)) d'un programme nNO.
* Cet analyseur est spécifié dans le document de projet
* (grammaire et diagrammes syntaxique)
* l'analyseur est (initialement) agrémenté de points de génération
* en provenance du package "pointsDeGeneration"
* pour afficher les identificateurs de classes
* et de variables globales. Ceci en guise
* d'exemple. Les lignes contenant du code permettant
* de prendre en compte ces points de génération sont
* notés 						//PG
* M. Guyomard
* 11-04-04
*/

/*  classe AnalyseurLL1
*     parser
*     (les autres méthodes ne sont pas publiques)
*/




import analyseurLexical.*;
import analyseurLexical.MesExceptions.*;

import javax.swing.*;

import ptGen.*;


public class analyseurLL1{

  anaLex aL;
  PointsGeneration aT;							//PG
  JTextArea jTextArea2;
  //jTextArea2 peut être utilisé pour
  //afficher du texte dans la fenêtre SO
  //comme par exemple par :
  //jTextArea2.append("passe par ici\n");


  public analyseurLL1(anaLex al,PointsGeneration at,JTextArea jTa){
    aL=al;
    aT=at;
    jTextArea2=jTa;

  }

  public void parser() throws MonException {
    aL.initLire();
    programme();
  }

  /*
  * procedures de l'analyseur : :
  */

  // 1 programme
  void programme() throws MonException {
    aT.debut(aL);                                 //PG
    specifProgPrinc();
    aL.accepteMotCle("is");
    corpsProgPrinc();
    if (!aL.estFel()){
      aL.erreur(13,"");
    };
    aT.fin();                                     //PG (affiche le contenu de la TDI)
    aT.genCode();                                 //PG qui génère du code
  }//programme

  // 2 corpsProgPrinc
  void corpsProgPrinc() throws MonException {
    if (! aL.estMotCle("begin")){
      partieDecla();
    };
    aL.accepteMotCle("begin");
    if (! aL.estMotCle("end")){
      suiteInstrNonVide();
    };
    aL.accepteMotCle("end");
    aL.accepteCaractere('.');
  }//corpsProgPrinc

  // 3 specifProgPrinc
  void specifProgPrinc() throws MonException {
    aL.accepteMotCle("procedure");
    aL.accepteIdentificateur();
  }//specifProgPrinc

  // 4 partieDecla
  void partieDecla() throws MonException {
    if (aL.estMotCle("type")){
      listeDeclaClass();
      if (! aL.estMotCle("begin")){
        aT.flagVarGloOn();							//PG
        listeDeclaVar();
        aT.flagVarGloOf();							//PG
      }
    }else{
      aT.flagVarGloOn();							//PG
      listeDeclaVar();
      aT.flagVarGloOf();							//PG
    }
  }//partieDecla

  // 5 listeDeclaClass
  void listeDeclaClass() throws MonException {
    declaClass();
    while(aL.estMotCle("type")){
      declaClass();
    }
  }//listeDeclaClass

  // 6 declaClass
  void declaClass() throws MonException {
    aL.accepteMotCle("type");
    aT.creerClasse(aL.getUl().ch());        //PG
    aL.accepteIdentificateur();
    aL.accepteMotCle("is");
    aL.accepteMotCle("class");
    if (aL.estCaractere('(')){
      heritage();
    };
    if (! aL.estMotCle("interface")){
      listeChamps();
    };
    aL.accepteMotCle("interface");
    interfaceNNO();
    aL.accepteMotCle("implementation");
    implementation();
    aL.accepteMotCle("end");
    aL.accepteCaractere(';');
  }//declaClass

  // 7 heritage
  void heritage() throws MonException {
    aL.accepteCaractere('(');
    aL.accepteIdentificateur();
    aL.accepteCaractere(')');
  }//heritage

  // 8 interfaceNNO
  void interfaceNNO() throws MonException {
    listeSpecifMethodes();
  }//interfaceNNO

  // 9 listeChamps
  void listeChamps() throws MonException {
    listeDeclaVar();
  }//listeChamps

  // 10 listeSpecifMethodes
  void listeSpecifMethodes() throws MonException {
    specifMethode();
    while (aL.estMotCle("procedure") || aL.estMotCle("function") || aL.estMotCle("constructor")){
      specifMethode();
    }
  }//listeSpecifMethode

  // 11 specifMethode
  void specifMethode() throws MonException {
    if(aL.estMotCle("procedure")){
      specifMethodeProc();
    }else if(aL.estMotCle("function")){
      specifMethodeFonct();
    }else{
      //constructeur
      specifMethodeConstr();
    };
    aL.accepteCaractere(';');
  }//specifMethode

  // 12 specifMethodeProc
  void specifMethodeProc() throws MonException {
    aL.accepteMotCle("procedure");
    aL.accepteIdentificateur();
    partieFormelle();
  }//specifMethodeProc

  // 13 specifMethodeFonct
  void specifMethodeFonct() throws MonException {
    aL.accepteMotCle("function");
    aL.accepteIdentificateur();
    partieFormelle();
    aL.accepteMotCle("return");
    aL.accepteIdentificateur();
  }//specifMethodeFonct

  // 14 specifMethodeConstr
  void specifMethodeConstr() throws MonException {
    aL.accepteMotCle("constructor");
    aL.accepteIdentificateur();
    partieFormelle();
  }//specifMethodeConstr

  // 15 implementation
  void implementation() throws MonException {
    operation();
    while (aL.estMotCle("procedure") || aL.estMotCle("function") || aL.estMotCle("constructor")){
      operation();
    }
  }//implementation

  // 16 operation
  void operation() throws MonException {
    if(aL.estMotCle("procedure")){
      procedure();
    }else if(aL.estMotCle("function")){
      fonction();
    }else{
      //constructeur
      constructeur();
    }
    aL.accepteCaractere(';');
  }//operation

  // 17 constructeur
  void constructeur() throws MonException {
    aL.accepteMotCle("constructor");
    aL.accepteIdentificateur();
    aL.accepteMotCle("is");
    corpsProc();
  }//constructeur

  // 18 procedure
  void procedure() throws MonException {
    aL.accepteMotCle("procedure");
    aL.accepteIdentificateur();
    aL.accepteMotCle("is");
    corpsProc();
  }//procedure

  // 19 fonction
  void fonction() throws MonException {
   aL.accepteMotCle("function");
   aL.accepteIdentificateur();
   aL.accepteMotCle("is");
   corpsFonct();																			//+++
  }//fonction

  // 20 corpsFonct
  void corpsFonct() throws MonException {
    if (! aL.estMotCle("begin")){
      partieDeclaProc();
    };
    aL.accepteMotCle("begin");
    suiteInstrNonVide();
    aL.accepteMotCle("end");
  }//corpsFonct

  // 21 partieFormelle
  void partieFormelle() throws MonException {
    aL.accepteCaractere('(');
    if (!aL.estCaractere(')')){
      listeSpecifFormelle();
    };
    aL.accepteCaractere(')');
  }//partieFormelle

  // 22 listeSpecifFormelle
  void listeSpecifFormelle() throws MonException {
    specif();
    while(aL.estCaractere(';')){
     aL.lire();//accepteCaractere(';');
     specif();
    }
  }//listeSpecifFormelle

  // 23 specif
  void specif() throws MonException {
    listeIdent();
    aL.accepteCaractere(':');
    if (aL.estMotCle("in")){
      mode();
    };
    aL.accepteIdentificateur();
  }//specif

  // 24 mode
  void mode() throws MonException {
    aL.accepteMotCle("in");
    if (aL.estMotCle("out")){
      aL.lire();
    }
  }//mode

  // 25 partieDeclaProc
  void partieDeclaProc() throws MonException {
    listeDeclaVar();
  }//parteiDeclaProc

  // 26 listeDeclaVar
  void listeDeclaVar() throws MonException {
    declaVar();
    while(aL.estIdentificateur()){
      declaVar();
    };
  }//listeDeclaVar

  // 27 declaVar
  void declaVar() throws MonException {
    listeIdent();
    aL.accepteCaractere(':');
    aL.accepteIdentificateur();
    aL.accepteCaractere(';');
  }//declaVar

  // 28 listeIdent
  void listeIdent() throws MonException {
    aT.insererIdentGlo(aL.getUl().ch()); 						 //PG
    aL.accepteIdentificateur();
    while(aL.estCaractere(',')){
      aL.lire();
      aT.insererIdentGlo(aL.getUl().ch());  						//PG
      aL.accepteIdentificateur();
    }
  }//listeIdent

  // 29 suiteInstrNonVide
  void suiteInstrNonVide() throws MonException {
    instr();
    while (aL.estCaractere(';')){
      aL.lire();
      instr();
    }
  }//suiteInstrNonVide

  // 30 instr
  void instr() throws MonException {
    if(aL.estIdentificateur()){
      aL.accepteIdentificateur();
      if(aL.estCaractere('.')){
        aL.lire();
        listeAppelFonct();
      }else if(aL.estMotCle(":=")){
        aL.accepteMotCle(":=");
        expression();
      }else{
        aL.erreur(6,". ou := ");
      }
    }else if(aL.estMotCle("while")){
      boucle();
    }else if(aL.estMotCle("if")){
      altern();
    }else if(aL.estMotCle("get") || aL.estMotCle("put")){
      es();
    }else if(aL.estMotCle("return")){
      retour();
    }else if(aL.estMotCle("error")){
      aL.accepteMotCle("error");
      aL.accepteCaractere('(');
      expression();
      aL.accepteCaractere(')');
    }else{
      aL.erreur(7,"");
    }
  }//instr

  // 31 listePe
  void listePe() throws MonException {
    expression();
    while(aL.estCaractere(',')){
      aL.lire();
      expression();
    }
  }//listePe

  // 32 expression
  void expression() throws MonException {
    exp1();
    while(aL.estMotCle("or")){
      aL.lire();
      exp1();
    }
  }//expression

  // 33 exp1
  void exp1() throws MonException {
    exp2();
    while(aL.estMotCle("and")){
      aL.lire();
      exp2();
    }
  }//exp1

  // 34 exp2
  void exp2() throws MonException{
    //la distinction entre les opérateurs = et /= et les autres n'est pas
    //nécessaire syntaxiquement. Elle l'est sémantiquement puisque = et /=
    //sont applicables à tous types. les autres ne sont applicables qu'aux entiers
    exp3();
    if (aL.estCaractere('=') || aL.estMotCle("/=")) {
      opRel();
      exp3();
	}else if ( aL.estCaractere('<') ||aL.estCaractere('>') || aL.estMotCle("<=") || aL.estMotCle(">=") ) {
      opRel();
      exp3();
	}
  }//exp2

  // 35 opRel
  void opRel() throws MonException {
    if(aL.estCaractere('=')){
      aL.lire();
    }else if (aL.estCaractere('<')){
      aL.lire();
    }else if (aL.estCaractere('>')) {
      aL.lire();
    }else if (aL.estMotCle("/=")) {
      aL.lire();
    }else if (aL.estMotCle("<=")) {
      aL.lire();
    }else if (aL.estMotCle(">=")) {
      aL.lire();
    }else{
      aL.erreur(8,"");
    }
  }//opRel

  // 36 exp3
  void exp3() throws MonException {
    exp4();
    while(aL.estCaractere('+') || aL.estCaractere('-')){
      opAd();
      exp4();
    }
  }//exp3

  // 37 opAd
  void opAd() throws MonException {
    if(aL.estCaractere('+')) {
      aL.lire();
    }else if (aL.estCaractere('-')){
      aL.lire();
    }else{
      aL.erreur(9,"");
    }
  }//opAd

  // 38 exp4
  void exp4() throws MonException {
    prim();
    while(aL.estCaractere('*') || aL.estCaractere('/')){
      opMult();
      prim();
    }
  }//exp4

  // 39 opMult
  void opMult() throws MonException {
     if(aL.estCaractere('*')){
       aL.lire();
     }else if (aL.estCaractere('/')){
       aL.lire();
     }else{
       aL.erreur(10,"");//redondant
     }
  }//opMult

  //40 prim
  void prim() throws MonException {
    if(aL.estCaractere('+') || aL.estCaractere('-') || aL.estMotCle("not")) {
      opUnaire();
    };
    elemPrim();
  }

  //41 opUnaire
  void opUnaire() throws MonException {
    if (aL.estCaractere('+')){
      aL.lire();
    }else if (aL.estCaractere('-')) {
      aL.lire();
    }else if (aL.estMotCle("not")) {
      aL.lire();
    }else {
      aL.erreur(14,"");
    }
  }//opUnaire

  // 42 elemPrim
  void elemPrim() throws MonException {
    if (aL.estEntier() || aL.estMotCle("true") || aL.estMotCle("false") || aL.estMotCle("nil")){
      valeur();
    }else if(aL.estCaractere('(')){
      aL.accepteCaractere('(');//redondant
      expression();
      aL.accepteCaractere(')');
    }else if (aL.estIdentificateur()){
      aL.accepteIdentificateur();
      if (aL.estCaractere('.')){
        aL.accepteCaractere('.');
        listeAppelFonct();
      }
    }else{
      aL.erreur(36,"");
    }
  }//elemPrim

  // 43 listeAppelFonct
  void listeAppelFonct() throws MonException {
    appelFonct();
    while(aL.estCaractere('.')){
      aL.accepteCaractere('.');
      appelFonct();
    }
  }//listeAppelFonct

  // 44 appelFonct
  void appelFonct() throws MonException {
    aL.accepteIdentificateur();
    aL.accepteCaractere('(');
    if (!aL.estCaractere(')')){
      listePe();
    }
    aL.accepteCaractere(')');
  }//appelFonct

  // 45 valeur
  void valeur() throws MonException {
    if (aL.estEntier()){
      aL.lire();
    }else if(aL.estMotCle("true")) {
      aL.lire();
    }else if(aL.estMotCle("false")){
      aL.lire();
    }else if(aL.estMotCle("nil")){
      aL.lire();
    }else{
      aL.erreur(11,"");
    }
  }//valeur

  // 46 es
  void es() throws MonException {
    if (aL.estMotCle("get")){
      aL.lire();
      aL.accepteCaractere('(');
      aL.accepteIdentificateur();
      aL.accepteCaractere(')');
    }else if(aL.estMotCle("put")){
      aL.lire();
      aL.accepteCaractere('(');
      expression();
      aL.accepteCaractere(')');
    }else{
      aL.erreur(13,"");
    }
  }//es

  // 47	boucle
  void boucle() throws MonException {
    aL.accepteMotCle("while");
    expression();
    aL.accepteMotCle("loop");
    suiteInstrNonVide();
    aL.accepteMotCle("end");
  }//boucle

  // 48	altern
  void altern() throws MonException {
    aL.accepteMotCle("if");
    expression();
    aL.accepteMotCle("then");
    suiteInstrNonVide();
    if(aL.estMotCle("else")){
      aL.lire();
      suiteInstrNonVide();
    };
    aL.accepteMotCle("end");
  }//altern

  // 49	retour
  void retour() throws MonException {
    aL.accepteMotCle("return");
    expression();
  }//retour

  // 50 corpsProc
  void corpsProc() throws MonException {
    if (! aL.estMotCle("begin")){
      partieDeclaProc();
    };
    aL.accepteMotCle("begin");
    if (! aL.estMotCle("end")){
      suiteInstrNonVide();
    }
    aL.accepteMotCle("end");
  }//corpsProc



}
