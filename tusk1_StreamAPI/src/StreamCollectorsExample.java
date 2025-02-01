import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );
        // Группируем заказы по продуктам
        Map<String, List<Order>> groupedOrders = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct));

        // Подсчитываем общую стоимость для каждого продукта
        Map<String, Double> totalCostByProduct = groupedOrders.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .mapToDouble(Order::getCost)
                                .sum()
                ));

        // Сортируем продукты по убыванию общей стоимости
        List<Map.Entry<String, Double>> sortedProducts = totalCostByProduct.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toList());

        // Выбираем три самых дорогих продукта
        List<Map.Entry<String, Double>> topThreeProducts = sortedProducts.stream()
                .limit(3)
                .collect(Collectors.toList());

        // Выводим результат
        System.out.println("Три самых дорогих продукта:");
        for (Map.Entry<String, Double> entry : topThreeProducts) {
            System.out.println("Продукт: " + entry.getKey() + ", Общая стоимость: " + entry.getValue());
        }
    }
}