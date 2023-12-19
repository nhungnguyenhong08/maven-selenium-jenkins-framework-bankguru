set ProjectPath=%~dp0
cd %ProjectPath%
echo %ProjectPath% 
set p=%PATH%
java -javaagent:"%ProjectPath%\libAllure\aspectjweaver-1.9.8.jar" -classpath "%ProjectPath%bin;%ProjectPath%libAllure\*;%ProjectPath%libExtendV2\*;%ProjectPath%libExtendV3\*;%ProjectPath%libExtendV4\*;%ProjectPath%libExtendV5\*;%ProjectPath%libLog4Jver2\*;%ProjectPath%libLog4Jver2\*;;%ProjectPath%libReportNG\*;%ProjectPath%libSelenium\*;%ProjectPath%libWebDriverManager\*;" org.testng.TestNG "%ProjectPath%bin\runNopCommerceUser.xml"
allure serve .\allure-json\
pause