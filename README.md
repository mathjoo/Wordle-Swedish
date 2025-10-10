# Wordle-Swedish

A Swedish-language version of the popular Wordle game, built in **Java** with **JavaFX** for the GUI and **Maven** for project management. The game challenges users to guess a five-letter Swedish word, with colored feedback for each guess, just like the original Wordle.

## 🛠️ Technologies Used

- **Java** – core game logic
- **JavaFX** – user interface (grid, buttons, feedback)
- **Maven** – project management and dependency handling

---

## 🎮 Features

- Color-coded feedback for guessed letters (green, yellow, gray)
- Filtered Swedish word list, only valid five-letter words are accepted
- Add new words to the dictionary via the "+" button
- Reveal the correct word by pressing the **second letter box from the left** on the **second row from the bottom**
- Functional virtual keyboard with:
  - Letter input buttons
  - Enter button to submit a guess
  - Delete button to remove a letter

---

## 👤 My Contributions

I worked primarily on the **user interface** and **game mechanics**, including:

- Implementing the **GUI logic**:
  - How button presses register letters
  - Visual layout of letter boxes and keyboard
  - Functionality for Enter and Delete buttons
- Writing the **logic for color feedback** (matching guessed letters to the correct word)
- Filtering the **Swedish word list** to allow only five-letter words

---

## 🚀 Running the Project

Make sure you have **Java** and **Maven** installed.

To run:

```bash
mvn clean install
```

and then

```bash
mvn exec:java
```
