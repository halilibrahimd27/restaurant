/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

/**
 *
 * @author Halil
 */

import entity.Calisan;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("calisanBean")
@SessionScoped
public class CalisanBean extends GenericBean<Calisan> {

    public CalisanBean() {
        super(Calisan.class);
    }
}
