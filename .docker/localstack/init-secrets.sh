#!/bin/bash

# Configuration
LOCALSTACK_URL="http://localhost:4566"
SECRET_NAME="db/credentials"
REGION="us-east-1"

# Database credentials (in a real scenario, these might come from environment variables)
DB_SECRETS=$(cat <<EOF
{
  "db-url": "jdbc:mysql://localhost:3306/secrets_manager?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true",
  "db-username": "root",
  "db-password": "root",
  "db-engine": "mysql",
  "db-host": "localhost",
  "db-port": "3306",
  "db-name": "secrets_manager"
}
EOF
)

# Create the secret in Secrets Manager
aws --endpoint-url="$LOCALSTACK_URL" secretsmanager create-secret \
  --name "$SECRET_NAME" \
  --description "Database credentials" \
  --secret-string "$DB_SECRETS" \
  --region "$REGION"

# Verify the secret was created
if [ $? -eq 0 ]; then
  echo "Secret '$SECRET_NAME' created successfully."
  echo "You can retrieve it with:"
  echo "awslocal --endpoint-url=$LOCALSTACK_URL secretsmanager get-secret-value --secret-id $SECRET_NAME --region $REGION"
else
  echo "Failed to create secret."
  exit 1
fi