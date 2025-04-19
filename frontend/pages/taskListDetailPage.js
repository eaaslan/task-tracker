import { fetchTaskList } from "../api/taskListApi.js";

export async function renderTaskListPage(id) {
  const taskListData = await fetchTaskList(id);
  let tasksHTML = ``;
  if (Array.isArray(taskListData.tasks)) {
    tasksHTML = taskListData.tasks.map((task) => {
      return /* HTML */ ` <li class="task" data-id=${task.id}>
        ${task.title} - ${task.status}
        <button class="delete-task-btn">Delete</button>
      </li>`;
    });
  }
  const html = /* HTML */ `
    <div class="taskList-container" data-tasklist-id="${id}">
      <h3>${taskListData.title}</h3>
      <p>${taskListData.description ?? ""}</p>
      <div class="progress">
        Progress: ${!isNaN(taskListData.progress) ? taskListData.progress : "0"}
      </div>
      <div class="count">Total task: ${taskListData.count}</div>

      <div class="tasklist-tasks-container">
        <ul>
          ${tasksHTML}
        </ul>
      </div>
      <button id="back-btn">⬅ Back</button>
      <button id="add-task-btn">Add Task</button>
    </div>
  `;

  $("#app").html(html);
}
