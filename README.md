# Spring Boot + AWS Secrets Manager Demo

A sample project demonstrating how to integrate Spring Boot with AWS Secrets Manager to dynamically load secrets (e.g., database credentials) without hardcoding them in `application.yml`.

## Features
- Loads secrets from AWS Secrets Manager at startup
- Auto-refreshes secrets via Spring Actuator's `/refresh` endpoint
- Demonstrates database connection with dynamic credentials

## Prerequisites
- Java 17+
- AWS account (or LocalStack for local testing)
- MySQL (or compatible database)
- AWS CLI configured (for secret creation)

## Setup

### 1. Create a Secret in AWS Secrets Manager

```bash
awslocal secretsmanager create-secret \
    --name db/credentials \
    --secret-string '{
      "db-url":"jdbc:mysql://localhost:3306/secrets_manager?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
      "db-username":"root",
      "db-password":"root",
      "db-engine":"mysql",
      "db-host":"localhost",
      "db-port":"3306",
      "db-name":"secrets_manager"
  }'
```

### 2. Change secret password to test refresh

```bash
awslocal secretsmanager put-secret-value \
    --secret-id db/credentials \
    --secret-string '{
      "db-url":"jdbc:mysql://localhost:3306/secrets_manager?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
      "db-username":"root",
      "db-password":"newPassword",
      "db-engine":"mysql",
      "db-host":"localhost",
      "db-port":"3306",
      "db-name":"secrets_manager"
  }'
```