package zoot.analyse ;

import java_cup.runtime.*;
import zoot.exceptions.AnalyseLexicaleException;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{

  private StringBuilder chaine ;

  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

csteE = [0-9]+
csteB = (vrai|faux)

finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]
type = (entier|booleen)
idf = [A-Za-z][A-Za-z0-9]*

%%
"//".*                                    { /* DO NOTHING */ }

"debut"                { return symbol(CodesLexicaux.DEBUT); }
"fin"              	   { return symbol(CodesLexicaux.FIN); }

"ecrire"               { return symbol(CodesLexicaux.ECRIRE); }
"retourne"               { return symbol(CodesLexicaux.RETOURNE); }
"non"                  { return symbol(CodesLexicaux.NON);}
"et"                  { return symbol(CodesLexicaux.ET);}
"ou"                  { return symbol(CodesLexicaux.OU);}
"repeter"                  { return symbol(CodesLexicaux.REPETER);}
"jusqua"                  { return symbol(CodesLexicaux.JUSQUA);}
"finrepeter"                  { return symbol(CodesLexicaux.FINREPETER);}
"si"                  { return symbol(CodesLexicaux.SI);}
"alors"                  { return symbol(CodesLexicaux.ALORS);}
"finsi"                  { return symbol(CodesLexicaux.FINSI);}
"sinon"                  { return symbol(CodesLexicaux.SINON);}

";"                    { return symbol(CodesLexicaux.POINTVIRGULE); }

{csteE}      	       { return symbol(CodesLexicaux.CSTENTIERE, yytext()); }


"variables"             { return symbol(CodesLexicaux.VARIABLES); }
"fonctions"             { return symbol(CodesLexicaux.FONCTIONS); }

{csteB}      	        { return symbol(CodesLexicaux.CSTBOOLEENE, yytext());}
{type}      	        { return symbol(CodesLexicaux.TYPE, yytext());}
{espace}                { }
{idf}                   { return symbol(CodesLexicaux.IDF, yytext());}
"="                     { return symbol(CodesLexicaux.EGALE);}
"("                    { return symbol(CodesLexicaux.PARENTHESEOUVRANTE);}
")"                    { return symbol(CodesLexicaux.PARENTHESEFERMANTE);}
","                    { return symbol(CodesLexicaux.VIRGULE);}
"+"                    { return symbol(CodesLexicaux.PLUS);}
"*"                    { return symbol(CodesLexicaux.MULT);}
"-"                    { return symbol(CodesLexicaux.NEGATIVE);}
"<"                  { return symbol(CodesLexicaux.INF);}
"=="                  { return symbol(CodesLexicaux.EQUAL);}
"!="                  { return symbol(CodesLexicaux.DIFFERENT);}

.                      { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }

