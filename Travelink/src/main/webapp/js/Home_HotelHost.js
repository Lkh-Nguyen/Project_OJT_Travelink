/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener("DOMContentLoaded", function () {
    const profile = document.querySelector(".profile");
    const dropdownMenu = document.getElementById("dropdownMenu");

    profile.addEventListener("click", function () {
        dropdownMenu.classList.toggle("show-menu");
    });

    window.addEventListener("click", function (event) {
        if (!event.target.matches(".profile") && !event.target.matches(".profile *")) {
            dropdownMenu.classList.remove("show-menu");
        }
    });
});
