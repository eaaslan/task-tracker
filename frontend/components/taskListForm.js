export function renderTaskListForm() {
    return `
    <form id="taskListForm">
      <h3>Add Task List</h3>
      <input type="text" id="taskList-title" placeholder="Title" required>
      <textarea id="taskList-description" placeholder="Description"></textarea>
      <div class="form-buttons">
        <button id="submit-taskList" type="submit">Save</button>
        <button id="cancel-btn" type="button">Cancel</button>
      </div>
    </form>
  `;
}