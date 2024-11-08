# ProduitFX

A simple JavaFX Maven project to add products to a MySQL database.

### Database Setup

1. **Create the database**:

   ```sql
   CREATE DATABASE IF NOT EXISTS produfx;
   USE produfx;

   CREATE TABLE IF NOT EXISTS COMMERCIAL (
        matricule VARCHAR(20) PRIMARY KEY,
        nom VARCHAR(50) NOT NULL,
        prenom VARCHAR(50) NOT NULL,
        adresse VARCHAR(100),
        tel VARCHAR(15),
        email VARCHAR(50)
   );

   CREATE TABLE IF NOT EXISTS PRODUIT (
        id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(100) NOT NULL,
        description TEXT,
        price DECIMAL(10, 2) NOT NULL,
        commercial_matricule VARCHAR(20),
        FOREIGN KEY (commercial_matricule) REFERENCES commercial(matricule) ON DELETE SET NULL 
   );
   ```

2. **Change the port** in `util/DatabaseConnection.java` (default is `3306`):

   ```java
   private static final String URL = "jdbc:mysql://localhost:4306/produfx";
   ```

### Cloning the Repo

1. **Clone the repository**:

   ```bash
   git clone https://github.com/Adamo08/ProduitFX.git
   ```

2. **Navigate into the project directory**:

   ```bash
   cd produitFX
   ```

3. **Run the project**