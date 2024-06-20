package ru.marketplace.server.entities.cart;

import jakarta.persistence.*;
import lombok.*;
import ru.marketplace.server.entities.users.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CartItem> items = new ArrayList<>();

    private LocalDateTime createdCart;

    private boolean timerStarted;

    public Cart() {
        this.createdCart = LocalDateTime.now();
        this.timerStarted = false;
    }

}


//TODO
/* 1. В катологе убрать кнопку "В корзину", добавить количество оставщихся на складе,
        если товаров не осталось то выводить "Нет на сладе" +
   2. Добавить количество в детали товара +, правильно отрисовывать аттрибуты продукта
   3. Создать корзину +
   4. Оформление заказа(оплата, пвз)
   4.1 Поиск и сортировка в каталоге
   4.2 Добавить товарам продавца
   5. Убрать роль Админа при регистрации
   6. Настроить отзывы только для купивщих людей товар
   7. Управление товаром для продавца
   8. В класс User добавить поля
   9. Тесты
   10. Чистка кода
*
* */
