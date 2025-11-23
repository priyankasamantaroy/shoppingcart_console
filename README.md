# shoppingcart_console
ShoppingCartConsole
===================

Lightweight console shopping cart application implementing customer and admin flows (browse/add/remove/update products, cart management, checkout). The code is intentionally small and self-contained so it can be used for demos, exercises, or quick manual testing.

Quick links to important classes
- Entry point: [`com.consoleapp.ConsoleApp`](src/com/consoleapp/ConsoleApp.java) — main menu, input handling, and UI flow.
- Domain & store: [`com.consoleapp.Shop`](src/com/consoleapp/Shop.java), [`com.consoleapp.Product`](src/com/consoleapp/Product.java), [`com.consoleapp.Category`](src/com/consoleapp/Category.java).
- Users: [`com.consoleapp.Customer`](src/com/consoleapp/Customer.java), [`com.consoleapp.Admin`](src/com/consoleapp/Admin.java), [`com.consoleapp.User`](src/com/consoleapp/User.java).
- Cart: [`com.consoleapp.Cart`](src/com/consoleapp/Cart.java).
- Product management API: [`com.consoleapp.ProductManager`](src/com/consoleapp/ProductManager.java).
- Domain exception: [`com.consoleapp.InvalidProductException`](src/com/consoleapp/InvalidProductException.java).

Key features and language demos
- Console-driven customer and admin flows with simple menus in [`com.consoleapp.ConsoleApp`](src/com/consoleapp/ConsoleApp.java).
- Defensive input handling and validation via helper methods in the console UI.
- Domain validation and a checked exception for inventory operations: see [`com.consoleapp.Shop.reserveProduct`](src/com/consoleapp/Shop.java) and [`com.consoleapp.InvalidProductException`](src/com/consoleapp/InvalidProductException.java).
- Modern Java idioms demonstrated: varargs, LVTI (`var`), streams/lambdas, defensive copying, immutable record-like Product (see [`com.consoleapp.Product`](src/com/consoleapp/Product.java)), interface default/static/private methods in [`com.consoleapp.ProductManager`](src/com/consoleapp/ProductManager.java).

Exception handling approach (summary)
- UI-level try/catch blocks around user actions to prevent crashes and provide friendly messages; see [`com.consoleapp.ConsoleApp`](src/com/consoleapp/ConsoleApp.java).
- Domain errors use a checked exception for invalid inventory operations (`com.consoleapp.InvalidProductException`) — callers catch and handle to allow retry or show an error message: see [`com.consoleapp.Shop.reserveProduct`](src/com/consoleapp/Shop.java).
- Input validation is defensive: the console loop validates numeric ranges and re-prompts (helper in [`com.consoleapp.ConsoleApp`](src/com/consoleapp/ConsoleApp.java)).
- Not found results return null from search methods (e.g., [`com.consoleapp.Shop.searchProductById`](src/com/consoleapp/Shop.java)) and callers explicitly check for null before proceeding.
- Concurrency safety for stock updates: stock decrement uses synchronization in [`com.consoleapp.Shop.reserveProduct`](src/com/consoleapp/Shop.java).

Build & run
- Recommended JDK: 17+ 
- Compile and run from project root:
  - Compile:
    javac -d out --source-path src src\com\consoleapp\*.java
  - Run:
    java -cp out com.consoleapp.ConsoleApp

Project file list (sources & config)
- [.gitignore](.gitignore)
- [README.md](README.md)
- [.vscode/settings.json](.vscode/settings.json)
- [.github/java-upgrade/20251108215056/progress.md](.github/java-upgrade/20251108215056/progress.md)
- [.github/java-upgrade/20251108215056/logs/](.github/java-upgrade/20251108215056/logs/)
- [.github/java-upgrade/20251112214703/progress.md](.github/java-upgrade/20251112214703/progress.md)
- [.github/java-upgrade/20251112214703/logs/](.github/java-upgrade/20251112214703/logs/)
- [.github/java-upgrade/20251112214707/logs/](.github/java-upgrade/20251112214707/logs/)
- [src/com/consoleapp/Admin.java](src/com/consoleapp/Admin.java)
- [src/com/consoleapp/Cart.java](src/com/consoleapp/Cart.java)
- [src/com/consoleapp/Category.java](src/com/consoleapp/Category.java)
- [src/com/consoleapp/ConsoleApp.java](src/com/consoleapp/ConsoleApp.java)
- [src/com/consoleapp/Customer.java](src/com/consoleapp/Customer.java)
- [src/com/consoleapp/InvalidProductException.java](src/com/consoleapp/InvalidProductException.java)
- [src/com/consoleapp/Product.java](src/com/consoleapp/Product.java)
- [src/com/consoleapp/ProductManager.java](src/com/consoleapp/ProductManager.java)
- [src/com/consoleapp/Shop.java](src/com/consoleapp/Shop.java)
- [src/com/consoleapp/User.java](src/com/consoleapp/User.java)

Notes and next steps
- Add unit tests for filter and checked-exception paths (target [`com.consoleapp.Shop.filterProducts`](src/com/consoleapp/Shop.java) and [`com.consoleapp.InvalidProductException`](src/com/consoleapp/InvalidProductException.java)).
- Consider centralized logging (instead of only console output) for easier debugging.
- If you plan to enable preview language features, update build flags (e.g., --enable-preview) and ensure your runtime matches the compiler setting.



