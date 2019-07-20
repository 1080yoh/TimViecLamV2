package gv.bkap.timvieclam.model.server;

public interface IServerInteractor {
    String sendRequest(int typeRequest, String url, String param[][]);
}
