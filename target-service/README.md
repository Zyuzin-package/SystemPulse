# target-server
Сервис заглушка для тестирования библиотеки. 
Представляет собой веб сервер который переодически генерирует разные ошибки


Запросы для триггера ошибок


curl.exe -i http://localhost:8081/demo/custom-exception
curl.exe -i http://localhost:8081/demo/error
curl.exe -i http://localhost:8081/demo/exception
