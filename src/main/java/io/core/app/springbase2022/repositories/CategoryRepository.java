package io.core.app.springbase2022.repositories;



import io.core.app.springbase2022.models.items.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {


}
