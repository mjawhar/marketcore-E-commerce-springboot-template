Leonardo_Phoenix_09_une_image_riche_et_colore_qui_comporte_div_1#!/bin/bash

# Script d'optimisation pour Leonardo_Phoenix_09_une_image_riche_et_colore_qui_comporte_div_1.jpg
# Optimise l'image et crée des versions responsive

INPUT_IMAGE="images/Leonardo_Phoenix_09_une_image_riche_et_colore_qui_comporte_div_1.jpg"
OUTPUT_DIR="src/main/resources/static/img/optimized"

# Créer le dossier de sortie s'il n'existe pas
mkdir -p "$OUTPUT_DIR"

echo "🔄 Début de l'optimisation de l'image..."

# Vérifier si ImageMagick est installé
if ! command -v convert &> /dev/null; then
    echo "❌ ImageMagick n'est pas installé."
    echo "📦 Installation en cours avec Homebrew..."
    brew install imagemagick
fi

# Optimisation principale - version desktop (1920px max)
echo "📸 Création de la version desktop optimisée..."
convert "$INPUT_IMAGE" \
  -strip \
  -quality 85 \
  -resize 1920x1080\> \
  -sampling-factor 4:2:0 \
  -interlace Plane \
  "$OUTPUT_DIR/banner-desktop.jpg"

# Version tablette (768px)
echo "📱 Création de la version tablette..."
convert "$INPUT_IMAGE" \
  -strip \
  -quality 85 \
  -resize 768x \
  -sampling-factor 4:2:0 \
  -interlace Plane \
  "$OUTPUT_DIR/banner-tablet.jpg"

# Version mobile (480px)
echo "📱 Création de la version mobile..."
convert "$INPUT_IMAGE" \
  -strip \
  -quality 85 \
  -resize 480x \
  -sampling-factor 4:2:0 \
  -interlace Plane \
  "$OUTPUT_DIR/banner-mobile.jpg"

# Version WebP pour navigateurs modernes (meilleure compression)
echo "🌐 Création des versions WebP..."
convert "$INPUT_IMAGE" \
  -strip \
  -quality 80 \
  -resize 1920x1080\> \
  "$OUTPUT_DIR/banner-desktop.webp"

convert "$INPUT_IMAGE" \
  -strip \
  -quality 80 \
  -resize 768x \
  "$OUTPUT_DIR/banner-tablet.webp"

convert "$INPUT_IMAGE" \
  -strip \
  -quality 80 \
  -resize 480x \
  "$OUTPUT_DIR/banner-mobile.webp"

# Afficher les tailles des fichiers
echo ""
echo "✅ Optimisation terminée !"
echo "📊 Comparaison des tailles :"
echo ""
echo "Original:"
ls -lh "$INPUT_IMAGE" | awk '{print $5 "\t" $9}'
echo ""
echo "Optimisées:"
ls -lh "$OUTPUT_DIR"/banner-* | awk '{print $5 "\t" $9}'
echo ""
echo "📁 Images sauvegardées dans: $OUTPUT_DIR"

