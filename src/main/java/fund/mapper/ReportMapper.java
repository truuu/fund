package fund.mapper;

import java.util.List;
import java.util.Map;

public interface ReportMapper {

    List<Map<String,Object>> selectReport1a(Map<String,Object> param);
    List<Map<String,Object>> selectReport1b(Map<String,Object> param);
    List<Map<String,Object>> selectReport2a(Map<String,Object> param);
    List<Map<String,Object>> selectReport2b(Map<String,Object> param);
    List<Map<String,Object>> selectReport2c(Map<String,Object> param);

}
