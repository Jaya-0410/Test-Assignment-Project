cd %cd%
@echo off
FOR /F "delims=\" %%i IN ('cd') DO set ADDRESS=%%~nxi
cd..
allure open %ADDRESS%
end