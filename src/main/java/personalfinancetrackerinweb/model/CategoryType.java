package personalfinancetrackerinweb.model;


public enum CategoryType {
    INCOME("income"), EXPENSE("expense");
    
    private String label;
    
    private CategoryType(String label){
        this.label = label;
    }
    public String getLabel(){
        return label;
    }
}

