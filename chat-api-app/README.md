# Build
## developing 
if you change the jdk don't forget to change the version in the Dockerfile
```bash
  . ./setup_env.bash
  
  # bring mysql up. adminer can be accessed at:
  # http://localhost:48080/?server=db&username=user1&db=testdb
  docker-compose up
  ./mvnw spring-boot:run -Dspring.profiles.active=dev
  # the graphqli client should be avaialbe at http://localhost:8080/graphiql
```



# testing
https://github.com/springboot-testcontainer/springboot-testcontainer-mysql

#Docker
see https://github.com/spotify/dockerfile-maven

to disable docker building use the switch -Ddockerfile.skip

```bash
	./mvnw clean package
	docker images
	#in my case version is 0.0.1-SNAPSHOT
	docker run -it --rm --network=host -e _JAVA_OPTIONS="-Dspring.profiles.active=dev"  refresh2019/chat-api-app:0.0.1-SNAPSHOT 

```