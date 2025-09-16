1. Принцип единой ответственности (SRP)
   Пример: Класс Product.java содержит только поля и геттеры, отвечая за модель данных товара. Он не занимается логикой фильтрации или бизнес-операциями.
   Код: https://github.com/EvdISolo/DesignPatternss3.1/blob/de1539e9628ad954e6a8ab755e6b88ec0c7283f7/src/main/java/model/Product.java#L18-L24

2. Принцип открытости/закрытости (OCP)
   Пример: Интерфейс ProductFilter позволяет добавлять новые фильтры (KeywordFilter, PriceRangeFilter) без изменения существующих классов или бизнес-логики.
   Код: https://github.com/EvdISolo/DesignPatternss3.1/blob/de1539e9628ad954e6a8ab755e6b88ec0c7283f7/src/main/java/filter/ProductFilter.java#L5-L7

3. Принцип подстановки Лисков (LSP)
   Пример: Классы KeywordFilter и PriceRangeFilter реализуют интерфейс ProductFilter. Их можно свободно заменять друг другом в ProductService, не нарушая работу программы.
   Код: https://github.com/EvdISolo/DesignPatternss3.1/blob/de1539e9628ad954e6a8ab755e6b88ec0c7283f7/src/main/java/filter/KeywordFilter.java#L5-L16

https://github.com/EvdISolo/DesignPatternss3.1/blob/de1539e9628ad954e6a8ab755e6b88ec0c7283f7/src/main/java/filter/PriceRangeFilter.java#L5-L17

4. Принцип разделения интерфейса (ISP)
   Пример: Интерфейс ProductFilter содержит только один метод matches(), что позволяет реализовывать только нужный функционал, не заставляя реализовывать лишние методы.
   Код: https://github.com/EvdISolo/DesignPatternss3.1/blob/de1539e9628ad954e6a8ab755e6b88ec0c7283f7/src/main/java/filter/ProductFilter.java#L5-L7

5. Принцип инверсии зависимостей (DIP)
   Пример: В ProductService реализована зависимость от интерфейса ProductFilter, а не от конкретных его реализаций. Это позволяет передавать любой фильтр, реализующий интерфейс, без изменения бизнес-логики.
   Код: https://github.com/EvdISolo/DesignPatternss3.1/blob/de1539e9628ad954e6a8ab755e6b88ec0c7283f7/src/main/java/service/ProductService.java#L8-L30
