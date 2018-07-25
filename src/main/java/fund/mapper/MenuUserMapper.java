package fund.mapper;


import java.util.List;

 public interface MenuUserMapper {
     List<Integer> selectMenuIdByUserId(int userId);
 }

