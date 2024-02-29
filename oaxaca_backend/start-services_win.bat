@echo off
REM This script assumes that Maven is installed on your machine and in your PATH variable.

start cmd /k "cd .\shared_library && mvn install && echo done > done"

:wait
if not exist ".\shared_library\done" goto wait

del ".\shared_library\done"
start cmd /k "cd .\cart_service && mvn spring-boot:run"
start cmd /k "cd .\customer_service && mvn spring-boot:run"
start cmd /k "cd .\kitchen_staff_service && mvn spring-boot:run"
start cmd /k "cd .\menu_service && mvn spring-boot:run"
start cmd /k "cd .\waiter_service && mvn spring-boot:run"