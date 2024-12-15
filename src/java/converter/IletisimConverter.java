/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 
package converter;

import dao.IletisimDAO;
import entity.Iletisim;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;


@FacesConverter(value="mesajlarConverter")
public class IletisimConverter implements Converter {
    
    private IletisimDAO mDao;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uıc, String string) {
       return this.mDao.equals(Integer.valueOf(string));
        
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uıc, Object t) {
        Iletisim m = (Iletisim)t;
        return String.valueOf(m.getMesaj());
    }
    
    
}
*/