package personalfinancetrackerinweb.controller;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.repository.CategoryRepository;

@Named
@ViewScoped
public class CategoryController implements Serializable {

    @Inject
    private CategoryRepository categoryRepository;
    private Category category;
    private List<Category> categoryList;
    
    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
    
    @PostConstruct
    public void init() {
        category = new Category();
        findAll();
    }
    
    public void beforeCreate() {
        category = new Category();
    }
    
    public void saveData() {
        if (category.getId() == 0) {
            categoryRepository.create(category);
        } else {
            categoryRepository.update(category);
        }
        category = new Category();
        findAll();
    }

    public void deleteData(Category category) {
        categoryRepository.delete(category.getId());
        findAll();
    }

    public void updateData(Category categoryEntity) {
        categoryRepository.update(categoryEntity);
    }
    
    public void findAll() {
        categoryList = categoryRepository.findAll();
    }
}
