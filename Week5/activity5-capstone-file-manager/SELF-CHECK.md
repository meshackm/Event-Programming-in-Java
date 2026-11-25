# Activity 5 Self-Check -- Capstone File Manager

**Q1: How do the tree on the left and the table on the right talk to each other?**

Through a `TreeSelectionListener` on the tree. When you select a node, the listener grabs the
`File` out of it, checks it's a directory, and calls `loadFolderContents(folder)`, which empties
the table model and refills it. Neither component holds a reference to the other, so that one
method call is the only thing joining them. I could swap out either side without touching the
other.

**Q2: Why call `tableModel.setRowCount(0)` before loading the new files?**

To empty the table first. Without it `addRow` just appends, so you'd end up with the new folder's
files stacked underneath the last folder's and the table would grow every time you clicked
around.

**Q3: What does `JSplitPane` do?**

Holds two components with a divider between them that the user can drag to change how much
space each one gets. `setDividerLocation(280)` sets where it starts.

## What works

| Feature | Behaviour |
|---|---|
| Menu and toolbar | File and Help menus; Home, Up Folder and Refresh buttons |
| Tree | Folder hierarchy starting at the home directory |
| Table | Name, Type, Size and Modified for the selected folder, read-only |
| Status bar and title | Item count, and the current full path |
| Double-click | A folder navigates into it, a file pops up "Would open: /path" |

## Two changes from the guide's listing

**The tree only goes three levels deep now.** The guide's `buildFolderTree(File)` walks the whole
home directory before the window even appears. On my machine that took long enough to look
like the app had hung, and because `listFiles()` follows symlinks, a symlink pointing back up at
a parent makes the recursion never end and you get a `StackOverflowError`. I added a depth
argument with `TREE_DEPTH = 3`, which starts instantly and can't run away. The proper fix
would be loading each node lazily with a `TreeWillExpandListener` when the user expands it, but
that's more than the activity asks for.

**I added a tree cell renderer so you see file names.** `DefaultMutableTreeNode` labels a node
using `userObject.toString()`, and for a `File` that's the full absolute path. So every row in the
guide's tree reads `/home/user/Documents/Projects` instead of just `Projects`. The renderer uses
`file.getName()` instead, and falls back to the absolute path for a filesystem root, because
`getName()` on those returns an empty string.

## Deliverables

- [x] `FileManager.java`, browsing the real filesystem with a tree, table, menu, toolbar and
      status bar
