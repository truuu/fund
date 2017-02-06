package fund.mapper;


import java.util.List;
import fund.dto.User;

 public interface UserMapper {
     User selectById(int id);
     User selectByLoginId(String loginName);
     List<User> selectAll();
     void insert(User user);
     void update(User user);
     void updatePassword(User user);
     void delete(int id);
 }

