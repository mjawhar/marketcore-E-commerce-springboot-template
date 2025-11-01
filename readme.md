# MarketCore – README détaillé

> **Frameworks principaux :** Spring Boot 3 / Spring 6, Spring Security 6, Spring Data JPA, Thymeleaf 3.1, TailwindCSS (classes utilitaires dans les templates)
>
> projet is live on for test : 
> [https://www.demo.marketcore.tech](https://www.demo.marketcore.tech)
> Youtube: 
> [https://youtu.be/ULajONYnKfw](https://youtu.be/ULajONYnKfw)

> ⚠️ **Note importante :** Cette version sur Git est une **version lite** destinée à explorer le projet. Pour obtenir la **version complète** avec toutes les fonctionnalités avancées, rendez-vous sur : [https://jaouher6.gumroad.com/l/kwwbic](https://jaouher6.gumroad.com/l/kwwbic)


---
## 📚 Table of Contents

1. [Overview & Features](#overview--features)
2. [Technical Architecture](#technical-architecture)
3. [Prerequisites](#prerequisites)
4. [Installation & Quick Start](#installation--quick-start)
5. [Configuration (application.properties / .yml)](#configuration-applicationproperties--yml)
6. [Roles & Account Management](#roles--account-management)
7. [Main Modules Functionality](#main-modules-functionality)
8. [Useful Commands](#useful-commands)
9. [Demo Data / Seed](#demo-data--seed)
10. [Troubleshooting (common errors)](#troubleshooting-common-errors)
11. [Roadmap / Improvement Ideas](#roadmap--improvement-ideas)
12. [License & Credits](#license--credits)

---

## Overview & Features

### Public Side

* Browse catalog (product list, keyword search).
* Product page with detailed information (title, description, price, seller, category...).
* Shopping cart for "logged-in users" stored in database.
* Update quantities, remove items.
* Checkout (order validation) with cart retrieval and total display.
* Currency management via `currencySymbol` variable and amount formatting.
* Personal seller space: each user can activate their "seller" profile, publish products and track orders.

### Seller Side / "Seller" Space

Seller dashboard with:

* List of products and associated orders.
* Create / edit / delete products.
* Sales status and tracking.
* Role-restricted access (ROLE_SELLER) via Spring Security.

### Back-office / Administration

* CRUD Categories.
* CRUD Products **with seller selection**.
* Order list and line details.
* User & role management (ADMIN, SELLER, USER).
* Search / filtering (e.g., by product name).

### Security / Authentication

* Spring Security 6: Login/Logout, role management, route restrictions.
* CSRF protection by default (Thymeleaf forms).

### Miscellaneous

* Modular Thymeleaf templates (layout fragment, header/footer fragments).
* TailwindCSS usage (utility classes directly in HTML – no build needed if using CDN or pre-compiled version).
* Server-side validation (Bean Validation @Valid) + BindingResult.

---

### 🗺️ Automatic Sitemap Generation

The project includes a dynamic `sitemap.xml` generation module.  
Whenever a product, category or main page is added or modified, the sitemap is automatically regenerated and exposed at the URL.

- **Automatic meta tags**: dynamic generation of `<title>` and `<meta name="description">` tags for product and category pages.  
  The title and description are built from database data (product name, description excerpt, category name), to optimize SEO without manual configuration.

### 🚀 Quality & Performance

The project has been audited with **Google Lighthouse**:

| Criteria | Score |
|----------|-------|
| ⚡ Performance | 76 / 100 |
| ♿ Accessibility | 90 / 100 |
| ✅ Best Practices | 100 / 100 |

These results demonstrate code quality, compliance with web standards (semantic HTML, security, HTTPS, etc.) and the lightweight nature of the Thymeleaf/Tailwind architecture.

---

## Technical Architecture

```
src/
 ├── main/java/
 │   └── com.example.marketcore
 │        ├── controller/         # Web Controllers (MVC)
 │        ├── service/            # Business Services
 │        ├── repository/         # Spring Data JPA Repositories
 │        ├── model/entity/       # JPA Entities (Product, Category, User, CartItem, Order, etc.)
 │        ├── security/           # Spring Security Config & classes
 │        └── config/             # Various configurations
 │
 ├── main/resources/
 │   ├── templates/               # Thymeleaf Views
 │   │    ├── fragments/          # Layout, header, footer...
 │   │    ├── cart/               # Shopping cart
 │   │    ├── product/            # Product pages, admin products
 │   │    ├── order/              # Checkout, confirmation
 │   │    └── ...
 │   ├── static/                  # CSS/JS (if needed)
 │   ├── application.properties   # Spring Boot Config
 │   ├── data.sql (optional)      # Initial data sets
 │   └── schema.sql (optional)
 │
 └── test/java/                   # Unit / Integration tests
```

---

## Prerequisites

* **Java 17+** (Spring Boot 3 requires Java 17 minimum).
* **Maven 3.8+** (or Gradle if the project build is configured that way).
* **Database**: MySQL / PostgreSQL / H2 (depending on your configuration).
* Optional: Docker + Docker Compose to quickly launch the DB.

---

## Installation & Quick Start

```bash
# 1. Clone the repo (or extract the zip)
$ git clone https://github.com/my-org/marketcore.git
$ cd marketcore

# 2. Configure the DB (see next section) in application.properties

# 3. Launch the app
$ ./mvnw spring-boot:run    # or mvn spring-boot:run

# 4. Access the app
# Front office: http://localhost:8080
# Back office:  http://localhost:8080/admin/products
```

To generate an executable jar:

```bash
mvn clean package -DskipTests
java -jar target/marketcore-*.jar
```

---

## Configuration (application.properties / .yml)

Example `src/main/resources/application.properties`:

```properties
# --- DB ---
spring.datasource.url=jdbc:mysql://localhost:3306/marketcore?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=secret
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

# --- Thymeleaf ---
spring.thymeleaf.cache=false

# --- App ---
app.currencySymbol=€
```

> **Tip for Thymeleaf price formatting**: use *literal* syntax to avoid escaping errors:
>
> ```html
> th:text="|${#numbers.formatDecimal(i.price,1,'COMMA',2,'POINT')} ${currencySymbol}|"
> ```

---

## Roles & Account Management

* **ADMIN**: full access (user management, products, categories, orders).
* **SELLER**: can manage their own products.
* **USER**: purchase, cart, personal orders.

Depending on your implementation, a `data.sql` script can create:

```sql
INSERT INTO users (id, email, password, full_name, enabled) VALUES (...);
INSERT INTO roles (id, name) VALUES (1,'ADMIN'), (2,'SELLER'), (3,'USER');
INSERT INTO users_roles (user_id, role_id) VALUES (...);
```

---

## 📧 Email Sending System

The project integrates an email sending system via **Mailjet** for automatic notifications.

### Configuration

Add the following properties in `application.properties`:

```properties
# --- Mailjet Configuration ---
mailjet.api.key=your_api_key
mailjet.secret.key=your_secret_key
mailjet.sender.email=noreply@marketcore.com
mailjet.sender.name=marketcore
app.base.url=https://marketcore.com
```

### Implemented Features

#### 1. 🔑 Password Reset Email

Sent automatically when a user requests to reset their password.

**Trigger**: Via the forgot password form

**Content**:
- Secure link with temporary token
- Link expires after 1 hour
- Professional design with clear instructions

**Code usage**:
```java
emailService.sendPasswordResetEmail(userEmail, resetToken);
```

#### 2. ✅ Order Confirmation Email

Sent automatically after each successfully validated order.

**Trigger**: Automatic after `orderService.placeOrder()`

**Content**:
- Order number
- Customer name
- Detailed list of ordered products (quantity × name - price)
- Total amount in EUR
- Order date and time
- "Next steps" section to inform the customer
- Order tracking button
- Professional design in marketcore colors

**Code usage**:
```java
emailService.sendOrderConfirmationEmail(
    clientEmail,
    orderNumber,
    customerName,
    totalAmount,
    orderDetails
);
```

**Example of order details**:
```
2x iPhone 13 - 2500.00 EUR
1x AirPods Pro - 450.00 EUR
3x USB-C Cable - 30.00 EUR
```

### Error Handling

The email system is designed not to block critical operations:

- If an email fails to send, the main operation (order, reset) continues normally
- Errors are logged for tracking and debugging
- Uses `@Slf4j` for structured logging

### Architecture

```
EmailService (interface)
  └── MailjetEmailService (implementation)
       ├── sendPasswordResetEmail()
       ├── sendOrderConfirmationEmail()
       └── Integrated HTML Templates
```

### HTML Templates

Emails use responsive HTML templates with:
- Modern and professional design
- Multi-email client compatibility
- marketcore brand colors
- CTA (Call-to-Action) buttons
- Footer with legal information

---

## Useful Commands

```bash
# Run tests
mvn test

# Formatting / checkstyle (if configured)
mvn spotless:apply

# Generate a jar
mvn clean package -DskipTests

# Run with specific profile
env SPRING_PROFILES_ACTIVE=prod java -jar target/marketcore.jar
```

---

## Roadmap / Improvement Ideas

* Product pagination & sorting.
* Product image upload (File storage / S3).
* Real payment method integration (Stripe, PayPal...).
* Internationalization (i18n) via messages.properties.
* Integration tests (MockMvc) + UI tests.
* Complete Dockerization (app + DB) with docker-compose.

---

## License & Credits

✅ Personal and professional use authorized.

🚫 Resale, redistribution or code publication prohibited.

🛠️ Installation support provided.
This support only covers help with initial project setup (installation, basic configuration, launch).

📦 Product sold as is.

