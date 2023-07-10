package personalfinancetrackerinweb.controller;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import personalfinancetrackerinweb.repository.CategoryRepository;
import personalfinancetrackerinweb.model.Category;

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
        return categoryRepository.findAll();
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
    
    
    @PostConstruct
    public void init() {
        category = new Category();
        categoryList = categoryRepository.findAll();

    }

    public void saveCategory() {
        categoryRepository.create(category);
        category = new Category();
    
    }

    public void deleteCategory(int id) {
        categoryRepository.delete(id);
  
    }

    public void updateCategory(Category categoryEntity) {
        categoryRepository.update(categoryEntity);

    }
    

    
}
