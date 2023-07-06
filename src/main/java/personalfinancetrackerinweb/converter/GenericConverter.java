package personalfinancetrackerinweb.converter;

import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import personalfinancetrackerinweb.model.AbstractEntity;
import personalfinancetrackerinweb.repository.generic.GenericAbstractRepository;

public abstract class GenericConverter implements Converter {

    protected abstract GenericAbstractRepository getGenericRepository();

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {

        if (value == null || value.length() == 0 || value.equals("") || value.equalsIgnoreCase("null") || value.toLowerCase().startsWith("sel")) {
            return null;
        }
        try {
            return getGenericRepository().getById(getKey(value));
        } catch (NumberFormatException nfe) {
            Logger.getLogger("Severe: Number format Exception, Can't convert value " + value);
            return null;
        }
    }

    java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    String getStringKey(Object value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if ("".equals(object)) {
            return "";
        }
        if (object == null) {
            return null;
        }
        if (object instanceof AbstractEntity) {
            AbstractEntity o = (AbstractEntity) object;
            return getStringKey(Integer.valueOf(o.getId()));
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: converter for " + this.getClass());
        }
    }
}
