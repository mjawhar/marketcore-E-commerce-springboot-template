#!/bin/bash

# Script d'optimisation des assets pour production
# Ce script optimise CSS, JS et active la compression

echo "🚀 Optimisation des assets pour production..."

# Vérifier si npm est installé
if ! command -v npm &> /dev/null
then
    echo "❌ npm n'est pas installé. Veuillez installer Node.js et npm."
    exit 1
fi

# 1. Compiler Tailwind CSS en mode production (minifié + purge)
echo "📦 1/3 - Compilation et minification du CSS Tailwind..."
npm run build:css

if [ $? -ne 0 ]; then
    echo "❌ Erreur lors de la compilation du CSS"
    exit 1
fi

# 2. Minifier les fichiers JavaScript
echo "⚡ 2/3 - Minification des fichiers JavaScript..."
for jsfile in src/main/resources/static/js/*.js; do
    if [[ -f "$jsfile" ]]; then
        filename=$(basename "$jsfile" .js)
        npx terser "$jsfile" -c -m -o "src/main/resources/static/js/${filename}.min.js"
        echo "   ✓ Minifié: ${filename}.js -> ${filename}.min.js"
    fi
done

# 3. Afficher les tailles des fichiers
echo ""
echo "📊 3/3 - Tailles des fichiers optimisés:"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

if [[ -f "src/main/resources/static/css/output.css" ]]; then
    size=$(du -h "src/main/resources/static/css/output.css" | cut -f1)
    echo "   CSS: output.css = $size"
fi

for minjs in src/main/resources/static/js/*.min.js; do
    if [[ -f "$minjs" ]]; then
        size=$(du -h "$minjs" | cut -f1)
        filename=$(basename "$minjs")
        echo "   JS:  $filename = $size"
    fi
done

echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""
echo "✅ Optimisation terminée avec succès!"
echo ""
echo "📝 Prochaines étapes:"
echo "   1. Vérifiez que les fichiers .min.js sont créés"
echo "   2. Mettez à jour layout.html pour utiliser les fichiers minifiés"
echo "   3. Activez la compression Gzip dans application.properties"
echo "   4. Déployez sur production"
echo ""

