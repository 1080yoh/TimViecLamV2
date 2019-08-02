package gv.bkap.timvieclam.model.main;

public interface IMainInteractor {
    void loadCategories();

    void loadJobItems();

    void loadJobItems(int category);

    void detailJobItem(int idJob);

}
