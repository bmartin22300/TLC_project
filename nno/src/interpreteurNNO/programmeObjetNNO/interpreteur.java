package interpreteurNNO.programmeObjetNNO;

import interpreteurNNO.instructions.*;
import java.io.*;
import javax.swing.*;

import java.awt.*;



/* Classe permettant
* l'interprétation d'un programme
* nilNovi Objet
*
*  M. Guyomard
*
* 5/11/04
*/


public class interpreteur {
  instInstr[] po; // programme objet
  int cE; //compteur d'emplacement, pour initialiser le po
  			 //désigne le dernier emplacement occupé
  int co; //compteur ordinal
  final int maxPo=10000; // taille max du po

  int[] pile; //pile d'exécution
  int base,ip,ipTas;//pointeur de "pile";
  final int maxPile=10000; //taille "pile"

  JTextArea jTA2;
  JTextArea jTA3;

  public interpreteur(JTextArea zoneTA2,JTextArea zoneTA3) {
    po=new instInstr[maxPo];
    cE=-1;

    pile=new int[maxPile];
    ip=-1;
    base=-2;
    ipTas=maxPile;
    jTA2=zoneTA2;
    jTA3=zoneTA3;
  }//interpreteur


  //37 méthodes pour la création d'un PO
  public void debutProg() {
    cE=cE+1;
    po[cE]=new instDebutProg();
  }//debutProg

  public void finProg() {
    cE=cE+1;
    po[cE]=new instFinProg();
  }//finProg

  public void reserver(int n) {
    cE=cE+1;
    po[cE]=new instReserver(n);
  }//reserver

  public void empiler(int val) {
    cE=cE+1;
    po[cE]=new instEmpiler(val);
  }//empiler

  public void empilerAd(int ad) {
    cE=cE+1;
    po[cE]=new instEmpilerAd(ad);
  }//empilerAd

  public void affectation() {
    cE=cE+1;
    po[cE]=new instAffectation();
  }//affectation

  public void valeurPile() {
    cE=cE+1;
    po[cE]=new instValeurPile();
  }//valeurPile

  public void get() {
    cE=cE+1;
    po[cE]=new instGet();
  }//get

  public void put() {
    cE=cE+1;
    po[cE]=new instPut();
  }//put

  public void moins() {
    cE=cE+1;
    po[cE]=new instMoins();
  }//moins

  public void sous() {
    cE=cE+1;
    po[cE]=new instSous();
  }//sous

  public void add() {
    cE=cE+1;
    po[cE]=new instAdd();
  }//add

  public void mult() {
    cE=cE+1;
    po[cE]=new instMult();
  }//mult

  public void div() {
    cE=cE+1;
    po[cE]=new instDiv();
  }//div

  public void egal() {
    cE=cE+1;
    po[cE]=new instEgal();
  }//egal

  public void diff() {
    cE=cE+1;
    po[cE]=new instDiff();
  }//diff

  public void inf() {
    cE=cE+1;
    po[cE]=new instInf();
  }//inf

  public void infeg() {
    cE=cE+1;
    po[cE]=new instInfeg();
  }//infeg

  public void sup() {
    cE=cE+1;
    po[cE]=new instSup();
  }//sup

  public void supeg() {
    cE=cE+1;
    po[cE]=new instSupeg();
  }//supeg

  public void et() {
    cE=cE+1;
    po[cE]=new instEt();
  }//et

  public void ou() {
    cE=cE+1;
    po[cE]=new instOu();
  }//ou

  public void non() {
    cE=cE+1;
    po[cE]=new instNon();
  }//non

  public void tra(int ad) {
    cE=cE+1;
    po[cE]=new instTra(ad);
  }//tra

  public void tze(int ad) {
    cE=cE+1;
    po[cE]=new instTze(ad);
  }//tze

  public void erreur() {
    cE=cE+1;
    po[cE]=new instErreur();
  }//erreur

  public void empilerTas(int val) {
    cE=cE+1;
    po[cE]= new instEmpilerTas(val);
  }//empilerTas

  public void empilerIpTas() {
    cE=cE+1;
    po[cE]=new instEmpilerIpTas();
  }//empilerIpTas

  public void empilerAdAt(int ad) {
    cE=cE+1;
    po[cE]=new instEmpilerAdAt(ad);
  }//empilerAdAt

  public void reserverBloc() {
    cE=cE+1;
    po[cE]=new instReserverBloc();
  }//reserverBloc

  public void retourConstr() {
    cE=cE+1;
    po[cE]=new instRetourConstr();
  }//retourConstr

  public void retourFonct() {
    cE=cE+1;
    po[cE]=new instRetourFonct();
  }//retourFonct

  public void retourProc() {
    cE=cE+1;
    po[cE]=new instRetourProc();
  }//retourProc

  public void empilerParam(int ad) {
    cE=cE+1;
    po[cE]=new instEmpilerParam(ad);
  }//empilerParam

  public void traVirt(int i, int nbP) {
    cE=cE+1;
    po[cE]=new instTraVirt(i,nbP);
  }//traVirt

  public void traConstr(int ad, int nbP) {
    cE=cE+1;
    po[cE]=new instTraConstr(ad,nbP);
  }//traVirt

  //délivre la valeur courante du compteur d'emplacement
  public int getValCE() {
    return cE;
  }//getValCE

  //modifie l'arg1 de l'instruction i du po
  public void modifInstr(int i, int v) {
	 ((instInstr1)po[i]).modifArg1(v);
  }

  public void interpreter() {
    jTA3.setText("");
    jTA3.append("début de l'exécution\n");
    if (cE==-1) {
      jTA3.append("pas de code objet\n");
    }else{
      int co; // compteur ordinal
      boolean pb;//problème dans l'interprétation ou non
      int codeErreur;//type de l'erreur d'interprétation
      int codopCour;//code opération courant

      codeErreur=0;//pour faire plaisir au compîlateur Java

      co=0;
      pb=false;
      codopCour=po[0].getCodOp();

      /* invariant
        */
      while (!((pb) || (codopCour==2) || (codopCour==26))) {
        //2: finProg; 26 : erreur
        switch (codopCour) {
        case 1://debutProg
          ip=-1;
          base=-2;
          ipTas=maxPile;
          co=co+1;
          break;
        case 3://reserver
          {
            int nouvIP=ip+((instInstr1)po[co]).getArg1();
            if (nouvIP>=ipTas) {
              pb=true;
              codeErreur=2;
            }else{
			     //on met tous les emplacements réservés à -1
			     // de façon à détecter les références folles
			     {
			       int nouvIPp1=nouvIP+1;//optimisation CA
			       int k;
	             k=ip+1;
			       /*inv :
			         pile[ip+1..k-1]=-1 and ip+1<=k<=nouvIP+1*/
			       while (! (k==nouvIPp1)) {
			         pile[k]=-1;
				      k=k+1;
				    }
			     };
              ip=nouvIP;
              co=co+1;
            }
          };
          break;
        case 4://empiler
          {
            int nouvIP=ip+1;
            if (nouvIP>=ipTas) {
              pb=true;
              codeErreur=2;
            }else{
              ip=nouvIP;
              pile[ip]=((instInstr1)po[co]).getArg1();
              co=co+1;
            }
          };
          break;
        case 5://empilerAd
          {
            int nouvIP=ip+1;
            if (nouvIP>=ipTas) {
              pb=true;
              codeErreur=2;
            }else{
              ip=nouvIP;
              pile[ip]=base+2+((instInstr1)po[co]).getArg1();
              co=co+1;
            }
          };
          break;
        case 6://affectation
          pile[pile[ip-1]]=pile[ip];
          ip=ip-2;
          co=co+1;
          break;
        case 7://valeurPile
          pile[ip]=pile[pile[ip]];
          co=co+1;
          break;
        case 8://get
          {
            Integer v;//valeur à lire
            v=lireInteger("Entrer un entier");
            jTA3.append(":"+v+"\n");
            pile[pile[ip]]=v.intValue();
          };
          ip=ip-1;
          co=co+1;
          break;
        case 9://put
          jTA3.append(">"+pile[ip]+"\n");
          ip=ip-1;
          co=co+1;
          break;
        case 10://moins
          pile[ip]=-pile[ip];
          co=co+1;
          break;
        case 11://sous
          pile[ip-1]=pile[ip-1]-pile[ip];
          ip=ip-1;
          co=co+1;
          break;
        case 12://add
          pile[ip-1]=pile[ip-1]+pile[ip];
          ip=ip-1;
          co=co+1;
          break;
        case 13://mult
          pile[ip-1]=pile[ip-1]*pile[ip];
          ip=ip-1;
          co=co+1;
          break;
        case 14://div
		    if (pile[ip]==0) {
			   codeErreur=3;
				pb=true;
			 }else{
            pile[ip-1]=pile[ip-1]/pile[ip];
            ip=ip-1;
            co=co+1;
			 }
          break;
        case 15://egal
          {
            int sommet;
            sommet=pile[ip];
            ip=ip-1;
            if (pile[ip]==sommet) {
              pile[ip]=1;
            }else{
              pile[ip]=0;
            };
          };
          co=co+1;
          break;
        case 16://diff
          {
            int sommet;
            sommet=pile[ip];
            ip=ip-1;
            if (pile[ip]!=sommet) {
              pile[ip]=1;
            }else{
              pile[ip]=0;
            };
          };
          co=co+1;
          break;
        case 17://inf
          {
            int sommet;
            sommet=pile[ip];
            ip=ip-1;
            if (pile[ip]<sommet) {
              pile[ip]=1;
            }else{
              pile[ip]=0;
            };
          };
          co=co+1;
          break;
        case 18://infeg
          {
            int sommet;
            sommet=pile[ip];
            ip=ip-1;
            if (pile[ip]<=sommet) {
              pile[ip]=1;
            }else{
              pile[ip]=0;
            };
          };
          co=co+1;
          break;
        case 19://sup
          {
            int sommet;
            sommet=pile[ip];
            ip=ip-1;
            if (pile[ip]>sommet) {
              pile[ip]=1;
            }else{
              pile[ip]=0;
            };
          };
          co=co+1;
          break;
        case 20://supeg
          {
            int sommet;
            sommet=pile[ip];
            ip=ip-1;
            if (pile[ip]>=sommet) {
              pile[ip]=1;
            }else{
              pile[ip]=0;
            };
          };
          co=co+1;
          break;
        case 21://et
          {
            int sommet;
            sommet=pile[ip];
            ip=ip-1;
            if ((pile[ip]==sommet) && (sommet==1)) {
              pile[ip]=1;
            }else{
              pile[ip]=0;
            };
          };
          co=co+1;
          break;
        case 22://ou
          {
            int sommet;
            sommet=pile[ip];
            ip=ip-1;
            if ((pile[ip]==1) || (sommet==1)) {
              pile[ip]=1;
            }else{
              pile[ip]=0;
            };
          };
          co=co+1;
          break;
        case 23://non
          if (pile[ip]==0) {
            pile[ip]=1;
          }else{
            pile[ip]=0;
          };
          co=co+1;
          break;
        case 24://tra
          co=((instInstr1)po[co]).getArg1();
          break;
        case 25://tze
          if (pile[ip]==0) {
            co=((instInstr1)po[co]).getArg1();
          }else{
            co=co+1;
          };
          ip=ip-1;
          break;
        case 27://empilerTas
          {
            int nouvIpTas=ipTas-1;
            if (ip>=nouvIpTas) {
              pb=true;
              codeErreur=2;
            }else{
              ipTas=nouvIpTas;
              pile[ipTas]=((instInstr1)po[co]).getArg1();
              co=co+1;
            }
          };
          break;
        case 28://empilerIpTas
          {
            int nouvIp=ip+1;
            if (nouvIp>=ipTas) {
              pb=true;
              codeErreur=2;
            }else{
              ip=nouvIp;
              pile[ip]=ipTas;
              co=co+1;
            }
          };
          break;
        case 29://empilerAdAt
          {
            int nouvIp=ip+1;
            if (nouvIp>=ipTas) {
              pb=true;
              codeErreur=2;
            }else{
              ip=nouvIp;
              pile[ip]=pile[base-1]-1-((instInstr1)po[co]).getArg1();
              co=co+1;
            }
          };
          break;
        case 30://reserverBloc
          {
            int nouvIp=ip+2;
            if (nouvIp>=ipTas) {
              pb=true;
              codeErreur=2;
            }else{
              pile[ip+1]=base;
              ip=nouvIp;
              co=co+1;
            }
          };
          break;
        case 31://retourConstr
          {
            int ar,nouvBase;
            ar=pile[base+1];
            nouvBase=pile[base];
            ip=base-1;
            base=nouvBase;
            co=ar;
          };
          break;
        case 32://retourFonct
          {
            int nouvBase,ar,v;
            nouvBase=pile[base];
            ar=pile[base+1];
            v=pile[ip];
            ip=base-1;
            pile[ip]=v;
            base=nouvBase;
            co=ar;
          };
          break;
        case 33://retourProc
          {
            int nouvBase;
            nouvBase=pile[base];
            co=pile[base+1];
            ip=base-2;
            base=nouvBase;
          };
          break;
        case 34://empilerParam
          {
            int nouvIp=ip+1;
            if (nouvIp>=ipTas) {
              pb=true;
              codeErreur=2;
            }else{
              ip=nouvIp;
              pile[ip]=pile[base+2+((instInstr1)po[co]).getArg1()];
              co=co+1;
            }
          };
          break;
        case 35://traVirt
          {
            int objet;
			   int prefixe;
			   prefixe=ip-((instInstr2)po[co]).getArg2()-2;
			   if (pile[prefixe]==-1) {
				  //référence folle :
				  pb=true;
				  codeErreur=4;
			   }else{
			     base=prefixe+1;
			     pile[prefixe+2]=co+1;
              objet=pile[prefixe];
              co=pile[pile[objet]-((instInstr2)po[co]).getArg1()-1];
			   }
          };
		    //tracer("traVirt",co);
          break;
        case 36://traConstr
		  {
			int prefixe;//adresse du paramètre classe sur lequel s'effectue l'appel
			int t,classe;//t : nombre d'attributs, classe : adresse de la classe
			prefixe=ip-((instInstr2)po[co]).getArg2()-2;
            classe=pile[prefixe];
            t=pile[classe];
			//jTA3.append("prefixe,classe,t : "+prefixe+","+classe+","+t+"\n");
			if (pile[prefixe]==-1) {
			  //référence folle :
			  pb=true;
			  codeErreur=4;
			}else{
           int nouvIpTas=ipTas-t-1;
	        if (ip>=nouvIpTas) {
             pb=true;
             codeErreur=2;
           }else{
             pile[ipTas-1]=classe;
             pile[prefixe]=ipTas-1;
             ipTas=nouvIpTas;
             base=prefixe+1;

			    pile[prefixe+2]=co+1;
             co=((instInstr2)po[co]).getArg1();
				 //jTA3.append("ipTas : "+prefixe+classe+t+"\n");
			  };
			}
		  };
		  //tracer("traConstr",co);
		  break;
        default:
          codeErreur=1;//erreur code instruction
          pb=true;
          break;
        };
        if (co>cE || co<0) {
          pb=true;
          codeErreur=0;//compteur ordinal hors limites
        }else{
         codopCour=po[co].getCodOp();
        }
      };//while
      if (pb) {
		  jTA3.append("!!!interruption de l'exécution : ");
        switch (codeErreur) {
        case 0: //compteur ordinal hors limites
          jTA3.append("compteur ordinal hors limites\n");
          break;
        case 1://code instruction incorrect
          jTA3.append("code instruction incorrect à l'adresse "+co+"\n");
          break;
        case 2://débordement de pile
          jTA3.append("débordement de pile à l'adresse "+co+"\n");
          break;
        case 3://division par 0
          jTA3.append("division par zéro à l'adresse "+co+"\n");
          break;
        case 4:
		    //l'objet sur lequel s'applique la méthode virt n'est pa créé (=-1)
          jTA3.append("objet non initialisé à l'adresse "+co+"\n");
          break;
        }
      }else if (codopCour==26) {
        jTA3.append("interruption de l'exécution, erreur n° "+pile[ip]);
      }else{
        jTA3.append("fin de l'exécution\n");
      }
    }
  }//interpreter

  void tracer(String s, int co) {
    //pour mise au point interpréteur
    jTA3.append("______________________"+s+"_________________________\n");
    int i;
    if (ipTas!=maxPile) {
      i=maxPile-1;
      /*invariant:
        pile[maxPile-1..ipTas] est affiché*/
      while (!(i==ipTas)) {
        jTA3.append("            "+i+" : "+pile[i]+"\n");
        i=i-1;
      };
      jTA3.append("ipTas --->  "+i+" : "+pile[i]+"\n");
    }
    jTA3.append("                   .\n");
    jTA3.append("                   .\n");
    jTA3.append("                   .\n");
    if (ip!=-1){
      jTA3.append("ip ---->       "+ip+" : "+pile[ip]+"\n");
      i=ip-1;
      /*invariant:
        pile[ip..O] est affiché*/
      while (i!=-1){
        if (i==base) {
          jTA3.append("base ---->     "+i+" : "+pile[i]+"\n");
        }else{
          jTA3.append("               "+i+" : "+pile[i]+"\n");
        };
        i=i-1;
      };
    };
    jTA3.append("co = "+co+"\n");
    jTA3.append("base = "+base+"\n");
    jTA3.append("fin "+s+"\n");
    jTA3.append("-----------------------------------------------------------------------\n");
  }//tracer

  public void desassembler() {
    int i;
    jTA2.setText("");//RAZ zone
    i=0;
    /*invariant :
      po[0..i-1] est affiché*/
    while (i!=cE+1) {
      jTA2.append(i+" : "+po[i].toString()+"\n");
      i=i+1;
    }
  }//desassembler

  Integer lireInteger(String s){
    /*utilisé dans l'interprétation de
    l'instruction get pour lire à partir d'une boîte de dialogue*/

    String val="";
    val=JOptionPane.showInputDialog(s);
    try {
	  return new Integer(val);
    }catch (NumberFormatException e) {
      Toolkit.getDefaultToolkit().beep(); //bip si erreur
      //production du message d'erreur et "bouclage" par appel récursif :
      if (val==null||val.length()==0) {
	    return lireInteger("Valeur incorrecte, entrer un entier :");
      }else{
	    return lireInteger(val+" : valeur incorrecte, entrer un entier :");
      }
    }
  }//lireInteger

}
