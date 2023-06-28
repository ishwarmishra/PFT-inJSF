
package personalfinancetrackerinweb.model;


public abstract class AbstractEntity implements IModelId{
    private int id;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
    
}
