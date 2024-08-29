<h1>JAVA-asciimenusLib</h1>

**JAVA-asciimenusLib** is an automatic ASCII menus library designed to simplify the creation of text-based menus in Java applications. This library provides two main classes: `MainMenu` and `SubMenu`, which allow developers to create interactive menus that can execute methods or run specific classes.

---

### Installation
Include the library in your Java project by copying the source files from the repository.

### Usage

#### MainMenu
The `MainMenu` class is designed to display a menu of options and execute the `run` method of the selected option. The options are passed as an array of objects, where each object must have a public `run(InputStream inputStream)` method.

**Constructors:**
- `MainMenu(Object[] options)`
- `MainMenu(String name, Object[] options)`
- `MainMenu(String name, String pattern, Object[] options)`

**Methods:**
- `void run(InputStream inputStream)`: Executes the selected option's `run` method.

**Example:**
```java
Object[] options = {new Option1(), new Option2()};
MainMenu menu = new MainMenu("Main Menu", "Option ", options);
menu.run(System.in);
```

#### SubMenu
The `SubMenu` class creates a menu that lists methods of a specified object, allowing users to execute them directly. Custom options can be added, and certain methods can be hidden from the menu.

**Constructors:**
- `SubMenu(Object object)`
- `SubMenu(String name, Object object)`
- `SubMenu(String name, Object object, String[] customOptions)`
- `SubMenu(String name, Object object, String[] customOptions, String[] hiddenOptions)`

**Methods:**
- `int run(InputStream inputStream)`: Displays the menu and executes the selected method.

**Example:**
```java
String[] customOptions = {"Custom Option"};
SubMenu submenu = new SubMenu("Sub Menu", this, customOptions);
int option = 0;
while(option != submenu.getExit()) {
    option = submenu.run(System.in);
    // Handle custom options if any
}
```

#### Interface: `ITitlePrinter`
- **Method:** `void printTitle(String name)`: Prints the title of the menu.

### Contributing
Feel free to fork this repository and submit pull requests.

### License
This project is open-source, licensed under the MIT License.
