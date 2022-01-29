- POST /employee сохраняет нового сотрудника принимает JSON обьект в виде:
{“firstName”: String,
“lastName”: "String,
“phoneNumber”: String } номер телефона должен состоять из цифр и иметь длину от 10 до 25 символов

- GET /employee весь список сотрудников
- GET /employee/{depId} отфильтрованный по отделу список, принимает параметр id отдела
- DELETE /employee/{id} удаляет запись сотрудника, принимает параметр id сотрудника
- PUT /addDepartment/{id}/{depId} добавляет сотрудника в отдел, принимает id сотрудника и id отдела
- PUT /deleteFromDepartment/{id} удаляет сотрудника из отдела, принимает id сотрудника
- POST /department создает запись отдела, принимает JSON обьект в виде: {“name”: String }
