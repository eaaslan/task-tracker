import { renderTaskListsPage } from "./pages/taskListsPage.js";
import { setAddTaskListEvents } from "./pages/addTaskListPage.js";

$(() => {
  const init = async () => {
    buildHtmlStructure();
    await renderTaskListsPage();
    setAddTaskListEvents(buildHtmlStructure);
  };
  const buildHtmlStructure = () => {
    const html = `
      <div class="task-tracker-container">
        <h2 id="task-tracker-header">Task Tracker</h2>
        <div class="taskList-wrapper">
          <h3>Task Lists</h3>
          <div class="taskList-items"></div>
        <button class="add-taskList-btn"> Add Task List</button>
        </div>
      </div>
    `;
    $("#app").html(html);
  };
  init();
});
