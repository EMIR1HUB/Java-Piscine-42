package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest extends Assertions {
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(0L, "name1", 10000),
            new Product(1L, "name2", 0),
            new Product(2L, "name3", 50),
            new Product(3L, "name4", 15),
            new Product(4L, "name5", 200)
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(3L, "name4", 15);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(1L, "name2", 20);

    EmbeddedDatabase dataSource;
    ProductsRepository productsRepository;

    @BeforeEach
    public void init() throws SQLException {
        this.dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        try {
            productsRepository = new ProductsRepositoryJdbcImpl(dataSource.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindAll() {
        System.out.println(productsRepository.findAll());
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepository.findAll());
    }


    @Test
    public void testFindById() {
        Long id = 3L;
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsRepository.findById(id).get());
    }


    @Test
    public void checkFindByIdException(){
        assertThrows(RuntimeException.class, () -> productsRepository.findById(25L));
    }

    @Test
    public void testUpdate() throws SQLException {
        Product expectedProduct = productsRepository.findById(EXPECTED_UPDATED_PRODUCT.getId()).orElse(null);
        expectedProduct.setPrice(EXPECTED_UPDATED_PRODUCT.getPrice());
        productsRepository.update(expectedProduct);
        Product result = productsRepository.findById(expectedProduct.getId()).orElse(null);

        System.out.println(result + "\n" + EXPECTED_UPDATED_PRODUCT);
        assertNotNull(result);
        assertEquals(result, EXPECTED_UPDATED_PRODUCT);
    }

    @Test
    public void testSave(){
        Integer countBefore = productsRepository.findAll().size();
        productsRepository.save(new Product(6L, "name6", 1210));
        System.out.println(productsRepository.findAll());
        assertEquals(countBefore, productsRepository.findAll().size() - 1);
    }

    @Test
    public void testDelete(){
        Integer countBefore = productsRepository.findAll().size();
        productsRepository.delete(6L);
        System.out.println(productsRepository.findAll());
        assertEquals(countBefore, productsRepository.findAll().size());
    }

    @AfterEach
    public void end() {
        dataSource.shutdown();
    }
}
