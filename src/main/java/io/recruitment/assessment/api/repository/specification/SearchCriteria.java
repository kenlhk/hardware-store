package io.recruitment.assessment.api.repository.specification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
    private boolean isOrPredicate;

    public SearchCriteria(String key, String operation, Object value) {
        if (key.startsWith("'")) {
            isOrPredicate = true;
            key = key.replaceFirst("'", "");
        } else {
            isOrPredicate = false;
        }
        this.key = key;
        this.operation = operation;
        this.value = value;
    }
}
