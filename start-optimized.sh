#!/bin/bash

# Script de démarrage optimisé pour éviter les fuites mémoire
# Utilisation: ./start-optimized.sh

echo "🚀 Démarrage de MarketCore avec optimisations mémoire..."

# Paramètres JVM optimisés
JAVA_OPTS="-Xms512m \
-Xmx1024m \
-XX:+UseG1GC \
-XX:MaxGCPauseMillis=200 \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=./heapdump.hprof \
-XX:+ExitOnOutOfMemoryError \
-XX:MaxMetaspaceSize=256m \
-XX:+DisableExplicitGC \
-Dspring.profiles.active=prod"

echo "Paramètres JVM:"
echo "$JAVA_OPTS"
echo ""

# Vérifier si le JAR existe
JAR_FILE="target/marketcore-pro-v3-0.0.1-SNAPSHOT.jar"
if [ ! -f "$JAR_FILE" ]; then
    echo "❌ Le fichier JAR n'existe pas. Compilation en cours..."
    ./mvnw clean package -DskipTests
fi

echo "✅ Démarrage de l'application..."
java $JAVA_OPTS -jar $JAR_FILE

