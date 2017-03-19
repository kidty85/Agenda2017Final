/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 * Enumération pour les types d'agenda.
 *
 * @author quangminhnguyen
 */
public enum AgendaTypeEnum {

    PRI("PRIVE"),
    PAR("PARTAGE");

    String code;

    private AgendaTypeEnum(String code) {
        this.code = code;
    }

}
