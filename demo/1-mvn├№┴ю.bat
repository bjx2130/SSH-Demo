echo ��ǰĿ¼�ǣ�%cd%  

del /f /s /q .\WebContent\WEB-INF\lib\*

echo ɾ��jar��  ------------------�� ������jar

call mvn -f 2-pom.xml dependency:copy-dependencies -DoutputDirectory=.\WebContent\WEB-INF\lib



pause