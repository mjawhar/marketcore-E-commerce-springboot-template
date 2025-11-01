#!/bin/bash

# Script pour optimiser les images existantes sans surcharger la RAM
# Traite les images une par une pour économiser la mémoire

echo "🖼️  Optimisation des images existantes..."
echo "⚠️  Mode économie mémoire : traitement séquentiel"

# Couleurs pour l'affichage
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Chemin du bucket (à adapter selon votre configuration)
BUCKET_PATH="${CC_FS_BUCKET:-/app/bucket}"
BUCKET_PATH="${BUCKET_PATH%%:*}"

# Créer un dossier temporaire pour les images optimisées
TEMP_DIR="${BUCKET_PATH}/optimized_temp"
mkdir -p "$TEMP_DIR"

# Compteurs
total=0
optimized=0
skipped=0
errors=0

echo -e "${YELLOW}Recherche des images dans: $BUCKET_PATH${NC}"

# Traiter les images une par une (économie mémoire)
find "$BUCKET_PATH" -maxdepth 1 -type f \( -iname "*.jpg" -o -iname "*.jpeg" -o -iname "*.png" \) | while read -r img; do
    total=$((total + 1))
    filename=$(basename "$img")

    # Vérifier la taille du fichier
    size=$(stat -f%z "$img" 2>/dev/null || stat -c%s "$img" 2>/dev/null)
    size_kb=$((size / 1024))

    # Optimiser uniquement si > 200KB
    if [ $size_kb -gt 200 ]; then
        echo -e "  Optimisation: $filename (${size_kb}KB)..."

        # Utiliser ImageMagick avec limitations mémoire strictes
        # -limit memory 50MB -limit map 100MB pour économiser la RAM
        if command -v convert &> /dev/null; then
            convert "$img" \
                -limit memory 50MB \
                -limit map 100MB \
                -resize '1920x1920>' \
                -quality 88 \
                -strip \
                "$TEMP_DIR/$filename" 2>/dev/null

            if [ $? -eq 0 ]; then
                new_size=$(stat -f%z "$TEMP_DIR/$filename" 2>/dev/null || stat -c%s "$TEMP_DIR/$filename" 2>/dev/null)
                new_size_kb=$((new_size / 1024))
                saved=$((size_kb - new_size_kb))

                # Remplacer uniquement si gain significatif (>10%)
                if [ $saved -gt $((size_kb / 10)) ]; then
                    mv "$TEMP_DIR/$filename" "$img"
                    echo -e "    ${GREEN}✓ Optimisée: ${size_kb}KB → ${new_size_kb}KB (économie: ${saved}KB)${NC}"
                    optimized=$((optimized + 1))
                else
                    rm "$TEMP_DIR/$filename"
                    echo -e "    ⊘ Gain insuffisant, image conservée"
                    skipped=$((skipped + 1))
                fi
            else
                echo -e "    ✗ Erreur lors de l'optimisation"
                errors=$((errors + 1))
            fi
        else
            echo -e "${YELLOW}⚠️  ImageMagick non installé. Installation recommandée:${NC}"
            echo -e "  macOS: brew install imagemagick"
            echo -e "  Ubuntu/Debian: sudo apt-get install imagemagick"
            break
        fi
    else
        skipped=$((skipped + 1))
    fi

    # Pause courte pour éviter la surcharge
    sleep 0.1
done

# Nettoyage
rm -rf "$TEMP_DIR"

echo ""
echo -e "${GREEN}✅ Optimisation terminée !${NC}"
echo "  Total traité: $total images"
echo "  Optimisées: $optimized images"
echo "  Ignorées: $skipped images"
echo "  Erreurs: $errors images"
