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
    
    //Make the Category Class Instance
    private Category category;
    
    //Keep all the records of the category that are retrieve from the DATABASE
    private List<Category> categoryList;
    

    public CategoryRepositoryImpl getCategoryRepository() {
        return categoryRepositoryImpl;
    }

    public void setCategoryRepositoryImpl(CategoryRepositoryImpl categoryRepositoryImpl) {
        this.categoryRepositoryImpl = categoryRepositoryImpl;
    }
    //To get the User input from the Dialog box
    public Category getCategory() {
        return category;
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
    //It resets the category instance
    public void beforeCreate() {
        category = new Category();
    }
    //To Update the existing Category
    public void setCategory(Category category) {
        this.category = category;
    }
    //To save the new Category
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
    public void findAll() {
        categoryList = categoryRepositoryImpl.findAll();
    }
     public String getHeader() {
        if (category.getId() == 0 ) 
            return "Add Category";
        else
            return "Update Category";
        
    }
}
