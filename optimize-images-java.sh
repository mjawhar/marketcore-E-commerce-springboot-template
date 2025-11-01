#!/bin/bash

# Script pour optimiser les images existantes en utilisant Java + Thumbnailator
# Fonctionne partout (pas besoin d'ImageMagick)

echo "🖼️  Optimisation des images existantes avec Java..."
echo ""

# Aller dans le dossier du projet
cd "$(dirname "$0")"

# Compiler si nécessaire
if [ ! -d "target/classes" ]; then
    echo "📦 Compilation du projet..."
    mvn compile -DskipTests -q
fi

# Exécuter l'optimiseur Java
echo "🚀 Lancement de l'optimisation..."
echo ""

# Définir le classpath
if [ -f "target/marketcore-pro-v3-0.0.1-SNAPSHOT.jar" ]; then
    # Si JAR existe, l'utiliser
    java -Xmx256m -cp target/marketcore-pro-v3-0.0.1-SNAPSHOT.jar com.marketcore.util.ImageOptimizer
else
    # Sinon, utiliser les classes compilées
    mvn exec:java -Dexec.mainClass="com.marketcore.util.ImageOptimizer" -Dexec.cleanupDaemonThreads=false -q
fi

echo ""
echo "✅ Terminé !"

