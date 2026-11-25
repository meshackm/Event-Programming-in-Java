# Activity 1 Self-Check -- JList and Custom Renderers

The way this works: `setCellRenderer()` installs the renderer, then Swing calls
`getListCellRendererComponent()` once for each visible row every time it repaints. You hand back
the `Component` it should draw in that row. Calling `super` first gets you the normal selection
colours and font, so you only have to describe what you're changing.

**Q1: What does a custom renderer do?**

It decides how each row looks. Instead of plain text you can add an icon, change the colour or
font, or return a whole panel with several labels in it, which is enough to build something like
an email row with the sender, subject, preview and date.

**Q2: How often does the renderer get called?**

Once per visible row per repaint, so it can easily be hundreds of times a second while you're
scrolling. That means it has to be fast, and you shouldn't be creating images or loading anything
inside it. Worth knowing too: there's only one renderer object for the whole list, not one per
row, so it can't store anything about a specific row.

**Q3: Why does `!e.getValueIsAdjusting()` matter?**

One click or drag fires a whole run of events with `getValueIsAdjusting()` returning `true`, then
a final one returning `false`. Checking for `false` means my handler runs once on the final
selection instead of once for every step on the way there.

## Deliverables

- [x] `ContactListDemo.java`, contacts with green, orange and grey status dots, and the
      selection shown underneath
