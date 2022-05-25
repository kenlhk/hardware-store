package io.recruitment.assessment.api.repository.specification;

import io.recruitment.assessment.api.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ProductSpecificationBuilder {

    private final List<SearchCriteria> criteriaList;

    public ProductSpecificationBuilder() {
        criteriaList = new ArrayList<SearchCriteria>();
    }

    public ProductSpecificationBuilder with(String key, String operation, Object value) {
        criteriaList.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public ProductSpecificationBuilder parse(String search) {
        if (search != null) {
            Pattern pattern = Pattern.compile("('?\\w+?)(:|!|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                with(
                        matcher.group(1),
                        matcher.group(2),
                        matcher.group(3));
            }
        }
        return this;
    }

    public Specification<Product> build() {
        if (criteriaList.size() == 0) {
            return null;
        }

        List<Specification> specs = criteriaList.stream()
                .map(ProductSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < criteriaList.size(); i++) {
            result = criteriaList.get(i)
                    .isOrPredicate()
                    ? Specification.where(result)
                    .or(specs.get(i))
                    : Specification.where(result)
                    .and(specs.get(i));
        }
        return result;
    }
}
