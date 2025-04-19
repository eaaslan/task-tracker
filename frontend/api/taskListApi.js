export function createTaskList(taskList) {
  return $.ajax({
    url: "http://localhost:8080/task-list",
    method: "POST",
    contentType: "application/json",
    data: JSON.stringify(taskList),
  });
}

export function createTask(task, taskListId) {
  task["taskListId"] = Number(taskListId);
  return $.ajax({
    url: `http://localhost:8080/task-list/${taskListId}/tasks`,
    method: "POST",
    contentType: "application/json",
    data: JSON.stringify(task),
  });
}

export async function deleteTaskListTask(id) {
  const response = await fetch("http://localhost:8080/task/" + id, {
    method: "DELETE",
  });
  if (!response.ok) {
    throw new Error("Failed to delete the task");
  }
  return response.json();
}

export async function fetchTaskLists() {
  const response = await fetch("http://localhost:8080/task-list");
  if (!response.ok) {
    throw new Error("Network response was not ok");
  }
  return await response.json();
}

export async function fetchTaskList(id) {
  const response = await fetch("http://localhost:8080/task-list/" + id);
  if (!response.ok) {
    throw new Error("Network response was not ok");
  }
  return await response.json();
}
