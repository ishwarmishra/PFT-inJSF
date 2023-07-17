package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.repository.CategoryRepositoryImpl;

@Named
@ViewScoped
public class CategoryController implements Serializable {

    @Inject
    private CategoryRepositoryImpl categoryRepositoryImpl;
    private Category category;
    private List<Category> categoryList;

    public CategoryRepositoryImpl getCategoryRepository() {
        return categoryRepositoryImpl;
    }

    public void setCategoryRepositoryImpl(CategoryRepositoryImpl categoryRepositoryImpl) {
        this.categoryRepositoryImpl = categoryRepositoryImpl;
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
            categoryRepositoryImpl.create(category);
        } else {
            categoryRepositoryImpl.update(category);
        }
        category = new Category();
        findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Category saved successfully!"));

    }

    public void deleteData(Category category) {
        categoryRepositoryImpl.delete(category.getId());
        findAll();
    }

    public void updateData(Category categoryEntity) {
        categoryRepositoryImpl.update(categoryEntity);
    }

    public void findAll() {
        categoryList = categoryRepositoryImpl.findAll();
    }
}
