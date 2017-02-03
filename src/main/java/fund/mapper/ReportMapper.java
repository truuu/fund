package fund.mapper;

import java.util.HashMap;
import java.util.List;

public interface ReportMapper {

    List<HashMap<String,Object>> selectReport1a(HashMap<String,Object> param);
    List<HashMap<String,Object>> selectReport1b(HashMap<String,Object> param);
    List<HashMap<String,Object>> selectReport2a(HashMap<String,Object> param);
    List<HashMap<String,Object>> selectReport2b(HashMap<String,Object> param);
    List<HashMap<String,Object>> selectReport2c(HashMap<String,Object> param);

}
