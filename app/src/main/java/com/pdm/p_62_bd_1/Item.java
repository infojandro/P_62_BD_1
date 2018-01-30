package com.pdm.p_62_bd_1;

class Item {
    private String nombrePersona;
    private int idPersona;

    Item(String nombrePersona, int idPersona) {
        this.nombrePersona = nombrePersona;
        this.idPersona=idPersona;
    }

    String getNombrePersona() {
        return nombrePersona;
    }
    int getIdPersona() {
        return idPersona;
    }

}
