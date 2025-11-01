document.addEventListener('DOMContentLoaded', function() {
    // Sélectionner tous les éléments avec la classe 'product-box'
    const productBoxes = document.querySelectorAll('.product-box');

    // Ajouter un gestionnaire d'événements de clic à chaque boîte de produit
    productBoxes.forEach(box => {
        box.addEventListener('click', function(event) {
            // Ne pas rediriger si l'utilisateur clique sur un bouton ou un lien à l'intérieur de la boîte
            if (
                event.target.closest('button') ||
                event.target.closest('a') ||
                event.target.closest('form') ||
                event.target.closest('.add-to-cart-form')
            ) {
                return; // Ne rien faire, laisser l'événement se propager normalement
            }

            // Obtenir l'URL du produit à partir de l'attribut data
            const productUrl = this.dataset.productUrl;

            // Rediriger vers la page du produit si l'URL est disponible
            if (productUrl) {
                window.location.href = productUrl;
            }
        });
    });
});

