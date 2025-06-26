# §dNovaConfig: How the Example Config Works

§bThis tutorial shows how the in-game UI is powered by code. Every setting you see is created using NovaConfig's elegant Kotlin DSL. Let’s break it down step by step.


## §dGeneral

This category is defined with:

- category("General") { ... }

Inside it, we use three buttons and a text paragraph.

### §aIntroduction Paragraph

- Defined using textparagraph { ... }
- Displays static text about NovaConfig using color codes and \\n for line breaks.

### §aLink Buttons

Each of these is created with button { ... } and includes:

- configName – internal ID for the element
- name – label shown in the UI
- description – explanation shown below the name
- onclick { ... } – opens a link in the browser or prints a fallback message if unsupported


## §dExample

This category uses category("Example") { ... } and contains multiple element types.

### §aSubcategories

- Created using subcategory("General")
- Just a visual divider to group related settings

### §aParagraph Element

- Another textparagraph, this time with longer wrapped content
- Demonstrates how description can contain long-form multiline text


### §aToggle: Do Something

- Built with toggle { ... }
- Stores a boolean (true/false) setting
- default = true means it starts enabled

### §aConditional Toggle

- Also a toggle, but with shouldShow { it["do_something"] }
- This makes it only appear if the first toggle is enabled
- This works for every element!


### §aColor Picker

- Created using colorpicker { ... }
- Uses RGBA values, with default = rgba(255, 255, 255, 255)


### §aButton with Feedback

- Uses onclick { ... } to show a message via chatutils.clientMsg(...)


### §aText Input

- Built with textinput { ... }
- Includes a placeholder = "..." and a live onvaluechange { string -> ... } callback


### §aKeybind

- Built using keybind { ... }
- Stores a key code integer; default = 80 is the §eP§r key


### §aDropdown

- Created using dropdown { ... }
- Takes a list of string options with options = listOf("...")
- Default selection is index 0


### §aSlider

- Created using slider { ... }
- Defines a continuous float range with min, max, and default


### §aStep Slider

- Created using stepslider { ... }
- Uses min, max, step = 2 to lock input to discrete values like 2, 4, 6, etc.


## §dTutorial (Markdown)

The final tab is a §lmarkdowncatagory§r loaded from:

- markdowncatagory(name = "Tutorial", markdown = FileUtils.loadMarkdown("example.md"))

You can use this for guides like this one! Just write the content in a .md file using Nova-friendly syntax like #§d, and it will render as part of the config UI.
