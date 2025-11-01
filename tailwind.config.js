/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/main/resources/templates/**/*.html",
    "./src/main/resources/static/js/**/*.js"
  ],
  theme: {
    extend: {
      colors: {
        primary: {
          DEFAULT: '#3b82f6',
          50: '#eff6ff',
          100: '#dbeafe',
          200: '#bfdbfe',
          300: '#93c5fd',
          400: '#60a5fa',
          500: '#3b82f6',
          600: '#2563eb',
          700: '#1d4ed8',
          800: '#1e40af',
          900: '#1e3a8a',
        },
        secondary: {
          DEFAULT: '#10b981',
          50: '#ecfdf5',
          100: '#d1fae5',
          200: '#a7f3d0',
          300: '#6ee7b7',
          400: '#34d399',
          500: '#10b981',
          600: '#059669',
          700: '#047857',
          800: '#065f46',
          900: '#064e3b',
        },
        // Couleurs pour l'interface d'administration
        admin: {
          dark: '#1e293b',      // Couleur de fond de la barre latérale (slate-800)
          accent: '#3b82f6',    // Couleur d'accent (bleu)
          primary: '#3b82f6',   // Couleur primaire (bleu)
          secondary: '#2563eb', // Couleur secondaire (bleu plus foncé)
        }
      },
      fontFamily: {
        sans: ['Segoe UI', 'Roboto', 'Helvetica Neue', 'Arial', 'sans-serif'],
      },
    },
  },
  plugins: [],
  // Optimisations pour production
  safelist: [
    // Classes dynamiques qui pourraient être purgées par erreur
    {
      pattern: /bg-(blue|green|red|yellow|orange|purple|pink|indigo)-(50|100|200|300|400|500|600|700|800|900)/,
    },
    {
      pattern: /text-(blue|green|red|yellow|orange|purple|pink|indigo)-(50|100|200|300|400|500|600|700|800|900)/,
    },
  ],
}
