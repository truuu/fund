package fund.mapper;


import java.util.List;

import fund.dto.User;

 public interface UserMapper {
     User selectById(int id);
     User selectByLoginName(String loginName);
     List<User> selectAll();
     void insert(User user);
     void update(User user);
     void updatePassword(User user);
     void updateErrorCount(User user);
     void delete(int id);
 }

