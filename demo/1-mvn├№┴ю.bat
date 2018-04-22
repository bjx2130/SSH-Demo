echo 当前目录是：%cd%  

del /f /s /q .\WebContent\WEB-INF\lib\*

echo 删除jar包  ------------------》 加载新jar

call mvn -f 2-pom.xml dependency:copy-dependencies -DoutputDirectory=.\WebContent\WEB-INF\lib



pause