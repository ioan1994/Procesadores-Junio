import java.io.*;
import java_cup.runtime.Symbol;


%%
%{

/**
* clase updClass
*/


private void showError(int linea){
	System.out.println("\033[31m Error en linea " + linea + ". No se reconoce \"" + "\"\033[37m");

}

private void prueba(String linea){
	System.out.println("\033[31m Error en linea " + linea.substring(0,4) + ". No se reconoce \"" + "\"\033[37m");
}
%}


comienzo="start"
fin="end"
tabla="table"
datos="data"
registro="register"
menor="<"
mayor=">"
dospuntos=":"
barra="/"
comillas="\""
campo="field"
valor="value"
numero="number"
of="of"
campos="fields" 
descripcion="description"
basededatos="database"
suma="+"
resta="-"

_necesario="required"
_tipo="type" 
_cadena="string"
_fecha="date"
_entero="integer"
_comaflotante="float"
_nombre="name"

necesario=("true"|"false")
tipo = ("integer"|"float"|"string"|"date")

nombre=([A-Z][A-Za-z0-9]*|[A-Z][A-Za-z0-9]*[-]|[A-Z][A-Za-z0-9]*[-][A-Za-z0-9]*)
natural=[0-9]+
entero=[+-]?{natural}
comaflotante={entero}[.]{natural}
cadena={comillas}[^\n\"]+{comillas}
fecha={natural}{natural}/{natural}{natural}/{natural}{natural}



%line
%unicode
%class lexBD
%cup

%eof{ 
	//end of file -- gestion de errores y muestra de resultados
%eof}


%%


{comienzo} {return new Symbol(sym.COMIENZO,yyline,yyline,yytext());}
{fin} {return new Symbol(sym.FIN,yyline,yyline,yytext());}
{tabla} {return new Symbol(sym.TABLA,yyline,yyline,yytext());}
{datos} {return new Symbol(sym.DATOS,yyline,yyline,yytext());}
{registro} {return new Symbol(sym.REGISTRO,yyline,yyline,yytext());}
{menor} {return new Symbol(sym.MENOR,yyline,yyline,yytext());}
{mayor} {return new Symbol(sym.MAYOR,yyline,yyline,yytext());}
{dospuntos} {return new Symbol(sym.DOSPUNTOS,yyline,yyline,yytext());}
{barra} {return new Symbol(sym.BARRA,yyline,yyline,yytext());}
{comillas} {return new Symbol(sym.COMILLAS,yyline,yyline,yytext());}
{campo} {return new Symbol(sym.CAMPO,yyline,yyline,yytext());}
{valor} {return new Symbol(sym.VALOR,yyline,yyline,yytext());}
{numero} {return new Symbol(sym.NUMERO,yyline,yyline,yytext());}
{of} {return new Symbol(sym.OF,yyline,yyline,yytext());}
{campos} {return new Symbol(sym.CAMPOS,yyline,yyline,yytext());}
{descripcion} {return new Symbol(sym.DESCRIPCION,yyline,yyline,yytext());}
{basededatos} {return new Symbol(sym.BASEDEDATOS,yyline,yyline,yytext());}
{suma} {return new Symbol(sym.SUMA,yyline,yyline,yytext());}
{resta} {return new Symbol(sym.RESTA,yyline,yyline,yytext());}
{necesario} {return new Symbol(sym.NECESARIO,yyline,yyline,yytext());}

{_necesario} {return new Symbol(sym._NECESARIO,yyline,yyline,yytext());}
{_tipo} {return new Symbol(sym._TIPO,yyline,yyline,yytext());}
{_nombre} {return new Symbol(sym._NOMBRE,yyline,yyline,yytext());}

{tipo} {return new Symbol(sym.TIPO,yyline,yyline,yytext());}
{cadena} {return new Symbol(sym.CADENA,yyline,yyline,yytext());}
{fecha} {return new Symbol(sym.FECHA,yyline,yyline,yytext());}
{comaflotante} {return new Symbol(sym.COMAFLOTANTE,yyline,yyline,yytext());}
{natural} {return new Symbol(sym.NATURAL,yyline,yyline,yytext());}
{nombre} {
			if(yytext().length() > 10) 
			{
				return new Symbol(sym.NOMBRE,yyline,yyline,yytext().substring(0,10)); 
			}
			else
			{
				return new Symbol(sym.NOMBRE,yyline,yyline,yytext());
			}
		 }


[" "\t\n\r]	{}

.       {System.out.println("ERROR");}