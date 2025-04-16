# Global Arguments
## Versions
ARG BUILD_GRADLE_VERSION=8.13
ARG BUILD_JDK_VERSION=jdk21
ARG DEPLOY_JDK_VERSION=21-jre-alpine

# Setting Up Builder Image
## Pulling Image
FROM gradle:${BUILD_GRADLE_VERSION}-${BUILD_JDK_VERSION} AS build

# Building Application
## Creating Working Directory
WORKDIR /app

## Copy Gradle & Project Files
COPY gradle/wrapper/ gradle/wrapper/
COPY build.gradle settings.gradle ./
COPY payment-service/ payment-service/
COPY common/ common/

## Build & Test Project
RUN gradle :payment-service:build --no-daemon

# Setting Up Deployable Image
## Pulling Image
FROM eclipse-temurin:${DEPLOY_JDK_VERSION} AS deploy

# Metadata
## Application Properties
ARG ARTIFACT_NAME=payment-service
ARG APPLICATION_VERSION=1.0-SNAPSHOT

## Container Labels
LABEL multi.name="Payment Service" \
      multi.type="API" \
      version=${APPLICATION_VERSION} \
      description="A microservice for handling payment data"

# Container Security
## Creating Non-Root User
ARG USER_ID=1001
ARG USER_NAME=appuser
ARG GROUP_ID=${USER_ID}
ARG GROUP_NAME=appgroup
RUN addgroup --gid ${GROUP_ID} ${GROUP_NAME} \
    && adduser \
    --gecos "" \
    --uid ${USER_ID} \
    --ingroup ${GROUP_NAME} \
    --no-create-home \
    --disabled-password \
    ${USER_NAME}

## Switching to User
USER ${USER_NAME}

# Installing Application
## Creating Working Directory
WORKDIR /app

## Retrieving Build Artifact
COPY --from=build /app/${ARTIFACT_NAME}/build/libs/${ARTIFACT_NAME}-${APPLICATION_VERSION}.jar ./${ARTIFACT_NAME}.jar

# Running Application
## Exposing Application Port
EXPOSE 8082

## Starting Application
ENTRYPOINT ["java", "-jar", "payment-service.jar"]
