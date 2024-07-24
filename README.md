# VK Bot

Этот проект реализует простого чат-бота для ВКонтакте на Java с использованием Spring Boot. Бот отвечает на присланный ему текст, цитируя его.

## Требования

- Java 11+
- Maven
- Токен доступа ВКонтакте с разрешениями `messages`
- Ngrok (для тестирования локально)

## Установка

1. Клонируйте репозиторий:

    ```bash
    git clone https://github.com/ToxicButterfly/VKBot
    cd VKBot
    ``` 
   
2. Обновите файл `application.properties` в директории `src/main/resources` добавив свои данные:

    ```properties
    vk.api.token=YOUR_ACCESS_TOKEN
    vk.group.id=YOUR_GROUP_ID
    vk.api.confirmation.code=YOUR_CONFIRMATION_CODE
    vk.api.version=YOUR_API_VERSION
    ```
3. Создайте сборку Maven:

    ```bash
    mvn clean install
    ```

## Запуск

1. Запустите Spring Boot приложение:

    ```bash
    mvn spring-boot:run
    ```

2. Запустите Ngrok для туннелирования локального сервера:

    ```bash
    ngrok http 8080
    ```

3. Скопируйте HTTPS URL, предоставленный Ngrok, и настройте URL обработчика событий в вашем сообществе ВКонтакте в разделе "Настройки -> Callback API".

## Использование

После настройки и запуска бота, он будет отвечать на сообщения, отправленные ему в личные сообщения и в групповые беседы.
