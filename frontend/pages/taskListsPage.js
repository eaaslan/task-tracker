import { fetchTaskLists } from "../api/taskListApi.js";

export async function renderTaskListsPage() {
  try {
    const data = await fetchTaskLists();
    // Clear existing content first
    $(".taskList-items").empty();

    if (data && data.length > 0) {
      data.forEach((taskList) => {
        const currentTaskList = /* HTML */ `
          <div class="taskList" data-id="${taskList.id}">
            <div class="title">${taskList.title}</div>
            <div class="actions"></div>
          </div>
        `;
        $(".taskList-items").append(currentTaskList);
      });
    } else {
      $(".taskList-items").html(
        "<p>No task lists found. Create one to get started!</p>"
      );
    }
  } catch (error) {
    console.error("Error rendering task lists:", error);

    $(".taskList-items").html(
      '<p class="error">Failed to load task lists. Please try again.</p>'
    );
  }
}
