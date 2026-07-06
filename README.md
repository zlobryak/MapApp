
# Используемые библиотеки и зависимости

## Плагины Gradle

| Плагин | Назначение |
|--------|------------|
| `android.application` | Базовый плагин для сборки Android-приложения |
| `kotlin.android` | Поддержка языка Kotlin в проекте |
| `kotlin-parcelize` | Автоматическая реализация `Parcelable` через аннотацию `@Parcelize` для передачи объектов между экранами |
| `com.google.dagger.hilt.android` | Плагин Hilt для автоматической генерации кода внедрения зависимостей (DI) |
| `kotlin.ksp` | Kotlin Symbol Processing — современная замена KAPT для обработки аннотаций (Room, Hilt). Работает быстрее и потребляет меньше памяти |

## Основные зависимости

### Базовые Android-компоненты

- **`androidx.core.ktx`** — Kotlin-расширения для Android Core API (упрощают работу с SharedPreferences, Bundle и т.д.)
- **`androidx.appcompat`** — поддержка старых версий Android и базовые компоненты (AppCompatActivity, темы)
- **`androidx.constraintlayout`** — гибкий Layout-менеджер для построения сложных UI-иерархий с плоской структурой
- **`androidx.activity`** — современные API для Activity (например, `ActivityResultContracts` для запроса разрешений)
- **`androidx.fragment`** — современные API для Fragment, включая делегат `by viewModels()` для работы с ViewModel

### UI

- **`material`** — библиотека Material Design. Используется для `MaterialButton`, `FloatingActionButton`, `TextInputLayout`, тем и стилей

### Карты

- **`maps.mobile`** — Yandex MapKit. Основной SDK для отображения карт, маркеров, работы с геолокацией

> **Почему Yandex MapKit, а не Google Maps?**  
> Приложение ориентировано на путешествия по России. Yandex Maps обеспечивает лучшую детализацию российских городов, достопримечательностей и инфраструктуры по сравнению с Google Maps.

### База данных

- **`androidx.room`** — ORM-библиотека от Google для работы с SQLite. Позволяет описывать базу данных через аннотации (`@Entity`, `@Dao`, `@Database`) и работать с ней через типобезопасные Kotlin-объекты
- **`androidx.room.compiler`** — аннотационный процессор Room (через KSP), который генерирует реализацию БД (`AppDb_Impl`) и DAO во время компиляции

### Внедрение зависимостей (DI)

- **`hilt.android`** — библиотека Hilt (обертка над Dagger) для автоматического внедрения зависимостей. Упрощает передачу `Repository` в `ViewModel`, `Dao` в `Repository`, `Database` в `Dao`
- **`hilt.android.compiler`** — аннотационный процессор Hilt, генерирующий код DI

### Lifecycle и асинхронность

- **`lifecycle.livedata.ktx`** — расширения для работы с `LiveData` и `ViewModel`. Включает функции `asLiveData()` (конвертация Flow → LiveData) и `viewModelScope` (CoroutineScope для запуска корутин внутри ViewModel)

##  Архитектура проекта

Проект построен по паттерну **MVVM (Model-View-ViewModel)** с использованием **Repository Pattern**:
