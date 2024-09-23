
const container = document.getElementById('container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

registerBtn.addEventListener('click', () => {
    container.classList.add("active");
});

loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});



///Nút hiện mật khẩu tắt trong Customer_LoginRegister
function togglePasswordVisibility(inputId, spanId) {
    const passwordField = document.getElementById(inputId);
    const toggleIcon = document.getElementById(spanId);

    if (passwordField.type === "password") {
      passwordField.type = "text";
      toggleIcon.innerHTML = "👁️"; // Change to eye-off icon
    } else {
      passwordField.type = "password";
      toggleIcon.innerHTML = "&#128065;"; // Change back to eye icon
    }
  }
/////////////////////////////////////////////////////////////
