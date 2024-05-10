package be.bstorm.specification.utils.request;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public record SearchParam<T>(
        String field,
        SearchOperator op,
        Object value
) {

    public static <T> SearchParam<T> create(Map.Entry<String, String> entry) {
        String field;
        SearchOperator op;
        Object value;
        String[] key = entry.getKey().split("_");
        if (key.length == 1) {
            op = SearchOperator.EQ;
            field = key[0];
        } else {
            op = SearchOperator.valueOf(key[0].toUpperCase());
            field = key[1];
        }

        value = entry.getValue();

        return new SearchParam(field, op, value);
    }

    public static <T> List<SearchParam<T>> create(Map<String, String> routeParams) {
        return routeParams.entrySet().stream()
                .map(entry -> (SearchParam<T>)SearchParam.create(entry))
                .toList();
    }
}
