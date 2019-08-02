package gv.bkap.timvieclam.presenter.main;

public interface IMainPresenter {
    void processOptionItemClick(int id);

    void loadCategories();

    void loadJobItems();

    void processNavMenuClick(int id);

    void filterOutJobs(int idCategory);

    void detailJobItem(int idJob);
}
