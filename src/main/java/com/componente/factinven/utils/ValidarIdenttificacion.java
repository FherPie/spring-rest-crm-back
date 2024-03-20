package com.componente.factinven.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class ValidarIdenttificacion {
    private static final ValidarIdenttificacion VALIDAR_IDENTIFICACION;

    static {
        VALIDAR_IDENTIFICACION = new ValidarIdenttificacion();
    }

    /**
     * @param numero de cedula
     * @return true si es un documento v&aacute;lido
     * @throws Exception
     */
    public ValidadorResponse validarCedula(String numero) throws Exception {
        ValidadorResponse response = new ValidadorResponse();
        try {
            ValidadorResponse responseValorInicial = validarInicial(numero, 10);
            if (responseValorInicial.getValid()) {
                ValidadorResponse responseValorCodigoProvincia = validarCodigoProvincia(numero.substring(0, 2));
                if (responseValorCodigoProvincia.getValid()) {

                    ValidadorResponse responseTercerDigito = validarTercerDigito(String.valueOf(numero.charAt(2)), TipoDocumento.getTipoCedula());
                    if (responseTercerDigito.getValid()) {
                        ValidadorResponse responseAlgoritmo10 = algoritmoModulo10(numero, Integer.parseInt(String.valueOf(numero.charAt(9))));
                        if (responseAlgoritmo10.getValid()) {
                            response.setValid(true);
                            response.setMensaje("OK");
                        } else {
                            response = responseAlgoritmo10;
                        }

                    } else {
                        response = responseTercerDigito;
                    }


                } else {
                    response = responseValorCodigoProvincia;
                }

            } else {
                response = responseValorInicial;
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    /**
     * @param numero de ruc persona natural
     * @return true si es un documento v&aacute;lido
     * @throws Exception
     */
    public ValidadorResponse validarRucPersonaNatural(String numero) throws Exception {
        ValidadorResponse response = new ValidadorResponse();
        try {
            ValidadorResponse validarInicia = validarInicial(numero, 13);

            if (validarInicia.getValid()) {
                ValidadorResponse validarCodigoprov = validarCodigoProvincia(numero.substring(0, 2));
                if (validarCodigoprov.getValid()) {
                    ValidadorResponse validartercerDigito = validarTercerDigito(String.valueOf(numero.charAt(2)), TipoDocumento.getTipoRucNatural());

                    if (validartercerDigito.getValid()) {
                        ValidadorResponse validarCodigoEstablecimiento = validarCodigoEstablecimiento(numero.substring(10, 13));
                        if (validarCodigoEstablecimiento.getValid()) {
                            ValidadorResponse validaralgoritmo10 = algoritmoModulo10(numero.substring(0, 9), Integer.parseInt(String.valueOf(numero.charAt(9))));

                            if (validaralgoritmo10.getValid()) {
                                response.setValid(true);
                                response.setMensaje("Todas las validaciones OK");

                            } else {
                                response = validaralgoritmo10;
                            }
                        } else {
                            response = validarCodigoEstablecimiento;
                        }
                    } else {
                        response = validartercerDigito;
                    }
                } else {
                    response = validarCodigoprov;
                }
            } else {
                response = validarInicia;
            }

            return response;

        } catch (Exception e) {
            return null;
        }


    }

    /**
     * @param numero ruc empresa privada
     * @return
     * @throws Exception
     */
    public ValidadorResponse validarRucSociedadPrivada(String numero) throws Exception {
        ValidadorResponse response = new ValidadorResponse();
        // validaciones
        try {

            ValidadorResponse validadorInicial = validarInicial(numero, 13);
            if (validadorInicial.getValid()) {
                ValidadorResponse validarProvincia = validarCodigoProvincia(numero.substring(0, 2));
                if (validarProvincia.getValid()) {
                    ValidadorResponse validarterceDigito = validarTercerDigito(String.valueOf(numero.charAt(2)), TipoDocumento.getRucPrivada());

                    if (validarterceDigito.getValid()) {
                        ValidadorResponse validarCodigoEstablecimiento = validarCodigoEstablecimiento(numero.substring(10, 13));
                        if (validarCodigoEstablecimiento.getValid()) {
                            ValidadorResponse validarAlgoritmo1 = algoritmoModulo11(numero.substring(0, 9), Integer.parseInt(String.valueOf(numero.charAt(9))), TipoDocumento.getRucPrivada());

                            if (validarAlgoritmo1.getValid()) {
                                response.setValid(true);
                                response.setMensaje("Todas las validacioens correctas");
                            } else {
                                response = validarAlgoritmo1;
                            }
                        } else {
                            response = validarCodigoEstablecimiento;
                        }

                    } else {
                        response = validarterceDigito;
                    }
                } else {
                    response = validarProvincia;
                }
            } else {
                response = validadorInicial;
            }

            return response;
        } catch (Exception e) {
            return null;
        }


    }

    /**
     * @param numero
     * @param caracteres
     * @return
     * @throws Exception
     */
    protected ValidadorResponse validarInicial(String numero, int caracteres) throws Exception {
        ValidadorResponse response = new ValidadorResponse();

        if (StringUtils.isEmpty(numero)) {
            response.setMensaje("Valor no puede estar vacio");
            response.setValid(false);
        } else if (!NumberUtils.isDigits(numero)) {
            response.setMensaje("Valor ingresado solo puede tener dígitos");
            response.setValid(false);
        } else if (numero.length() != caracteres) {
            response.setMensaje("Valor ingresado debe tener " + caracteres + " caracteres");
            response.setValid(false);
        } else {
            response.setMensaje("Documento Válido");
            response.setValid(true);
        }

        return response;
    }

    /**
     * @param &uacutemero en el rango de n&uacutemeros de provincias del ecuador
     * @return
     * @throws Exception
     */
    protected ValidadorResponse validarCodigoProvincia(String numero) throws Exception {
        ValidadorResponse response = new ValidadorResponse();
        if (Integer.parseInt(numero) < 0 || Integer.parseInt(numero) > 24) {
            response.setMensaje("Codigo de Provincia (dos primeros dígitos) no deben ser mayor a 24 ni menores a 0");
            response.setValid(false);
        } else {
            response.setMensaje("Cumple formato dígito validador");
            response.setValid(true);
        }
        return response;
    }

    /**
     * @param numero
     * @param tipo   de documento cedula, ruc
     * @return
     * @throws Exception
     */
    protected ValidadorResponse validarTercerDigito(String numero, Integer tipo) throws Exception {
        ValidadorResponse valida = new ValidadorResponse();

        switch (tipo) {
            case 1:
            case 2:
                if (Integer.parseInt(numero) < 0 || Integer.parseInt(numero) > 5) {
                    valida.setMensaje("Tercer dígito debe ser mayor o igual a 0 y menor a 6 para cédulas y RUC de persona natural ... permitidos de 0 a 5");
                    valida.setValid(false);

                } else {
                    valida.setValid(true);
                    valida.setMensaje("OK");
                }
                break;
            case 3:
                if (Integer.parseInt(numero) != 9) {
                    valida.setMensaje("Tercer dígito debe ser igual a 9 para sociedades privadas");
                    valida.setValid(false);

                } else {
                    valida.setValid(true);
                    valida.setMensaje("OK");
                }
                break;

            case 4:
                if (Integer.parseInt(numero) != 6) {
                    valida.setMensaje("Tercer dígito debe ser igual a 6 para sociedades públicas");
                    valida.setValid(false);

                } else {
                    valida.setValid(true);
                    valida.setMensaje("OK");
                }
                break;
            default:
                valida.setValid(false);
                valida.setMensaje("Tipo de Identificacion no existe.");
                break;

        }

        return valida;
    }

    /**
     * @param digitosIniciales
     * @param digitoVerificador
     * @return
     * @throws Exception
     */
    protected ValidadorResponse algoritmoModulo10(String digitosIniciales, int digitoVerificador) throws Exception {
        ValidadorResponse valida = new ValidadorResponse();
        Integer[] arrayCoeficientes = new Integer[]{2, 1, 2, 1, 2, 1, 2, 1, 2};

        Integer[] digitosInicialesTMP = new Integer[digitosIniciales.length()];
        int indice = 0;
        for (char valorPosicion : digitosIniciales.toCharArray()) {
            digitosInicialesTMP[indice] = NumberUtils.createInteger(String.valueOf(valorPosicion));
            indice++;
        }

        int total = 0;
        int key = 0;

        for (Integer valorPosicion : digitosInicialesTMP) {
            if (key < arrayCoeficientes.length) {
                valorPosicion = (digitosInicialesTMP[key] * arrayCoeficientes[key]);

                if (valorPosicion >= 10) {
                    char[] valorPosicionSplit = String.valueOf(valorPosicion).toCharArray();
                    valorPosicion = (Integer.parseInt(String.valueOf(valorPosicionSplit[0]))) + (Integer.parseInt(String.valueOf(valorPosicionSplit[1])));

                }
                total = total + valorPosicion;
            }

            key++;
        }
        int residuo = total % 10;
        int resultado;

        if (residuo == 0) {
            resultado = 0;
        } else {
            resultado = 10 - residuo;
        }

        if (resultado != digitoVerificador) {
            valida.setValid(false);
            valida.setMensaje("Dígitos iniciales no validan contra Dígito Idenficador");


        } else {
            valida.setValid(true);
            valida.setMensaje("OK");
        }

        return valida;
    }

    /**
     * @param numero
     * @return
     * @throws Exception
     */
    protected ValidadorResponse validarCodigoEstablecimiento(String numero) throws Exception {
        ValidadorResponse response = new ValidadorResponse();
        if (Integer.parseInt(numero) < 1) {
            response.setValid(false);
            response.setMensaje("Código de establecimiento no puede ser 0");

        } else {
            response.setValid(true);
            response.setMensaje("OK");

        }
        return response;
    }

    /**
     * @param digitosIniciales
     * @param digitoVerificador
     * @param tipo
     * @return
     * @throws Exception
     */
    protected ValidadorResponse algoritmoModulo11(String digitosIniciales, int digitoVerificador, Integer tipo) throws Exception {
        List<Integer> arrayCoeficientes = null;
        ValidadorResponse response = new ValidadorResponse();
        switch (tipo) {

            case 3:
                arrayCoeficientes = Arrays.asList(4, 3, 2, 7, 6, 5, 4, 3, 2);
                break;
            case 4:
                arrayCoeficientes = Arrays.asList(3, 2, 7, 6, 5, 4, 3, 2);
                break;
            default:
                throw new Exception("Tipo de Identificacion no existe.");
        }

        List<Integer> digitosInicialesTMP = IntStream.range(0, digitosIniciales.length()).mapToObj(
                        i -> NumberUtils.createInteger(String.valueOf(digitosIniciales.charAt(i)))).
                collect(Collectors.toCollection(() -> new ArrayList<>(digitosIniciales.length())));


        AtomicInteger consolidadodMultiplicacionIndiceConeficiente = new AtomicInteger();
        List<Integer> finalArrayCoeficientes = arrayCoeficientes;
        IntStream.range(0, arrayCoeficientes.size()).map(x -> (digitosInicialesTMP.get(x) * finalArrayCoeficientes.get(x))).
                forEach(consolidadodMultiplicacionIndiceConeficiente::addAndGet);


        int residuo = consolidadodMultiplicacionIndiceConeficiente.get() % 11;
        int resultado;

        if (residuo == 0) {
            resultado = 0;
        } else {
            resultado = (11 - residuo);
        }

        if (resultado != digitoVerificador) {
            response.setValid(false);
            response.setMensaje("Dígitos iniciales no validan contra Dígito Idenficador");

        } else {
            response.setValid(true);
            response.setMensaje("Validación modulo 11 OK");
        }

        return response;
    }

    public static ValidarIdenttificacion getInstance() {
        return VALIDAR_IDENTIFICACION;
    }

}
