package fund.mapper;

public interface LoginErrorMapper {
    int selectCount(String loginName);
    void insert(String loginName);
    void deleteOld();
    void deleteAll(String loginName);
}