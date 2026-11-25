# Week 5 Final Self-Assessment

## LO1 — Create and use advanced Swing components — 5 / 5

| Component | What it's for |
|---|---|
| `JList` | A flat list you can select from. Contacts, a playlist, an inbox |
| `JTable` | Rows and columns, editable if you want. A bank statement, an inventory |
| `JTree` | Data nested inside data. A folder structure, a project sidebar |
| `JTabbedPane` | Several views as tabs, one showing at a time |

The thing they have in common is that none of them hold their own data. It lives in a model
(`ListModel`, `TableModel`, `TreeModel`), the component draws whatever is in the model, and
changing the model fires an event that makes the display catch up. I used them separately in
`ContactListDemo`, `StudentTableDemo` and `FileExplorerDemo`, then together in the capstone.

## LO2 — Implement custom renderers — 5 / 5

**How do you change how a `JList` row looks?** Call `setCellRenderer()` and pass a subclass of
`DefaultListCellRenderer` that overrides
`getListCellRendererComponent(list, value, index, isSelected, cellHasFocus)`. Call `super` first so
you keep the normal selection colours and font, then change the `JLabel` it gives you back (icon,
colour, border) and return it.

It's the same idea for the others. For a `JTable` you call `setCellRenderer()` on a specific column
from `table.getColumnModel().getColumn(i)` with a `DefaultTableCellRenderer` subclass, and for a
`JTree` it's a `DefaultTreeCellRenderer` subclass.

I wrote three renderers this week: the coloured status dots in the contact list (using a small
custom `Icon`), red text for GPAs under 3.0 in the student table, and file names instead of full
paths in the capstone's tree.

Two things about renderers I want to remember. Swing calls them for every visible cell on every
repaint, so they have to be cheap. And there's one renderer object shared by every cell, so it
can't hold onto anything about a particular row. Everything it needs comes in as a parameter.

## LO3 — Create dialogs, menus and toolbars — 5 / 5

The four I used most:

1. `JOptionPane.showMessageDialog(parent, message, title, type)` to tell the user something
2. `JOptionPane.showConfirmDialog(parent, message, title, optionType)` to ask yes or no, which
   returns an `int`
3. `JOptionPane.showInputDialog(parent, message)` to ask for a value, which returns a `String`
   or `null` if they cancelled
4. `JFileChooser.showOpenDialog(parent)` and `showSaveDialog(parent)` to pick a file

Menus go `JMenuBar` then `JMenu` then `JMenuItem`, and the bar is installed with
`frame.setJMenuBar(menuBar)`. Since `JMenu` extends `JMenuItem` you can nest one inside
another for a submenu. Toolbars are just a `JToolBar` with normal buttons in it, added to
`BorderLayout.NORTH`.

`MiniEditor` does all of this and really does open and save files through a `JFileChooser`, and the
capstone has File and Help menus. In both of them I wrote each command once and attached it
to both the menu item and the toolbar button, so the two can't end up doing different things.

## LO4 — Build a multi-pane application — 5 / 5

**Did the capstone `FileManager` run?** Yes, and it browses the real filesystem. The tree shows
the folders under my home directory, selecting one loads its contents into the table,
double-clicking a folder row goes into it, Home and Up Folder move around, Refresh re-reads,
and the status bar and title show the item count and current path.

I did have to fix two things in the guide's version before it was usable. The tree walked the
whole home directory up front, which was slow and could recurse forever through a symlink, and
every row in the tree showed a full absolute path because `DefaultMutableTreeNode` labels
nodes with `File.toString()`. Both are written up in
`activity5-capstone-file-manager/SELF-CHECK.md`.

**All four ratings are 4 or 5, so the Week 5 outcomes are met.**

## Reflection

If Week 4 was about events, this week was about models. Every component here keeps its data in
a separate object, and that's what made the capstone manageable. The tree and the table don't
share any code and neither one knows the other exists. The only thing connecting them is a
single `loadFolderContents(File)` call from a selection listener.

The renderer is the same kind of separation but for how things look. It took me a while to stop
thinking of a renderer as a widget per row. It isn't. It's one function Swing calls for each visible
cell to ask what that cell should look like. Once that clicked, both of the rules made sense at the
same time: that's why it has to be fast, and that's why it can't store anything.

The components themselves were mostly just learning which one to reach for. Flat list, use
`JList`. Rows and columns, use `JTable`. Nested, use `JTree`. The habit I'd like to keep is asking
where the data is going to live before worrying about which component draws it.
