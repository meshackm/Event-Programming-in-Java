# Activity 4 Self-Check -- Menus, Toolbars and Dialogs

The menu nests like this: `JMenuBar` holds the `JMenu`s ("File", "Edit", "Help"), and each
`JMenu` holds `JMenuItem`s with separators between groups. The bar goes on with
`frame.setJMenuBar(...)`, not `add`.

| Dialog | What it's for |
|---|---|
| `showMessageDialog` | Telling the user something |
| `showConfirmDialog` | Asking yes or no |
| `showInputDialog` | Asking for a value |
| `JFileChooser.showOpenDialog` | Picking a file to read |
| `JFileChooser.showSaveDialog` | Picking somewhere to write |

**Q1: What's the difference between a `JMenu` and a `JMenuItem`?**

A `JMenu` is the container that drops down, usually one of the top-level entries like "File". A
`JMenuItem` is a thing you click inside it, like "Save", and it fires an `ActionEvent` the same way
a button does. Because `JMenu` is itself a subclass of `JMenuItem`, you can put a `JMenu` inside
another one to get a submenu.

**Q2: When do you use `showOpenDialog` and when `showSaveDialog`?**

Open is for picking a file that already exists. Its button says "Open" and it expects the file to be
there. Save is for picking where to write, so its button says "Save" and typing a filename that
doesn't exist yet is fine.

**Q3: What does `showConfirmDialog` return?**

An `int` that matches `YES_OPTION`, `NO_OPTION`, `CANCEL_OPTION` or `CLOSED_OPTION`. The last
one is when the user closes the dialog with the X. Compare against the constants rather than
raw numbers, and treat `CLOSED_OPTION` as a no.

## Fix I had to make to the guide's listing

The guide declares `saveAction` before `saveAsAction`, but `saveAction` calls
`saveAsAction.run()`. Java won't let a local variable refer to another one declared further down
the same block, so the code as printed doesn't compile. I swapped the two declarations round so
`saveAsAction` comes first. Nothing else changed.

## What I tested

Typing updates the word count live. Save As writes the file and updates the title and status bar,
Open reads it back, and Save on an already-named file just overwrites it. Save on a new
document falls through to Save As. Exit asks first, About shows the dialog, and Cut, Copy and
Paste work on the selection.

## Deliverables

- [x] `MiniEditor.java`, opens, edits and saves real text files
