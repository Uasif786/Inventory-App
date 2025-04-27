# Inventory App – Umar Asif

## Project Overview
The **Inventory Management App** was developed to help users track, add, update, and delete inventory items easily. The app provides an intuitive interface and uses a local SQLite database to store item information persistently.  

It addresses the user needs for:
- Quickly viewing available inventory
- Easily adding new products
- Editing product details
- Deleting outdated or incorrect entries

---

## App Screens and Features
- **Login Screen:** Secure user authentication to access inventory.
- **Inventory Dashboard:** Displays all inventory items in a grid layout (`RecyclerView`).
- **Add/Edit/Delete Items:** Simple buttons and input fields to manage inventory.

The UI was built to be **user-centered**, with clear labels, easy navigation, and responsive design for different screen sizes.  
Success was achieved by minimizing user effort for critical actions and maintaining a consistent, clean visual style.

---

## Development Approach
- Built a `DBHelper` class first to handle all SQLite database operations.
- Developed modular activities (`LoginActivity`, `InventoryActivity`) to separate responsibilities.
- Used an `InventoryAdapter` with `RecyclerView` to display and update inventory dynamically.
- Followed **incremental testing** after completing each feature to catch issues early.
- Maintained clean, organized code for easy future maintenance.

These strategies promote faster development, easier debugging, and scalable codebases for future Android projects.

---

## Testing and Validation
Testing included:
- User login/logout validation
- Item addition, editing, and deletion
- Data persistence check in SQLite database
- Layout and functionality verification on different screen sizes

Testing is critical because it ensures a smooth, bug-free user experience and verifies that both frontend (UI) and backend (data storage) work as intended.

---

## Challenges and Innovations
A challenge was dynamically updating the inventory list after edits or deletions.  
To solve this, I implemented efficient in-memory list updates and `notifyDataSetChanged()` calls to refresh the `RecyclerView` seamlessly without reloading the entire activity.

This ensured a smooth, real-time inventory experience for the user.

---

## Knowledge and Skills Demonstrated
This project showcased my ability to:
- Integrate **SQLite databases** with Android apps
- Design and manage dynamic UIs using **RecyclerView** and custom **Adapters**
- Apply **user-centered design principles**
- Write **modular, maintainable code**
- Perform thorough **functionality testing**

It reflects my growing experience and technical skill in **Android app development**.

---

**Developed by:** Umar Asif  
**Project:** Inventory Management System  
**Language:** Java  
**Database:** SQLite
