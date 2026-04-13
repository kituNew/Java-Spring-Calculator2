# ДЗ №2 — калькулятор со SpEL и настройками

## Что реализовано

- вычисление арифметических выражений через SpEL;
- поддержка нескольких чисел, скобок и операций `+ - * /`;
- вычисление факториала;
- приветствие пользователя из настроек;
- пользователь задаётся в настройках строкой JSON или CSV и внедряется как объект `User` через `@Value`;
- максимальная длина выражения задаётся в настройках и реально проверяется;
- обработка ошибок ввода и вычислений.

## Настройки

Используется `application.yml`:

```yml
app:
  user: '{"name":"Влад","salutation":"милорд"}'
  expression:
    max-length: 100
```

Можно заменить JSON на CSV:

```yml
app:
  user: 'Влад,милорд'
```

## Основные классы

- `model/User`, `model/Salutation`
- `config/UserConverter` — преобразование строки из настроек в `User`
- `config/ConversionConfig` — регистрация конвертера для `@Value`
- `config/SpelConfig` — `SpelExpressionParser`
- `service/GreetingService`
- `service/ExpressionEvaluator`
- `service/FactorialService`
- `console/ConsoleCalculatorRunner`
- `controller/OperationController`

## REST-эндпоинты

- `GET /calc/expression?value=(2+3)*4`
- `GET /calc/factorial?value=5`
- `GET /calc/greeting`

## Как запускать

### Консольный режим

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="console"
```

### Веб-режим

```bash
mvn spring-boot:run
```

