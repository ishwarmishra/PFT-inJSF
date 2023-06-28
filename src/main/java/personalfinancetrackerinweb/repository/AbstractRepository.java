package personalfinancetrackerinweb.repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import personalfinancetrackerinweb.model.AbstractEntity;

public abstract class AbstractRepository<T extends AbstractEntity> implements IRepository<T> {

    protected List<T> dataList;
    protected int nextId;
    protected Connection connection;

    public AbstractRepository() {
        dataList = new ArrayList<>();
        nextId = 1;
        
    }
    
    @Override
    public T add(T data) {
        data.setId(nextId);
        nextId++;
        dataList.add(data);
        return data;
    }

    @Override
    public void delete(int id) {
        T itemToRemove = findById(id);
        if (itemToRemove != null) {
            dataList.remove(itemToRemove);
            System.out.println("Item with ID " + id + " deleted.");
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }

    @Override
    public T update(T data) {
        T itemToUpdate = getItemById(data.getId());
        if (itemToUpdate != null) {
            int index = dataList.indexOf(itemToUpdate);
            dataList.set(index, data);
            System.out.println("Item with ID " + data.getId() + " updated.");
        } else {
            System.out.println("Item with ID " + data.getId() + " not found.");
        }
        return data;
    }

    @Override
    public T findById(int id) {
        for (T data : dataList) {
            if (data.getId() == id) {
                return data;
            }
        }

        return null;
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(dataList);
    }

    private T getItemById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }


}
