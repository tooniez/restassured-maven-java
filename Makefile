.PHONY: install test clean package

install:
	mvn install -f pom.xml

test:
	mvn test -f pom.xml -X

clean:
	mvn clean -f pom.xml

package:
	mvn package -f pom.xml