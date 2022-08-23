package io.core.app.springbase2022.repositories;




import io.core.app.springbase2022.models.invoices.Invoice;
import io.core.app.springbase2022.models.invoices.ItemsInvoices;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ItemInvoiceRepository extends PagingAndSortingRepository<ItemsInvoices, Long> {

}
