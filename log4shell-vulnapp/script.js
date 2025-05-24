
document.addEventListener("DOMContentLoaded", () => {
  const input = document.getElementById("name");
  input.addEventListener("input", () => {
    console.log("Name field changed:", input.value);
  });
});
