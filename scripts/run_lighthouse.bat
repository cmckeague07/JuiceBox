@echo off
:: ==========================================================
:: Run Lighthouse Accessibility & Performance Audits
:: ==========================================================
setlocal enabledelayedexpansion

:: Get timestamp (YYYYMMDD_HHMM)
for /f "tokens=2 delims==" %%I in ('wmic os get localdatetime /value') do set datetime=%%I
set timestamp=%datetime:~0,8%_%datetime:~8,6%

:: Define output folder
set REPORT_DIR=C:\Software Testing Projects\JuiceBox-Automation\reports\lighthouse
mkdir "%REPORT_DIR%\%timestamp%" >nul 2>&1

:: Run Lighthouse audit
echo Running Lighthouse audits at %timestamp%...
lighthouse http://localhost:3000 --only-categories=accessibility,performance --chrome-flags="--headless" ^
 --output html ^
 --output json ^
 --output-path "%REPORT_DIR%\%timestamp%\lighthouse-report"

echo.
echo Lighthouse report generated at:
echo %REPORT_DIR%\%timestamp%\lighthouse-report.html
echo.

pause
