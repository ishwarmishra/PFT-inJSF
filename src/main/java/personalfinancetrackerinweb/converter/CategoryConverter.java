package personalfinancetrackerinweb.converter;

import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.repository.CategoryRepositoryImpl;
import personalfinancetrackerinweb.repository.generic.GenericAbstractRepository;


@FacesConverter(value = "categoryConverter", forClass = Category.class)
public class CategoryConverter extends GenericConverter {

    @Inject
    private  CategoryRepositoryImpl categoryRepository;

    @Override
    protected GenericAbstractRepository getGenericRepository() {
        return categoryRepository;

    }

}