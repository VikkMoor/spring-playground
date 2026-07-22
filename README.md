# SpringCore

Учебный Maven-проект для изучения основ Spring Framework. Репозиторий содержит серию независимых домашних заданий, каждое из которых оформлено в отдельном Java-пакете с собственной точкой входа (`main`).

> **Примечание.** В репозитории отсутствуют формулировки заданий от преподавателя или курса. Описания целей и тем ниже составлены на основе анализа исходного кода, имён пакетов и истории коммитов.

## Содержание

- [Обзор](#обзор)
- [Требования](#требования)
- [Сборка и запуск](#сборка-и-запуск)
- [Структура репозитория](#структура-репозитория)
- [Домашние задания](#домашние-задания)
  - [HW01 — Dependency Injection (DI)](#hw01--dependency-injection-di)
  - [HW02 — Bean Scope](#hw02--bean-scope)
  - [HW03 — Bean Lifecycle](#hw03--bean-lifecycle)
  - [HW04 — External Properties](#hw04--external-properties)
- [Зависимости Maven](#зависимости-maven)

---

## Обзор

| Параметр | Значение |
|---|---|
| **Язык** | Java 21 |
| **Система сборки** | Maven |
| **Фреймворк** | Spring Framework 6.2.8 |
| **Конфигурация** | Java-based (`@Configuration`, аннотации) |
| **Количество заданий** | 4 (`hw01` — `hw04`) |
| **Тесты** | Отсутствуют |
| **Remote-репозиторий** | [github.com/VikkMoor/spring-playground](https://github.com/VikkMoor/spring-playground) |

Проект демонстрирует пошаговое освоение IoC-контейнера Spring: от базового внедрения зависимостей до работы с внешними свойствами конфигурации.

---

## Требования

- JDK 21+
- Apache Maven 3.6+

---

## Сборка и запуск

Сборка всего проекта:

```bash
mvn compile
```

Каждое домашнее задание запускается отдельно через главный класс соответствующего пакета (из IDE или командной строки):

| Задание | Главный класс |
|---|---|
| HW01 | `moor.hw01_di.Main` |
| HW02 | `moor.hw02_beanscope.Main` |
| HW03 | `moor.hw03_beanLifecycle.LifecyclePlaygroundApp` |
| HW04 | `moor.hw04_property.App` |

Пример запуска из командной строки (после `mvn compile`):

```bash
java -cp target/classes moor.hw01_di.Main
```

---

## Структура репозитория

```
SpringCore/
├── pom.xml                              # Maven-конфигурация и зависимости
├── .gitignore
└── src/
    └── main/
        ├── java/moor/
        │   ├── hw01_di/                 # ДЗ 1: Dependency Injection
        │   ├── hw02_beanscope/          # ДЗ 2: Области видимости бинов
        │   ├── hw03_beanLifecycle/      # ДЗ 3: Жизненный цикл бина
        │   └── hw04_property/           # ДЗ 4: Внешние свойства
        └── resources/
            └── application.properties   # Конфигурация для HW04
```

---

## Домашние задания

Задания расположены в порядке выполнения (подтверждено историей Git-коммитов).

---

### HW01 — Dependency Injection (DI)

**Пакет:** `moor.hw01_di`

#### Тема

Внедрение зависимостей (Dependency Injection) в Spring: интерфейсы, реализации, способы инъекции и разрешение неоднозначности бинов.

#### Цель работы

*Вывод по коду:* освоить механизм IoC-контейнера Spring — регистрацию компонентов, внедрение зависимостей тремя способами и выбор конкретной реализации при наличии нескольких бинов одного типа.

#### Что реализовано

- Интерфейс `GreetingService` с двумя реализациями: дружелюбное и формальное приветствие.
- Три класса-потребителя (`GreetingPrinter*`), демонстрирующие разные способы внедрения `GreetingService`.
- Конфигурационный класс с `@ComponentScan`.
- Точка входа, получающая бины из контекста и выводящая приветствие для имени `"Vikky"`.

#### Технологии и инструменты

| Инструмент | Использование |
|---|---|
| Java 21 | Язык реализации |
| Spring Context 6.2.8 | IoC-контейнер |
| Maven | Сборка проекта |
| `AnnotationConfigApplicationContext` | Запуск Java-конфигурации |

#### Ключевые классы и компоненты

| Класс | Роль |
|---|---|
| `AppConfig` | Конфигурация Spring, сканирование пакета |
| `GreetingService` | Интерфейс сервиса приветствий |
| `FriendlyGreetingService` | Реализация с `@Primary` |
| `FormalGreetingService` | Альтернативная реализация |
| `GreetingPrinterConstructor` | Constructor injection |
| `GreetingPrinterField` | Field injection (`@Autowired`) |
| `GreetingPrinterSetter` | Setter injection с `@Qualifier("formalGreetingService")` |
| `Main` | Точка входа |

#### Изученные концепции Java/Spring

- **IoC (Inversion of Control)** — управление объектами контейнером Spring.
- **Dependency Injection** — constructor, field и setter injection.
- **Stereotype-аннотации:** `@Configuration`, `@Component`, `@Service`.
- **`@ComponentScan`** — автоматическое обнаружение компонентов.
- **`@Primary`** — выбор бина по умолчанию при нескольких реализациях.
- **`@Qualifier`** — явное указание нужного бина.
- **`ApplicationContext`** — получение управляемых объектов.

#### Структура пакета

```
hw01_di/
├── AppConfig.java
├── Main.java
├── GreetingService.java
├── FriendlyGreetingService.java
├── FormalGreetingService.java
├── GreetingPrinterConstructor.java
├── GreetingPrinterField.java
└── GreetingPrinterSetter.java
```

---

### HW02 — Bean Scope

**Пакет:** `moor.hw02_beanscope`

#### Тема

Области видимости (scope) Spring-бинов: singleton vs prototype и корректное использование prototype-бина внутри singleton-компонента.

#### Цель работы

*Вывод по коду:* понять различие между singleton и prototype scope, а также научиться получать новый экземпляр prototype-бина при каждом обращении через `ObjectProvider`.

#### Что реализовано

- `TaskContext` — prototype-бин с уникальным UUID, генерируемым при создании.
- `JobRunner` — singleton-компонент, вызывающий `runOnce()` три раза.
- Корректное внедрение через `ObjectProvider<TaskContext>` (в коде также закомментирован некорректный вариант с прямым constructor injection).
- Вывод UUID в консоль при каждом вызове — демонстрация создания новых экземпляров.

#### Технологии и инструменты

| Инструмент | Использование |
|---|---|
| Java 21 | Язык реализации |
| Spring Context 6.2.8 | Управление scope бинов |
| `ObjectProvider<T>` | Ленивое получение prototype-бина |
| Maven | Сборка проекта |

#### Ключевые классы и компоненты

| Класс | Роль |
|---|---|
| `AppConfig` | Конфигурация со `@ComponentScan` |
| `TaskContext` | Prototype-бин (`@Scope("prototype")`) |
| `JobRunner` | Singleton, использующий `ObjectProvider` |
| `Main` | Запуск трёх итераций и закрытие контекста |

#### Изученные концепции Java/Spring

- **Singleton scope** (по умолчанию) — один экземпляр на контейнер.
- **Prototype scope** — новый экземпляр при каждом запросе.
- **Проблема внедрения prototype в singleton** — прямой constructor injection «замораживает» один экземпляр.
- **`ObjectProvider<T>`** — паттерн для получения свежего prototype-бина по требованию.
- **`context.close()`** — корректное завершение работы контекста.

#### Структура пакета

```
hw02_beanscope/
├── AppConfig.java
├── Main.java
├── TaskContext.java
└── JobRunner.java
```

---

### HW03 — Bean Lifecycle

**Пакет:** `moor.hw03_beanLifecycle`

#### Тема

Жизненный цикл Spring-бина: фазы создания, инициализации и уничтожения; влияние scope на поведение lifecycle-callback'ов.

#### Цель работы

*Вывод по коду:* изучить callback-методы `@PostConstruct` и `@PreDestroy`, сравнить поведение singleton- и prototype-бинов, а также поэкспериментировать с переключением scope (согласно комментарию в `AppConfig`).

#### Что реализовано

- Регистрация бинов через `@Bean`-методы (в отличие от `@ComponentScan` в предыдущих заданиях).
- `AppLogger` — singleton-бин с lifecycle-callback'ами.
- `OperationContext` — бин с lifecycle-callback'ами; в текущей версии кода помечен `@Scope("prototype")`.
- Демонстрация идентичности экземпляров через сравнение ссылок (`==`).
- Закрытие контекста для вызова `@PreDestroy`.

#### Технологии и инструменты

| Инструмент | Использование |
|---|---|
| Java 21 | Язык реализации |
| Spring Context 6.2.8 | Управление lifecycle |
| Jakarta Annotations API 2.1.1 | `@PostConstruct`, `@PreDestroy` |
| Maven | Сборка проекта |

#### Ключевые классы и компоненты

| Класс | Роль |
|---|---|
| `AppConfig` | Java-конфигурация с `@Bean`-методами |
| `AppLogger` | Singleton с lifecycle-callback'ами |
| `OperationContext` | Бин для экспериментов со scope |
| `LifecyclePlaygroundApp` | Точка входа, сравнение экземпляров |

#### Изученные концепции Java/Spring

- **Bean lifecycle:** constructor → `@PostConstruct` → использование → `@PreDestroy`.
- **`@Bean`-методы** в `@Configuration`-классе.
- **`@Scope("prototype")`** и его влияние на создание экземпляров.
- **Различие singleton и prototype** при повторных вызовах `getBean()`.
- **`@PreDestroy` для prototype-бинов** — Spring не управляет полным lifecycle prototype-бина (уничтожение контекста не вызывает `@PreDestroy` у prototype).

> В `AppConfig` присутствует комментарий: *«шаг 4: потом убери эту строку и сравни поведение»* — предполагается эксперимент с удалением `@Scope("prototype")` для сравнения результатов. Текущее состояние кода — scope **включён**.

#### Структура пакета

```
hw03_beanLifecycle/
├── AppConfig.java
├── LifecyclePlaygroundApp.java
├── AppLogger.java
└── OperationContext.java
```

---

### HW04 — External Properties

**Пакет:** `moor.hw04_property`

#### Тема

Внешняя конфигурация приложения: загрузка properties-файла и инъекция значений через `@Value`.

#### Цель работы

*Вывод по коду:* научиться выносить настройки приложения во внешний файл и получать их в Spring-бинах, в том числе с использованием значений по умолчанию.

#### Что реализовано

- Файл `application.properties` с параметрами `planner.*`.
- Загрузка properties через `@PropertySource`.
- `PlannerProperties` — компонент, инкапсулирующий числовые настройки.
- `PlannerService` — сервис, читающий флаг `enabled` с дефолтным значением `false` и делегирующий остальные параметры в `PlannerProperties`.
- Вывод текущей конфигурации в консоль.

#### Технологии и инструменты

| Инструмент | Использование |
|---|---|
| Java 21 | Язык реализации |
| Spring Context 6.2.8 | `@PropertySource`, `@Value` |
| Maven | Сборка проекта |
| `application.properties` | Внешний файл конфигурации |

#### Ключевые классы и компоненты

| Класс / файл | Роль |
|---|---|
| `AppConfig` | `@PropertySource("classpath:application.properties")` |
| `PlannerProperties` | Бин с `@Value` для `default-duration` и `batch-size` |
| `PlannerService` | Бизнес-компонент с `@Value` и значением по умолчанию |
| `App` | Точка входа |
| `application.properties` | Файл конфигурации |

#### Содержимое `application.properties`

```properties
planner.enabled=true
planner.default-duration=60
planner.batch-size=10
```

#### Изученные концепции Java/Spring

- **`@PropertySource`** — подключение properties-файла к контексту.
- **`@Value("${key}")`** — инъекция значения свойства.
- **Значения по умолчанию:** `@Value("${planner.enabled:false}")`.
- **Constructor injection** для immutable-конфигурации (`PlannerProperties`).
- **Разделение конфигурации и логики** — выделение properties в отдельный компонент.

#### Структура пакета

```
hw04_property/
├── AppConfig.java
├── App.java
├── PlannerProperties.java
└── PlannerService.java

resources/
└── application.properties
```

---

## Зависимости Maven

| Зависимость | Версия | Статус использования |
|---|---|---|
| `spring-context` | 6.2.8 | Используется во всех заданиях |
| `jakarta.annotation-api` | 2.1.1 | Используется в HW03 (`@PostConstruct`, `@PreDestroy`) |
| `spring-aop` | 6.2.8 | Объявлена в `pom.xml`, **в коде не используется** |
| `aspectjweaver` | 1.9.24 | Объявлена в `pom.xml`, **в коде не используется** |

> Назначение зависимостей `spring-aop` и `aspectjweaver` по текущему коду определить нельзя — возможно, они добавлены для будущих заданий.

---

## Автор

Информация об авторе в репозитории явно не указана. Remote-репозиторий: [VikkMoor/spring-playground](https://github.com/VikkMoor/spring-playground).
