1. Принцип единой ответственности (SRP)
   Пример: Класс Product.java содержит только поля и геттеры, отвечая за модель данных товара. Он не занимается логикой фильтрации или бизнес-операциями.
   Код: src/main/java/org/example/model/Product.java — строки 1-10

2. Принцип открытости/закрытости (OCP)
   Пример: Интерфейс ProductFilter позволяет добавлять новые фильтры (KeywordFilter, PriceRangeFilter) без изменения существующих классов или бизнес-логики.
   Код: src/main/java/org/example/filter/ProductFilter.java — строки 1-8

3. Принцип подстановки Лисков (LSP)
   Пример: Классы KeywordFilter и PriceRangeFilter реализуют интерфейс ProductFilter. Их можно свободно заменять друг другом в ProductService, не нарушая работу программы.
   Код: src/main/java/org/example/filter/KeywordFilter.java — строки 1-20

4. Принцип разделения интерфейса (ISP)
   Пример: Интерфейс ProductFilter содержит только один метод matches(), что позволяет реализовывать только нужный функционал, не заставляя реализовывать лишние методы.
   Код: src/main/java/org/example/filter/ProductFilter.java — строки 1-8

5. Принцип инверсии зависимостей (DIP)
   Пример: В ProductService реализована зависимость от интерфейса ProductFilter, а не от конкретных его реализаций. Это позволяет передавать любой фильтр, реализующий интерфейс, без изменения бизнес-логики.
   Код: src/main/java/org/example/service/ProductService.java — строки 5-15