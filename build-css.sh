#!/bin/bash

# Script pour compiler le CSS Tailwind avant le déploiement
# Exécutez ce script avant de pousser votre code en production

echo "🎨 Compilation du CSS Tailwind..."

# Vérifier si npm est installé
if ! command -v npm &> /dev/null
then
    echo "❌ npm n'est pas installé. Veuillez installer Node.js et npm."
    exit 1
fi

# Compiler le CSS en mode production (minifié)
npm run build:css

if [ $? -eq 0 ]; then
    echo "✅ CSS compilé avec succès !"
    echo "📦 Le fichier output.css est prêt pour la production"
    echo ""
    echo "Fichier généré : src/main/resources/static/css/output.css"
    echo ""
    echo "Vous pouvez maintenant :"
    echo "1. Commiter le fichier output.css"
    echo "2. Pousser vers votre dépôt Git"
    echo "3. Déployer sur le serveur de production"
else
    echo "❌ Erreur lors de la compilation du CSS"
    exit 1
fi

