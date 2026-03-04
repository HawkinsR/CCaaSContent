# Demo: Dockerizing a Spring Boot Microservice

## Phase 1: The Concept

**Time:** 5 mins

1. **Context:** The old way of deploying Java apps was handing an Operations engineer a massive `.jar` file and telling them, "Make sure the server has Java 17 installed." If they had Java 8 installed, the app crashed ("It works on my machine!").
2. **The Container Solution:** Docker allows us to package the application *and* the precise Operating System/Java runtime environment it requires into a single, immutable box.

## Phase 2: The Code (Live Implementation)

**Time:** 15 mins

1. Open `code/Dockerfile`.
2. **Walkthrough the Code:**
    * `FROM openjdk:17-jdk-alpine`: Explain that this is the base Operating System. It's a tiny Linux distribution with exactly Java 17 installed.
    * `EXPOSE 8080`: Documentation stating this container expects traffic on port 8080.
    * `COPY target/*.jar app.jar`: We compiled our code locally using Maven. We are now copying that `.jar` INTO the Linux box.
    * `ENTRYPOINT`: The exact terminal command the Linux box runs when it boots up (`java -jar /app.jar`).
3. **Run the App (Terminal):**
    * Run `docker build -t my-microservice:1.0 .` to build the Image.
    * Run `docker run -p 8080:8080 my-microservice:1.0` to start the Container.
    * Navigate to `localhost:8080` to verify the Spring Boot app is running purely from inside the Docker engine.
