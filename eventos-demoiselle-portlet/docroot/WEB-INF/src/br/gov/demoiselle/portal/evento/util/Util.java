package br.gov.demoiselle.portal.evento.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author root
 */
public class Util
{
    /**
     * 1 - Valor a arredondar. 2 - Quantidade de casas depois da vírgula. 3 -
     * Arredondar para cima ou para baixo? Para cima = 0 (ceil) Para baixo = 1
     * ou qualquer outro inteiro (floor)
     *
     * @param valor
     * @param casas
     * @param ceilOrFloor
     * @return
     */
    public static double arredondar( final double valor, final int casas, final int ceilOrFloor )
    {
        double arredondado = valor;

        arredondado *= ( Math.pow( 10, casas ) );

        if ( ceilOrFloor == 0 )
        {
            arredondado = Math.ceil( arredondado );
        } else
        {
            arredondado = Math.floor( arredondado );
        }

        arredondado /= ( Math.pow( 10, casas ) );

        return arredondado;
    }

    /**
     * @param cls
     * @param campos
     * @param annotations
     */
    public static void carregaDadosClasse( final Class cls, final List<String> campos, final List<String> annotations )
    {
        final Field[] lf = cls.getDeclaredFields(  );

        for ( final Field f : lf )
        {
            final Column c = f.getAnnotation( Column.class );

            if ( ( c != null ) && c.updatable(  ) )
            {
                // if (c.scale() > 0) {
                //
                // campos.add(f.getName());
                // annotations.add(c.name()+"001-00"+c.scale());
                //
                // } else {
                campos.add( f.getName(  ) );
                annotations.add( c.name(  ) );

                // }
            }
        }
    }

    /**
     * Constrói o nome do método get, de acordo com o nome do atributo
     *
     * @param fieldName
     * @return Método get do atributo
     */
    public static String criaGetNomeMetodo( final String fieldName )
    {
        final StringBuilder methodName = new StringBuilder( "get" );

        methodName.append( fieldName.substring( 0, 1 ).toUpperCase(  ) );
        methodName.append( fieldName.substring( 1,
                                                fieldName.length(  ) ) );

        return methodName.toString(  );
    }

    /**
     * Constrói o nome do método get, de acordo com o nome do atributo
     *
     * @param classeName
     * @return Método get do atributo
     */
    public static String criaPrimeiraLetraMaiuscula( final String classeName )
    {
        return classeName.substring( 0, 1 ).toUpperCase(  ) + classeName.substring( 1,
                                                                                    classeName.length(  ) );
    }

    /**
     * Constrói o nome do método set, de acordo com o nome do atributo
     *
     * @param fieldName
     * @return Método set do atributo
     */
    public static String criaSetNomeMetodo(final String fieldName)
    {
        final StringBuilder methodName = new StringBuilder("set");

        if(fieldName.startsWith("_")){
        	methodName.append(fieldName.substring(1, 2).toUpperCase());
        	methodName.append(fieldName.substring(2, fieldName.length()) );
        }
        else{
        	methodName.append(fieldName.substring(0, 1).toUpperCase());
        	methodName.append(fieldName.substring(1, fieldName.length()));
        }

        return methodName.toString();
    }


    /**
     * Retorna o nome de um atributo da classe
     *
     * @param coluna
     * @param cls
     * @return
     */
    public static String getNomeAtributoAnnotationFromColuna( final Class cls, final String coluna )
    {
        String nmAtributo = null;
        final Field[] lf = cls.getDeclaredFields(  );

        for ( final Field f : lf )
        {
            final Column c = f.getAnnotation( Column.class );

            if ( ( c != null ) && (c.name(  ).equalsIgnoreCase( coluna ) ))
            {

                nmAtributo = f.getName(  );

                break;
            }
        }

        return nmAtributo;
    }

      /**
     * Retorna o nome de um atributo da classe
     *
     * @param coluna
     * @param cls
     * @return
     */
    public static String getNomeAtributoFromColuna( final Class cls, final String coluna )
    {
        String nmAtributo = null;
        final Field[] lf = cls.getDeclaredFields();

        for ( final Field f : lf )
        {
            if ((f.getName().equalsIgnoreCase(coluna) || f.getName().equalsIgnoreCase("_" + coluna) ))
            {
                nmAtributo = f.getName(  );
                break;
            }
        }

        return nmAtributo;
    }

    /**
     * @param anotacao
     * @return
     */
    public static int getQtdePeriodico( final String anotacao )
    {
        if ( ( anotacao != null ) && ! anotacao.isEmpty(  ) )
        {
            if ( anotacao.length(  ) <= 3 )
            {
                return 0;
            } else
            {
                return Integer.parseInt( anotacao.substring( 7 ) );
            }
        }

        return 0;
    }

    /**
     * @param texto
     * @param separador
     * @return
     */
    public static List<String> split( final String texto, final String separador )
    {
        final String[] palavras = texto.split( separador );
        final ArrayList<String> lista = new ArrayList<String>(  );

        for ( final String palavra : palavras )
        {
            lista.add( palavra );
        }

        /*
         * if ( texto.startsWith( separador ) ) { texto = texto.substring( 1 );
         * } while ( texto.indexOf( separador ) > -1 ) { aux = texto.substring(
         * 0, texto.indexOf( separador ) ); texto = texto.substring(
         * texto.indexOf( separador ) + 1 ); lista.add( aux ); if (
         * texto.indexOf( separador ) == -1 ) { lista.add( texto ); } }
         */
        return lista;
    }

    /**
     * @param cls
     * @return
     */
    public static boolean verificaJPA( final Class cls )
    {
        final Annotation anno = cls.getAnnotation( Entity.class );

        if ( anno != null )
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * @param totalMemoria
     * @return
     */
    public static String visualizaMemoria( final double totalMemoria )
    {
        if ( ( ( ( totalMemoria / 1024 ) / 1024 ) / 1024 ) > 1 )
        {
            return arredondar( ( ( ( totalMemoria / 1024 ) / 1024 ) / 1024 ), 2, 0 ) + " Gb";
        } else if ( ( ( totalMemoria / 1024 ) / 1024 ) > 1 )
        {
            return arredondar( ( ( totalMemoria / 1024 ) / 1024 ), 2, 0 ) + " Mb";
        } else if ( ( totalMemoria / 1024 ) > 1 )
        {
            return arredondar( ( ( totalMemoria / 1024 ) ), 2, 0 ) + " Kb";
        } else
        {
            return arredondar( ( totalMemoria ), 2, 0 ) + " bytes";
        }
    }

    public static List<String> stringTokenizerToList(StringTokenizer stringTokenizer){
        List<String> lista = new ArrayList<String>();

        for (StringTokenizer stringTokenizer1 = stringTokenizer; stringTokenizer1.hasMoreTokens();) {
            lista.add(stringTokenizer1.nextToken());
        }

        return lista;
    }
}
