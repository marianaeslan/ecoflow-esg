FROM eclipse-temurin:25-jdk AS build

WORKDIR /build

# Copy Maven wrapper and configuration files first (for layer caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Pre-download dependencies
RUN chmod +x mvnw && ./mvnw -B -q dependency:go-offline

# Copy source code and build
COPY src src
RUN ./mvnw -B -q clean package -DskipTests

FROM eclipse-temurin:25-jre

WORKDIR /app

# Install curl for healthcheck
RUN apt-get update && apt-get install -y --no-install-recommends curl && apt-get clean && rm -rf /var/lib/apt/lists/*

# Create non-root user with home directory
RUN useradd -m -u 9000 ecoflow

# Copy the JAR from build stage
COPY --from=build /build/target/ecoflow-0.0.1-SNAPSHOT.jar /app/app.jar

# Create logs directory with proper permissions
RUN mkdir -p /app/logs && chown -R ecoflow:ecoflow /app/logs /app/app.jar

# Set Java options for container memory limits
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75"

# Expose API port
EXPOSE 8080

# Health check
HEALTHCHECK --start-period=90s --interval=30s --timeout=10s --retries=3 \
    CMD curl -fs http://localhost:8080/actuator/health || exit 1

# Switch to non-root user
USER ecoflow

# Entry point
ENTRYPOINT ["sh","-c","exec java $JAVA_OPTS -jar /app/app.jar"]
