# 🔄 Guide : Que faire après avoir modifié un fichier HTML

## 📝 Workflow simple

### Quand vous modifiez UNIQUEMENT le HTML (pas de nouvelles classes Tailwind)

Si vous ajoutez juste du contenu, modifiez du texte, ou utilisez des classes Tailwind qui existent déjà :

```bash
# Rien à faire ! 
# Rechargez simplement votre navigateur (F5 ou Cmd+R)
```

---

### Quand vous ajoutez de NOUVELLES classes Tailwind

Si vous utilisez de nouvelles classes Tailwind qui n'existaient pas avant (ex: `bg-purple-600`, `text-3xl`, etc.) :

**1️⃣ Recompiler le CSS :**
```bash
npm run build:css
# OU
./build-css.sh
```

**2️⃣ Rechargez votre navigateur**

**3️⃣ Avant de déployer en production :**
```bash
git add src/main/resources/static/css/output.css
git commit -m "Update CSS with new Tailwind classes"
git push
```

---

## 🎯 En résumé

| Action | CSS à recompiler ? | Commande |
|--------|-------------------|----------|
| Modifier du texte HTML | ❌ Non | Juste recharger le navigateur |
| Changer un `th:text` | ❌ Non | Juste recharger le navigateur |
| Réorganiser des éléments | ❌ Non | Juste recharger le navigateur |
| Utiliser classes existantes | ❌ Non | Juste recharger le navigateur |
| **Nouvelles classes Tailwind** | ✅ **OUI** | `npm run build:css` |
| Modifier `input.css` | ✅ **OUI** | `npm run build:css` |
| Modifier `tailwind.config.js` | ✅ **OUI** | `npm run build:css` |

---

## 💡 Astuce : Mode développement

Pour éviter de recompiler manuellement à chaque fois, utilisez le mode **watch** :

```bash
npm run watch:css
```

Ce mode surveille vos fichiers et recompile automatiquement le CSS dès que vous ajoutez de nouvelles classes Tailwind !

---

## 🚀 Déploiement en production

**N'oubliez JAMAIS cette étape avant de déployer :**

1. Compiler le CSS :
   ```bash
   ./build-css.sh
   ```

2. Commiter le fichier CSS :
   ```bash
   git add src/main/resources/static/css/output.css
   git commit -m "Update compiled CSS"
   git push
   ```

---

## 🔍 Comment savoir si je dois recompiler ?

**Recompiler SI :**
- ✅ Vous voyez que vos nouveaux styles ne s'appliquent pas
- ✅ Vous avez utilisé une classe Tailwind que vous n'aviez jamais utilisée avant
- ✅ Vous avez modifié la configuration Tailwind
- ✅ Vous avez ajouté des styles personnalisés dans `input.css`

**PAS besoin de recompiler SI :**
- ❌ Vous changez juste du contenu (texte, images, liens)
- ❌ Vous réutilisez des classes Tailwind déjà présentes dans d'autres fichiers
- ❌ Vous modifiez la logique Thymeleaf (`th:if`, `th:each`, etc.)

---

## 📋 Checklist avant de déployer

- [ ] Le site fonctionne bien en local
- [ ] J'ai testé sur mobile (responsive)
- [ ] J'ai compilé le CSS : `./build-css.sh`
- [ ] J'ai committé le fichier CSS compilé
- [ ] J'ai poussé vers Git
- [ ] Prêt pour la production ! 🎉

