set -o allexport

source .env

set +o allexport

./gradlew "$@"