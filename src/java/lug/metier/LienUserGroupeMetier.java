/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lug.metier;

import lug.dao.ILienUserGroupeDao;

/**
 * Classe métier pour l'entité LienUserGroupe.
 * 
 * @author quangminhnguyen
 */
public class LienUserGroupeMetier implements ILienUserGroupeMetier {
    ILienUserGroupeDao lienUserGroupeDao;

    public LienUserGroupeMetier() {
    }

    public void setLienUserGroupeDao(ILienUserGroupeDao lienUserGroupeDao) {
        this.lienUserGroupeDao = lienUserGroupeDao;
    }
    
}
