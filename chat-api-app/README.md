# Build
## setup
```bash
  ~/.jabba/bin/jabba install openjdk@1.11.0
  export JAVA_HOME=$( ~/.jabba/bin/jabba which openjdk@1.11.0)
  export PATH="$JAVA_HOME/bin:$PATH"
  
  # bring mysql up. adminer can be accessed at:
  # http://localhost:48080/?server=db&username=user1&db=testdb
  docker-compose up
  ./mvnw spring-boot:run -Dspring.profiles.active=dev 
```
# testing
https://github.com/springboot-testcontainer/springboot-testcontainer-mysql
