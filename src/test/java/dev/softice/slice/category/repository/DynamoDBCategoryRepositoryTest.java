package dev.softice.slice.category.repository;

import java.util.List;
import java.util.Optional;

import dev.softice.slice.category.domain.Category;
import dev.softice.slice.common.AbstractDatabaseTest;
import dev.softice.slice.common.DynamoDbTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

import static org.assertj.core.api.Assertions.assertThat;

@DynamoDbTest
public class DynamoDBCategoryRepositoryTest extends AbstractDatabaseTest {

    @Autowired
    private DynamoDbTable<Category> categoryTable;

    @Autowired
    private DynamoDBCategoryRepository categoryRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeEach
    public void setUp() {
        categoryTable.deleteTable();
        categoryTable.createTable();
        categoryTable.putItem(Category.builder()
                                      .name("Software development")
                                      .partitionKey("Category")
                                      .sortKey("Category#501735c3-5da7-4684-82d3-37af5d5dc44f")
                                      .build());
        categoryTable.putItem(Category.builder()
                                      .name("Anime")
                                      .partitionKey("Category")
                                      .sortKey("Category#601735c3-6da7-4684-62d3-47af5d5dc44e")
                                      .build());

    }

    @Test
    public void shouldBeAbleToFindAllCategories() {
        List<Category> expectedCategories = List.of(
            Category.builder()
                    .name("Software development")
                    .partitionKey("Category")
                    .sortKey("Category#501735c3-5da7-4684-82d3-37af5d5dc44f")
                    .build(),
            Category.builder()
                    .name("Anime")
                    .partitionKey("Category")
                    .sortKey("Category#601735c3-6da7-4684-62d3-47af5d5dc44e")
                    .build()
        );

        List<Category> actualCategories = categoryRepository.findAll();

        assertThat(actualCategories).isEqualTo(expectedCategories);
    }

    @Test
    public void shouldBeAbleToFindCategoriesById() {
        Category expectedCategory = Category.builder()
                                            .name("Software development")
                                            .partitionKey("Category")
                                            .sortKey("Category#501735c3-5da7-4684-82d3-37af5d5dc44f")
                                            .build();

        Optional<Category> actualCategory = categoryRepository.findById("501735c3-5da7-4684-82d3-37af5d5dc44f");

        assertThat(actualCategory).isEqualTo(Optional.of(expectedCategory));
    }
}
