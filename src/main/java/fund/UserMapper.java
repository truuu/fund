package fund;

import java.util.List;

public interface UserMapper {
    User selectById(int id);
    User selectByLoginId(String loginId);

}
