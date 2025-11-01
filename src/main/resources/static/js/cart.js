/**
 * Script pour gérer la fonctionnalité d'ajout au panier avec AJAX
 */
document.addEventListener('DOMContentLoaded', function() {
    // Intercepter les formulaires d'ajout au panier
    document.addEventListener('submit', function(e) {
        const form = e.target;

        // Vérifier si c'est un formulaire d'ajout au panier en utilisant la classe spécifique
        if (form.classList.contains('add-to-cart-form')) {
            e.preventDefault();

            // Récupérer la quantité du formulaire
            const qtyInput = form.querySelector('input[name="qty"]');
            const qty = qtyInput ? qtyInput.value : 1;

            // Récupérer le token CSRF
            const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
            const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

            // Envoyer la requête AJAX
            fetch(form.action, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'Accept': 'application/json',
                    [csrfHeader]: csrfToken
                },
                body: new URLSearchParams({
                    qty: qty
                })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erreur réseau');
                }
                return response.json();
            })
            .then(data => {
                if (data.success) {
                    // Mettre à jour le compteur du panier
                    updateCartCount();

                    // Afficher la popup avec les détails du produit
                    showCartPopup(data);
                }
            })
            .catch(error => {
                console.error('Erreur lors de l\'ajout au panier:', error);
            });
        }
    });

    // Fonctions pour gérer la popup du panier
    function showCartPopup(data) {
        // Mettre à jour les détails du produit dans la popup
        document.getElementById('cart-popup-product-name').textContent = data.productName;
        document.getElementById('cart-popup-quantity').textContent = data.quantity;
        document.getElementById('cart-popup-price').textContent = formatPrice(data.productPrice);

        // Mettre à jour l'image du produit
        const imgElement = document.getElementById('cart-popup-image');
        // Construire le chemin complet de l'image basé sur l'ID du produit
        const imagePath = `/images/${data.productId}/1`;
        imgElement.src = imagePath;
        imgElement.onerror = function() {
            // En cas d'erreur de chargement, utiliser l'image par défaut
            this.src = '/img/placeholder.png';
        }

        // Afficher la popup
        const popup = document.getElementById('cart-popup');
        popup.classList.remove('hidden');
        document.body.style.overflow = 'hidden';
    }

    // Fonction pour formater le prix
    function formatPrice(price) {
        return new Intl.NumberFormat('fr-FR', {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        }).format(price) + ' DT';
    }

    // Fonction pour mettre à jour le compteur du panier
    function updateCartCount() {
        fetch('/cart/count', {
            headers: {
                'Accept': 'application/json'
            }
        })
        .then(response => response.json())
        .then(data => {
            const cartCount = document.getElementById('cart-count');
            if (cartCount) {
                const count = parseInt(data.count);
                cartCount.textContent = count;

                // Afficher ou masquer le badge selon le nombre d'articles
                if (count <= 0) {
                    cartCount.style.display = 'none';
                } else {
                    cartCount.style.display = 'flex';
                }
            }
        })
        .catch(error => {
            console.error('Erreur lors de la récupération du nombre d\'articles dans le panier:', error);
        });
    }

    // Gestion de la fermeture de la popup
    const closeCartPopupBtn = document.getElementById('close-cart-popup');
    const cartPopupOverlay = document.getElementById('cart-popup-overlay');
    const cartPopupContinue = document.getElementById('cart-popup-continue');

    if (closeCartPopupBtn) {
        closeCartPopupBtn.addEventListener('click', closeCartPopup);
    }

    if (cartPopupOverlay) {
        cartPopupOverlay.addEventListener('click', closeCartPopup);
    }

    if (cartPopupContinue) {
        cartPopupContinue.addEventListener('click', closeCartPopup);
    }

    function closeCartPopup() {
        const popup = document.getElementById('cart-popup');
        if (popup) {
            popup.classList.add('hidden');
            document.body.style.overflow = '';
        }
    }
});
