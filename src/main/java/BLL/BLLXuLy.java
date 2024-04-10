package BLL;

import DAL.xulyDAL;
import DAL.xuly;
import java.util.List;
public class BLLXuLy {
    private xulyDAL xulyDAL;

    public BLLXuLy() {
        xulyDAL = new xulyDAL();
    }

    public List<xuly> getAllXuLy() {
        return xulyDAL.getAllXuLy();
    }
}
