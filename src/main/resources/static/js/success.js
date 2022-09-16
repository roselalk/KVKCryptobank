let button = document.querySelector(".login")

button.addEventListener('click', () => {
  location.href = "login.html"
})

let toggleMode = document.querySelector(".toggle-mode");

toggleMode.addEventListener("click", () => {

  let light = document.querySelector(".light");
  light.toggleAttribute("disabled")
  let dark = document.querySelector(".dark");
  dark.toggleAttribute("disabled")


});
