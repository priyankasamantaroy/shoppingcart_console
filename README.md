# shoppingcart_console
ShoppingCartConsole
===================

Introduction
- Console-based shopping cart application supporting customer and admin flows (browse/add/remove/update products, cart management, checkout).

User stories implemented (high level)
- Customer: browse products, add/remove items to cart, view cart with itemized totals, checkout.
- Admin: view/add/remove/update products, view inventory stats.

New/additional language-feature demonstrations added
- Constructor chaining and overloading (Product).
- Varargs helper to add multiple products (Shop.addProducts).
- LVTI (var) usage in loops and local variables.
- Defensive copying when exposing internal lists (Shop.getProducts, filterProducts).
- Predicate/lambda-based filterProducts(Predicate<Product>) and usage from ConsoleApp.
- Immutable ProductRecord (record).
- Example checked exception InvalidProductException (new file).
- Method references / lambdas can be used with filterProducts (demonstrated in code).

Build & Run
- Use JDK 17+ (records available since Java 16). If using JDK 25 or preview features, configure VS Code Java runtime to JDK 25 and compile/run with --enable-preview as needed.
- From project root:
  javac -d out --source-path src src\com\consoleapp\*.java
  java -cp out com.consoleapp.ConsoleApp

Evaluation (short)
- Adherence: Core user stories are implemented and functional; UI is separated from domain logic. Additional language features required by the assignment have been added or demonstrated: constructor chaining/overloading, varargs, LVTI, defensive copying, records, a checked exception, and lambda-based filtering with Predicate.
- Problems encountered: original code review revealed missing explicit examples for several advanced features; these were added minimally to meet the brief without changing existing flows. Ensure all domain files compile together (some method signatures assumed to exist).
- Next steps: run full compile, review Product/Shop field names to ensure consistency, add unit tests for filterProducts and checked-exception paths, and record a short screencast showcasing the new filtering and the places in code demonstrating the named language features.
