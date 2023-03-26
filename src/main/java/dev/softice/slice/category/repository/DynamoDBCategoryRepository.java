package dev.softice.slice.category.repository;

import java.util.List;
import java.util.Optional;

import dev.softice.slice.category.domain.Category;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

@Repository
public class DynamoDBCategoryRepository implements CategoryRepository {

    private final DynamoDbTable<Category> categoryTable;

    public DynamoDBCategoryRepository(DynamoDbTable<Category> categoryTable) {
        this.categoryTable = categoryTable;
    }

    @Override
    public List<Category> findAll() {
        Key key = Key.builder()
                     .partitionValue(Category.CATEGORY_PK)
                     .sortValue(Category.CATEGORY_SK_PREFIX)
                     .build();
        return categoryTable.query(QueryConditional.sortBeginsWith(key))
                            .stream()
                            .flatMap(page -> page.items().stream())
                            .toList();
    }

    @Override
    public Category create(Category categoryToCreate) {
        categoryTable.putItem(categoryToCreate);
        return categoryToCreate;
    }

    @Override
    public Optional<Category> findById(String id) {
        Key key = Key.builder()
                     .partitionValue(Category.CategoryKeyBuilder.makePartitionKey(id))
                     .sortValue(Category.CategoryKeyBuilder.makeSortKey(id))
                     .build();
        return Optional.ofNullable(categoryTable.getItem(key));
    }
}
