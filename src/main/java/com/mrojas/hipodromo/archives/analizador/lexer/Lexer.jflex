
package com.mrojas.hipodromo.archives.analizador.lexer;


import java_cup.runtime.*;
import com.mrojas.hipodromo.archives.analizador.parser.*;

%%

/*segunda sección: configuración */

%class Lexer
%public
%cup
%unicode
%line
%column
%state PALABRA

%{ 

    StringBuilder string = new StringBuilder();

  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }


  private void error(String message) {
    System.out.println("Error en linea: "+(yyline+1)+", columna "+(yycolumn+1)+" : "+message);
  }
%}

WhiteSpace = [\s]

/* TOKENS DE NUMEROS */
NUM = [0-9]+ ("." [0-9]+)?
LETRA = [a-zA-Z]
%%

/* tercer sección: reglas léxicas*/

<YYINITIAL> {
{WhiteSpace} {;}
{NUM} {return symbol(sym.NUM, new Double(yytext()));}
{LETRA} { string.setLength(0); string.append(yytext()); yybegin(PALABRA); }
[^] {;}
}

<PALABRA> {
  [,] {yybegin(YYINITIAL); return symbol(sym.NAME, string.toString());}
  [^]  {string.append(yytext());}
}

<<EOF>>                 { return symbol(sym.EOF); }