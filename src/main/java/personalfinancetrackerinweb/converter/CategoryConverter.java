package personalfinancetrackerinweb.converter;


import javax.faces.convert.FacesConverter;

import javax.inject.Inject;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.repository.CategoryRepository;
import personalfinancetrackerinweb.repository.generic.GenericAbstractRepository;


@FacesConverter(value = "categoryConverter", forClass = Category.class)
public class CategoryConverter extends GenericConverter {

    @Inject
    private  CategoryRepository categoryRepository;

    @Override
    protected GenericAbstractRepository getGenericRepository() {
        return categoryRepository;

    }

}