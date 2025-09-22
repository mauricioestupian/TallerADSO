dependencias para pruebas automatizadas
asegurar que el pom.xml contenga
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-test</artifactId>
<scope>test</scope>
</dependency>
Incluye JUnit 5, Mockito, AssertJ, Hamcrest, y utilidades para MockMvc.
