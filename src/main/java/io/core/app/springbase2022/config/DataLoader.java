package io.core.app.springbase2022.config;

import io.core.app.springbase2022.models.items.Category;
import io.core.app.springbase2022.models.items.Item;
import io.core.app.springbase2022.models.users.Role;
import io.core.app.springbase2022.models.users.RoleName;
import io.core.app.springbase2022.models.users.User;
import io.core.app.springbase2022.repositories.*;
import io.core.app.springbase2022.services.IUserService;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Transactional
public class DataLoader implements ApplicationRunner {

    @Value("${app.data.loader.delete}")
    private Boolean shouldItCleanTheData;

    @Value("${app.data.loader.create}")
    private Boolean shouldItCreateData;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;


    @Autowired
    private ItemInvoiceRepository itemInvoiceRepository;



    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    private EasyRandom factory;

    private void createFactory() {
        EasyRandomParameters parameters = new EasyRandomParameters()
                .seed(123L)
                .objectPoolSize(100)
                .randomizationDepth(3)
                .charset(StandardCharsets.UTF_8)
                .stringLengthRange(5, 50)
                .collectionSizeRange(1, 10)
                .scanClasspathForConcreteTypes(true)
                .overrideDefaultInitialization(false)
                .ignoreRandomizationErrors(true);
        factory = new EasyRandom(parameters);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        createFactory();
        if (this.shouldItCleanTheData)
        {
            this.itemInvoiceRepository.deleteAll();
            this.invoiceRepository.deleteAll();
            this.itemRepository.deleteAll();
            this.categoryRepository.deleteAll();
            this.userRepository.deleteAll();

        }
        if (this.shouldItCreateData)
        {
            this.createUser();
            this.createCategory();
            this .createItems();
        }



    }

    private void createCategory() {
        Category category = new Category();
        category.setName("General");
        this.category = this.categoryRepository.save(category);

    }
    private void createItems() {

        this.itemRepository.saveAll(
                IntStream.range(1, 10).mapToObj(e ->
                        {
                            Item item = factory.nextObject(Item.class);
                            item.setPrice(1000d);
                            item.setQuality(1000d);
                            return item;

                        }

                ).map(item -> {
                    item.setCategory(this.category);
                    return item;
                }).collect(Collectors.toSet())
        );
    }


    private void createUser() {
        User user = new User();
        roleRepository.save(new Role(RoleName.ROLE_USER));
        roleRepository.save(new Role(RoleName.ROLE_ADMIN));
        user.setPassword(passwordEncoder.encode("12345"));
        user.setUsername("admin");
        user.setEnabled(true);
        user.setRoles((List<Role>) this.roleRepository.findAll());
        this.userRepository.save(user);




    }


}
