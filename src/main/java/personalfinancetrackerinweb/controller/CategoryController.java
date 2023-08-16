package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.model.User;
import personalfinancetrackerinweb.repository.CategoryRepositoryImpl;

@Named
@ViewScoped
public class CategoryController extends AbstractMessageController implements Serializable {

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

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @PostConstruct
    public void init() {
        category = new Category();

        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
        .getExternalContext().getRequest();
        User user = (User) httpServletRequest.getSession().getAttribute("loggedInClient");
        category.setUser(user);
        findAll();
    }

    public void beforeCreate() {
        
        category = new Category();
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
        .getExternalContext().getRequest();
        User user = (User) httpServletRequest.getSession().getAttribute("loggedInClient");
        category.setUser(user);
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void saveData() {
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
        .getExternalContext().getRequest();

        User user = (User) httpServletRequest.getSession().getAttribute("loggedInClient");
        category.setUser(user);

        if (category.getId() == 0) {
            categoryRepositoryImpl.create(category);
            super.infoMessage("Category Added successfully!");
        } else {
            categoryRepositoryImpl.update(category);
            super.infoMessage("Category Updated successfully!");
        }
        category = new Category();
        findAll();
    }

    public void deleteData(Category category) {
        categoryRepositoryImpl.delete(category.getId());
        findAll();
    }

    public void findAll() {
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
        .getExternalContext().getRequest();
        User user = (User) httpServletRequest.getSession().getAttribute("loggedInClient");
        categoryList = categoryRepositoryImpl.findByUser(user.getId());
    }

    public String getHeader() {
        if (category.getId() == 0) {
            return "Add Category";
        } else {
            return "Update Category";
        }

    }
}
