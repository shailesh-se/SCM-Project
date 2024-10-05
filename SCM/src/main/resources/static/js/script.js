// Theme initialization based on localStorage
if (
  localStorage.getItem("color-theme") === "dark" ||
  (!("color-theme" in localStorage) &&
    window.matchMedia("(prefers-color-scheme: dark)").matches)
) {
  document.documentElement.classList.add("dark");
} else {
  document.documentElement.classList.remove("dark");
}

const themeToggleDarkIcon_2 = document.getElementById(
  "theme-toggle-dark-icon-2"
);
const themeToggleLightIcon_2 = document.getElementById(
  "theme-toggle-light-icon-2"
);

// Change the icons inside the button based on previous settings
if (
  localStorage.getItem("color-theme") === "dark" ||
  (!("color-theme" in localStorage) &&
    window.matchMedia("(prefers-color-scheme: dark)").matches)
) {
  themeToggleLightIcon_2.classList.remove("hidden");
} else {
  themeToggleDarkIcon_2.classList.remove("hidden");
}

// Add event listener to the button for theme toggle
const themeToggleBtn_2 = document.getElementById("theme_change_button_2");
themeToggleBtn_2.addEventListener("click", function () {
  // Toggle icons
  themeToggleDarkIcon_2.classList.toggle("hidden");
  themeToggleLightIcon_2.classList.toggle("hidden");

  // Sync theme changes
  sync_theme();
});

// Function to sync theme changes to localStorage and document
function sync_theme() {
  if (localStorage.getItem("color-theme")) {
    if (localStorage.getItem("color-theme") === "light") {
      document.documentElement.classList.add("dark");
      localStorage.setItem("color-theme", "dark");
    } else {
      document.documentElement.classList.remove("dark");
      localStorage.setItem("color-theme", "light");
    }
  } else {
    if (document.documentElement.classList.contains("dark")) {
      document.documentElement.classList.remove("dark");
      localStorage.setItem("color-theme", "light");
    } else {
      document.documentElement.classList.add("dark");
      localStorage.setItem("color-theme", "dark");
    }
  }
}
