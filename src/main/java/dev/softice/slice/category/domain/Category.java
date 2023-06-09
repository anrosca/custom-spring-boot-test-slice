package dev.softice.slice.category.domain;

import dev.softice.slice.common.DynamoDbBase;
import lombok.EqualsAndHashCode;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
@EqualsAndHashCode(callSuper = true)
public class Category extends DynamoDbBase {
    public static final String CATEGORY_PK = "Category";
    public static final String CATEGORY_SK_PREFIX = "Category#";

    private String name;

    public Category() {
    }

    public Category(CategoryBuilder builder) {
        this.name = builder.name;
        this.partitionKey = builder.partitionKey;
        this.sortKey = builder.sortKey;
    }

    public void setId(String id) {
        setPartitionKey(CATEGORY_PK);
        setSortKey(CategoryKeyBuilder.makeSortKey(id));
    }

    public String getId() {
        return getSortKey().substring(CATEGORY_SK_PREFIX.length());
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CategoryBuilder builder() {
        return new CategoryBuilder();
    }

    public static class CategoryKeyBuilder {
        public static String makePartitionKey(String id) {
            return CATEGORY_PK;
        }

        public static String makeSortKey(String id) {
            return CATEGORY_SK_PREFIX + id;
        }
    }

    public static class CategoryBuilder {
        private String name;
        private String partitionKey;
        private String sortKey;

        public CategoryBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CategoryBuilder partitionKey(String partitionKey) {
            this.partitionKey = partitionKey;
            return this;
        }

        public CategoryBuilder sortKey(String sortKey) {
            this.sortKey = sortKey;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}
