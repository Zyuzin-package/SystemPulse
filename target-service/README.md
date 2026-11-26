# target-server
Сервис заглушка для тестирования библиотеки. 
Представляет собой веб сервер который переодически генерирует разные ошибки


Запросы для триггера ошибок


curl.exe -i http://localhost:8080/demo/custom-exception
curl.exe -i http://localhost:8080/demo/error
curl.exe -i http://localhost:8080/demo/exception
