export function renderCreateTaskPage() {
  const page = $(".taskList-container");
  const html = /* HTML */ `
    <h2>Create task</h2>
    <form id="create-task-form">
      <label for="title">Title:</label>
      <input type="text" id="title" name="title" required />

      <label for="description">Description:</label>
      <textarea id="description" name="description"></textarea>

      <label for="dueDate">Due Date:</label>
      <input type="datetime-local" id="dueDate" name="dueDate" />

      <fieldset>
        <legend>Priority:</legend>
        <label
          ><input type="radio" name="priority" value="LOW" checked /> Low</label
        >
        <label
          ><input type="radio" name="priority" value="MEDIUM" /> Medium</label
        >
        <label><input type="radio" name="priority" value="HIGH" /> High</label>
      </fieldset>

      <fieldset>
        <legend>Status:</legend>
        <label
          ><input type="radio" name="status" value="OPEN" checked /> Open</label
        >
        <label
          ><input type="radio" name="status" value="CLOSED" /> Closed</label
        >
      </fieldset>

      <button class="create-task-btn" type="submit">Create Task</button>
      <button class="cancel-create-task-btn" type="button">Cancel</button>
    </form>
  `;
  page.html(html);
}
