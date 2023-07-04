package personalfinancetrackerinweb.controller;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import personalfinancetrackerinweb.repository.CategoryRepository;
import personalfinancetrackerinweb.model.CategoryEntity;

@Named
@ViewScoped
public class CategoryController implements Serializable {

    @Inject
    private CategoryRepository categoryRepository;
    private CategoryEntity category;
    private List<CategoryEntity> categoryList;


    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<CategoryEntity> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryEntity> categoryList) {
        this.categoryList = categoryList;
    }
    
    
    @PostConstruct
    public void init() {
        category = new CategoryEntity();
    }

    public void saveCategory() {
        categoryRepository.create(category);
        category = new CategoryEntity();
        findAll();
    }

    public void deleteCategory(int id) {
        categoryRepository.delete(id);
        findAll();
    }

    public void updateCategory(CategoryEntity categoryEntity) {
        categoryRepository.update(categoryEntity);
        findAll();
    }

    public void findAll() {
        categoryList = categoryRepository.findAll();
    }
}
