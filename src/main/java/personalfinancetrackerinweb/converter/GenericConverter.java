package personalfinancetrackerinweb.converter;

import java.util.logging.Logger;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import personalfinancetrackerinweb.model.AbstractEntity;
import personalfinancetrackerinweb.repository.generic.GenericAbstractRepository;

public abstract class GenericConverter<T extends AbstractEntity> implements Converter {

    protected abstract GenericAbstractRepository<T> getGenericRepository();

    private static final Logger LOGGER = Logger.getLogger(GenericConverter.class.getName());

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            int id = Integer.parseInt(value);
            return getGenericRepository().getById(id);
        } catch (NumberFormatException e) {
            LOGGER.warning("Invalid ID: " + value);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        if (value instanceof AbstractEntity) {
            AbstractEntity entity = (AbstractEntity) value;
            return String.valueOf(entity.getId());
        } else {
            LOGGER.warning("Invalid object type: " + value.getClass().getName());
            return "";
        }
    }
}
