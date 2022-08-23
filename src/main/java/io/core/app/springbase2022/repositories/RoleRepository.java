package io.core.app.springbase2022.repositories;



import io.core.app.springbase2022.models.users.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

    Role findByName(String name);

    //@Query("select u from USER u where u.username=?1")
    //User findByUsername2(String username);

}
