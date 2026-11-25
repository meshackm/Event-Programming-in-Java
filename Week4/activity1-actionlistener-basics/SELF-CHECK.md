# Activity 1 Self-Check -- ActionListener Basics

**Q1: What is the event source?**

The `JButton` called `clickButton`. It's what creates the `ActionEvent` when you click it.

**Q2: What is the event listener?**

The lambda `e -> {...}` I passed to `addActionListener`. It implements `ActionListener`.

**Q3: What is the event handler?**

The code inside the lambda body, so the `JOptionPane.showMessageDialog(...)` call.

**Q4: Where does `e` come from?**

Swing creates the `ActionEvent` and hands it to my method when the click happens. It holds the
source component, the action command, any modifier keys and a timestamp.

## Deliverables

- [x] `FirstButton.java`, a button that responds to clicks
- [x] `ExperimentButton.java`, the Step 1.3 experiments
