/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', function () {
                const descriptionInput = document.getElementById('description');
                const charCount = document.getElementById('charCount');
                const errorMessage = document.getElementById('error-message');
                const feedbackForm = document.getElementById('feedbackForm');

                descriptionInput.addEventListener('input', function () {
                    let currentLength = descriptionInput.value.length;
                    console.log(currentLength);
                    charCount.textContent = `${currentLength}/200`;

                    if (currentLength > 200) {
                        descriptionInput.classList.add('error');
                        errorMessage.style.display = 'block';
                    } else {
                        descriptionInput.classList.remove('error');
                        errorMessage.style.display = 'none';
                    }
                });

                feedbackForm.addEventListener('submit', function (e) {
                    const currentLength = descriptionInput.value.length;

                    if (currentLength > 200) {
                        e.preventDefault();
                        descriptionInput.classList.add('error');
                        errorMessage.style.display = 'block';
                        return;
                    }

                    document.getElementById('date').value = new Date().toISOString().split('T')[0];

                    // Show overlay for success message
//                    e.preventDefault();
                    document.getElementById('overlay').style.display = 'flex';
                    setTimeout(function () {
                        document.getElementById('overlay').style.display = 'none';
                    }, 3000);
                });
            });
