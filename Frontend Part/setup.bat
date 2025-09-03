@echo off
echo ========================================
echo Hospital Management System - Setup
echo ========================================
echo.

echo Installing dependencies...
npm install

echo.
echo Installing Angular Material...
ng add @angular/material --skip-confirmation

echo.
echo Installing Bootstrap...
npm install bootstrap @popperjs/core

echo.
echo Setup complete! 
echo.
echo To start the development server, run:
echo   ng serve
echo.
echo The application will be available at:
echo   http://localhost:4200
echo.
pause
