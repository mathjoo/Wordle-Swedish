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
- Option to play again if correct word guessed or ran out of tries

---

## 👤 My Contributions

I worked primarily on the **user interface** and **game mechanics**, including:

- Implementing **GUI logic**:
  - Handling button presses to register letters
  - Designing the visual layout of letter boxes and keyboard
  - Adding functionality for Enter and Delete buttons
- Developing the **color feedback logic** to match guessed letters with the correct word
- Filtering the **Swedish word list** to enforce five-letter words
- Implementing the **Play Again** feature
- Handling edge cases in the **Add New Word** function

---

## 🚀 Running the Project

Make sure you have **Java** and **Maven** installed.

To run: 

1. Open a terminal and navigate to the main project directory.
2. Build the project:

```bash
mvn clean install
```

3. Run the project:

```bash
mvn exec:java
```
