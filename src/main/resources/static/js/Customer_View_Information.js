document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('myForm');
    const saveBtn = document.getElementById('saveBtn');
    const cancelBtn = document.getElementById('cancelBtn');
    let formOriginalState = new FormData(form);
  
    // Kiểm tra sự thay đổi của form
    form.addEventListener('input', function() {
      const currentFormState = new FormData(form);
      let isFormChanged = false;
  
      for (let [key, value] of currentFormState.entries()) {
        if (value !== formOriginalState.get(key)) {
          isFormChanged = true;
          break;
        }
      }
  
      // Kích hoạt hoặc vô hiệu hóa nút dựa trên thay đổi của form
      saveBtn.disabled = !isFormChanged;
      cancelBtn.disabled = !isFormChanged;
    });
  
    // Khi submit form, cập nhật lại trạng thái ban đầu của form
    form.addEventListener('submit', function(event) {

    });
  
    // Xử lý nút cancel
    cancelBtn.addEventListener('click', function() {
      form.reset(); // Đặt lại giá trị ban đầu của form
      saveBtn.disabled = true;
      cancelBtn.disabled = true;
    });
  });
  