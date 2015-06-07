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

tabla="table"
datos="data"
registro="register"
cerrar=">"
comillas="\""
valor="value"
numero="number"
de="of"
campos="fields" 
descripcion="description"
basededatos="database"
true="true"
false="false"


necesario=("true"|"false")

_nombre="name"

tipo=("integer"|"float"|"string"|"date")
nombre=([A-Z][A-Za-z0-9]*|[A-Z][A-Za-z0-9]*[-]|[A-Z][A-Za-z0-9]*[-][A-Za-z0-9]*)
comaflotante={entero}[.]{natural}
entero=[-+]?{natural}
natural=[0-9]+
cadena={comillas}[^\n\"]+{comillas}
fecha=[0-3][0-9]/[0-1][0-9]/[0-9][0-9]


start="<start"
end="<end"

aField="<field"
cField="</field"


aRegistro="<start"
cRegistro="<end"

abrirCampo="<field>"
cerrarCampo="</field>"

abrirNombre="<name:"
abrirValor="<value:"
abrirNumeroDeCampos="<number of fields:"
abrirTipo="<type:"
abrirNecesario="<required:"

abrirNombreTabla="<table name:"




%line
%unicode
%class lexBD
%cup

%eof{ 
	//end of file -- gestion de errores y muestra de resultados
%eof}


%%


{start} {return new Symbol(sym.START,yyline,yyline,yytext());}
{end} {return new Symbol(sym.END,yyline,yyline,yytext());}

{aField} {return new Symbol(sym.AFIELD,yyline,yyline,yytext());}
{cField} {return new Symbol(sym.CFIELD,yyline,yyline,yytext());}

{datos} {return new Symbol(sym.DATOS,yyline,yyline,yytext());}

{registro} {return new Symbol(sym.REGISTRO,yyline,yyline,yytext());}

{aRegistro} {return new Symbol(sym.AREGISTRO,yyline,yyline,yytext());}
{cRegistro} {return new Symbol(sym.CREGISTRO,yyline,yyline,yytext());}

{abrirCampo} {return new Symbol(sym.ABRIRCAMPO,yyline,yyline,yytext());}
{cerrarCampo} {return new Symbol(sym.CERRARCAMPO,yyline,yyline,yytext());}

{abrirNombre} {return new Symbol(sym.ABRIRNOMBRE,yyline,yyline,yytext());}
{abrirNumeroDeCampos} {return new Symbol(sym.ABRIRNUMERODECAMPOS,yyline,yyline,yytext());}
{abrirTipo} {return new Symbol(sym.ABRIRTIPO,yyline,yyline,yytext());}
{abrirNecesario} {return new Symbol(sym.ABRIRNECESARIO,yyline,yyline,yytext());}

{abrirNombreTabla} {return new Symbol(sym.ABRIRNOMBRETABLA,yyline,yyline,yytext());}
{basededatos} {return new Symbol(sym.BASEDEDATOS,yyline,yyline,yytext());}


{abrirValor} {return new Symbol(sym.ABRIRVALOR,yyline,yyline,yytext());}
{descripcion} {return new Symbol(sym.DESCRIPCION,yyline,yyline,yytext());}

{necesario} {
			if(yytext().equals("true")){
				return new Symbol(sym.NECESARIO,yyline,yyline,true);
			}else{

				return new Symbol(sym.NECESARIO,yyline,yyline,false);
			}
		}

{tabla} {return new Symbol(sym.TABLA,yyline,yyline,yytext());}

{cerrar} {return new Symbol(sym.CERRAR,yyline,yyline,yytext());}


{tipo} {return new Symbol(sym.TIPO,yyline,yyline,yytext());}
{fecha} {return new Symbol(sym.FECHA,yyline,yyline,yytext());}
{natural} {return new Symbol(sym.NATURAL,yyline,yyline,Integer.parseInt(yytext()));}
{comaflotante} {return new Symbol(sym.COMAFLOTANTE,yyline,yyline,Float.parseFloat(yytext()));}
{entero} {return new Symbol(sym.ENTERO,yyline,yyline,Integer.parseInt(yytext()));}
{cadena} {return new Symbol(sym.CADENA,yyline,yyline,yytext());}

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