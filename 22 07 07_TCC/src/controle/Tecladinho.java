package controle;

import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tecladinho {
    private static Scanner imput = new Scanner(System.in);
    private static Scanner imputNumber = new Scanner(System.in);
    private static SimpleDateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static Scanner getImput () {
        return imput;
    }

    public static SimpleDateFormat getDateFormat() {
        return DateFormat;
    }
    public static String next() {
        return imput.next();
    }
    public static String next(boolean obrigatorio) {
        while(true) {
            String valor = imput.next();
            if((!valor.isEmpty() && !valor.isBlank()) || obrigatorio == false) {
                return valor;
            }
            else {
                System.out.println("------------------------ ESTE CAMPO É OBRIGATÓRIO ! --------------------");
                System.out.println("digite novamente !");
                System.out.println();
            }
        }    
    }
 
    public static String next(boolean obrigatorio, int tamanho) {
        while(true) {
            String valor = imput.next();
            if(valor.isEmpty() || valor.isBlank()) {
                if(obrigatorio == false) {
                    return valor;
                }
                System.out.println("------------------------ ESTE CAMPO É OBRIGATÓRIO ! --------------------");
                System.out.println("digite novamente !");
                System.out.println();
            
            }
            else if(valor.length() > tamanho) {
                System.out.println();
                System.out.println("o tamanho maximo do CPF é "+tamanho+" digitos");
                System.out.println("digite novamente !");
                System.out.println();
            }
            else {
               return valor;
            }
        }    

    }   
    public static String nextLine() {
        return imput.useDelimiter("\\n").next();
    }
    public static String nextLine(boolean obrigatorio) {
        while(true) {
            String valor = imput.useDelimiter("\\n").next();
            if((!valor.isEmpty() && !valor.isBlank()) || obrigatorio == false) {
                return valor;
            }
            else {
                System.out.println("------------------------ ESTE CAMPO É OBRIGATÓRIO ! --------------------");
                System.out.println("digite novamente !");
                System.out.println();
            }
        } 
    }
    public static int nextInt() {
        while(true) {
            try {
                return Integer.parseInt(imputNumber.next());
            } catch (NumberFormatException e) {
                System.out.println("---------------------- ERRO -----------------");
                System.out.println("Valor Invalido !");
                System.out.println();
                System.out.println("informe o valor novamente: ");
            }
        }
        
    }
    public static float nextFloat() {
        while(true) {
            try {
                return Float.parseFloat(imputNumber.next().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("---------------------- ERRO -----------------");
                System.out.println("Valor Invalido !");
                System.out.println();
                System.out.println("informe o valor novamente: ");
            }
        }

        //return Float.parseFloat(imputNumber.next().replace(",", "."));
    }
    public static double nextDouble() {
        while(true) {
            try {
                return Double.parseDouble(imputNumber.next().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("---------------------- ERRO -----------------");
                System.out.println("Valor Invalido !");
                System.out.println();
                System.out.println("informe o valor novamente: ");
            }
        }
    }
    public static Date nextDate() {
        while(true) {
            String valor =" ";
            try {
                valor = imput.next();
                return DateFormat.parse(valor);
            } catch (ParseException e) {
                System.out.println("---------------------- ERRO -----------------");
                System.out.println("Valor Invalido !");
                System.out.println();
                System.out.println("informe o valor novamente: ");
            }
        }

    }
    public static boolean nextBoolean (){
        while(true) {
            try {
                String valor = imput.next();
                if(valor == "S") {
                    return true;
                }
                else if (valor == "N") {
                    return false;
                }
                else {
                    System.out.println("---------------------- ERRO1 aqui -----------------");
                    System.out.println("Valor Invalido !");
                    System.out.println();
                    System.out.println("informe o valor novamente: ");
                }
            } catch(Exception e) {
                System.out.println("---------------------- ERRO2 -----------------");
                System.out.println("Valor Invalido !");
                System.out.println();
                System.out.println("informe o valor novamente: ");

            }

               
        }
        

    }
}
