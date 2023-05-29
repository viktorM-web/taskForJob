package org.task.rf.nonton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductsImpl {

    private Map<String, Product> storage = new HashMap<>();

    public boolean addProduct(Product product) {
        var productFromStorage = storage.putIfAbsent(product.getId(), product);
        return productFromStorage == null;
    }

    public boolean deleteProduct(Product product) {
        return storage.remove(product.getId(), product);
    }

    public String getName(String id) {
        var maybeProduct = Optional.ofNullable(storage.get(id));
        return maybeProduct.isPresent()
                ? maybeProduct.get().getName()
                : "";
    }

    public List<String> findByName(String name) {
        return storage.values().stream()
                .filter(product -> product.getName().equals(name))
                .map(Product::getId)
                .collect(Collectors.toList());
    }
}
