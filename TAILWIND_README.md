# Configuration Tailwind CSS

Ce projet utilise Tailwind CSS en mode local. Le CSS est compilé **en local** et déployé directement sur le serveur de production (qui n'a pas besoin de npm).

## 🚀 Workflow de déploiement (IMPORTANT)

### Avant chaque déploiement en production :

1. **Compiler le CSS en local** :
   ```bash
   ./build-css.sh
   # OU
   npm run build:css
   ```

2. **Commiter le fichier CSS compilé** :
   ```bash
   git add src/main/resources/static/css/output.css
   git commit -m "Update compiled CSS"
   ```

3. **Pousser vers Git et déployer** :
   ```bash
   git push
   ```

Le fichier `output.css` compilé est **inclus dans le dépôt Git** pour que le serveur de production puisse l'utiliser directement sans npm.

## 📁 Structure des fichiers

- **Configuration** : `tailwind.config.js` - Configuration Tailwind (couleurs, breakpoints, etc.)
- **CSS source** : `src/main/resources/static/css/input.css` - Fichier source avec les directives Tailwind
- **CSS compilé** : `src/main/resources/static/css/output.css` - Fichier généré (minifié, prêt pour la production)
- **Script de build** : `build-css.sh` - Script pour compiler le CSS facilement

## 💻 Développement local

### Installation (première fois uniquement)
```bash
npm install
```

### Mode développement (avec watch)
Pour compiler automatiquement le CSS à chaque modification :

```bash
npm run watch:css
```

### Mode production (minifié)
Pour compiler le CSS une seule fois en mode production :

```bash
npm run build:css
# OU
./build-css.sh
```

## 🎨 Personnalisation

Vous pouvez personnaliser les couleurs et autres paramètres dans `tailwind.config.js`. Les couleurs personnalisées actuelles sont :

- **primary** : Bleu (utilisé pour les boutons principaux, liens, etc.)
- **secondary** : Vert (utilisé pour les actions secondaires)

## ⚠️ Notes importantes

- Le fichier `output.css` est **inclus dans Git** pour le déploiement en production
- Le serveur de production n'a **pas besoin de npm**
- Tous vos styles personnalisés doivent être ajoutés dans `input.css`
- Les classes Tailwind utilisées dans vos templates seront automatiquement incluses dans le fichier final
- **N'oubliez jamais** de compiler le CSS avant de déployer en production !

## 📦 Fichiers à versionner

✅ **À inclure dans Git** :
- `tailwind.config.js`
- `package.json`
- `src/main/resources/static/css/input.css`
- `src/main/resources/static/css/output.css` ← **IMPORTANT pour la production**
- `build-css.sh`

❌ **À exclure de Git** :
- `node_modules/`
- `package-lock.json`
