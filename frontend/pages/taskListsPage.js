import { fetchTaskLists } from '../api/taskListApi.js';

export function renderTaskListsPage() {
  $('#app').html('<h1>Task Lists</h1><div id="task-list-container"></div>');

  fetchTaskLists().then(taskLists => {
    taskLists.forEach(list => {
      $('#task-list-container').append(`
        <div class="task-list">
          <h3>${list.title}</h3>
          <p>${list.description || ''}</p>
        </div>
      `);
    });
  });
}
