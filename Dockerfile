FROM adoptopenjdk/openjdk8
LABEL maintainer="ashwin.net"
ADD target/inventory-management-system-0.0.1-SNAPSHOT.jar inventory-management-system.jar
ENTRYPOINT ["java","-jar","inventory-management-system.jar"]