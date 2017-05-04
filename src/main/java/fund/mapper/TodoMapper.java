package fund.mapper;

import java.util.List;
import fund.dto.Todo;

public interface TodoMapper {
    List<Todo> selectList();
    void insert(Todo todo);
    void delete(int id);
}
