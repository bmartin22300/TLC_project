package ptGen;

/*
*/

/*
*  Classe AfficheTI
*    insererIdent
*    afficherIdent
*/

import analyseurLexical.*;
import java.util.*;
import javax.swing.*;
import PtGen.TListeEnt.TListeEnt;
import PtGen.TListeEnt.PListeEnt;

import java.util.*;
import analyseurLexical.MesExceptions.*;
import analyseurLexical.uniteLexicale.*;


import nno.*;
import interpreteurNNO.programmeObjetNNO.*;


public class PointsGeneration {

  JTextArea jTA;
  PListeEnt l;
  anaLex aL;
  boolean varGlo; //a vrai si on est dans la partie varGlo
  interpreteur inter;

  public PointsGeneration(javax.swing.JTextArea af,interpreteur nouvInter){
    jTA=af;
    varGlo=false;
    inter=nouvInter;//........................................
  }

  public void debut(anaLex nouvAL) throws MonException {//diag synt n° 1
    l= new TListeEnt();
    aL=nouvAL;
  }

  public void creerClasse(String identClasse) throws MonException {//diag synt n° 6
    int aSC=l.getAdStatEntGlo(identClasse); //exemple d'accès à l'interface PListeEnt
    if (aSC!=-1) {
      aL.erreur(15,"");
    }else if (identClasse.equals("integer") ||identClasse.equals("boolean") || identClasse.equals("this")){
      aL.erreur(34,"");
    }else{
      l.creerClasse(identClasse);
    }
  }

  public void flagVarGloOn() {//diag synt n° 4
    varGlo=true;
  }

  public void flagVarGloOf() {//diag synt n° 4
    varGlo=false;
  }

  public void insererIdentGlo(String identVarGlo) {//diag synt n° 28
    if (varGlo==true) {
      l.creerVarGlo(identVarGlo);
    }
  }

  public void fin(){//diag synt n° 1
    jTA.append(l.toString());
  }

  public void genCode(){//diag synt n° 1
    //génération de code à la fin de l'analyse syntaxique
	// 2e exemple exp arithm. version 20-05-05
    inter.debutProg();//0
	inter.empilerTas(0);//1
	inter.empilerIpTas();//2
	inter.tra(11);//3
	inter.empiler(1);//4
	inter.erreur();//5
	inter.retourConstr();//6
	inter.empiler(2);//7
	inter.erreur();//8
	inter.empiler(0);//9
	inter.retourFonct();//10
	inter.empilerTas(7);//11
	inter.empilerTas(1);//12
	inter.empilerIpTas();//13
	inter.tra(23);//14
	inter.empilerAdAt(0);//15
	inter.empilerAd(0);//16
	inter.valeurPile();//17
	inter.affectation();//18
	inter.retourConstr();//19
	inter.empilerAdAt(0);//20
	inter.valeurPile();//21
	inter.retourFonct();//22
	inter.empilerTas(20);//23
	inter.empilerTas(2);//24
	inter.empilerIpTas();//25
	inter.tra(43);//26
	inter.empiler(3);//27
	inter.erreur();//28
	inter.retourConstr();//29
	inter.empilerAd(-3);//30
	inter.valeurPile();//31
	inter.reserverBloc();//32
	inter.empilerAdAt(0);//33
	inter.valeurPile();//34
	inter.empilerAdAt(1);//35
	inter.valeurPile();//36
	inter.traVirt(1,2);//37
	inter.retourFonct();//38
	inter.empiler(4);//39
	inter.erreur();//40
	inter.empiler(0);//41
	inter.retourFonct();//42
	inter.empilerTas(30);//43
	inter.empilerTas(39);//44
	inter.empilerTas(2);//45
	inter.empilerIpTas();//46
	inter.tra(67);//47
	inter.empilerAdAt(0);//48
	inter.empilerAd(0);//49
	inter.valeurPile();//50
	inter.affectation();//51
	inter.empilerAdAt(1);//52
	inter.empilerAd(1);//53
	inter.valeurPile();//54
	inter.affectation();//55
	inter.retourConstr();//56
	inter.empilerAd(0);//57
	inter.valeurPile();//58
	inter.reserverBloc();//59
	inter.traVirt(0,0);//60
	inter.empilerAd(1);//61
	inter.valeurPile();//62
	inter.reserverBloc();//63
	inter.traVirt(0,0);//64
	inter.add();//65
	inter.retourFonct();//66
	inter.empilerTas(30);//67
	inter.empilerTas(57);//68
	inter.empilerTas(2);//69
	inter.empilerIpTas();//70
	inter.tra(91);//71
	inter.empilerAdAt(0);//72
	inter.empilerAd(0);//73
	inter.valeurPile();//74
	inter.affectation();//75
	inter.empilerAdAt(1);//76
	inter.empilerAd(1);//77
	inter.valeurPile();//78
	inter.affectation();//79
	inter.retourConstr();//80
	inter.empilerAd(0);//81
	inter.valeurPile();//82
	inter.reserverBloc();//83
	inter.traVirt(0,0);//84
	inter.empilerAd(1);//85
	inter.valeurPile();//86
	inter.reserverBloc();//87
	inter.traVirt(0,0);//88
	inter.mult();//89
	inter.retourFonct();//90
	inter.empilerTas(30);//91
	inter.empilerTas(81);//92
	inter.reserver(7);//93
	inter.empiler(11);//94
	inter.get();//95
	inter.empiler(5);//96
	inter.empiler(1);//97
	inter.valeurPile();//98
	inter.reserverBloc();//99
	inter.empiler(11);//100
	inter.valeurPile();//101
	inter.traConstr(15,1);//102
	inter.affectation();//103
	inter.empiler(6);//104
	inter.empiler(1);//105
	inter.valeurPile();//106
	inter.reserverBloc();//107
	inter.empiler(2);//108
	inter.traConstr(15,1);//109
	inter.affectation();//110
	inter.empiler(8);//111
	inter.empiler(4);//112
	inter.valeurPile();//113
	inter.reserverBloc();//114
	inter.empiler(5);//115
	inter.valeurPile();//116
	inter.empiler(6);//117
	inter.valeurPile();//118
	inter.traConstr(72,2);//119
	inter.affectation();//120
	inter.empiler(7);//121
	inter.empiler(1);//122
	inter.valeurPile();//123
	inter.reserverBloc();//124
	inter.empiler(1);//125
	inter.traConstr(15,1);//126
	inter.affectation();//127
	inter.empiler(9);//128
	inter.empiler(3);//129
	inter.valeurPile();//130
	inter.reserverBloc();//131
	inter.empiler(7);//132
	inter.valeurPile();//133
	inter.empiler(8);//134
	inter.valeurPile();//135
	inter.traConstr(48,2);//136
	inter.affectation();//137
	inter.empiler(10);//138
	inter.empiler(9);//139
	inter.valeurPile();//140
	inter.affectation();//141
	inter.empiler(10);//142
	inter.valeurPile();//143
	inter.reserverBloc();//144
	inter.traVirt(0,0);//145
	inter.put();//146
	inter.finProg();//147
  }

}
