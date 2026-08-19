## Reflection & Conceptual Review

### 1. Why must developers avoid relying on placeholder attributes as a replacement for explicit `<label>` elements?

A placeholder is not a good replacement for a label because it disappears once the user starts typing. A label stays on the screen and lets the user know what information belongs in that field. Labels also make forms more accessible for people who use screen readers.

### 2. If an `<input>` element has an `id` attribute but lacks a `name` attribute, what occurs during form submission to the server?

The `id` helps identify the input on the webpage, but the `name` is what identifies the data when the form is sent to the server. If the input does not have a `name`, the information entered into that input will not be included when the form is submitted.

### 3. What is the technical difference between using a `<select>` dropdown versus an `<input>` paired with a `<datalist>`?

A `<select>` makes the user choose from the options that are provided. An `<input>` with a `<datalist>` gives the user suggestions, but they can still type their own answer if none of the suggestions fit.

### 4. Why does setting `<form method="put">` fail to dispatch an HTTP PUT request in native HTML?

Native HTML forms do not support PUT as a form method. HTML forms normally use GET or POST when sending data to a server. If an application needs to make a PUT request, it would need another approach instead of relying on the form's `method` attribute.
