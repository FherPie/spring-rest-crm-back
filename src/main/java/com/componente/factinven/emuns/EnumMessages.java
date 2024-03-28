package com.componente.factinven.emuns;

public enum EnumMessages {

   SUCCESS_SAVE("Guardado con Éxito"),
   DELETE_SAVE("Eliminado con Éxito"),
   ERROR("Hubo un problema ");

   private String valor;


   EnumMessages(String nombreValor){
    this.valor= nombreValor;
   }


    public String getValor() {
        return valor;
    }


    public void setValor(String valor) {
        this.valor = valor;
    }


}
