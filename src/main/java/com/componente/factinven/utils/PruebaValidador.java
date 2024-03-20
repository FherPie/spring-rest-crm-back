package com.componente.factinven.utils;

public class PruebaValidador {

    public static void main(String[] args) {

        ValidarIdenttificacion valid = new ValidarIdenttificacion();
        ValidadorResponse valida = new ValidadorResponse();
        Boolean valido = true;

        try {

            String valor = "RUC";
            String dato= "PERSONA";

            switch (valor) {
                case "RUC":

                    if(dato.contains("PERSONA"))
                    {
                        valida = valid.validarRucPersonaNatural("1717467805001");
                    } else if (dato.contains("EMPRESA")) {
                        valida = valid.validarRucSociedadPrivada("1717467805001");
                    }
                    else {
                        valida = valid.validarRucPersonaNatural("1717467805001");
                    }
                    break;
                default:

                    break;
            }

            System.out.println(valida.getMensaje());
            System.out.println(valida.getValid());

         /*   valida = valid.validarRucPersonaNatural("1745189650001");
            System.out.println(valida.getMensaje());
            System.out.println(valida.getValid());*/



        } catch (Exception e) {

        }


    }
}
